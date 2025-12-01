package rent.easily.advertisement.domain.entity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import lombok.Getter;
import rent.easily.shared.domain.Entity;

@Getter
public class Advertisement extends Entity {
    private Long advertisementId;
    private final boolean active;
    private final double rentAmount;
    private final String information;
    private final LocalDate postedAt;
    private final Long propertyId;

    public Advertisement(
        final double rentAmount,
        final String information,
        final Long propertyId
    ) {
        super();
        this.active = true;
        this.rentAmount = rentAmount;
        this.information = information;
        this.propertyId = propertyId;
        this.postedAt = getDate();
    }

    public Advertisement(
        final Long advertisementId,
        final String active,
        final double rentAmount,
        final String information,
        final LocalDate postedAt,
        final Long propertyId
    ) {
        super();
        this.advertisementId = advertisementId;
        this.active = getActiveFromString(active);
        this.rentAmount = rentAmount;
        this.information = information;
        this.propertyId = propertyId;
        this.postedAt = postedAt;
    }

    @Override
    public final void validate() {
        if(isNull(rentAmount)){
            addError("Rent amount in Advertisement is mandatory.", "domain.Advertisement.rentAmount");
        }
        if(isNull(propertyId)){
            addError("Advertisement must contain a property", "domain.Advertisement.propertyId");
        }
        if(isNull(postedAt)){
            addError("Advertisement must contain a post date", "domain.Advertisement.postedAt");
        }
    }

    private boolean getActiveFromString(final String active) {
        return "1".equals(active);
    }

    private LocalDate getDate() {
        return  new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
