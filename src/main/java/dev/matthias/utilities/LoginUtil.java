package dev.matthias.utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginUtil {

    public static boolean login(int status, String email, String password) {
        String tableName = null;
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
                return rs.getInt("count") > 0;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
