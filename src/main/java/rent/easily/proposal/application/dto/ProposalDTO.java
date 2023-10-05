package rent.easily.proposal.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import rent.easily.shared.application.dto.DateTimeDTO;

@Getter
@NoArgsConstructor
public class ProposalDTO {
    private Long id;
    private Long advertisementId;
    private Long userId;
    private DateTimeDTO proposedAt;
    private double amount;
    private String information;

    public ProposalDTO(
        Long advertisementId,
        Long userId,
        double amount,
        String information,
        DateTimeDTO proposedAt
    ) {
        this.advertisementId = advertisementId;
        this.userId = userId;
        this.amount = amount;
        this.information = information;
        this.proposedAt = proposedAt;
    }

    public ProposalDTO(
        Long id,
        Long advertisementId,
        Long userId,
        double amount,
        String information,
        DateTimeDTO proposedAt
    ) {
        this.id = id;
        this.advertisementId = advertisementId;
        this.userId = userId;
        this.amount = amount;
        this.information = information;
        this.proposedAt = proposedAt;
    }
}