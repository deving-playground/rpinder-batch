package net.devpg.rpinder.batch.collector.vo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Function;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

import net.devpg.rpinder.batch.collector.model.GoldPriceInfo;

@Getter
@ToString
@EqualsAndHashCode
@Accessors(fluent = true)
public class GoldPriceByType {
    private final Price priceOf24K;
    private final Price priceOf18K;
    private final Price priceOf14K;

    // TODO: 일급 컬렉션을 쓰게 되면서 serialize 시 value 가 나와 아래와 같이 조치하였으나 후속 조치가 필요합니다.
    @JsonView(GoldPriceInfo.class)
    private BigDecimal getPriceOf24K() {
        return priceOf24K.value();
    }

    @JsonView(GoldPriceInfo.class)
    private BigDecimal getPriceOf18K() {
        return priceOf18K.value();
    }

    @JsonView(GoldPriceInfo.class)
    private BigDecimal getPriceOf14K() {
        return priceOf14K.value();
    }

    public static GoldPriceByType as24kFrom(Price priceOf24K) {
        BigDecimal priceOf18kAsBigDecimal = calculate(0.825).apply(priceOf24K.value());
        BigDecimal priceOf14kAsBigDecimal = calculate(0.6435).apply(priceOf24K.value());
        return of(priceOf24K, Price.from(priceOf18kAsBigDecimal), Price.from(priceOf14kAsBigDecimal));
    }

    private static Function<BigDecimal, BigDecimal> calculate(double multiplyValue) {
        return x -> {
            BigDecimal multipliedX = x.multiply(BigDecimal.valueOf(multiplyValue));
            return cutDecimalPoint().apply(multipliedX);
        };
    }

    private static Function<BigDecimal, BigDecimal> cutDecimalPoint() {
        return x -> x.setScale(0, RoundingMode.FLOOR);
    }

    public static GoldPriceByType of(Price priceOf24K, Price priceOf18K, Price priceOf14K) {
        return new GoldPriceByType(priceOf24K, priceOf18K, priceOf14K);
    }

    private GoldPriceByType(Price priceOf24K, Price priceOf18K, Price priceOf14K) {
        this.priceOf24K = priceOf24K;
        this.priceOf18K = priceOf18K;
        this.priceOf14K = priceOf14K;
    }
}
