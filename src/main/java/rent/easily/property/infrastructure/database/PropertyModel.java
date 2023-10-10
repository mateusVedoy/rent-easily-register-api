package rent.easily.property.infrastructure.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "property")
@Getter
@Setter
@NoArgsConstructor
public class PropertyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_code")
    private Long id;
    @Column(name = "pro_description")
    private String description;
    @Column(name = "pro_active")
    private String active;
    @Column(name = "usr_code")
    private Long userId;

    public PropertyModel(String description, Long userId) {
        this.description = description;
        this.userId = userId;
        this.active = "1";
    }

    public PropertyModel(Long id, String description, Long userId, String active) {
        this.id = id;
        this.description = description;
        this.userId = userId;
        this.active = active;
    }

    public String toString() {
        return "Description: " + this.getDescription() +
                ", UserId: " + this.getUserId();
    }
}
