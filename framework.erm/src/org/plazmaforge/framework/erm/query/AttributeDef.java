package org.plazmaforge.framework.erm.query;

import java.io.Serializable;

import org.plazmaforge.framework.core.sql.type.Type;
import org.plazmaforge.framework.erm.mapping.Attribute;
import org.plazmaforge.framework.erm.mapping.Entity;

public abstract class AttributeDef implements Serializable {

    /**
     * Attribute of column
     */
    private Attribute attribute;
    
    /**
     * Name of column
     */
    private String columnName;
    
    /**
     * Table of column
     */
    private TableDef table;
    

    /**
     * Entity of Attribute
     */
    private Entity entity;

    
    
    public String getAttributeName() {
        return attribute == null ? null : attribute.getName();
    }

    public Type getAttributeType() {
        return attribute == null ? null : attribute.getType();
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public String getEntityIdentifier() {
        return entity == null ? null : entity.getIdentifier();
    }

    public TableDef getTable() {
        return table;
    }

    public void setTable(TableDef table) {
        this.table = table;
    }

    public String getTableName() {
        return table == null ? null :table.getTableName();
    }

    public String getTableAlias() {
	return table == null ? null :table.getTableAlias();
    }

    
    public String toString() {
	return toPropertiesString(); 
    }

    public String toPropertiesString() {
	return "attribute=" + getAttributeName()+ ", column=" + getColumnName()+ ", entity=" + getEntityIdentifier() + ", table=" + getTableName();
    }
    
    
}
