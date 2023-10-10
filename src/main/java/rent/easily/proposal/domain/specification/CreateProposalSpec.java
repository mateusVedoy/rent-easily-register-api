package rent.easily.proposal.domain.specification;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import rent.easily.advertisement.infrastructure.database.AdvertisementRepository;
import rent.easily.proposal.domain.entity.Proposal;
import rent.easily.proposal.infrastructure.database.ProposalRepository;
import rent.easily.shared.domain.exception.BusinessException;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.ICriteria;
import rent.easily.user.infrastructure.database.UserRepository;

@ApplicationScoped
public class CreateProposalSpec implements ICriteria<Proposal> {

    @Inject
    UserRepository userRepository;
    @Inject
    ProposalRepository proposalRepository;
    @Inject
    AdvertisementRepository advertisementRepository;

    private List<BusinessException> errors;

    @Override
    public void validate(Proposal entry) throws ValidationError {

        errors = new ArrayList<>();
        
        if(!existsAdsForGivenId(entry.getAdvertisementId()))
            errors.add(new BusinessException("There's no Advertisement for given advertisementId", "domain.Advertisement"));

        if (!isThereUserForGivenId(entry.getUserId()))
            errors.add(new BusinessException("There's no User for given userId", "domain.User.id"));

        if(isThereProposalForGivenUserAndAdd(entry.getUserId(), entry.getAdvertisementId()))
            errors.add(new BusinessException("There's already Proposal for given user and add", "domain.Proposal"));

        if(hasErrors())
            throw new ValidationError(errors);
    }

    private boolean hasErrors() {
        return errors.size() > 0;
    }

    private boolean existsAdsForGivenId(Long id) {
        return advertisementRepository.existsById(id);
    }

    private boolean isThereUserForGivenId(Long id) {
        return userRepository.existsUserById(id);
    }

    private boolean isThereProposalForGivenUserAndAdd(Long userId, Long add) {
        return proposalRepository.existsProposalsForUserAndAdd(userId, add);
    }
}
