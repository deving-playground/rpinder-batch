package net.devpg.rpinder.batch.collector.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import net.devpg.rpinder.batch.collector.vo.GoldPriceByType;

@Getter
@ToString
@AllArgsConstructor(staticName = "of")
public class GoldPriceInfo {

    private final String date;
    private final String collectDateTime;
    private final GoldPriceByType goldPriceOnPurchase;
}
