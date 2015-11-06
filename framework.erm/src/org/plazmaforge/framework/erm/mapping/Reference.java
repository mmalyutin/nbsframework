package org.plazmaforge.framework.erm.mapping;

import org.plazmaforge.framework.erm.RelationType;

public class Reference extends Association implements IReference {
    
    
    public Reference() {
	
    }

    public boolean isReferenceType() {
	return true;
    }

    public RelationType getRelationType() {
        return isUnique() ? RelationType.OneToOne : RelationType.ManyToOne;
    }

    public String toString() {
	return "Reference Attribute: " + toPropertiesString(); 
    }

    
    public void setJoinClassName(String joinClassName) {
	super.setJoinClassName(joinClassName);
	super.setClassName(joinClassName);
    }
    
    public void setClassName(String className) {
	setJoinEntityIdentifier(className);
	super.setClassName(className);
    }
}
