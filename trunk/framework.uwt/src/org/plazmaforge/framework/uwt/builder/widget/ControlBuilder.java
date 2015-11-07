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

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.builder.IUIBuilder;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.LayoutData;

public class ControlBuilder extends WidgetBuilder {

    protected void populate(IData data, UIObject element) {
  	if (data == null) {
  	    return;
  	}
  	
  	super.populate(data, element);
  	
  	Control control = (Control) element;
  	Color background  = getColor(data, Control.PROPERTY_BACKGROUND);
  	if (background != null) {
  	    control.setBackground(background);
  	}
  	Color foreground  = getColor(data, Control.PROPERTY_FOREGROUND);
  	if (foreground != null) {
  	    control.setForeground(foreground);
  	}
  	Font font = getFont(data, Control.PROPERTY_FONT);
  	if (font != null) {
  	    control.setFont(font);
  	}

  	////
  	
  	Integer x = getInteger(data, Control.PROPERTY_X);
  	if (x != null) {
  	    control.setX(x);
  	}
  	
  	Integer y = getInteger(data, Control.PROPERTY_Y);
  	if (y != null) {
  	    control.setY(y);
  	}

  	Integer width = getInteger(data, Control.PROPERTY_WIDTH);
  	if (width != null) {
  	    control.setWidth(width);
  	}

  	Integer height = getInteger(data, Control.PROPERTY_HEIGHT);
  	if (height != null) {
  	    control.setHeight(height);
  	}
  	
  	////
  	
  	Boolean visible = getBoolean(data, Control.PROPERTY_VISIBLE);
  	if (visible != null) {
  	    control.setVisible(visible);
  	}

  	Boolean enabled = getBoolean(data, Control.PROPERTY_ENABLED);
  	if (enabled != null) {
  	    control.setEnabled(enabled);
  	}

  	
  	////
  	
  	String toolTip = getRSString(data, Control.PROPERTY_TOOL_TIP);
  	if (toolTip != null) {
  	    control.setToolTip(toolTip);
  	}
  	
  	////

  	LayoutData layoutData = getLayoutData(data);
	if (layoutData != null) {
	    control.setLayoutData(layoutData);
	}
	
  	// TODO: Populate Control properties:
  	// - ContextMenu
  	
    }
    
    
    protected LayoutData getLayoutData(IData data) {
	Object value = getValue(data, Composite.PROPERTY_LAYOUT_DATA);
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
	return (builder == null) ? null : (LayoutData) builder.buildObject(layoutData);
    }


}
