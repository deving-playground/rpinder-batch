package net.devpg.rpinder.batch.collector.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@ToString
@Accessors(fluent = true)
@AllArgsConstructor(staticName = "from")
public class GoldPriceHtml {
	private String contents;
}
