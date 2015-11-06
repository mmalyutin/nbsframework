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

package org.plazmaforge.framework.database.structure;

import java.util.HashMap;
import java.util.Map;



/** 
 * @author ohapon
 */

public class DBObject implements IDBObject {

    /**
     * Indicates normal state.
     */
    int NORMAL = 0;

    /**
     * Indicates modified state.
     */
    int MODIFIED = 1;

    /**
     * Indicates deleted state.
     */
    int DELETED = 2;

    /**
     * Indicates that this details was newly created.
     */
    int CREATED = 3;

    
    private String id;
    
    private String name;
    
    private String type;

    private Object data;
    
    
    
    
    private Map<Object, Object> propertyMap = new HashMap<Object, Object>();
    
    
    public DBObject() {
	super();
    }

    
    public DBObject(String type, String id, String name) {
	super();
	this.type = type;
	this.id = id;
	this.name = name;
    }

    //

    private int state = NORMAL;
    

    /**
     * Is intance in NORMAL state?
     */
    public boolean isNormal() {
        return state == NORMAL;
    }

    /**
     * Is intance in MODIFIED state?
     */
    public boolean isModified() {
        return state == MODIFIED;
    }

    /**
     * Is intance in DELETED state?
     */
    public boolean isDeleted() {
        return state == DELETED;
    }

    /**
     * Is intance in CREATED state?
     */
    public boolean isCreated() {
        return state == CREATED;
    }




    /**
     * Turn the details' state to NORMAL.
     */
    public void setNormal() {
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
    
    
    //
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void generateId() {
	id = Integer.toHexString(System.identityHashCode(this));
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    

    public Object getData() {
        return data;
    }


    public void setData(Object data) {
        this.data = data;
    }    
    
    public Object getData(Object key) {
	return propertyMap.get(key);
    }
    
    public void setData(Object key, Object data) {
	propertyMap.put(key, data);
    }
    
    public String toString( ){
	return name == null ? super.toString() : name;
    }
    
    public int hashCodeById() {
	return id == null ? super.hashCode() : id.hashCode(); 
    }
    
    public boolean equalsById(Object obj) {
	if (obj == null) {
	    return false;
	}
	if (obj == this) {
	    return true;
	}
	if (!(obj instanceof DBObject)) {
	    return false;
	}
	DBObject dbObj = (DBObject) obj;
	String data1 = this.getId();
	String data2 = dbObj.getId();
	if (data1 == null && data2 == null) {
	    return true;
	}
	if (data1 == null || data2 == null) {
	    return false;
	}
	return data1.equals(data2);
    }
}
