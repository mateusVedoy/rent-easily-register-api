package rent.easily.advertisement.application.useCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import rent.easily.advertisement.application.dto.AdvertisementDTO;
import rent.easily.advertisement.domain.entity.Advertisement;
import rent.easily.advertisement.domain.specification.CreateAdvertisementSpec;
import rent.easily.advertisement.infrastructure.database.AdvertisementModel;
import rent.easily.advertisement.infrastructure.database.AdvertisementRepository;
import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.useCase.CreateEntity;
import rent.easily.shared.domain.port.IConvert;
import rent.easily.shared.domain.port.ICriteria;

@ApplicationScoped
public class CreateAdvertisement {
    @Inject
    AdvertisementRepository repository;
    @Inject
    IConvert<Advertisement, AdvertisementDTO> convertToDTO;
    @Inject
    IConvert<AdvertisementDTO, Advertisement> convertToDomain;
    @Inject
    CreateEntity<AdvertisementDTO, Advertisement, AdvertisementModel> createEntity;
    @Inject
    ICriteria<Advertisement> spec;

    public APIResponse execute(AdvertisementDTO dto) {
        return createEntity.execute(dto, repository, convertToDomain, convertToDTO, spec);
    }
}
