package net.devpg.rpinder.batch.collector.extractor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import net.devpg.rpinder.batch.collector.CrawlingPattern;
import net.devpg.rpinder.batch.collector.extractor.PriceExtractor;
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
        Elements sellCommonElement = commonElement.select(crawlingPattern.sell().common());
        List<Price> goldPriceList = Optional.ofNullable(crawlingPattern.sell().golds())
            .orElse(Collections.emptyList())
            .stream()
            .map(pattern -> {
                Elements patternElement = sellCommonElement.select(pattern);
                return Price.from(BigDecimalUtil.valueOf(patternElement.text().replace(",", "")));
            })
            .collect(Collectors.toList());
        return goldPriceList.isEmpty() ? null :
            GoldPriceByType.of(goldPriceList.get(0), goldPriceList.get(1), goldPriceList.get(2));
    }

    private GoldPriceByType extractGoldPriceByPurchase(Elements commonElement) {
        Elements elements = commonElement.select(crawlingPattern.purchase().gold24k());
        return GoldPriceByType.of(
            Price.from(BigDecimalUtil.valueOf(elements.text().replace(",", ""))), null, null);
    }
}
