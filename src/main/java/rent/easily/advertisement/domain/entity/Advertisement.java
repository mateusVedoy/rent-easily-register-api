package rent.easily.advertisement.domain.entity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import rent.easily.shared.domain.Entity;

@Getter
@NoArgsConstructor
public class Advertisement extends Entity {
    private Long id;
    private boolean active;
    private double rentAmount;
    private String information;
    private LocalDate postedAt;
    private Long propertyId;

    public Advertisement(
        double rentAmount,
        String information,
        Long propertyId
    ) {
        this.active = true;
        this.rentAmount = rentAmount;
        this.information = information;
        this.propertyId = propertyId;
        this.postedAt = setDate();
        validate();
    }

    public Advertisement(
        Long id,
        String active,
        double rentAmount,
        String information,
        LocalDate postedAt,
        Long propertyId
    ) {
        this.id = id;
        this.active = setActive(active);
        this.rentAmount = rentAmount;
        this.information = information;
        this.propertyId = propertyId;
        this.postedAt = postedAt;
        validate();
    }

    private boolean setActive(String active) {
        return active.equals("1") ? true : false;
    }

    @Override
    public void validate() {
        if(isNull(rentAmount))
            addError("Rent amount in Advertisement is mandatory.", "domain.Advertisement.rentAmount");
        if(isNull(propertyId))
            addError("Advertisement must contain a property", "domain.Advertisement.propertyId");
        if(isNull(postedAt))
            addError("Advertisement must contain a post date", "domain.Advertisement.postedAt");
    }

    private LocalDate setDate() {
        return  new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
