package net.devpg.rpinder.batch.collector;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Setter;

@Setter
@Component
@ConfigurationProperties(prefix = "app.crawl.pattern")
public class CrawlingPattern {
    private String common;
    private GoldPriceTypePattern purchase;
    private GoldPriceTypePattern sell;

    public String common() {
        return common;
    }

    public GoldPriceTypePattern purchase() {
        return purchase;
    }

    public GoldPriceTypePattern sell() {
        return sell;
    }

}
