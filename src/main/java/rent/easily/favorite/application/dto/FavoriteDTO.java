package rent.easily.favorite.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import rent.easily.shared.application.dto.DateTimeDTO;

@Getter
@NoArgsConstructor
public class FavoriteDTO {
    private Long id;
    private Long advertisementId;
    private Long userId;
    private DateTimeDTO favoritedAt;

    public FavoriteDTO(Long ad, Long userId, DateTimeDTO dateTime) {
        this.advertisementId = ad;
        this.userId = userId;
        this.favoritedAt = dateTime;
    }
    public FavoriteDTO(Long id, Long ad, Long userId, DateTimeDTO dateTime) {
        this.id = id;
        this.advertisementId = ad;
        this.userId = userId;
        this.favoritedAt = dateTime;
    }
}
