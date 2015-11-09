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

package org.plazmaforge.framework.uwt.swt.adapter;

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.UWT;
import org.plazmaforge.framework.uwt.layout.BoxData;
import org.plazmaforge.framework.uwt.widget.Control;

public class SWTBoxDataAdapter extends SWTLayoutDataAdapter {
    
    public Object createDelegate(UIObject parent, UIObject element) {
   	BoxData layoutData = (BoxData) element;   
   	org.eclipse.swt.layout.RowData xLayoutData = new org.eclipse.swt.layout.RowData();
   	//TODO
   	//xLayoutData.exclude = ???
   	
   	populateLayoutSize(layoutData, xLayoutData);
   	
	return xLayoutData;
    }
    
    ////
    protected void populateLayoutSize(Object xLayoutData, int width, int height) {
	populateLayoutSize((org.eclipse.swt.layout.RowData) xLayoutData, width, height);
    }
    
    protected void populateLayoutSize(org.eclipse.swt.layout.RowData xLayoutData, int width, int height) {
	if (width != UWT.DEFAULT) {
	    xLayoutData.width = width; 
	}
	if (height != UWT.DEFAULT) {
	    xLayoutData.height = height; 
	}
    }

    public Object createDefaultLayoutData(Control control) {
	org.eclipse.swt.layout.RowData xLayoutData = new org.eclipse.swt.layout.RowData();
	populateLayoutSize(control, xLayoutData);
	return xLayoutData;
    }

    public boolean isCompatible(Object xLayoutData) {
	if (xLayoutData == null) {
	    return false;
	}
	return (xLayoutData instanceof org.eclipse.swt.layout.RowData);
    }
    ////


}
