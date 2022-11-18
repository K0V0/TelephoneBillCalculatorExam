package kovo.logic;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Customer extends Price implements Comparable<Customer> {

    private static final String BEST_CONSUMER_DISCOUNT_PRICE = "0";

    private final String phoneNumber;
    private final List<Call> calls;

    public Customer(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.calls = new ArrayList<>();
    }

    public void addCall(LocalDateTime startTime, LocalDateTime endTime) {
        calls.add(new Call(startTime, endTime));
        super.init();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void applyBestConsumerDiscount() {
        super.setPrice(new BigDecimal(BEST_CONSUMER_DISCOUNT_PRICE));
    }

    @Override
    protected BigDecimal calculatePrice() {
        return calls.stream()
                .map(Call::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public int compareTo(Customer o) {
        int result = this.getPrice().compareTo(o.getPrice());
        if (result != 0) {
            return result;
        }
        long currentPhoneNumber = Integer.parseInt(this.getPhoneNumber());
        long comparedPhoneNumber = Integer.parseInt(o.getPhoneNumber());
        if (currentPhoneNumber > comparedPhoneNumber) {
            return 1;
        } else if (currentPhoneNumber < comparedPhoneNumber) {
            return -1;
        }
        return 0;
    }

}
