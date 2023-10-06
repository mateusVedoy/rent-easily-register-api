package rent.easily.user.domain.specification;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import rent.easily.shared.domain.exception.BusinessException;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.ICriteria;
import rent.easily.user.domain.entity.User;
import rent.easily.user.infrastructure.database.UserRepository;

@ApplicationScoped
public class CreateUserSpec implements ICriteria<User>{

    @Inject
    UserRepository repository;

    @Override
    public void validate(User entry) throws ValidationError {
        User user = repository.getUserByCPF(entry.getCPF());
        if(exists(user))
            throw new ValidationError(List.of(new BusinessException("User already exists to informed CPF", "domain.User.CPF")));
    }

    private boolean exists(User user) {
        return user != null;
    }
    
}
