package net.devpg.rpinder.batch.collector.writer;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import net.devpg.rpinder.batch.collector.model.StoreData;
import net.devpg.rpinder.batch.collector.vo.GoldPrice;

@Slf4j
@Component
public class GoldPriceIndexer implements BaseIndexer<StoreData<GoldPrice>> {

	@Override
	public void write(List<? extends StoreData<GoldPrice>> items) throws Exception {
		log.info("==[ 3단계 : 색인]== Execute Index, Writer Step!");
		items.forEach(i -> log.info("--CHECK: Index Data: {}", (i).getData()));
		/*
		 * TODO: 데이터 수집 후 NULL 을 반환하기 전까지 read-process-write 가 반복됩니다.
		 * - 특정 범위(Range)에 대한 반복적 읽기가 아니기 때문에 해당 배치 구조와 맞지 않다는 생각이 듭니다.
		 * - tasklet 배치구조로 변경 할지 아니면 batch 가 아닌 일반 web 이 빠진 boot 로만 구현할지 검토가 필요합니다.
		 * - 임시로 강제 종료 합니다.
		 */
		System.exit(0);
	}
}
