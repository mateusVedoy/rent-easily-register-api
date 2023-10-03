package rent.easily.evaluation.application.specification;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import rent.easily.evaluation.domain.Evaluation;
import rent.easily.evaluation.infrastructure.database.EvaluationRepository;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.ICriteria;

@ApplicationScoped
public class CreateEvaluationSpec implements ICriteria<Evaluation> {

    @Inject
    EvaluationRepository repository;

    @Override
    public void validate(Evaluation entry) throws ValidationError {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validate'");
    }    
}
