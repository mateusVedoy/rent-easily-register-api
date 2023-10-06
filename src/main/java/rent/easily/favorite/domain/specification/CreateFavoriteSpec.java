package rent.easily.favorite.domain.specification;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ValidationException;
import rent.easily.favorite.domain.entity.Favorite;
import rent.easily.favorite.infrastructure.database.FavoriteRepository;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.ICriteria;

@ApplicationScoped
public class CreateFavoriteSpec implements ICriteria<Favorite> {

    @Inject
    FavoriteRepository repository;

    @Override
    public void validate(Favorite entry) throws ValidationError {
        if(!getFavoritesByAdAndUser(entry.getUserId(), entry.getAdvertisementId()))
            throw new ValidationException("Already exists a favorite to this user and advertisement");
    }

    private boolean getFavoritesByAdAndUser(Long userId, Long advertisementId) {
        return repository.exitsFavoriteByUserAndAdd(userId, advertisementId);
    }

    
}
