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
import org.plazmaforge.framework.config.object.IEntityConfigurable;

public class ComplexEntity extends BaseEntity implements IAttributeHolder, IFileHolder {

    private static final long serialVersionUID = -2486385904567863851L;

    private AttributeContainer attributeContainer;
    
    private FileContainer fileContainer;
    
    
    /////////////////////////////////////////////////////////////////////////////////////////
    //
    // Attribute Container block
    //
    /////////////////////////////////////////////////////////////////////////////////////////
    
    public Integer getAttributeOwnerId() {
	return getId();
    }
    
    public IEntityConfig getAttributeEntity() {
	if (this instanceof IEntityConfigurable) {
	    return ((IEntityConfigurable) this).getEntity();
	}
	return null;
    }
    
    public AttributeContainer getAttributeContainer() {
	if (attributeContainer == null) {
	    attributeContainer = new AttributeContainer();
	}
        return attributeContainer;
    }

    public void setAttributeContainer(AttributeContainer attributeContainer) {
        this.attributeContainer = attributeContainer;
    }

    
    public List<Attribute> getDeclareAttributes() {
	return getAttributeContainer().getDeclareAttributes();
    }

    public void setDeclareAttributes(List<Attribute> declareAttributes) {
	getAttributeContainer().setDeclareAttributes(declareAttributes);
    }
    

    public List<Attribute> getUnusedAttributes() {
	return getAttributeContainer().getUnusedAttributes();
    }

    public AttributeValue getAttribute(String code) {
	return getAttributeContainer().getAttribute(code);
    }
    
    public Object getAttributeValue(String code) {
	return getAttributeContainer().getAttributeValue(code);
    }

    public void setAttributeValue(String code, Object value) {
	getAttributeContainer().setAttributeValue(code, value);
    }
    
    public List<AttributeValue> getAttributes() {
	return getAttributeContainer().getAttributes();
    }

    public void setAttributes(List<AttributeValue> attributes) {
	 getAttributeContainer().setAttributes(attributes);
    }

    public void addAttribute(AttributeValue attribute) {
	getAttributeContainer().addAttribute(attribute);
    }
    
    public boolean removeAttribute(AttributeValue attribute) {
	return getAttributeContainer().removeAttribute(attribute);
   }

    /////////////////////////////////////////////////////////////////////////////////////////
    //
    // File Container block
    //
    /////////////////////////////////////////////////////////////////////////////////////////
    
    public Integer getFileOwnerId() {
	return getId();
    }
    
    public IEntityConfig getFileEntity() {
	if (this instanceof IEntityConfigurable) {
	    return ((IEntityConfigurable) this).getEntity();
	}
	return null;
    }
    
    public FileContainer getFileContainer() {
	if (fileContainer == null) {
	    fileContainer = new FileContainer();
	}
        return fileContainer;
    }

    public void setFileContainer(FileContainer fileContainer) {
        this.fileContainer = fileContainer;
    }

    public List<File> getFiles() {
	return getFileContainer().getFiles();
    }

    public void setFiles(List<File> files) {
	 getFileContainer().setFiles(files);
    }

    public void addFile(File file) {
	getFileContainer().addFile(file);
    }
    
    public boolean removeFile(File file) {
	return getFileContainer().removeFile(file);
   }
}
