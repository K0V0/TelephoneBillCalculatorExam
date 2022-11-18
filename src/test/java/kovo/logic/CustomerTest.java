package kovo.logic;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {

    @Test
    public void testPricesMath() {
        Customer customer = new Customer("123456789");
        customer.addCall(
                LocalDateTime.of(2022, 11, 18, 12, 0, 0),
                LocalDateTime.of(2022, 11, 18, 12, 1, 0));
        customer.addCall(
                LocalDateTime.of(2022, 11, 18, 6, 0, 0),
                LocalDateTime.of(2022, 11, 18, 6, 1, 0));
        assertEquals(new BigDecimal("1.5"), customer.getPrice());
    }

}
