package rent.easily.advertisement.application.converter;

import jakarta.enterprise.context.ApplicationScoped;
import rent.easily.advertisement.application.dto.AdvertisementDTO;
import rent.easily.advertisement.domain.entity.Advertisement;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.IConvert;

@ApplicationScoped
public class ConvertToDomain implements IConvert<AdvertisementDTO, Advertisement> {

    @Override
    public Advertisement convert(AdvertisementDTO entry) throws ValidationError {
        Advertisement ads = new Advertisement(entry.getRentAmount(), entry.getInformation(), entry.getPropertyId());

        if (ads.isValid())
            return ads;
        throw new ValidationError(ads.getErrors());
    }

}
