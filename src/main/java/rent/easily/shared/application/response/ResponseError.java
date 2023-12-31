package rent.easily.shared.application.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rent.easily.shared.domain.exception.BusinessException;

@Getter
@Setter
@NoArgsConstructor
public class ResponseError extends APIResponse<Message> {

    private List<Message> errors;

    public ResponseError(int status, String message, List<BusinessException> errors) {
        super(status, message);
        this.errors = buildMessagesFromBusinessException(errors);
    }

    public ResponseError(int status, String message, String stack) {
        super(status, message);
        this.errors = new ArrayList<>(List.of(new Message(message, stack)));
    }

    public ResponseError(int status, String message, Exception ex) {
        super(status, message);
        errors = convertAnyExceptionToMessageList(ex);
    }

    public ResponseError(int status, String message, BusinessException ex) {
        super(status, message);
        errors = convertBusinessExceptionToMessageList(ex);
    }

    private List<Message> convertBusinessExceptionToMessageList(BusinessException ex) {
        return new ArrayList<>(List.of(
           new Message(ex.getMessage(), ex.getTrace())
        ));
    }

    private List<Message> buildMessagesFromBusinessException(List<BusinessException> errors) {

        if(errors == null)
            return null;

        List<Message> messages = new ArrayList<>();

        errors.forEach(error -> {
            messages.add(new Message(error.getMessage(), error.getTrace()));
        });

        return messages;
    }

    public List<Message> getErrors() {
        return errors;
    }

    @Override
    public int getStatus() {
        return super.getStatus();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    private List<Message> convertAnyExceptionToMessageList(Exception ex) {
        return new ArrayList<>(List.of(
                new Message(ex.getMessage(), setStackTraceByErrorMessage(ex.getMessage()))
        ));
    }

    private String setStackTraceByErrorMessage(String message) {
        if(!isStringNullOrEmpty(message))
            return message.replace(" ", ".");
        return null;
    }

    private boolean isStringNullOrEmpty(String value) {
        return value == null || value.isEmpty() || value.isBlank();
    }

    private String getObjectsToStringFromErrors() {
        if(hasErrors()){
            StringBuilder builder = new StringBuilder();
            builder.append("[ ");
            this.errors.forEach(value -> {
                builder.append(value.toString());
                builder.append(" ");
            });
            builder.append("]");
            return builder.toString();
        }
        return null;
    }
    private boolean hasErrors() {
        return this.errors != null && this.errors.size() > 0;
    }
    @Override
    public String toString() {
        return "ResponseError{" +
                "errors:" + getObjectsToStringFromErrors() +
                ", status:" + super.getStatus() +
                ", message:'" + super.getMessage() + '\'' +
                '}';
    }

    @Override
    public List<Message> content() {
        return this.errors;    
    }
    
}
