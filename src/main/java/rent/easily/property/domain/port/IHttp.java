package rent.easily.property.domain.port;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import rent.easily.property.application.dto.PropertyValidationDTO;

@RegisterRestClient()
public interface IHttp {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/validate")
    void post(PropertyValidationDTO body);
}