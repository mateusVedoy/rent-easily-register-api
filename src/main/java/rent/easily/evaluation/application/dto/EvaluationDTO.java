package rent.easily.evaluation.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EvaluationDTO {
    private Long id;
    private Long evaluatorId;
    private Long evaluatedId;
    private String evaluation;
    private float score;
    private Long advertisementId;
    private DateTimeDTO postingDateTime;

    public EvaluationDTO(Long id, Long evaluatorId, Long evaluatedId, String evaluation, float score,
            Long advertisementId, DateTimeDTO postingDateTime) {
        this.id = id;
        this.evaluatorId = evaluatorId;
        this.evaluatedId = evaluatedId;
        this.evaluation = evaluation;
        this.score = score;
        this.advertisementId = advertisementId;
        this.postingDateTime = postingDateTime;
    }

    public EvaluationDTO(Long evaluatorId, Long evaluatedId, String evaluation, float score,
            Long advertisementId, DateTimeDTO postingDateTime) {
        this.evaluatorId = evaluatorId;
        this.evaluatedId = evaluatedId;
        this.evaluation = evaluation;
        this.score = score;
        this.advertisementId = advertisementId;
        this.postingDateTime = postingDateTime;
    }
}
