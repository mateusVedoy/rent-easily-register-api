package rent.easily.favorite.application.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import rent.easily.favorite.application.dto.FavoriteDTO;
import rent.easily.favorite.domain.Favorite;
import rent.easily.favorite.infrastructure.database.FavoriteModel;
import rent.easily.favorite.infrastructure.database.FavoriteRepository;
import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.useCase.CreateEntity;
import rent.easily.shared.application.useCase.GetAllEntities;
import rent.easily.shared.application.useCase.GetEntityById;
import rent.easily.shared.domain.port.IConvert;
import rent.easily.user.application.dto.UserDTO;
import rent.easily.user.infrastructure.database.UserRepository;

@Path("/favorite")
@Transactional
public class FavoriteController {

    @Inject
    CreateEntity<FavoriteDTO, Favorite, FavoriteModel> createEntity;
    @Inject
    FavoriteRepository repository;
    @Inject
    IConvert<FavoriteDTO, Favorite> convertToDomain;
    @Inject
    IConvert<Favorite, FavoriteDTO> convertToDTO;
    @Inject
    GetAllEntities<FavoriteDTO, Favorite, FavoriteModel> getAllEntities;
    @Inject
    GetEntityById<FavoriteDTO, Favorite, FavoriteModel> getEntityById;

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public APIResponse create(FavoriteDTO dto) {
       return createEntity.execute(dto, repository, convertToDomain, convertToDTO);
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

    @DELETE
    @Path("/delete/{identifier}")
    @Produces(MediaType.APPLICATION_JSON)
    public APIResponse deleteById(@PathParam("identifier") String identifier) {
        Long id = Long.parseLong(identifier);
        return null;
    }
}
