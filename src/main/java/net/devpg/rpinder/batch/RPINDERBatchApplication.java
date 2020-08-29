package net.devpg.rpinder.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import net.devpg.rpinder.batch.common.config.ElasticsearchConfig;

@EnableConfigurationProperties(ElasticsearchConfig.class)
@EnableBatchProcessing
@SpringBootApplication
public class RPINDERBatchApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RPINDERBatchApplication.class, args);
        SpringApplication.exit(context);
    }

}
