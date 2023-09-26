package rent.easily.favorite.application.useCase;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import rent.easily.favorite.application.dto.FavoriteDTO;
import rent.easily.favorite.domain.Favorite;
import rent.easily.favorite.infrastructure.database.FavoriteRepository;
import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.response.ResponseError;
import rent.easily.shared.application.response.ResponseSuccess;
import rent.easily.shared.application.response.StatusMessage;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.IConvert;

@ApplicationScoped
public class GetFavoriteByAd {
    
    @Inject
    FavoriteRepository repository;
    @Inject
    IConvert<FavoriteDTO, Favorite> convertToDomain;
    @Inject
    IConvert<Favorite, FavoriteDTO> convertToDTO;

    public APIResponse execute(Long advertisementId) {
        try {
            List<Favorite> favorite = repository.getFavoritesByAd(advertisementId);
            if(isEmpty(favorite))
                return new ResponseSuccess<>(200, StatusMessage.EMPTY_SUCCESS.getValue());
            List<FavoriteDTO> dtos = buildList(favorite);
            return new ResponseSuccess<>(200, StatusMessage.SUCCESS.getValue(), dtos);
        } catch (ValidationError validationError) {
            return new ResponseError(400, StatusMessage.ERROR.getValue(), validationError);
        }catch (Exception e) {
            return new ResponseError(400, StatusMessage.ERROR.getValue(), e);
        }
    }

    private List<FavoriteDTO> buildList(List<Favorite> list) throws ValidationError {
        List<FavoriteDTO> dtos = new ArrayList<>();
        for(Favorite favorite: list) {
            dtos.add(convertToDTO.convert(favorite));
        }
        return dtos;
    }

    private boolean isEmpty(List<Favorite> list) {
        return list.size() == 0;
    }
}
