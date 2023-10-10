package rent.easily.proposal.application.controller;

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
import rent.easily.proposal.application.dto.ProposalDTO;
import rent.easily.proposal.application.useCase.CreateProposal;
import rent.easily.proposal.application.useCase.DeleteProposalById;
import rent.easily.proposal.application.useCase.FindAllProposals;
import rent.easily.proposal.application.useCase.FindProposalById;
import rent.easily.shared.application.response.APIResponse;

@Path("/proposal")
@Transactional
public class ProposalController {

    @Inject
    CreateProposal createProposal;
    @Inject
    FindAllProposals findAllProposals;
    @Inject
    FindProposalById findProposalById;
    @Inject
    DeleteProposalById deleteEntityById;

    @POST
    @Path("/create")
    public Response create(ProposalDTO dto) {
        APIResponse result = createProposal.execute(dto);
        return Response.status(result.getStatus()).entity(result).build();
    }

    @GET
    @Path("/find/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        APIResponse result = findAllProposals.execute();
        return Response.status(result.getStatus()).entity(result).build();
    }

    @GET
    @Path("/find/id/{identifier}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("identifier") String identifier) {
        Long id = Long.parseLong(identifier);
        APIResponse result = findProposalById.execute(id);
        return Response.status(result.getStatus()).entity(result).build();
    }

    @DELETE
    @Path("/delete/{identifier}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("identifier") String identifier) {
        Long id = Long.parseLong(identifier);
        APIResponse result = findProposalById.execute(id);
        return Response.status(result.getStatus()).entity(result).build();
    }
}
