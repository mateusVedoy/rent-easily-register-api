package rent.easily.advertisement.application.converter;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.NoArgsConstructor;
import rent.easily.advertisement.application.dto.AdvertisementDTO;
import rent.easily.advertisement.domain.entity.Advertisement;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.IConvert;

@ApplicationScoped
@NoArgsConstructor
public class ConvertToDTO implements IConvert<Advertisement, AdvertisementDTO>{


    @Override
    public AdvertisementDTO convert(final Advertisement entry) throws ValidationError {
        return new AdvertisementDTO(entry.getAdvertisementId(), entry.isActive(), entry.getRentAmount(), entry.getInformation(), entry.getPropertyId());
    }
    
}
