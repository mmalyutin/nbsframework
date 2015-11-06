package org.plazmaforge.framework.erm.query;


public class OrderDef extends AttributeDef {
    
    private boolean asc;
    

    public OrderDef() {
	this.asc = true;
    }

    public boolean isAsc() {
        return asc;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }

    public String toString() {
	return "Order: " + toPropertiesString(); 
    }
    
}
