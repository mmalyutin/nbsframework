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

import java.util.List;

import org.plazmaforge.framework.config.object.IEntityConfig;
import org.plazmaforge.framework.core.exception.ApplicationException;

/**
 * 
 * @author ohapon
 *
 */
public class AttributeHelper {

    public static interface AttributeManager {
	
	Integer doInsertAttribute(Attribute attribute) throws ApplicationException;

	List<Attribute> doFindAttributesByEntity(IEntityConfig entity) throws ApplicationException;

	List<AttributeValue> doFindByOwner(IEntityConfig entity, Integer ownerId) throws ApplicationException;
	
	
	void doInsertAttributeValue(AttributeValue attributeValue) throws ApplicationException;
	    
	void doUpdateAttributeValue(AttributeValue attributeValue) throws ApplicationException;
	    
	void doDeleteAttributeValue(AttributeValue attributeValue) throws ApplicationException;
   }
   
    private AttributeManager attributeManager;
    
    public AttributeHelper(AttributeManager attributeManager) {
	super();
	this.attributeManager = attributeManager;
    }

    public List<AttributeValue> getAttributes(IAttributeHolder holder) throws ApplicationException {
	if (holder == null) {
	    return null;
	}
	IEntityConfig entity = holder.getAttributeEntity();
	Integer ownerId = holder.getAttributeOwnerId();
	if (entity == null || ownerId == null) {
	    return null;
	}
	List<AttributeValue> attributeValues = attributeManager.doFindByOwner(entity, ownerId);
	return attributeValues;
    }

    /**
     * Load attribute values from holder
     * @param holder
     * @throws ApplicationException
     */
    public void loadAttributeValues(IAttributeHolder holder) throws ApplicationException {
	if (holder == null) {
	    return;
	}
	IEntityConfig entity = holder.getAttributeEntity();
	Integer ownerId = holder.getAttributeOwnerId();
	if (entity == null || ownerId == null) {
	    return;
	}
	List<Attribute> declareAttributes = attributeManager.doFindAttributesByEntity(entity);
	List<AttributeValue> attributeValues = attributeManager.doFindByOwner(entity, ownerId);
	holder.setDeclareAttributes(declareAttributes);
	holder.setAttributes(attributeValues);
    }
    
    /**
     * Save (Synchronize: Insert/Update/Delete) attribute values
     * @param holder
     * @throws ApplicationException
     */
    public void saveAttributeValues(IAttributeHolder holder) throws ApplicationException {
	if (holder == null) {
	    return;
	}
	List<AttributeValue> attributeValues = holder.getAttributes();
	for (AttributeValue attrValue : attributeValues) {
	    Integer id = attrValue.getId();
	    if (id == null) {
		if (attrValue.isCreated()) {
		    Attribute attr = attrValue.getAttribute();
		    
		    // INSERT NEW ATTRIBUTE
		    if (attr != null && attr.getId() == null) {
			attr.setEntity(holder.getAttributeEntity());
			attributeManager.doInsertAttribute(attr);
		    }
		    
		    // INSERT NEW ATTRIBUTE VALUE
		    attrValue.setOwnerId(holder.getAttributeOwnerId());
		    attrValue.setEntity(holder.getAttributeEntity());
		    attributeManager.doInsertAttributeValue(attrValue);
		}
	    } else {
		if (attrValue.isDeleted()) {
		    // DELETE ATTRIBUTE VALUE
		    attributeManager.doDeleteAttributeValue(attrValue);
		} else {
		    // UPDATE ATTRIBUTE VALUE
		    attributeManager.doUpdateAttributeValue(attrValue);
		}
	    }
	}
	
	// GET ATTRIBUTE VALUES FROM DB
	List<AttributeValue> dbAttributeValues = getAttributes(holder);
	if (dbAttributeValues == null) {
	    return;
	}
	
	// DELETE ALL NOT FOUND ATTRIBUTE VALUES
	for (AttributeValue dbAttrValue : dbAttributeValues) {
	    Integer id = dbAttrValue.getId();
	    boolean found = false;
	    for (AttributeValue attrVal : attributeValues) {
		if (id.equals(attrVal.getId())) {
		    found = true;
		}
	    }
	    if (!found) {
		attributeManager.doDeleteAttributeValue(dbAttrValue);
	    }
	}
	
    }

    /**
     * Delete attribute values from holder
     * @param holder
     * @throws ApplicationException
     */
    public void deleteAttributeValues(IAttributeHolder holder) throws ApplicationException {
	if (holder == null) {
	    return;
	}
	List<AttributeValue> attributeValues = getAttributes(holder);
	if (attributeValues == null || attributeValues.size() == 0) {
	    return;
	}
	for (AttributeValue attrVal : attributeValues) {
	    attributeManager.doDeleteAttributeValue(attrVal);
	}
    }    
}
