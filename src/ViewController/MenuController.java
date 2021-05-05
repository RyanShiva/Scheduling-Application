package ViewController;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This class controls the MenuController screen.*/
public class MenuController implements Initializable {

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

    /**This is the onActionDisplayAppointments event handler. When the Appointments button is clicked, this handler switches to the Appointments screen.
     * @param event ActionEvent is generated when the user clicks the Appointments button in the Menu screen*/
    @FXML
    public void onActionDisplayAppointments(ActionEvent event) throws IOException {
        switchScreen("/ViewController/Appointments.fxml", event);
    }

    /**This is the onActionDisplayCustomers event handler. When the Customers button is clicked, this handler switches to the Customers screen.
     * @param event ActionEvent is generated when the user clicks the Customers button in the Menu screen*/
    @FXML
    public void onActionDisplayCustomers(ActionEvent event) throws IOException {
        switchScreen("/ViewController/Customers.fxml", event);
    }

    /**This is the onActionDisplayReports event handler. When the Reports button is clicked, this handler switches to the Reports screen.
     * @param event ActionEvent is generated when the user clicks the Reports button in the Menu screen*/
    @FXML
    public void onActionDisplayReports(ActionEvent event) throws IOException {
        switchScreen("/ViewController/Reports.fxml", event);
    }

    /**This is the onActionExitApplication event handler. When the Exit button is clicked, this handler exits the java application.
     * @param event ActionEvent is generated when the user clicks the exit button in the Menu screen*/
    @FXML
    public void onActionExitApplication(ActionEvent event) {
        System.exit(0);
    }

    /**This is the initialize method. This method initializes the controller.
     * @param rb resource bundle
     * @param url URL*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
