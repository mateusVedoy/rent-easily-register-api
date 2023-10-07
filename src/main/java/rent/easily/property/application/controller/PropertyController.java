package rent.easily.property.application.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import rent.easily.property.application.dto.PropertyDTO;
import rent.easily.property.application.useCase.CreateProperty;
import rent.easily.property.application.useCase.UpdateProperty;
import rent.easily.property.domain.entity.Property;
import rent.easily.property.infrastructure.database.PropertyModel;
import rent.easily.property.infrastructure.database.PropertyRepository;
import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.useCase.DeleteEntityById;
import rent.easily.shared.application.useCase.GetAllEntities;
import rent.easily.shared.application.useCase.GetEntityById;
import rent.easily.shared.domain.port.IConvert;

@Path("/property")
@Transactional
public class PropertyController {

    @Inject
    CreateProperty createEntity;
    @Inject
    PropertyRepository repository;
    @Inject
    IConvert<PropertyDTO, Property> convertToDomain;
    @Inject
    IConvert<Property, PropertyDTO> convertToDTO;
    @Inject
    GetAllEntities<PropertyDTO, Property, PropertyModel> getAllEntities;
    @Inject
    GetEntityById<PropertyDTO, Property, PropertyModel> getEntityById;
    @Inject
    DeleteEntityById<Property, PropertyModel> deleteEntityById;
    @Inject
    UpdateProperty updateProperty;

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public APIResponse create(PropertyDTO dto) {
        return createEntity.execute(dto);
    }

    @GET
    @Path("/find/all")
    @Produces(MediaType.APPLICATION_JSON)
    public APIResponse getAll() {
        return getAllEntities.execute(repository, convertToDomain, convertToDTO);
    }

    //TODO: listar todos imóveis do usuario

    //TODO: editar imovel

    @PATCH
    @Path("/update/{identifier}")
     @Produces(MediaType.APPLICATION_JSON)
     public APIResponse updateById(@PathParam("identifier") String identifier, PropertyDTO dto) {
        Long id = Long.parseLong(identifier);
        return updateProperty.execute(id, dto);
     }

    @GET
    @Path("/find/id/{identifier}")
    @Produces(MediaType.APPLICATION_JSON)
    public APIResponse getById(@PathParam("identifier") String identifier) {
        Long id = Long.parseLong(identifier);
        return getEntityById.execute(id, repository, convertToDomain, convertToDTO);
    }

    @DELETE
    @Path("/delete/id/{identifier}")
    @Produces(MediaType.APPLICATION_JSON)
    public APIResponse deleteById(@PathParam("identifier") String identifier) {
        Long id = Long.parseLong(identifier);
        return deleteEntityById.execute(id, repository);
    }
}
