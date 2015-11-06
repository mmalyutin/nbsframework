package org.plazmaforge.framework.erm.model;

public class MCity extends MLocality {

    private String phoneCode;
    
    
    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String toString() {
	return "City[" + toPropertiesString() + "]";
    }
    
    public String toPropertiesString() {
	return super.toPropertiesString() + ", phoneCode=" + phoneCode;
    }

}
