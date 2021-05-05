package Model;

import java.time.LocalDateTime;

/** This class supplies the logic for an Appointment object.*/
public class Appointment {

    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private int customerId;
    private int userId;
    private Contact contact;

    /** This is an Appointment Constructor. This constructor creates a new Appointment object with parameters for the object attributes.
     * @param appointmentId appointment ID
     * @param title appointment title
     * @param description appointment description
     * @param location appointment location
     * @param type type of appointment
     * @param start appointment start date/time
     * @param end appointment end date/time
     * @param createDate appointment create date/time
     * @param createdBy user who created the appointment
     * @param lastUpdate appointment last update date/time
     * @param lastUpdatedBy user who last updated the appointment
     * @param customerId customer ID
     * @param userId user ID
     * @param contact contact associated with the appointment*/
    public Appointment(int appointmentId, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy, int customerId, int userId, Contact contact) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerId = customerId;
        this.userId = userId;
        this.contact = contact;
    }

/** This is an Appointment Constructor. This constructor creates a new Appointment object with default values for the object attributes.*/
    public Appointment() {
    }

    /** This is the appointmentId Getter. This method returns the Appointment ID.
     * @return Appointment ID*/
    public int getAppointmentId() {
        return appointmentId;
    }

    /** This is the appointmentId Setter. This method sets the Appointment ID.
     * @param appointmentId Appointment ID*/
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /** This is the title Getter. This method returns the Appointment Title.
     * @return Appointment Title*/
    public String getTitle() {
        return title;
    }

    /** This is the title Setter. This method sets the Appointment Title.
     * @param title Appointment Title*/
    public void setTitle(String title) {
        this.title = title;
    }

    /** This is the description Getter. This method returns the Appointment Description.
     * @return Appointment Description*/
    public String getDescription() {
        return description;
    }

    /** This is the description Setter. This method sets the Appointment Description.
     * @param description Appointment Description*/
    public void setDescription(String description) {
        this.description = description;
    }

    /** This is the location Getter. This method returns the Appointment Location.
     * @return Appointment Location*/
    public String getLocation() {
        return location;
    }

    /** This is the location Setter. This method sets the Appointment Location.
     * @param location Appointment Location*/
    public void setLocation(String location) {
        this.location = location;
    }

    /** This is the type Getter. This method returns the Appointment Type.
     * @return Appointment Type*/
    public String getType() {
        return type;
    }

    /** This is the type Setter. This method sets the Appointment Type.
     * @param type Appointment Type*/
    public void setType(String type) {
        this.type = type;
    }

    /** This is the start Getter. This method returns the Appointment Start date/time.
     * @return Appointment Start date/time*/
    public LocalDateTime getStart() {
        return start;
    }

    /** This is the start Setter. This method sets the Appointment Start date/time.
     * @param start Appointment Start date/time*/
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /** This is the end Getter. This method returns the Appointment End date/time.
     * @return Appointment End date/time*/
    public LocalDateTime getEnd() {
        return end;
    }

    /** This is the end Setter. This method sets the Appointment End date/time.
     * @param end Appointment End date/time*/
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /** This is the createDate Getter. This method returns the Appointment Create date/time.
     * @return Appointment Create date/time*/
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /** This is the createDate Setter. This method sets the Appointment Create date/time.
     * @param createDate Appointment Create date/time*/
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /** This is the createdBy Getter. This method returns the Appointment creator.
     * @return Appointment creator*/
    public String getCreatedBy() {
        return createdBy;
    }

    /** This is the createdBy Setter. This method sets the Appointment creator.
     * @param createdBy Appointment creator*/
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /** This is the lastUpdate Getter. This method returns the Appointment Last Update date/time.
     * @return Appointment Last Update date/time*/
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /** This is the lastUpdate Setter. This method sets the Appointment Last Update date/time.
     * @param lastUpdate Appointment Last Update date/time*/
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /** This is the lastUpdatedBy Getter. This method returns the last Appointment updater.
     * @return last Appointment updater*/
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /** This is the lastUpdatedBy Setter. This method sets the last Appointment updater.
     * @param lastUpdatedBy last Appointment updater*/
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /** This is the customerId Getter. This method returns the ID of the Customer associated with the Appointment.
     * @return ID of the Customer associated with the Appointment*/
    public int getCustomerId() {
        return customerId;
    }

    /** This is the customerId Setter. This method sets the ID of the Customer associated with the Appointment.
     * @param customerId ID of the Customer associated with the Appointment*/
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /** This is the userId Getter. This method returns the ID of the User associated with the Appointment.
     * @return ID of the User associated with the Appointment*/
    public int getUserId() {
        return userId;
    }

    /** This is the userId Setter. This method sets the ID of the User associated with the Appointment.
     * @param userId ID of the User associated with the Appointment*/
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /** This is the contact Getter. This method returns the Contact associated with the Appointment.
     * @return Contact associated with the Appointment*/
    public Contact getContact() {
        return contact;
    }

    /** This is the contact Setter. This method sets the Contact associated with the Appointment.
     * @param contact Contact associated with the Appointment*/
    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
