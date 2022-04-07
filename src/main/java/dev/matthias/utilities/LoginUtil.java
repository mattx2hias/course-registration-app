package dev.matthias.utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginUtil {

    public static void login(int status) {
        Scanner s = new Scanner(System.in);
        String email = null;
        String password = null;
        String tableName = null;
        try{
            System.out.println("Enter email: ");
            email = s.next();
            System.out.println("Enter password: ");
            password = s.next();
        }catch(InputMismatchException e) {
            e.printStackTrace();
        }finally { s.close(); }

        if(status == 1) tableName="student";
            else tableName="faculty";

        try {
            Connection conn = ConnectionUtil.createConnection();
            String query = "select count(*) from "+ tableName +" where email = ? and password = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){

            } else {
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
