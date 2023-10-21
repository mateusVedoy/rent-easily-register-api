package rent.easily.user.application.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.useCase.GetAllEntities;
import rent.easily.shared.application.useCase.GetEntityById;
import rent.easily.shared.domain.port.IConvert;
import rent.easily.user.application.dto.UserDTO;
import rent.easily.user.application.useCase.CreateUser;
import rent.easily.user.application.useCase.GetUserByCredentials;
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
    @Inject
    GetUserByCredentials getUserByCredentials;

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(UserDTO dto) {
        APIResponse result = createUser.execute(dto);
        return Response.status(result.getStatus()).entity(result).build();
    }

    @POST
    @Path("/find/credentials")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByCredentials(UserDTO dto) {
        APIResponse result = getUserByCredentials.execute(dto);
       return Response.status(200).entity(result).build();
    }

    @GET
    @Path("/find/all")
    //@RolesAllowed({ "lesse", "lessor" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        APIResponse result = getAllEntities.execute(repository, convertToDomain, convertToDTO);
        return Response.status(result.getStatus()).entity(result).build();
    }

    @GET
    @Path("find/id/{identifier}")
    @RolesAllowed({ "lesse", "lessor" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("identifier") String identifier) {
        Long id = Long.parseLong(identifier);
        APIResponse result = getEntityById.execute(id, repository, convertToDomain, convertToDTO);
        return Response.status(result.getStatus()).entity(result).build();
    }

    @PATCH
    @Path("/update/id/{identifier}")
    @RolesAllowed({ "lesse", "lessor" })
    @Produces(MediaType.APPLICATION_JSON_PATCH_JSON)
    public Response update(@PathParam("identifier") String identifier, UserDTO user) {
        Long id = Long.parseLong(identifier);
        APIResponse result = updateUser.execute(user, id);
        return Response.status(result.getStatus()).entity(result).build();
    }
}
