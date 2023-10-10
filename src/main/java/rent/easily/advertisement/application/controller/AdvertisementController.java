package rent.easily.advertisement.application.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import rent.easily.advertisement.application.dto.AdvertisementDTO;
import rent.easily.advertisement.application.useCase.CreateAdvertisement;
import rent.easily.advertisement.domain.entity.Advertisement;
import rent.easily.advertisement.infrastructure.database.AdvertisementModel;
import rent.easily.advertisement.infrastructure.database.AdvertisementRepository;
import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.useCase.GetAllEntities;
import rent.easily.shared.domain.port.IConvert;

@Path("/advertisement")
@Transactional
public class AdvertisementController {
    @Inject
    AdvertisementRepository repository;
    @Inject
    IConvert<Advertisement, AdvertisementDTO> convertToDTO;
    @Inject
    IConvert<AdvertisementDTO, Advertisement> convertToDomain;
    @Inject
    CreateAdvertisement createAdvertisement;
    @Inject
    GetAllEntities<AdvertisementDTO, Advertisement, AdvertisementModel> getAllEntities;

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(AdvertisementDTO dto) {
        APIResponse result = createAdvertisement.execute(dto);
        return Response.status(result.getStatus()).entity(result).build();
    }

    @GET
    @Path("/find/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        APIResponse result = getAllEntities.execute(repository,convertToDomain, convertToDTO);
        return Response.status(result.getStatus()).entity(result).build();
    }
}
