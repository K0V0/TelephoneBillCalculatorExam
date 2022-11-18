package kovo;

import kovo.logic.Customer;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static kovo.logic.Utils.parseCallsLog;

public class TelephoneBillCalculatorImpl implements TelephoneBillCalculator {

    private final Map<String, Customer> customersMap;

    public TelephoneBillCalculatorImpl() {
        this.customersMap = new HashMap<>();
    }

    @Override
    public BigDecimal calculate(String phoneLog) {
        customersMap.putAll(parseCallsLog(phoneLog));
        applyBestConsumerDiscount();
        return sumPrices();
    }

    private void applyBestConsumerDiscount() {
         customersMap.values().stream()
                 .max(Comparator.naturalOrder())
                 .ifPresent(Customer::applyBestConsumerDiscount);
    }

    private BigDecimal sumPrices() {
         return customersMap.values().stream()
                .map(Customer::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
