package net.devpg.rpinder.batch.collector.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@ToString
@Accessors(fluent = true)
@AllArgsConstructor(staticName = "of")
public class GoldPrice {
	private final GoldPriceByType priceOnPurchase;
	private final GoldPriceByType priceOnSell;
}
