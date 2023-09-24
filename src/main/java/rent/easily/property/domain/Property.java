package rent.easily.property.domain;

import lombok.Getter;
import lombok.Setter;
import rent.easily.shared.domain.Entity;

@Getter
@Setter
public class Property extends Entity {

    private Long id;
    private String description;
    private Long userId;

    public Property() {
    }

    public Property(Long id, String description, Long userId) {
        this.id = id;
        this.description = description;
        this.userId = userId;
    }

    @Override
    public void validate() {
        throw new UnsupportedOperationException("Unimplemented method 'validate'");
    }
}
