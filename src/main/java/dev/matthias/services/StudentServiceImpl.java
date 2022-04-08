package dev.matthias.services;

import dev.matthias.data.CourseDAOPostgres;
import dev.matthias.data.StudentDAOPostgres;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentServiceImpl implements StudentService{
    @Override
    public void studentPrompt() {
        System.out.println( "==================Student_Options===============\n" +
                            "1. View course catalog\t2. Register for a course\n" +
                            "3. Cancel registration\t4. View enrolled courses\n" +
                            "5. Logout " +
                            "================================================");
        Scanner s = new Scanner(System.in);
        try {
            switch(s.nextInt()) {
                case 1: viewCourseCatalog(); break;
                case 2: registerForCourse(); break;
                case 3: cancelRegistration(); break;
                case 4: viewEnrolledCourses(); break;
                case 5: break;
            }
        } catch(InputMismatchException e) {
            e.printStackTrace();
            System.out.println("Invalid input.");
            studentPrompt();
        } finally { s.close(); }
    }

    @Override
    public boolean registerForCourse() {
        System.out.println("Enter Course ID: ");
        //option to view course catalog
        Scanner s = new Scanner(System.in).useDelimiter("\n");
        String courseID;
        try {
            courseID = s.next();

        } catch(InputMismatchException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean viewCourseCatalog() {
        System.out.println( "================================");
        new StudentDAOPostgres().viewCourseCatalog();
        System.out.println("================================");
        new StudentServiceImpl().studentPrompt();
        return false;
    }

    @Override
    public boolean cancelRegistration() {
        System.out.println("cancel registration");
        return false;
    }

    @Override
    public void viewEnrolledCourses() {
        System.out.println("view enrolled courses");
    }
}
