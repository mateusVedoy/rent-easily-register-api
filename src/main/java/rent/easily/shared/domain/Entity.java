package rent.easily.shared.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity {
    
    private List<Exception> errors;

    public Entity() {
        errors = new ArrayList<>();
    }

    public boolean isValid() {
        return this.errors.size() == 0;
    }

    public List<Exception> getErrors() {
        return this.errors;
    }

    public void addError(Exception exception) {
        this.errors.add(exception);
    }

    public abstract void validate();
}