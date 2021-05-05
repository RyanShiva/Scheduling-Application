package DAO;

import Model.User;
import utils.DBConnection;
import utils.DBQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**This is the User DAO Implementation. It supplies an interface to create, read, update, and delete User data in the database.*/
public class UserDaoImpl {

    /**This is the isValidUser method. It checks whether the username parameter is the username of a User in the database.
     * @param username The username to check
     * @return boolean value of whether the username was found in the database*/
    public static Boolean isValidUser(String username) throws SQLException {
        Connection conn = DBConnection.startConnection();
        String selectStatement = "SELECT * FROM users WHERE User_Name = ?";
        DBQuery.setPreparedStatement(conn, selectStatement); //Create PreparedStatement
        PreparedStatement ps = DBQuery.getPreparedStatement();

        // key-value mapping
        ps.setString(1, username);

        ps.execute(); // Execute PreparedStatement

        ResultSet result = ps.getResultSet();

        if (result.next()) {
            DBConnection.closeConnection();
            return true;
        }
        else {
            DBConnection.closeConnection();
            return false;
        }
    }

    /**This is the isCorrectPassword method. It checks whether the password parameter is the correct password for the User corresponding to the username parameter.
     * @param username The username of the User to check the password against
     * @param password The password to check
     * @return boolean value of whether the password is correct for the corresponding User*/
    public static Boolean isCorrectPassword(String username, String password) throws SQLException {
        Connection conn = DBConnection.startConnection();
        String selectStatement = "SELECT * FROM users WHERE User_Name = ?";
        DBQuery.setPreparedStatement(conn, selectStatement); //Create PreparedStatement
        PreparedStatement ps = DBQuery.getPreparedStatement();

        // key-value mapping
        ps.setString(1, username);

        ps.execute(); // Execute PreparedStatement

        ResultSet result = ps.getResultSet();

        if (result.next() && password.equals(result.getString("Password"))) {
                DBConnection.closeConnection();
                return true;
        }
        else {
            DBConnection.closeConnection();
            return false;
        }
    }

    /**This is the getUser method. It returns a User object by selecting the user record from the database with the username matching the username parameter.
     * @param username username of the User object to return
     * @return A User object that matches the username parameter*/
    public static User getUser(String username) throws SQLException {
        Connection conn = DBConnection.startConnection();
        String selectStatement = "SELECT * FROM users WHERE User_Name = ?";
        DBQuery.setPreparedStatement(conn, selectStatement); //Create PreparedStatement
        PreparedStatement ps = DBQuery.getPreparedStatement();

        // key-value mapping
        ps.setString(1, username);

        ps.execute(); // Execute PreparedStatement

        ResultSet result = ps.getResultSet();

        result.next();

        int userId = result.getInt("User_ID");
        String password = result.getString("Password");
        User newUser = new User(userId, username, password);
        DBConnection.closeConnection();
        return newUser;
    }
}