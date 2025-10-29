package rent.easily.advertisement.domain.entity;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class) 
public class AdvertisementTest {

    @Test
    @DisplayName("Deve criar um Advertisement ativo com dados válidos")
    void shouldCreateActiveAdvertisementWithValidData() {
        Advertisement ad = new Advertisement(1200.50, "Apartamento mobiliado", 10L);

        assertTrue(ad.isActive());
        assertEquals(1200.50, ad.getRentAmount());
        assertEquals("Apartamento mobiliado", ad.getInformation());
        assertEquals(10L, ad.getPropertyId());
        assertNotNull(ad.getPostedAt());
        assertTrue(ad.getErrors().isEmpty(), "Não deve conter erros de validação");
    }

    @Test
    @DisplayName("Deve converter corretamente o campo active quando for '1' ou '0'")
    void shouldSetActiveFromString() {
        Advertisement adActive = new Advertisement(1L, "1", 1000.0, "Info", LocalDate.now(), 1L);
        Advertisement adInactive = new Advertisement(2L, "0", 1000.0, "Info", LocalDate.now(), 1L);

        assertTrue(adActive.isActive(), "String '1' deve ser true");
        assertFalse(adInactive.isActive(), "String '0' deve ser false");
    }


    @Test
    @DisplayName("Deve criar Advertisement com id e postedAt definidos manualmente")
    void shouldCreateAdvertisementWithCustomPostedAt() {
        LocalDate postedDate = LocalDate.of(2024, 5, 10);
        Advertisement ad = new Advertisement(5L, "1", 2000.0, "Casa ampla", postedDate, 15L);

        assertEquals(5L, ad.getId());
        assertEquals(postedDate, ad.getPostedAt());
        assertTrue(ad.isActive());
        assertEquals(2000.0, ad.getRentAmount());
        assertEquals("Casa ampla", ad.getInformation());
        assertTrue(ad.getErrors().isEmpty());
    }

    @Test
    @DisplayName("Deve definir postedAt com a data atual ao criar novo anúncio")
    void shouldSetPostedAtWithCurrentDate() {
        Advertisement ad = new Advertisement(1300.0, "Apartamento novo", 11L);

        LocalDate today = LocalDate.now();
        assertEquals(today, ad.getPostedAt(), "postedAt deve ser igual à data atual");
    }
}