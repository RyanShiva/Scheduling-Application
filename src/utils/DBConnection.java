package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** This class facilitates the application's connection to the database.*/
public class DBConnection {

    // JDBC URL
    private static final String jdbcURL = "jdbc:mysql://wgudb.ucertify.com/WJ06mH3";

    // Driver and Connection Interface Reference
    private static final String MYSQLJDBCDriver = "com.mysql.cj.jdbc.Driver";
    private static Connection conn = null;

    /**This is the startConnection method. This method creates the Connection object for connecting to the database.
     * @return Connection object*/
    public static Connection startConnection() {
        try {
            Class.forName(MYSQLJDBCDriver);
            String username = "U06mH3";
            String password = "53688808963";
            conn = DriverManager.getConnection(jdbcURL, username, password);
        }
        catch(ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    /**This is the closeConnection method. This method closes the connection of the Connection object 'conn'.*/
    public static void closeConnection() {
        try {
            conn.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
