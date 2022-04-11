package dev.matthias.utilities;

import dev.matthias.entities.Faculty;
import dev.matthias.entities.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginUtil {

    public static Student sLogin(String email, String password) {
        try {
            String query = "select * from student where email = ? and password = ?";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Student stud = new Student();
            stud.setStudentID(rs.getInt("student_id"));
            stud.setFirstName(rs.getString("first_name"));
            stud.setLastName(rs.getString("last_name"));
            stud.setYear(rs.getString("year"));
            stud.setMajor(rs.getString("major"));
            return stud;
        } catch (SQLException e) {
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return null;
        }
    }

    public static Faculty fLogin(String email, String password) {
        try {
            String query = "select * from faculty where email = ? and password = ?";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Faculty fac = new Faculty();
            fac.setfId(rs.getInt("faculty_id"));
            fac.setFirstName(rs.getString("first_name"));
            fac.setLastName(rs.getString("last_name"));
            fac.setDepartment(rs.getString("department"));
            return fac;
        } catch (SQLException e) {
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return null;
        }
    }
}
