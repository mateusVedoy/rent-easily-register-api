package rent.easily.user.application.dto;

import lombok.Getter;

@Getter
public enum RegisterTypeDTO {
    LESSE("lesse"),
    LESSOR("lessor");

    private String value;

    RegisterTypeDTO(String value) {
        this.value = value;
    }
}
