package net.devpg.rpinder.batch.collector.parser;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

import net.devpg.rpinder.batch.collector.vo.GoldPrice;
import net.devpg.rpinder.batch.collector.vo.GoldPriceByType;
import net.devpg.rpinder.batch.collector.vo.Price;

@Slf4j
@Component
public class JsoupGoldPriceDocumentParser implements GoldPriceDocumentParser<Document> {

	@Override
	public GoldPrice parse(Document document) {
		Elements baseElement = document.body().select("table > tbody");
		return GoldPrice.of(parsePurchaseOnPrice(baseElement), parsePriceOnSell(baseElement));
	}

	private GoldPriceByType parsePurchaseOnPrice(Elements baseElement) {
		Elements priceOnPurchaseElement = baseElement.select("tr:nth-child(7) > td > table > tbody")
			.select("tr:nth-child(5)");
		BigDecimal purchasePriceOf24K = getPrice(priceOnPurchaseElement.select("tr:nth-child(5) > td:nth-child(8) > b"));
		BigDecimal purchasePriceOf18K = purchasePriceOf24K.multiply(BigDecimal.valueOf(0.825));
		BigDecimal purchasePriceOf14K = purchasePriceOf24K.multiply(BigDecimal.valueOf(0.6435));
		return GoldPriceByType.of(Price.from(purchasePriceOf24K), Price.from(purchasePriceOf18K), Price.from(purchasePriceOf14K));
	}

	private GoldPriceByType parsePriceOnSell(Elements baseElement) {
		Elements priceOnSellBaseElement = baseElement.select("tr:nth-child(9) > td > table > tbody");
		BigDecimal sellPriceOf24KSource = getPrice(priceOnSellBaseElement.select("tr:nth-child(3) > td:nth-child(8) > b"));
		BigDecimal sellPriceOf18KSource = getPrice(priceOnSellBaseElement.select("tr:nth-child(5) > td:nth-child(8)"));
		BigDecimal sellPriceOf14KSource = getPrice(priceOnSellBaseElement.select("tr:nth-child(7) > td:nth-child(8)"));
		return GoldPriceByType.of(Price.from(sellPriceOf24KSource), Price.from(sellPriceOf18KSource),
			Price.from(sellPriceOf14KSource));
	}

	private BigDecimal getPrice(Elements elements) {
		String text = elements.text();
		if (StringUtils.isEmpty(text)) {
			return BigDecimal.valueOf(-1L);
		}
		return BigDecimal.valueOf(Long.parseLong(text.replace(",", "")));
	}


}
