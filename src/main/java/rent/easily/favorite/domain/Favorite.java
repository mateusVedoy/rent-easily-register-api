package rent.easily.favorite.domain;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import rent.easily.shared.domain.Entity;

@Getter
@NoArgsConstructor
public class Favorite extends Entity {

    private Long id;
    private Long advertisementId;
    private Long userId;
    private LocalDateTime dateTime;

    public Favorite(Long ad, Long userId) {
        this.advertisementId = ad;
        this.userId = userId;
        this.dateTime = setDateTime();
        validate();
    }

    public Favorite(Long id, Long ad, Long userId, LocalDateTime dateTime) {
        this.id = id;
        this.advertisementId = ad;
        this.userId = userId;
        this.dateTime = setDateTime();
        this.dateTime = dateTime;
    }

    private LocalDateTime setDateTime() {
        return new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().withNano(0);
    }

    @Override
    public void validate() {
        if(isNull(this.advertisementId))
            addError("Advertisement Id is mandatory.", "domain.Favorite.advertisementId");

        if(isNull(this.userId))
            addError("User id is mandatory.", "domain.Favorite.userId");

        if(isNull(this.dateTime))
            addError("Date and time is mandatory.", "domain.Favorite.dateTime");
    }   
}
