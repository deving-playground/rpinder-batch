package net.devpg.rpinder.batch.collector.parser;

import net.devpg.rpinder.batch.collector.vo.GoldPrice;

public interface GoldPriceDocumentParser<T> {
	GoldPrice parse(T document);
}
