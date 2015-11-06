package org.plazmaforge.framework.erm.mapping;

public class Key extends Attribute implements IKey {

    

    public boolean isKey() {
        return true;
    }
    
    public boolean isUnique() {
	// Key is unique always
        return true;
    }
    
    public void setUnique(boolean unique) {
        throw new UnsupportedOperationException("Key is unique always");
    }
    
    public boolean isReadonly() {
        return false;
    }

    public void setReadonly(boolean readonly) {
	throw new UnsupportedOperationException("Key is not readonly always");
    }
    
    public boolean isInsert() {
	// We can insert non auto key only 
        return !isAutoKey();
    }


    public void setInsert(boolean insert) {
	throw new UnsupportedOperationException();
    }


    public boolean isUpdate() {
	// We can update assign key only
        return isAssignKey();
    }


    public void setUpdate(boolean update) {
	throw new UnsupportedOperationException();
    }
    
    
    public Attribute[] getDetailKeys() {
	return getDetailAttributes();
    }

    
    public boolean isAutoKey() {
        return isGeneratorType("autoincrement");
    }
    
    public boolean isAssignKey() {
	return getGeneratorType() == null || isGeneratorType("assigned");
    }
    
    protected boolean isGeneratorType(String type) {
	if (type == null) { 
	    return false;
	}
	return type.equals(getGeneratorType());
    }

    public String toString() {
	return "Key Attribute: " + toPropertiesString(); 
    }
}
