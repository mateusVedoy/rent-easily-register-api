package rent.easily.advertisement.infrastructure.database;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.NoArgsConstructor;
import rent.easily.advertisement.domain.entity.Advertisement;
import rent.easily.shared.infrastructure.Repository;

@ApplicationScoped
@NoArgsConstructor
public class AdvertisementRepository extends  Repository<Advertisement, AdvertisementModel> {


    public boolean existsById(final Long advertisementId) {
        final Long result = count("advertisementId = ?1", advertisementId);
        return result > 0;
    }

    @Override
    protected AdvertisementModel convertToModel(final Advertisement entity) {
        return new AdvertisementModel(entity.getRentAmount(), entity.getInformation(), entity.getPostedAt(), entity.getPropertyId());
    }

    @Override
    protected List<Advertisement> convertToDomainList(final List<AdvertisementModel> models) {
        final List<Advertisement> adds = new ArrayList<>();
        for (final AdvertisementModel model : models) {
            adds.add(new Advertisement(model.getAdvertisementId(), model.getActive(), model.getRentAmount(), model.getInformation(), model.getPostedAt(), model.getPropertyId()));
        }
        return adds;
    }
    
}
