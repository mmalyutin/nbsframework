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

/**
 * 
 */
package org.plazmaforge.framework.uwt.builder.widget.panel;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.builder.UIBuilder;
import org.plazmaforge.framework.uwt.widget.Container;
import org.plazmaforge.framework.uwt.widget.Style.Orientation;
import org.plazmaforge.framework.uwt.widget.panel.SplitPanel;

/**
 * @author ohapon
 *
 */
public class SplitPanelBuilder extends PanelBuilder {

    @Override
    public boolean accept(String type) {
	return eq(type, UIBuilder.SPLIT_PANEL_TYPE);
    }

    @Override
    public UIElement buildObject(IData data) {
	if (data == null) {
	    return null;
	}
	Orientation orientation = getOrientation(data, SplitPanel.PROPERTY_ORIENTATION);
	SplitPanel panel = orientation == null ? new SplitPanel() : new SplitPanel(orientation); 
	populate(data, panel);
	return panel;
    }
    
    @Override
    protected void populateLayout(IData data, Container container) {
	// Layout is not supported  
    }
}
