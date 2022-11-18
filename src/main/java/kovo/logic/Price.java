package kovo.logic;

import java.math.BigDecimal;

public abstract class Price {

    private BigDecimal price;

    protected Price() {
        this.price = BigDecimal.ZERO;
    }

    protected abstract BigDecimal calculatePrice();

    protected void init() {
        this.price = calculatePrice();
    }

    protected void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

}