package rent.easily.favorite.application.converter;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import rent.easily.shared.application.dto.DateTimeDTO;
import rent.easily.shared.application.service.DateTimeService;
import rent.easily.favorite.application.dto.FavoriteDTO;
import rent.easily.favorite.domain.entity.Favorite;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.IConvert;

@ApplicationScoped
public class ConvertToDTO implements IConvert<Favorite, FavoriteDTO> {

    @Inject
    DateTimeService dateTimeService;

    @Override
    public FavoriteDTO convert(Favorite entry) throws ValidationError {
        DateTimeDTO dateTime = new DateTimeDTO(
                dateTimeService.resolveDate(entry.getDateTime()),
                dateTimeService.resolveTime(entry.getDateTime()));
        return new FavoriteDTO(
                entry.getId(),
                entry.getAdvertisementId(),
                entry.getUserId(),
                dateTime);

    }
}
