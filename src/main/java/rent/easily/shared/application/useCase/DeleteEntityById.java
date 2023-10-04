package rent.easily.shared.application.useCase;

import jakarta.enterprise.context.Dependent;
import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.response.ResponseError;
import rent.easily.shared.application.response.ResponseSuccess;
import rent.easily.shared.application.response.StatusMessage;
import rent.easily.shared.infrastructure.Repository;

@Dependent
public class DeleteEntityById<T1, T2> {
    public APIResponse execute(Long id, Repository<T1, T2> repository) {
        try {
            if(repository.deleteById(id))
                return new ResponseSuccess<>(200, "Data deleted successfully.");
            return new ResponseError(400, "Data cannot be deleted. Verify your request information", "shared.application.useCase.DeleteEntityById");
        } catch (Exception exception) {
            return new ResponseError(400, StatusMessage.ERROR.getValue(), exception);
        }
    }
}
