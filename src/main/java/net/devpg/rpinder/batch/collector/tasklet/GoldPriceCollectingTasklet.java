package net.devpg.rpinder.batch.collector.tasklet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import net.devpg.rpinder.batch.collector.parser.GoldPriceDocumentParser;
import net.devpg.rpinder.batch.collector.writer.GoldPriceFileWriter;

@Slf4j
@Component
@RequiredArgsConstructor
public class GoldPriceCollectingTasklet implements Tasklet, StepExecutionListener {
	private final GoldPriceDocumentParser<Document> goldPriceDocumentParser;
	private final GoldPriceFileWriter goldPriceFileWriter;

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
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		Document document = Jsoup.connect("http://goldgold.co.kr/charts/1_price1.php").get();
		goldPriceFileWriter.write(goldPriceDocumentParser.parse(document));
		return RepeatStatus.FINISHED;
	}


}
