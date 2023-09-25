package rent.easily.favorite.infrastructure.database;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "favorite")
@Getter
@NoArgsConstructor
public class FavoriteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fvt_code")
    private Long id;
    @Column(name = "fvt_advertisement")
    private Long advertisementId;
    @Column(name = "fvt_user")
    private Long userId;
    @Column(name = "fvt_dateTime")
    private LocalDateTime dateTime;

    public FavoriteModel(Long ad,Long userId, LocalDateTime dateTime) {
        this.advertisementId = ad;
        this.userId = userId;
        this.dateTime = dateTime;
    }

    public FavoriteModel(Long id, Long ad,Long userId, LocalDateTime dateTime) {
        this.id = id;
        this.advertisementId = ad;
        this.userId = userId;
        this.dateTime = dateTime;
    }
}
