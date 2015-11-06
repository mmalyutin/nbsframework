package org.plazmaforge.framework.erm.model;

public class MCountryComplex extends MCountry {

    public MCountryComplex() {
	super();
    }
    
    private MComposite composite;

    public MComposite getComposite() {
        return composite;
    }

    public void setComposite(MComposite composite) {
        this.composite = composite;
    }
    
    public String getCompositeName() {
	return composite == null ? null : composite.getName(); 
    }
    
    public String toString() {
	return "CountryComplex[" + toPropertiesString() + ", compositeName="  + getCompositeName() + "]";
    }

}
