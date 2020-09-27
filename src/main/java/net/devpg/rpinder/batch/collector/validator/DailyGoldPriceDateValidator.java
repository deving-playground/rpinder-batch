package net.devpg.rpinder.batch.collector.validator;

import static net.devpg.rpinder.batch.util.LocalDateTimeUtil.*;

import java.time.LocalDate;

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

    public boolean isToday(Document document) {
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
