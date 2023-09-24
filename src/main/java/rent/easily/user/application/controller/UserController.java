package rent.easily.user.application.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.useCase.CreateEntity;
import rent.easily.shared.application.useCase.GetAllEntities;
import rent.easily.shared.domain.port.IConvert;
import rent.easily.user.application.dto.UserDTO;
import rent.easily.user.domain.User;
import rent.easily.user.infrastructure.database.UserModel;
import rent.easily.user.infrastructure.database.UserRepository;

@Path("/user")
@Transactional
public class UserController {

    @Inject
    CreateEntity<UserDTO, User, UserModel> createEntity;
    @Inject
    UserRepository repository;
    @Inject
    IConvert<UserDTO, User> convertToDomain;
    @Inject
    IConvert<User, UserDTO> convertToDTO;
    @Inject
    GetAllEntities<UserDTO, User, UserModel> getAllEntities;
    
    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public APIResponse create(UserDTO dto) {
        return createEntity.execute(dto, repository, convertToDomain, convertToDTO);
    }

    @GET
    @Path("/find/all")
    @Produces(MediaType.APPLICATION_JSON)
    public APIResponse getAll() {
        return getAllEntities.execute(repository, convertToDomain, convertToDTO);
    }
}
