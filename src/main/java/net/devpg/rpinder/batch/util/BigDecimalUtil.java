package net.devpg.rpinder.batch.util;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public class BigDecimalUtil {
    public static BigDecimal valueOf(String value) {
        return BigDecimal.valueOf(Long.parseLong(value));
    }
}
