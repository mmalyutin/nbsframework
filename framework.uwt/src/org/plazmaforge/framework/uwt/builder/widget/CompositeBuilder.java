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

package org.plazmaforge.framework.uwt.builder.widget;

import java.util.List;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.builder.IUIBuilder;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Layout;
import org.plazmaforge.framework.uwt.widget.Widget;

/**
 * 
 * @author ohapon
 *
 */
public class CompositeBuilder extends ControlBuilder {

    @Override
    public UIObject buildObject(IData data) {
	if (data == null) {
	    return null;
	}
	Composite composite = new Composite();
	populate(data, composite);
	return composite;
    }
    
    @Override
    protected void populate(IData data, UIObject element) {
	if (data == null) {
	    return;
	}
	populateCommon(data, element);
	populateBody(data, element);
    }
    
    protected void populateCommon(IData data, UIObject element) {
	super.populate(data, element);
    }
    
    protected void populateBody(IData data, UIObject element) {
	Composite composite = (Composite) element;
	populateLayout(data, composite);
	assignResource(data, element);
	populateContentChildren(data, composite);	
    }
    
    protected Layout getLayout(IData data) {
	Object value = getValue(data, Composite.PROPERTY_LAYOUT);
	if (value == null) {
	    return null;
	}
	if (!(value instanceof String)) {
	    return null;
	}
	IData layoutData = createData((String) value);
	if (layoutData == null) {
	    return null;
	}
	IUIBuilder builder = getBuilder(layoutData);
	return (builder == null) ? null : (Layout) builder.buildObject(layoutData);
    }

    protected void populateLayout(IData data, Composite composite) {
	Layout layout = getLayout(data);
	if (layout == null) {
	    return;
	}
	composite.setLayout(layout);
    }
    
    protected void populateContentChildren(IData data, Composite composite) {
	List<IData> children = getChildrenOfNode(data, Composite.PROPERTY_CHILDREN);
	populateContentChildren(children, composite);
    }
    
    protected void populateContentChildren(List<IData> children, Composite composite) {
	 if (children == null || children.isEmpty()) {
	     return;
	 }
	 for (IData child: children) {
	     Widget childWidget = (Widget) buildDataObject(child);
	     composite.add(childWidget);
	 }
    }
    
   

}
