package net.devpg.rpinder.batch.collector.core;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import net.devpg.rpinder.batch.collector.core.strategy.step.tasklet.CloseResourceTasklet;
import net.devpg.rpinder.batch.collector.core.strategy.step.tasklet.GoldPriceCollectingTasklet;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CollectorJobStepConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final GoldPriceCollectingTasklet goldPriceCollectingTasklet;
    private final CloseResourceTasklet closeResourceTasklet;

    @Bean
    public Job collectorJob() {
        return jobBuilderFactory.get("collectorJob")
            .start(collectStep())
            .next(closeStep())
            .build();
    }

    @Bean
    public Step collectStep() {
        return stepBuilderFactory.get("collectStep")
            .tasklet(goldPriceCollectingTasklet)
            .build();
    }

    @Bean
    public Step closeStep() {
        return stepBuilderFactory.get("closeStep")
            .tasklet(closeResourceTasklet)
            .build();
    }

}
