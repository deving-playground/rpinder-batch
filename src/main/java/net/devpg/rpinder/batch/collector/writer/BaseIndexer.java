package net.devpg.rpinder.batch.collector.writer;

import org.springframework.batch.item.ItemWriter;

import net.devpg.rpinder.batch.collector.model.StoreData;

public interface BaseIndexer extends ItemWriter<StoreData> {
}
