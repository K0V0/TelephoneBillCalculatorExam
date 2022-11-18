package kovo.logic;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PricedUnit extends Price {
    private static final String RUSH_HOURS_PRICE = "1";
    private static final String WEAK_HOURS_PRICE = "0.5";
    private static final String LONG_CALL_DISCOUNT_PRICE = "0.2";

    private final LocalDateTime startTime;

    public PricedUnit(LocalDateTime startTime) {
        this.startTime = startTime;
        super.init();
    }

    @Override
    protected BigDecimal calculatePrice() {
        if (startTime.getHour() >= 8 && startTime.getHour() < 16) {
            return new BigDecimal(RUSH_HOURS_PRICE);
        }
        return new BigDecimal(WEAK_HOURS_PRICE);
    }

    public void applyLongCallDiscountPrice() {
        super.setPrice(new BigDecimal(LONG_CALL_DISCOUNT_PRICE));
    }

}
