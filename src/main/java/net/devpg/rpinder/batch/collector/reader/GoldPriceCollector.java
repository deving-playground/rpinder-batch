package net.devpg.rpinder.batch.collector.reader;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import net.devpg.rpinder.batch.collector.model.CollectData;
import net.devpg.rpinder.batch.collector.model.GoldPriceHtmlCollectData;

@Slf4j
@Component
public class GoldPriceCollector implements BaseCollector {
	@Override
	public CollectData collect() {
		log.info("===== Execute Collect Step");
		return GoldPriceHtmlCollectData.newInstance();
	}
}
