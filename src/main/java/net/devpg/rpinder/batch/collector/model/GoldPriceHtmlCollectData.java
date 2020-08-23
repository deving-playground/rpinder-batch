package net.devpg.rpinder.batch.collector.model;

import lombok.ToString;

@ToString
public class GoldPriceHtmlCollectData<E> implements CollectData<E> {
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
