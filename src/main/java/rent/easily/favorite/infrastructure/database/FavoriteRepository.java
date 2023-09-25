package rent.easily.favorite.infrastructure.database;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import rent.easily.favorite.domain.Favorite;
import rent.easily.shared.infrastructure.Repository;

@ApplicationScoped
public class FavoriteRepository extends Repository<Favorite, FavoriteModel> {

    @Override
    protected FavoriteModel convertToModel(Favorite entity) {
        return new FavoriteModel(entity.getAdvertisementId(), entity.getUserId(), entity.getDateTime());
    }

    @Override
    protected List<Favorite> convertToDomainList(List<FavoriteModel> model) {
        List<Favorite> favorites = new ArrayList<>();
        for(FavoriteModel mdl: model) {
            favorites.add(new Favorite(mdl.getId(), mdl.getAdvertisementId(), mdl.getUserId(), mdl.getDateTime()));
        }
        return favorites;
    }
    
}
