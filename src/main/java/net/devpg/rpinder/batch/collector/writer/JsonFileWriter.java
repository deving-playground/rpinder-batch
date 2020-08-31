package net.devpg.rpinder.batch.collector.writer;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import net.devpg.rpinder.batch.collector.vo.GoldPrice;

@Slf4j
@Service
@RequiredArgsConstructor
public class JsonFileWriter {
    private final Gson gson;

    @Value("${app.crawl.store.directory}")
    private String storeDirectory;

    public void write(GoldPrice goldPrice) {
        File file = new File(storeDirectory);
        if (!file.exists()) {
            boolean success = file.mkdir();
            if (!success) {
                log.error("fail to make directory");
            }
        }
        try (FileWriter fileWriter = new FileWriter(file.getPath() + "/" + todayFileName())) {
            fileWriter.write(gson.toJson(goldPrice));
        } catch (Exception e) {
            log.error("fail to write file", e);
        }
    }

    private String todayFileName() {
        LocalDateTime todayDate = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        return todayDate.format(DateTimeFormatter.ISO_DATE).concat(".json");
    }
}
