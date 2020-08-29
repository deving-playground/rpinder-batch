package net.devpg.rpinder.batch.collector.service;

import java.util.Optional;

import org.jsoup.nodes.Document;

public interface Extractor<E> {
    // TODO: lint 에 걸린 이유 확인이 필요합니다.
    E extract(Optional<Document> document);
}
