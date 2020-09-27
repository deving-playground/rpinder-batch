package net.devpg.rpinder.batch.util;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeUtil {
    public final static ZoneId ZONE_ID = ZoneId.of("Asia/Seoul");
    public final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
}
