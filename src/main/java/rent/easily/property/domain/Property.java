package rent.easily.property.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import rent.easily.shared.domain.Entity;

@Getter
@NoArgsConstructor
public class Property extends Entity {

    private Long id;
    private String description;
    private Long userId;


    public Property(Long id, String description, Long userId) {
        this.id = id;
        this.description = description;
        this.userId = userId;
        validate();
    }

    public Property(String description, Long userId) {
        this.description = description;
        this.userId = userId;
        validate();
    }

    @Override
    public void validate() {
        if (isNull(this.description))
            addError("Description is mandatory", "domain.Property.description");
        if (isNull(this.userId))
            addError("User is mandatory", "domain.Property.userId");
    }
}