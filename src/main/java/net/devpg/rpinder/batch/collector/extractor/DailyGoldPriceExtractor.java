package net.devpg.rpinder.batch.collector.extractor;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import net.devpg.rpinder.batch.collector.CrawlingPattern;
import net.devpg.rpinder.batch.collector.vo.GoldPrice;
import net.devpg.rpinder.batch.collector.vo.GoldPriceByType;
import net.devpg.rpinder.batch.collector.vo.Price;
import net.devpg.rpinder.batch.util.BigDecimalUtil;

@Slf4j
@Service
@RequiredArgsConstructor
public class DailyGoldPriceExtractor implements PriceExtractor<Document, GoldPrice> {
    private final CrawlingPattern crawlingPattern;

    @Override
    public GoldPrice extract(Document document) {
        //Common Pattern
        Elements commonElement = document.select(crawlingPattern.common());

        //Extract Purchase Price
        GoldPriceByType goldPriceByPurchase = extractGoldPriceByPurchase(commonElement);
        //Extract Sell Price
        GoldPriceByType goldPriceBySell = extractGoldPriceBySell(commonElement);

        return GoldPrice.of(goldPriceByPurchase, goldPriceBySell);
    }

    private GoldPriceByType extractGoldPriceBySell(Elements commonElement) {
        final BigDecimal MINUS_ONE = BigDecimal.valueOf(-1L);
        List<Price> goldPriceList = Optional.ofNullable(crawlingPattern.sell().golds())
            .orElse(Collections.emptyList())
            .stream()
            .map(pattern -> Optional.ofNullable(commonElement.select(pattern))
                .filter(elements -> !Strings.isBlank(elements.text()))
                .map(elements -> Price.from(BigDecimalUtil.valueOf(elements.text().replace(",", ""))))
                .orElse(Price.from(MINUS_ONE)))
            .collect(Collectors.toList());
        if (goldPriceList.isEmpty() || goldPriceList.stream()
            .anyMatch(price -> price.value().compareTo(MINUS_ONE) == 0))
            throw new RuntimeException("Part of the selling price does not exist");
        return GoldPriceByType.of(goldPriceList.get(0), goldPriceList.get(1), goldPriceList.get(2));
    }

    private GoldPriceByType extractGoldPriceByPurchase(Elements commonElement) {
        return Optional.of(commonElement.select(crawlingPattern.purchase().gold24k()))
            .filter(elements -> !Strings.isBlank(elements.text()))
            .map(elements -> GoldPriceByType.of(
                Price.from(BigDecimalUtil.valueOf(elements.text().replace(",", ""))), null, null))
            .orElseThrow(() -> new RuntimeException("The purchase price does not exist"));
    }
}
