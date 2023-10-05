package rent.easily.evaluation.infrastructure.database;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import rent.easily.evaluation.domain.entity.Evaluation;
import rent.easily.shared.infrastructure.Repository;

@ApplicationScoped
public class EvaluationRepository extends Repository<Evaluation, EvaluationModel> {

    @Override
    protected EvaluationModel convertToModel(Evaluation entity) {
        return new EvaluationModel(
                entity.getId(),
                entity.getEvaluatorId(),
                entity.getEvaluatedId(),
                entity.getEvaluation(),
                entity.getScore(),
                entity.getAdvertisementId(),
                entity.getPostingDateTime());
    }

    @Override
    protected List<Evaluation> convertToDomainList(List<EvaluationModel> model) {
        List<Evaluation> evaluations = new ArrayList<>();
        for (EvaluationModel mdl : model) {
            evaluations.add(
                    new Evaluation(
                            mdl.getId(),
                            mdl.getEvaluatorId(),
                            mdl.getEvaluatedId(),
                            mdl.getEvaluation(),
                            mdl.getScore(),
                            mdl.getAdvertisementId(),
                            mdl.getPostingDateTime()));
        }
        return evaluations;
    }
}
