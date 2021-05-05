package Model;

import java.time.LocalDateTime;

/** This class supplies the logic for a Customer object.*/
public class Customer {

    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private String countryName;
    private String divisionName;

    /** This is a Customer Constructor. This constructor creates a new Customer object with parameters for the object attributes.
     * @param customerId customer ID
     * @param customerName customer name
     * @param address customer address
     * @param postalCode customer postal code
     * @param phone customer phone number
     * @param createDate customer create date/time
     * @param createdBy user who created the customer
     * @param lastUpdate customer last update date/time
     * @param lastUpdatedBy user who last updated the customer
     * @param countryName customer country
     * @param divisionName customer division*/
    public Customer(int customerId, String customerName, String address, String postalCode, String phone, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy, String countryName, String divisionName) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryName = countryName;
        this.divisionName = divisionName;
    }

    /** This is a Customer Constructor. This constructor creates a new Customer object with default values for the object attributes.*/
    public Customer() {
    }

    /** This is the customerId Getter. This method returns the Customer ID.
     * @return Customer ID*/
    public int getCustomerId() {
        return customerId;
    }

    /** This is the customerId Setter. This method sets the Customer ID.
     * @param customerId Customer ID*/
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /** This is the customerName Getter. This method returns the Customer Name.
     * @return Customer Name*/
    public String getCustomerName() {
        return customerName;
    }

    /** This is the customerName Setter. This method sets the Customer Name.
     * @param customerName Customer Name*/
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /** This is the address Getter. This method returns the Customer Address.
     * @return Customer Address*/
    public String getAddress() {
        return address;
    }

    /** This is the address Setter. This method sets the Customer Address.
     * @param address Customer Address*/
    public void setAddress(String address) {
        this.address = address;
    }

    /** This is the postalCode Getter. This method returns the Customer Postal Code.
     * @return Customer Postal Code*/
    public String getPostalCode() {
        return postalCode;
    }

    /** This is the postalCode Setter. This method sets the Customer Postal Code.
     * @param postalCode Customer Postal Code*/
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /** This is the phone Getter. This method returns the Customer Phone Number.
     * @return Customer Phone Number*/
    public String getPhone() {
        return phone;
    }

    /** This is the phone Setter. This method sets the Customer Phone Number.
     * @param phone Customer Phone Number*/
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /** This is the createDate Getter. This method returns the Customer create date/time.
     * @return Customer create date/time*/
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /** This is the createDate Setter. This method sets the Customer create date/time.
     * @param createDate Customer create date/time*/
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /** This is the createdBy Getter. This method returns the Customer creator.
     * @return Customer creator*/
    public String getCreatedBy() {
        return createdBy;
    }

    /** This is the createdBy Setter. This method sets the Customer creator.
     * @param createdBy Customer creator*/
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /** This is the lastUpdate Getter. This method returns the Customer last update date/time.
     * @return Customer last update date/time*/
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /** This is the lastUpdate Setter. This method sets the Customer last update date/time.
     * @param lastUpdate Customer last update date/time*/
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /** This is the lastUpdatedBy Getter. This method returns the Customer last updater.
     * @return Customer last updater*/
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /** This is the lastUpdatedBy Setter. This method sets the Customer last updater.
     * @param lastUpdatedBy Customer last updater*/
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /** This is the countryName Getter. This method returns the Customer country.
     * @return Customer Customer country*/
    public String getCountryName() {
        return countryName;
    }

    /** This is the countryName Setter. This method sets the Customer country.
     * @param countryName Customer country*/
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /** This is the divisionName Getter. This method returns the Customer division.
     * @return Customer division*/
    public String getDivisionName() {
        return divisionName;
    }

    /** This is the divisionName Setter. This method sets the Customer division.
     * @param divisionName Customer division*/
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /** This method overrides the default toString() method for Customer objects.
     * @return Customer string format*/
    @Override
    public String toString() {
        return("# " + customerId + " " + customerName);
    }
}
