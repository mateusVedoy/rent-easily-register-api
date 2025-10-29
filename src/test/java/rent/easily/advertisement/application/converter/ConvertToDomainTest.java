package rent.easily.advertisement.application.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rent.easily.advertisement.application.dto.AdvertisementDTO;
import rent.easily.advertisement.domain.entity.Advertisement;
import rent.easily.shared.domain.exception.ValidationError;

@ExtendWith(MockitoExtension.class)
public class ConvertToDomainTest {

    ConvertToDomain converter = new ConvertToDomain();

    @Test
    @DisplayName("Deve converter um DTO válido para a entidade Advertisement com sucesso")
    public void testConvert_WhenValidDTO_ShouldReturnDomain() {

        AdvertisementDTO dto = Mockito.mock(AdvertisementDTO.class);
        
        Mockito.when(dto.getRentAmount()).thenReturn(1500.00);
        Mockito.when(dto.getInformation()).thenReturn("Ótimo apartamento no centro");
        Mockito.when(dto.getPropertyId()).thenReturn(1L);

        Advertisement result = Assertions.assertDoesNotThrow(
            () -> converter.convert(dto),
            "Ocorreu uma exceção inesperada durante a conversão."
        );

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1500.00, result.getRentAmount());
        Assertions.assertEquals("Ótimo apartamento no centro", result.getInformation());
        Assertions.assertEquals(1L, result.getPropertyId());
    }

    @Test
    @DisplayName("Deve lançar ValidationError ao tentar converter um DTO com dados inválidos")
    public void testConvert_WhenInvalidDTO_ShouldThrowValidationError() {
        
        AdvertisementDTO dto = Mockito.mock(AdvertisementDTO.class);
        
        Mockito.when(dto.getInformation()).thenReturn("Apartamento com valor inválido");
        Mockito.when(dto.getPropertyId()).thenReturn(null);

        ValidationError exception = Assertions.assertThrows(
            ValidationError.class, 
            () -> converter.convert(dto), 
            "A conversão de um DTO inválido deveria lançar uma ValidationError."
        );

        Assertions.assertNotNull(exception.getErrors());
        Assertions.assertFalse(exception.getErrors().isEmpty());
    }
}