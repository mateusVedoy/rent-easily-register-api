package rent.easily.property.application.converter;

import jakarta.enterprise.context.ApplicationScoped;
import rent.easily.property.application.dto.PropertyDTO;
import rent.easily.property.domain.entity.Property;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.IConvert;

@ApplicationScoped
public class ConvertToDomain implements IConvert<PropertyDTO, Property>{

    @Override
    public Property convert(PropertyDTO entry) throws ValidationError {
        Property property = new Property(
            entry.getDescription(),
            entry.getUserId(),
            entry.getRegistryId()
        );
        if(property.isValid())
            return property;
        throw new ValidationError(property.getErrors());
    }
}
