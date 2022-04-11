package dev.matthias.api;

import dev.matthias.data.CourseDAO;
import dev.matthias.data.CourseDAOPostgres;
import dev.matthias.data.StudentDAO;
import dev.matthias.data.StudentDAOPostgres;
import dev.matthias.entities.Student;
import dev.matthias.services.StudentService;
import dev.matthias.services.StudentServiceImpl;
import dev.matthias.utilities.LogLevel;
import dev.matthias.utilities.Logger;
import dev.matthias.utilities.RegexUtil;
import java.util.InputMismatchException;
import dev.matthias.utilities.List;

import java.util.Locale;
import java.util.Scanner;

public class StudentPrompt {

    Student student;
    StudentService service;
    CourseDAO cDao;
    StudentDAO sDao;

    public StudentPrompt(Student student) {
        this.student = student;
        this.service = new StudentServiceImpl();
        this.cDao = new CourseDAOPostgres();
        this.sDao = new StudentDAOPostgres();
    }

    public void mainMenu() {
        System.out.println( "===================Student_Options=================\n" +
                            "1->View course catalog   2->Register for a course\n" +
                            "3->Cancel registration   4->View enrolled courses\n" +
                            "5->Logout \n" +
                            "===================================================");
        try (Scanner s = new Scanner(System.in)) {
            switch (s.nextInt()) {
                case 1: viewCourseCatalogPrompt(); break;
                case 2: registerPrompt(); break;
                case 3: cancelRegistrationPrompt(); break;
                case 4: viewEnrolledCoursesPrompt(); break;
                case 5: LoginPrompt.login(); break;
            }
        } catch (InputMismatchException e) {
            e.printStackTrace();
            System.out.println("Invalid input.");
            this.mainMenu();
        }
    }

    private void viewCourseCatalogPrompt() {
        System.out.println( "================================");
        List<String> catalog = this.service.viewCourseCatalog();
        for(int i = 0; i < catalog.size(); i++)
            System.out.println(this.cDao.readCourseById(catalog.get(i)).toString());
        System.out.println("================================");
        System.out.println("1. Go Back");
        Scanner s = new Scanner(System.in);
        if(s.nextInt() == 1) this.mainMenu();
    }

    private void viewEnrolledCoursesPrompt() {
        System.out.println("============Enrolled In============");
        List<String> enrolledList = this.service.viewEnrolledCourses(this.student.getStudentID());
        for(int i = 0; i < enrolledList.size(); i++)
            System.out.println(this.cDao.readCourseById(enrolledList.get(i)).toString());
        System.out.println("===================================");
        System.out.println("1. Go Back");
        Scanner s = new Scanner(System.in);
        if(s.nextInt() == 1) this.mainMenu();
    }

    private void cancelRegistrationPrompt() {
        Scanner s = new Scanner(System.in).useDelimiter("\n");
        try{
            System.out.print("Enter course id: ");
            String id = s.next().toUpperCase(Locale.ROOT);
            byte statusCode = this.service.cancelRegistration(this.student.getStudentID(), id);
            switch(statusCode) {
                case -1: System.out.println("Database error."); break;
                case  0: System.out.println("Unregistered from " + id + "."); break;
                case  1: System.out.println("Not enrolled in " + id + "."); break;
            }
            this.mainMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registerPrompt() {
        Scanner s = new Scanner(System.in).useDelimiter("\n");
        try{
            System.out.print("Enter course id: ");
            String cId = s.next().toUpperCase(Locale.ROOT);
            byte statusCode = this.service.registerForCourse(this.student.getStudentID(), cId);
            switch(statusCode) {
                case -1: System.out.println("Database error."); break;
                case  0: System.out.println("Successfully registered for " + cId + "."); break;
                case  1: System.out.println("Already enrolled in " + cId + "."); break;
                case  2: System.out.println("Cannot register as the course has already started."); break;
                case  3: System.out.println(cId + " is full."); break;
            }
            this.mainMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void registerNewAccountPrompt() {
        Scanner s = new Scanner(System.in).useDelimiter("\n");
        try {
            System.out.print("Enter email: ");
            this.student.setEmail(s.next());
            //validate email
            System.out.print("Enter password: ");
            String password = s.next();
            byte statusCode = RegexUtil.validatePassword(password);
            while(statusCode != 0) {
                if(statusCode == -1){
                    System.out.println("Password has to be at least 8 characters.");
                } else if(statusCode == 1) {
                    System.out.println("Password has to have at least 1 special character(?!@#$%^&*~-_=+`).");
                }
                System.out.print("Enter password: ");
                password = s.next();
                statusCode = RegexUtil.validatePassword(password);
            }
            System.out.print("Confirm password: ");
            if(password.equals(s.next())) this.student.setPassword(password);
            System.out.print("First name: ");
            this.student.setFirstName(s.next());
            //validate first name
            System.out.print("Last name: ");
            this.student.setLastName(s.next());
            //validate last name
            this.student.setYear("Freshman");
            System.out.println("==================\n"+this.student.toString()+"Confirm details[Y or N]: ");
            String confirm = s.next();
            while(!RegexUtil.yesOrNoInput(confirm)) {
                System.out.println( "======================================================\n"+
                                    "1->Email | 2->Password | 3->First Name | 4->Last Name | 5->Cancel");
                switch(s.nextInt()) {
                    case 1:
                        System.out.print("Enter email: ");
                        this.student.setEmail(s.next());
                        break;
                    case 2:
                        System.out.print("Enter password: ");
                        password = s.next();
                        System.out.print("Confirm password: ");
                        if(password.equals(s.next())) this.student.setPassword(password);
                        break;
                    case 3:
                        System.out.print("First name: ");
                        this.student.setFirstName(s.next());
                        break;
                    case 4:
                        System.out.print("Last name: ");
                        this.student.setLastName(s.next());
                        break;
                    case 5: LoginPrompt.login(); return;
                    default:
                        System.out.println("No changes made.");
                        break;
                }
                System.out.println("==================\n"+this.student.toString()+"\nConfirm details?[Y or N]: ");
                confirm = s.next();
            }

            this.student.setStudentID(this.service.generateNewStudentId());

            statusCode = this.service.registerNewAccount(this.student);
            switch(statusCode) {
                case -1:
                    System.out.println("Database error.");
                    LoginPrompt.login();
                case  0:
                    System.out.println("Account creation successful.");
                    System.out.println("Student Id: " + this.student.getStudentID());
                    Logger.log("Student " + this.student.getStudentID() + " created.", LogLevel.INFO);
                    this.mainMenu();
                case  1:
                    System.out.println("Account already exists.");
                    LoginPrompt.login();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
            registerNewAccountPrompt();
        }
    }
}
