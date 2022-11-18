package kovo;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TelephoneBillCalculatorImplTest {

    @Test
    public void testBasic() {
        String callsLog = "420774577453,13-01-2020 12:00:00,13-01-2020 12:01:00\n" +
                "420776562353,18-01-2020 12:00:00,18-01-2020 12:03:00";
        test("1", callsLog);
    }

    @Test
    public void testBestConsumerDiscountByNumber() {
        String callsLog = "123456788,13-01-2020 12:00:00,13-01-2020 12:01:00\n" +
                "123456789,18-01-2020 12:00:00,18-01-2020 12:01:00";
        test("1", callsLog);
        callsLog = "123456788,13-01-2020 12:00:00,13-01-2020 12:01:00\n" +
                "123456789,18-01-2020 12:00:00,18-01-2020 12:01:00\n" +
                "123456789,18-01-2020 12:05:00,18-01-2020 12:06:00";
        test("1", callsLog);
        callsLog = "123456788,13-01-2020 12:00:00,13-01-2020 12:03:00\n" +
                "123456789,18-01-2020 12:00:00,18-01-2020 12:01:00\n" +
                "123456789,18-01-2020 12:05:00,18-01-2020 12:06:00";
        test("2", callsLog);
    }

    @Test
    public void testEmpty() {
        String callsLog = "";
        test("0", callsLog);
    }

    private void test(String expectedPrice, String callsLog) {
        TelephoneBillCalculator telephoneBillCalculator = new TelephoneBillCalculatorImpl();
        assertEquals(new BigDecimal(expectedPrice), telephoneBillCalculator.calculate(callsLog));
    }
}
