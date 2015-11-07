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

package org.plazmaforge.framework.uwt.gxt.adapter;

import java.util.Collection;
import java.util.Map;

import org.plazmaforge.framework.uwt.widget.table.TableItem;

import com.extjs.gxt.ui.client.data.ModelData;

public class GXTTableItemModel implements ModelData {

    
    private TableItem item;

    public GXTTableItemModel(TableItem item) {
	this.item = item;
    }

    public TableItem getItem() {
        return item;
    }

    ////
    
    protected int indexOfProperty(String property) {
	try {
	    return property == null ? -1: Integer.valueOf(property);
	} catch (NumberFormatException ex) {
	    return -1;
	}
    }
    
    ////
    
    
    @Override
    public <X> X get(String property) {
	if ("text".equals(property) ) {
	    return (X) getText();
	}
	return (X) getText(indexOfProperty(property));
    }

    @Override
    public <X> X set(String property, X value) {
	if ("text".equals(property) ) {
	    setText((String) value);
	}
	int index = indexOfProperty(property);
	Object oldValue = getText(index);
	setText(index, (String) value);
	return (X) oldValue;
    }

    @Override
    public Map<String, Object> getProperties() {
	return null;
    }

    @Override
    public Collection<String> getPropertyNames() {
	return null;
    }

    @Override
    public <X> X remove(String property) {
	return null;
    }

    ////
    
    private String getText() {
	return item.getText();
    }

    private String getText(int index) {
	return index < 0 ? null : item.getText(index);
    }

    private void setText(String text) {
	item.setText(text);
    }

    private void setText(int index, String text) {
	if (index < 0) {
	    return;
	}
	item.setText(index, text);
    }

}
