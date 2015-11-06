package org.plazmaforge.framework.ext.association;

import org.plazmaforge.framework.ext.data.ClassType;

/**
 * The <code>AssociationElement</code> describe element of association 
 * 
 * @author ohapon
 *
 */
public class AssociationElement {

    private ClassType element;
    
    /**
     * Type of element. By default we use name of super class 
     */
    private Class elementType;

    
    public AssociationElement() {
	element = new ClassType();
    }

    public AssociationElement(Class elementClass) {
	this();
	setElementClass(elementClass);
    }

    public AssociationElement(String elementClassName) {
	this();
	setElementClassName(elementClassName);
    }
    
    public String getElementClassName() {
	return element.getName();
    }

    public void setElementClassName(String elementClassName) {
	element.setName(elementClassName);
    }

    public Class getElementClass() {
	return element.getType();
    }

    public void setElementClass(Class elementClass) {
	element.setType(elementClass);
    }

    public Class getElementType() {
        return elementType;
    }

    public void setElementType(Class elementType) {
        this.elementType = elementType;
    }


   
    
}
