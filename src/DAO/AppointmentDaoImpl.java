package DAO;

import Model.Appointment;
import Model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBConnection;
import utils.DBQuery;

import java.sql.*;
import java.time.LocalDateTime;

import static DAO.ContactDaoImpl.getContact;

/**This is the Appointment DAO Implementation. It supplies an interface to create, read, update, and delete Appointment data in the database.*/
public class AppointmentDaoImpl {

    private static final ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    /**This is the addAppointment method. It adds an appointment to the database.
     * @param newAppointment The appointment passed in to be inserted into the database.*/
    public static void addAppointment(Appointment newAppointment) throws SQLException {

        Connection conn = DBConnection.startConnection();
        String insertStatement = "INSERT INTO appointments(Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) " +
                "VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        DBQuery.setPreparedStatement(conn, insertStatement); //Create PreparedStatement
        PreparedStatement ps = DBQuery.getPreparedStatement();

        // key-value mapping
        ps.setString(1, newAppointment.getTitle());
        ps.setString(2, newAppointment.getDescription());
        ps.setString(3, newAppointment.getLocation());
        ps.setString(4, newAppointment.getType());
        ps.setTimestamp(5, Timestamp.valueOf(newAppointment.getStart()));
        ps.setTimestamp(6, Timestamp.valueOf(newAppointment.getEnd()));
        ps.setTimestamp(7, Timestamp.valueOf(newAppointment.getCreateDate()));
        ps.setString(8, newAppointment.getCreatedBy());
        ps.setTimestamp(9, Timestamp.valueOf(newAppointment.getLastUpdate()));
        ps.setString(10, newAppointment.getLastUpdatedBy());
        ps.setInt(11, newAppointment.getCustomerId());
        ps.setInt(12, newAppointment.getUserId());
        ps.setInt(13, newAppointment.getContact().getContactId());

        ps.execute(); // Execute PreparedStatement
        DBConnection.closeConnection();
    }

    /**This is the getAllAppointments method. It returns an observable list of Appointment objects by selecting every appointment record from the database, creating a a corresponding Appointment object, and adding it to the list.
     * @return An observable list of Appointment objects containing every appointment in the database.*/
    public static ObservableList<Appointment> getAllAppointments() throws SQLException {

        allAppointments.clear();

        Connection conn = DBConnection.startConnection();
        String selectStatement = "SELECT * FROM appointments";
        DBQuery.setPreparedStatement(conn, selectStatement); //Create PreparedStatement
        PreparedStatement ps = DBQuery.getPreparedStatement();

        ps.execute(); // Execute PreparedStatement

        ResultSet result = ps.getResultSet();

        while(result.next()){

            int appointmentId = result.getInt("Appointment_ID");
            String title = result.getString("Title");
            String description = result.getString("Description");
            String location = result.getString("Location");
            String type = result.getString("Type");
            LocalDateTime start = result.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = result.getTimestamp("End").toLocalDateTime();
            LocalDateTime createDate = result.getTimestamp("Create_Date").toLocalDateTime();
            String createdBy = result.getString("Created_By");
            LocalDateTime lastUpdate = result.getTimestamp("Last_Update").toLocalDateTime();
            String lastUpdatedBy = result.getString("Last_Updated_By");
            int customerId = result.getInt("Customer_ID");
            int userId = result.getInt("User_ID");
            Contact contact = getContact(result.getInt("Contact_ID"), conn);

            Appointment appointmentResult = new Appointment(appointmentId, title, description, location, type, start, end, createDate, createdBy, lastUpdate, lastUpdatedBy, customerId, userId, contact);
            allAppointments.add(appointmentResult);

        }

        DBConnection.closeConnection();
        return allAppointments;
    }

    /**This is the updateAppointment method. It updates the attributes appointment in the database with the Appointment ID that matches the Appointment object passed in as a parameter.
     * @param updatedAppointment The appointment to be updated. Its fields contain the updated information.*/
    public static void updateAppointment(Appointment updatedAppointment) throws SQLException {

        Connection conn = DBConnection.startConnection();
        String updateStatement = "UPDATE appointments " +
                "SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Create_Date = ?, Created_By = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? " +
                "WHERE Appointment_ID = ?";
        DBQuery.setPreparedStatement(conn, updateStatement); //Create PreparedStatement
        PreparedStatement ps = DBQuery.getPreparedStatement();

        // key-value mapping
        ps.setString(1, updatedAppointment.getTitle());
        ps.setString(2, updatedAppointment.getDescription());
        ps.setString(3, updatedAppointment.getLocation());
        ps.setString(4, updatedAppointment.getType());
        ps.setTimestamp(5, Timestamp.valueOf(updatedAppointment.getStart()));
        ps.setTimestamp(6, Timestamp.valueOf(updatedAppointment.getEnd()));
        ps.setTimestamp(7, Timestamp.valueOf(updatedAppointment.getCreateDate()));
        ps.setString(8, updatedAppointment.getCreatedBy());
        ps.setTimestamp(9, Timestamp.valueOf(updatedAppointment.getLastUpdate()));
        ps.setString(10, updatedAppointment.getLastUpdatedBy());
        ps.setInt(11, updatedAppointment.getCustomerId());
        ps.setInt(12, updatedAppointment.getUserId());
        ps.setInt(13, updatedAppointment.getContact().getContactId());
        ps.setInt(14, updatedAppointment.getAppointmentId());

        ps.execute(); // Execute PreparedStatement
        DBConnection.closeConnection();
    }

    /**This is the deleteAppointment method. It deletes the appointment record in the database with the Appointment ID that matches the Appointment object passed in as a parameter.
     * @param selectedAppointment The appointment to be deleted from the database.*/
    public static void deleteAppointment(Appointment selectedAppointment) throws SQLException {

        Connection conn = DBConnection.startConnection();
        String deleteStatement = "DELETE FROM appointments WHERE Appointment_ID = ?";
        DBQuery.setPreparedStatement(conn, deleteStatement); //Create PreparedStatement
        PreparedStatement ps = DBQuery.getPreparedStatement();

        // key-value mapping
        ps.setInt(1, selectedAppointment.getAppointmentId());

        ps.execute(); // Execute PreparedStatement
        DBConnection.closeConnection();
    }

    /**This is the deleteAssociatedAppointments method. It deletes the appointment records in the database with the Customer ID that matches that of the Appointment object passed in as a parameter.
     * @param customerId All appointment records in the database with the same customer ID as this parameter are deleted.*/
    public static void deleteAssociatedAppointments(int customerId) throws SQLException {

        Connection conn = DBConnection.startConnection();
        String deleteStatement = "DELETE FROM appointments WHERE Customer_ID = ?";
        DBQuery.setPreparedStatement(conn, deleteStatement); //Create PreparedStatement
        PreparedStatement ps = DBQuery.getPreparedStatement();

        // key-value mapping
        ps.setInt(1, customerId);

        ps.execute(); // Execute PreparedStatement
        DBConnection.closeConnection();
    }
}
