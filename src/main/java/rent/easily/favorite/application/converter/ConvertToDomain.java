package rent.easily.favorite.application.converter;

import jakarta.enterprise.context.ApplicationScoped;
import rent.easily.favorite.application.dto.FavoriteDTO;
import rent.easily.favorite.domain.Favorite;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.IConvert;

@ApplicationScoped
public class ConvertToDomain implements IConvert<FavoriteDTO, Favorite>{

    @Override
    public Favorite convert(FavoriteDTO entry) throws ValidationError {
        Favorite favorite = new Favorite(entry.getAdvertisementId(), entry.getUserId());
        if(favorite.isValid())
            return favorite;
        throw new ValidationError(favorite.getErrors());
    }
    
}
