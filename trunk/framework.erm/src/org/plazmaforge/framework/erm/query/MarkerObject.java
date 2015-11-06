package org.plazmaforge.framework.erm.query;

public class MarkerObject {

    private Object data;

    private boolean modify;
    
    public MarkerObject(Object data, boolean modify) {
	super();
	this.data = data;
	this.modify = modify;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isModify() {
        return modify;
    }

    public void setModify(boolean modify) {
        this.modify = modify;
    }

    
}
