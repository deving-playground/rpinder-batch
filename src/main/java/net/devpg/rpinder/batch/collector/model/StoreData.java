package net.devpg.rpinder.batch.collector.model;

public interface StoreData<E> {
	void setData(E e);

	E getData();
}
