package rent.easily.property.application.useCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import rent.easily.property.application.dto.PropertyDTO;
import rent.easily.property.domain.entity.Property;
import rent.easily.property.infrastructure.database.PropertyModel;
import rent.easily.property.infrastructure.database.PropertyRepository;
import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.useCase.CreateEntity;
import rent.easily.shared.domain.port.IConvert;
import rent.easily.shared.domain.port.ICriteria;

@ApplicationScoped
public class CreateProperty {
    @Inject
    CreateEntity<PropertyDTO, Property, PropertyModel> createEntity;
    @Inject
    PropertyRepository repository;
    @Inject
    IConvert<PropertyDTO, Property> convertToDomain;
    @Inject
    IConvert<Property, PropertyDTO> convertToDTO;
    @Inject
    ICriteria<Property> spec;
    

    public APIResponse execute(PropertyDTO dto) {
        return createEntity.execute(dto, repository, convertToDomain, convertToDTO, spec);
    }
}
