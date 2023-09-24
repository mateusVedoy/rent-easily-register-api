package rent.easily.property.application.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import rent.easily.property.application.dto.PropertyDTO;
import rent.easily.property.domain.Property;
import rent.easily.property.infrastructure.database.PropertyModel;
import rent.easily.property.infrastructure.database.PropertyRepository;
import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.useCase.CreateEntity;
import rent.easily.shared.application.useCase.GetAllEntities;
import rent.easily.shared.domain.port.IConvert;

@Path("/property")
@Transactional
public class PropertyController {

    @Inject
    CreateEntity<PropertyDTO, Property, PropertyModel> createEntity;
    @Inject
    PropertyRepository repository;
    @Inject
    IConvert<PropertyDTO, Property> convertToDomain;
    @Inject
    IConvert<Property, PropertyDTO> convertToDTO;
    @Inject
    GetAllEntities<PropertyDTO, Property, PropertyModel> getAllEntities;
    
    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public APIResponse create(PropertyDTO dto) {
        return createEntity.execute(dto, repository, convertToDomain, convertToDTO);
    }

    @GET
    @Path("/find/all")
    @Produces(MediaType.APPLICATION_JSON)
    public APIResponse getAll() {
        return getAllEntities.execute(repository, convertToDomain, convertToDTO);
    }
}
