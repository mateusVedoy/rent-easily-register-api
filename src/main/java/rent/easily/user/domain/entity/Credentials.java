package rent.easily.user.domain.entity;

import lombok.Getter;
import rent.easily.shared.domain.Entity;

@Getter
public class Credentials extends Entity {
    private String mail;
    private String password;

    public Credentials(
            String mail,    //validar formato de email mais além
            String pass) {
        super();
        this.mail = mail;
        this.password = pass;
        validate();
    }

    @Override
    public void validate() {
        if (isNull(this.mail))
            addError("User mail cannot be null", "domain.User.Credentials.mail");
        if (isNull(this.password))
            addError("User password cannot be null", "domain.User.Credentials.password");
    }
}
