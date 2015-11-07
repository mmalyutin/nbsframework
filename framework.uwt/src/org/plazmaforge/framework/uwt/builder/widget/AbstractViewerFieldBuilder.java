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

/**
 * 
 */
package org.plazmaforge.framework.uwt.builder.widget;

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.builder.UIBuilder;
import org.plazmaforge.framework.uwt.widget.IField;

/**
 * @author ohapon
 *
 */
public abstract class AbstractViewerFieldBuilder extends AbstractFieldBuilder {

    
    protected abstract void populateItems(IData data, UIObject element);
    
    @Override
    protected void populate(IData data, UIObject element) {
	super.populate(data, element);
	populateItems(data, element);
    }
    
    @Override
    protected void populateValue(IData data, UIObject element) {
	IField field = (IField) element;
	populateFieldValue(data, field);
    }
    
    protected List<IData> getDataItems(IData data) {
	return getChildrenOfNode(data, "items");
    }
    
    protected <T> List<T> getItems(Class<T> tClass, IData data, UIObject element) {
	List<IData> items = getDataItems(data);
	if  (items == null) {
	    return null;
	}
	List<T> list = new ArrayList<T>();
	for (IData item: items) {
	    T object = getItem(tClass, item);
	    list.add(object);
	}
	return list;
    }
    
    protected <T> T getItem(Class<T> tClass, IData item) {
	String s = (String) item.get(UIBuilder.SYS_PROPERTY_VALUE);
	return (T) s;
    }
    
}
