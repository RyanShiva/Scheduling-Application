package Model;

/** This class supplies the logic for a Country object.*/
public class Country {

    private int countryId;
    private String country;

    /** This is the Country Constructor. This constructor creates a new Country object with parameters for the object attributes.
     * @param countryId country ID
     * @param country country name*/
    public Country(int countryId, String country) {
        this.countryId = countryId;
        this.country = country;
    }

    public Country() {
    }

    /** This is the countryId Getter. This method returns the Country ID.
     * @return Country ID*/
    public int getCountryId() {
        return countryId;
    }

    /** This is the countryId Setter. This method sets the Country ID.
     * @param countryId Country ID*/
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /** This is the country Getter. This method returns the Country Name.
     * @return Country Name*/
    public String getCountry() {
        return country;
    }

    /** This is the country Setter. This method sets the Country Name.
     * @param country Country Name*/
    public void setCountry(String country) {
        this.country = country;
    }

    /** This method overrides the default toString() method for Country objects.
     * @return Country string format*/
    @Override
    public String toString() {
        return(country);
    }
}
