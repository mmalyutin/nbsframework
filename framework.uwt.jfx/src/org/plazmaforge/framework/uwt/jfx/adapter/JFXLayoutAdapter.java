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
import org.plazmaforge.framework.uwt.jfx.layout.XLayout;
import org.plazmaforge.framework.uwt.jfx.widget.XLayoutContainer;
import org.plazmaforge.framework.uwt.widget.Layout;

/**
 * 
 * @author ohapon
 *
 */
public abstract class JFXLayoutAdapter extends JFXAbstractAdapter {

    @Override
    public void disposeDelegate(UIElement parent, UIElement element) {
	
    }
    
    public abstract javafx.scene.layout.Pane createContainer(XLayout xLayout);
    
    public void addChild(XLayoutContainer parent, javafx.scene.Node widget, UIElement element) {
	parent.getChildren().add(widget);
    }
    
    
    protected javafx.geometry.Insets createInsets(double top, double right, double bottom, double left) {
	return new javafx.geometry.Insets(top, right, bottom, left);
    }

    protected javafx.geometry.Insets createPadding(XLayout xLayout) {
	return new javafx.geometry.Insets(xLayout.getMarginTop(), xLayout.getMarginRight(), xLayout.getMarginBottom(), xLayout.getMarginLeft());
    }
    
    protected void updateMargin(Layout layout, XLayout xLayout) {
	// Margin
	xLayout.setMarginLeft(layout.getMarginLeft());
	xLayout.setMarginTop(layout.getMarginTop());
	xLayout.setMarginRight(layout.getMarginRight());
	xLayout.setMarginBottom(layout.getMarginBottom());
    }
    
}
