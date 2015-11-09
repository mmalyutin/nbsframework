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


import javax.swing.SwingConstants;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.Separator;
import org.plazmaforge.framework.uwt.widget.Style.Orientation;

public class SwingSeparatorAdapter extends SwingControlAdapter {

    @Override
    public Object createDelegate(UIObject parent, UIObject element) {
	Separator separator = (Separator) element;
	java.awt.Container xParent = getContent(parent.getDelegate());
	int xOrientation = 0;
	java.awt.Dimension xSize = null;
	if (Orientation.VERTICAL == separator.getOrientation()) {
	    xOrientation = SwingConstants.VERTICAL;
	    xSize = new java.awt.Dimension(3, 50);
	} else {
	    xOrientation = SwingConstants.HORIZONTAL;
	    xSize = new java.awt.Dimension(50, 3);
	}
	javax.swing.JSeparator xLabel = new javax.swing.JSeparator(xOrientation);
	xLabel.setPreferredSize(xSize);
	
	addToParent(xParent, xLabel, element);	
	return xLabel;
    }

}
