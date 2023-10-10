package rent.easily.evaluation.application.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import rent.easily.evaluation.application.dto.EvaluationDTO;
import rent.easily.evaluation.application.useCase.CreateEvaluation;
import rent.easily.evaluation.domain.entity.Evaluation;
import rent.easily.evaluation.infrastructure.database.EvaluationModel;
import rent.easily.evaluation.infrastructure.database.EvaluationRepository;
import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.useCase.DeleteEntityById;
import rent.easily.shared.application.useCase.GetAllEntities;
import rent.easily.shared.application.useCase.GetEntityById;
import rent.easily.shared.domain.port.IConvert;

@Path("/evaluation")
@Transactional
public class EvaluationController {

    @Inject
    EvaluationRepository repository;
    @Inject
    IConvert<EvaluationDTO, Evaluation> convertToDomain;
    @Inject
    IConvert<Evaluation, EvaluationDTO> convertToDTO;
    @Inject
    GetAllEntities<EvaluationDTO, Evaluation, EvaluationModel> getAllEntities;
    @Inject
    GetEntityById<EvaluationDTO, Evaluation, EvaluationModel> getEntityById;
    @Inject
    CreateEvaluation createEvaluation;
    @Inject
    DeleteEntityById<Evaluation, EvaluationModel> deleteEntityById;

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(EvaluationDTO dto) {
       APIResponse result = createEvaluation.execute(dto);
       return Response.status(result.getStatus()).entity(result).build();
    }

    @GET
    @Path("find/id/{identifier}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("identifier") String identifier) {
        Long id = Long.parseLong(identifier);
        APIResponse result = getEntityById.execute(id, repository, convertToDomain, convertToDTO);
        return Response.status(result.getStatus()).entity(result).build();
    }

    @GET
    @Path("/find/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        APIResponse result = getAllEntities.execute(repository, convertToDomain, convertToDTO);
        return Response.status(result.getStatus()).entity(result).build();
    }

    @DELETE
    @Path("/delete/{identifier}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("identifier") String identifier) {
        Long id = Long.parseLong(identifier);
        APIResponse result = deleteEntityById.execute(id, repository);
        return Response.status(result.getStatus()).entity(result).build();
    }
}
