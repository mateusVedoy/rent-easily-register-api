package rent.easily.advertisement.infrastructure.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import rent.easily.advertisement.domain.entity.Advertisement;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class) 
class AdvertisementRepositoryTest {

    @Spy
    private AdvertisementRepository repository;

    private Advertisement domainEntity;
    private AdvertisementModel modelEntity;
    private LocalDate now;

    private class StubAdvertisementModel extends AdvertisementModel {
        private Long id;
        private String active;

        public StubAdvertisementModel(Long id, String active, double rentAmount, String information, LocalDate postedAt, Long propertyId) {
            super(rentAmount, information, postedAt, propertyId);
            this.id = id;
            this.active = active;
        }

        @Override public Long getId() { return this.id; }
        @Override public String getActive() { return this.active; }
    }


    @BeforeEach
    void setUp() {
        now = LocalDate.now();

        domainEntity = new Advertisement(
                1L, "1", 1500.0, "Info", now, 10L
        );

        modelEntity = new StubAdvertisementModel(
                1L, "1", 1500.0, "Info", now, 10L
        );
    }


    @Test
    @DisplayName("existsById: Deve retornar true quando count > 0")
    void testExistsById_ShouldReturnTrue_WhenCountIsPositive() {
 
        Long idToTest = 1L;
        doReturn(1L).when(repository).count("id = ?1", idToTest);

        boolean result = repository.existsById(idToTest);

        assertTrue(result);
    }

    @Test
    @DisplayName("existsById: Deve retornar false quando count == 0")
    void testExistsById_ShouldReturnFalse_WhenCountIsZero() {
       
        Long idToTest = 2L;
        doReturn(0L).when(repository).count("id = ?1", idToTest);

        boolean result = repository.existsById(idToTest);

        assertFalse(result);
    }

    @Test
    @DisplayName("convertToModel: Deve converter corretamente Dominio para Modelo")
    void testConvertToModel() {
        
        AdvertisementModel resultModel = repository.convertToModel(domainEntity);

        assertNotNull(resultModel);
        assertEquals(domainEntity.getRentAmount(), resultModel.getRentAmount());
        assertEquals(domainEntity.getInformation(), resultModel.getInformation()); 
        assertEquals(domainEntity.getPostedAt(), resultModel.getPostedAt());
        assertEquals(domainEntity.getPropertyId(), resultModel.getPropertyId());
    }

    @Test
    @DisplayName("convertToDomainList: Deve converter corretamente Lista de Modelo para Lista de Dominio")
    void testConvertToDomainList() {
     
        List<AdvertisementModel> modelList = List.of(modelEntity);
        List<Advertisement> domainList = repository.convertToDomainList(modelList);

        assertNotNull(domainList);
        assertEquals(1, domainList.size());

        Advertisement resultDomain = domainList.get(0);
        assertEquals(modelEntity.getId(), resultDomain.getId());
        assertEquals(modelEntity.getRentAmount(), resultDomain.getRentAmount());
        assertTrue(resultDomain.isActive()); 
    }

    @Test
    @DisplayName("convertToDomainList: Deve retornar lista vazia se a entrada for vazia")
    void testConvertToDomainList_Empty() {

        List<AdvertisementModel> modelList = List.of();

        List<Advertisement> domainList = repository.convertToDomainList(modelList);

        assertNotNull(domainList);
        assertTrue(domainList.isEmpty());
    }
}