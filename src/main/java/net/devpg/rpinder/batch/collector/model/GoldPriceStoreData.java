package net.devpg.rpinder.batch.collector.model;

import lombok.NoArgsConstructor;
import lombok.ToString;

import net.devpg.rpinder.batch.collector.vo.GoldPrice;

@ToString
@NoArgsConstructor(staticName = "newInstance")
public class GoldPriceStoreData implements StoreData<GoldPrice> {
	private GoldPrice goldPrice;

	@Override
	public void setData(GoldPrice goldPrice) {
		this.goldPrice = goldPrice;
	}

	@Override
	public GoldPrice getData() {
		return goldPrice;
	}
}
