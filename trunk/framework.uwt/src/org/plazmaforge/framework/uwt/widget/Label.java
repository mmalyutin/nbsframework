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

public class Label extends Control implements HasTextIcon {
    
    private String text;

    private Image icon;
    

    public Label() {
    }

    public Label(String text) {
	this.text = text;
    }

    public Label(String text, Image icon) {
	super();
	this.text = text;
	this.icon = icon;
    }

    @Override
    public void setText(String text) {
        this.text = text;
        fireChangeProperty(PROPERTY_TEXT, text);
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setIcon(Image icon) {
	this.icon = icon;
	fireChangeProperty(PROPERTY_ICON, icon);
	
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

    ////
    
    @Override
    public Image getIcon() {
	return icon;
    }

}
