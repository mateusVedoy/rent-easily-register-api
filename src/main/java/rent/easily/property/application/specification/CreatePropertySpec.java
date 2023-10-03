package rent.easily.property.application.specification;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ValidationException;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.ICriteria;
import rent.easily.property.domain.Property;
import rent.easily.property.infrastructure.database.PropertyRepository;

@ApplicationScoped
public class CreatePropertySpec implements ICriteria<Property>{

    @Inject
    PropertyRepository repository;

    @Override
    public void validate(Property entry) throws ValidationError {
        Property property = repository.findPerId(entry.getId());

        if(exists(property))
            throw new ValidationException("Property already exists");
    }

    private boolean exists(Property property) {
        return property != null;
    }
    
}
