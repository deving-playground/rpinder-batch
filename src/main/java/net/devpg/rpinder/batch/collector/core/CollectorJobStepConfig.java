package net.devpg.rpinder.batch.collector.core;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

import net.devpg.rpinder.batch.collector.model.CollectData;
import net.devpg.rpinder.batch.collector.model.StoreData;
import net.devpg.rpinder.batch.collector.processor.BaseRefiningProcessor;
import net.devpg.rpinder.batch.collector.reader.BaseCollector;
import net.devpg.rpinder.batch.collector.tasklet.CloseResourceTasklet;
import net.devpg.rpinder.batch.collector.vo.GoldPrice;
import net.devpg.rpinder.batch.collector.vo.GoldPriceHtml;
import net.devpg.rpinder.batch.collector.writer.BaseIndexer;

@EnableBatchProcessing
@Configuration
@RequiredArgsConstructor
public class CollectorJobStepConfig {
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final BaseCollector<CollectData<GoldPriceHtml>> goldPriceCollector;
	private final BaseRefiningProcessor<CollectData<GoldPriceHtml>, StoreData<GoldPrice>> goldPriceRefiningProcessor;
	private final BaseIndexer<StoreData<GoldPrice>> goldPriceIndexer;
	private final CloseResourceTasklet closeResourceTasklet;

	@Bean
	public Job collectorJob() {
		return jobBuilderFactory.get("collectorJob")
			.start(collectorStep())
			.next(closeResourceStep())
			// .next(CloseResourceStep())
			.build();
	}

	@Bean
	public Step collectorStep() {
		return stepBuilderFactory.get("collectorStep")
			.<CollectData<GoldPriceHtml>, StoreData<GoldPrice>>chunk(1)
			.reader(goldPriceCollector::collect)
			.processor(goldPriceRefiningProcessor)
			.writer(goldPriceIndexer)
			.build();
	}

	@Bean
	public Step closeResourceStep() {
		return stepBuilderFactory.get("closeResourceStep")
			.tasklet(closeResourceTasklet)
			.build();
	}
}
