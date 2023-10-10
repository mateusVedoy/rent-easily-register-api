package rent.easily.advertisement.application.dto;

import java.time.LocalDate;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@ApplicationScoped
public class AdvertisementDTO {
    private Long id;
    private boolean active;
    private double rentAmount;
    private String information;
    private LocalDate postedAt;
    private Long propertyId;

    public AdvertisementDTO(double rentAmount, String information, Long propertyId) {
        this.rentAmount = rentAmount;
        this.information = information;
        this.propertyId = propertyId;
    }

    public AdvertisementDTO(Long id, boolean active, double rentAmount, String information, Long propertyId) {
        this.id = id;
        this.active = active;
        this.rentAmount = rentAmount;
        this.information = information;
        this.propertyId = propertyId;
    }
}
