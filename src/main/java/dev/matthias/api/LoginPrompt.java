package dev.matthias.api;

import dev.matthias.entities.Faculty;
import dev.matthias.entities.Student;
import dev.matthias.utilities.LoginUtil;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginPrompt {

    public static void login() {
        System.out.println( "==================================\n" +
                            "   University Of Wombo Terminal   \n" +
                            "==================================\n" +
                            "1->Student   2->Faculty   3->Quit");
        int selection;
        Scanner s = new Scanner(System.in).useDelimiter("\n");
        try  {
            selection = s.nextInt();
            if (selection == 1) {
                LoginPrompt.studentLogin();
            } else if (selection == 2) {
                LoginPrompt.facultyLogin();
            } else return;
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }
    }
    public static void studentLogin() {
        System.out.println("===============================================\n" +
                            "1->Existing Student   2->New Student   3->Back");
        Student newStudent = new Student();
        StudentPrompt sp = new StudentPrompt(newStudent);
        String email = null;
        String password = null;
        int selection;
        Scanner s = new Scanner(System.in).useDelimiter("\n");
        try {
            selection = s.nextInt();
            if(selection == 1) {
                System.out.print("Enter email: ");
                email = s.next(); //check for valid email
                System.out.print("Enter password: ");
                password = s.next();
                Student stud = LoginUtil.sLogin(email, password);
                sp = new StudentPrompt(stud);
                if(stud == null){
                    System.out.println("Invalid email or password.");
                    LoginPrompt.login();
                } else {
                    System.out.println("\nWelcome, " + stud.getFirstName());
                    sp.mainMenu();
                }

            }else if(selection == 2) {
                sp.registerNewAccountPrompt();
            } //else LoginPrompt.login();
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }
    }

    public static void facultyLogin() {
        Scanner s = new Scanner(System.in).useDelimiter("\n");
        String email = null;
        String password = null;
        try {
            System.out.print("Enter email: ");
            email = s.next(); //check for valid email
            System.out.print("Enter password: ");
            password = s.next();
            Faculty fac = LoginUtil.fLogin(email, password);
            FacultyPrompt fp = new FacultyPrompt(fac);
            if(fac == null){
                System.out.println("Invalid email or password.");
                LoginPrompt.login();
            } else {
                System.out.println("\nWelcome, " + fac.getFirstName());
                fp.mainMenu();
            }
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }
    }
}
