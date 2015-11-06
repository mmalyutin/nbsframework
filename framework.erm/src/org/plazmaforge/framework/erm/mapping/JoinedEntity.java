package org.plazmaforge.framework.erm.mapping;

public class JoinedEntity extends Entity {

    
    private String extendsClassName;
    
    private boolean dynamicInsert = true; // Dynamic insert record in parent table. Only for extends-type='joined-subclass'

    private boolean dynamicUpdate = true; // Dynamic update record in parent table. Only for extends-type='joined-subclass'

    
    public String getExtendsClassName() {
        return extendsClassName;
    }

    public void setExtendsClassName(String extendsClassName) {
	if (extendsClassName == null) {
	    throw new IllegalArgumentException("Extends class must be not null");
	}
        this.extendsClassName = extendsClassName;
    }
    
    public boolean hasHierarchy() {
	return true; 
    }
    
    public EntityType getEntityType() {
        return EntityType.JoinedEntity;
    }
    
    public boolean isDynamicInsert() {
        return dynamicInsert;
    }

    public void setDynamicInsert(boolean dynamicInsert) {
        this.dynamicInsert = dynamicInsert;
    }

    public boolean isDynamicUpdate() {
        return dynamicUpdate;
    }

    public void setDynamicUpdate(boolean dynamicUpdate) {
        this.dynamicUpdate = dynamicUpdate;
    }
}
