package rent.easily.proposal.domain.entity;

import java.time.LocalDateTime;

import lombok.Getter;
import rent.easily.shared.domain.Entity;

@Getter
public class Proposal extends Entity {

    private Long id;
    private LocalDateTime dateTime;
    private Long advertisementId;
    private Long userId;
    private double amount;
    private String information;

    public Proposal(Long advertisementId, Long userId, double amount, String information) {
        this.dateTime = setDateTime();
        this.advertisementId = advertisementId;
        this.userId = userId;
        this.amount = amount;
        this.information = information;
        validate();
    }

    public Proposal(Long id, LocalDateTime dateTime, Long advertisementId, Long userId, double amount,
            String information) {
        this.id = id;
        this.dateTime = dateTime;
        this.advertisementId = advertisementId;
        this.userId = userId;
        this.amount = amount;
        this.information = information;
        validate();
    }

    @Override
    public void validate() {
        if (isNull(this.advertisementId))
            addError("Advertisement is mandatory", "domain.Proposal.advertisementId");
        if (isNull(this.userId))
            addError("User is mandatory", "domain.Proposal.userId");
        if (isNull(this.dateTime))
            addError("Proposal date time is mandatory", "domain.Proposal.dateTime");
        if (isNull(this.amount))
            addError("Amount is mandatory", "domain.Proposal.amount");
    }
}
