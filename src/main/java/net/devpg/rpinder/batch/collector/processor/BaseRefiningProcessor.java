package net.devpg.rpinder.batch.collector.processor;

import org.springframework.batch.item.ItemProcessor;

import net.devpg.rpinder.batch.collector.model.CollectData;
import net.devpg.rpinder.batch.collector.model.StoreData;

public interface BaseRefiningProcessor<E extends CollectData, R extends StoreData> extends ItemProcessor<E, R> {
}
