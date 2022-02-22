package com.laisha.gem.entity;

import com.laisha.gem.entity.enums.GemOriginCountry;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class AbstractGem {

    private String gemId;
    private String name;
    private GemOriginCountry originCountry;
    private GemVisualParameters parameters;
    private LocalDate registrationDate;
    private BigDecimal price;

    protected AbstractGem() {
        parameters = new GemVisualParameters();
    }

    protected AbstractGem(String gemId,
                          String name,
                          GemOriginCountry originCountry,
                          GemVisualParameters parameters,
                          LocalDate registrationDate,
                          BigDecimal price) {
        this.gemId = gemId;
        this.name = name;
        this.originCountry = originCountry;
        this.parameters = parameters;
        this.registrationDate = registrationDate;
        this.price = price;
    }

    public String getGemId() {
        return gemId;
    }

    public void setGemId(String gemId) {
        this.gemId = gemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GemOriginCountry getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(GemOriginCountry originCountry) {
        this.originCountry = originCountry;
    }

    public GemVisualParameters getParameters() {
        return parameters;
    }

    public void setParameters(GemVisualParameters parameters) {
        this.parameters = parameters;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractGem that = (AbstractGem) o;
        if (gemId != null ? !gemId.equals(that.gemId) : that.gemId != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;
        if (originCountry != that.originCountry) return false;
        if (parameters != null ? !parameters.equals(that.parameters) : that.parameters != null)
            return false;
        if (registrationDate != null ? !registrationDate.equals(that.registrationDate) : that.registrationDate != null)
            return false;
        return price != null ? price.equals(that.price) : that.price == null;
    }

    @Override
    public int hashCode() {

        int result = gemId != null ? gemId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (originCountry != null ? originCountry.hashCode() : 0);
        result = 31 * result + (parameters != null ? parameters.hashCode() : 0);
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder();
        sb.append("gemId='").append(gemId).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", originCountry=").append(originCountry);
        sb.append(", ").append(parameters.toString());
        sb.append(", registrationDate=").append(registrationDate);
        sb.append(", price=").append(price);
        return sb.toString();
    }
}
