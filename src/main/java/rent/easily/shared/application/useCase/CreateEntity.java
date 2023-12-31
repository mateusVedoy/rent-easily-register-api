package rent.easily.shared.application.useCase;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.Dependent;
import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.response.ResponseError;
import rent.easily.shared.application.response.ResponseSuccess;
import rent.easily.shared.application.response.StatusMessage;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.IConvert;
import rent.easily.shared.domain.port.ICriteria;
import rent.easily.shared.infrastructure.Repository;

// t1 - dto, t2 - entity, t3 - model
@Dependent
public class CreateEntity<T1, T2, T3> {

    public APIResponse execute(
            T1 dto,
            Repository<T2, T3> repository,
            IConvert<T1, T2> convertToDomain,
            IConvert<T2, T1> convertToDTO,
            ICriteria<T2> specification) {
        try {
            T2 entity = convertToDomain.convert(dto);
            if (hasSpecification(specification))
                specification.validate(entity);
            List<T2> results = repository.save(entity);
            List<T1> dtos = new ArrayList<>();
            for (T2 result : results) {
                dtos.add(convertToDTO.convert(result));
            }
            return new ResponseSuccess<>(201, StatusMessage.CREATED.getValue(), dtos);
        } catch (ValidationError validationException) {
            return new ResponseError(400, StatusMessage.ERROR.getValue(), validationException.getErrors());
        } catch (Exception exception) {
            return new ResponseError(400, StatusMessage.ERROR.getValue(), exception);
        }
    }

    private boolean hasSpecification(ICriteria<T2> spec) {
        return spec != null;
    }
}
