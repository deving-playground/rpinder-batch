package net.devpg.rpinder.batch.collector.vo;

import java.math.BigDecimal;

import org.springframework.util.Assert;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@ToString
@EqualsAndHashCode
@Accessors(fluent = true)
public class Price {
    private final BigDecimal value;

    public static Price from(String value) {
        Assert.notNull(value, "Illegal String Parameter..(Price.from(null))");
        return from(Long.valueOf(value));
    }

    public static Price from(Long value) {
        Assert.notNull(value, "Illegal Long Parameter..(Price.from(null))");
        return from(BigDecimal.valueOf(value));
    }

    public static Price from(BigDecimal value) {
        Assert.notNull(value, "Illegal BigDecimal Parameter..(Price.from(null))");
        return new Price(value);
    }

    private Price(BigDecimal value) {
        this.value = value;
    }
}
