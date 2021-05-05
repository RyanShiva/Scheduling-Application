package DAO;

import Model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBConnection;
import utils.DBQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**This is the Contact DAO Implementation. It supplies an interface to create, read, update, and delete Contact data in the database.*/
public class ContactDaoImpl {

    private static final ObservableList<Contact> allContacts = FXCollections.observableArrayList();

    /**This is the getAllContacts method. It returns an observable list of Contact objects by selecting every contact record from the database, creating a a corresponding Contact object, and adding it to the list.
     * @return An observable list of Contact objects containing every contact in the database.*/
    public static ObservableList<Contact> getAllContacts() throws SQLException {

        allContacts.clear();

        Connection conn = DBConnection.startConnection();
        String selectStatement = "SELECT * FROM contacts";
        DBQuery.setPreparedStatement(conn, selectStatement); //Create PreparedStatement
        PreparedStatement ps = DBQuery.getPreparedStatement();

        ps.execute(); // Execute PreparedStatement

        ResultSet result = ps.getResultSet();

        while (result.next()) {

            int contactId = result.getInt("Contact_ID");
            String contactName = result.getString("Contact_Name");
            String email = result.getString("Email");


            Contact contactResult = new Contact(contactId, contactName, email);
            allContacts.add(contactResult);
        }

        DBConnection.closeConnection();
        return allContacts;
    }

    /**This is the getContact method. It returns the Contact object with the contact ID matching the contactId parameter.
     * @param contactId contact ID of the contact to return
     * @param conn Connection object
     * @return A contact object matching the contactId parameter.*/
    public static Contact getContact(int contactId, Connection conn) throws SQLException {

        String selectStatement = "SELECT * FROM contacts WHERE Contact_ID = ?";
        DBQuery.setPreparedStatement(conn, selectStatement); //Create PreparedStatement
        PreparedStatement ps = DBQuery.getPreparedStatement();

        // key-value mapping
        ps.setInt(1, contactId);

        ps.execute(); // Execute PreparedStatement

        ResultSet result = ps.getResultSet();

        result.next();

        String contactName = result.getString("Contact_Name");
        String email = result.getString("Email");
        return new Contact(contactId, contactName, email);
    }

}
