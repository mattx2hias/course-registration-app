package dev.matthias.api;

import dev.matthias.utilities.LogLevel;
import dev.matthias.utilities.Logger;
import dev.matthias.utilities.LoginUtil;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {



    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("University Of Wombo, Bikini Bottom");
        System.out.println("1. Student Login\t2. Faculty Login");
        int selection = 0;
        try {
            selection = s.nextInt();
            LoginUtil.login(selection);
        } catch(InputMismatchException e) {
            e.printStackTrace();
        } finally { s.close(); }
    }
}
