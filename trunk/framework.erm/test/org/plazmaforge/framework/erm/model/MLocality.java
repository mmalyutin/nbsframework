package org.plazmaforge.framework.erm.model;

public class MLocality extends MModel {


    
    private String name;

    private MCountry country;
    
    private Integer localityTypeId;
    
   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
	return "Locality[" + toPropertiesString() + "]";
    }
    
    public String toPropertiesString() {
	return super.toPropertiesString() + ", name=" + getName() 
		+ ", "  + (country == null ? "Country[null]" : country.toString()
		+ ", localityTypeId=" + localityTypeId);
    }

    public MCountry getCountry() {
        return country;
    }

    public void setCountry(MCountry country) {
        this.country = country;
    }

    public Integer getLocalityTypeId() {
        return localityTypeId;
    }

    public void setLocalityTypeId(Integer localityTypeId) {
        this.localityTypeId = localityTypeId;
    }

    
    
}
