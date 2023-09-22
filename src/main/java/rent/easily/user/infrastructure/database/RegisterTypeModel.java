package rent.easily.user.infrastructure.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import rent.easily.shared.infrastructure.Model;

@Entity
@Table(name = "registertype")
@Getter @Setter
public class RegisterTypeModel extends Model {
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rgt_code")
    private Long id;
    @Column(name = "rgt_description")
    private String description;

    public RegisterTypeModel() {}

    public RegisterTypeModel (String description) {
        this.description = description;
    }

    public RegisterTypeModel (Long id, String description) {
        this.id = id;
        this.description = description;
    }
}
