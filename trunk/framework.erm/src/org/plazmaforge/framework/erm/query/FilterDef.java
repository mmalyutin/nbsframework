package org.plazmaforge.framework.erm.query;

public abstract class FilterDef {

    public static final String DISCRIMINATOR = "DISCRIMINATOR";
    
    public static final String CRITERIA = "CRITERIA";

    
    // Special marker discriminator, criteria...
    private String marker;
 
    
    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }
    
    public abstract boolean isParameter();

    public abstract int getParameterCount();

    public abstract TypeValue getTypeValue();
    
    public abstract TypeValue[] getTypeValues();
    
    public abstract String toSqlString();
    
}
