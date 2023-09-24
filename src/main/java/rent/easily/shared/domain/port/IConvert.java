package rent.easily.shared.domain.port;

import rent.easily.shared.domain.exception.ValidationError;

public interface IConvert<T1, T2> {
    T2 convert(T1 entry) throws ValidationError;
}
