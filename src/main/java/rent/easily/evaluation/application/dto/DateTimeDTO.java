package rent.easily.evaluation.application.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DateTimeDTO {
    private LocalDate date;
    private LocalTime time;

    public DateTimeDTO(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }
}
