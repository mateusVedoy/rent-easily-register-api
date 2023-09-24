package rent.easily.user.application.converter;

import jakarta.enterprise.context.ApplicationScoped;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.IConvert;
import rent.easily.user.application.dto.RegisterTypeDTO;
import rent.easily.user.application.dto.UserDTO;
import rent.easily.user.domain.RegisterType;
import rent.easily.user.domain.User;

@ApplicationScoped
public class ConvertToDomain implements IConvert<UserDTO, User>{

    @Override
    public User convert(UserDTO entry) throws ValidationError {
        User user = new User(entry.getId(), entry.getFullName(), entry.getCPF(), entry.getIncome(), setRegisterType(entry.getRegisterType()));
        if(user.isValid())
            return user;
        throw new ValidationError(user.getErrors());
    }
    
    private Long setRegisterType(String value) {
        if(RegisterTypeDTO.LESSE.getValue().equals(value))
            return RegisterType.LESSEE.getValue();
        else if(RegisterTypeDTO.LESSOR.getValue().equals(value))
            return RegisterType.LESSOR.getValue();
        else
            return null;
    }
}
