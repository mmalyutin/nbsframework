package org.plazmaforge.framework.erm.model;

public class MPartner extends MModel implements IContactable {

    private String code;
    
    private String name;
    
    

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    private MDefPhone phone;
    
    
    public MDefPhone getPhone() {
        return phone;
    }

    public void setPhone(MDefPhone phone) {
        this.phone = phone;
    }
    
    public String toString() {
	return "Partner[" + toPropertiesString() + "]";
    }

    public String toPropertiesString() {
	return super.toPropertiesString() 
		+ ", code=" + getCode() 
		+ ", name=" + getName()
		+ (phone == null ? "" : phone.toPropertiesString()); 
    }
    
}
