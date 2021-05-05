package Model;

/** This class supplies the logic for a FirstLevelDivision object.*/
public class FirstLevelDivision {

    private int divisionId;
    private String division;
    private int countryId;

    /** This is the FirstLevelDivision Constructor. This constructor creates a new FirstLevelDivision object with parameters for the object attributes.
     * @param divisionId division ID
     * @param division division name
     * @param countryId country ID*/
    public FirstLevelDivision(int divisionId, String division, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }

    public FirstLevelDivision() {
    }

    /** This is the divisionId Getter. This method returns the First Level Division ID.
     * @return First Level Division ID*/
    public int getDivisionId() {
        return divisionId;
    }

    /** This is the divisionId Setter. This method sets the First Level Division ID.
     * @param divisionId First Level Division ID*/
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /** This is the division Getter. This method returns the First Level Division Name.
     * @return First Level Division Name*/
    public String getDivision() {
        return division;
    }

    /** This is the division Setter. This method sets the First Level Division Name.
     * @param division First Level Division Name*/
    public void setDivision(String division) {
        this.division = division;
    }

    /** This is the countryId Getter. This method returns the Country ID of the First Level Division.
     * @return Country ID of the First Level Division*/
    public int getCountryId() {
        return countryId;
    }

    /** This is the countryId Setter. This method sets the Country ID of the First Level Division.
     * @param countryId Country ID of the First Level Division*/
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /** This method overrides the default toString() method for FirstLevelDivision objects.
     * @return FirstLevelDivision string format*/
    @Override
    public String toString() {
        return(division);
    }
}
