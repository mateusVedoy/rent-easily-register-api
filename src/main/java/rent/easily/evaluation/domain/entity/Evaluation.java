package rent.easily.evaluation.domain.entity;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import rent.easily.shared.domain.Entity;

@Getter
@NoArgsConstructor
public class Evaluation extends Entity {

    private Long id;
    private Long evaluatorId;
    private Long evaluatedId;
    private String evaluation;
    private float score;
    private Long advertisementId;
    private LocalDateTime postingDateTime;

    public Evaluation(Long id, Long evaluatorId, Long evaluatedId, String evaluation, float score,
            Long advertisementId, LocalDateTime postingDateTime) {
        this.id = id;
        this.evaluatorId = evaluatorId;
        this.evaluatedId = evaluatedId;
        this.evaluation = evaluation;
        this.score = score;
        this.advertisementId = advertisementId;
        this.postingDateTime = postingDateTime;
    }

    public Evaluation(Long id, Long evaluatorId, Long evaluatedId, String evaluation, float score,
            Long advertisementId) {
        this.id = id;
        this.evaluatorId = evaluatorId;
        this.evaluatedId = evaluatedId;
        this.evaluation = evaluation;
        this.score = score;
        this.advertisementId = advertisementId;
        this.postingDateTime = setDateTime();
    }

    @Override
    public void validate() {
        // TODO: implements this validation
        throw new UnsupportedOperationException("Unimplemented method 'validate'");
    }
}
