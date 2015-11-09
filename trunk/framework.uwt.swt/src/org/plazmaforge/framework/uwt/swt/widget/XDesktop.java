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

package org.plazmaforge.framework.uwt.swt.widget;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabFolder2Adapter;
import org.eclipse.swt.custom.CTabFolderEvent;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;
import org.plazmaforge.framework.core.data.Callback;
import org.plazmaforge.framework.core.data.Notifier;
import org.plazmaforge.framework.uwt.swt.layout.XFitLayout;
import org.plazmaforge.framework.uwt.widget.Window;


public class XDesktop extends Composite {

    // TDI Implementation
    private List<DesktopListener> listeners = new ArrayList<DesktopListener>();
    private CTabFolder tabFolder;
    
    public XDesktop(Composite parent, int style) {
	super(parent, style);
	setLayout(new XFitLayout());
	
	//[TDI]
	tabFolder = new CTabFolder(this, SWT.CLOSE | SWT.FLAT);
	
	tabFolder.addCTabFolder2Listener(new CTabFolder2Adapter() {
		public void close(CTabFolderEvent e) {
		    CTabItem tabItem = (CTabItem) e.item;
		    e.doit = false; // Always false because we use programming invoke method 'close'
		    doClose(tabItem);
		}
	});
	
	
	tabFolder.addSelectionListener(new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
		    CTabItem tabItem = (CTabItem) e.item;
		    doSelect(tabItem);
		}
	});
	
	tabFolder.setVisible(false);
    }

    /**
     * Return container of items
     * @return
     */
    public Composite getContainer() {
	// [TDI] - TabFolder
	return tabFolder;
    }

    /**
     * Update state of container
     * Visible if count of items > 0
     */
    protected void updateState() {
	boolean visible = tabFolder.getItemCount() > 0;
	tabFolder.setVisible(visible);
    }

    
    public XDesktopItem createItem(String title) {
	Widget owner = createItemOwner(title);
   	Composite content = createItemContent(owner);
   	XDesktopItem item = new XDesktopItem(this);
   	item.setOwner(owner);
   	item.setContent(content); // Assign to DesktopItem
   	assignContent(owner, content);
   	owner.setData("item", item);
   	updateState();
	return item;
    }
    
    protected void updateTitle(XDesktopItem item) {
	if (item.getOwner() == null) {
	    return;
	}
	String title = item.getTitle();
	if (title == null) {
	    title = "";
	}
	((CTabItem) item.getOwner()).setText(title);
    }
    
    protected Composite createItemContent(Widget owner) {
	//[TDI]: Use getContainer() like parent
   	Composite content = new Composite(getContainer(), SWT.NONE);
   	content.setLayout(new XFitLayout());
   	return content;
    }
    
    protected Widget createItemOwner(String title) {
	//[TDI]
	CTabItem tabItem =  createTabItem(title);
	return tabItem;
    }
    
    protected void assignContent(Widget owner, Composite content) {
	//[TDI]
	((CTabItem) owner).setControl(content); // Assign to TabItem
    }

    /**
     * Create TabItem with title
     * @param title
     * @return
     */
    //[TDI]
    private CTabItem createTabItem(String title) {
	// Create Tab Item
	CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
	tabItem.setText(title == null ? "" : title);
	return tabItem;
    }


    
    public void setSelectionItem(XDesktopItem item) {
	if (item == null) {
	    throw new IllegalArgumentException("Item must be not null");
	}
	//[TDI]
	CTabItem tabItem = getTabItem(item);
	if (tabItem == null) {
	    return;
	}
	tabFolder.setSelection(tabItem);
    }

    //[TDI]
    protected CTabItem getTabItem(XDesktopItem item) {
	CTabItem[] tabItems = tabFolder.getItems();
	for (CTabItem tabItem: tabItems) {
	    if (item == tabItem.getData("item")) {
		return tabItem;
	    }
	}
	return null;
    }


    protected DesktopEvent createEvent(XDesktopItem item) {
	String name = item.getName();
	String title = item.getTitle();
	Control control = item.getContent();
	return new DesktopEvent(name, title, control);
    }

    protected void fireOpenItem(DesktopEvent event) {
	for (DesktopListener l : listeners) {
	    l.openItem(event);
	}
    }

    protected void fireCloseItem(DesktopEvent event) {
	for (DesktopListener l : listeners) {
	    l.closeItem(event);
	}
    }

    protected void fireActivateItem(DesktopEvent event) {
	for (DesktopListener l : listeners) {
	    l.activateItem(event);
	}
    }

    protected void doClose(final CTabItem tabItem) {
	final XDesktopItem item = (XDesktopItem) tabItem.getData("item");
	final Notifier client = item.getNotifier();
	if (client == null) {
	    closeTabItem(tabItem);
	    
	    updateState();
	    DesktopEvent event = createEvent(item);
	    fireCloseItem(event);
	    return;

	}
	client.canNotify(Window.METHOD_CLOSE, new Callback<Boolean>() {
	    
	    public void onFailure(Throwable error) {
		//do nothing
	    }

	    public void onSuccess(Boolean result) {
		boolean close = result == null ? false : result;
		if (close) {
		    closeTabItem(tabItem);
		    client.notify(Window.METHOD_CLOSE);
		    
		    updateState();
		    DesktopEvent event = createEvent(item);
		    fireCloseItem(event);
		}
	    }
	});
    }
    
    protected void doSelect(final CTabItem tabItem) {
	final XDesktopItem item = (XDesktopItem) tabItem.getData("item");
	DesktopEvent event = createEvent(item);
	fireActivateItem(event);
    }
    
    protected void closeTabItem(CTabItem tabItem) {
	tabItem.dispose();
    }

}
