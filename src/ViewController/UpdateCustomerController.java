package ViewController;


import DAO.CountryDaoImpl;
import DAO.CustomerDaoImpl;
import DAO.DivisionDaoImpl;
import Model.Country;
import Model.Customer;
import Model.FirstLevelDivision;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.DBConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/** This class controls the UpdateCustomerController screen.*/
public class UpdateCustomerController implements Initializable {

    Stage stage;
    Parent scene;

    Customer customerToUpdate = new Customer();

    /** This method switches the screen to the location specified by the location parameter.
     * @param location path to the screen to load
     * @param event ActionEvent caused by the user clicking a button*/
    public void switchScreen(String location, ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(location));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This method checks the fields of the Update Customer form to make sure that every field contains input.
     * It checks whether the text field input is too many characters.
     * The method will generate alerts if there are any errors in the input and then return false.
     * @return true if all the fields of the form contain valid input.*/
    public Boolean isValidInput() {
        boolean emptyInput = false;
        String errorMessage = "The following required fields are empty:";
        if(nameTxt.getText().trim().isEmpty()) {
            errorMessage = errorMessage + "\nName";
            emptyInput = true;
        }
        if(addressTxt.getText().trim().isEmpty()) {
            errorMessage = errorMessage + "\nAddress";
            emptyInput = true;
        }
        if(postalCodeTxt.getText().trim().isEmpty()) {
            errorMessage = errorMessage + "\nPostal Code";
            emptyInput = true;
        }
        if(phoneNumberTxt.getText().trim().isEmpty()) {
            errorMessage = errorMessage + "\nPhone Number";
            emptyInput = true;
        }
        if(countryCombo.getSelectionModel().getSelectedItem() == null) {
            errorMessage = errorMessage + "\nCountry";
            emptyInput = true;
        }
        if(divisionCombo.getSelectionModel().getSelectedItem() == null) {
            errorMessage = errorMessage + "\nDivision";
            emptyInput = true;
        }

        if(emptyInput) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(errorMessage);
            alert.showAndWait();
        }

        boolean tooLongInput = false;
        String errorMessage2 = "Text must be 50 characters or fewer. The following fields contain too many characters:";
        if(nameTxt.getText().trim().length() > 50) {
            errorMessage2 = errorMessage2 + "\nName";
            tooLongInput = true;
        }
        if(addressTxt.getText().trim().length() > 50) {
            errorMessage2 = errorMessage2 + "\nAddress";
            tooLongInput = true;
        }
        if(postalCodeTxt.getText().trim().length() > 50) {
            errorMessage2 = errorMessage2 + "\nPostal Code";
            tooLongInput = true;
        }
        if(phoneNumberTxt.getText().trim().length() > 50) {
            errorMessage2 = errorMessage2 + "\nPhone Number";
            tooLongInput = true;
        }

