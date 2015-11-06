package org.plazmaforge.framework.erm.model;

public class MDefPhone extends MModel {

    private IContactable contactable;
    
    
    
    public IContactable getContactable() {
        return contactable;
    }

    public void setContactable(IContactable contactable) {
        this.contactable = contactable;
    }

    private String phoneString;

    public String getPhoneString() {
        return phoneString;
    }

    public void setPhoneString(String phoneString) {
        this.phoneString = phoneString;
    }
    
    public String toString() {
	return "Phone[" + toPropertiesString() + "]";
    }
    
    public String toPropertiesString() {
	return super.toPropertiesString() + ", phoneStringe=" + getPhoneString(); 
    }
    
}
