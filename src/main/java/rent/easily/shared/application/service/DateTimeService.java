package rent.easily.shared.application.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.enterprise.context.Dependent;

@Dependent
public class DateTimeService {
    public LocalDate resolveDate(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();
    }

    public LocalTime resolveTime(LocalDateTime localDateTime) {
        return localDateTime.toLocalTime();
    }
}
