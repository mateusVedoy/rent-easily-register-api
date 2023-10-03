package rent.easily.favorite.application.useCase;

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
import rent.easily.shared.application.service.BuildListService;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.IConvert;

@ApplicationScoped
public class GetFavoriteByUser {

    @Inject
    FavoriteRepository repository;
    @Inject
    IConvert<Favorite, FavoriteDTO> convertToDTO;
    @Inject
    BuildListService<Favorite, FavoriteDTO> buildList;

    public APIResponse execute(Long userId) {

        try {
            List<Favorite> favorite = repository.getFavoritesByUser(userId);
            if (isEmpty(favorite))
                return new ResponseSuccess<>(200, StatusMessage.EMPTY_SUCCESS.getValue());
            List<FavoriteDTO> dtos = buildList.process(favorite, convertToDTO);
            return new ResponseSuccess<>(200, StatusMessage.SUCCESS.getValue(), dtos);
        } catch (ValidationError validationError) {
            return new ResponseError(400, StatusMessage.ERROR.getValue(), validationError);
        } catch (Exception e) {
            return new ResponseError(400, StatusMessage.ERROR.getValue(), e);
        }
    }

    private boolean isEmpty(List<Favorite> list) {
        return list.size() == 0;
    }
}
