package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/** This class creates and returns PreparedStatement objects for sending SQL queries to the database.*/
public class DBQuery {

    private static PreparedStatement statement;

    /**This is the setPreparedStatement method. This method creates a PreparedStatement object.
     * @param conn Connection object
     * @param sqlStatement String containing the SQL statement.*/
    public static void setPreparedStatement(Connection conn, String sqlStatement) throws SQLException {
        statement = conn.prepareStatement(sqlStatement);
    }

    /**This is the getPreparedStatement method. This method returns the PreparedStatement object set by the method setPreparedStatement.
     * @return PreparedStatement object*/
    public static PreparedStatement getPreparedStatement() {
        return statement;
    }
}
