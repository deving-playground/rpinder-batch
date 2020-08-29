package net.devpg.rpinder.batch.collector.service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import net.devpg.rpinder.batch.collector.model.GoldPriceInfo;
import net.devpg.rpinder.batch.collector.vo.GoldPriceByType;
import net.devpg.rpinder.batch.collector.vo.Price;
import net.devpg.rpinder.batch.common.config.JsoupConfig;
import net.devpg.rpinder.batch.common.constant.TransactionType;
import net.devpg.rpinder.batch.common.exception.IllegalCollectedDateException;
import net.devpg.rpinder.batch.common.util.DateCustomUtility;

@Slf4j
@Service
@RequiredArgsConstructor
public class JsoupDocumentExtractor implements Extractor<Map<TransactionType, GoldPriceInfo>> {
    private final JsoupConfig jsoupConfig;

    @Override
    public Map<TransactionType, GoldPriceInfo> extract(Optional<Document> document) {
        Map<TransactionType, GoldPriceInfo> resultMap = Maps.newEnumMap(TransactionType.class);
        document.ifPresent(doc -> bindMap(doc, resultMap));
        return resultMap;
    }

    public void bindMap(Document doc, Map<TransactionType, GoldPriceInfo> resultMap) {
        Element dateElementOfCollectedTarget = extractDate(doc);

        if (validateDate(dateElementOfCollectedTarget.text())) {
            throw new IllegalCollectedDateException("수집을 시작한 날짜와 수집 대상 날짜가 동일하지 않습니다.");
        }

        Element buyPriceOfPureGoldElement = doc.select(jsoupConfig.getTodayGoldPriceQuery()).get(0);
        GoldPriceInfo goldPriceInfo = extractTodayGoldPriceInfo(buyPriceOfPureGoldElement);

        resultMap.put(TransactionType.PURCHASE, goldPriceInfo);
        log.info("collected resultMap => {}", resultMap);
    }

    private boolean validateDate(String dateOfCollectedTarget) {
        LocalDate targetDate = DateCustomUtility.valueOf(dateOfCollectedTarget);
        log.debug("currentDate: {}", DateCustomUtility.getCurrentDate());
        log.debug("targetDate: {}", targetDate);
        return DateCustomUtility.getCurrentDate().isEqual(targetDate);
    }

    private Element extractDate(Document doc) {
        return doc.select(jsoupConfig.getDateQuery().get(0))
            .select(jsoupConfig.getDateQuery().get(1))
            .get(1);
    }

    private GoldPriceInfo extractTodayGoldPriceInfo(Element buyPriceOfPureGoldElement) {
        log.debug("buyPriceOfPureGoldElement.text() => {} ", buyPriceOfPureGoldElement.text());

        String priceOf24KAsString = buyPriceOfPureGoldElement.text().replace(",", "");
        Price priceOf24K = Price.from(priceOf24KAsString);

        GoldPriceByType goldPriceByType = GoldPriceByType.as24kFrom(priceOf24K);
        log.info("collected goldPriceByType => {}", goldPriceByType);

        return GoldPriceInfo.of(
            DateCustomUtility.getCurrentDate().toString(),
            DateCustomUtility.getCurrentDateTimeAsString(),
            goldPriceByType);
    }
}
