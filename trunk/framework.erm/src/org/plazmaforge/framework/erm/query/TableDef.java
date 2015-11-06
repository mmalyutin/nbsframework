package org.plazmaforge.framework.erm.query;

import java.io.Serializable;

import org.plazmaforge.framework.erm.mapping.Entity;

public class TableDef implements Serializable {

    private Entity entity;
    
    private String tableAlias;

    private boolean join;
    
    
    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public String getEntityIdentifier() {
        return entity == null ? null : entity.getIdentifier();
    }

    public String getTableName() {
        return entity == null ? null : entity.getTableName();
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    public boolean isJoin() {
        return join;
    }

    public void setJoin(boolean join) {
        this.join = join;
    }
    
    
}
