package rent.easily.user.application.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import rent.easily.shared.application.service.EntityNormalizer;
import rent.easily.shared.domain.exception.BusinessException;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.user.application.dto.UserDTO;
import rent.easily.user.domain.entity.Credentials;
import rent.easily.user.domain.entity.RegisterType;
import rent.easily.user.domain.entity.User;
import rent.easily.user.infrastructure.database.UserModel;
import rent.easily.user.infrastructure.database.UserRepository;
import rent.easily.user.application.dto.RegisterTypeDTO;

@ApplicationScoped
public class UserNormalize extends EntityNormalizer<UserDTO, UserModel> {

    @Inject
    UserRepository repository;


    @Override
    public UserModel normalize(UserDTO entry, Long id) throws ValidationError {
        UserModel model = repository.findById(id);
        if(isNull(model))
            throw new ValidationError(List.of(new BusinessException("There's no User to given id", "User.service.UserNormalize")));
        
        User newUser = new User(
            id, 
            setNonNull(entry.getFullName(), model.getFullName()), 
            setNonNull(entry.getCpf(), model.getCPF()),
            setNonNull(entry.getIncome(), model.getIncome()),
            setNonNull(buildCredentials(entry.getCredentials().getMail(), entry.getCredentials().getPassword()), buildCredentials(model.getMail(), model.getPassword())),
            setNonNull(setRegisterType(entry.getRegisterType()), model.getTypeId()));

        model.setFullName(newUser.getFullName());
        model.setCPF(newUser.getCPF());
        model.setIncome(newUser.getIncome());
        model.setTypeId(newUser.getType().getValue());

        return model;
    }

    private Credentials buildCredentials(String mail, String pass) {
        Credentials credentials = new Credentials(mail, pass);
        return credentials.isValid() ? credentials : null;
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
