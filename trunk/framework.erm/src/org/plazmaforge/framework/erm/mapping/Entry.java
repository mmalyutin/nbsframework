package org.plazmaforge.framework.erm.mapping;

import org.plazmaforge.framework.erm.RelationType;

/**
 * Entry attribute is relation from outer table (OneToOne)
 * The Entry does't support column(s)
 * The Entry use only join column(s)
 * 
 * @author ohapon
 *
 */
public class Entry extends Association implements IEntry {

    public boolean isEntryType() {
	return true;
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////
    
    public void setColumn(Column column) {
	throw new UnsupportedOperationException("Join attribute doesn't support columns");
    }


    public void setColumnName(String columnName) {
	throw new UnsupportedOperationException("Join attribute doesn't support columns");
    }

    public void addColumns(Column column) {
	throw new UnsupportedOperationException("Join attribute doesn't support columns");
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////

    
    public RelationType getRelationType() {
	// Join is OneToOne always
        return RelationType.OneToOne;
    }
    
    
    public String toString() {
	return "Join Attribute: " + toPropertiesString(); 
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
