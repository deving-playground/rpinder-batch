package net.devpg.rpinder.batch.collector.model;

public interface CollectData<E> {
	void setData(E e);

	E getData();
}
