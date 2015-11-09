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

package org.plazmaforge.framework.uwt.swing.adapter;


import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.Style.Orientation;
import org.plazmaforge.framework.uwt.layout.BoxLayout;

public class SwingBoxLayoutAdapter extends SwingLayoutAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {

	BoxLayout layout = (BoxLayout) element;
	java.awt.Container owner = (java.awt.Container) layout.getData(SwingLayoutAdapter.SYS_PROPERTY_OWNER);
	
	// Reset owner in layout 
	layout.setData(SwingLayoutAdapter.SYS_PROPERTY_OWNER, null);
	
	javax.swing.BoxLayout xLayout = createLayout(layout, owner);
	populateLayout(layout, xLayout);
	
	return xLayout;
    }
    
    protected javax.swing.BoxLayout createLayout(BoxLayout layout, java.awt.Container owner) {
	int axis = layout.getOrientation().equals(Orientation.HORIZONTAL) ? javax.swing.BoxLayout.X_AXIS : javax.swing.BoxLayout.Y_AXIS;
	javax.swing.BoxLayout xLayout = new javax.swing.BoxLayout(owner, axis);
	return xLayout;
    }
    
    protected void populateLayout(BoxLayout layout, javax.swing.BoxLayout xLayout) {
	//xLayout.marginLeft = layout.getMarginLeft();
	//xLayout.marginTop = layout.getMarginTop();
	//xLayout.marginRight = layout.getMarginRight();
	//xLayout.marginBottom = layout.getMarginBottom();
    }
}
