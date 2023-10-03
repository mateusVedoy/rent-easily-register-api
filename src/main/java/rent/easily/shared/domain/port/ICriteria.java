package rent.easily.shared.domain.port;

import rent.easily.shared.domain.exception.ValidationError;

public interface ICriteria<T> {
    void validate(T entry) throws ValidationError;
}
