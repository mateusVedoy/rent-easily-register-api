package rent.easily.proposal.domain.specification;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import rent.easily.proposal.domain.entity.Proposal;
import rent.easily.shared.domain.exception.BusinessException;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.ICriteria;
import rent.easily.user.infrastructure.database.UserRepository;

@ApplicationScoped
public class CreateProposalSpec implements ICriteria<Proposal> {

    @Inject
    UserRepository userRepository;

    @Override
    public void validate(Proposal entry) throws ValidationError {
        // TODO: valida exist de anuncio

        if (!isThereUserForGivenId(entry.getUserId()))
            throw new ValidationError(
                    List.of(new BusinessException("There's no User for given userId", "domain.User.id")));
    }

    private boolean isThereUserForGivenId(Long id) {
        return userRepository.existsUserById(id);
    }
}
