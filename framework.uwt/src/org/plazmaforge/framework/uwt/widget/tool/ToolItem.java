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

package org.plazmaforge.framework.uwt.widget.tool;

import org.plazmaforge.framework.uwt.action.ActionItem;
import org.plazmaforge.framework.uwt.event.SelectionListener;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.widget.Item;
import org.plazmaforge.framework.uwt.widget.Widget;

public class ToolItem extends Item implements ActionItem {

    
    /**
     * ToolTip of control
     */
    private String toolTip;
    
    /**
     * True if control is enabled
     */
    private boolean enabled = true;
    
    
    private String command;
    
    
    public ToolItem() {
    }

    public ToolItem(String text) {
	super(text);
    }

    public ToolItem(Image icon) {
	super(icon);
    }

    public ToolItem(String text, Image icon) {
	super(text, icon);
    }

    
    public void setParent(Widget parent) {
  	if (!(parent instanceof ToolBar)) {
  	    throw new IllegalArgumentException("Parent must be ToolBar");
  	}
  	super.setParent(parent);
    }

    public ToolBar getToolBar() {
	return (ToolBar) getParent();
    }

    ////

    
    public String getToolTip() {
        return toolTip;
    }

    public void setToolTip(String toolTip) {
        this.toolTip = toolTip;
        fireChangeProperty(PROPERTY_TOOL_TIP, toolTip);
    }
    
    
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        fireChangeProperty(PROPERTY_ENABLED, enabled);
    }
    
    
    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void addSelectionListener(SelectionListener listener) {
	addSelectionListener(this, listener);
    }

    public void removeSelectionListener(SelectionListener listener) {
	removeSelectionListener(this, listener);
    }

}
