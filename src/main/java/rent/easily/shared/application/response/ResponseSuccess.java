package rent.easily.shared.application.response;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseSuccess<T> extends APIResponse {

    private List<T> data;

    public ResponseSuccess(int status, String message) {
        super(status, message);
    }

    public ResponseSuccess(int status, String message, List<T> data) {
        super(status, message);
        this.data = data;
    }

    @Override
    public List<T> content() {
        return this.data;
    }
    
}
