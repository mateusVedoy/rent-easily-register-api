package rent.easily.shared.domain.exception;

import java.util.List;

public class ValidationError extends Exception {
    private List<BusinessException> errors;

    public ValidationError(List<BusinessException> errors) {
        this.errors = errors;
    }

    public List<BusinessException> getErrors() {
        return this.errors;
    }
    
}