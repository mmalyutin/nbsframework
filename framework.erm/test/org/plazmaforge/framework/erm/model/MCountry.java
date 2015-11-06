package org.plazmaforge.framework.erm.model;

public class MCountry extends MModel {

    
    private String code;
    
    private String name;

    
    public MCountry() {
	super();
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String toString() {
	return "Country[" + toPropertiesString() + "]";
    }
    
    public String toPropertiesString() {
	return super.toPropertiesString() + ", code=" + getCode() + ", name=" + getName(); 
    }

    
}
