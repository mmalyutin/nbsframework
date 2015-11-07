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

package org.plazmaforge.framework.uwt.widget.tree;

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.widget.DataItem;

public class TreeItem extends DataItem {

    private Image[] images;
    
    private String[] strings;
    
    private Object[] values;
    
    private TreeItem parent;
    
    /**
     * Children
     */
    private List<TreeItem> items;

    
    private int capacity = 10;
    
    
    
    public TreeItem() {
	super();
    }


    public TreeItem(Image image) {
	super(image);
    }


    public TreeItem(String text, Image image) {
	super(text, image);
    }


    public TreeItem(String text) {
	super(text);
    }

    public void setParent(TreeItem parent) {
	this.parent = parent;
    }
    
    
    public TreeItem getParent() {
        return parent;
    }


    public List<TreeItem> getItems() {
	if (items == null) {
	    items = new ArrayList<TreeItem>();
	}
        return items;
    }

    
    public void addItem(TreeItem item) {
	getItems().add(item);
	item.setParent(this);
    }

    public void removeItem(TreeItem item) {
	getItems().remove(item);
	item.setParent(null);
    }

    public int getItemCount() {
	return items == null ? 0 : items.size();
    }

    public boolean hasItems() {
	return items != null && !items.isEmpty();
    }

    public boolean hasParentItem() {
	return getParent() != null;
    }
    
    protected int getStringCount() {
	return strings == null ? 0 : strings.length;
    }
    
    protected int getImageCount() {
	return images == null ? 0 : images.length;
    }
    
    protected int getValueCount() {
	return values == null ? 0 : values.length;
    }


    public void setText(int i, String text) {
	if (i < 0) {
	    throw new IllegalArgumentException("Index must be >= 0");
	}
	if (i >= getStringCount()) {
	    strings = (String[]) arraycopy(strings, new String[getArraySize(i)], strings == null ? 0 : strings.length);
	}
	strings[i] = text;
    }

    public String getText(int i) {
	if (i < 0 || i >= getStringCount()) {
	    return null;
	}
	return strings[i];
    }

    public String getText() {
        return getText(0);
    }

    public void setText(String text) {
        setText(0, text);
    }    
    
    public void setImage(int i, Image image) {
	if (i < 0) {
	    throw new IllegalArgumentException("Index must be >= 0");
	}
	if (i >= getImageCount()) {
	    images = (Image[]) arraycopy(images, new Image[getArraySize(i)], images == null ? 0 : images.length);
	}
	images[i] = image;
    }

    public Image getImage(int i) {
	if (i < 0 || i >= getImageCount()) {
	    return null;
	}
	return images[i];
    }
    
    public Image getImage() {
        return getImage(0);
    }

    public void setImage(Image image) {
	setImage(0, image);
    }

    
    public void setValue(int i, Object value) {
	if (i < 0) {
	    throw new IllegalArgumentException("Index must be >= 0");
	}
	if (i >= getValueCount()) {
	    values = (Object[]) arraycopy(values, new Object[getArraySize(i)], values == null ? 0: values.length);
	}
	values[i] = value;
    }

    public Object getValue(int i) {
	if (i < 0 || i >= getValueCount()) {
	    return null;
	}
	return values[i];
    }

    public Object getValue() {
        return getValue(0);
    }

    public void setValue(Object value) {
        setValue(0, value);
    }        
    
    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }


    protected Object arraycopy(Object src, Object dest, int length) {
	if (src == null) {
	    return dest;
	}
	System.arraycopy(src, 0, dest, 0, length);
	return dest;
    }
    
    protected int getArraySize(int i) {
	return i + capacity;
    }
    
   
}
