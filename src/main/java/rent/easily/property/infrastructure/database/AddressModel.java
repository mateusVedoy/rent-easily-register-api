package rent.easily.property.infrastructure.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
public class AddressModel {

    @Id
    @Column(name = "adr_zipCode")
    private String zipCode;
    @Column(name = "adr_country")
    private String country;
    @Column(name = "adr_state")
    private String state;
    @Column(name = "adr_city")
    private String city;
    @Column(name = "adr_neighborhood")
    private String neighborhood;
    @Column(name = "adr_street")
    private String street;
    @Column(name = "adr_streetNumber")
    private String streetNumber;

    public AddressModel(
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
    }
}
