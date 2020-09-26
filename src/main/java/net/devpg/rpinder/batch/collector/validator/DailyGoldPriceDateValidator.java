package net.devpg.rpinder.batch.collector.validator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import net.devpg.rpinder.batch.collector.CrawlingPattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class DailyGoldPriceDateValidator {
    private final CrawlingPattern crawlingPattern;
    private final ZoneId ZONE_ID = ZoneId.of("Asia/Seoul");
    private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public boolean isToday(Document document) {
        log.info(document.toString());
        //Common Pattern
        Elements commonElement = document.select(crawlingPattern.common());
        Elements dateElement = commonElement.select(crawlingPattern.date());

        LocalDate referenceDate = LocalDate.now(ZONE_ID);
        LocalDate comparisonDate = LocalDate.parse(dateElement.text(), DATE_FORMATTER);

        boolean valid = referenceDate.equals(comparisonDate);
        if (!valid)
            log.warn("date is not valid ==> reference date : {}, comparison date : {}", referenceDate, comparisonDate);
        return valid;
    }
}
