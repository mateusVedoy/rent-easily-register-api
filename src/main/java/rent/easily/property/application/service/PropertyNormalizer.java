package rent.easily.property.application.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import rent.easily.property.application.dto.PropertyDTO;
import rent.easily.property.domain.entity.Property;
import rent.easily.property.infrastructure.database.PropertyModel;
import rent.easily.property.infrastructure.database.PropertyRepository;
import rent.easily.shared.application.service.EntityNormalizer;
import rent.easily.shared.domain.exception.BusinessException;
import rent.easily.shared.domain.exception.ValidationError;

@ApplicationScoped
public class PropertyNormalizer extends EntityNormalizer<PropertyDTO, PropertyModel>{

    @Inject
    PropertyRepository repository;

    @Override
    public PropertyModel normalize(PropertyDTO entry, Long id) throws ValidationError {
       PropertyModel model = repository.findById(id);
       if(isNull(model))
            throw new ValidationError(List.of(new BusinessException("There's no Property to given id", "Property.service.PropertyNormalize")));
        
        Property property = new Property(id, setNonNull(entry.getDescription(), model.getDescription()), setNonNull(entry.getUserId(), model.getUserId()), setNonNull(setActive(entry.isActive()), model.getActive()));

        model.setDescription(property.getDescription());
        model.setActive(setActive(property.isActive()));

        return model;
    }

    private String setActive(boolean value) {
        return value ? "1" : "0";
    }
    
}
