package rent.easily.property.domain.entity;

import lombok.Getter;
import rent.easily.shared.domain.Entity;

@Getter
public class Address extends Entity {

    private String country;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private String streetNumber;
    private String zipCode;

    public Address(
            String country,
            String state,
            String city,
            String neighborhood,
            String street,
            int streetNumber,
            String zipCode) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.neighborhood = neighborhood;
        this.street = street;
        this.streetNumber = setStreetNumber(streetNumber);
        this.zipCode = zipCode;
        validate();
    }

    public Address(
            String country,
            String state,
            String city,
            String neighborhood,
            String street,
            String streetNumber,
            String zipCode) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.neighborhood = neighborhood;
        this.street = street;
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
        validate();
    }

    public String toString() {
        return this.street + ", " + this.streetNumber + " - " + this.neighborhood + ", " + this.city + " - " + this.state + ", " + this.zipCode + ", " + this.country;
    }

    private String setStreetNumber(int streetNumber) {
        return (streetNumber <= 0) ? "SN" : String.valueOf(streetNumber);
    }

    @Override
    public void validate() {
        if(isNull(this.country))
            addError("Country is mandatory and cannot be null or empty.", "domain.Address.country");
        if(isNull(this.state))
            addError("State is mandatory and cannot be null or empty.", "domain.Address.state");
        if(isNull(this.city))
            addError("City is mandatory and cannot be null or empty.", "domain.Address.city");
        if(isNull(this.neighborhood))
            addError("Neighborhood is mandatory and cannot be null or empty.", "domain.Address.neighborhood");
        if(isNull(this.street))
            addError("Neighborhood is mandatory and cannot be null or empty.", "domain.Address.neighborhood");
        if(isNull(this.zipCode))
            addError("ZipCode is mandatory and cannot be null or empty.", "domain.Address.zipCode");
    }

}
