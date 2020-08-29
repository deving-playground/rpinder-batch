package net.devpg.rpinder.batch.collector.tasklet;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class GoldPriceCollectingTasklet implements Tasklet, StepExecutionListener {
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
		return RepeatStatus.FINISHED;
	}
}
