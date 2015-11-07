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


import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.util.Size;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.google.gwt.user.client.ui.Widget;

import org.plazmaforge.framework.uwt.gxt.layout.XGridData;

public class XContentPanel extends ContentPanel {

    public Size getTrimSize() {
	Size size = getFrameSize();
	size.width = 0;
	return size;
    }
    
    protected void afterExpand() {
	super.afterExpand();
	Widget parent = getParent();
	if (parent instanceof LayoutContainer) {
	    Object ld = getLayoutData();
	    if (ld instanceof XGridData) {
		((XGridData) ld).setCurrentHeight(-1);
	    }
	    ((LayoutContainer) parent).layout(true);
	}
	//((LayoutContainer) ((LayoutContainer) getParent()).getParent()).layout();
	//fireEvent(Events.Resize);
    }
    
    protected void afterCollapse() {
	super.afterCollapse();
	
	Widget parent = getParent();
	if (parent instanceof LayoutContainer) {
	    Object ld = getLayoutData();
	    if (ld instanceof XGridData) {
		((XGridData) ld).setCurrentHeight(-1);
	    }
	    ((LayoutContainer) parent).layout(true);
	}
	
	//((LayoutContainer) ((LayoutContainer) getParent()).getParent()).layout();
	//fireEvent(Events.Resize);
    }
}
