package ViewController;


import DAO.AppointmentDaoImpl;
import Model.Appointment;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

/** This class controls the AppointmentsController screen.*/
public class AppointmentsController implements Initializable {

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

    /** This method gets all appointments from the database and uses them to populate the Appointments tableview.*/
    public void populateAppointmentsView() {
        try {
            appointmentsView.setItems(AppointmentDaoImpl.getAllAppointments());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**This method populates the Appointments tableview with all appointments in a given month.*/
    public void filterAppointmentsByMonth() throws SQLException {
        ObservableList<Appointment> allAppointmentsList = AppointmentDaoImpl.getAllAppointments();
        ObservableList<Appointment> filteredAppointmentsList = FXCollections.observableArrayList();

        Month selectedMonth = appointmentDatePicker.getValue().getMonth();
        int selectedYear = appointmentDatePicker.getValue().getYear();

        for (int i = 0; i < allAppointmentsList.size(); ++i) {
            if((allAppointmentsList.get(i).getStart().toLocalDate().getMonth() == selectedMonth &&
                    allAppointmentsList.get(i).getStart().toLocalDate().getYear() == selectedYear) ||
                    (allAppointmentsList.get(i).getEnd().toLocalDate().getMonth() == selectedMonth) &&
                    allAppointmentsList.get(i).getEnd().toLocalDate().getYear() == selectedYear) {
                filteredAppointmentsList.add(allAppointmentsList.get(i));
            }
        }
        appointmentsView.setItems(filteredAppointmentsList);
    }

    /**This method populates the Appointments tableview with all appointments in a given week.*/
    public void filterAppointmentsByWeek() throws SQLException {
        ObservableList<Appointment> allAppointmentsList = AppointmentDaoImpl.getAllAppointments();
        ObservableList<Appointment> filteredAppointmentsList = FXCollections.observableArrayList();

        LocalDate selectedDay = appointmentDatePicker.getValue();
        DayOfWeek dayOfWeek = selectedDay.getDayOfWeek();
        LocalDate startOfWeek = selectedDay.minusDays(dayOfWeek.getValue() - 1);
        LocalDate endOfWeek = startOfWeek.plusDays(6);

        for (int i = 0; i < allAppointmentsList.size(); ++i) {
            if((allAppointmentsList.get(i).getStart().toLocalDate().isAfter(startOfWeek.minusDays(1)) &&
                    allAppointmentsList.get(i).getStart().toLocalDate().isBefore(endOfWeek.plusDays(1))) ||
                    (allAppointmentsList.get(i).getEnd().toLocalDate().isAfter(startOfWeek.minusDays(1)) &&
                    allAppointmentsList.get(i).getEnd().toLocalDate().isBefore(endOfWeek.plusDays(1)))) {
                filteredAppointmentsList.add(allAppointmentsList.get(i));
            }
        }
        appointmentsView.setItems(filteredAppointmentsList);
    }

    @FXML
    private TableView<Appointment> appointmentsView;

    @FXML
    private TableColumn<Appointment, Integer> appointmentIdCol;

    @FXML
    private TableColumn<Appointment, String> titleCol;

    @FXML
    private TableColumn<Appointment, String> descriptionCol;

    @FXML
    private TableColumn<Appointment, String> locationCol;

    @FXML
    private TableColumn<Appointment, String> contactCol;

    @FXML
    private TableColumn<Appointment, String> typeCol;

    @FXML
    private TableColumn<Appointment, LocalDateTime> startCol;

    @FXML
    private TableColumn<Appointment, LocalDateTime> endCol;

    @FXML
    private TableColumn<Appointment, Integer> customerIdCol;

    @FXML
    private DatePicker appointmentDatePicker;

    @FXML
    private RadioButton viewAllAppointmentsRBtn;

    @FXML
    private RadioButton viewByMonthRBtn;

    @FXML
    private RadioButton viewByWeekRBtn;

    /**This is the onActionFilterAppointmentsByMonth event handler. When the View by Month radio button is selected, the handler filters the Appointments TableView to only show appointments in a given month.
     * @param event ActionEvent generated by the user clicking the View by Month radio button.*/
    @FXML
    public void onActionFilterAppointmentsByMonth(ActionEvent event) throws SQLException {
        if(appointmentDatePicker.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a date in the date picker. Appointments will be filtered by the month of the date you select.");
            alert.showAndWait();
            viewAllAppointmentsRBtn.setSelected(true);
            return;
        }

        filterAppointmentsByMonth();
    }

    /**This is the onActionFilterAppointmentsByWeek event handler. When the View by Week radio button is selected, the handler filters the Appointments TableView to only show appointments in a given week.
     * @param event ActionEvent generated by the user clicking the View by Week radio button.*/
    @FXML
    public void onActionFilterAppointmentsByWeek(ActionEvent event) throws SQLException {
        if(appointmentDatePicker.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a date in the date picker. Appointments will be filtered by the week of the date you select.");
            alert.showAndWait();
            viewAllAppointmentsRBtn.setSelected(true);
            return;
        }

        filterAppointmentsByWeek();
    }

    /**This is the onActionShowAllAppointments event handler. When the View All Appointments radio button is selected, the handler updates the Appointments TableView to show all appointments.
     * @param event ActionEvent generated by the user clicking the View All Appointments radio button.*/
    @FXML
    public void onActionShowAllAppointments(ActionEvent event) {
        populateAppointmentsView();
    }

    /**This is the onActionUpdateAppointmentsView event handler. When a date is selected in the Date Picker, the handler updates the Appointments TableView based on the radio button that is selected.
     * @param event ActionEvent generated by the user selecting a date in the Date Picker*/
    @FXML
    public void onActionUpdateAppointmentsView(ActionEvent event) throws SQLException {
        if(viewByMonthRBtn.isSelected())
            filterAppointmentsByMonth();
        else if(viewByWeekRBtn.isSelected())
            filterAppointmentsByWeek();
    }

    /** This is the onActionDeleteAppointment event handler.
     * When the Delete button is clicked, the handler checks whether there is an Appointment selected, then deletes the appointment from the database.
     * @param event ActionEvent generated by the user clicking the Delete button.*/
    @FXML
    public void onActionDeleteAppointment(ActionEvent event) throws SQLException {
        if(appointmentsView.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select an appointment to delete.");
            alert.showAndWait();

            return;
        }

        Alert alert2 = new Alert (Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this appointment?");
        Optional<ButtonType> result = alert2.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            Appointment appointmentToDelete = appointmentsView.getSelectionModel().getSelectedItem();
            AppointmentDaoImpl.deleteAppointment(appointmentToDelete);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Appointment with ID #" + appointmentToDelete.getAppointmentId() + " was successfully cancelled.\nType: " + appointmentToDelete.getType());
            alert.showAndWait();

        }
        populateAppointmentsView();
    }

    /** This is the onActionDisplayAddAppointment event handler.
     * When the Add button is clicked, the handler switches to the Add Appointment screen.
     * @param event ActionEvent generated by the user clicking the Add button.*/
    @FXML
    public void onActionDisplayAddAppointment(ActionEvent event) throws IOException {
        switchScreen("/ViewController/AddAppointment.fxml", event);
    }

    /** This is the onActionDisplayMenu event handler.
     * When the Menu button is clicked, the handler switches to the Menu screen.
     * @param event ActionEvent generated by the user clicking the Menu button.*/
    @FXML
    public void onActionDisplayMenu(ActionEvent event) throws IOException {
        switchScreen("/ViewController/Menu.fxml", event);
    }

    /** This is the onActionDisplayUpdateAppointment event handler.
     * When the Update button is clicked, the handler checks whether there is an Appointment selected, then switches to the Update Appointment Screen and populates the form with the appointment's data.
     * @param event ActionEvent generated by the user clicking the Update button.*/
    @FXML
    public void onActionDisplayUpdateAppointment(ActionEvent event) throws IOException, SQLException {

        if(appointmentsView.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select an appointment to update.");
            alert.showAndWait();
            return;
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ViewController/UpdateAppointment.fxml"));
        loader.load();

        UpdateAppointmentController UAController = loader.getController();
        UAController.sendAppointment(appointmentsView.getSelectionModel().getSelectedItem());

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**This is the onActionExitApplication event handler. When the Exit button is clicked, this handler exits the java application.
     * @param event ActionEvent is generated when the user clicks the exit button in the Appointments screen*/
    @FXML
    public void onActionExitApplication(ActionEvent event) {
        System.exit(0);
    }

    /**This is the initialize method. This method initializes the controller.
     * @param rb resource bundle
     * @param url URL*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        populateAppointmentsView();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        this.contactCol.setCellValueFactory((cellData) -> {
            return new ReadOnlyObjectWrapper((cellData.getValue()).getContact().getContactName());
        });
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        this.startCol.setCellValueFactory((cellData) -> {
            return new ReadOnlyObjectWrapper((cellData.getValue()).getStart().format(formatter));
        });
        this.endCol.setCellValueFactory((cellData) -> {
            return new ReadOnlyObjectWrapper((cellData.getValue()).getEnd().format(formatter));
        });
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        viewAllAppointmentsRBtn.setSelected(true);
    }

}
