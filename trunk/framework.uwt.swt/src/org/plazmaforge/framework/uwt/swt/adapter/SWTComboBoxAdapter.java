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

//import org.eclipse.jface.viewers.ComboViewer;
import java.util.List;

import org.eclipse.swt.SWT;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.swt.widget.Combo;
import org.plazmaforge.framework.uwt.widget.ComboBox;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.Listener;

public class SWTComboBoxAdapter extends SWTControlAdapter {


    public Object createDelegate(UIObject parent, UIObject element) {
	org.eclipse.swt.widgets.Composite xParent = (org.eclipse.swt.widgets.Composite) getContent(parent.getDelegate());
	Combo xComboBox = new Combo(xParent, SWT.BORDER);
	//ComboViewer viewer = new ComboViever(xComboBox); 
	addToParent(xParent, xComboBox, element);
	return xComboBox;
    }

    protected Combo getCombo(Object delegate) {
	return (Combo) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	Combo xComboBox = getCombo(element.getDelegate());
	if (xComboBox == null) {
	    return;
	}
	if (ComboBox.PROPERTY_TEXT.equals(name)) {
	    xComboBox.setText(getSafeString(value));
	    return;
	} else if (ComboBox.PROPERTY_VALUE.equals(name)) {
	    xComboBox.setValue(value);
	    return;
	} else if (ComboBox.PROPERTY_SELECTION_INDEX.equals(name)) {
	    xComboBox.setSelection(intValue(value));
	    return;
	} else if (ComboBox.PROPERTY_DATA_LIST.equals(name)) {
	    
	    List<?> dataList = (List<?>) value;
	    
	    ///////////////////////////////////////////
	    // TODO
	    ///////////////////////////////////////////
	    xComboBox.removeAll();
	    if (dataList == null ) {
		return;
	    }
	    for (Object data : dataList ) {
		xComboBox.addValue(data);
	    }
	    ///////////////////////////////////////////
	    
	    return;
	} else if (ComboBox.PROPERTY_DISPLAY_PROPERTY.equals(name)) {
	    xComboBox.setDisplayProperty(getSafeString(value));
	    return;
	}
	
	super.setProperty(element, name, value);
 
	
    }


    @Override
    public Object getProperty(UIObject element, String name) {
	Combo xComboBox = getCombo(element.getDelegate());
	if (xComboBox == null) {
	    return null;
	}
	if (ComboBox.PROPERTY_TEXT.equals(name)) {
	    return xComboBox.getText();
	} else if (ComboBox.PROPERTY_VALUE.equals(name)) {
	    return xComboBox.getValue();
	} else if (ComboBox.PROPERTY_SELECTION_INDEX.equals(name)) {
	    return xComboBox.getSelectionIndex();
	}
	return super.getProperty(element, name);
    }

    
    @Override
    public Object invoke(UIObject element, String methodName, Object[] args) {
	Combo xComboBox = getCombo(element.getDelegate());
	if (xComboBox == null) {
	    return -1;
	}
	if (ComboBox.METHOD_GET_SELECTION.equals(methodName)) {
	    return xComboBox.getSelectionIndex();
	} else if (ComboBox.METHOD_SET_SELECTION.equals(methodName)) {
	    xComboBox.setSelection((Integer) args[0]);
	    return null;
	}
	return super.invoke(element, methodName, args);
    }

    
    
    @Override
    public void addListener(UIObject element, String eventType, Listener listener) {
	
	Control control = (Control) element;
	Combo xComboBox = getCombo(element.getDelegate());
	if (xComboBox == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    xComboBox.addSelectionListener(createSelectionListener(control, listener));
	    return;
	}
	
	super.addListener(element, eventType, listener);
    }

    @Override
    public void removeListener(UIObject element, String eventType, Listener listener) {
	
	Control control = (Control) element;
	Combo xComboBox = getCombo(element.getDelegate());
	if (xComboBox == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    xComboBox.removeSelectionListener(getSelectionListener(control, listener));
	    return;
	}
	
	super.removeListener(element, eventType, listener);
    }

}
