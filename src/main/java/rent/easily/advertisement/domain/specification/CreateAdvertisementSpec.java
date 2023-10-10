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
    PropertyRepository propertyRepository;

    public CreateAdvertisementSpec() {
        this.errors = new ArrayList<>();
    }

    @Override
    public void validate(Advertisement entry) throws ValidationError {
        if(!isTherePropertyForGivenId(entry.getPropertyId()))
            errors.add(new BusinessException("There's no Property for given propertyId", "domain.Advertisement.propertyId"));
        if(!isRentAmountValid(entry.getRentAmount()))
            errors.add(new BusinessException("Rent amount must to be greater than zero", "domain.Advertisement.rentAmount"));

        if(hasErrors())
            throw new ValidationError(errors);
    }

    private boolean hasErrors() {
        return errors.size() > 0;
    }
    
    private boolean isTherePropertyForGivenId(Long id) {
       return propertyRepository.existsById(id);
    }

    private boolean isRentAmountValid(double rent) {
        return rent > 0;
    }
}
