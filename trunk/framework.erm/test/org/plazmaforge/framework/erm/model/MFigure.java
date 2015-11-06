package org.plazmaforge.framework.erm.model;

public class MFigure extends MModel {

    private String type;
    
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    protected String toTypeString() {
   	return "Figure";
    }
    
    protected String toPropertiesString() {
	return super.toPropertiesString() + ", type=" + getType() + ", name=" + getName();
    }
}


