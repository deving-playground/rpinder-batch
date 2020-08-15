package net.devpg.rpinder.batch.collector.model;

import lombok.NoArgsConstructor;
import lombok.ToString;

import net.devpg.rpinder.batch.collector.vo.GoldPriceHtml;

@ToString
@NoArgsConstructor(staticName = "newInstance")
public class GoldPriceHtmlCollectData implements CollectData<GoldPriceHtml> {
	private GoldPriceHtml goldPriceHtml;

	@Override
	public void setData(GoldPriceHtml goldPriceHtml) {
		this.goldPriceHtml = goldPriceHtml;
	}

	@Override
	public GoldPriceHtml getData() {
		return goldPriceHtml;
	}
}
