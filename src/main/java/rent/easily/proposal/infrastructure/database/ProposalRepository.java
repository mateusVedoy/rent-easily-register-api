package rent.easily.proposal.infrastructure.database;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import rent.easily.proposal.domain.entity.Proposal;
import rent.easily.shared.infrastructure.Repository;

@ApplicationScoped
public class ProposalRepository extends Repository<Proposal, ProposalModel>{


    public boolean existsProposalsForUserAndAdd(Long userId, Long add) {
        Long result = count("userId = ?1 AND advertisementId = ?2", userId, add);
        return result > 0;
    }

    @Override
    protected ProposalModel convertToModel(Proposal entity) {
        return new ProposalModel(entity.getInformation(), entity.getUserId(), entity.getAdvertisementId(), entity.getAmount(), entity.getDateTime());
    }

    @Override
    protected List<Proposal> convertToDomainList(List<ProposalModel> models) {
        List<Proposal> proposals = new ArrayList<>();
        for(ProposalModel model: models) {
            proposals.add(new Proposal(model.getId(), model.getDateTime(), model.getAdvertisementId(), model.getUserId(), model.getAmount(), model.getInformation()));
        }
        return proposals;
    }
    
}
