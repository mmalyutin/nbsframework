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

package org.plazmaforge.framework.uwt.jfx.adapter;

import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.jfx.layout.XGridLayout;
import org.plazmaforge.framework.uwt.jfx.layout.XLayout;
import org.plazmaforge.framework.uwt.jfx.widget.XGridPanel;
import org.plazmaforge.framework.uwt.layout.GridLayout;


/**
 * 
 * @author ohapon
 *
 */
public class JFXGridLayoutAdapter extends JFXLayoutAdapter {

    
    public Object createDelegate(UIElement parent, UIElement element) {
	GridLayout layout = (GridLayout) element;  
	XGridLayout xLayout = new XGridLayout(layout.getColumnCount());
	
	// Spacing
	xLayout.setVerticalSpacing(layout.getVerticalSpacing());
	xLayout.setHorizontalSpacing(layout.getHorizontalSpacing());
	
	xLayout.setFix(layout.isFix());
	
	// Margin
	xLayout.setMarginLeft(layout.getMarginLeft());
	xLayout.setMarginTop(layout.getMarginTop());
	xLayout.setMarginRight(layout.getMarginRight());
	xLayout.setMarginBottom(layout.getMarginBottom());
	
	return xLayout;
    }

    protected XGridLayout getXGridLayout(Object delegate) {
	return (XGridLayout) delegate;
    }

    @Override
    public javafx.scene.layout.Pane createContainer(XLayout xLayout) {
	XGridLayout xGridLayout = (XGridLayout) xLayout;
	XGridPanel xGridPanel = new XGridPanel();
	
	// Padding
	xGridPanel.setPadding(createPadding(xGridLayout));
	
	// H/V Spacing
	xGridPanel.setHgap(xGridLayout.getHorizontalSpacing());
	xGridPanel.setVgap(xGridLayout.getVerticalSpacing());
	return xGridPanel;
    }
    

}
