package rent.easily.shared.application.service;

import jakarta.enterprise.context.Dependent;
import rent.easily.shared.domain.exception.ValidationError;

@Dependent
public abstract class EntityNormalizer<T1, T2> {

    public abstract T2 normalize(T1 entry, Long id) throws ValidationError;

    protected <T> boolean isNull(T value) {
        return value == null;
    } 

    protected <T> T setNonNull(T one, T two) {
        return !isNull(one) ? one: two;
    }
}
