package org.plazmaforge.framework.erm.mapping;

import java.util.ArrayList;
import java.util.List;

public class CompositeKey extends Key implements IKey, IComposite {


    private List<Attribute> attributes;
    
    
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
    

    public boolean hasAttributes() {
	return attributes != null && !attributes.isEmpty();
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
	return "CompositeKey Attribute: " + toPropertiesString(); 
    }

}
