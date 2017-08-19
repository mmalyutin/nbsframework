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
package org.plazmaforge.framework.uwt.gxt.widget;

import java.util.Iterator;

import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;


/**
 * 
 * @author ohapon
 *
 */
public class XCoolBar extends HorizontalPanel {

    public XCoolBar() {
	super();
	setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
	setWidth("100%");
    }
    
    protected void resetCellWidths() {
	if (getWidgetCount() == 0) {
	    return;
	}
	
	// Remove width of all cells
	Iterator<Widget> children = iterator();
	Widget widget = null;
	while (children.hasNext()) {
	    widget = children.next();
	    setCellWidth(widget, null);
	}

    }

    @Override
    public void add(Widget w) {
	resetCellWidths();
	super.add(w);
	setCellWidth(w, "100%");
    }
}
