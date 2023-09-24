package rent.easily.shared.application.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private String message;
    private String stacktrace;

    public Message(String message, String stacktrace) {
        this.message = message;
        this.stacktrace = stacktrace;
    }
}
