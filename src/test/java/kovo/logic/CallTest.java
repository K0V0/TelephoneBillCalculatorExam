package kovo.logic;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallTest {

    @Test
    public void testUnitsUsed() {
        testUnitsUsed(1, 12, 0, 0, 12, 0, 1);
        testUnitsUsed(1, 12, 0, 0, 12, 0, 59);
        testUnitsUsed(1, 12, 0, 0, 12, 1, 0);
        testUnitsUsed(2, 12, 0, 0, 12, 1, 1);
        testUnitsUsed(2, 11, 59, 59, 12, 1, 0);
    }

    @Test
    public void testPriceSumming() {
        testPrice("2",2, 12, 0, 0, 12, 1, 1);
    }

    @Test
    public void testPriceForPriceMarginsOverlappingCalls() {
        testPrice("0.5",1, 7, 59, 30, 8, 0, 15);
        testPrice("1",1, 15, 59, 30, 16, 0, 15);
        testPrice("1.5",2, 7, 59, 30, 8, 0, 31);
        testPrice("1.5",2, 15, 59, 30, 16, 0, 31);
    }

    @Test
    public void testPriceAfterLongCallDiscount() {
        testPrice("5",5, 12, 0, 0, 12, 5, 0);
        testPrice("5.2",6, 12, 0, 0, 12, 5, 1);
    }

    private static void testPrice(String expectedPrice, int expectedPricedUnits,
                                  int startHour, int startMinute, int startSecond, int endHour, int endMinute, int endSecond) {
        Call call = new Call(
                LocalDateTime.of(2022, 11, 18, startHour, startMinute, startSecond),
                LocalDateTime.of(2022, 11, 18, endHour, endMinute, endSecond));
        assertEquals(expectedPricedUnits, call.getPricedUnits().size());
        assertEquals(new BigDecimal(expectedPrice), call.getPrice());
    }

    private static void testUnitsUsed(int expectedCount,
                                      int startHour, int startMinute, int startSecond, int endHour, int endMinute, int endSecond) {
        Call call = new Call(
                LocalDateTime.of(2022, 11, 18, startHour, startMinute, startSecond),
                LocalDateTime.of(2022, 11, 18, endHour, endMinute, endSecond));
        assertEquals(expectedCount, call.getPricedUnits().size());
    }
}
