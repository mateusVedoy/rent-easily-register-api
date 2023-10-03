package rent.easily.property.application.converter;

import jakarta.enterprise.context.ApplicationScoped;
import rent.easily.property.application.dto.PropertyDTO;
import rent.easily.property.domain.Property;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.IConvert;
import rent.easily.user.application.dto.RegisterTypeDTO;
import rent.easily.user.application.dto.UserDTO;
import rent.easily.user.domain.RegisterType;
import rent.easily.user.domain.User;

@ApplicationScoped
public class ConvertToDTO implements IConvert<Property, PropertyDTO>{

    @Override
    public PropertyDTO convert(Property entry) throws ValidationError {
        return new PropertyDTO(
            entry.getId(),
            entry.getDescription(),
            entry.getUserId()
        );
    }    
}
