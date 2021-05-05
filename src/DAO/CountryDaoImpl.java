package DAO;

import Model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBConnection;
import utils.DBQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**This is the Country DAO Implementation. It supplies an interface to create, read, update, and delete Country data in the database.*/
public class CountryDaoImpl {

    private static final ObservableList<Country> allCountries = FXCollections.observableArrayList();

    /**This is the getCountryId method. It accepts a country name as a parameter and returns the matching country ID.
     * @param countryName Name of the country of the country ID to return
     * @param conn Connection object
     * @return Country ID matching the country name parameter*/
    public static int getCountryId(String countryName, Connection conn) throws SQLException {

        String selectStatement = "SELECT * FROM countries WHERE Country = ?";
        DBQuery.setPreparedStatement(conn, selectStatement); //Create PreparedStatement
        PreparedStatement ps = DBQuery.getPreparedStatement();

        // key-value mapping
        ps.setString(1, countryName);

        ps.execute(); // Execute PreparedStatement

        ResultSet result = ps.getResultSet();

        result.next();

        int countryId = result.getInt("Country_ID");
        return countryId;
    }

    /**This is the getAllCountries method. It returns an observable list of Country objects by selecting every country record from the database, creating a a corresponding Country object, and adding it to the list.
     * @return An observable list of Country objects containing every country in the database.*/
    public static ObservableList<Country> getAllCountries() throws SQLException {

        allCountries.clear();

        Connection conn = DBConnection.startConnection();
        String selectStatement = "SELECT * FROM countries";
        DBQuery.setPreparedStatement(conn, selectStatement); //Create PreparedStatement
        PreparedStatement ps = DBQuery.getPreparedStatement();

        ps.execute(); // Execute PreparedStatement

        ResultSet result = ps.getResultSet();

        while (result.next()) {

            int countryId = result.getInt("Country_ID");
            String country = result.getString("Country");

            Country countryResult = new Country(countryId, country);
            allCountries.add(countryResult);
        }

        DBConnection.closeConnection();
        return allCountries;
    }
}
