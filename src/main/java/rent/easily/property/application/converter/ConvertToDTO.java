package rent.easily.property.application.converter;

import jakarta.enterprise.context.ApplicationScoped;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.IConvert;
import rent.easily.user.application.dto.RegisterTypeDTO;
import rent.easily.user.application.dto.UserDTO;
import rent.easily.user.domain.RegisterType;
import rent.easily.user.domain.User;

@ApplicationScoped
public class ConvertToDTO implements IConvert<User, UserDTO>{

    @Override
    public UserDTO convert(User entry) throws ValidationError {
        return new UserDTO(entry.getId(), entry.getFullName(), entry.getCPF(), entry.getIncome(), setRegisterType(entry.getType().getValue()));
    }

    private String setRegisterType(Long value) {
        if(RegisterType.LESSEE.getValue().equals(value))
            return RegisterTypeDTO.LESSE.getValue();
        else if(RegisterType.LESSOR.getValue().equals(value))
            return RegisterTypeDTO.LESSOR.getValue();
        else
            return null;
    }
    
}
