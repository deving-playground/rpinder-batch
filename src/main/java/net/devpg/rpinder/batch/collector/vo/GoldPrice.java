package net.devpg.rpinder.batch.collector.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(fluent = true)
public class GoldPrice {
    private String date;
    private String collectDateTime;
    private GoldPriceByType priceOnPurchase;
    private GoldPriceByType priceOnSell;

    public static GoldPrice of(GoldPriceByType priceOnPurchase, GoldPriceByType priceOnSell) {
        GoldPrice goldPrice = new GoldPrice();
        goldPrice.priceOnPurchase(priceOnPurchase);
        goldPrice.priceOnSell(priceOnSell);
        return goldPrice;
    }

    public GoldPrice() {
    }

    public GoldPrice(String collectDateTime) {
        this.collectDateTime = collectDateTime;
    }
}
