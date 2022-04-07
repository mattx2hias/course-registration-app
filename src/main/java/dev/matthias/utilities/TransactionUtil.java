package dev.matthias.utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionUtil {
    //use commit in DAO CRUD methods
    //use rollback in Tests

    public static void rollback() {
        try {
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement("rollback;");
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void commit() {
        try {
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement("commit;");
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
