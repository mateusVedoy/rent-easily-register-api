package rent.easily.property.application.useCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import rent.easily.property.application.dto.PropertyDTO;
import rent.easily.property.application.service.PropertyNormalizer;
import rent.easily.property.domain.entity.Property;
import rent.easily.property.infrastructure.database.PropertyModel;
import rent.easily.property.infrastructure.database.PropertyRepository;
import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.useCase.UpdateEntity;

@ApplicationScoped
public class UpdateProperty {
    
    @Inject
    PropertyRepository repository;
    @Inject
    PropertyNormalizer normalizer;
    @Inject
    UpdateEntity<PropertyDTO, Property, PropertyModel> UpdateEntity;

    public APIResponse execute(Long id, PropertyDTO dto) {
        return UpdateEntity.execute(id, dto, repository, normalizer);
    }
}
