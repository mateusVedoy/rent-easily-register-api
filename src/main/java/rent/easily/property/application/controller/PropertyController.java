package rent.easily.property.application.controller;

import jakarta.annotation.security.RolesAllowed;
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
import jakarta.ws.rs.core.Response;
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
    @RolesAllowed({ "lessor" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(PropertyDTO dto) {
        APIResponse result = createEntity.execute(dto);
        return Response.status(result.getStatus()).entity(result).build();
    }

    @GET
    @Path("/find/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        APIResponse result = getAllEntities.execute(repository, convertToDomain, convertToDTO);
        return Response.status(result.getStatus()).entity(result).build();
    }

    // TODO: listar todos imóveis do usuario

    // TODO: editar imovel

    @PATCH
    @Path("/update/{identifier}")
    @RolesAllowed({ "lessor" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateById(@PathParam("identifier") String identifier, PropertyDTO dto) {
        Long id = Long.parseLong(identifier);
        APIResponse result = updateProperty.execute(id, dto);
        return Response.status(result.getStatus()).entity(result).build();
    }

    @GET
    @Path("/find/id/{identifier}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("identifier") String identifier) {
        Long id = Long.parseLong(identifier);
        APIResponse result = getEntityById.execute(id, repository, convertToDomain, convertToDTO);
        return Response.status(result.getStatus()).entity(result).build();
    }

    @DELETE
    @Path("/delete/id/{identifier}")
    @RolesAllowed({ "lessor" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("identifier") String identifier) {
        Long id = Long.parseLong(identifier);
        APIResponse result = deleteEntityById.execute(id, repository);
        return Response.status(result.getStatus()).entity(result).build();
    }
}
