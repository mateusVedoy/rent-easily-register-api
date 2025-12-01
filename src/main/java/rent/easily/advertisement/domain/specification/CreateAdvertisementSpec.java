package rent.easily.advertisement.domain.specification;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import rent.easily.advertisement.domain.entity.Advertisement;
import rent.easily.property.infrastructure.database.PropertyRepository;
import rent.easily.shared.domain.exception.BusinessException;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.ICriteria;

@ApplicationScoped
public class CreateAdvertisementSpec implements ICriteria<Advertisement>{

    private List<BusinessException> errors;

    @Inject
    private final PropertyRepository propertyRepo;

    public CreateAdvertisementSpec(final PropertyRepository propertyRepo) {
        this.propertyRepo = propertyRepo;
    }

    @Override
    public void validate(final Advertisement entry) throws ValidationError {
        this.errors = new ArrayList<>();
        if(!isTherePropertyForGivenId(entry.getPropertyId())) {
            errors.add(new BusinessException("There's no Property for given propertyId", "domain.Advertisement.propertyId"));
        }
        if(!isRentAmountValid(entry.getRentAmount())) {
            errors.add(new BusinessException("Rent amount must to be greater than zero", "domain.Advertisement.rentAmount"));
        }

        if(hasErrors()){
            throw new ValidationError(errors);
        }
    }

    private boolean hasErrors() {
        return !errors.isEmpty();
    }
    
    private boolean isTherePropertyForGivenId(final Long propertyId) {
       return propertyRepo.existsById(propertyId);
    }

    private boolean isRentAmountValid(final double rent) {
        return rent > 0;
    }
}
