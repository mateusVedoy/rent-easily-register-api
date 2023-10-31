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
    private String registryId;
    private PropertyAddressDTO address;

    public PropertyDTO(Long id, String description, Long userId, String registryId, boolean active, PropertyAddressDTO address) {
        this.id = id;
        this.description = description;
        this.userId = userId;
        this.registryId = registryId;
        this.active = active;
        this.address = address;
    }
     public PropertyDTO(String description, Long userId, String registryId, PropertyAddressDTO address) {
        this.description = description;
        this.userId = userId;
        this.registryId = registryId;
        this.address = address;
    }
}
