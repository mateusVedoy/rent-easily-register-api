package rent.easily.shared.application.useCase;

import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.response.ResponseError;
import rent.easily.shared.application.response.ResponseSuccess;
import rent.easily.shared.application.response.StatusMessage;
import rent.easily.shared.domain.port.IConvert;
import rent.easily.shared.infrastructure.Repository;

public class DeleteEntityById<T1, T2, T3> {
    public APIResponse execute(Long id, Repository<T2, T3> repository, IConvert<T1, T2> convertToDomain,
            IConvert<T2, T1> convertToDTO) {
        try {
            if(repository.deleteById(id))
                return new ResponseSuccess<>(200, "Data deleted successfully.");
            return new ResponseError(400, "Data cannot be deleted.", "shared.application.useCase.DeleteEntityById");
        } catch (Exception exception) {
            return new ResponseError(400, StatusMessage.ERROR.getValue(), exception);
        }
    }
}
