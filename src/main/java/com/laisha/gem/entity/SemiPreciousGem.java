package com.laisha.gem.entity;

import java.math.BigDecimal;
import java.time.Instant;

public class SemiPreciousGem extends AbstractGem {

    private double gemWeight;

    public SemiPreciousGem(String gemId,
                           String gemName,
                           String gemOrigin,
                           GemVisualParameters parameters,
                           Instant gemRegistrationDate,
                           double gemWeight,
                           BigDecimal price) {
        super(gemId, gemName, gemOrigin, parameters, gemRegistrationDate, price);
        this.gemWeight = gemWeight;
    }

    public double getGemWeight() {
        return gemWeight;
    }

    public void setGemWeight(double gemWeight) {
        this.gemWeight = gemWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        SemiPreciousGem that = (SemiPreciousGem) o;

        return Double.compare(that.gemWeight, gemWeight) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(gemWeight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SemiPreciousGem{");
        sb.append("gemWeight=").append(gemWeight);
        sb.append('}');
        return sb.toString();
    }
}
