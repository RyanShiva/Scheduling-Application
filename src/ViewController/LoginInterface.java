package ViewController;

import Model.User;

import java.sql.SQLException;

/** This functional interface provides an abstract method to create a login alert.*/
@FunctionalInterface
public interface LoginInterface {

    /**This is the loginAlert method. It checks for upcoming appointments for the user logging in.
     * @param user The user logging in.*/
    void loginAlert(User user) throws SQLException;
}
