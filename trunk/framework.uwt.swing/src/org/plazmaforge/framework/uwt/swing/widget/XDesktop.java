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

package org.plazmaforge.framework.uwt.swing.widget;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.plazmaforge.framework.core.data.Callback;
import org.plazmaforge.framework.core.data.Notifier;
import org.plazmaforge.framework.uwt.widget.Window;


public class XDesktop extends JPanel {

    private DesktopTabbedPane tabbedPane;
    
    private List<XDesktopItem> items = new ArrayList<XDesktopItem>(); 
    
    // TDI Implementation
    public XDesktop() {
	super();
	setLayout(new BorderLayout());
	tabbedPane = new DesktopTabbedPane();
	tabbedPane.setClosable(true);
	add(tabbedPane);
	tabbedPane.setVisible(false);
    }

    class DesktopTabbedPane extends XTabbedPane {
	
	  public void removeTabAt(int index) {
	      doClose(index);
	  }
	  
	  public void forceRemoveTabAt(int index) {
	      super.removeTabAt(index);
	  }
    }
    
    public DesktopTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    /**
     * Update state of container
     * Visible if count of items > 0
     */
    protected void updateState() {
	boolean visible = getTabbedPane().getTabCount() > 0;
	getTabbedPane().setVisible(visible);
    }
    
    public XDesktopItem createItem(String title) {
	javax.swing.JPanel xContent = new javax.swing.JPanel();
   	xContent.setLayout(new BorderLayout());
   	
   	XDesktopItem item = new XDesktopItem(this);
   	item.setContent(xContent);
	if (title == null) {
	    title = "";
	}
   	getTabbedPane().addTab(title, xContent);
   	items.add(item);
   	updateState();
   	return item;
    }
    
    protected void updateTitle(XDesktopItem item) {
	Container xContent = item.getContent();
	int index = getTabbedPane().indexOfComponent(xContent);
	String title = item.getTitle();
	if (title == null) {
	    title = "";
	}
	getTabbedPane().setTitleAt(index, title);
    }
    
    public void setSelectionItem(XDesktopItem item) {
	if (item == null) {
	    throw new IllegalArgumentException("Item must be not null");
	}
	int index = indexOfItem(item);
	if (index < 0) {
	    return;
	}
	getTabbedPane().setSelectedIndex(index);
    }
    
    protected XDesktopItem getItem(int index) {
	Component component = getTabbedPane().getComponentAt(index);
	if (component == null) {
	    return null;
	}
	for (XDesktopItem item : items) {
	    if (component == item.getContent()) {
		return item;
	    }
	}
	return null;
    }
    
    protected int indexOfItem(XDesktopItem item) {
	if (item == null || items == null) {
	    return -1;
	}
	return items.indexOf(item);
    }

    
    protected void doClose(final int index) {
	
	final XDesktopItem item = getItem(index);
	final Notifier client = item.getNotifier();
	if (client == null) {
	    getTabbedPane().forceRemoveTabAt(index);
	    items.remove(item);
	    
	    updateState();
	    //DesktopEvent event = createEvent(item);
	    //fireCloseItem(event);
	    return;
	}
	client.canNotify(Window.METHOD_CLOSE, new Callback<Boolean>() {
	    
	    public void onFailure(Throwable error) {
		//do nothing
	    }

	    public void onSuccess(Boolean result) {
		boolean close = result == null ? false : result;
		if (close) {
		    getTabbedPane().forceRemoveTabAt(index);
		    items.remove(item);
		    client.notify(Window.METHOD_CLOSE);
		    
		    updateState();
		    //DesktopEvent event = createEvent(item);
		    //fireCloseItem(event);
		}
	    }
	});
    }
    
    
}
