package rent.easily.advertisement.application.usecase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import rent.easily.advertisement.application.dto.AdvertisementDTO;
import rent.easily.advertisement.domain.entity.Advertisement;
import rent.easily.advertisement.infrastructure.database.AdvertisementModel;
import rent.easily.advertisement.infrastructure.database.AdvertisementRepository;
import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.useCase.CreateEntity;
import rent.easily.shared.domain.port.IConvert;
import rent.easily.shared.domain.port.ICriteria;

@ApplicationScoped
public class CreateAdvertisement {
    @Inject
    public final AdvertisementRepository repository;
    @Inject
    public final IConvert<Advertisement, AdvertisementDTO> convertToDTO;
    @Inject
    public final IConvert<AdvertisementDTO, Advertisement> convertToDomain;
    @Inject
    public final CreateEntity<AdvertisementDTO, Advertisement, AdvertisementModel> createEntity;
    @Inject
    public ICriteria<Advertisement> spec;

    public CreateAdvertisement(final AdvertisementRepository repository, final IConvert<Advertisement, AdvertisementDTO> convertToDTO, final IConvert<AdvertisementDTO, Advertisement> convertToDomain, final CreateEntity<AdvertisementDTO, Advertisement, AdvertisementModel> createEntity, final ICriteria<Advertisement> spec) {
        this.repository = repository;
        this.convertToDTO = convertToDTO;
        this.convertToDomain = convertToDomain;
        this.createEntity = createEntity;
        this.spec = spec;
    }

    public APIResponse execute(final AdvertisementDTO dto) {
        return createEntity.execute(dto, repository, convertToDomain, convertToDTO, spec);
    }
}
