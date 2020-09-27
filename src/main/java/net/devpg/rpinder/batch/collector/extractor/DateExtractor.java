package net.devpg.rpinder.batch.collector.extractor;

import static net.devpg.rpinder.batch.util.LocalDateTimeUtil.*;

import java.time.LocalDate;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import net.devpg.rpinder.batch.collector.CrawlingPattern;
import net.devpg.rpinder.batch.collector.vo.GoldPrice;

@Slf4j
@Service
@RequiredArgsConstructor
public class DateExtractor implements Extractor<Document, GoldPrice> {
    private final CrawlingPattern crawlingPattern;

    @Override
    public void extract(Document document, GoldPrice goldPrice) {
        //Common Pattern
        Elements commonElement = document.select(crawlingPattern.common());
        //Extract Today Date
        Elements dateElement = commonElement.select(crawlingPattern.date());
        goldPrice.date(LocalDate.parse(dateElement.text(), DATE_FORMATTER).format(DATE_FORMATTER));
    }
}
