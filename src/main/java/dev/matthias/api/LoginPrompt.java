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
                            "1->Student | 2->Faculty | 3->Quit");
        int selection;
        String email = null;
        String password = null;
        try (Scanner s = new Scanner(System.in).useDelimiter("\n")) {
            selection = s.nextInt();
            if (selection == 1) {
                System.out.println( "===============================================\n" +
                                    "1->Existing Student | 2->New Student | 3->Quit");
                selection = s.nextInt();
                if (selection == 2) {
                    Student newStudent = new Student();
                    StudentPrompt sp = new StudentPrompt(newStudent);
                    sp.registerNewAccountPrompt();
                    selection = 1;
                } else return;
            }
            if (selection == 1 || selection == 2) {
                System.out.print("Enter email: ");
                email = s.next(); //check for valid email
                System.out.print("Enter password: ");
                password = s.next();
            }

            // status codes for incorrect email, password or account doesn't exist
            if (selection == 1) {
                Student stud = LoginUtil.sLogin(email, password);
                if (stud == null) {
                    LoginPrompt.login();
                } else System.out.println("\nWelcome, " + stud.getFirstName());
                StudentPrompt sp = new StudentPrompt(stud);
                sp.mainMenu();
            } else if (selection == 2) {
                Faculty fac = LoginUtil.fLogin(email, password);
                if (fac == null) {
                    LoginPrompt.login();
                } else System.out.println("\nWelcome, " + fac.getFirstName());
                FacultyPrompt fp = new FacultyPrompt(fac);
                fp.mainMenu();
            } else if (selection == 3) {
            } else {
                System.out.println("Please enter 1, 2, or 3.");
                LoginPrompt.login();
            }
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }
    }
}
