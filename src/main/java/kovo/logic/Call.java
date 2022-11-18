package kovo.logic;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static kovo.logic.Utils.getPriceFromSubCollection;

public class Call extends Price {

    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final List<PricedUnit> pricedUnits;

    public Call(LocalDateTime startTime, LocalDateTime endTime) {
        this.pricedUnits = new ArrayList<>();
        this.startTime = startTime;
        this.endTime = endTime;
        setPriceUnits();
        super.init();
    }

    public List<PricedUnit> getPricedUnits() {
        return pricedUnits;
    }

    @Override
    protected BigDecimal calculatePrice() {
        return getPriceFromSubCollection(pricedUnits);
    }

    private void setPriceUnits() {
        long seconds = endTime.toEpochSecond(ZoneOffset.UTC) - startTime.toEpochSecond(ZoneOffset.UTC);
        long usedMinutes = (seconds % 60 == 0) ? (seconds / 60) : (seconds / 60) + 1;
        for (int i = 0; i < usedMinutes; i++) {
            PricedUnit pricedUnit = new PricedUnit(startTime.plusMinutes(i));
            if (i >= 5) { pricedUnit.applyLongCallDiscountPrice(); }
            pricedUnits.add(pricedUnit);
        }
    }
}
