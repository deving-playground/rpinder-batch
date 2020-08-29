package net.devpg.rpinder.batch.common.config;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.TestPropertySource;

@IfProfileValue(name = "spring.profiles.active", value = "local")
@SpringBootTest
@TestPropertySource("classpath:application.yml")
class ElasticsearchConfigTest {
    @Autowired
    private ElasticsearchConfig elasticsearchConfig;

    @Test
    @DisplayName("ElasticSearch 설정을 읽어옵니다.")
    void elasticsearch_config_load_test() {
        // given
        List<String> expectedHosts = Collections.singletonList("localhost:9200");
        String expectedIndex = "ring-price";
        String expectedType = "_doc";

        // when
        List<String> hosts = elasticsearchConfig.getHost();
        IndicesConfig indices = elasticsearchConfig.getIndices();

        // then
        assertAll(
            () -> assertEquals(expectedHosts, hosts),
            () -> assertEquals(expectedIndex, indices.getIndex()),
            () -> assertEquals(expectedType, indices.getType())
        );
    }
}