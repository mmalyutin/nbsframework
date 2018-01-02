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

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.core.data.Callback;
import org.plazmaforge.framework.core.data.Notifier;

import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * @author ohapon
 *
 */
public class XDesktop extends SimpleContainer {

    private DesktopTabPanel tabPanel;
    
    private List<XDesktopItem> items = new ArrayList<XDesktopItem>();
    
    
    public XDesktop() {
	super();
	tabPanel = new DesktopTabPanel();
	add(tabPanel);
	tabPanel.setVisible(false);
    }

    class DesktopTabPanel extends XTabPanel {
	
	@Override
	protected void close(Widget widget) {
	    XTabItem tabItem = (XTabItem) getConfig(widget);
	    doClose(tabItem);
	}
	
	public void forceClose(XTabItem item) {
	    Widget widget = item.getWidget();
	    super.close(widget);
	}
	
    }

    /**
     * Update state of container
     * Visible if count of items > 0
     */
    protected void updateState() {
	boolean visible = getTabPanel().getWidgetCount() > 0;
	getTabPanel().setVisible(visible);
    }

    public XDesktopItem createItem(String title) {
	
	
	XDesktopItem item = new XDesktopItem(this);
	
	XTabItem tabItem = new XTabItem();
	tabItem.setClosable(true);
	
	XLayoutContainer content = new XLayoutContainer(new SimpleContainer());
	
	// Set absolute position of content of TabItem
	content.setAbsolutePosition();

	item.setShell(tabItem);
	item.setContent(content);
	if (title == null) {
	    title = "";
	}
	
	tabItem.setText(title);
	getTabPanel().add(content, tabItem);
	items.add(item);
	updateState();
	return item;
    }
    
    protected void updateTitle(XDesktopItem item) {
	XTabItem tabItem = (XTabItem) item.getShell();
	String title = item.getTitle();
	if (title == null) {
	    title = "";
	}
	tabItem.setText(title);
    }


    public DesktopTabPanel getTabPanel() {
        return tabPanel;
    }

    
    protected XDesktopItem getItem(XTabItem tabItem) {
	if (tabItem == null) {
	    return null;
	}
	for (XDesktopItem item : items) {
	    if (tabItem == item.getShell()) {
		return item;
	    }
	}
	return null;
    }

    
    public void setSelectionItem(XDesktopItem item) {
	if (item == null) {
	    throw new IllegalArgumentException("Item must be not null");
	}
	Widget widget = (Widget) item.getContent();
	getTabPanel().setActiveWidget(widget);
    }
    
    protected void doClose(final XTabItem tabItem) {
	
	final XDesktopItem item = getItem(tabItem);
	final Notifier client = item.getNotifier();
	if (client == null) {
	    getTabPanel().forceClose(tabItem);
	    items.remove(item);
	    
	    updateState();
	    //DesktopEvent event = createEvent(item);
	    //fireCloseItem(event);
	    return;
	}
	client.canNotify("close", new Callback<Boolean>() {
	    
	    public void onFailure(Throwable error) {
		//do nothing
	    }

	    public void onSuccess(Boolean result) {
		boolean close = result == null ? false : result;
		if (close) {
		    getTabPanel().forceClose(tabItem);
		    items.remove(item);
		    client.notify("close");
		    
		    updateState();
		    //DesktopEvent event = createEvent(item);
		    //fireCloseItem(event);
		}
	    }
	});
    }

}
