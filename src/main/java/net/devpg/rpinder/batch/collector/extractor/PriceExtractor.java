package net.devpg.rpinder.batch.collector.extractor;

public interface PriceExtractor<T, R> {
    R extract(T t);
}
