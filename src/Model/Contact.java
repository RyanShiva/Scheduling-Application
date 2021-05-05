package Model;

/** This class supplies the logic for a Contact object.*/
public class Contact {

    private int contactId;
    private String contactName;
    private String email;

    /** This is the Contact Constructor. This constructor creates a new Contact object with parameters for the object attributes.
     * @param contactId contact ID
     * @param contactName contact name
     * @param email contact email*/
    public Contact(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }

    /** This is the contactId Getter. This method returns the Contact ID.
     * @return Contact ID*/
    public int getContactId() {
        return contactId;
    }

    /** This is the contactId Setter. This method sets the Contact ID.
     * @param contactId Contact ID*/
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /** This is the contactName Getter. This method returns the Contact Name.
     * @return Contact Name*/
    public String getContactName() {
        return contactName;
    }

    /** This is the contactName Setter. This method sets the Contact Name.
     * @param contactName Contact Name*/
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /** This is the email Getter. This method returns the Contact Email.
     * @return Contact Email*/
    public String getEmail() {
        return email;
    }

    /** This is the email Setter. This method sets the Contact Email.
     * @param email Contact Email*/
    public void setEmail(String email) {
        this.email = email;
    }

    /** This method overrides the default toString() method for Contact objects.
     * @return Contact string format*/
    @Override
    public String toString() {
        return("# " + contactId + " " + contactName);
    }
}
