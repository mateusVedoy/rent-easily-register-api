package rent.easily.favorite.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FavoriteDTO {
    private Long id;
    private Long advertisementId;
    private Long userId;
    private DateTime favoritedAt;

    public FavoriteDTO(Long ad, Long userId, DateTime dateTime) {
        this.advertisementId = ad;
        this.userId = userId;
        this.favoritedAt = dateTime;
    }
    public FavoriteDTO(Long id, Long ad, Long userId, DateTime dateTime) {
        this.id = id;
        this.advertisementId = ad;
        this.userId = userId;
        this.favoritedAt = dateTime;
    }
}
