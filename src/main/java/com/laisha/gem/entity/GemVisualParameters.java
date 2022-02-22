package com.laisha.gem.entity;

import com.laisha.gem.entity.enums.GemColour;

public class GemVisualParameters {

    public static final String DEFAULT_IS_CERTIFIED = "false";

    private GemColour colour;
    private boolean isCertified;
    private int facetNumber;
    private int transparency;

    public GemVisualParameters() {
    }

    public GemVisualParameters(GemColour gemColour,
                               int facetNumber,
                               int transparency,
                               boolean isCertified) {
        this.colour = gemColour;
        this.facetNumber = facetNumber;
        this.transparency = transparency;
        this.isCertified = isCertified;
    }

    public GemColour getColour() {
        return colour;
    }

    public void setColour(GemColour colour) {
        this.colour = colour;
    }

    public int getTransparency() {
        return transparency;
    }

    public void setTransparency(int transparency) {
        this.transparency = transparency;
    }

    public int getFacetNumber() {
        return facetNumber;
    }

    public void setFacetNumber(int facetNumber) {
        this.facetNumber = facetNumber;
    }

    public boolean getIsCertified() {
        return isCertified;
    }

    public void setIsCertified(boolean certified) {
        this.isCertified = certified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GemVisualParameters that = (GemVisualParameters) o;

        if (transparency != that.transparency) return false;
        if (facetNumber != that.facetNumber) return false;
        if (isCertified != that.isCertified) return false;
        return colour != null ? colour.equals(that.colour) : that.colour == null;
    }

    @Override
    public int hashCode() {
        int result = colour != null ? colour.hashCode() : 0;
        result = 31 * result + transparency;
        result = 31 * result + facetNumber;
        result = 31 * result + (isCertified ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GemVisualParameters{");
        sb.append("gemColour='").append(colour).append('\'');
        sb.append(", gemTransparency=").append(transparency);
        sb.append(", gemFacetNumber=").append(facetNumber);
        sb.append(", certifiedGem=").append(isCertified);
        sb.append('}');
        return sb.toString();
    }
}
