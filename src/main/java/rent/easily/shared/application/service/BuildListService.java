package rent.easily.shared.application.service;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.Dependent;
import rent.easily.shared.domain.exception.ValidationError;
import rent.easily.shared.domain.port.IConvert;

@Dependent
public class BuildListService<T1, T2> {
    
    public List<T2> process(List<T1> list, IConvert<T1, T2> converter) throws ValidationError {
        List<T2> result = new ArrayList<>();
        for(T1 value: list) {
            result.add(converter.convert(value));
        }
        return result;
    }
}
