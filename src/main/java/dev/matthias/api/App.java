package dev.matthias.api;

import dev.matthias.entities.Course;
import dev.matthias.services.FacultyServiceImpl;
import dev.matthias.services.StudentServiceImpl;
import dev.matthias.utilities.LoginUtil;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class App {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int selection;
        String email;
        String password;

//        System.out.println( "=============================\n" +
//                            "University Of Wombo Terminal\n" +
//                            "=============================");
//        System.out.println("1. Student \t2. Faculty ");

        try {
//            selection = s.nextInt();
//            System.out.print("Enter email: ");
//            email = s.next(); //check for valid email
//            System.out.print("Enter password: ");
//            password = s.next();
//
//            if(LoginUtil.login(selection, email, password)) {
//                if(selection == 1) new StudentServiceImpl().studentPrompt();
//                    else if(selection == 2) new FacultyServiceImpl().facultyPrompt();
//                    else System.out.println("Please enter 1 or 2.");
//            } else System.out.println("Incorrect login credentials.");

            new StudentServiceImpl().studentPrompt();
            //new FacultyServiceImpl().facultyPrompt();

        } catch(InputMismatchException e) {
            e.printStackTrace();
        } finally { s.close(); }
    }
}
