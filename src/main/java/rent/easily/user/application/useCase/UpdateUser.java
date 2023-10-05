package rent.easily.user.application.useCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.useCase.UpdateEntity;
import rent.easily.user.application.dto.UserDTO;
import rent.easily.user.application.service.UserNormalize;
import rent.easily.user.domain.entity.User;
import rent.easily.user.infrastructure.database.UserModel;
import rent.easily.user.infrastructure.database.UserRepository;

@ApplicationScoped
public class UpdateUser {

    @Inject
    UserRepository repository;
    @Inject
    UpdateEntity<UserDTO, User, UserModel> updateEntity;
    @Inject
    UserNormalize normalizer;

    public APIResponse execute(UserDTO dto, Long id) {
       return updateEntity.execute(id, dto, repository, normalizer);
    }   
}
