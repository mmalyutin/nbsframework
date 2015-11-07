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

package org.plazmaforge.framework.uwt.widget;

import org.plazmaforge.framework.uwt.action.ActionItem;
import org.plazmaforge.framework.uwt.event.SelectionListener;
import org.plazmaforge.framework.uwt.graphics.Image;

public class Button extends Control implements  ActionItem {

    
    private String text;
    
    private Image icon;
    
    private String command;
    

    public Button() {
	
    }

    public Button(String text) {
	this.text = text;
    }

    public Button(String text, Image icon) {
	super();
	this.text = text;
	this.icon = icon;
    }

    @Override
    public void setText(String text) {
        this.text = text;
        fireChangeProperty(PROPERTY_TEXT, text);
    }
    
    public String getText() {
        return text;
    }
    

    @Override
    public void setIcon(Image image) {
	this.icon = image;
	fireChangeProperty(PROPERTY_ICON, image);
	
    }
    
    public void setIcon(String path) {
	doGetIcon().setPath(path);
	fireChangeProperty(PROPERTY_ICON_PATH, path);
    }
    
    @Override
    public Image getIcon() {
	return icon;
    }
    
    protected Image doGetIcon() {
	if (icon == null) {
	    icon = new Image();
	}
	return icon;
    }
    
    
    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
    
    ////

    public void addSelectionListener(SelectionListener listener) {
	addSelectionListener(this, listener);
    }
    
    public void removeSelectionListener(SelectionListener listener) {
	removeSelectionListener(this, listener);
    }
    

}