        if(tooLongInput) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText(errorMessage2);
            alert2.showAndWait();
        }

        return !(emptyInput || tooLongInput);
    }

    @FXML
    private TextField idTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField addressTxt;

    @FXML
    private TextField postalCodeTxt;

    @FXML
    private TextField phoneNumberTxt;

    @FXML
    private ComboBox<Country> countryCombo;

    @FXML
    private ComboBox<FirstLevelDivision> divisionCombo;

    /**This is the onActionCancelUpdateCustomer event handler. When the Cancel button is clicked, this handler returns the user to the Customers screen without modifying the database.
     * @param event ActionEvent is generated when the user clicks the Cancel button*/
    @FXML
    public void onActionCancelUpdateCustomer(ActionEvent event) throws IOException {
        switchScreen("/ViewController/Customers.fxml", event);
    }

    /**This method gets all the divisions of a country and populates divisionCombo with an observable list of FirstLevelDivisions.
     * @param event ActionEvent is generated when the user selects a country in countryCombo*/
    @FXML
    public void onActionFilterDivision(ActionEvent event) throws IOException {
        if(countryCombo.getSelectionModel().getSelectedItem() == null)
            return;

        int countryId = countryCombo.getSelectionModel().getSelectedItem().getCountryId();

        try {
            divisionCombo.setItems(DivisionDaoImpl.getCountryDivisions(countryId));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        divisionCombo.getSelectionModel().selectFirst();
        divisionCombo.getSelectionModel().clearSelection();
    }

    /**This is the onActionSaveCustomer event handler.
     * When the Save button is clicked, this handler checks the fields for input.
     * If the input is missing or contains errors, the handler generates one or more error alerts.
     * If all fields contain valid data, then the customer is updated in the database and the user is returned to the Appointments screen.
     * @param event ActionEvent is generated when the user clicks the Save button*/
    @FXML
    public void onActionSaveCustomer(ActionEvent event) throws IOException, SQLException {
        if(!isValidInput())
            return;

        customerToUpdate.setCustomerName(nameTxt.getText());
        customerToUpdate.setAddress(addressTxt.getText());
        customerToUpdate.setPostalCode(postalCodeTxt.getText());
        customerToUpdate.setPhone(phoneNumberTxt.getText());
        customerToUpdate.setLastUpdate(LocalDateTime.now());
        customerToUpdate.setLastUpdatedBy(User.getCurrentUser().getUserName());
        customerToUpdate.setCountryName(countryCombo.getSelectionModel().getSelectedItem().getCountry());
        customerToUpdate.setDivisionName(divisionCombo.getSelectionModel().getSelectedItem().getDivision());

        CustomerDaoImpl.updateCustomer(customerToUpdate);

        switchScreen("/ViewController/Customers.fxml", event);
    }

    /**This method sends an Customer object to the UpdateCustomerController.
     * @param customer Customer object to send*/
    public void sendCustomer(Customer customer) throws SQLException {

        customerToUpdate.setCustomerId(customer.getCustomerId());
        customerToUpdate.setCustomerName(customer.getCustomerName());
        customerToUpdate.setAddress(customer.getAddress());
        customerToUpdate.setPostalCode(customer.getPostalCode());
        customerToUpdate.setPhone(customer.getPhone());
        customerToUpdate.setCreateDate(customer.getCreateDate());
        customerToUpdate.setCreatedBy(customer.getCreatedBy());
        customerToUpdate.setLastUpdate(customer.getLastUpdate());
        customerToUpdate.setLastUpdatedBy(customer.getLastUpdatedBy());
        customerToUpdate.setCountryName(customer.getCountryName());
        customerToUpdate.setDivisionName(customer.getDivisionName());

        idTxt.setText(Integer.toString(customerToUpdate.getCustomerId()));
        nameTxt.setText(customerToUpdate.getCustomerName());
        addressTxt.setText(customerToUpdate.getAddress());
        postalCodeTxt.setText(customerToUpdate.getPostalCode());
        phoneNumberTxt.setText(customerToUpdate.getPhone());

        Connection conn = DBConnection.startConnection();

        try {
            countryCombo.setItems(CountryDaoImpl.getAllCountries());
            Country country = new Country();
            country.setCountryId(CountryDaoImpl.getCountryId(customerToUpdate.getCountryName(), conn));
            country.setCountry(customerToUpdate.getCountryName());
            countryCombo.setValue(country);

            divisionCombo.setItems(DivisionDaoImpl.getCountryDivisions(country.getCountryId()));
            FirstLevelDivision division = new FirstLevelDivision();
            division.setDivisionId(DivisionDaoImpl.getDivisionId(customerToUpdate.getDivisionName(), conn));
            division.setDivision(customerToUpdate.getDivisionName());
            division.setCountryId(country.getCountryId());
            divisionCombo.setValue(division);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBConnection.closeConnection();

    }

    /**This is the initialize method. This method initializes the controller.
     * @param rb resource bundle
     * @param url URL*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
