package rent.easily.property.application.converter;

import jakarta.enterprise.context.ApplicationScoped;
import rent.easily.property.application.dto.PropertyAddressDTO;
import rent.easily.property.application.dto.PropertyDTO;
import rent.easily.property.domain.entity.Address;
import rent.easily.property.domain.entity.Property;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.IConvert;

@ApplicationScoped
public class ConvertToDTO implements IConvert<Property, PropertyDTO>{

    @Override
    public PropertyDTO convert(Property entry) throws ValidationError {

        PropertyAddressDTO address = convertAddress(entry.getAddress());

        return new PropertyDTO(
            entry.getId(),
            entry.getDescription(),
            entry.getUserId(),
            entry.getRegistryId(),
            entry.isActive(),
            address
        );
    }    

    private PropertyAddressDTO convertAddress(Address entity) {
        return new PropertyAddressDTO(entity.getCountry(), entity.getState(), entity.getCity(), entity.getNeighborhood(), entity.getStreet(), entity.getStreetNumber(), entity.getZipCode());
    }
}
