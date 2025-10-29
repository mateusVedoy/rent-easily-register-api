package rent.easily.advertisement.domain.specification;

// Importações removidas do Quarkus
// import io.quarkus.test.InjectMock;
// import io.quarkus.test.junit.QuarkusTest;
// import jakarta.inject.Inject;

// Importações adicionadas do Mockito
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import rent.easily.advertisement.domain.entity.Advertisement;
import rent.easily.property.infrastructure.database.PropertyRepository;
import rent.easily.shared.domain.exception.BusinessException;
import rent.easily.shared.domain.exception.ValidationError;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateAdvertisementSpecTest {

    @InjectMocks 
    CreateAdvertisementSpec spec;

    @Mock 
    PropertyRepository propertyRepository;

    @Test
    @DisplayName("Deve validar com sucesso (Caminho Feliz)")
    void testValidate_HappyPath_ShouldNotThrowException() {
       
        Long propertyId = 1L;
        double rentAmount = 1500.0;
        Advertisement ad = new Advertisement(rentAmount, "Info", propertyId);

        Mockito.when(propertyRepository.existsById(propertyId)).thenReturn(true);

        assertDoesNotThrow(() -> {
            spec.validate(ad);
        });
    }

    @Test
    @DisplayName("Deve lançar ValidationError se o PropertyId não existir")
    void testValidate_PropertyNotFound_ShouldThrowValidationError() {
       
        Long propertyId = 99L; 
        double rentAmount = 1500.0;
        Advertisement ad = new Advertisement(rentAmount, "Info", propertyId);

        Mockito.when(propertyRepository.existsById(propertyId)).thenReturn(false);

        ValidationError exception = assertThrows(ValidationError.class, () -> {
            spec.validate(ad);
        });

        assertNotNull(exception.getErrors());
        assertEquals(1, exception.getErrors().size());

        BusinessException businessException = exception.getErrors().get(0);
        assertEquals("There's no Property for given propertyId", businessException.getMessage());
        
        assertEquals("domain.Advertisement.propertyId", businessException.getTrace());
    }

    @Test
    @DisplayName("Deve lançar ValidationError se o RentAmount for zero")
    void testValidate_InvalidRentAmount_Zero_ShouldThrowValidationError() {
    
        Long propertyId = 1L;
        double rentAmount = 0.0; 
        Advertisement ad = new Advertisement(rentAmount, "Info", propertyId);

        Mockito.when(propertyRepository.existsById(propertyId)).thenReturn(true);

        ValidationError exception = assertThrows(ValidationError.class, () -> {
            spec.validate(ad);
        });

        assertEquals(1, exception.getErrors().size());
        BusinessException businessException = exception.getErrors().get(0);
        assertEquals("Rent amount must to be greater than zero", businessException.getMessage());
        assertEquals("domain.Advertisement.rentAmount", businessException.getTrace());
    }

    @Test
    @DisplayName("Deve lançar ValidationError se o RentAmount for negativo")
    void testValidate_InvalidRentAmount_Negative_ShouldThrowValidationError() {
  
        Long propertyId = 1L;
        double rentAmount = -100.0; 
        Advertisement ad = new Advertisement(rentAmount, "Info", propertyId);

        Mockito.when(propertyRepository.existsById(propertyId)).thenReturn(true);

        ValidationError exception = assertThrows(ValidationError.class, () -> {
            spec.validate(ad);
        });

        assertEquals(1, exception.getErrors().size());
        assertEquals("Rent amount must to be greater than zero", exception.getErrors().get(0).getMessage());
    }

    @Test
    @DisplayName("Deve lançar ValidationError com DOIS erros (PropertyId e RentAmount)")
    void testValidate_PropertyNotFoundAndInvalidRent_ShouldThrowWithTwoErrors() {
      
        Long propertyId = 99L;    
        double rentAmount = 0.0; 
        Advertisement ad = new Advertisement(rentAmount, "Info", propertyId);

        Mockito.when(propertyRepository.existsById(propertyId)).thenReturn(false);

        ValidationError exception = assertThrows(ValidationError.class, () -> {
            spec.validate(ad);
        });

        assertEquals(2, exception.getErrors().size());

        boolean hasPropertyError = exception.getErrors().stream()
                .anyMatch(err -> err.getTrace().equals("domain.Advertisement.propertyId"));
        boolean hasRentError = exception.getErrors().stream()
                .anyMatch(err -> err.getTrace().equals("domain.Advertisement.rentAmount"));

        assertTrue(hasPropertyError, "Deveria conter o erro do propertyId");
        assertTrue(hasRentError, "Deveria conter o erro do rentAmount");
    }
}