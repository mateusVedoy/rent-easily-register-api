package rent.easily.shared.application.useCase;

import java.util.List;

import jakarta.enterprise.context.Dependent;
import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.response.ResponseError;
import rent.easily.shared.application.response.ResponseSuccess;
import rent.easily.shared.application.response.StatusMessage;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.IConvert;
import rent.easily.shared.infrastructure.Repository;

@Dependent
public class GetEntityById<T1, T2, T3> {
    public APIResponse execute(Long id, Repository<T2, T3> repository, IConvert<T1, T2> convertToDomain,
            IConvert<T2, T1> convertToDTO) {
        try {
            T2 entity = repository.findPerId(id);
            if(isNull(entity))
                return new ResponseSuccess<>(200, StatusMessage.EMPTY_SUCCESS.getValue());
            T1 dto = convertToDTO.convert(entity);
            return new ResponseSuccess<T1>(200, StatusMessage.SUCCESS.getValue(), List.of(dto));
        } catch (ValidationError validationException) {
            return new ResponseError(400, StatusMessage.ERROR.getValue(), validationException);
        } catch (Exception exception) {
            return new ResponseError(400, StatusMessage.ERROR.getValue(), exception);
        }
    }

    private boolean isNull(T2 object) {
        return object == null;
    }
}
