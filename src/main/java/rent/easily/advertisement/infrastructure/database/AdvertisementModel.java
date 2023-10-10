package rent.easily.advertisement.infrastructure.database;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AdvertisementModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "add_code")
    private Long id;
    @Column(name = "add_active")
    private String active;
    @Column(name = "add_rentamount")
    private double rentAmount;
    @Column(name = "add_information")
    private String information;
    @Column(name = "add_postdate")
    private LocalDate postedAt;
    @Column(name = "pro_code")
    private Long propertyId;

    public AdvertisementModel (
        double rentAmount,
        String information,
        LocalDate postedAt,
        Long propertyId
    ) {
        this.active = "1";
        this.rentAmount = rentAmount;
        this.information = information;
        this.propertyId = propertyId;
        this.postedAt = postedAt;
    }

    public AdvertisementModel (
        Long id,
        String active,
        double rentAmount,
        String information,
        LocalDate postedAt,
        Long propertyId
    ) {
        this.id = id;
        this.active = active;
        this.rentAmount = rentAmount;
        this.information = information;
        this.propertyId = propertyId;
        this.postedAt = postedAt;
    }
}
