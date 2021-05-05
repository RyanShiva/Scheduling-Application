package DAO;

import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBConnection;
import utils.DBQuery;

import java.sql.*;
import java.time.LocalDateTime;

/**This is the Customer DAO Implementation. It supplies an interface to create, read, update, and delete Customer data in the database.*/
public class CustomerDaoImpl {

    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    /**This is the addCustomer method. It adds a customer to the database.
     * @param newCustomer The customer passed in to be inserted into the database.*/
    public static void addCustomer(Customer newCustomer) throws SQLException {

        Connection conn = DBConnection.startConnection();

        String insertStatement = "INSERT INTO customers(Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) " +
                "VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        DBQuery.setPreparedStatement(conn, insertStatement); //Create PreparedStatement
        PreparedStatement ps = DBQuery.getPreparedStatement();

        // key-value mapping
        ps.setString(1, newCustomer.getCustomerName());
        ps.setString(2, newCustomer.getAddress());
        ps.setString(3, newCustomer.getPostalCode());
        ps.setString(4, newCustomer.getPhone());
        ps.setTimestamp(5, Timestamp.valueOf(newCustomer.getCreateDate()));
        ps.setString(6, newCustomer.getCreatedBy());
        ps.setTimestamp(7, Timestamp.valueOf(newCustomer.getLastUpdate()));
        ps.setString(8, newCustomer.getLastUpdatedBy());
        ps.setInt(9, DivisionDaoImpl.getDivisionId(newCustomer.getDivisionName(), conn));

        ps.execute(); // Execute PreparedStatement
        DBConnection.closeConnection();
    }

    /**This is the getAllCustomers method. It returns an observable list of Customer objects by selecting every customer record from the database, creating a a corresponding Customer object, and adding it to the list.
     * @return An observable list of Customer objects containing every customer in the database.*/
    public static ObservableList<Customer> getAllCustomers() throws SQLException {

        allCustomers.clear();

        Connection conn = DBConnection.startConnection();
        String selectStatement = "SELECT * FROM customers, first_level_divisions, countries " +
                "WHERE countries.Country_ID = first_level_divisions.COUNTRY_ID " +
                "AND first_level_divisions.Division_ID = customers.Division_ID " +
                "ORDER BY Customer_ID";
        DBQuery.setPreparedStatement(conn, selectStatement); //Create PreparedStatement
        PreparedStatement ps = DBQuery.getPreparedStatement();

        ps.execute(); // Execute PreparedStatement

        ResultSet result = ps.getResultSet();

        while(result.next()){

            int customerId = result.getInt("Customer_ID");
            String customerName = result.getString("Customer_Name");
            String address = result.getString("Address");
            String postalCode = result.getString("Postal_Code");
            String phone = result.getString("Phone");
            LocalDateTime createDate = result.getTimestamp("Create_Date").toLocalDateTime();
            String createdBy = result.getString("Created_By");
            LocalDateTime lastUpdate = result.getTimestamp("Last_Update").toLocalDateTime();
            String lastUpdatedBy = result.getString("Last_Updated_By");
            String countryName = result.getString("Country");
            String divisionName = result.getString("Division");

            Customer customerResult = new Customer(customerId, customerName, address, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdatedBy, countryName, divisionName);
            allCustomers.add(customerResult);
        }

        DBConnection.closeConnection();
        return allCustomers;
    }

    /**This is the getCustomer method. It returns a Customer object by selecting the customer record from the database with the Customer ID matching the customerId parameter.
     * @param customerId customer ID of the customer to return
     * @return A Customer object that matches the customerId parameter*/
    public static Customer getCustomer(int customerId, Connection conn) throws SQLException {
        String selectStatement = "SELECT * FROM customers, first_level_divisions, countries " +
                "WHERE countries.Country_ID = first_level_divisions.COUNTRY_ID " +
                "AND first_level_divisions.Division_ID = customers.Division_ID " +
                "AND Customer_ID = ?";
        DBQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        ps.setInt(1, customerId);

        ps.execute();

        ResultSet result = ps.getResultSet();

        result.next();

            String customerName = result.getString("Customer_Name");
            String address = result.getString("Address");
            String postalCode = result.getString("Postal_Code");
            String phone = result.getString("Phone");
            LocalDateTime createDate = result.getTimestamp("Create_Date").toLocalDateTime();
            String createdBy = result.getString("Created_By");
            LocalDateTime lastUpdate = result.getTimestamp("Last_Update").toLocalDateTime();
            String lastUpdatedBy = result.getString("Last_Updated_By");
            String countryName = result.getString("Country");
            String divisionName = result.getString("Division");

            Customer customerResult = new Customer(customerId, customerName, address, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdatedBy, countryName, divisionName);
            return customerResult;
        }

    /**This is the updateCustomer method. It updates the attributes customer in the database with the Customer ID that matches the Customer object passed in as a parameter.
     * @param updatedCustomer The customer to be updated. Its fields contain the updated information.*/
    public static void updateCustomer(Customer updatedCustomer) throws SQLException {

        Connection conn = DBConnection.startConnection();
        String updateStatement = "UPDATE customers " +
                "SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? " +
                "WHERE Customer_ID = ?";
        DBQuery.setPreparedStatement(conn, updateStatement); //Create PreparedStatement
        PreparedStatement ps = DBQuery.getPreparedStatement();

        // key-value mapping
        ps.setString(1, updatedCustomer.getCustomerName());
        ps.setString(2, updatedCustomer.getAddress());
        ps.setString(3, updatedCustomer.getPostalCode());
        ps.setString(4, updatedCustomer.getPhone());
        ps.setTimestamp(5, Timestamp.valueOf(updatedCustomer.getLastUpdate()));
        ps.setString(6, updatedCustomer.getLastUpdatedBy());
        ps.setInt(7, DivisionDaoImpl.getDivisionId(updatedCustomer.getDivisionName(), conn));
        ps.setInt(8, updatedCustomer.getCustomerId());

        ps.execute(); // Execute PreparedStatement
        DBConnection.closeConnection();
    }

    /**This is the deleteCustomer method. It deletes the customer record in the database with the Customer ID that matches the Customer object passed in as a parameter.
     * @param selectedCustomer The customer to be deleted from the database.*/
    public static void deleteCustomer(Customer selectedCustomer) throws SQLException {

        Connection conn = DBConnection.startConnection();
        String deleteStatement = "DELETE FROM customers WHERE Customer_ID = ?";
        DBQuery.setPreparedStatement(conn, deleteStatement); //Create PreparedStatement
        PreparedStatement ps = DBQuery.getPreparedStatement();

        // key-value mapping
        ps.setInt(1, selectedCustomer.getCustomerId());

        ps.execute(); // Execute PreparedStatement
        DBConnection.closeConnection();
    }
}
