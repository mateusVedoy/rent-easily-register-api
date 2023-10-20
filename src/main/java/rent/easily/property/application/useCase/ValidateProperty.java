package rent.easily.property.application.useCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import rent.easily.property.infrastructure.database.PropertyModel;
import rent.easily.property.infrastructure.database.PropertyRepository;
import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.response.ResponseError;
import rent.easily.shared.application.response.ResponseSuccess;
import rent.easily.shared.application.response.StatusMessage;

@ApplicationScoped
public class ValidateProperty {

    private final static String OK = "OK";

    @Inject
    PropertyRepository repository;

    public APIResponse execute(Long propertyId, String response) {

        try {
            if(!isThereProperty(propertyId))
                return new ResponseError(400, "There's no Property for given Id", "property.application.useCase.ValidateProperty");

            if(isPropertyValid(response))
                activateProperty(propertyId);
            else
                deleteProperty(propertyId);

            return new ResponseSuccess<>(200, "Property managed according its state");
 
        } catch (Exception e) {
            return new ResponseError(400, StatusMessage.ERROR.getValue(), e);
        }
        

    }

    private boolean isPropertyValid(String response) {
        return response.equals(OK);
    }

    private boolean isThereProperty(Long id) {
        return repository.findById(id) != null;
    }

    private void deleteProperty(Long id) {
        repository.deleteById(id);
    }

    private void activateProperty(Long id) {
        PropertyModel model = repository.findById(id);
        model.setActive("1");
        repository.persist(model);
    }
}
