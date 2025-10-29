package rent.easily.advertisement.application.useCase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import rent.easily.advertisement.application.dto.AdvertisementDTO;
import rent.easily.shared.domain.exception.*;
import rent.easily.advertisement.domain.entity.Advertisement;
import rent.easily.advertisement.infrastructure.database.AdvertisementModel;
import rent.easily.advertisement.infrastructure.database.AdvertisementRepository;
import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.response.ResponseError;
import rent.easily.shared.application.response.ResponseSuccess;
import rent.easily.shared.application.response.StatusMessage;
import rent.easily.shared.application.useCase.CreateEntity;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.IConvert;
import rent.easily.shared.domain.port.ICriteria;

@ExtendWith(MockitoExtension.class)
public class CreateAdvertisementTest {

    @InjectMocks
    private CreateAdvertisement createAdvertisement;

    @Spy
    private CreateEntity<AdvertisementDTO, Advertisement, AdvertisementModel> createEntity = new CreateEntity<>();

    @Mock
    private AdvertisementRepository repository;
    @Mock
    private IConvert<Advertisement, AdvertisementDTO> convertToDTO;
    @Mock
    private IConvert<AdvertisementDTO, Advertisement> convertToDomain;
    @Mock
    private ICriteria<Advertisement> spec;
    @Mock
    private BusinessException businessException;
    private AdvertisementDTO requestDTO;
    private Advertisement advertisement;
    private Advertisement savedAdvertisement;
    private AdvertisementDTO responseDTO;

    @BeforeEach
    void setUp() {

        requestDTO = new AdvertisementDTO(1500.0, "Apartamento novo", 1L);

        advertisement = new Advertisement(1500.0, "Apartamento novo", 1L); 

        savedAdvertisement = new Advertisement(1L, "1", 1500.0, "Apartamento novo", LocalDate.now(), 1L); //

        responseDTO = new AdvertisementDTO(1L, true, 1500.0, "Apartamento novo", 1L);
    }

    @Test
    void testExecute_Success() throws ValidationError {
        
        when(convertToDomain.convert(requestDTO)).thenReturn(advertisement);

        doNothing().when(spec).validate(advertisement);

        when(repository.save(advertisement)).thenReturn(List.of(savedAdvertisement));

        when(convertToDTO.convert(savedAdvertisement)).thenReturn(responseDTO);

        APIResponse response = createAdvertisement.execute(requestDTO);

        assertNotNull(response);
        assertTrue(response.isSuccess()); 
        assertEquals(201, response.getStatus());
        assertEquals(StatusMessage.CREATED.getValue(), response.getMessage());

        assertInstanceOf(ResponseSuccess.class, response);
        ResponseSuccess<AdvertisementDTO> successResponse = (ResponseSuccess<AdvertisementDTO>) response;
        assertEquals(1, successResponse.content().size());
        assertEquals(responseDTO, successResponse.content().get(0));
        assertEquals(1L, successResponse.content().get(0).getId());

        verify(convertToDomain, times(1)).convert(requestDTO);
        verify(spec, times(1)).validate(advertisement);
        verify(repository, times(1)).save(advertisement);
        verify(convertToDTO, times(1)).convert(savedAdvertisement);
    }

    @Test
    void testExecute_ValidationFailure() throws ValidationError {

        List<BusinessException> errors = List.of(new BusinessException("Property ID is mandatory", "Advertisement.propertyId"));
        ValidationError validationError = new ValidationError(errors); 

        when(convertToDomain.convert(requestDTO)).thenReturn(advertisement);

        doThrow(validationError).when(spec).validate(advertisement);

        APIResponse response = createAdvertisement.execute(requestDTO);

        assertNotNull(response);
        assertFalse(response.isSuccess());
        assertEquals(400, response.getStatus());
        assertEquals(StatusMessage.ERROR.getValue(), response.getMessage());

        assertInstanceOf(ResponseError.class, response);
        ResponseError errorResponse = (ResponseError) response;
        assertEquals(1, errorResponse.content().size());
        assertEquals("Property ID is mandatory", errorResponse.content().get(0).getMessage());

        verify(convertToDomain, times(1)).convert(requestDTO);
        verify(spec, times(1)).validate(advertisement);

        verify(repository, never()).save(any());
        verify(convertToDTO, never()).convert(any());
    }

    @Test
    void testExecute_RepositoryFailure() throws ValidationError {

        RuntimeException dbException = new RuntimeException("Database connection failed");

        when(convertToDomain.convert(requestDTO)).thenReturn(advertisement);

        doNothing().when(spec).validate(advertisement);

        when(repository.save(advertisement)).thenThrow(dbException);

        APIResponse response = createAdvertisement.execute(requestDTO);

        assertNotNull(response);
        assertFalse(response.isSuccess());
        assertEquals(400, response.getStatus());
        assertEquals(StatusMessage.ERROR.getValue(), response.getMessage());

        assertInstanceOf(ResponseError.class, response);
        ResponseError errorResponse = (ResponseError) response;
        assertEquals(1, errorResponse.content().size());
        assertEquals("Database connection failed", errorResponse.content().get(0).getMessage());

        verify(convertToDomain, times(1)).convert(requestDTO);
        verify(spec, times(1)).validate(advertisement);
        verify(repository, times(1)).save(advertisement);

        verify(convertToDTO, never()).convert(any());
    }

     @Test
    void testExecute_Success_WithNullSpecification() throws ValidationError {
       
        createAdvertisement.spec = null;

        when(convertToDomain.convert(requestDTO)).thenReturn(advertisement);

        when(repository.save(advertisement)).thenReturn(List.of(savedAdvertisement));

        when(convertToDTO.convert(savedAdvertisement)).thenReturn(responseDTO);

        APIResponse response = createAdvertisement.execute(requestDTO); 

        assertNotNull(response);
        assertTrue(response.isSuccess());
        assertEquals(201, response.getStatus());

        verify(convertToDomain, times(1)).convert(requestDTO);
        verify(repository, times(1)).save(advertisement);
        verify(convertToDTO, times(1)).convert(savedAdvertisement);
        
        verify(spec, never()).validate(any());
    }
}