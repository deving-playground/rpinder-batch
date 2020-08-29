package net.devpg.rpinder.batch.collector.core.strategy.step.tasklet;

import java.util.Map;

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

import net.devpg.rpinder.batch.collector.model.GoldPriceInfo;
import net.devpg.rpinder.batch.collector.service.Collector;
import net.devpg.rpinder.batch.collector.service.FileWriter;
import net.devpg.rpinder.batch.common.constant.TransactionType;

@Slf4j
@RequiredArgsConstructor
@Component
public class GoldPriceCollectingTasklet implements Tasklet, StepExecutionListener {

    private final Collector<Map<TransactionType, GoldPriceInfo>> goldPriceCollector;
    private final FileWriter fileWriter;
    private final ObjectMapper objectMapper;

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
        Map<TransactionType, GoldPriceInfo> resultMap = goldPriceCollector.collect();

        GoldPriceInfo goldPriceInfo = resultMap.get(TransactionType.PURCHASE);
        log.info("====Collected Data: {}", goldPriceInfo);

        String result = objectMapper.writeValueAsString(goldPriceInfo);
        log.info("json => {}", result);

        fileWriter.write(result);

        return RepeatStatus.FINISHED;
    }
}
