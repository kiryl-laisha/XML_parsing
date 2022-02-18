package com.laisha.gem.entity;

import java.math.BigDecimal;
import java.time.Instant;

public class PreciousGem extends AbstractGem {

    private double gemValue;

    public PreciousGem(String gemId,
                       String gemName,
                       String gemOrigin,
                       GemVisualParameters parameters,
                       Instant gemRegistrationDate,
                       double gemValue,
                       BigDecimal price) {
        super(gemId, gemName, gemOrigin, parameters, gemRegistrationDate, price);
        this.gemValue = gemValue;
    }

    public double getGemValue() {
        return gemValue;
    }

    public void setGemValue(double gemValue) {
        this.gemValue = gemValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PreciousGem that = (PreciousGem) o;

        return Double.compare(that.gemValue, gemValue) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(gemValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PreciousGem{");
        sb.append("gemValue=").append(gemValue);
        sb.append('}');
        return sb.toString();
    }
}
