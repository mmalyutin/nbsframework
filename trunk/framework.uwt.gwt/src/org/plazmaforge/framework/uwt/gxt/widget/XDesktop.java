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

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

/**
 * 
 * @author ohapon
 *
 */
public class XDesktop extends LayoutContainer {

    private DesktopTabPanel tabPanel;
    
    private List<XDesktopItem> items = new ArrayList<XDesktopItem>();
    
    
    public XDesktop() {
	super();
	setLayout(new FitLayout());
	tabPanel = new DesktopTabPanel();
	add(tabPanel);
	tabPanel.setVisible(false);
    }

    class DesktopTabPanel extends TabPanel {
	
	@Override
	protected void close(TabItem item) {
	    doClose(item);
	}
	
	public void forceClose(TabItem item) {
	    super.close(item);
	}
	
    }

    /**
     * Update state of container
     * Visible if count of items > 0
     */
    protected void updateState() {
	boolean visible = getTabPanel().getItemCount() > 0;
	getTabPanel().setVisible(visible);
    }

    public XDesktopItem createItem(String title) {
	TabItem tabItem = new TabItem();
	tabItem.setClosable(true);
	tabItem.setLayout(new FitLayout());
	XDesktopItem item = new XDesktopItem(this);
	item.setContent(tabItem);
	if (title == null) {
	    title = "";
	}
	tabItem.setText(title);
	getTabPanel().add(tabItem);
	items.add(item);
	updateState();
	return item;
    }
    
    protected void updateTitle(XDesktopItem item) {
	TabItem tabItem = (TabItem) item.getContent();
	String title = item.getTitle();
	if (title == null) {
	    title = "";
	}
	tabItem.setText(title);
    }


    public DesktopTabPanel getTabPanel() {
        return tabPanel;
    }

    
    protected XDesktopItem getItem(TabItem tabItem) {
	if (tabItem == null) {
	    return null;
	}
	for (XDesktopItem item : items) {
	    if (tabItem == item.getContent()) {
		return item;
	    }
	}
	return null;
    }

    
    public void setSelectionItem(XDesktopItem item) {
	if (item == null) {
	    throw new IllegalArgumentException("Item must be not null");
	}
	TabItem tabItem = (TabItem) item.getContent();
	getTabPanel().setSelection(tabItem);
    }
    
    protected void doClose(final TabItem tabItem) {
	
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
