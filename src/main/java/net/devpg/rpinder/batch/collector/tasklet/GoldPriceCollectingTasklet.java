package net.devpg.rpinder.batch.collector.tasklet;

import org.jsoup.nodes.Document;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import net.devpg.rpinder.batch.collector.parser.JsoupParser;
import net.devpg.rpinder.batch.collector.service.GoldPriceExtractService;
import net.devpg.rpinder.batch.collector.validator.DailyGoldPriceDateValidator;
import net.devpg.rpinder.batch.collector.vo.GoldPrice;
import net.devpg.rpinder.batch.collector.writer.JsonFileWriter;

@Slf4j
@Component
@RequiredArgsConstructor
public class GoldPriceCollectingTasklet implements Tasklet, StepExecutionListener {
    @Value("${app.crawl.site}")
    private String crawlSite;
    private final GoldPriceExtractService goldPriceExtractService;
    private final DailyGoldPriceDateValidator priceDateValidator;
    private final JsonFileWriter jsonFileWriter;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("Collect gold price task start.");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("Collect gold price task finish.");
        return ExitStatus.COMPLETED;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        //TODO CHECK Weekend return RepeatStatus.FINISHED;

        //jsoup 수집
        Document crawlingDocument = JsoupParser.getDocument(crawlSite);
        if (crawlingDocument == null) {
            log.error("document is null");
            return RepeatStatus.FINISHED;
        }
        //날짜 유효성 체크
        if (!priceDateValidator.isToday(crawlingDocument)) {
            return RepeatStatus.FINISHED;
        }

        //필요한 데이터만 추출
        GoldPrice goldPrice = goldPriceExtractService.extract(crawlingDocument);

        //파일로 적재
        jsonFileWriter.write(goldPrice);
        return RepeatStatus.FINISHED;
    }
}
