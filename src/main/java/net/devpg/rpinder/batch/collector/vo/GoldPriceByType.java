package net.devpg.rpinder.batch.collector.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@ToString
@Accessors(fluent = true)
@AllArgsConstructor(staticName = "of")
public class GoldPriceByType {
	private final Price priceOf24K;
	private final Price priceOf18K;
	private final Price priceOf14K;
}
