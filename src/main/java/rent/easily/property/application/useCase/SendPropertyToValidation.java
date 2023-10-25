package rent.easily.property.application.useCase;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import rent.easily.property.application.dto.PropertyDTO;
import rent.easily.property.application.dto.PropertyValidationDTO;
import rent.easily.property.domain.port.IHttp;
import rent.easily.property.infrastructure.database.PropertyRepository;
import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.response.ResponseError;
import rent.easily.shared.application.response.ResponseSuccess;
import rent.easily.shared.application.response.StatusMessage;
import rent.easily.shared.domain.exception.BusinessException;
import rent.easily.user.domain.entity.User;
import rent.easily.user.infrastructure.database.UserRepository;

@ApplicationScoped
public class SendPropertyToValidation {

    @Inject
    UserRepository userRepository;
    @RestClient
    IHttp rest;

    public APIResponse send(PropertyDTO dto) {

        try {
            User user = userRepository.findPerId(dto.getUserId());
            PropertyValidationDTO validationDTO = new PropertyValidationDTO(dto.getId(), user.getCPF(), dto.getRegistryId());
            rest.post(validationDTO);
            System.out.println("Property has been sent to validation. Identifier: "+dto.getId());
            return new ResponseSuccess<>(200, "OK");
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return new ResponseError(400, StatusMessage.ERROR.getValue(), e);
        }
    }
}
