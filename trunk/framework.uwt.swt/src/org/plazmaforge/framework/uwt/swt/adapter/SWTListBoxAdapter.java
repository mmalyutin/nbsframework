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

package org.plazmaforge.framework.uwt.swt.adapter;

import java.util.List;

import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.swt.SWT;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.swt.adapter.viewer.SWTListContentProvider;
import org.plazmaforge.framework.uwt.swt.adapter.viewer.SWTListLabelProvider;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.ListBox;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.table.Table;

public class SWTListBoxAdapter extends SWTControlAdapter {

    public static final String SYS_PROPETY_LIST_VIEWER = "$listViewer";
    
    public static final String SYS_PROPETY_LIST_BOX = "$listBox";
    
    
    public Object createDelegate(UIObject parent, UIObject element) {
	org.plazmaforge.framework.uwt.widget.ListBox listBox = (org.plazmaforge.framework.uwt.widget.ListBox) element;
	org.eclipse.swt.widgets.Composite xParent = (org.eclipse.swt.widgets.Composite) getContent(parent.getDelegate());
	org.eclipse.swt.widgets.List xListBox = new org.eclipse.swt.widgets.List(xParent, SWT.BORDER | SWT.FULL_SELECTION);
	org.eclipse.jface.viewers.ListViewer xListViewer = new org.eclipse.jface.viewers.ListViewer(xListBox);
	xListBox.setData(SYS_PROPETY_LIST_VIEWER, xListViewer);
	xListBox.setData(SYS_PROPETY_LIST_BOX, listBox);
	addToParent(xParent, xListBox, element);
	return xListBox;
    }
    
    @Override
    public void checkDelegate(UIObject element) {
	// clear super method
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	org.eclipse.swt.widgets.List xListBox = (org.eclipse.swt.widgets.List) element.getDelegate();
	if (xListBox == null) {
	    return;
	}
	org.eclipse.jface.viewers.ListViewer viewer = (org.eclipse.jface.viewers.ListViewer) xListBox.getData(SYS_PROPETY_LIST_VIEWER);
	ListBox listBox = (ListBox) element;
	
	if (ListBox.PROPERTY_VALUE.equals(name)) {
	    //TODO
	    //xList.setValue(value);
	    return;
	} else if (ListBox.PROPERTY_SELECTION_INDEX.equals(name)) {
	    xListBox.setSelection(intValue(value));
	    return;
	} else if (Table.PROPERTY_DATA_LIST.equals(name)) {
	    
	    List<Object> dataList = (List<Object>) value;
	    IContentProvider contentProvider = viewer.getContentProvider();
	    if (contentProvider != null) {
		contentProvider.dispose();
	    }
	    
	    SWTListContentProvider newContentProvider = new SWTListContentProvider(listBox.getDataProvider());
	    newContentProvider.setDataList(dataList);
	    viewer.setContentProvider(newContentProvider);
	    viewer.setLabelProvider(new SWTListLabelProvider(listBox));
	    viewer.setInput("");
	    return;
	} else if (ListBox.PROPERTY_DISPLAY_PROPERTY.equals(name)) {
	    // LabelProvider uses 'displayProperty'
	    return;
	}    
	super.setProperty(element, name, value);
    }

    @Override
    public Object getProperty(UIObject element, String name) {
	org.eclipse.swt.widgets.List xListBox = (org.eclipse.swt.widgets.List) element.getDelegate();
	if (xListBox == null) {
	    return null;
	}
	if (ListBox.PROPERTY_VALUE.equals(name)) {
	    //TODO
	    //return xListBox.getValue();
	    return null;
	} else if (ListBox.PROPERTY_SELECTION_INDEX.equals(name)) {
	    return xListBox.getSelectionIndex();
	}
	return super.getProperty(element, name);
    }

    
    @Override
    public Object invoke(UIObject element, String methodName, Object[] args) {
	org.eclipse.swt.widgets.List xListBox = (org.eclipse.swt.widgets.List) element.getDelegate();
	if (xListBox == null) {
	    return -1;
	}
	if (ListBox.METHOD_GET_SELECTION.equals(methodName)) {
	    return xListBox.getSelectionIndex();
	} else if (ListBox.METHOD_SET_SELECTION.equals(methodName)) {
	    xListBox.setSelection((Integer) args[0]);
	    return null;
	}
	return super.invoke(element, methodName, args);
    }
    
    @Override
    public void addListener(UIObject element, String eventType, Listener listener) {
	
	Control control = (Control) element;
	org.eclipse.swt.widgets.List xListBox = (org.eclipse.swt.widgets.List) element.getDelegate();
	if (xListBox == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    xListBox.addSelectionListener(createSelectionListener(control, listener));
	    return;
	} else if (eq(Events.Enter, eventType)) {
	    xListBox.addKeyListener(createKEnterListener(control, listener));
	    xListBox.addMouseListener(createMEnterListener(control, listener));
	    return;
	} 
 	
	super.addListener(element, eventType, listener);
    }
    
    @Override
    public void removeListener(UIObject element, String eventType, Listener listener) {
	
	Control control = (Control) element;
	org.eclipse.swt.widgets.List xListBox = (org.eclipse.swt.widgets.List) element.getDelegate();
	if (xListBox == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    xListBox.removeSelectionListener(getSelectionListener(control, listener));
	    return;
	} else if (eq(Events.Enter, eventType)) {
	    xListBox.removeKeyListener(getKeyListener(control, listener, 0));
	    xListBox.removeMouseListener(getMouseListener(control, listener, 1));
	    return;
	} 
 	
	super.removeListener(element, eventType, listener);
    }

}
