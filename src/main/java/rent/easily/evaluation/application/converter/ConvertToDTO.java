package rent.easily.evaluation.application.converter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.enterprise.context.ApplicationScoped;
import rent.easily.evaluation.application.dto.EvaluationDTO;
import rent.easily.evaluation.domain.entity.Evaluation;
import rent.easily.shared.application.dto.DateTimeDTO;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.IConvert;

@ApplicationScoped
public class ConvertToDTO implements IConvert<Evaluation, EvaluationDTO> {

    @Override
    public EvaluationDTO convert(Evaluation entry) throws ValidationError {
        DateTimeDTO postingDateTime = new DateTimeDTO(
                resolveDate(entry.getPostingDateTime()),
                resolveTime(entry.getPostingDateTime()));
        return new EvaluationDTO(
                entry.getId(),
                entry.getEvaluatorId(),
                entry.getEvaluatedId(),
                entry.getEvaluation(),
                entry.getScore(),
                entry.getAdvertisementId(),
                postingDateTime);
    }

    private LocalDate resolveDate(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();
    }

    private LocalTime resolveTime(LocalDateTime localDateTime) {
        return localDateTime.toLocalTime();
    }
}
