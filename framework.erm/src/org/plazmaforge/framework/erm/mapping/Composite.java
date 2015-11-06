package org.plazmaforge.framework.erm.mapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite attribute.
 * The composite has own attributes.
 * The composite does't support column(s)
 * 
 * @author ohapon
 *
 */
public class Composite extends Attribute implements IComposite {

    private List<Attribute> attributes;
    
    private int joinDepth;
    
    /////////////////////////////////////////////////////////////////////////////////////////////
    
    public void setColumn(Column column) {
	throw new UnsupportedOperationException("Composite attribute doesn't support columns");
    }


    public void setColumnName(String columnName) {
	throw new UnsupportedOperationException("Composite attribute doesn't support columns");
    }

    public void addColumns(Column column) {
	throw new UnsupportedOperationException("Composite attribute doesn't support columns");
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////
	
	
    protected List<Attribute> doGetAttributes() {
	if (attributes == null) {
	    attributes = new ArrayList<Attribute>(); 
	}
        return attributes;
    }

    public Attribute[] getAttributes() {
	if (attributes == null) {
	    return new Attribute[0]; 
	}
        return attributes.toArray(new Attribute[0]);
    }


    public void addAttribute(Attribute attribute) {
	if (!isCompositeType()) {
	    throw new IllegalArgumentException("Attribute must be composite");
	}
	attribute.setParent(this);
	doGetAttributes().add(attribute);
    }

    public boolean hasAttributes() {
	return attributes != null && !attributes.isEmpty();
    }
    
    public Attribute getAttributeByName(String name) {
	if (name == null) {
	    throw new IllegalArgumentException("Attribute Name must be not null");
	}
	if (!hasAttributes()) {
	    return null;
	}
	for (Attribute a: attributes) {
	    if (name.equals(a.getName())) {
		return a;
	    }
	}
	return null;
    }
    

    /////////////////////////////////////////////////////////////////////////////////////
    
    public boolean isCompositeType() {
	return true;
    }
    
    public boolean isAnonym() {
	return getName() == null;
    }
    
    public boolean isPseudoComposite() {
	return isAnonym();
    }

    public String toString() {
	return "Composite Attribute: " + toPropertiesString(); 
    }

    /////////////////////////////////////////////////////////////////////////////////////

    public int getJoinDepth() {
        return joinDepth;
    }


    public void setJoinDepth(int joinDepth) {
        this.joinDepth = joinDepth;
    }

    
}
