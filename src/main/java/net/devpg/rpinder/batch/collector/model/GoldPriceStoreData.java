package net.devpg.rpinder.batch.collector.model;

import lombok.ToString;

@ToString
public class GoldPriceStoreData<E> implements StoreData<E> {
	private E e;

	@Override
	public void setData(E e) {
		this.e = e;
	}

	@Override
	public E getData() {
		return e;
	}
}
