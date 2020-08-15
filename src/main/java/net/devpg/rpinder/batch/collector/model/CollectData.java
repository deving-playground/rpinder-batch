package net.devpg.rpinder.batch.collector.model;

public interface CollectData<T> {
	void setData(T t);

	T getData();
}
