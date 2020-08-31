package net.devpg.rpinder.batch.collector;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.helper.StringUtil;

import lombok.Setter;

@Setter
public class GoldPriceTypePattern {
    private String common;
    private String gold24k;
    private String gold18k;
    private String gold14k;

    public String common() {
        return common;
    }

    public String gold24k() {
        return gold24k;
    }

    public String gold18k() {
        return gold18k;
    }

    public String gold14k() {
        return gold14k;
    }

    public List<String> golds() {
        List<String> goldList = new ArrayList<>();
        if (!StringUtil.isBlank(gold24k()))
            goldList.add(gold24k);
        if (!StringUtil.isBlank(gold18k()))
            goldList.add(gold18k);
        if (!StringUtil.isBlank(gold14k()))
            goldList.add(gold14k);
        return goldList.isEmpty() ? null : goldList;
    }
}
