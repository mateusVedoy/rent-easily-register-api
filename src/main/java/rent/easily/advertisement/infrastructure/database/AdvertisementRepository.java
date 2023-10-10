package rent.easily.advertisement.infrastructure.database;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import rent.easily.advertisement.domain.entity.Advertisement;
import rent.easily.shared.infrastructure.Repository;

@ApplicationScoped
public class AdvertisementRepository extends  Repository<Advertisement, AdvertisementModel> {

    @Override
    protected AdvertisementModel convertToModel(Advertisement entity) {
        return new AdvertisementModel(entity.getRentAmount(), entity.getInformation(), entity.getPostedAt(), entity.getPropertyId());
    }

    @Override
    protected List<Advertisement> convertToDomainList(List<AdvertisementModel> models) {
        List<Advertisement> adds = new ArrayList<>();
        for(AdvertisementModel model: models) {
            adds.add(new Advertisement(model.getId(), model.getActive(), model.getRentAmount(), model.getInformation(), model.getPostedAt(), model.getPropertyId()));
        }
        return adds;
    }
    
}
