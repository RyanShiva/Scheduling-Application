package DAO;

import Model.FirstLevelDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBConnection;
import utils.DBQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**This is the FirstLevelDivision DAO Implementation. It supplies an interface to create, read, update, and delete FirstLevelDivision data in the database.*/
public class DivisionDaoImpl {

    private static final ObservableList<FirstLevelDivision> selectedCountryDivisions = FXCollections.observableArrayList();

    /**This is the getDivisionId method. It accepts a division name as a parameter and returns the matching Division ID.
     * @param divisionName Name of the division of the division ID to return
     * @param conn Connection object
     * @return Division ID matching the division name parameter*/
    public static int getDivisionId(String divisionName, Connection conn) throws SQLException {

        String selectStatement = "SELECT * FROM first_level_divisions WHERE Division = ?";
        DBQuery.setPreparedStatement(conn, selectStatement); //Create PreparedStatement
        PreparedStatement ps = DBQuery.getPreparedStatement();

        // key-value mapping
        ps.setString(1, divisionName);

        ps.execute(); // Execute PreparedStatement

        ResultSet result = ps.getResultSet();

        result.next();

        int divisionId = result.getInt("Division_ID");
        return divisionId;
    }


    /**This is the getCountryDivisions method. It returns an observable list of FirstLevelDivision objects by selecting the division records from the database that match the country ID parameter.
     * @param countryId The country ID of the divisions to return.
     * @return An observable list of FirstLevelDivision objects containing objects corresponding to the division records from the database that match the countryId parameter.*/
    public static ObservableList<FirstLevelDivision> getCountryDivisions(int countryId) throws SQLException {

        selectedCountryDivisions.clear();

        Connection conn = DBConnection.startConnection();
        String selectStatement = "SELECT * FROM first_level_divisions WHERE COUNTRY_ID = ?";
        DBQuery.setPreparedStatement(conn, selectStatement); //Create PreparedStatement
        PreparedStatement ps = DBQuery.getPreparedStatement();

        //key-value mapping
        ps.setInt(1, countryId);

        ps.execute(); // Execute PreparedStatement

        ResultSet result = ps.getResultSet();

        while (result.next()) {

            int divisionId = result.getInt("Division_ID");
            String division = result.getString("Division");

            FirstLevelDivision divisionResult = new FirstLevelDivision(divisionId, division, countryId);
            selectedCountryDivisions.add(divisionResult);
        }

        DBConnection.closeConnection();
        return selectedCountryDivisions;
    }
}