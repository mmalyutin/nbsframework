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

import org.plazmaforge.framework.uwt.gxt.layout.XGridData;
import org.plazmaforge.framework.uwt.gxt.layout.XLayout;
import org.plazmaforge.framework.uwt.gxt.layout.XLayoutData;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.resources.CommonStyles;
import com.sencha.gxt.core.client.util.Size;

/**
 * 
 * @author ohapon
 *
 */
public abstract class XAbstractCellLayoutContainer<L extends XLayout> extends XAbstractLayoutContainer<L> {

    public XAbstractCellLayoutContainer(L layout) {
	super(layout);
    }

    protected void preparePosition(Widget widget) {
 	// Set absolute position mode
 	widget.addStyleName(CommonStyles.get().positionable());
 	// widget.getElement().getStyle().setPosition(Position.ABSOLUTE);
 	widget.getElement().getStyle().setMargin(0, Unit.PX);
     }
     
     protected XLayoutData getLayoutData(Widget widget) {
 	Object ld = widget.getLayoutData();
 	if (!isValidLayoutData(ld)) {
 	    return null;
 	}
 	return (XLayoutData) ld;
     }
          
     protected boolean isValidLayoutData(Object ld) {
 	if (ld == null) {
 	    return false;
 	}
 	return acceptLayoutData(ld);
     }
     
     protected boolean isValidLayoutData(Widget widget) {
 	return isValidLayoutData(widget.getLayoutData());
     }
     
     protected abstract boolean acceptLayoutData(Object ld);
     
     protected abstract XLayoutData createLayoutData();
     
     protected XLayoutData prepareLayoutData(Widget widget) {
 	XLayoutData layoutData = getLayoutData(widget);
 	if (layoutData == null) {
 	    layoutData = createLayoutData();

 	    // Set or override Layout Data
 	    //widget.setLayoutData(layoutData);
 	}
 	return layoutData;
 	
 	
// 	Object ld = widget.getLayoutData();
// 	XGridData layoutData = null;
// 	if (ld != null && ld instanceof XGridData) {
// 	    layoutData = (XGridData) ld;
// 	}
// 	if (layoutData == null) {
// 	    layoutData = new XGridData();
 //
// 	    // Set or override Layout Data
// 	    widget.setLayoutData(layoutData);
// 	}
// 	return layoutData;
     }
     
     protected Size preparePreferredSize(Widget widget, XLayoutData layoutData) {

 	// Get original preferredWidth/preferredHeight
 	int preferredWidth = layoutData.getPreferredWidth();
 	int preferredHeight = layoutData.getPreferredHeight();

 	/*
 	 * boolean needCalculateWidth = hWidth == -1 || preferredWidth == -1 ||layoutData.isHorizontalFlex();
 	 * boolean needCalculateHeight = hHeight == -1 || preferredHeight == -1 || layoutData.isVerticalFlex();
 	 * 
 	 * if (needCalculateWidth || needCalculateHeight) {
 	 * 
 	 *    // First compute size Size 
 	 *    preferredSize = computePreferredSize(c);
 	 * 
 	 *    if (needCalculateWidth) { 
 	 *        preferredWidth = layoutData.getWidth() == -1 ? preferredWidth: layoutData.getWidth(); 
 	 *    }
 	 * 
 	 *    if (needCalculateHeight) { 
 	 *       preferredHeight = layoutData.getHeight() == -1 ? preferredHeight : layoutData.getHeight());
 	 *    }
 	 * 
 	 * }
 	 */

 	/////////////////////////////////////////////////////////////////////////////////////
 	//
 	// Initialize preferred size
 	//
 	/////////////////////////////////////////////////////////////////////////////////////
 	// 1. by layout data size
 	if (preferredWidth == -1) {
 	    preferredWidth = layoutData.getWidth();
 	}
 	if (preferredHeight == -1) {
 	    preferredHeight = layoutData.getHeight();
 	}

 	// 2. by compute size
 	if (preferredWidth == -1 || preferredHeight == -1) {

 	    // First compute size
 	    Size preferredSize = computePreferredSize(widget);

 	    if (preferredWidth == -1) {
 		preferredWidth = preferredSize.getWidth();
 	    }

 	    if (preferredHeight == -1) {
 		preferredHeight = preferredSize.getHeight();
 	    }
 	}
 	
 	return new Size(preferredWidth, preferredHeight);
     }
     

}
