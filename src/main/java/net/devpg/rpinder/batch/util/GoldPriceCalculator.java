package net.devpg.rpinder.batch.util;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import net.devpg.rpinder.batch.collector.vo.Price;

@NoArgsConstructor(access = AccessLevel.NONE)
public class GoldPriceCalculator {
    private final static BigDecimal weightOf18k = BigDecimal.valueOf(0.825);
    private final static BigDecimal weightOf14k = BigDecimal.valueOf(0.6435);

    public static BigDecimal calculate18k(@NonNull BigDecimal priceOf24k) {
        return priceOf24k.multiply(weightOf18k);
    }

    public static BigDecimal calculate14k(@NonNull BigDecimal priceOf24k) {
        return priceOf24k.multiply(weightOf14k);
    }
}
