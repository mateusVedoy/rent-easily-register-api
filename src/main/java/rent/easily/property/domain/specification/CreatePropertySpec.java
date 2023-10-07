package rent.easily.property.domain.specification;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import rent.easily.property.domain.entity.Property;
import rent.easily.shared.domain.exception.BusinessException;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.ICriteria;
import rent.easily.user.infrastructure.database.UserModel;
import rent.easily.user.infrastructure.database.UserRepository;

@ApplicationScoped
public class CreatePropertySpec implements ICriteria<Property>{

    @Inject
    UserRepository repository;

    @Override
    public void validate(Property entry) throws ValidationError {
        UserModel model = repository.findById(entry.getUserId());

        if(isNull(model))
            throw new ValidationError(List.of(new BusinessException("There's no User for given userId", "domain.User")));
    }

    private <T> boolean isNull(T entry)  {
        return entry == null;
    }
    
}
