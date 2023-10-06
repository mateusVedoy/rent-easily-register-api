package rent.easily.proposal.application.useCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import rent.easily.proposal.application.dto.ProposalDTO;
import rent.easily.proposal.domain.entity.Proposal;
import rent.easily.proposal.infrastructure.database.ProposalModel;
import rent.easily.proposal.infrastructure.database.ProposalRepository;
import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.useCase.GetEntityById;
import rent.easily.shared.domain.port.IConvert;

@ApplicationScoped
public class FindProposalById {
    
    @Inject
    ProposalRepository repository;
    @Inject
    IConvert<ProposalDTO, Proposal> convertToDomain;
    @Inject
    IConvert<Proposal, ProposalDTO> convertToDTO;
    @Inject
    GetEntityById<ProposalDTO, Proposal, ProposalModel> getEntityById;

    public APIResponse execute(Long id) {
        return getEntityById.execute(id, repository, convertToDomain, convertToDTO);
    }
}
