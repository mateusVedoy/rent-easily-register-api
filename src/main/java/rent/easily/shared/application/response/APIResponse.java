package rent.easily.shared.application.response;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public abstract class APIResponse<T> {
    private int status;
    private String message;

    public APIResponse(int status, String message){
        this.status = status;
        this.message= message;
    }

    public abstract List<T> content();
}
