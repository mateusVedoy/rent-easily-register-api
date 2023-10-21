package rent.easily.user.application.useCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.response.ResponseError;
import rent.easily.shared.application.response.ResponseSuccess;
import rent.easily.shared.domain.exception.BusinessException;
import rent.easily.user.application.dto.UserDTO;
import rent.easily.user.infrastructure.database.UserRepository;

@ApplicationScoped
public class GetUserByCredentials {
    
    @Inject
    UserRepository repository;

    public APIResponse execute(UserDTO dto) {
        boolean isThereUser = repository.existsUserByCredentials(dto.getCredentials().getMail(), dto.getCredentials().getPassword()) ;

        if(isThereUser)
            return new ResponseSuccess<>(200, "OK");

        return new ResponseError(400, "NOK", new BusinessException("There's no user for given mail and pass", "domain.User"));
    }
}
