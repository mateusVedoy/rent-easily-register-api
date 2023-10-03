package rent.easily.user.application.specification;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ValidationException;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.ICriteria;
import rent.easily.user.domain.User;
import rent.easily.user.infrastructure.database.UserRepository;

@ApplicationScoped
public class CreateUserSpec implements ICriteria<User>{

    @Inject
    UserRepository repository;

    @Override
    public void validate(User entry) throws ValidationError {
        User user = repository.getUserByCPF(entry.getCPF());
        if(exists(user))
            throw new ValidationException("User already exists to informed CPF");
    }

    private boolean exists(User user) {
        return user != null;
    }
    
}
