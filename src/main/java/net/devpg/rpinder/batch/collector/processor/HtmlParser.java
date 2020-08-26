package net.devpg.rpinder.batch.collector.processor;

import org.springframework.stereotype.Service;

@Service
class HtmlParser {
	String parse(String htmlDocument) {
		return String.format("%s is Parsed", htmlDocument);
	}
}
