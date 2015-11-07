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

package org.plazmaforge.framework.uwt.generator.widget;

import static org.plazmaforge.framework.uwt.event.Events.KeyDown;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.generator.ScopeContext;
import org.plazmaforge.framework.uwt.generator.SourceWriter;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Control;

public class ControlGenerator extends WidgetGenerator {

    @Override
    public void generatePopulate(ScopeContext context, IData data, String bean, SourceWriter sw) {
	
	super.generatePopulate(context, data, bean, sw);
	
	generateSetColorProperty(context, data,  bean, Control.PROPERTY_BACKGROUND, sw);
	generateSetColorProperty(context, data,  bean, Control.PROPERTY_FOREGROUND, sw);
	generateSetFontProperty(context, data, bean, Control.PROPERTY_FONT, sw);
	
	generateSetIntegerProperty(context, data, bean, Control.PROPERTY_X, sw);
	generateSetIntegerProperty(context, data, bean, Control.PROPERTY_Y, sw);

	generateSetIntegerProperty(context, data, bean, Control.PROPERTY_WIDTH, sw);
	generateSetIntegerProperty(context, data, bean, Control.PROPERTY_HEIGHT, sw);
	
	generateSetBooleanProperty(context, data, bean, Control.PROPERTY_VISIBLE, sw);
	generateSetBooleanProperty(context, data, bean, Control.PROPERTY_ENABLED, sw);
	
	generateSetRSStringProperty(context, data, bean, Control.PROPERTY_TOOL_TIP, "setToolTip", sw);

	generateSetData(context, "%layoutData", data, bean, Composite.PROPERTY_LAYOUT_DATA, null, sw);
	
	// TODO: Populate Control properties:
  	// - LayoutData
  	// - ContextMenu
    }
    
    @Override
    protected void generateAddListener(ScopeContext context, String bean, String type, String handleString, SourceWriter sw) {
	
	if (eq(type, KeyDown)) {
	    generateAddKeyDownListener(context, bean, handleString, sw);
	    return;
	} else if (eq(type, Events.KeyUp)) {
	    generateAddKeyUpListener(context, bean, handleString, sw);
	    return;
	} else if (eq(type, Events.MouseClick)) {
	    generateAddMouseClickListener(context, bean, handleString, sw);
	    return;
	} else if (eq(type, Events.MouseDoubleClick)) {
	    generateAddMouseDoubleClickListener(context, bean, handleString, sw);
	    return;
	} else if (eq(type, Events.MouseDown)) {
	    generateAddMouseDownListener(context, bean, handleString, sw);
	    return;
	} else if (eq(type, Events.MouseUp)) {
	    generateAddMouseUpListener(context, bean, handleString, sw);
	    return;
	} else if (eq(type, Events.FocusIn)) {
	    generateAddFocusInListener(context, bean, handleString, sw);
	    return;
	} else if (eq(type, Events.FocusOut)) {
	    generateAddFocusOutListener(context, bean, handleString, sw);
	    return;
	}

	super.generateAddListener(context, bean, type, handleString, sw);
    }

}
