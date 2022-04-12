package dev.matthias.data;

import dev.matthias.entities.Faculty;
import dev.matthias.utilities.ConnectionUtil;
import dev.matthias.utilities.LogLevel;
import dev.matthias.utilities.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FacultyDAOPostgres implements FacultyDAO{

    @Override
    public Faculty readFaculty(String email, String password) {
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
            return fac;
        } catch (SQLException e) {
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return null;
        }
    }

    @Override
    public boolean addFaculty(Faculty faculty) {
        try{
            String query = "insert into faculty values (?, ?, ?, ?, ?);";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, faculty.getfId());
            ps.setString(2, faculty.getFirstName());
            ps.setString(3, faculty.getLastName());
            ps.setString(4, faculty.getEmail());
            ps.setString(5, faculty.getPassword());
            if(ps.executeUpdate() > 0) {
                Logger.log("Added faculty member " + faculty.getfId(), LogLevel.INFO);
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
}
