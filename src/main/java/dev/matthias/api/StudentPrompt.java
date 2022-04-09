package dev.matthias.api;

import dev.matthias.data.CourseDAO;
import dev.matthias.data.CourseDAOPostgres;
import dev.matthias.data.StudentDAO;
import dev.matthias.data.StudentDAOPostgres;
import dev.matthias.entities.Student;
import dev.matthias.services.StudentService;
import dev.matthias.services.StudentServiceImpl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class StudentPrompt {

    Student student;
    StudentService service;
    CourseDAO cDao;

    public StudentPrompt(Student student) {
        this.student = student;
        this.service = new StudentServiceImpl();
        this.cDao = new CourseDAOPostgres();
    }

    public void mainMenu() {
        System.out.println( "==================Student_Options===============\n" +
                            "1. View course catalog\t2. Register for a course\n" +
                            "3. Cancel registration\t4. View enrolled courses\n" +
                            "5. Logout \n" +
                            "================================================");
        Scanner s = new Scanner(System.in);
        try {
            switch(s.nextInt()) {
                case 1: viewCourseCatalogPrompt(); break;
                case 2: registerPrompt(); break;
                case 3: cancelRegistrationPrompt(); break;
                case 4: viewEnrolledCoursesPrompt(); break;
                case 5: LoginPrompt.login(); break;
            }
        } catch(InputMismatchException e) {
            e.printStackTrace();
            System.out.println("Invalid input.");
            this.mainMenu();
        } finally { s.close(); }
    }

    private void viewCourseCatalogPrompt() {
        System.out.println( "================================");
        List<String> catalog = this.service.viewCourseCatalog();
        for (String s : catalog) {
            System.out.println(this.cDao.readCourseById(s).toString());
        }
        System.out.println("================================");
        System.out.println("1. Go Back");
        Scanner s = new Scanner(System.in);
        if(s.nextInt() == 1) this.mainMenu();
    }

    private void viewEnrolledCoursesPrompt() {
        System.out.println("============Enrolled In============");
        for(String s : this.service.viewEnrolledCourses(this.student.getStudentID())) {
            if(s != null) {
                System.out.println(this.cDao.readCourseById(s).toString());
            }
        }
        System.out.println("===================================");
        System.out.println("1. Go Back");
        Scanner s = new Scanner(System.in);
        if(s.nextInt() == 1) this.mainMenu();
    }

    private void cancelRegistrationPrompt() {
        Scanner s = new Scanner(System.in).useDelimiter("\n");
        try{
            System.out.print("Enter course id: ");
            String id = s.next();
            this.service.cancelRegistration(this.student, id);
            this.mainMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registerPrompt() {
        Scanner s = new Scanner(System.in).useDelimiter("\n");
        try{
            System.out.print("Enter course id: ");
            this.service.registerForCourse(this.student.getStudentID(), s.next());
            this.mainMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
