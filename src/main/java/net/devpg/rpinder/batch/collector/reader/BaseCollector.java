package net.devpg.rpinder.batch.collector.reader;

import net.devpg.rpinder.batch.collector.model.CollectData;

public interface BaseCollector<E extends CollectData> {
	E collect();
}
