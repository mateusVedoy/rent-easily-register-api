package rent.easily.property.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PropertyDTO {
    private Long id;
    private String description;
    private Long userId;
    private boolean active;

    public PropertyDTO(Long id, String description, Long userId, boolean active) {
        this.id = id;
        this.description = description;
        this.userId = userId;
        this.active = active;
    }
     public PropertyDTO(String description, Long userId) {
        this.description = description;
        this.userId = userId;
    }
}
