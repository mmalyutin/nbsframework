package org.plazmaforge.framework.erm.model;

public class MPerson extends MModel {

    private String code;

    private MPersonPart personPart;
    
    
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public MPersonPart getPersonPart() {
        return personPart;
    }

    public void setPersonPart(MPersonPart personPart) {
        this.personPart = personPart;
    }
    
    public String toString() {
   	return "Person[" + toPropertiesString() + "]";
    }
    
    public String toPropertiesString() {
   	return super.toPropertiesString() + ", code=" + code;
    }
    
}
