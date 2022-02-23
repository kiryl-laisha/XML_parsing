package com.laisha.gem.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PreciousGem extends AbstractGem {

    private double caratValue;

    public PreciousGem() {
    }

    public PreciousGem(String gemId,
                       String name,
                       GemOriginCountry originCountry,
                       GemVisualParameters parameters,
                       LocalDate registrationDate,
                       double caratValue,
                       BigDecimal price) {
        super(gemId, name, originCountry, parameters, registrationDate, price);
        this.caratValue = caratValue;
    }

    public double getCaratValue() {
        return caratValue;
    }

    public void setCaratValue(double caratValue) {
        this.caratValue = caratValue;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof PreciousGem)) return false;
        if (!super.equals(o)) return false;
        PreciousGem that = (PreciousGem) o;
        return Double.compare(that.caratValue, caratValue) == 0;
    }

    @Override
    public int hashCode() {

        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(caratValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("PreciousGem{");
        sb.append(super.toString());
        sb.append(", caratValue=").append(caratValue);
        sb.append('}');
        return sb.toString();
    }
}
