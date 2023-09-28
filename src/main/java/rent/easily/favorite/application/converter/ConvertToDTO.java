package rent.easily.favorite.application.converter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.enterprise.context.ApplicationScoped;
import rent.easily.favorite.application.dto.DateTimeDTO;
import rent.easily.favorite.application.dto.FavoriteDTO;
import rent.easily.favorite.domain.Favorite;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.IConvert;

@ApplicationScoped
public class ConvertToDTO implements IConvert<Favorite, FavoriteDTO> {

    @Override
    public FavoriteDTO convert(Favorite entry) throws ValidationError {
        DateTimeDTO dateTime = new DateTimeDTO(
                resolveDate(entry.getDateTime()),
                resolveTime(entry.getDateTime()));
        return new FavoriteDTO(
                entry.getId(),
                entry.getAdvertisementId(),
                entry.getUserId(),
                dateTime);

    }

    private LocalDate resolveDate(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();
    }

    private LocalTime resolveTime(LocalDateTime localDateTime) {
        return localDateTime.toLocalTime();
    }
}
