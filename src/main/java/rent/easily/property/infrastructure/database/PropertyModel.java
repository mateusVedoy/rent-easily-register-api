package rent.easily.property.infrastructure.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "property")
@Getter
@Setter
public class PropertyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prop_code")
    private Long id;
    @Column(name = "prop_description")
    private String description;
    @Column(name = "usr_code")
    private Long userId;

    public PropertyModel() {
    }

    public PropertyModel(String description, Long userId) {
        this.description = description;
        this.userId = userId;
    }

    public PropertyModel(Long id, String description, Long userId) {
        this.id = id;
        this.description = description;
        this.userId = userId;
    }

    public String toString() {
        return "Description: " + this.getDescription() +
                ", UserId: " + this.getUserId();
    }
}
