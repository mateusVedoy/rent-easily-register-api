package rent.easily.advertisement.application.converter;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.NoArgsConstructor;
import rent.easily.advertisement.application.dto.AdvertisementDTO;
import rent.easily.advertisement.domain.entity.Advertisement;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.IConvert;

@ApplicationScoped
@NoArgsConstructor
public class ConvertToDomain implements IConvert<AdvertisementDTO, Advertisement> {

    @Override
    public Advertisement convert(final AdvertisementDTO entry) throws ValidationError {
        final Advertisement ads = new Advertisement(entry.getRentAmount(), entry.getInformation(), entry.getPropertyId());
        ads.validate();

        if (ads.isValid()) {
            return ads;
        }
            
        throw new ValidationError(ads.getErrors());
    }

}
