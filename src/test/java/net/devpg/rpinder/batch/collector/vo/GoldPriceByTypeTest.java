package net.devpg.rpinder.batch.collector.vo;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("유형별 금값(GoldPriceByType) 클래스")
class GoldPriceByTypeTest {

    @Test
    @DisplayName("순금 금 값으로 나머지 금 값을 계산합니다.")
    void test_to_calculate_gold_price() {
        // given
        Price priceOf24K = Price.from("283000");

        // when
        GoldPriceByType goldPriceByType = GoldPriceByType.as24kFrom(priceOf24K);

        // then
        assertAll(
            () -> assertEquals(BigDecimal.valueOf(283_000), goldPriceByType.priceOf24K().value()),
            () -> assertEquals(BigDecimal.valueOf(233_475), goldPriceByType.priceOf18K().value()),
            () -> assertEquals(BigDecimal.valueOf(182_110), goldPriceByType.priceOf14K().value()),

            () -> assertEquals(Price.from("283000"), goldPriceByType.priceOf24K()),
            () -> assertEquals(Price.from("233475"), goldPriceByType.priceOf18K()),
            () -> assertEquals(Price.from("182110"), goldPriceByType.priceOf14K()),

            () -> assertEquals(
                GoldPriceByType.of(Price.from("283000"), Price.from("233475"), Price.from("182110")),
                goldPriceByType
            )
        );

    }
}