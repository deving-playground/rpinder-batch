package net.devpg.rpinder.batch.common.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class IndicesConfig {
    private final String index;
    private final String type;
}
