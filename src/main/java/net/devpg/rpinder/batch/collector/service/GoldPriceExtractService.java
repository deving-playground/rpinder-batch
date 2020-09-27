package net.devpg.rpinder.batch.collector.service;

import static net.devpg.rpinder.batch.util.LocalDateTimeUtil.*;

import java.time.LocalDateTime;
import java.util.Set;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import net.devpg.rpinder.batch.collector.extractor.Extractor;
import net.devpg.rpinder.batch.collector.vo.GoldPrice;

@Service
@RequiredArgsConstructor
public class GoldPriceExtractService {
    private final Set<Extractor<Document, GoldPrice>> extractorSet;

    public GoldPrice extract(Document document) {
        GoldPrice goldPrice = new GoldPrice(LocalDateTime.now(ZONE_ID).format(DATE_TIME_FORMATTER));
        extractorSet.forEach(e -> e.extract(document, goldPrice));
        return goldPrice;
    }
}
