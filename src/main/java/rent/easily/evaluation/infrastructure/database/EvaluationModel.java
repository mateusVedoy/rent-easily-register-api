package rent.easily.evaluation.infrastructure.database;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "evaluation")
@Getter
@NoArgsConstructor
public class EvaluationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eva_code")
    private Long id;
    @Column(name = "eva_evaluatorId")
    private Long evaluatorId;
    @Column(name = "eva_evaluatedId")
    private Long evaluatedId;
    @Column(name = "eva_evaluation")
    private String evaluation;
    @Column(name = "eva_score")
    private float score;
    @Column(name = "eva_advertisementId")
    private Long advertisementId;
    @Column(name = "eva_postingDateTime")
    private LocalDateTime postingDateTime;

    public EvaluationModel(Long id, Long evaluatorId, Long evaluatedId, String evaluation, float score,
            Long advertisementId, LocalDateTime postingDateTime) {
        this.id = id;
        this.evaluatorId = evaluatorId;
        this.evaluatedId = evaluatedId;
        this.evaluation = evaluation;
        this.score = score;
        this.advertisementId = advertisementId;
        this.postingDateTime = postingDateTime;
    }

    public EvaluationModel(Long evaluatorId, Long evaluatedId, String evaluation, float score,
            Long advertisementId, LocalDateTime postingDateTime) {
        this.evaluatorId = evaluatorId;
        this.evaluatedId = evaluatedId;
        this.evaluation = evaluation;
        this.score = score;
        this.advertisementId = advertisementId;
        this.postingDateTime = postingDateTime;
    }
}
