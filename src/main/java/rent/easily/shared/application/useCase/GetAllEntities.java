package rent.easily.shared.application.useCase;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.Dependent;
import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.response.ResponseError;
import rent.easily.shared.application.response.ResponseSuccess;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.IConvert;
import rent.easily.shared.infrastructure.Repository;

@Dependent
public class GetAllEntities<T1, T2, T3> {
    public APIResponse execute(Repository<T2, T3> repository, IConvert<T1, T2> convertToDomain,
            IConvert<T2, T1> convertToDTO) {
        try {
            List<T2> entities = repository.getAll();
            List<T1> list = new ArrayList<>();
            for (T2 entity : entities) {
                list.add(convertToDTO.convert(entity));
            }
            return buildList(list);
        } catch (ValidationError validationException) {
            return new ResponseError(400, "Something went wrong. Consult errors.", validationException);
        } catch (Exception exception) {
            return new ResponseError(400, "Something went wrong. Consult errors.", exception);
        }
    }

    private APIResponse buildList(List<T1> list) {
        if(!isThereAnyValueToBeSolved(list))
            return new ResponseSuccess<>(200, "There's no data to be returned.");
        
        return new ResponseSuccess<>(200, "Data fetched bellow.", list);
    }

    private boolean isThereAnyValueToBeSolved(List<T1> list) {
        return !list.isEmpty();
    }
}
