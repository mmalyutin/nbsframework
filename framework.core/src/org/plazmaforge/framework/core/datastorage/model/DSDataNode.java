/*
 * Copyright (C) 2012-2015 Oleh Hapon ohapon@users.sourceforge.net
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

/**
 * 
 */
package org.plazmaforge.framework.core.datastorage.model;

import java.util.List;
import java.util.UUID;

import org.plazmaforge.framework.core.datastorage.DSResultSet;

/**
 * DSDataNoode describes node of DSDataModel
 * 
 *         +-----------+
 *         |DSDataNode |           DSDataLevel 1
 *         +-----------+                | previous
 *           |       |                  |
 *           |       |                  |
 *           |       |                  |
 * +-----------+   +-----------+        | next
 * |DSDataNode |   | DSDataNode|   DSDataLevel 2
 * +-----------+   +-----------+
 *
 * 
 * @author ohapon
 *
 */
public class DSDataNode {

    private DSDataItem dataItem;
    
    private DSDataNode parent;
    
    private List<DSDataNode> children;

    private String id;
    
    // Field index offset
    private int fieldOffset;
    
    public DSDataNode() {
	super();
	id = UUID.randomUUID().toString();
    }
    
    public String getId() {
        return id;
    }

    public DSDataItem getDataItem() {
        return dataItem;
    }

    public void setDataItem(DSDataItem dataItem) {
        this.dataItem = dataItem;
    }

    public DSDataNode getParent() {
        return parent;
    }

    public void setParent(DSDataNode parent) {
        this.parent = parent;
    }

    public List<DSDataNode> getChildren() {
        return children;
    }

    public void setChildren(List<DSDataNode> children) {
        this.children = children;
    }

    public int getFieldOffset() {
        return fieldOffset;
    }

    public void setFieldOffset(int fieldOffset) {
        this.fieldOffset = fieldOffset;
    }

    public DSResultSet getResultSet() {
	return dataItem == null ? null : dataItem.getResultSet();
    }

    
    

}
