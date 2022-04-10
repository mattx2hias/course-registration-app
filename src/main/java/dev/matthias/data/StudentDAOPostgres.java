package dev.matthias.data;

import dev.matthias.entities.Student;
import dev.matthias.utilities.*;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class StudentDAOPostgres implements StudentDAO{

    @Override
    public String[] readEnrolledCourses(int sId) {
        try{
            String[] courseList = new String[10];
            String query = "select course_id from student_course where student_id = ?";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, sId);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            while(rs.next()) {
                courseList[i] = rs.getString("course_id");
                i++;
            }
            return courseList;
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return null;
        }
    }

    @Override
    public boolean registerNewAccount(Student stud) {
        try{
            String query = "insert into student values (?, ?, ?, ?, ?, ?, ?);";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, stud.getStudentID());
            ps.setString(2, stud.getFirstName());
            ps.setString(3, stud.getLastName());
            ps.setString(4, stud.getEmail());
            ps.setString(5, stud.getPassword());
            ps.setString(6, stud.getYear());
            ps.setString(7, stud.getMajor());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return false;
        }
    }

    @Override
    public boolean registerForCourse(int sId, String cId) {
        // check if prereqs are completed
        try {
            String query = "insert into student_course values (?, ?);";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, sId);
            ps.setString(2, cId);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return false;
        }
    }

    @Override
    public boolean cancelRegistration(int sId, String cId) {
        try {
            String query = "delete from student_course where student_id = ? and course_id = ?;";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, sId);
            ps.setString(2, cId);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return false;
        }
    }

    @Override
    public List<String> readCourseCatalog() {
        try{
            List<String> ids = new ArrayList<>();
            String query = "select course_id from course";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            while(rs.next()) ids.add(i++, rs.getString("course_id"));
            return ids;
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return null;
        }
    }

    @Override
    public boolean checkIfIdExists(int sId) {
        try {
            String query = "select count(*) from student where student_id = ?";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, sId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("count") > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return false;
        }
    }

    @Override
    public boolean checkIfEmailExists(String email) {
        try {
            String query = "select count(*) from student where email = ?";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("count") > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return false;
        }
    }
}
