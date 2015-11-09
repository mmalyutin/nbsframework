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
import org.plazmaforge.framework.uwt.layout.GridLayout;

public class SWTGridLayoutAdapter extends SWTLayoutAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	GridLayout layout = (GridLayout) element;
	org.eclipse.swt.layout.GridLayout XLayout = new org.eclipse.swt.layout.GridLayout();
	
	// Reset marginWidth and marginHeight because we use detail margin values (left, top, right, bottom)
	XLayout.marginWidth = 0;
	XLayout.marginHeight = 0;
	
	XLayout.numColumns = layout.getColumnCount();
	
	XLayout.marginLeft = layout.getMarginLeft();
	XLayout.marginTop = layout.getMarginTop();
	XLayout.marginRight = layout.getMarginRight();
	XLayout.marginBottom = layout.getMarginBottom();
	
	XLayout.verticalSpacing = layout.getVerticalSpacing();
	XLayout.horizontalSpacing = layout.getHorizontalSpacing();
	return XLayout;
    }

}
