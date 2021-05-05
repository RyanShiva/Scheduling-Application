package ViewController;


import DAO.AppointmentDaoImpl;
import DAO.UserDaoImpl;
import Model.Appointment;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.TimeZone;

/** This class controls the LoginController screen.*/
public class LoginController implements Initializable {

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

    /** This method creates a record in the login_activity file each time a Sign In is attempted.
     * @param isSuccessfulLogin true if the login was successful*/
    public void recordLogin(boolean isSuccessfulLogin) throws IOException {
        String filename = "login_activity.txt";

        FileWriter fileWriter = new FileWriter(filename, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        if (isSuccessfulLogin)
            printWriter.println(LocalDate.now() + " " + LocalTime.now() + " " + ZoneId.systemDefault() + "   User '" + User.getCurrentUser().getUserName() + "' successfully logged in.");
        else
            printWriter.println(LocalDate.now() + " " + LocalTime.now() + " " + ZoneId.systemDefault() + "   Login attempt failed.");
        printWriter.close();
    }

    @FXML
    private Label titleLbl;

    @FXML
    private Label usernameLbl;

    @FXML
    private TextField usernameTxt;

    @FXML
    private Label passwordLbl;

    @FXML
    private TextField passwordTxt;

    @FXML
    private Button signInLbl;

    @FXML
    private Button exitLbl;

    @FXML
    private Label locationLbl;

    /**This is the onActionExitApplication event handler. When the Exit button is clicked, this handler exits the java application.
     * @param event ActionEvent is generated when the user clicks the exit button in the Login screen*/
    @FXML
    public void onActionExitApplication(ActionEvent event) {
        System.exit(0);
    }

    /**This is the onActionSignIn event handler (Lambda Discussion #1).
     *
     * Lambda Discussion 1:
     * The onActionSignIn method contains 1 lambda expression.
     * The onActionSignIn handler needs to generate an alert when the user logs in, and the contents of the alert depend on whether there is an upcoming appointment scheduled for the user within 15 minutes of the login time.
     * To generate this alert, the LoginInterface is instantiated by using a lambda expression.
     * The abstract method belonging to LoginInterface is the loginAlert method, which accepts a User object as a parameter corresponding to the parameter of the lambda expression.
     * The parameter of the lambda is passed to the body, where the code that generates the alert is located.
     * The loginAlert method of upcomingAppointmentAlert is called, passing in the current user as a User parameter.
     * The lambda expression is a succinct and convenient way to implement the abstract method of LoginInterface without the need to create an anonymous class.
     * The body of the lambda could easily be passed to other parts of the application if the alert needed to be generated in other screens or different event handlers.
     * @param event ActionEvent is generated when the user clicks the Sign In button in the Login screen*/
    @FXML
    public void onActionSignIn(ActionEvent event) throws IOException, SQLException {
        String username = usernameTxt.getText();
        String password = passwordTxt.getText();

        if (!UserDaoImpl.isValidUser(username)) {
            recordLogin(false);
            Alert alert = new Alert(Alert.AlertType.ERROR);

            try {
                ResourceBundle rbundle = ResourceBundle.getBundle("Lang/Nat", Locale.getDefault());
                if(Locale.getDefault().getLanguage().equals("fr")) {
                    alert.setContentText(rbundle.getString("Please enter a valid username."));
                    alert.showAndWait();
                    return;
                }
            }
            catch (MissingResourceException e) {
                System.out.println(e.getMessage());
            }

            alert.setContentText("Please enter a valid username.");
            alert.showAndWait();
            return;
        }
        if (!UserDaoImpl.isCorrectPassword(username, password)) {
            recordLogin(false);
            Alert alert = new Alert(Alert.AlertType.ERROR);

            try {
                ResourceBundle rbundle = ResourceBundle.getBundle("Lang/Nat", Locale.getDefault());
                if(Locale.getDefault().getLanguage().equals("fr")) {
                    alert.setContentText(rbundle.getString("Incorrect Password!"));
                    alert.showAndWait();
                    return;
                }
            }
            catch (MissingResourceException e) {
                System.out.println(e.getMessage());
            }

            alert.setContentText("Incorrect Password!");
            alert.showAndWait();
            return;
        }

        User.setCurrentUser(UserDaoImpl.getUser(username));
        recordLogin(true);

        switchScreen("/ViewController/Menu.fxml", event);

        LoginInterface upcomingAppointmentAlert = user -> {
            ObservableList<Appointment> allAppointments = AppointmentDaoImpl.getAllAppointments();
            ObservableList<Appointment> filteredAppointments = FXCollections.observableArrayList();

            LocalDateTime currentDateTime = LocalDateTime.now();
            for(Appointment appointment : allAppointments) {
                if(appointment.getUserId() != user.getUserId())
                    continue;
                LocalDateTime start = appointment.getStart();
                long timeDifference = ChronoUnit.MINUTES.between(currentDateTime, start) + 1;
                if(0 < timeDifference && timeDifference <= 15 && currentDateTime.isBefore(start))
                    filteredAppointments.add(appointment);
            }

            if(filteredAppointments.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("You have no upcoming appointments in the next 15 minutes.");
                alert.showAndWait();
                return;
            }

            Appointment upcomingAppointment = new Appointment();
            LocalTime appointmentTime = currentDateTime.toLocalTime().plusMinutes(17L);
            for(Appointment appointment : filteredAppointments) {
                if(appointment.getStart().toLocalTime().isBefore(appointmentTime)) {
                    upcomingAppointment = appointment;
                    appointmentTime = appointment.getStart().toLocalTime();
                }
            }

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("You have an appointment scheduled for "
                    + (ChronoUnit.MINUTES.between(currentDateTime.toLocalTime(), appointmentTime) + 1L)
                    + " minutes from now!"
                    + "\n\nAppointment ID: #" + upcomingAppointment.getAppointmentId()
                    + "\nDate: " + upcomingAppointment.getStart().toLocalDate()
                    + "\nStart Time: " + appointmentTime);
            alert.showAndWait();
        };

        upcomingAppointmentAlert.loginAlert(User.getCurrentUser());
    }

    /**This is the initialize method. This method initializes the controller.
     * @param rb resource bundle
     * @param url URL*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ResourceBundle rbundle = ResourceBundle.getBundle("Lang/Nat", Locale.getDefault());
            if(Locale.getDefault().getLanguage().equals("fr")) {
                titleLbl.setText(rbundle.getString("Scheduling") + " " + rbundle.getString("Application") + " " + rbundle.getString("Login"));
                usernameLbl.setText(rbundle.getString("Username") + ": ");
                passwordLbl.setText(rbundle.getString("Password") + ": ");
                signInLbl.setText(rbundle.getString("Sign In"));
                exitLbl.setText(rbundle.getString("Exit"));
                locationLbl.setText(rbundle.getString("Time Zone") + ": ");
            }
        }
        catch (MissingResourceException e) {
            System.out.println(e.getMessage());
        }

        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
        locationLbl.setText(locationLbl.getText() + localZoneId);
    }

}
