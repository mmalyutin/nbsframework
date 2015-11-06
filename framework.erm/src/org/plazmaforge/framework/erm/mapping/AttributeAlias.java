package org.plazmaforge.framework.erm.mapping;

import org.plazmaforge.framework.core.criteria.Criteria;

/**
 * Attribute alias
 * 
 * @author ohapon
 *
 */
public class AttributeAlias {

    /**
     * Name of alias
     */
    private String name;
    
    /**
     * Name of attribute
     */
    private String attribute;
    
    /**
     * Name of attribute for order
     */
    private String orderAttribute;
    
    /**
     * Name attribute for filter
     */
    private String filterAttribute;

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
	if (name == null) {
	    throw new IllegalArgumentException("Name of alias must be not null");
	}
        this.name = name;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
	if (attribute == null) {
	    throw new IllegalArgumentException("Attribute of alias must be not null");
	}
        this.attribute = attribute;
    }

    /**
     * Return attribute by type (ORDER, FILTER)
     * @param type
     * @return
     */
    public String getAttribute(int type) {
	if (type == Criteria.ORDER) {
	    return getOrderAttribute(); 
	} else if (type == Criteria.FILTER) {
	    return getFilterAttribute(); 
	} 
        return getAttribute();
    }

    public String getOrderAttribute() {
        return orderAttribute == null ? attribute : orderAttribute;
    }

    public void setOrderAttribute(String orderAttribute) {
        this.orderAttribute = orderAttribute;
    }

    public String getFilterAttribute() {
        return filterAttribute == null ? attribute : filterAttribute;
    }

    public void setFilterAttribute(String filterAttribute) {
        this.filterAttribute = filterAttribute;
    }
    
    
}
