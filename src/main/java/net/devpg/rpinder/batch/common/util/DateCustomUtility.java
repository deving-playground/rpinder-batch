package net.devpg.rpinder.batch.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DateCustomUtility {
    private final LocalDate CURRENT_DATE = LocalDate.now();
    private final LocalDateTime CURRENT_DATE_TIME = LocalDateTime.now();
    private final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
    private final DateTimeFormatter DEFAULT_PATTERN_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LocalDate getCurrentDate() {
        return CURRENT_DATE;
    }

    public LocalDateTime getCurrentDateTime() {
        return CURRENT_DATE_TIME;
    }

    public String getCurrentDateTimeAsString() {
        return getCurrentDateTime().format(DEFAULT_PATTERN_FORMATTER);
    }

    public LocalDate valueOf(String dateAsString) {
        return valueOf(dateAsString, DEFAULT_FORMATTER);
    }

    public LocalDate valueOf(String dateAsString, DateTimeFormatter formatter) {
        return LocalDate.parse(dateAsString, formatter);
    }
}
