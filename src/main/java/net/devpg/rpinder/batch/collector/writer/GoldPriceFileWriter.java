package net.devpg.rpinder.batch.collector.writer;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import net.devpg.rpinder.batch.collector.vo.GoldPrice;

@Slf4j
@Component
@RequiredArgsConstructor
public class GoldPriceFileWriter {
	private static final ObjectMapper OM = new ObjectMapper();

	public void write(GoldPrice goldPrice) throws Exception {
		File savePath = new File("./data");
		if (!savePath.exists()) {
			if (!savePath.mkdirs()) {
				throw new IllegalStateException("Fail to create collector save directory");
			}
		}

		LocalDateTime now = LocalDateTime.now();
		Map<String, Object> o = new HashMap<>();
		o.put("date", now.format(DateTimeFormatter.ISO_LOCAL_DATE));
		o.put("collectDateTime", now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		o.put("goldPriceOnPurchase", goldPrice.priceOnPurchase());
		OM.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		String data = OM.writeValueAsString(o);
		Files.writeString(new File(savePath, generateFileNameByTime(now)).toPath(), data);
	}

	private String generateFileNameByTime(LocalDateTime timestamp) {
		return String.format("%s.json", timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	}

}
