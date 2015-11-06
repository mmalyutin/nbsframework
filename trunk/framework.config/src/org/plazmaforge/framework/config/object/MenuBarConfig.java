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


package org.plazmaforge.framework.config.object;

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.config.ConfigUtils;




/**
 * 
 * @author ohapon
 *
 */
public class MenuBarConfig extends UIObjectConfig implements IMenuBarConfig {

    private static final long serialVersionUID = 8663848320462411710L;
    
    private List<IMenuItemConfig> children = new ArrayList<IMenuItemConfig>();

    public List<IMenuItemConfig> getChildren() {
	return children;
    }

    public void addChild(IMenuItemConfig menu) {
	getChildren().add(menu);
	//menu.setParent(this);
    }

    public void addChild(int index, IMenuItemConfig menu) {
	getChildren().add(index, menu);
	//menu.setParent(this);
    }
    
    public void addChildBefore(IMenuItemConfig menu, IMenuItemConfig selectedMenu) {
	int index = ConfigUtils.getPrevIndex(children, selectedMenu);
	if (index > -1) {
	    getChildren().add(index, menu);
	} else {
	    getChildren().add(menu);
	}
	//menu.setParent(this);
    }

    public void addChildAfter(IMenuItemConfig menu, IMenuItemConfig selectedMenu) {
	int index = ConfigUtils.getNextIndex(children, selectedMenu);
	if (index > -1) {
	    getChildren().add(index, menu);
	} else {
	    getChildren().add(menu);
	}
	//menu.setParent(this);
    }
    
    
    public boolean upChild(IMenuItemConfig menu) {
	return ConfigUtils.upElement(children, menu);
    }

    public boolean downChild(IMenuItemConfig menu) {
	return ConfigUtils.downElement(children, menu);
    }


    public void removeChild(IMenuItemConfig menu) {
	getChildren().remove(menu);
	//menu.setParent(null);
    }

    public boolean equals(Object o) {
	//TODO
	return super.equals(o);
    }
}
