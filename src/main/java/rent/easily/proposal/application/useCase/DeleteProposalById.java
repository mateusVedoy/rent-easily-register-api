package rent.easily.proposal.application.useCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import rent.easily.proposal.domain.entity.Proposal;
import rent.easily.proposal.infrastructure.database.ProposalModel;
import rent.easily.proposal.infrastructure.database.ProposalRepository;
import rent.easily.shared.application.response.APIResponse;
import rent.easily.shared.application.useCase.DeleteEntityById;

@ApplicationScoped
public class DeleteProposalById {
    @Inject
    ProposalRepository repository;
    @Inject
    DeleteEntityById<Proposal, ProposalModel> deleteEntityById;

    public APIResponse execute(Long id) {
        return deleteEntityById.execute(id, repository);
    }
}
