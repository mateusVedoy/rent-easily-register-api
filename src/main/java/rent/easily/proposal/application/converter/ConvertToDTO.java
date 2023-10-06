package rent.easily.proposal.application.converter;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import rent.easily.proposal.application.dto.ProposalDTO;
import rent.easily.proposal.domain.entity.Proposal;
import rent.easily.shared.application.dto.DateTimeDTO;
import rent.easily.shared.application.service.DateTimeService;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.IConvert;

@ApplicationScoped
public class ConvertToDTO implements IConvert<Proposal, ProposalDTO> {

    @Inject
    DateTimeService dateTimeService;

    @Override
    public ProposalDTO convert(Proposal entry) throws ValidationError {
        DateTimeDTO dateTime = new DateTimeDTO(
                dateTimeService.resolveDate(entry.getDateTime()),
                dateTimeService.resolveTime(entry.getDateTime()));
        return new ProposalDTO(entry.getId(), entry.getAdvertisementId(), entry.getUserId(), entry.getAmount(), entry.getInformation(), dateTime);
    }

}
