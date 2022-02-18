package com.laisha.gem.entity;

public class GemVisualParameters {

    private String gemColour;
    private int gemTransparency;
    private int gemFacetNumber;
    private boolean certifiedGem = false;

    public GemVisualParameters(String gemColour,
                               int gemTransparency,
                               int gemFacetNumber) {
        this.gemColour = gemColour;
        this.gemTransparency = gemTransparency;
        this.gemFacetNumber = gemFacetNumber;
    }

    public GemVisualParameters(String gemColour,
                               int gemTransparency,
                               int gemFacetNumber,
                               boolean certified) {
        this.gemColour = gemColour;
        this.gemTransparency = gemTransparency;
        this.gemFacetNumber = gemFacetNumber;
        this.certifiedGem = certified;
    }

    public String getGemColour() {
        return gemColour;
    }

    public void setGemColour(String gemColour) {
        this.gemColour = gemColour;
    }

    public int getGemTransparency() {
        return gemTransparency;
    }

    public void setGemTransparency(int gemTransparency) {
        this.gemTransparency = gemTransparency;
    }

    public int getGemFacetNumber() {
        return gemFacetNumber;
    }

    public void setGemFacetNumber(int gemFacetNumber) {
        this.gemFacetNumber = gemFacetNumber;
    }

    public boolean isCertifiedGem() {
        return certifiedGem;
    }

    public void setCertifiedGem(boolean certifiedGem) {
        this.certifiedGem = certifiedGem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GemVisualParameters that = (GemVisualParameters) o;

        if (gemTransparency != that.gemTransparency) return false;
        if (gemFacetNumber != that.gemFacetNumber) return false;
        if (certifiedGem != that.certifiedGem) return false;
        return gemColour != null ? gemColour.equals(that.gemColour) : that.gemColour == null;
    }

    @Override
    public int hashCode() {
        int result = gemColour != null ? gemColour.hashCode() : 0;
        result = 31 * result + gemTransparency;
        result = 31 * result + gemFacetNumber;
        result = 31 * result + (certifiedGem ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GemVisualParameters{");
        sb.append("gemColour='").append(gemColour).append('\'');
        sb.append(", gemTransparency=").append(gemTransparency);
        sb.append(", gemFacetNumber=").append(gemFacetNumber);
        sb.append(", certifiedGem=").append(certifiedGem);
        sb.append('}');
        return sb.toString();
    }
}
