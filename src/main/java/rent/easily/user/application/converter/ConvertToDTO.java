package rent.easily.user.application.converter;

import jakarta.enterprise.context.ApplicationScoped;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.IConvert;
import rent.easily.user.application.dto.CredentialsDTO;
import rent.easily.user.application.dto.RegisterTypeDTO;
import rent.easily.user.application.dto.UserDTO;
import rent.easily.user.domain.entity.RegisterType;
import rent.easily.user.domain.entity.User;

@ApplicationScoped
public class ConvertToDTO implements IConvert<User, UserDTO>{

    @Override
    public UserDTO convert(User entry) throws ValidationError {
        CredentialsDTO credentialsDTO = new CredentialsDTO(entry.getCredentials().getMail(), entry.getCredentials().getPassword());
        return new UserDTO(entry.getId(), entry.getFullName(), entry.getCPF(), entry.getIncome(), credentialsDTO, setRegisterType(entry.getType().getValue()));
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
