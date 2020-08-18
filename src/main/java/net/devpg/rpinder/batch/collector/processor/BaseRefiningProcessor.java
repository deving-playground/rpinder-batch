package net.devpg.rpinder.batch.collector.processor;

import org.springframework.batch.item.ItemProcessor;

import net.devpg.rpinder.batch.collector.model.CollectData;
import net.devpg.rpinder.batch.collector.model.StoreData;

public interface BaseRefiningProcessor extends ItemProcessor<CollectData, StoreData> {
}
