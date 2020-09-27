package net.devpg.rpinder.batch.collector.extractor;

import java.util.Optional;

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
import net.devpg.rpinder.batch.util.GoldPriceCalculator;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoldPurchasePriceExtractor implements Extractor<Document, GoldPrice> {
    private final CrawlingPattern crawlingPattern;

    @Override
    public void extract(Document document, GoldPrice goldPrice) {
        //Common Pattern
        Elements commonElement = document.select(crawlingPattern.common());

        //Extract Purchase Price
        GoldPriceByType goldPriceByPurchase = extractGoldPriceByPurchase(commonElement);

        goldPrice.priceOnPurchase(goldPriceByPurchase);
    }

    private GoldPriceByType extractGoldPriceByPurchase(Elements commonElement) {
        return Optional.of(commonElement.select(crawlingPattern.purchase().gold24k()))
            .filter(elements -> !Strings.isBlank(elements.text()))
            .map(elements -> BigDecimalUtil.valueOf(elements.text().replace(",", "")))
            .map(priceOf24k -> GoldPriceByType.of(
                Price.from(priceOf24k),
                Price.from(GoldPriceCalculator.calculate18k(priceOf24k)),
                Price.from(GoldPriceCalculator.calculate14k(priceOf24k))))
            .orElseThrow(() -> new RuntimeException("The purchase price does not exist"));
    }
}
