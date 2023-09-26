package rent.easily.favorite.infrastructure.database;

import java.util.ArrayList;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import rent.easily.favorite.domain.Favorite;
import rent.easily.shared.infrastructure.Repository;

@ApplicationScoped
public class FavoriteRepository extends Repository<Favorite, FavoriteModel> {

    public List<Favorite> getFavoritesByAd(Long advertisementId) {
        PanacheQuery<FavoriteModel> result = find("advertisementId = ?1", advertisementId);
        List<FavoriteModel> models = result.list();
        return convertToDomainList(models);
    }

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
