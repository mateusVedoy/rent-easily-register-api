package rent.easily.evaluation.application.converter;

import jakarta.enterprise.context.ApplicationScoped;
import rent.easily.evaluation.application.dto.EvaluationDTO;
import rent.easily.evaluation.domain.Evaluation;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.IConvert;

@ApplicationScoped
public class ConvertToDomain implements IConvert<EvaluationDTO, Evaluation>{

    @Override
    public Evaluation convert(EvaluationDTO entry) throws ValidationError {
        Evaluation evaluation = new Evaluation(
                entry.getId(),
                entry.getEvaluatorId(),
                entry.getEvaluatedId(),
                entry.getEvaluation(),
                entry.getScore(),
                entry.getAdvertisementId());
        if(evaluation.isValid())
            return evaluation;
        throw new ValidationError(evaluation.getErrors());
    }
    
}
