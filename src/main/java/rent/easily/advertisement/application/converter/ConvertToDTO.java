package rent.easily.advertisement.application.converter;

import jakarta.enterprise.context.ApplicationScoped;
import rent.easily.advertisement.application.dto.AdvertisementDTO;
import rent.easily.advertisement.domain.entity.Advertisement;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.IConvert;

@ApplicationScoped
public class ConvertToDTO implements IConvert<Advertisement, AdvertisementDTO>{

    @Override
    public AdvertisementDTO convert(Advertisement entry) throws ValidationError {
        return new AdvertisementDTO(entry.getId(), entry.isActive(), entry.getRentAmount(), entry.getInformation(), entry.getPropertyId());
    }
    
}
