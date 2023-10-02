package rent.easily.shared.application.useCase;

import jakarta.enterprise.context.Dependent;
import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.response.ResponseError;
import rent.easily.shared.application.response.ResponseSuccess;
import rent.easily.shared.application.response.StatusMessage;
import rent.easily.shared.application.service.EntityNormalizer;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.infrastructure.Repository;

//t1 - dto, t2 - entity, t3 - model
@Dependent
public class UpdateEntity<T1, T2, T3> {

    public APIResponse execute(
            Long id,
            T1 dto,
            Repository<T2, T3> repository,
            EntityNormalizer<T1, T3> normalizer) {
        try {
            T3 model = normalizer.normalize(dto, id);
            repository.edit(model);
            return new ResponseSuccess<>(200, StatusMessage.UPDATE.getValue());
        } catch (ValidationError validationException) {
            return new ResponseError(400, StatusMessage.ERROR.getValue(), validationException.getErrors());
        } catch (Exception exception) {
            return new ResponseError(400, StatusMessage.ERROR.getValue(), exception);
        }
    }
}
