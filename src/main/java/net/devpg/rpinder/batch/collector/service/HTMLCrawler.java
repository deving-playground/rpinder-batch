package net.devpg.rpinder.batch.collector.service;

import java.io.IOException;
import java.util.Optional;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HTMLCrawler {
    public Optional<Document> take(String url) {
        try {
            Connection.Response response = Jsoup.connect(url)
                .method(Connection.Method.GET)
                .timeout(3_000)
                .execute();

            Assert.state(response.statusCode() / 200 == 1,
                String.format("Failed to get normal response status(%d)..!", response.statusCode()));

            Document doc = response.charset("euc-kr").parse();
            Assert.notNull(doc, "Failed to parse response into document..!");

            log.debug("doc => {}", doc);
            return Optional.of(doc);
        } catch (IOException e) {
            log.error("An error occurred while connecting url on Jsoup..!", e);
        }
        return Optional.empty();
    }
}
