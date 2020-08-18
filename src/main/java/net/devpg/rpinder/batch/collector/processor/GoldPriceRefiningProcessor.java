package net.devpg.rpinder.batch.collector.processor;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import net.devpg.rpinder.batch.collector.model.CollectData;
import net.devpg.rpinder.batch.collector.model.GoldPriceStoreData;
import net.devpg.rpinder.batch.collector.model.StoreData;

@Slf4j
@Component
public class GoldPriceRefiningProcessor implements BaseRefiningProcessor {
	@Override
	public StoreData process(CollectData item) throws Exception {
		log.info("===== process =====");
		return GoldPriceStoreData.newInstance();
	}
}
