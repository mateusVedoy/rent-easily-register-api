package rent.easily.property.application.converter;

import jakarta.enterprise.context.ApplicationScoped;
import rent.easily.property.application.dto.PropertyDTO;
import rent.easily.property.domain.entity.Address;
import rent.easily.property.domain.entity.Property;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.IConvert;

@ApplicationScoped
public class ConvertToDomain implements IConvert<PropertyDTO, Property>{

    @Override
    public Property convert(PropertyDTO entry) throws ValidationError {

        Address address = new Address(entry.getAddress().getCountry(), entry.getAddress().getState(), entry.getAddress().getCity(), entry.getAddress().getNeighborhood(), entry.getAddress().getStreet(), entry.getAddress().getStreetNumber(), entry.getAddress().getZipCode());

        Property property = new Property(
            entry.getDescription(),
            entry.getUserId(),
            entry.getRegistryId(),
            address
        );
        if(property.isValid())
            return property;
        throw new ValidationError(property.getErrors());
    }
}
