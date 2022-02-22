package com.laisha.gem.entity;

import com.laisha.gem.entity.enums.GemOriginCountry;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SemiPreciousGem extends AbstractGem {

    private double weight;

    public SemiPreciousGem() {
    }

    public SemiPreciousGem(String gemId,
                           String name,
                           GemOriginCountry originCountry,
                           GemVisualParameters parameters,
                           LocalDate registrationDate,
                           double weight,
                           BigDecimal price) {
        super(gemId, name, originCountry, parameters, registrationDate, price);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        SemiPreciousGem that = (SemiPreciousGem) o;

        return Double.compare(that.weight, weight) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SemiPreciousGem{");
        sb.append("weight=").append(weight);
        sb.append('}');
        return sb.toString();
    }
}
