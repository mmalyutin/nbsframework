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
public class ToolBarConfig extends UIObjectConfig implements IToolBarConfig {

    private static final long serialVersionUID = -8924789858969338255L;
    
    private List<IToolItemConfig> buttons = new ArrayList<IToolItemConfig>();

    public List<IToolItemConfig> getChildren() {
	return buttons;
    }

    public void addChild(IToolItemConfig button) {
	getChildren().add(button);
	//button.setParent(this);
	fireModifyObject(button);
    }

    public void addChild(int index, IToolItemConfig button) {
	getChildren().add(index, button);
	//button.setParent(this);
	fireModifyObject(button);
    }
    
    public void addButtonBefore(IToolItemConfig button, IToolItemConfig selectedButton) {
	int index = ConfigUtils.getPrevIndex(buttons, selectedButton);
	if (index > -1) {
	    getChildren().add(index, button);
	} else {
	    getChildren().add(button);
	}
	//button.setParent(this);
	fireModifyObject(button);
    }

    public void addButtonAfter(IToolItemConfig button, IToolItemConfig selectedButton) {
	int index = ConfigUtils.getNextIndex(buttons, selectedButton);
	if (index > -1) {
	    getChildren().add(index, button);
	} else {
	    getChildren().add(button);
	}
	//button.setParent(this);
	fireModifyObject(button);
    }
    
    
    public boolean upButton(IToolItemConfig button) {
	boolean flag = ConfigUtils.upElement(buttons, button);
	if (flag) {
	    fireModifyObject(button);
	}
	return flag;
    }

    public boolean downButton(IToolItemConfig button) {
	boolean flag = ConfigUtils.downElement(buttons, button);
	if (flag) {
	    fireModifyObject(button);
	}
	return flag;
    }
    
    public void removeButton(IToolItemConfig button) {
	getChildren().remove(button);
	//button.setParent(null);
	fireModifyObject(button);
    }
    
    protected void fireModifyObject(IToolItemConfig toolItem) {
	//TODO
    }

}
