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
package org.plazmaforge.framework.uwt.builder.widget.menu;

import java.util.List;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.builder.IUIBuilder;
import org.plazmaforge.framework.uwt.builder.UIBuilder;
import org.plazmaforge.framework.uwt.widget.menu.Menu;
import org.plazmaforge.framework.uwt.widget.menu.MenuItem;

/**
 * @author ohapon
 *
 */
public class MenuBuilder extends MenuItemBuilder {

    @Override
    public UIObject buildObject(IData data) {
	if (data == null) {
	    return null;
	}
	Menu menu = new Menu();
	populate(data, menu);
	return menu;
    }
    
    @Override
    protected void populate(IData data, UIObject element) {
	if (data == null) {
	    return;
	}
	
	super.populate(data, element);
	
	Menu menu = (Menu) element;
	
	List<IData> children = (List<IData>) getValue(data, UIBuilder.SYS_PROPERTY_CHILDREN);
	populateMenu(children, menu);
	
    }
    
    protected void populateMenu(List<IData> children, Menu menu) {
	 if (children == null || children.isEmpty()) {
	     return;
	 }
	 for (IData child: children) {
	     IUIBuilder builder = getBuilder(child);
	     if (builder == null) {
		 //ERROR
		 continue;
	     }
	     MenuItem childWidget = (MenuItem) builder.buildObject(child);
	     menu.addItem(childWidget);
	     
	 }
    }

}
