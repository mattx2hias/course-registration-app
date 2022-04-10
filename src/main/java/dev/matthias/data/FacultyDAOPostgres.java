package dev.matthias.data;

import dev.matthias.utilities.ConnectionUtil;
import dev.matthias.utilities.LogLevel;
import dev.matthias.utilities.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FacultyDAOPostgres implements FacultyDAO{
    @Override
    public boolean removeEnrolledStudents(String cId) {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String query = "delete from student_course where course_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, cId);
            ps.execute();
            Logger.log("Deleted " + cId + ".", LogLevel.INFO);
            //log all studentId's that were removed
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return false;
        }
    }
}
