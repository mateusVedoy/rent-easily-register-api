package rent.easily.evaluation.application.useCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import rent.easily.evaluation.application.dto.EvaluationDTO;
import rent.easily.evaluation.domain.entity.Evaluation;
import rent.easily.evaluation.infrastructure.database.EvaluationModel;
import rent.easily.evaluation.infrastructure.database.EvaluationRepository;
import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.useCase.CreateEntity;
import rent.easily.shared.domain.port.IConvert;
import rent.easily.shared.domain.port.ICriteria;

@ApplicationScoped
public class CreateEvaluation {

    @Inject
    CreateEntity<EvaluationDTO, Evaluation, EvaluationModel> createEntity;
    @Inject
    EvaluationRepository repository;
    @Inject
    IConvert<EvaluationDTO, Evaluation> convertToDomain;
    @Inject
    IConvert<Evaluation, EvaluationDTO> convertToDTO;
    @Inject
    ICriteria<Evaluation> spec;

    public APIResponse execute(EvaluationDTO dto) {
        return createEntity.execute(dto, repository, convertToDomain, convertToDTO, spec);
    }
}
