package rent.easily.proposal.application.converter;

import jakarta.enterprise.context.ApplicationScoped;
import rent.easily.proposal.application.dto.ProposalDTO;
import rent.easily.proposal.domain.entity.Proposal;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.IConvert;

@ApplicationScoped
public class ConvertToDomain implements IConvert<ProposalDTO, Proposal> {

    @Override
    public Proposal convert(ProposalDTO entry) throws ValidationError {
        Proposal proposal = new Proposal(entry.getAdvertisementId(), entry.getUserId(), entry.getAmount(),
                entry.getInformation());

        if (proposal.isValid())
            return proposal;

        throw new ValidationError(proposal.getErrors());
    }

}
