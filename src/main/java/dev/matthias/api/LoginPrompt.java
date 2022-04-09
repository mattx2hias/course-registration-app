package dev.matthias.api;

import dev.matthias.entities.Faculty;
import dev.matthias.entities.Student;
import dev.matthias.services.FacultyService;
import dev.matthias.services.FacultyServiceImpl;
import dev.matthias.services.StudentService;
import dev.matthias.services.StudentServiceImpl;
import dev.matthias.utilities.LoginUtil;

import java.awt.event.KeyListener;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginPrompt {

    public static void login() {
        System.out.println( "=============================\n" +
                            "University Of Wombo Terminal\n" +
                            "=============================");
        System.out.println("1. Student \t2. Faculty 3. Quit");

        Scanner s = new Scanner(System.in).useDelimiter("\n");
        int selection;
        String email = null;
        String password = null;
        try {
            selection = s.nextInt();
            if(selection == 1 || selection == 2) {
                System.out.print("Enter email: ");
                email = s.next(); //check for valid email
                System.out.print("Enter password: ");
                password = s.next();
            }

            // status codes for incorrect email, password or account doesn't exist
            if(selection == 1) {
                Student stud = LoginUtil.sLogin(email, password);
                if(stud == null) {
                    System.out.println("Error");
                    LoginPrompt.login();
                } else System.out.println("Welcome, " + stud.getFirstName());
                StudentPrompt sp = new StudentPrompt(stud);
                sp.mainMenu();
            }
            else if(selection == 2) {
                Faculty fac = LoginUtil.fLogin(email, password);
                if(fac == null) {
                    System.out.println("Error");
                    LoginPrompt.login();
                } else System.out.println("Welcome, " + fac.getFirstName());
                FacultyPrompt fp = new FacultyPrompt(fac);
                fp.mainMenu();
            }
            else if(selection == 3) {  }
            else {
                System.out.println("Please enter 1, 2, or 3.");
                LoginPrompt.login();
            }
        } catch(InputMismatchException e) {
            e.printStackTrace();
        } finally { s.close(); }
    }
}
