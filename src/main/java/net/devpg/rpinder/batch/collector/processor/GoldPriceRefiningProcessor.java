package net.devpg.rpinder.batch.collector.processor;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import net.devpg.rpinder.batch.collector.model.CollectData;
import net.devpg.rpinder.batch.collector.model.GoldPriceStoreData;
import net.devpg.rpinder.batch.collector.model.StoreData;
import net.devpg.rpinder.batch.collector.vo.GoldPrice;
import net.devpg.rpinder.batch.collector.vo.GoldPriceByType;
import net.devpg.rpinder.batch.collector.vo.GoldPriceHtml;
import net.devpg.rpinder.batch.collector.vo.Price;

@Slf4j
@RequiredArgsConstructor
@Component("goldPriceRefiningProcessor")
public class GoldPriceRefiningProcessor
	implements BaseRefiningProcessor<CollectData<GoldPriceHtml>, StoreData<GoldPrice>> {
	private final HtmlParser htmlParser;

	@Override
	public StoreData<GoldPrice> process(CollectData<GoldPriceHtml> item) throws Exception {
		GoldPriceHtml goldPriceHtml = item.getData();
		String parsedHtml = htmlParser.parse(goldPriceHtml.contents());
		log.info("==[ 2단계 : 정제]== Execute Refine, Processor Step!");
		log.info("--CHECK: parsedHtml => {}", parsedHtml);
		return getRefinedDataAsGoldPrice();
	}

	private StoreData<GoldPrice> getRefinedDataAsGoldPrice() {
		StoreData<GoldPrice> goldPriceStoreData = new GoldPriceStoreData<>();

		GoldPriceByType priceOnPurchase = GoldPriceByType.of(Price.from(BigDecimal.valueOf(24L)),
			Price.from(BigDecimal.valueOf(18L)), Price.from(BigDecimal.valueOf(14L)));
		GoldPriceByType priceOnSell = GoldPriceByType.of(Price.from(BigDecimal.valueOf(24L)),
			Price.from(BigDecimal.valueOf(18L)), Price.from(BigDecimal.valueOf(14L)));

		goldPriceStoreData.setData(GoldPrice.of(priceOnPurchase, priceOnSell));

		return goldPriceStoreData;
	}
}
