package ViewController;


import DAO.AppointmentDaoImpl;
import DAO.ContactDaoImpl;
import DAO.CustomerDaoImpl;
import Model.Appointment;
import Model.Contact;
import Model.Customer;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.util.ResourceBundle;

/** This class controls the AddAppointmentController screen.*/
public class AddAppointmentController implements Initializable {

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

    /** This method checks the fields of the Add Appointment form to make sure that every field contains input.
     * It checks whether the text field input is too many characters.
     * It also checks the start and end times to make sure the input will create valid LocalTime objects.
     * The method will generate alerts if there are any errors in the input and then return false.
     * @return true if all the fields of the form contain valid input.*/
    public Boolean isValidInput() {
        boolean emptyInput = false;
        String errorMessage = "The following required fields are empty:";

        String title = titleTxt.getText().trim();
        if(title.isEmpty()) {
            errorMessage = errorMessage + "\nTitle";
            emptyInput = true;
        }
        String description = descriptionTxt.getText().trim();
        if(description.isEmpty()) {
            errorMessage = errorMessage + "\nDescription";
            emptyInput = true;
        }
        String location = locationTxt.getText().trim();
        if(location.isEmpty()) {
            errorMessage = errorMessage + "\nLocation";
            emptyInput = true;
        }
        String type = typeTxt.getText().trim();
        if(type.isEmpty()) {
            errorMessage = errorMessage + "\nType";
            emptyInput = true;
        }
        if(contactCombo.getSelectionModel().getSelectedItem() == null) {
            errorMessage = errorMessage + "\nContact";
            emptyInput = true;
        }
        if(startDatePicker.getValue() == null) {
            errorMessage = errorMessage + "\nStart Date";
            emptyInput = true;
        }
        String startHour = startHourTxt.getText().trim();
        String startMinute = startMinuteTxt.getText().trim();
        if(startHour.isEmpty() ||
                startMinute.isEmpty() ||
                startTimeChoice.getSelectionModel().getSelectedItem() == null) {
            errorMessage = errorMessage + "\nStart Time";
            emptyInput = true;
        }
        if(endDatePicker.getValue() == null) {
            errorMessage = errorMessage + "\nEnd Date";
            emptyInput = true;
        }
        String endHour = endHourTxt.getText().trim();
        String endMinute = endMinuteTxt.getText().trim();
        if(endHour.isEmpty() ||
                endMinute.isEmpty() ||
                endTimeChoice.getSelectionModel().getSelectedItem() == null) {
            errorMessage = errorMessage + "\nEnd Time";
            emptyInput = true;
        }
        if(customerCombo.getSelectionModel().getSelectedItem() == null) {
            errorMessage = errorMessage + "\nCustomer";
            emptyInput = true;
        }

        if(emptyInput) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }

        boolean tooLongInput = false;
        String errorMessage2 = "Text must be 50 characters or fewer. The following fields contain too many characters:";
        if(title.length() > 50) {
            errorMessage2 = errorMessage2 + "\nTitle";
            tooLongInput = true;
        }
        if(description.length() > 50) {
            errorMessage2 = errorMessage2 + "\nDescription";
            tooLongInput = true;
        }
        if(location.length() > 50) {
            errorMessage2 = errorMessage2 + "\nLocation";
            tooLongInput = true;
        }
        if(type.length() > 50) {
            errorMessage2 = errorMessage2 + "\nType";
            tooLongInput = true;
        }

