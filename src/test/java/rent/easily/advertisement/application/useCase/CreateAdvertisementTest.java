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

    // Classe que queremos testar
    @InjectMocks
    private CreateAdvertisement createAdvertisement;

    // Dependência que queremos testar a lógica real (por isso @Spy)
    @Spy
    private CreateEntity<AdvertisementDTO, Advertisement, AdvertisementModel> createEntity = new CreateEntity<>();

    // Dependências que queremos mockar
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

    // Dados de teste
    private AdvertisementDTO requestDTO;
    private Advertisement advertisement;
    private Advertisement savedAdvertisement;
    private AdvertisementDTO responseDTO;

    @BeforeEach
    void setUp() {
        // 1. DTO de entrada (o que o usuário envia)
        requestDTO = new AdvertisementDTO(1500.0, "Apartamento novo", 1L);

        // 2. Entidade de domínio (resultado da conversão do DTO)
        advertisement = new Advertisement(1500.0, "Apartamento novo", 1L); //

        // 3. Entidade "salva" (o que o repositório retorna, com ID e data)
        savedAdvertisement = new Advertisement(1L, "1", 1500.0, "Apartamento novo", LocalDate.now(), 1L); //

        // 4. DTO de saída (resultado da conversão da entidade salva)
        responseDTO = new AdvertisementDTO(1L, true, 1500.0, "Apartamento novo", 1L); //
    }

    @Test
    void testExecute_Success() throws ValidationError {
        // Arrange
        
        // 1. Mock: Conversão DTO -> Entidade
        when(convertToDomain.convert(requestDTO)).thenReturn(advertisement);

        // 2. Mock: Validação (não faz nada, passa direto)
        doNothing().when(spec).validate(advertisement);

        // 3. Mock: Repositório salva e retorna a entidade com ID
        when(repository.save(advertisement)).thenReturn(List.of(savedAdvertisement));

        // 4. Mock: Conversão Entidade -> DTO (para a resposta)
        when(convertToDTO.convert(savedAdvertisement)).thenReturn(responseDTO);

        // Act
        APIResponse response = createAdvertisement.execute(requestDTO);

        // Assert
        assertNotNull(response);
        assertTrue(response.isSuccess()); //
        assertEquals(201, response.getStatus());
        assertEquals(StatusMessage.CREATED.getValue(), response.getMessage()); //

        // Verifica se a resposta é do tipo ResponseSuccess e contém os dados corretos
        assertInstanceOf(ResponseSuccess.class, response);
        ResponseSuccess<AdvertisementDTO> successResponse = (ResponseSuccess<AdvertisementDTO>) response;
        assertEquals(1, successResponse.content().size()); //
        assertEquals(responseDTO, successResponse.content().get(0));
        assertEquals(1L, successResponse.content().get(0).getId());

        // Verifica se todos os mocks foram chamados na ordem correta
        verify(convertToDomain, times(1)).convert(requestDTO);
        verify(spec, times(1)).validate(advertisement);
        verify(repository, times(1)).save(advertisement);
        verify(convertToDTO, times(1)).convert(savedAdvertisement);
    }

    @Test
    void testExecute_ValidationFailure() throws ValidationError {
        // Arrange
        // Cria uma lista de erros de validação
        List<BusinessException> errors = List.of(new BusinessException("Property ID is mandatory", "Advertisement.propertyId"));
        ValidationError validationError = new ValidationError(errors); //

        // 1. Mock: Conversão DTO -> Entidade
        when(convertToDomain.convert(requestDTO)).thenReturn(advertisement);

        // 2. Mock: Validação (lança a exceção)
        doThrow(validationError).when(spec).validate(advertisement);

        // Act
        APIResponse response = createAdvertisement.execute(requestDTO);

        // Assert
        assertNotNull(response);
        assertFalse(response.isSuccess());
        assertEquals(400, response.getStatus());
        assertEquals(StatusMessage.ERROR.getValue(), response.getMessage());

        // Verifica se é uma Resposta de Erro
        assertInstanceOf(ResponseError.class, response);
        ResponseError errorResponse = (ResponseError) response;
        assertEquals(1, errorResponse.content().size());
        assertEquals("Property ID is mandatory", errorResponse.content().get(0).getMessage());

        // Verifica a ordem das chamadas
        verify(convertToDomain, times(1)).convert(requestDTO);
        verify(spec, times(1)).validate(advertisement);

        // Garante que o repositório e o conversor DTO NUNCA foram chamados
        verify(repository, never()).save(any());
        verify(convertToDTO, never()).convert(any());
    }

    @Test
    void testExecute_RepositoryFailure() throws ValidationError {
        // Arrange
        // Simula uma exceção genérica do banco de dados
        RuntimeException dbException = new RuntimeException("Database connection failed");

        // 1. Mock: Conversão DTO -> Entidade
        when(convertToDomain.convert(requestDTO)).thenReturn(advertisement);

        // 2. Mock: Validação (passa)
        doNothing().when(spec).validate(advertisement);

        // 3. Mock: Repositório (lança exceção)
        when(repository.save(advertisement)).thenThrow(dbException);

        // Act
        APIResponse response = createAdvertisement.execute(requestDTO);

        // Assert
        assertNotNull(response);
        assertFalse(response.isSuccess());
        assertEquals(400, response.getStatus());
        assertEquals(StatusMessage.ERROR.getValue(), response.getMessage());

        // Verifica se a exceção foi capturada e colocada na Resposta de Erro
        assertInstanceOf(ResponseError.class, response);
        ResponseError errorResponse = (ResponseError) response;
        assertEquals(1, errorResponse.content().size());
        assertEquals("Database connection failed", errorResponse.content().get(0).getMessage());

        // Verifica a ordem
        verify(convertToDomain, times(1)).convert(requestDTO);
        verify(spec, times(1)).validate(advertisement);
        verify(repository, times(1)).save(advertisement);

        // Garante que o conversor de DTO nunca foi chamado
        verify(convertToDTO, never()).convert(any());
    }

     @Test
    void testExecute_Success_WithNullSpecification() throws ValidationError {
        // Arrange
        // Força a especificação injetada a ser nula para este teste
        createAdvertisement.spec = null;

        // 1. Mock: Conversão DTO -> Entidade
        when(convertToDomain.convert(requestDTO)).thenReturn(advertisement);

        // 2. Mock: Repositório salva
        when(repository.save(advertisement)).thenReturn(List.of(savedAdvertisement));

        // 3. Mock: Conversão Entidade -> DTO
        when(convertToDTO.convert(savedAdvertisement)).thenReturn(responseDTO);

        // Act
        // O createEntity.execute será chamado com o 'spec' nulo
        APIResponse response = createAdvertisement.execute(requestDTO); 

        // Assert
        assertNotNull(response);
        assertTrue(response.isSuccess());
        assertEquals(201, response.getStatus());

        // Verifica se a lógica de 'hasSpecification' em CreateEntity funcionou
        verify(convertToDomain, times(1)).convert(requestDTO);
        verify(repository, times(1)).save(advertisement);
        verify(convertToDTO, times(1)).convert(savedAdvertisement);
        
        // Garante que a validação nunca foi chamada
        verify(spec, never()).validate(any());
    }
}