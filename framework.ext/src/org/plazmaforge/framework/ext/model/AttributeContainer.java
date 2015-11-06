/*
 * Copyright (C) 2005-2010 Oleh Hapon ohapon@users.sourceforge.net
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * Oleh Hapon
 * Kyiv, UKRAINE
 * ohapon@users.sourceforge.net
 */

package org.plazmaforge.framework.ext.model;

import java.util.ArrayList;
import java.util.List;


/** 
 * @author ohapon
 */

public class AttributeContainer implements IAttributeContainer {
    
    private static final long serialVersionUID = -6970483502407441210L;

    private List<Attribute> declareAttributes;
    
    private List<AttributeValue> attributes;
    
    
    public List<Attribute> getDeclareAttributes() {
	if (declareAttributes == null) {
	    declareAttributes = new ArrayList<Attribute>();
	}	
        return declareAttributes;
    }

    public void setDeclareAttributes(List<Attribute> declareAttribute) {
        this.declareAttributes = declareAttribute;
    }

    public List<AttributeValue> getAttributes() {
	if (attributes == null) {
	    attributes = new ArrayList<AttributeValue>();
	}
        return attributes;
    }

    public void setAttributes(List<AttributeValue> attributes) {
        this.attributes = attributes;
    }


    public AttributeValue getAttribute(String code) {
	if (attributes == null) {
	    return null;
	}
	for (AttributeValue attr: attributes) {
	    if (code.equals(attr.getCode())) {
		return attr;
	    }
	}
	return null;
    }
    
    
    
    public void addAttribute(AttributeValue attribute) {
	getAttributes().add(attribute);
    }

    public boolean removeAttribute(AttributeValue attribute) {
	return getAttributes().remove(attribute);
    }
    
    public Object getAttributeValue(String code) {
	AttributeValue attribure = getAttribute(code);
	if (attribure == null) {
	    return null;
	}
	return attribure.getValue();
    }
    
    public void setAttributeValue(String code, Object value) {
	AttributeValue attr = getAttribute(code);
	if (attr != null) {
	    attr.setValue(value);
	    return;
	}
	// TODO: STUB
    }
    
    public List<Attribute> getUnusedAttributes() {
	List<Attribute> declaredAttributes = getDeclareAttributes();
	if (declaredAttributes == null || declaredAttributes.size() == 0) {
	    return null;
	}
	List<Attribute> unusedAttributes = new ArrayList<Attribute>();
	List<AttributeValue> attributes = getAttributes();
	
	// Empty attributes
	if (attributes == null || attributes.size() == 0) {
	    for (Attribute attr : declaredAttributes) {
		unusedAttributes.add(attr);
	    }
	    return unusedAttributes;// declaredAttributes;
	}
	
	
	for (Attribute attr : declaredAttributes) {
	    if (isUseAttribute(attr, attributes)) {
		continue;
	    }
	    unusedAttributes.add(attr);
	}
	if (unusedAttributes.size() == 0) {
	    return null;
	}
	return unusedAttributes;
    }
    
    public boolean isUseAttribute(Attribute attr, List<AttributeValue> attributeValues) {
	if (attr == null || attributeValues == null || attributeValues.size() == 0) {
	    return false;
	}
	for (AttributeValue attrVal : attributeValues) {
	    if (isUseAttribute(attr, attrVal)) {
		return true;
	    }
	}
	return false;
    }
    
    
    public boolean isUseAttribute(Attribute attr, AttributeValue attributeValue) {
	if (attr == null || attributeValue == null || attributeValue.getAttribute() == null) {
	    return false;
	}
	Integer attrId = attr.getId();
	if (attrId == null) {
	    return false;
	}
	return attrId.equals(attributeValue.getAttribute().getId());
    }
}
