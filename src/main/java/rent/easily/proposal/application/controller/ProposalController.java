package rent.easily.proposal.application.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import rent.easily.proposal.application.dto.ProposalDTO;
import rent.easily.proposal.application.useCase.CreateProposal;
import rent.easily.proposal.application.useCase.FindAllProposals;
import rent.easily.shared.application.response.APIResponse;

@Path("proposal")
@Transactional
public class ProposalController {
    
    @Inject
    CreateProposal createProposal;
    @Inject
    FindAllProposals findAllProposals;

    @POST
    @Path("/create")
    public APIResponse create(ProposalDTO dto) {
        return createProposal.execute(dto);
    }

    @GET
    @Path("/find/all")
    @Produces(MediaType.APPLICATION_JSON)
    public APIResponse getAll() {
        return findAllProposals.execute();
    }
}
