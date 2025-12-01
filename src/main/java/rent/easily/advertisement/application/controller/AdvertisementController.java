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
import rent.easily.advertisement.application.usecase.CreateAdvertisement;
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
    private final AdvertisementRepository repository;
    @Inject
    private final IConvert<Advertisement, AdvertisementDTO> convertToDTO;
    @Inject
    private final IConvert<AdvertisementDTO, Advertisement> convertToDomain;
    @Inject
    private final CreateAdvertisement createAdv;
    @Inject
    private final GetAllEntities<AdvertisementDTO, Advertisement, AdvertisementModel> getAllEntities;

    public AdvertisementController(
        final AdvertisementRepository repository, 
        final IConvert<Advertisement, AdvertisementDTO> convertToDTO, 
        final IConvert<AdvertisementDTO, Advertisement> convertToDomain, 
        final CreateAdvertisement createAdv, 
        final GetAllEntities<AdvertisementDTO, Advertisement, AdvertisementModel> getAllEntities) {
        this.repository = repository;
        this.convertToDTO = convertToDTO;
        this.convertToDomain = convertToDomain;
        this.createAdv = createAdv;
        this.getAllEntities = getAllEntities;
    }

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(final AdvertisementDTO dto) {
        final APIResponse result = createAdv.execute(dto);
        return Response.status(result.getStatus()).entity(result).build();
    }

    @GET
    @Path("/find/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        final APIResponse result = getAllEntities.execute(repository,convertToDomain, convertToDTO);
        return Response.status(result.getStatus()).entity(result).build();
    }
}
