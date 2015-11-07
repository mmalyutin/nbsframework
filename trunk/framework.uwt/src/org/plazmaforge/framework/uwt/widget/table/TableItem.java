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

package org.plazmaforge.framework.uwt.widget.table;

import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.widget.DataItem;

public class TableItem extends DataItem {

    private Image[] images;
    
    private String[] strings;
    
    private Object[] values;
    
	    
    protected String[] doGetStrings() {
	if (strings == null) {
	    strings = new String[DEFAULT_COLUMN_COUNT];
	}
	return strings;
    }

    protected Image[] doGetImages() {
	if (images == null) {
	    images = new Image[DEFAULT_COLUMN_COUNT];
	}
	return images;
    }
    
    ////
    
    public void setText(int i, String text) {
	doGetStrings()[i] = text;
    }

    public String getText(int i) {
	return doGetStrings()[i];
    }

    ////
    
    public void setImage(int i, Image image) {
	doGetImages()[i] = image;
    }

    public Image getImage(int i) {
	return doGetImages()[i];
    }
    
    ////
    
    public Image getImage() {
        return getImage(0);
    }

    public void setImage(Image image) {
	setImage(0, image);
    }

    public String getText() {
        return getText(0);
    }

    public void setText(String text) {
        setText(0, text);
    }    
    
    ////
    
    public String[] getItemText() {
	return strings;
    }

    public Image[] getItemImage() {
	return images;
    }

    
}
