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

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.util.CoreUtils;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Layout;
import org.plazmaforge.framework.uwt.widget.Widget;


public class ToolBar extends Composite {

    @Override
    public void setLayout(Layout layout) {
	// ignore
    }
    

    @Override
    protected void checkChild(Widget element) {
	if (!(element instanceof ToolItem)) {
	    throw new IllegalArgumentException("Child must be ToolItem");
	}
    }
    
    public List<ToolItem> getItems() {
	List<Widget> children = getChildren();
	List<ToolItem> items = new ArrayList<ToolItem>();
	CoreUtils.transferList(children, items);
	return items;
	
    }

    public void addItem(ToolItem item) {
	super.add(item);
    }

    public void removeItem(ToolItem item) {
	super.remove(item);
    }

    public void addSeparator() {
	addItem(new ToolSeparator());
    }
    
    public boolean hasItems() {
	return super.hasChildren();
    }


}
