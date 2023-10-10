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
import jakarta.ws.rs.core.Response;
import rent.easily.favorite.application.dto.FavoriteDTO;
import rent.easily.favorite.application.useCase.CreateFavorite;
import rent.easily.favorite.application.useCase.GetFavoriteByAd;
import rent.easily.favorite.application.useCase.GetFavoriteByUser;
import rent.easily.favorite.domain.entity.Favorite;
import rent.easily.favorite.infrastructure.database.FavoriteModel;
import rent.easily.favorite.infrastructure.database.FavoriteRepository;
import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.useCase.DeleteEntityById;
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
    @Inject
    DeleteEntityById<Favorite, FavoriteModel> deleteEntityById;

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(FavoriteDTO dto) {
       APIResponse result = createFavorite.execute(dto);
       return Response.status(result.getStatus()).entity(result).build();
    }

    @GET
    @Path("/find/advertisement/id/{identifier}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFavByAd(@PathParam("identifier") String identifier) {
        Long id = Long.parseLong(identifier);
        APIResponse result = getFavoriteByAd.execute(id);
        return Response.status(result.getStatus()).entity(result).build();
    }

    @GET
    @Path("/find/user/id/{identifier}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFavByUser(@PathParam("identifier") String identifier) {
        Long id = Long.parseLong(identifier);
        APIResponse result = getFavoriteByUser.execute(id);
        return Response.status(result.getStatus()).entity(result).build();
    }

    @GET
    @Path("find/id/{identifier}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("identifier") String identifier) {
        Long id = Long.parseLong(identifier);
        APIResponse result = getEntityById.execute(id, repository, convertToDomain, convertToDTO);
        return Response.status(result.getStatus()).entity(result).build();
    }

    @DELETE
    @Path("/delete/{identifier}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("identifier") String identifier) {
        Long id = Long.parseLong(identifier);
        APIResponse result = deleteEntityById.execute(id, repository);
        return Response.status(result.getStatus()).entity(result).build();
    }
}
