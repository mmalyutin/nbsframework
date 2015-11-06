package org.plazmaforge.framework.erm.model;

public class MRectangle extends MFigure {

    private String rectangleAttr;

    public String getRectangleAttr() {
        return rectangleAttr;
    }

    public void setRectangleAttr(String rectangleAttr) {
        this.rectangleAttr = rectangleAttr;
    }
    
    protected String toTypeString() {
   	return "Rectangle";
    }
    
    protected String toPropertiesString() {
	return super.toPropertiesString() + ", rectangleAttr=" + getRectangleAttr();
    }
}
