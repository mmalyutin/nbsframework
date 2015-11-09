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

package org.plazmaforge.framework.uwt.swt.layout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Scrollable;

public class XFitLayout extends Layout {

    public int marginWidth = 0;
    
    public int marginHeight = 0;

    
    protected Point computeSize(Composite composite, int wHint, int hHint, boolean flushCache) {
	Control[] children = composite.getChildren();
	int count = children.length;
	
	int maxWidth = 0;
	int maxHeight = 0;

	if (count > 0) {
	    Control child = children[0]; // Get first control
	    
	    int w = wHint;
	    int h = hHint;
	    
	    Point size = computeChildSize(child, w, h, flushCache);

	    maxWidth = Math.max(maxWidth, size.x);
	    maxHeight = Math.max(maxHeight, size.y);

	}	

	int width = maxWidth;
	int height = maxHeight;
	
	width += marginWidth * 2;
	height += marginHeight * 2;
	
	if (wHint != SWT.DEFAULT) {
	    width = wHint;
	}
	if (hHint != SWT.DEFAULT) {
	    height = hHint;
	}
	
	return new Point(width, height);
    }

    Point computeChildSize(Control control, int wHint, int hHint, boolean flushCache) {
	
	XFitData data = getFitData(control);
	if (data == null) {
	    data = new XFitData();
	    control.setLayoutData(data);
	}
	
	Point size = null;
	if (wHint == SWT.DEFAULT && hHint == SWT.DEFAULT) {
	    size = data.computeSize(control, wHint, hHint, flushCache);
	} else {
	    // TEMPORARY CODE
	    int trimX, trimY;
	    if (control instanceof Scrollable) {
		Rectangle rect = ((Scrollable) control).computeTrim(0, 0, 0, 0);
		trimX = rect.width;
		trimY = rect.height;
	    } else {
		trimX = trimY = control.getBorderWidth() * 2;
	    }
	    int w = wHint == SWT.DEFAULT ? wHint : Math.max(0, wHint - trimX);
	    int h = hHint == SWT.DEFAULT ? hHint : Math.max(0, hHint - trimY);
	    size = data.computeSize(control, w, h, flushCache);
	}
	return size;
    }
	
    protected boolean flushCache(Control control) {
	XFitData layoutData = getFitData(control);
	if (layoutData != null) {
	    ((XFitData) layoutData).flushCache();
	}
	return true;
    }

    String getName() {
	String string = getClass().getName();
	int index = string.lastIndexOf('.');
	if (index == -1) {
	    return string;
	}
	return string.substring(index + 1, string.length());
    }
	
    protected void layout(Composite composite, boolean flushCache) {
	
	Control[] children = composite.getChildren();
	int count = children.length;
	if (count == 0) {
	    return;
	}
	
	Rectangle rect = composite.getClientArea();
	    
	int width = rect.width - marginWidth * 2;
	int height = rect.height - marginHeight * 2;
	
	int x = rect.x + marginWidth;
	int y = rect.y + marginHeight;
	
	Control child = children[0];
	child.setBounds(x, y, width, height);

    }	
	
    public String toString() {
	String string = getName() + " {";
	if (marginWidth != 0)
	    string += "marginWidth=" + marginWidth + " ";
	if (marginHeight != 0)
	    string += "marginHeight=" + marginHeight + " ";
	string = string.trim();
	string += "}";
	return string;
    }

    protected XFitData getFitData(Control control) {
	Object layoutData = control.getLayoutData();
	return layoutData instanceof XFitData ? (XFitData) layoutData : null;
    }

}
