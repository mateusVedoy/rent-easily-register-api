package rent.easily.user.application.useCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.useCase.CreateEntity;
import rent.easily.shared.domain.port.IConvert;
import rent.easily.shared.domain.port.ICriteria;
import rent.easily.user.application.dto.UserDTO;
import rent.easily.user.domain.User;
import rent.easily.user.infrastructure.database.UserModel;
import rent.easily.user.infrastructure.database.UserRepository;

@ApplicationScoped
public class CreateUser {
    @Inject
    CreateEntity<UserDTO, User, UserModel> createEntity;
    @Inject
    UserRepository repository;
    @Inject
    IConvert<UserDTO, User> convertToDomain;
    @Inject
    IConvert<User, UserDTO> convertToDTO;
    @Inject
    ICriteria<User> spec;

    public APIResponse execute(UserDTO dto) {
        return createEntity.execute(dto, repository, convertToDomain, convertToDTO, spec);
    }
}
