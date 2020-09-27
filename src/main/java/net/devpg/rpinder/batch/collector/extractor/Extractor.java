package net.devpg.rpinder.batch.collector.extractor;

public interface Extractor<T, R> {
    void extract(T t,R r);
}
