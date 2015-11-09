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

package org.plazmaforge.framework.uwt.swing.adapter;

import javax.swing.JScrollPane;

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.swing.adapter.viewer.SwingListCellRenderer;
import org.plazmaforge.framework.uwt.swing.adapter.viewer.SwingListModel;
import org.plazmaforge.framework.uwt.swing.widget.XList;
import org.plazmaforge.framework.uwt.swing.widget.XScrollPane;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.ListBox;
import org.plazmaforge.framework.uwt.widget.Listener;

public class SwingListBoxAdapter extends SwingControlAdapter {


    public Object createDelegate(UIObject parent, UIObject element) {
	java.awt.Container xParent = getContent(parent.getDelegate());
	ListBox listBox = (ListBox) element;
	XList xListBox = new XList(new SwingListModel(listBox));
	xListBox.setCellRenderer(new SwingListCellRenderer(listBox));
	
	JScrollPane scrollpane = new XScrollPane(xListBox);
	addToParent(xParent, scrollpane, element);	
	return scrollpane; 
    }
    
    @Override
    protected java.awt.Component getViewComponent(Object delegate) {
   	return (java.awt.Component) getScrollComponent(delegate);
    }


    protected javax.swing.JList getJList(Object delegate) {
	return (javax.swing.JList) getScrollComponent(delegate);
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	javax.swing.JList xListBox = getJList(element.getDelegate());
	if (xListBox == null) {
	    return;
	}
	if (eq(ListBox.PROPERTY_DATA_LIST, name)) {
	    java.util.List dataList = (java.util.List) value;
	    SwingListModel model = (SwingListModel) xListBox.getModel();
	    model.setDataList(dataList);
	    return;
	} else if (ListBox.PROPERTY_VALUE.equals(name)) {
	    //xListBox.setSelectedItem(value);
	    //xListBox.repaint(); // why?
	    return;
	}  else if (ListBox.PROPERTY_SELECTION_INDEX.equals(name)) {
	    xListBox.setSelectedIndex(intValue(value));
	    xListBox.repaint(); // why?
	    return;
	} else if (ListBox.PROPERTY_DISPLAY_PROPERTY.equals(name)) {
	    // ListModel uses 'displayProperty'
	    return;
	}    
	
	super.setProperty(element, name, value);
	
    }

    @Override
    public Object getProperty(UIObject element, String name) {
	javax.swing.JList xListBox = getJList(element.getDelegate());
	if (xListBox == null) {
	    return null;
	}
	if (ListBox.PROPERTY_VALUE.equals(name)) {
	    //return xListBox.getSelectedItem();
	    return null;
	} else if (ListBox.PROPERTY_SELECTION_INDEX.equals(name)) {
	    return xListBox.getSelectedIndex();
	}
	return super.getProperty(element, name);
    }

    
    @Override
    public void addListener(UIObject element, String eventType, final Listener listener) {
	Control control = (Control) element;
	javax.swing.JList xList = getJList(element.getDelegate());
	if (xList == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    xList.getSelectionModel().addListSelectionListener(createListSelectionListener(control, listener));
	    return;
	} else if (eq(Events.Enter, eventType)) {
	    xList.addKeyListener(createKEnterListener(control, listener));
	    xList.addMouseListener(createMEnterListener(control, listener));
	    return;
	}  
	
	super.addListener(element, eventType, listener);
    }
    
    
    @Override
    public void removeListener(UIObject element, String eventType, final Listener listener) {
	Control control = (Control) element;
	javax.swing.JList xListBox = getJList(element.getDelegate());
	if (xListBox == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    xListBox.getSelectionModel().removeListSelectionListener(getListSelectionListener(control, listener));
	    return;
	} else if (eq(Events.Enter, eventType)) {
	    xListBox.removeKeyListener(getKeyListener(control, listener, 0));
	    xListBox.removeMouseListener(getMouseListener(control, listener, 1));
	    return;
	}  
	
	super.removeListener(element, eventType, listener);
    }

    
    
}
