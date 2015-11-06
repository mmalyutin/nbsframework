/*
 * Copyright (C) 2012-2013 Oleh Hapon ohapon@users.sourceforge.net
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
package org.plazmaforge.framework.config.object;

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.config.ConfigUtils;



/**
 * 
 * @author ohapon
 *
 */
public class EntityConfig extends ObjectConfig implements IEntityConfig {

    private static final long serialVersionUID = -5780049765405221951L;
    

    private IConfigIdentifier entityTypeIdentifier;

    private String entityClass;

    private String entityPackage;

    private boolean enable;

    private boolean master;

    private boolean bean;

    private boolean form;

    private boolean secure;

    private boolean system;
    
    private boolean custom;
    
    private boolean customAttribute;
    
    
    private List attributes;
    
    
    private boolean cache;
    
    private long cacheExpireTime;
    
    //TODO: STUB
    // id = mypackage.MyEntity
    // simpleUID  = MY_ENTYTY: Upper case simple id
    // simpleCode = MyEntity: Java style code
    
    private String simpleUID;
    
    private String simpleCode;
    

    public EntityConfig() {
    }

    public EntityConfig(String id, String code) {
	this.setId(id);
	setCode(code);
    }

    public EntityConfig(String id, String code, String name) {
	this.setId(id);
	setCode(code);
	setName(name);
    }

    public void setId(String id) {
	super.setId(id);
	
	//TODO: STUB
	if (id == null) {
	    simpleUID = null;
	} else {
	    simpleUID = getNormalizeString(getId());
	    int index = simpleUID.lastIndexOf('.');
	    if (index > -1 && simpleUID.length() > 1) {
		simpleUID = simpleUID.substring(index + 1);
	    }
	    if (!ConfigUtils.isUpperString(simpleUID)) {
		simpleUID = ConfigUtils.generateIdByCode(simpleUID);
	    }
	}
	simpleCode = ConfigUtils.generateCodeById(simpleUID);
    }
    
    public String getSimpleCode() {
        return simpleCode;
    }

    public String getConfigSimpleCode() {
	return getSimpleCode();
    }
	
    public String toString() {
	return getName();
    }

    public IConfigIdentifier getEntityTypeIdentifier() {
	return entityTypeIdentifier;
    }

    public void setEntityTypeIdentifier(IConfigIdentifier entityTypeIdentifier) {
	this.entityTypeIdentifier = entityTypeIdentifier;
    }

   
    public IEntityTypeConfig getEntityType() {
	return (IEntityTypeConfig) getEntityTypeIdentifier();
    }
    
    public String getEntityTypeName() {
	return getEntityType() == null ? null : getEntityType().getName();
    }
    
    public void setEntityType(IEntityTypeConfig entityTypeConfig) {
	setEntityTypeIdentifier(entityTypeConfig);
    }

    public String getEntityClass() {
	return entityClass;
    }

    public void setEntityClass(String entityClass) {
	this.entityClass = entityClass;
    }

    public String getEntityPackage() {
	return entityPackage;
    }

    public void setEntityPackage(String entityPackage) {
	this.entityPackage = entityPackage;
    }

    public boolean isEnable() {
	return enable;
    }

    public void setEnable(boolean enable) {
	this.enable = enable;
    }

    public boolean isMaster() {
	return master;
    }

    public void setMaster(boolean master) {
	this.master = master;
    }

    public boolean isBean() {
	return bean;
    }

    public void setBean(boolean bean) {
	this.bean = bean;
    }

    public boolean isForm() {
	return form;
    }

    public void setForm(boolean form) {
	this.form = form;
    }

    public boolean isSecure() {
	return secure;
    }

    public void setSecure(boolean secure) {
	this.secure = secure;
    }

    public boolean isSystem() {
	return system;
    }

    public void setSystem(boolean system) {
	this.system = system;
    }

    public boolean isCustom() {
        return custom;
    }

    public void setCustom(boolean custom) {
        this.custom = custom;
    }

    public boolean isCustomAttribute() {
        return customAttribute;
    }

    public void setCustomAttribute(boolean customAttribute) {
        this.customAttribute = customAttribute;
    }

    public boolean isCustomize() {
	return isCustom() || isCustomAttribute();
    }
    
    public List getAttributes() {
	if (attributes == null) {
	    attributes = new ArrayList();
	}
        return attributes;
    }

    public void setAttributes(List attributes) {
        this.attributes = attributes;
    }

    /**
     * Return true if the entity supports cache
     */
    public boolean isSupportCache() {
	return isEnable() && isMaster() && !isSystem();
    }
    
    
    public boolean isCache() {
        return cache;
    }

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    public long getCacheExpireTime() {
        return cacheExpireTime;
    }

    public void setCacheExpireTime(long cacheExpireTime) {
        this.cacheExpireTime = cacheExpireTime;
    }
    
    protected String getNormalizeString(String str) {
	return str == null ? null : str.trim();
    }
    
}
