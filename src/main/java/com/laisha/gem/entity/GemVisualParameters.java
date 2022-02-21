package com.laisha.gem.entity;

public class GemVisualParameters {

    public static final boolean DEFAULT_CERTIFIED_GEM = false;

    private String colour;
    private boolean certified;
    private int facetNumber;
    private int transparency;

    public GemVisualParameters() {
    }

    public GemVisualParameters(String gemColour,
                               int facetNumber,
                               int transparency,
                               boolean certified) {
        this.colour = gemColour;
        this.facetNumber = facetNumber;
        this.transparency = transparency;
        this.certified = certified;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
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

    public boolean getCertified() {
        return certified;
    }

    public void setCertified(boolean certified) {
        this.certified = certified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GemVisualParameters that = (GemVisualParameters) o;

        if (transparency != that.transparency) return false;
        if (facetNumber != that.facetNumber) return false;
        if (certified != that.certified) return false;
        return colour != null ? colour.equals(that.colour) : that.colour == null;
    }

    @Override
    public int hashCode() {
        int result = colour != null ? colour.hashCode() : 0;
        result = 31 * result + transparency;
        result = 31 * result + facetNumber;
        result = 31 * result + (certified ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GemVisualParameters{");
        sb.append("gemColour='").append(colour).append('\'');
        sb.append(", gemTransparency=").append(transparency);
        sb.append(", gemFacetNumber=").append(facetNumber);
        sb.append(", certifiedGem=").append(certified);
        sb.append('}');
        return sb.toString();
    }
}
