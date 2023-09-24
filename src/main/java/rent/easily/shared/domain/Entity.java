package rent.easily.shared.domain;

import java.util.ArrayList;
import java.util.List;
import rent.easily.shared.domain.exception.BusinessException;

public abstract class Entity {
    
    private List<BusinessException> errors;

    public Entity() {
        errors = new ArrayList<>();
    }

    public boolean isValid() {
        return this.errors.size() == 0;
    }

    public List<BusinessException> getErrors() {
        return this.errors;
    }

    public void addError(String message, String trace) {
        this.errors.add(new BusinessException(message, trace));
    }

    public abstract void validate();
}