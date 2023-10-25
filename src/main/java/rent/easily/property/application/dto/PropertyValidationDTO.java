package rent.easily.property.application.dto;

import lombok.Getter;

@Getter
public class PropertyValidationDTO {
    private Long id;
    private String userCPF;
    private String propertyRegistry;

    public PropertyValidationDTO(Long id, String cpf, String registry) {
        this.id = id;
        this.userCPF = cpf;
        this.propertyRegistry = registry;
    }
}
