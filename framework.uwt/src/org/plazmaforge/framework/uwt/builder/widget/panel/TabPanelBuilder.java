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

import java.util.List;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.builder.UIBuilder;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.panel.TabItem;
import org.plazmaforge.framework.uwt.widget.panel.TabPanel;

/**
 * @author ohapon
 *
 */
public class TabPanelBuilder extends PanelBuilder {

    @Override
    public boolean accept(String type) {
	return eq(type, UIBuilder.TAB_PANEL_TYPE);
    }

    @Override
    public UIObject buildObject(IData data) {
	if (data == null) {
	    return null;
	}
	TabPanel panel = new TabPanel();
	populate(data, panel);
	return panel;
    }

    @Override
    protected void populateLayout(IData data, Composite composite) {
	// Layout is not supported 
    }

    @Override
    protected void populateBody(IData data, UIObject element) {
	TabPanel tabPanel = (TabPanel) element;
	populateTabContent(data, tabPanel);	
    }
    
    protected void populateTabContent(IData data, TabPanel tabPanel) {
	List<IData> children = getChildrenOfNode(data, TabPanel.PROPERTY_TAB_ITEMS);
	populateTabContentChildren(children, tabPanel);
    }
    
    protected void populateTabContentChildren(List<IData> children, TabPanel tabPanel) {
	 if (children == null || children.isEmpty()) {
	     return;
	 }	
	 for (IData child: children) {
	     TabItem childWidget = (TabItem) buildDataObject(child);
	     tabPanel.addItem(childWidget);
	 }
   }
}
