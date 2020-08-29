package net.devpg.rpinder.batch.common.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "collect.query")
@Getter
@Setter
public class JsoupConfig {
    private List<String> dateQuery;
    private String todayGoldPriceQuery;
}
