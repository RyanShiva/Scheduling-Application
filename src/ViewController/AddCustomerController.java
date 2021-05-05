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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/** This class controls the AddCustomerController screen.*/
public class AddCustomerController implements Initializable {

    Stage stage;
    Parent scene;

    /** This method switches the screen to the location specified by the location parameter.
     * @param location path to the screen to load
     * @param event ActionEvent caused by the user clicking a button*/
    public void switchScreen(String location, ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(location));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This method checks the fields of the Add Customer form to make sure that every field contains input.
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

    /**This is the onActionCancelAddCustomer event handler. When the Cancel button is clicked, this handler returns the user to the Customers screen without modifying the database.
     * @param event ActionEvent is generated when the user clicks the Cancel button*/
    @FXML
    public void onActionCancelAddCustomer(ActionEvent event) throws IOException {
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
    }

    /**This is the onActionSaveCustomer event handler.
     * When the Save button is clicked, this handler checks the fields for input.
     * If the input is missing or contains errors, the handler generates one or more error alerts.
     * If all fields contain valid data, then a new customer is saved to the database and the user is returned to the Appointments screen.
     * @param event ActionEvent is generated when the user clicks the Save button*/
    @FXML
    public void onActionSaveCustomer(ActionEvent event) throws IOException, SQLException {

        if(!isValidInput())
            return;
        int customerId = -1;
        String customerName = nameTxt.getText().trim();
        String address = addressTxt.getText().trim();
        String postalCode = postalCodeTxt.getText().trim();
        String phone = phoneNumberTxt.getText().trim();
        LocalDateTime createDate = LocalDateTime.now();
        String createdBy = User.getCurrentUser().getUserName();
        LocalDateTime lastUpdate = LocalDateTime.now();
        String lastUpdatedBy = User.getCurrentUser().getUserName();
        String countryName = countryCombo.getSelectionModel().getSelectedItem().getCountry();
        String divisionName = divisionCombo.getSelectionModel().getSelectedItem().getDivision();

        Customer newCustomer = new Customer(customerId, customerName, address, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdatedBy, countryName, divisionName);
        CustomerDaoImpl.addCustomer(newCustomer);

        switchScreen("/ViewController/Customers.fxml", event);
    }

    /**This is the initialize method. This method initializes the controller.
     * @param rb resource bundle
     * @param url URL*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            countryCombo.setItems(CountryDaoImpl.getAllCountries());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
