/*
 * Copyright (C) 2005-2010 Oleh Hapon ohapon@users.sourceforge.net
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * Oleh Hapon
 * Kyiv, UKRAINE
 * ohapon@users.sourceforge.net
 */



package org.plazmaforge.framework.ext.model;


import org.plazmaforge.framework.config.object.IEntityConfig;
import org.plazmaforge.framework.config.object.IEntityConfigurable;


/** 
 * @author ohapon
 */

public class Attribute extends  BaseEntity implements IEntityConfigurable {

    private static final long serialVersionUID = -6304528654290405354L;

    private String code;
    
    private String name;
    
    private int order;
    
    private IEntityConfig entity;

    ////
    
    private DataType dataType;
    
    private IEntityConfig valueEntity;
    
    

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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }


    public IEntityConfig getEntity() {
        return entity;
    }

    public void setEntity(IEntityConfig entity) {
        this.entity = entity;
    }

    
    public String getEntityConfigId() {
	return entity == null ? null : entity.getConfigId();
    }

    
    
    public DataType getDataType() {
        return dataType;
    }

    public String getDataTypeName() {
        return dataType == null ? null : dataType.getName();
    }
    
    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }


   
    public IEntityConfig getValueEntity() {
        return valueEntity;
    }

    public void setValueEntity(IEntityConfig valueEntity) {
        this.valueEntity = valueEntity;
    }

    public String toString() {
	return getName();
    }
}
