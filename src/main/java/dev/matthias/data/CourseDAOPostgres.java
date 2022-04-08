package dev.matthias.data;

import dev.matthias.entities.Course;
import dev.matthias.services.FacultyServiceImpl;
import dev.matthias.utilities.ConnectionUtil;
import dev.matthias.utilities.LogLevel;
import dev.matthias.utilities.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

/**
 *  This class allows for the managing of the course table by implementing CRUD methods. They include creating, updating and
 *  deleting a course record as well as reading the course id.
 *
 */

public class CourseDAOPostgres implements CourseDAO{

    /**
     * Insert new record into course table.
     * Exits if {@link CourseDAOPostgres#checkIdForDuplicate} returns true.
     *
     * @param course Course object to be inserted into course table
     * @return Course object that was passed and inserted
     */

    @Override
public boolean createCourse(Course course) {
        try {
//            if(CourseDAOPostgres.checkIdForDuplicate(course)) {
//                Logger.log("Cannot add course with the same ID.", LogLevel.WARNING);
//                System.out.println("Cannot add duplicate entries.");
//                return false;
//            }

            String query = "insert into course values (?, ?, ?, ?, ?, ?)";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, course.getId().toUpperCase(Locale.ROOT));
            ps.setString(2, course.getName());
            ps.setString(3, course.getDescription());
            ps.setLong(4, course.getStart());
            ps.setLong(5, course.getEnd());
            ps.setInt(6, course.getCapacity());

            if(ps.executeUpdate() == 1) {
                Logger.log("Created " + course.getId(), LogLevel.INFO);
                return true;
            } else {
                Logger.log("Failed to create course " + course.getId(), LogLevel.WARNING);
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return false;
        }
    }

    /**
     * Select query based on id(refers to course_id).
     *
     * @param id the id that is used to pull from the course_id attribute
     * @return Course object that was queried from the course table
     */

    @Override
    public Course readCourseById(String id) {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String query = "select * from course where course_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, id.toUpperCase(Locale.ROOT));

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Course course = new Course();
                course.setId(rs.getString("course_id"));
                course.setName(rs.getString("course_name"));
                course.setDescription(rs.getString("description"));
                course.setStart(rs.getInt("start_date"));
                course.setEnd(rs.getInt("end_date"));
                course.setCapacity(rs.getInt("capacity"));
                return course;
            } else {
                System.out.println("Failed to find record.");
                new FacultyServiceImpl().facultyPrompt();
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return null;
        }
    }

    /**
     *  Update query to the course table based on the passed Course object.
     *
     * @param course the course with the updated/modified fields to be changed
     * @return the same course that was passed in
     */

    @Override
    public Course updateCourse(Course course) {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String query = "update course set course_name = ?, description = ?," +
                    " start_date = ?, end_date = ?, capacity = ? where course_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, course.getName());
            ps.setString(2, course.getDescription());
            ps.setLong(3, course.getStart());
            ps.setLong(4, course.getEnd());
            ps.setInt(5, course.getCapacity());
            ps.setString(6, course.getId());
            ps.executeUpdate();
            Logger.log("Updated " + course.getId(), LogLevel.INFO);
            return course;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update " + course.getId() + ".");
            new FacultyServiceImpl().facultyPrompt();
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return null;
        }
    }

    /**
     *  Delete record from course table based on id(refers to course_id attribute).
     *
     * @param id course id string
     * @return true if course was deleted, false if otherwise
     */

    @Override
    public boolean deleteCourseById(String id) {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String query = "delete from course where course_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.execute();
            Logger.log("Deleted " + id, LogLevel.INFO);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to delete " + id + ".");
            Logger.log(e.getMessage(), LogLevel.ERROR);
            new FacultyServiceImpl().facultyPrompt();
            return false;
        }
    }

    /**
     * Checks for duplicate entries based on course_id attribute.
     *
     * @return true if duplicate is found in course table
     */
    public static boolean checkIdForDuplicate(Course course) {
        try {
            System.out.println(course.getId());
            Connection conn = ConnectionUtil.createConnection();
            String query = "select count(*) from course where course_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, course.getId());

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
