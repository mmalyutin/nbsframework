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
package org.plazmaforge.framework.uwt.builder.widget.tool;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.builder.widget.AbstractItemBuilder;
import org.plazmaforge.framework.uwt.widget.tool.ToolItem;

/**
 * @author ohapon
 *
 */
public class ToolItemBuilder extends AbstractItemBuilder {

    @Override
    public UIObject buildObject(IData data) {
	if (data == null) {
	    return null;
	}
	ToolItem toolItem = new ToolItem();
	populate(data, toolItem);
	return toolItem;
    }
    
    @Override
    protected void populate(IData data, UIObject element) {
	if (data == null) {
	    return;
	}
	super.populate(data, element);
	
	
	ToolItem toolItem = (ToolItem) element;
	
  	Boolean enabled = getBoolean(data, ToolItem.PROPERTY_ENABLED);
  	if (enabled != null) {
  	    toolItem.setEnabled(enabled);
  	}

  	String toolTip = getRSString(data, ToolItem.PROPERTY_TOOL_TIP);
  	if (toolTip != null) {
  	    toolItem.setToolTip(toolTip);
  	}
	
	String command = getString(data, ToolItem.PROPERTY_COMMAND);
	if (command != null) {
	    toolItem.setCommand(command);
	}
    }    
}
