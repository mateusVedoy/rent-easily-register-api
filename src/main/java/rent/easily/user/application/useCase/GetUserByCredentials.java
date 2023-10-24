package rent.easily.user.application.useCase;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.response.ResponseError;
import rent.easily.shared.application.response.ResponseSuccess;
import rent.easily.shared.domain.exception.BusinessException;
import rent.easily.shared.domain.port.IConvert;
import rent.easily.user.application.dto.UserDTO;
import rent.easily.user.domain.entity.User;
import rent.easily.user.infrastructure.database.UserRepository;

@ApplicationScoped
public class GetUserByCredentials {

    @Inject
    UserRepository repository;
    @Inject
    IConvert<User, UserDTO> convertToDTO;

    public APIResponse execute(UserDTO dto) {
        try {

            User user = repository.getUserByCredentials(dto.getCredentials().getMail(),
                    dto.getCredentials().getPassword());

            if (user != null)
                return new ResponseSuccess<>(200, "OK", List.of(convertToDTO.convert(user)));

            return new ResponseError(400, "NOK",
                    new BusinessException("There's no user for given mail and pass", "domain.User"));

        } catch (Exception e) {
            return new ResponseError(400, "NOK", e);
        }
    }
}
