package rent.easily.user.application.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.useCase.GetAllEntities;
import rent.easily.shared.application.useCase.GetEntityById;
import rent.easily.shared.domain.port.IConvert;
import rent.easily.user.application.dto.UserDTO;
import rent.easily.user.application.useCase.CreateUser;
import rent.easily.user.application.useCase.UpdateUser;
import rent.easily.user.domain.entity.User;
import rent.easily.user.infrastructure.database.UserModel;
import rent.easily.user.infrastructure.database.UserRepository;

@Path("/user")
@Transactional
public class UserController {

    @Inject
    CreateUser createUser;
    @Inject
    UserRepository repository;
    @Inject
    IConvert<UserDTO, User> convertToDomain;
    @Inject
    IConvert<User, UserDTO> convertToDTO;
    @Inject
    GetAllEntities<UserDTO, User, UserModel> getAllEntities;
    @Inject
    GetEntityById<UserDTO, User, UserModel> getEntityById;
    @Inject
    UpdateUser updateUser;
    
    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public APIResponse create(UserDTO dto) {
        return createUser.execute(dto);
    }

    @GET
    @Path("/find/all")
    @Produces(MediaType.APPLICATION_JSON)
    public APIResponse getAll() {
        return getAllEntities.execute(repository, convertToDomain, convertToDTO);
    }

    @GET
    @Path("find/id/{identifier}")
    @Produces(MediaType.APPLICATION_JSON)
    public APIResponse getById(@PathParam("identifier") String identifier) {
        Long id = Long.parseLong(identifier);
        return getEntityById.execute(id, repository, convertToDomain, convertToDTO);
    }

    @PATCH
    @Path("/update/id/{identifier}")
    @Produces(MediaType.APPLICATION_JSON_PATCH_JSON)
    public APIResponse update(@PathParam("identifier") String identifier, UserDTO user) {
        Long id = Long.parseLong(identifier);
        return updateUser.execute(user, id);
    }
}
