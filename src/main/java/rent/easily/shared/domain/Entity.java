package rent.easily.shared.domain;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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

    public void addErrors(List<BusinessException> errors) {
        this.errors.addAll(errors);
    }

    public abstract void validate();

    protected <T> boolean isNull(T value) {
        return value == null;
    }

    protected LocalDateTime setDateTime() {
        return new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().withNano(0);
    }
}