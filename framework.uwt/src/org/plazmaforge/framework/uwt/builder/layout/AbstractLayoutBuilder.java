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

package org.plazmaforge.framework.uwt.builder.layout;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.builder.AbstractBuilder;
import org.plazmaforge.framework.uwt.widget.Layout;

public abstract class AbstractLayoutBuilder extends AbstractBuilder  {

    protected abstract Layout createLayout(IData data);

    public UIObject buildObject(IData data) {
	if (data == null) {
	    return null;
	}
	Layout layout = createLayout(data);
	if (layout == null) {
	    return null;
	}
	populate(data, layout);
	return layout;
    }    

    protected void populateMargin(IData data, UIObject element) {
	if (data == null || element == null) {
	    return;
	}
	Layout layout = (Layout) element;
	
	Integer marginLeft = getInteger(data, Layout.PROPERTY_MARGIN_LEFT);
	if (marginLeft != null) {
	    layout.setMarginLeft(marginLeft);
	}
	Integer marginTop = getInteger(data, Layout.PROPERTY_MARGIN_TOP);
	if (marginTop != null) {
	    layout.setMarginTop(marginTop);
	}
	Integer marginRight = getInteger(data, Layout.PROPERTY_MARGIN_RIGHT);
	if (marginRight != null) {
	    layout.setMarginRight(marginRight);
	}
	Integer marginBottom = getInteger(data, Layout.PROPERTY_MARGIN_BOTTOM);
	if (marginBottom != null) {
	    layout.setMarginBottom(marginBottom);
	}
	
    }

    protected void populate(IData data, UIObject element) {
	// do nothing by default
    }    
    
}
