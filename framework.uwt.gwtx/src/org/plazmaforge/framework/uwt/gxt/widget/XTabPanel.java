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

import org.plazmaforge.framework.uwt.gxt.util.GXTUtils;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Size;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;

/**
 * 
 * @author ohapon
 *
 */
public class XTabPanel extends TabPanel implements HasComputeSize {

    public static final int MAGIC_TAB_HEIGHT = 30;
    
    public XTabPanel() {
	super();
	
	// Set auto selection = false because we have bug with
	// activate first tab item with bad layout (container size = 0)
	// We call autoSelect() in forceLayout() method
	setAutoSelect(false);
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
    
    /**
     * Auto select tab item
     */
    protected void autoSelect() {
	if (getActiveWidget() == null && getWidgetCount() > 0) {
	    Widget widget = getWidget(0);
	    setActiveWidget(widget);
	}
    }
    
    @Override
    public void forceLayout() {
	autoSelect();
	super.forceLayout();
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
    
    protected Size getOffsetSize() {
	return GXTUtils.getOffsetSize(this);
    }
    
    public Size computeSize(int hWidth, int hHeight, boolean layout) {
	int count = getWidgetCount();
	if (count == 0) {
	    return getOffsetSize();
	}
	Widget widget = null;
	Size ws = null;
	int mWidth = 0;
	int mHeight = 0;
	for (int i = 0; i < count; i++) {
	    widget = getWidget(i);
	    ws = GXTUtils.computeSize(hWidth, hHeight, widget);
	    if  (ws != null) {
		if (ws.getWidth() > mWidth) {
		    mWidth = ws.getWidth();
		}
		if (ws.getHeight() > mHeight) {
		    mHeight = ws.getHeight();
		}
	    }
	}
	return new Size(mWidth, mHeight + MAGIC_TAB_HEIGHT);
	
    }
}
