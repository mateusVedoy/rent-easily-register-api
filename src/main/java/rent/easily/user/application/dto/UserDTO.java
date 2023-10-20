package rent.easily.user.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String fullName;
    private String cpf;
    private double income;
    private String registerType;
    private CredentialsDTO credentials;

    public UserDTO(Long id, String fullName, String CPF, double income, CredentialsDTO credentials, String registerType) {
        this.id = id;
        this.fullName = fullName;
        this.cpf = CPF;
        this.income = income;
        this.registerType = registerType;
        this.credentials = credentials;
    }
}
