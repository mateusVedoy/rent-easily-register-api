package rent.easily.favorite.domain.specification;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import rent.easily.advertisement.infrastructure.database.AdvertisementRepository;
import rent.easily.favorite.domain.entity.Favorite;
import rent.easily.favorite.infrastructure.database.FavoriteRepository;
import rent.easily.shared.domain.exception.BusinessException;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.ICriteria;
import rent.easily.user.infrastructure.database.UserRepository;

@ApplicationScoped
public class CreateFavoriteSpec implements ICriteria<Favorite> {

    @Inject
    FavoriteRepository repository;
    @Inject
    AdvertisementRepository advertisementRepository;
    @Inject
    UserRepository userRepository;

    private List<BusinessException> errors;

    public CreateFavoriteSpec() {
        this.errors = new ArrayList<>();
    }

    @Override
    public void validate(Favorite entry) throws ValidationError {

        if(!existsUserById(entry.getUserId()))
            errors.add(new BusinessException("There's no User for given userId", "domain.Favorite.userId"));
        
        if(!existsAdsById(entry.getAdvertisementId()))
            errors.add(new BusinessException("There's no Advertisement for given advertisementId", "domain.Favorite.advertisementId"));

        if(!getFavoritesByAdAndUser(entry.getUserId(), entry.getAdvertisementId()))
            errors.add(new BusinessException("Already exists a favorite to this user and advertisement", "domain.Favorite"));

        if(hasErrors())
            throw new ValidationError(errors);
    }

    private boolean hasErrors() {
        return errors.size() > 0;
    }

    private boolean getFavoritesByAdAndUser(Long userId, Long advertisementId) {
        return repository.exitsFavoriteByUserAndAdd(userId, advertisementId);
    }

    private boolean existsUserById(Long id) {
        return userRepository.existsUserById(id);
    }

    private boolean existsAdsById(Long id) {
        return advertisementRepository.existsById(id);
    }
}
