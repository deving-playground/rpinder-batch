package net.devpg.rpinder.batch.collector.reader;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import net.devpg.rpinder.batch.collector.model.CollectData;
import net.devpg.rpinder.batch.collector.model.GoldPriceHtmlCollectData;
import net.devpg.rpinder.batch.collector.vo.GoldPriceHtml;

@Slf4j
@Component
public class GoldPriceCollector implements BaseCollector<CollectData<GoldPriceHtml>> {

	@Override
	public CollectData<GoldPriceHtml> collect() {
		log.info("==[ 1단계 : 수집]== Execute Collect, Reader Step!");
		CollectData<GoldPriceHtml> collectedData = new GoldPriceHtmlCollectData<>();
		collectedData.setData(GoldPriceHtml.from("<html><body>Hello World..!</body></html>"));
		return collectedData;
	}
}
