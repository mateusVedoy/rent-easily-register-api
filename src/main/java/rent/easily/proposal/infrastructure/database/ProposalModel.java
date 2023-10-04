package rent.easily.proposal.infrastructure.database;

import jakarta.persistence.Table;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "proposal")
@Getter
@Setter
@NoArgsConstructor
public class ProposalModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pps_code")
    private Long id;
    @Column(name = "pps_information")
    private String information;
    @Column(name = "usr_code")
    private Long userId;
    @Column(name = "adv_code")
    private Long advertisementId;
    @Column(name = "pps_amount")
    private double amount;
    @Column(name = "pps_dateTime")
    private LocalDateTime dateTime;

    public ProposalModel(String information, Long userId, Long advertisementId, double amount, LocalDateTime dateTime) {
        this.information = information;
        this.userId = userId;
        this.advertisementId = advertisementId;
        this.amount = amount;
        this.dateTime = dateTime;
    }

    public ProposalModel(Long id, String information, Long userId, Long advertisementId, double amount, LocalDateTime dateTime) {
        this.id = id;
        this.information = information;
        this.userId = userId;
        this.advertisementId = advertisementId;
        this.amount = amount;
        this.dateTime = dateTime;
    }
}
