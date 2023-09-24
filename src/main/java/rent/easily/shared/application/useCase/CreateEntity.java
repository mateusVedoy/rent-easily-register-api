package rent.easily.shared.application.useCase;

import jakarta.enterprise.context.Dependent;
import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.response.ResponseError;
import rent.easily.shared.application.response.ResponseSuccess;
import rent.easily.shared.application.response.StatusMessage;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.IConvert;
import rent.easily.shared.infrastructure.Repository;

// t1 - dto, t2 - entity, t3 - model
@Dependent
public class CreateEntity<T1, T2, T3> {


    public APIResponse execute(
        T1 dto, 
        Repository<T2, T3> repository,
        IConvert<T1, T2> convertToDomain,
        IConvert<T2, T1> convertToDTO) {
        try {
            T2 entity = convertToDomain.convert(dto);
            repository.save(entity);
            return new ResponseSuccess<>(201, StatusMessage.CREATED.getValue());
        } catch (ValidationError validationException) {
            return new ResponseError(400, StatusMessage.ERROR.getValue(), validationException);
        } catch (Exception exception) {
            return new ResponseError(400, StatusMessage.ERROR.getValue(), exception);
        }
    }
}
