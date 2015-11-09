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

import java.util.List;

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.swing.adapter.viewer.SwingComboBoxCellRenderer;
import org.plazmaforge.framework.uwt.swing.adapter.viewer.SwingComboBoxModel;
import org.plazmaforge.framework.uwt.swing.widget.XComboBox;
import org.plazmaforge.framework.uwt.widget.ComboBox;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.Listener;

public class SwingComboBoxAdapter extends SwingControlAdapter {


    public Object createDelegate(UIObject parent, UIObject element) {
	java.awt.Container xParent = (java.awt.Container) getContent(parent.getDelegate());
	ComboBox comboBox = (ComboBox) element;
	XComboBox xComboBox = new XComboBox( new SwingComboBoxModel(comboBox));
	xComboBox.setRenderer(new SwingComboBoxCellRenderer(comboBox));
	addToParent(xParent, xComboBox, element);
	return xComboBox;
    }

    protected javax.swing.JComboBox getComboBox(Object delegate) {
	return (javax.swing.JComboBox) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	javax.swing.JComboBox xComboBox = getComboBox(element.getDelegate());
	if (xComboBox == null) {
	    return;
	}
	if (ComboBox.PROPERTY_DATA_LIST.equals(name)) {
	    
	    List dataList = (List) value;
	    SwingComboBoxModel model = (SwingComboBoxModel) xComboBox.getModel();
	    model.setDataList(dataList);

	    return;
	} else if (ComboBox.PROPERTY_VALUE.equals(name)) {
	    xComboBox.setSelectedItem(value);
	    xComboBox.repaint(); // why?
	    return;
	}  else if (ComboBox.PROPERTY_SELECTION_INDEX.equals(name)) {
	    xComboBox.setSelectedIndex(intValue(value));
	    xComboBox.repaint(); // why?
	    return;
	}
	
	super.setProperty(element, name, value);
	
    }

    
    @Override
    public Object getProperty(UIObject element, String name) {
	javax.swing.JComboBox xComboBox = getComboBox(element.getDelegate());
	if (xComboBox == null) {
	    return null;
	}
	if (ComboBox.PROPERTY_VALUE.equals(name)) {
	    return xComboBox.getSelectedItem();
	} else if (ComboBox.PROPERTY_SELECTION_INDEX.equals(name)) {
	    return xComboBox.getSelectedIndex();
	}
	return super.getProperty(element, name);
    }

    @Override
    public void addListener(UIObject element, String eventType, Listener listener) {
	Control control = (Control) element;
	javax.swing.JComboBox xComboBox = getComboBox(element.getDelegate());
	if (xComboBox == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    xComboBox.addActionListener(createActionListener(control, listener));
	    return;
	} 
	
	super.addListener(element, eventType, listener);
    }
    
    @Override
    public void removeListener(UIObject element, String eventType, Listener listener) {
	Control control = (Control) element;
	javax.swing.JComboBox xComboBox = getComboBox(element.getDelegate());
	if (xComboBox == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    xComboBox.removeActionListener(getActionListener(control, listener));
	    return;
	} 
	
	super.removeListener(element, eventType, listener);
    }

}
