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

import org.plazmaforge.framework.uwt.graphics.Image;

public abstract class Item extends Widget implements HasItem {

    private String text;

    private Image icon;
    
    
    public Item() {
    }
    
    public Item(String text) {
	super();
	this.text = text;
    }

    public Item(Image icon) {
	super();
	this.icon = icon;
    }

    public Item(String text, Image icon) {
	super();
	this.text = text;
	this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        fireChangeProperty(Control.PROPERTY_TEXT, text);
    }
    
    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
        fireChangeProperty(Control.PROPERTY_ICON, icon);
    }

    public void setIcon(String path) {
   	doGetIcon().setPath(path);
   	fireChangeProperty(PROPERTY_ICON_PATH, path);
    }
    
    protected Image doGetIcon() {
	if (icon == null) {
	    icon = new Image();
	}
	return icon;
    }
   
}
