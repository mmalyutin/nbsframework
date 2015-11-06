package org.plazmaforge.framework.erm.mapping;

public class SubclassEntity extends Entity {

    private static final long serialVersionUID = -6376836697341247453L;

    private String extendsClassName;
    
    private String discriminatorValue; // Only for extends-type='subclass'
    
    
    public EntityType getEntityType() {
        return EntityType.SubclassEntity;
    }
    
    public String getExtendsClassName() {
        return extendsClassName;
    }

    public void setExtendsClassName(String extendsClassName) {
        this.extendsClassName = extendsClassName;
    }    

    public boolean hasHierarchy() {
	return false; 
    }
    
    public String getDiscriminatorValue() {
        return discriminatorValue;
    }

    public void setDiscriminatorValue(String discriminatorValue) {
	//if (discriminatorValue == null) {
	//    throw new IllegalArgumentException("Discriminator value must be not null");
	//}
        this.discriminatorValue = discriminatorValue;
    }

}
