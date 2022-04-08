package dev.matthias.data;

import dev.matthias.entities.Course;
import dev.matthias.utilities.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAOPostgres implements StudentDAO{
    @Override
    public void viewCourseCatalog() {
        // check if prereqs are completed
        try{
            String query = "select course_id, course_name, start_date, end_date, capacity from course";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            Course dummyCourse = new Course();
            while(rs.next()) {
                dummyCourse.setId(rs.getString("course_id"));
                dummyCourse.setName(rs.getString("course_name"));
                dummyCourse.setStart(rs.getLong("start_date"));
                dummyCourse.setEnd(rs.getLong("end_date"));
                dummyCourse.setCapacity(rs.getInt("capacity"));
                //make strings take up a fixed amount of space
                System.out.println(dummyCourse.getId() + "\t| " + dummyCourse.getName());
                rs.next();
            }
        } catch(SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void registerForCourse() {
        // check if prereqs are completed
        // insert new record into student_course table
    }

    @Override
    public void viewEnrolledCourses() {
        //select * where student_id = ?
    }
}
