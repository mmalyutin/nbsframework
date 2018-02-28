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
import org.plazmaforge.framework.uwt.jfx.layout.XBoxLayout;
import org.plazmaforge.framework.uwt.jfx.layout.XHorizontalLayout;
import org.plazmaforge.framework.uwt.jfx.layout.XLayout;
import org.plazmaforge.framework.uwt.jfx.layout.XVerticalLayout;
import org.plazmaforge.framework.uwt.widget.Style.Orientation;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import org.plazmaforge.framework.uwt.layout.BoxLayout;

/**
 * 
 * @author ohapon
 *
 */
public class JFXBoxLayoutAdapter extends JFXLayoutAdapter {

    
    public Object createDelegate(UIElement parent, UIElement element) {
	BoxLayout layout = (BoxLayout) element;  
	XBoxLayout xLayout = createLayout(layout);
	return xLayout;
    }
    
    protected XBoxLayout createLayout(BoxLayout layout) {
	//TODO
	//return new XBoxLayout(layout.getOrientation().equals(Orientation.HORIZONTAL) ? Orientation.HORIZONTAL : Orientation.VERTICAL);
	return layout.getOrientation().equals(Orientation.VERTICAL) ? new XVerticalLayout() : new XHorizontalLayout();
    }

    protected XBoxLayout getXBoxLayout(Object delegate) {
	return (XBoxLayout) delegate;
    }

    @Override
    public javafx.scene.layout.Pane createContainer(XLayout xLayout) {
	//TODO
	//Orientation xOrientation = xLayout == null ? null : ((XBoxLayout) xLayout).getOrientation();
	//return (xOrientation == Orientation.VERTICAL) ? new VBox() : new HBox();
	return (xLayout instanceof XVerticalLayout) ? new VBox() : new HBox();
    }
    
    
}
