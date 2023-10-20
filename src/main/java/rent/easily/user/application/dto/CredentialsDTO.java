package rent.easily.user.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CredentialsDTO {
    private String mail;
    private String password;

    public CredentialsDTO(
        String mail,
        String pass
    ) {
        this.mail = mail;
        this.password = pass;
    }
}
