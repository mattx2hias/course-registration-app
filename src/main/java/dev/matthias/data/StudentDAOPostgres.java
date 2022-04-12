package dev.matthias.data;

import dev.matthias.entities.Student;
import dev.matthias.utilities.*;
import dev.matthias.utilities.List;
import java.sql.*;
import java.util.Locale;

public class StudentDAOPostgres implements StudentDAO{

    @Override
    public Student readStudent(String email, String password) {
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
            return stud;
        } catch (SQLException e) {
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return null;
        }
    }

    @Override
    public List<String> readEnrolledCourses(int sId) {
        try{
            List<String> courseList = new ArrayList<>();
            String query = "select course_id from student_course where student_id = ?";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, sId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                courseList.append(rs.getString("course_id"));
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
            String query = "insert into student values (?, ?, ?, ?, ?);";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, stud.getStudentID());
            ps.setString(2, stud.getFirstName());
            ps.setString(3, stud.getLastName());
            ps.setString(4, stud.getEmail());
            ps.setString(5, stud.getPassword());
            if(ps.executeUpdate() > 0) {
                Logger.log("Created student " + stud.getStudentID(), LogLevel.INFO);
                return true;
            } else {
                return false;
            }
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
            ps.setString(2, cId.toUpperCase(Locale.ROOT));
            if(ps.executeUpdate() > 0) {
                Logger.log(sId + " registered for " + cId, LogLevel.INFO);
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return false;
        }
    }

    @Override
    public boolean cancelRegistration(int sId, String cId) {
        try {
            String query = "delete from student_course where student_id = ? and course_id = ?";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, sId);
            ps.setString(2, cId.toUpperCase(Locale.ROOT));
            if(ps.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
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
            while(rs.next())
                ids.add(i++, rs.getString("course_id"));
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
    public boolean emailExists(String email) {
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

    /**
     *
     * @param sId
     * @param cId
     * @return true if student is enrolled in course
     */
    @Override
    public boolean isEnrolled(int sId, String cId) {
        try{
            String query = "select count(*) from student_course where student_id = ? and course_id = ?";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, sId);
            ps.setString(2, cId.toUpperCase(Locale.ROOT));
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("count") > 0;
            } else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return false;
        }
    }
}
