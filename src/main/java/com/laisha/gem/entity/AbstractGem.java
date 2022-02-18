package com.laisha.gem.entity;

import java.math.BigDecimal;
import java.time.Instant;

public abstract class AbstractGem {

    private String gemId;
    private String gemName;
    private String gemOrigin;
    private GemVisualParameters gemParameters;
    private Instant gemRegistrationDate;
    private BigDecimal gemPrice;

    protected AbstractGem(String gemId,
                String gemName,
                String gemOrigin,
                GemVisualParameters gemParameters,
                Instant gemRegistrationDate,
                BigDecimal gemPrice) {
        this.gemId = gemId;
        this.gemName = gemName;
        this.gemOrigin = gemOrigin;
        this.gemParameters = gemParameters;
        this.gemRegistrationDate = gemRegistrationDate;
        this.gemPrice = gemPrice;
    }

    public String getGemId() {
        return gemId;
    }

    public void setGemId(String gemId) {
        this.gemId = gemId;
    }

    public String getGemName() {
        return gemName;
    }

    public void setGemName(String gemName) {
        this.gemName = gemName;
    }

    public String getGemOrigin() {
        return gemOrigin;
    }

    public void setGemOrigin(String gemOrigin) {
        this.gemOrigin = gemOrigin;
    }

    public GemVisualParameters getGemParameters() {
        return gemParameters;
    }

    public void setGemParameters(GemVisualParameters gemParameters) {
        this.gemParameters = gemParameters;
    }

    public Instant getGemRegistrationDate() {
        return gemRegistrationDate;
    }

    public void setGemRegistrationDate(Instant gemRegistrationDate) {
        this.gemRegistrationDate = gemRegistrationDate;
    }

    public BigDecimal getGemPrice() {
        return gemPrice;
    }

    public void setGemPrice(BigDecimal gemPrice) {
        this.gemPrice = gemPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractGem that = (AbstractGem) o;

        if (gemId != null ? !gemId.equals(that.gemId) : that.gemId != null)
            return false;
        if (gemName != null ? !gemName.equals(that.gemName) : that.gemName != null)
            return false;
        if (gemOrigin != null ? !gemOrigin.equals(that.gemOrigin) : that.gemOrigin != null)
            return false;
        if (gemParameters != null ? !gemParameters.equals(that.gemParameters) : that.gemParameters != null)
            return false;
        if (gemRegistrationDate != null ? !gemRegistrationDate.equals(that.gemRegistrationDate) : that.gemRegistrationDate != null)
            return false;
        return gemPrice != null ? gemPrice.equals(that.gemPrice) : that.gemPrice == null;
    }

    @Override
    public int hashCode() {
        int result = gemId != null ? gemId.hashCode() : 0;
        result = 31 * result + (gemName != null ? gemName.hashCode() : 0);
        result = 31 * result + (gemOrigin != null ? gemOrigin.hashCode() : 0);
        result = 31 * result + (gemParameters != null ? gemParameters.hashCode() : 0);
        result = 31 * result + (gemRegistrationDate != null ? gemRegistrationDate.hashCode() : 0);
        result = 31 * result + (gemPrice != null ? gemPrice.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AbstractGem{");
        sb.append("gemId='").append(gemId).append('\'');
        sb.append(", gemName='").append(gemName).append('\'');
        sb.append(", gemOrigin='").append(gemOrigin).append('\'');
        sb.append(", gemParameters=").append(gemParameters);
        sb.append(", gemRegistrationDate=").append(gemRegistrationDate);
        sb.append(", price=").append(gemPrice);
        sb.append('}');
        return sb.toString();
    }
}
