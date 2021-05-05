package Model;

/** This class supplies the logic for a ReportTuple object. Report tuples appear in the tableviews of the automatically-generated reports.*/
public class ReportTuple {

    private String appointmentAttribute;
    private int appointmentAttributeQuantity;

    /** This is the ReportTuple Constructor. This constructor creates a new ReportTuple object with parameters for the object attributes.
     * @param appointmentAttribute String value of the specific attribute of the appointments to be counted or measured.
     * @param appointmentAttributeQuantity Quantity of the string attribute.*/
    public ReportTuple(String appointmentAttribute, int appointmentAttributeQuantity) {
        this.appointmentAttribute = appointmentAttribute;
        this.appointmentAttributeQuantity = appointmentAttributeQuantity;
    }

    /** This is the appointmentAttribute Getter. This method returns the Appointment attribute string.
     * @return Appointment attribute string*/
    public String getAppointmentAttribute() {
        return appointmentAttribute;
    }

    /** This is the appointmentAttribute Setter. This method sets the Appointment attribute string.
     * @param appointmentAttribute Appointment attribute string*/
    public void setAppointmentAttribute(String appointmentAttribute) {
        this.appointmentAttribute = appointmentAttribute;
    }

    /** This is the appointmentAttributeQuantity Getter. This method returns the Appointment attribute quantity.
     * @return Appointment attribute quantity*/
    public int getAppointmentAttributeQuantity() {
        return appointmentAttributeQuantity;
    }

    /** This is the appointmentAttributeQuantity Setter. This method sets the Appointment attribute quantity.
     * @param appointmentAttributeQuantity Appointment attribute quantity*/
    public void setAppointmentAttributeQuantity(int appointmentAttributeQuantity) {
        this.appointmentAttributeQuantity = appointmentAttributeQuantity;
    }
}
