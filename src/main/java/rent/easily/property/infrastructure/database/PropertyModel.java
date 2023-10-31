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
    @Column(name = "pro_registryId")
    private String registryId;
    @Column(name = "usr_code")
    private Long userId;
    @Column(name = "adr_code")
    private String address;

    public PropertyModel(String description, Long userId, String registryId, String address) {
        this.description = description;
        this.userId = userId;
        this.registryId = registryId;
        this.address = address;
        this.active = "0";
    }

    public PropertyModel(Long id, String description, Long userId, String registryId, String active, String address) {
        this.id = id;
        this.description = description;
        this.userId = userId;
        this.active = active;
        this.registryId = registryId;
        this.address = address;
    }

    public String toString() {
        return "Description: " + this.getDescription() +
                ", UserId: " + this.getUserId();
    }
}
