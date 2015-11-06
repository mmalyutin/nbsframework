package org.plazmaforge.framework.erm.model;

public class MRole extends MModel {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String toString() {
	return "Role[" + toPropertiesString() + "]";
    }
    
    public String toPropertiesString() {
	return super.toPropertiesString() + ", name=" + getName(); 
    }
}
