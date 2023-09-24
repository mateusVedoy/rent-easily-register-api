package rent.easily.user.application.dto;

import lombok.Getter;

@Getter
public class UserDTO {
    private Long id;
    private String fullName;
    private String CPF;
    private double income;
    private String registerType;

    public UserDTO(Long id, String fullName, String cpf, double income, String registerType) {
        this.id = id;
        this.fullName = fullName;
        this.CPF = cpf;
        this.income = income;
        this.registerType = registerType;
    }
}
