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



/**
 * @author ohapon
 * 
 */
public class ObjectConfig implements IObjectConfig {

    private static final long serialVersionUID = -8076071784027483965L;

    private int state = NORMAL;
    
    private boolean ditry;
    

    
    /** Id **/
    private String id;

    /** Code **/
    private String code;

    /** Name **/
    private String name;

    /** Caption **/
    private String caption;
    
    /** Description **/
    private String description;

    /** Type (replace class) **/
    private String type;
    
    /** Class name **/
    private String className;


    /** Category (example UI category) **/
    private String category;
    
    
    /** Enabled mode **/
    private boolean enabled = true;
    
    
    private IPackageConfig packageConfig;
    
    
    private boolean modify;
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
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

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }


    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public String getClassName() {
	return className;
    }

    public void setClassName(String className) {
	this.className = className;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public IPackageConfig getPackageConfig() {
        return packageConfig;
    }

    public void setPackageConfig(IPackageConfig packageConfig) {
        this.packageConfig = packageConfig;
    }
    
    public String getConfigId() {
	return getId();
    }

    public void setConfigId(String configId) {
	setId(configId);
    }
    
    public String getConfigName() {
	return getName();
    }

    public void setConfigName(String configName) {
	setName(configName);
    }
    
    public String toString() {
	return "id=" + id 
		+ ", code=" + code 
		+ ", name=" + name 
		+ ", caption=" + caption 
		+ ", description=" + description
		+ ", type=" + type 
		+ ", className=" + className 
		+ ", category=" + category;
    }
    
    public boolean equals(Object o) {
        
        if (o == null) {
            return false;
        }
        
        if (this == o) {
            return true;
        }
       
        if (!(o instanceof ObjectConfig)) {
            return false;
        }    
        ObjectConfig c = (ObjectConfig) o;
        
        if (!equals(c.getId(), getId())) {
            return false;
        }    
        if (!equals(c.getCode(), getCode())) {
            return false;
        }
        if (!equals(c.getName(), getName())) {
            return false;
        }
        if (!equals(c.getCaption(), getCaption())) {
            return false;
        }
        if (!equals(c.getDescription(), getDescription())) {
            return false;
        }
        if (!equals(c.getClassName(), getClassName())) {
            return false;
        }
        if (!equals(c.getType(), getType())) {
            return false;
        }
        if (!equals(c.getCategory(), getCategory())) {
            return false;
        }
        if (!equals(c.getPackageConfig(), getPackageConfig())) {
            return false;
        }

        if (c.isEnabled() != isEnabled()) {
            return false;
        }

        return true;
    }
    
    protected boolean equals(Object o1, Object o2) {
	if (o1 == null && o2 == null) {
	    return true;
	}
	if (o1 == null || o2 == null) {
	    return false;
	}
	return o1.equals(o2);
    }


    public boolean isModify() {
        return modify;
    }


    public void setModify(boolean modify) {
        this.modify = modify;
    }
    
    public void setModify() {
	setModify(true);
    }
    
    public void fireModifyObject(IObjectConfig obj) {
	//TODO: Must implement notifier
	//if (getConfigurer() == null) {
	//   return;
	//}
	//getConfigurer().fireModifyObject(obj);
    }


    /**
     * Is instance in NORMAL state?
     */
    public boolean isNormal() {
        return state == NORMAL;
    }

    /**
     * Is instance in MODIFIED state?
     */
    public boolean isModified() {
        return state == MODIFIED;
    }

    /**
     * Is instance in DELETED state?
     */
    public boolean isDeleted() {
        return state == DELETED;
    }

    /**
     * Is instance in CREATED state?
     */
    public boolean isCreated() {
        return state == CREATED;
    }




    /**
     * Turn the details' state to NORMAL.
     */
    public void setNormal() {
        ditry = false;
        state = NORMAL;
    }

    /**
     * Turn the details' state to MODIFIED.
     */
    public void setModified() {
        if (isNormal()) {
            state = MODIFIED;
        }
    }

    /**
     * Turn the details' state to DELETED.
     */
    public void setDeleted() {
        // DELETE operation has HIGH priority
        // so always marks as deleted
        if (isCreated()) {
            ditry = true;
        }
        state = DELETED;
    }

    /**
     * Turn the details' state to CREATED.
     */
    public void setCreated() {
        if (isNormal()) {
            state = CREATED;
        }
    }
    
    public boolean isDitry() {
        return ditry;
    }

}
