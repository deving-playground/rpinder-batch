package net.devpg.rpinder.batch.collector.writer;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import net.devpg.rpinder.batch.collector.model.StoreData;

@Slf4j
@Component
public class GoldPriceIndexer implements BaseIndexer {

	@Override
	public void write(List<? extends StoreData> items) throws Exception {
		log.info("===== write =====");
		items.forEach(i -> log.info("WRITER: {}", (i).getData()));
		System.exit(0);
	}
}
