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

package org.plazmaforge.framework.uwt.jfx.adapter;

import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.widget.menu.Menu;


/**
 * 
 * @author ohapon
 *
 */

public class JFXMenuAdapter extends JFXWidgetAdapter {

    public Object createDelegate(UIElement parent, UIElement element) {
	Object xParent = parent.getDelegate();
	javafx.scene.control.Menu xMenu = new javafx.scene.control.Menu();
	
	Menu menu = (Menu) element;
	
	// Get text
	String text = menu.getText();
	if (text == null) {
	    text = "";
	}
	xMenu.setText(text);
	
	//TODO
	// Get image
	//ImageResource xImage = createImage(element, menu.getIcon());
	//if (xImage != null) {
	//  xMenuItem.setIcon(xImage);
	//}
	
	if (xParent instanceof javafx.scene.control.MenuBar) {
	    ((javafx.scene.control.MenuBar) xParent).getMenus().addAll(xMenu);
	    ((javafx.scene.control.MenuBar) xParent).layout();
	} else if (xParent instanceof javafx.scene.control.Menu) {
	    ((javafx.scene.control.Menu) xParent).getItems().addAll(xMenu);
	    ((javafx.scene.control.Menu) xParent).fire();
	}
	
	return xMenu;
    }

}
