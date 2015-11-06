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

import java.io.Serializable;

import org.plazmaforge.framework.util.StringUtils;


/**
 * @author ohapon
 */
public class EntityObject<PK extends Serializable> implements IEntityObject<PK> {

    private static final long serialVersionUID = 8915083568855553058L;

    private int state = Statable.NORMAL;
    
    private PK id;

    private boolean ditry;
    
    /**
     * Special flag to mark system entity
     * User can not change system entity 
     */
    private boolean system;

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

    public PK getId() {
        return id;
    }

    public void setId(PK id) {
        this.id = id;
    }
    

    public boolean isDitry() {
        return ditry;
    }

    public boolean equals(Object o) {
        
        if (o == null) {
            return false;
        }
        
        if (this == o) {
            return true;
        }
       
        if (o instanceof EntityObject) {
            Object key1 = getId();
            Object key2 = ((EntityObject) o).getId();
            if (key1 == null || key2 == null) {
                return false;
            }
            if (key1 == key2) {
                return true;
            }
            return equalsId(key1, key2);
        }
      
        return false;
    }
    
    protected boolean equalsId(Object id1, Object id2) {
	return id1.equals(id2);
    }
    
    /**
     * Initialize
     */
    public void init() {
	// do nothing
    }

    /**
     * Destroy
     */
    public void destroy() {
	// do nothing
    }

    /**
     * Return true if the entity is sytem entity
     * @return
     */
    public boolean isSystem() {
        return system;
    }

    /**
     * Set system entity flag
     * @param system
     */
    public void setSystem(boolean system) {
        this.system = system;
    }
    
    
    protected boolean isEmpty(String str) {
	return StringUtils.isEmpty(str);
    }

}
