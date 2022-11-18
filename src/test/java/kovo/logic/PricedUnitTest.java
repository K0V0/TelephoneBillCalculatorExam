package kovo.logic;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PricedUnitTest {

    private static final BigDecimal RUSH_HOURS_PRICE = new BigDecimal("1");
    private static final BigDecimal WEAK_HOURS_PRICE = new BigDecimal("0.5");

    @Test
    public void testRushHoursPrice() {
        testPrice(RUSH_HOURS_PRICE, 8, 0, 0);
        testPrice(RUSH_HOURS_PRICE, 12, 0, 0);
        testPrice(RUSH_HOURS_PRICE, 15, 59, 59);
    }

    @Test
    public void testWeakHoursPrice() {
        testPrice(WEAK_HOURS_PRICE, 7, 59, 59);
        testPrice(WEAK_HOURS_PRICE, 16, 0, 0);
        testPrice(WEAK_HOURS_PRICE, 23, 59, 59);
    }

    private static void testPrice(BigDecimal expectedPrice, int hour, int minute, int second) {
        PricedUnit pricedUnit = new PricedUnit(LocalDateTime.of(2022, 11, 18, hour, minute, second));
        assertEquals(expectedPrice, pricedUnit.getPrice());
    }
}
