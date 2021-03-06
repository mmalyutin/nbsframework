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

package org.plazmaforge.framework.uwt.jfx.adapter;


import java.util.List;

import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.widget.ComboBox;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.Listener;


/**
 * 
 * @author ohapon
 *
 */
public class JFXListBoxAdapter extends JFXViewerAdapter {


    public Object createDelegate(UIElement parent, UIElement element) {
	javafx.scene.Parent xParent = getContent(parent.getDelegate());
	javafx.scene.control.ListView xListBox = new javafx.scene.control.ListView();
	addChild(xParent, xListBox, element);
	return xListBox;
    }

    protected javafx.scene.control.ListView asComboBox(Object delegate) {
	return (javafx.scene.control.ListView) delegate;
    }
    
 
    
    @Override
    public void setProperty(UIElement element, String name, Object value) {
	
	javafx.scene.control.ListView xListBox = asComboBox(element.getDelegate());
	if (xListBox == null) {
	    return;
	}
	if (ComboBox.PROPERTY_TEXT.equals(name)) {
	    //TODO
	    //xComboBox.setText(asSafeString(value));
	    return;
	} else if (ComboBox.PROPERTY_VALUE.equals(name)) {
	    //xListBox.setValue(value);
	    setSelectedItem(xListBox, value);
	    return;
	} else if (ComboBox.PROPERTY_SELECTION_INDEX.equals(name)) {
	    setSelectedIndex(xListBox, intValue(value));
	    return;
	} else if (ComboBox.PROPERTY_DATA_LIST.equals(name)) {
	    
	    List<?> dataList = (List<?>) value;
	    xListBox.setItems(toFXList(dataList));
	    return;
	} else if (ComboBox.PROPERTY_DISPLAY_PROPERTY.equals(name)) {
	    //TODO
	    //xComboBox.setDisplayProperty(asSafeString(value));
	    return;
	}
	
	super.setProperty(element, name, value);
 
	
    }


    @Override
    public Object getProperty(UIElement element, String name) {
	javafx.scene.control.ListView xListBox = asComboBox(element.getDelegate());
	if (xListBox == null) {
	    return null;
	}
	if (ComboBox.PROPERTY_TEXT.equals(name)) {
	    //TODO
	    //return xComboBox.getText();
	    return null;
	} else if (ComboBox.PROPERTY_VALUE.equals(name)) {
	    //return xListBox.getValue();
	    return getSelectedItem(xListBox);
	} else if (ComboBox.PROPERTY_SELECTION_INDEX.equals(name)) {
	    return getSelectedIndex(xListBox);
	}
	return super.getProperty(element, name);
    }

    
    @Override
    public Object invoke(UIElement element, String methodName, Object[] args) {
	javafx.scene.control.ListView xListBox = asComboBox(element.getDelegate());
	if (xListBox == null) {
	    return -1;
	}
	if (ComboBox.METHOD_GET_SELECTION.equals(methodName)) {
	    return getSelectedIndex(xListBox);
	} else if (ComboBox.METHOD_SET_SELECTION.equals(methodName)) {
	    setSelectedIndex(xListBox, intValue(args[0]));
	    return null;
	}
	return super.invoke(element, methodName, args);
    }

    
    
//    @Override
//    public void addListener(UIElement element, String eventType, Listener listener) {
//	
//	Control control = (Control) element;
//	javafx.scene.control.ComboBox xComboBox = asComboBox(element.getDelegate());
//	if (xComboBox == null) {
//	    return;
//	}
//
//	if (eq(Events.Selection, eventType)) {
//	    xComboBox.addSelectionListener(createSelectionListener(control, listener));
//	    return;
//	}
//	
//	super.addListener(element, eventType, listener);
//    }
//
//    @Override
//    public void removeListener(UIElement element, String eventType, Listener listener) {
//	
//	Control control = (Control) element;
//	javafx.scene.control.ComboBox xComboBox = asComboBox(element.getDelegate());
//	if (xComboBox == null) {
//	    return;
//	}
//
//	if (eq(Events.Selection, eventType)) {
//	    xComboBox.removeSelectionListener(getSelectionListener(control, listener));
//	    return;
//	}
//	
//	super.removeListener(element, eventType, listener);
//    }

}
