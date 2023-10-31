package rent.easily.property.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PropertyAddressDTO {
    private String country;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private int streetNumber;
    private String zipCode;

    public PropertyAddressDTO(
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
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
    }

    public PropertyAddressDTO(
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
        this.streetNumber = setStreetType(streetNumber);
        this.zipCode = zipCode;
    }

    private int setStreetType(String streetNumber) {
        return streetNumber.equals("SN") ? 0 : Integer.parseInt(streetNumber);
    }
}
