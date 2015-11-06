package org.plazmaforge.framework.erm.model;

public class MModel {
    
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String toString() {
	return toTypeString() + " [" + toPropertiesString() + "]";
    }

    protected String toTypeString() {
	return "Model";
    }
    
    protected String toPropertiesString() {
	return "id=" + getId();
    }

}
