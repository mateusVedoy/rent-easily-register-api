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
import rent.easily.favorite.application.useCase.CreateFavorite;
import rent.easily.favorite.application.useCase.GetFavoriteByAd;
import rent.easily.favorite.application.useCase.GetFavoriteByUser;
import rent.easily.favorite.domain.entity.Favorite;
import rent.easily.favorite.infrastructure.database.FavoriteModel;
import rent.easily.favorite.infrastructure.database.FavoriteRepository;
import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.useCase.GetAllEntities;
import rent.easily.shared.application.useCase.GetEntityById;
import rent.easily.shared.domain.port.IConvert;

@Path("/favorite")
@Transactional
public class FavoriteController {

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
    @Inject
    GetFavoriteByAd getFavoriteByAd;
    @Inject
    GetFavoriteByUser getFavoriteByUser;
    @Inject
    CreateFavorite createFavorite;

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public APIResponse create(FavoriteDTO dto) {
       return createFavorite.execute(dto);
    }

    @GET
    @Path("/find/advertisement/id/{identifier}")
    @Produces(MediaType.APPLICATION_JSON)
    public APIResponse getFavByAd(@PathParam("identifier") String identifier) {
        Long id = Long.parseLong(identifier);
        return getFavoriteByAd.execute(id);
    }

    @GET
    @Path("/find/user/id/{identifier}")
    @Produces(MediaType.APPLICATION_JSON)
    public APIResponse getFavByUser(@PathParam("identifier") String identifier) {
        Long id = Long.parseLong(identifier);
        return getFavoriteByUser.execute(id);
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
