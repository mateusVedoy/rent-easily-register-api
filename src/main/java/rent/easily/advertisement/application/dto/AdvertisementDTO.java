package rent.easily.advertisement.application.dto;

import java.time.LocalDate;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;

@Getter
@ApplicationScoped
public class AdvertisementDTO {
    private Long advertisementId;
    private boolean active;
    final private double rentAmount;
    final private String information;
    private LocalDate postedAt;
    final private Long propertyId;

    public AdvertisementDTO(final double rentAmount, final String information, final Long propertyId) {
        this.rentAmount = rentAmount;
        this.information = information;
        this.propertyId = propertyId;
    }

    public AdvertisementDTO(final Long advertisementId, final boolean active, final double rentAmount, final String information, final Long propertyId) {
        this.advertisementId = advertisementId;
        this.active = active;
        this.rentAmount = rentAmount;
        this.information = information;
        this.propertyId = propertyId;
    }
}
