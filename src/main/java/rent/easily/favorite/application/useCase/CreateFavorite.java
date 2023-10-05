package rent.easily.favorite.application.useCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import rent.easily.favorite.application.dto.FavoriteDTO;
import rent.easily.favorite.domain.entity.Favorite;
import rent.easily.favorite.infrastructure.database.FavoriteModel;
import rent.easily.favorite.infrastructure.database.FavoriteRepository;
import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.useCase.CreateEntity;
import rent.easily.shared.domain.port.IConvert;
import rent.easily.shared.domain.port.ICriteria;

@ApplicationScoped
public class CreateFavorite {

    @Inject
    CreateEntity<FavoriteDTO, Favorite, FavoriteModel> createEntity;
    @Inject
    FavoriteRepository repository;
    @Inject
    IConvert<FavoriteDTO, Favorite> convertToDomain;
    @Inject
    IConvert<Favorite, FavoriteDTO> convertToDTO;
    @Inject
    ICriteria<Favorite> spec;

    public APIResponse execute(FavoriteDTO dto) {
        return createEntity.execute(dto, repository, convertToDomain, convertToDTO, spec);
    }
}
