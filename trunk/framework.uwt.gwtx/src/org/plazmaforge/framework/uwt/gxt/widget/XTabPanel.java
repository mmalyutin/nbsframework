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

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;

/**
 * 
 * @author ohapon
 *
 */
public class XTabPanel extends TabPanel {

    public XTabPanel() {
	super();
    }

    public XTabPanel(TabPanelAppearance appearance) {
	super(appearance);
    }

    @Override
    public void add(IsWidget widget, TabItemConfig config) {
	super.add(widget, config);
	assign(config);
    }

    @Override
    public void add(Widget widget, String text) {
	TabItemConfig config =  new TabItemConfig(text);
	insert(widget, getWidgetCount(), config);
	assign(config);
    }

    @Override
    public void add(Widget widget, TabItemConfig config) {
	super.add(widget, config);
	assign(config);
    }
    
    protected void assign(TabItemConfig config) {
	if (!(config instanceof XTabItem)) {
	    return;
	}
	((XTabItem) config).setParent(this);
    }
    
    /**
     * Returns widget by TabItem
     * @param config
     * @return
     */
    public Widget getWidget(TabItemConfig config) {
	if (config == null) {
	    return null;
	}
	int count = getWidgetCount();
	if (count == 0) {
	    return null;
	}
	Widget widget = null;
	TabItemConfig item = null;
	for (int i = 0; i < count; i++) {
	    widget = getWidget(i);
	    item = getConfig(widget);
	    if (config == item) {
		return widget;
	    }
	}
	return null;
    }
}