        if(tooLongInput) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText(errorMessage2);
            alert2.showAndWait();
            return false;
        }

        boolean correctTimeInput = true;
        if(!startHour.matches("^(1[0-2]|0?[1-9])$") ||
                !startMinute.matches("^[0-5][0-9]$")) {
            correctTimeInput = false;
        }
        if(!endHour.matches("^(1[0-2]|0?[1-9])$") ||
                !endMinute.matches("^[0-5][0-9]$")) {
            correctTimeInput = false;
        }

        if(!correctTimeInput) {
            Alert alert3 = new Alert(Alert.AlertType.ERROR);
            alert3.setContentText("An invalid Start/End Time was entered.");
            alert3.showAndWait();
        }

        return correctTimeInput;
    }

    /** This method checks start and end times to make sure that the start time is before the end time.
     * @param start start time
     * @param end end time
     * @return true if the start time is before the end time, otherwise false*/
    public boolean startBeforeEnd(LocalDateTime start, LocalDateTime end) {
        if(start.isBefore(end))
            return true;
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The appointment start time must be before the end time.");
            alert.showAndWait();
            return false;
        }
    }

    /** This method checks start and end times to make sure that the appointment is within business hours. If the appointment is outside of business hours, then the method generates an error alert and returns false.
     * @param start start time
     * @param end end time
     * @return true if the appointment is within the business hours, otherwise false*/
    public boolean isWithinBusinessHours(LocalDateTime start, LocalDateTime end) {
        ZonedDateTime zonedLocalStart = start.atZone(ZoneId.systemDefault());
        ZonedDateTime zonedEasternStart = zonedLocalStart.withZoneSameInstant(ZoneId.of("US/Eastern"));
        LocalTime convertedStart = zonedEasternStart.toLocalTime();

        ZonedDateTime zonedLocalEnd = end.atZone(ZoneId.systemDefault());
        ZonedDateTime zonedEasternEnd = zonedLocalEnd.withZoneSameInstant(ZoneId.of("US/Eastern"));
        LocalTime convertedEnd = zonedEasternEnd.toLocalTime();

        if(convertedStart.isBefore(LocalTime.of(8, 0)) ||
                convertedStart.isAfter(LocalTime.of(22, 0)) ||
                convertedEnd.isBefore(LocalTime.of(8, 0)) ||
                convertedEnd.isAfter(LocalTime.of(22, 0))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The appointment time cannot be outside of business hours defined as 8:00 a.m. to 10:00 p.m. EST");
            alert.showAndWait();
            return false;
        }
        else
            return true;
    }

    /** This method checks start and end times to make sure that the appointment time does not overlap with another appointment with the same customer. If the appointment overlaps, then the method generates an error alert and returns true.
     * @param start start time
     * @param end end time
     * @param customerId The Customer ID of the Customer associated with the appointment
     * @return true if the appointment time overlaps with another appointment with the customer, otherwise false*/
    public boolean appointmentTimesOverlap(LocalDateTime start, LocalDateTime end, int customerId) throws SQLException {
        ObservableList<Appointment> allAppointments = AppointmentDaoImpl.getAllAppointments();
        ObservableList<Appointment> filteredAppointments = FXCollections.observableArrayList();

        for(Appointment appointment : allAppointments) {
            if(appointment.getCustomerId() == customerId)
                filteredAppointments.add(appointment);
        }

        boolean appointmentTimesOverlap = false;
        for(Appointment appointment : filteredAppointments) {
            if(start.isBefore(appointment.getStart())) {
                if((end.isAfter(appointment.getStart()))) {
                    appointmentTimesOverlap = true;
                    break;
                }
            }
            else if(appointment.getStart().isBefore(start)) {
                if((appointment.getEnd().isAfter(start))) {
                    appointmentTimesOverlap = true;
                    break;
                }
            }
            else {
                appointmentTimesOverlap = true;
                break;
            }
        }

        if(appointmentTimesOverlap) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The appointment time overlaps with another appointment Customer #" + customerId + " has scheduled.");
            alert.showAndWait();
        }

        return appointmentTimesOverlap;
    }

    @FXML
    private TextArea descriptionTxt;

    @FXML
    private TextField titleTxt;

    @FXML
    private TextField locationTxt;

    @FXML
    private TextField typeTxt;

    @FXML
    private ComboBox<Contact> contactCombo;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private ComboBox<Customer> customerCombo;

    @FXML
    private TextField endHourTxt;

    @FXML
    private TextField endMinuteTxt;

    @FXML
    private ChoiceBox<String> endTimeChoice;

    @FXML
    private TextField startHourTxt;

    @FXML
    private TextField startMinuteTxt;

    @FXML
    private ChoiceBox<String> startTimeChoice;

    @FXML
    private TextField userIDTxt;

    /**This is the onActionCancelAddAppointment event handler. When the Cancel button is clicked, this handler returns the user to the Appointments screen without modifying the database.
     * @param event ActionEvent is generated when the user clicks the Cancel button*/
    @FXML
    public void onActionCancelAddAppointment(ActionEvent event) throws IOException {
        switchScreen("/ViewController/Appointments.fxml", event);
    }

    /**This is the onActionSaveAppointment event handler.
     * When the Save button is clicked, this handler checks the fields for input.
     * If the input is missing or contains errors, the handler generates one or more error alerts.
     * If all fields contain valid data, then a new appointment is saved to the database and the user is returned to the Appointments screen.
     * @param event ActionEvent is generated when the user clicks the Save button*/
    @FXML
    public void onActionSaveAppointment(ActionEvent event) throws IOException, SQLException {
        if(!isValidInput())
            return;
        int appointmentId = -1;
        String title = titleTxt.getText().trim();
        String description = descriptionTxt.getText().trim();
        String location = locationTxt.getText().trim();
        String type = typeTxt.getText().trim();

        LocalDate startDate = startDatePicker.getValue();
        String startHour = startHourTxt.getText();
        String startMinute = startMinuteTxt.getText();
        if (startTimeChoice.getSelectionModel().getSelectedItem().equals("p.m.")) {
            if(Integer.parseInt(startHour) != 12)
                startHour = Integer.toString(Integer.parseInt(startHour) + 12);
        }
        else {
            if(Integer.parseInt(startHour) == 12)
                startHour = "00";
        }
        if(startHour.length() == 1)
            startHour = "0" + startHour;
        LocalTime startTime = LocalTime.parse(startHour + ":" + startMinute);
        LocalDateTime start = LocalDateTime.of(startDate, startTime);

        LocalDate endDate = endDatePicker.getValue();
        String endHour = endHourTxt.getText();
        String endMinute = endMinuteTxt.getText();

        if (endTimeChoice.getSelectionModel().getSelectedItem().equals("p.m.")) {
            if (Integer.parseInt(endHour) != 12)
                endHour = Integer.toString(Integer.parseInt(endHour) + 12);
        }
        else {
            if(Integer.parseInt(endHour) == 12)
                endHour = "00";
        }

        if(endHour.length() == 1)
            endHour = "0" + endHour;
        LocalTime endTime = LocalTime.parse(endHour + ":" + endMinute);
        LocalDateTime end = LocalDateTime.of(endDate, endTime);

        boolean startBeforeEnd = startBeforeEnd(start, end);
        boolean isWithinBusinessHours = isWithinBusinessHours(start, end);
        boolean appointmentTimesOverlap = appointmentTimesOverlap(start, end, customerCombo.getSelectionModel().getSelectedItem().getCustomerId());
        if(!startBeforeEnd || !isWithinBusinessHours || appointmentTimesOverlap)
            return;

        LocalDateTime createDate = LocalDateTime.now();
        String createdBy = User.getCurrentUser().getUserName();
        LocalDateTime lastUpdate = LocalDateTime.now();
        String lastUpdatedBy = User.getCurrentUser().getUserName();
        int customerId = customerCombo.getSelectionModel().getSelectedItem().getCustomerId();
        int userId = User.getCurrentUser().getUserId();
        Contact contact = contactCombo.getSelectionModel().getSelectedItem();

        Appointment newAppointment = new Appointment(appointmentId, title, description, location, type, start, end, createDate, createdBy, lastUpdate, lastUpdatedBy, customerId, userId, contact);
        AppointmentDaoImpl.addAppointment(newAppointment);


            switchScreen("/ViewController/Appointments.fxml", event);
    }

    /**This is the initialize method. This method initializes the controller.
     * @param rb resource bundle
     * @param url URL*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        startTimeChoice.getItems().addAll("a.m.", "p.m.");
        endTimeChoice.getItems().addAll("a.m.", "p.m.");
        try {
            contactCombo.setItems(ContactDaoImpl.getAllContacts());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            customerCombo.setItems(CustomerDaoImpl.getAllCustomers());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        userIDTxt.setText(Integer.toString(User.getCurrentUser().getUserId()));
    }

}
