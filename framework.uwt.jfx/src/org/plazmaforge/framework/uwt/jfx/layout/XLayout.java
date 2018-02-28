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

package org.plazmaforge.framework.uwt.jfx.layout;

import org.plazmaforge.framework.uwt.UWTException;

import javafx.scene.Node;
import javafx.scene.Parent;

/**
 * 
 * @author ohapon
 *
 */
public abstract class XLayout  {

    private Parent owner;
    
    private int marginLeft;
    
    private int marginTop;
    
    private int marginRight;
    
    private int marginBottom;
    
    public int getMarginLeft() {
        return marginLeft;
    }

    public Parent getOwner() {
        return owner;
    }

    public void assign(Parent owner) {
	if (this.owner != null) {
	    throw new UWTException("Can't assign new Owner in Layout. Old Owner is not empty");
	}
        this.owner = owner;
    }
    
    public void reset() {
	this.owner = null;
    }
    
    public void setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public void setMarginTop(int marginTop) {
        this.marginTop = marginTop;
    }

    public int getMarginRight() {
        return marginRight;
    }

    public void setMarginRight(int marginRight) {
        this.marginRight = marginRight;
    }

    public int getMarginBottom() {
        return marginBottom;
    }

    public void setMarginBottom(int marginBottom) {
        this.marginBottom = marginBottom;
    }
    
    protected XLayoutData getXLayoutData(Node widget) {
	if (widget == null) {
	    return null;
	}
	//TODO
	return null;
//	Object layoutData = widget.getLayoutData();
//	if (layoutData == null) {
//	    return null;
//	}
//	if (!(layoutData instanceof XLayoutData)) {
//	    return null;
//	}
//	return (XLayoutData) layoutData;
    }


}
