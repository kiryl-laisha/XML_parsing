package com.laisha.gem.entity;

import com.laisha.gem.entity.enums.GemOriginCountry;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PreciousGem extends AbstractGem {

    private double value;

    public PreciousGem() {
    }

    public PreciousGem(String gemId,
                       String name,
                       GemOriginCountry originCountry,
                       GemVisualParameters parameters,
                       LocalDate registrationDate,
                       double value,
                       BigDecimal price) {
        super(gemId, name, originCountry, parameters, registrationDate, price);
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof PreciousGem)) return false;
        if (!super.equals(o)) return false;
        PreciousGem that = (PreciousGem) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {

        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("PreciousGem{");
        sb.append(super.toString());
        sb.append("value=").append(value);
        sb.append('}');
        return sb.toString();
    }
}
