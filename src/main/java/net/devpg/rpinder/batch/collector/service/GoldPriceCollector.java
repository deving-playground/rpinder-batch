package net.devpg.rpinder.batch.collector.service;

import java.util.Map;
import java.util.Optional;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import net.devpg.rpinder.batch.collector.model.GoldPriceInfo;
import net.devpg.rpinder.batch.common.constant.TransactionType;

@RequiredArgsConstructor
@Service("GoldPriceCollectorForTask")
public class GoldPriceCollector implements Collector<Map<TransactionType, GoldPriceInfo>> {
    private final HTMLCrawler htmlCrawler;
    private final Extractor<Map<TransactionType, GoldPriceInfo>> documentExtractor;

    @Value("${collect.url}")
    private String targetUrl;

    @Override
    public Map<TransactionType, GoldPriceInfo> collect() {
        Optional<Document> doc = htmlCrawler.take(targetUrl);
        return documentExtractor.extract(doc);
    }
}
