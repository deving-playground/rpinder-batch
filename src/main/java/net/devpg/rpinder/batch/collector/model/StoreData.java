package net.devpg.rpinder.batch.collector.model;

public interface StoreData<T> {
	void setData(T t);

	T getData();
}
