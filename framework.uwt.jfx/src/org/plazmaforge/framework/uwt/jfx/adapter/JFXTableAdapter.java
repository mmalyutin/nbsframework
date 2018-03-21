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
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Viewer.AutoResizeColumns;
import org.plazmaforge.framework.uwt.widget.Viewer.SelectionMode;
import org.plazmaforge.framework.uwt.widget.table.Table;
import org.plazmaforge.framework.uwt.widget.table.TableColumn;

/**
 * 
 * @author ohapon
 *
 */
public class JFXTableAdapter extends JFXViewerAdapter {

    
    public Object createDelegate(UIElement parent, UIElement element) {
	Table table = (Table) element;
	javafx.scene.Parent xParent = getContent(parent.getDelegate());
	javafx.scene.control.TableView<?> xTable = new javafx.scene.control.TableView();
	
	// TODO: SelectionMode(Single, Multi), HeaderVisible, LinesVisible

	addChild(xParent, xTable, element);
	return xTable;
    }
    
    
    @Override
    public void checkDelegate(UIElement element) {
	// clear super method
    }

    protected javafx.scene.control.TableView<?> asTable(Object delegate) {
	return (javafx.scene.control.TableView<?>) delegate;
    }
    
    @Override
    public void setProperty(UIElement element, String name, Object value) {
	
	javafx.scene.control.TableView xTable = asTable(element.getDelegate());
	if (xTable == null) {
	    return;
	}
	
	if (Table.PROPERTY_LAYOUT.equals(name)) {
	    // ignore layout
	    return;
	} else if (Table.PROPERTY_LINES_VISIBLE.equals(name)) {
	    // TODO
	    return;
	} else if (Table.PROPERTY_HEADER_VISIBLE.equals(name)) {
	    // TODO
	    return;
	} else if (Table.PROPERTY_DATA_LIST.equals(name)) {
	    List<?> dataList = (List<?>) value;
	    xTable.setItems(toFXList(dataList));
	    return;
	} else if (Table.PROPERTY_SELECTION_INDEX.equals(name)) {
	    setSelectedIndex(xTable, intValue(value));
	    return;
	} else if (Table.PROPERTY_AUTO_RESIZE_COLUMNS.equals(name)) {
	    
	    AutoResizeColumns autoResizeMode = (AutoResizeColumns) value;
	    
	    // - OFF	Yes
	    // - ALL	Yes
	    // - LAST	No

	    // TODO
	    return;
	} else if (Table.PROPERTY_SELECTION_MODE.equals(name)) {
	    // TODO: ROW, CELL Selection
	    SelectionMode selectionMode = (SelectionMode) value;
	    
	    if (selectionMode == null || SelectionMode.ROW == selectionMode) {
		//TODO
	    } else if (SelectionMode.CELL == selectionMode) {
		//TODO
	    }
	    return;
	} else if (Table.PROPERTY_CHECK_SELECTION.equals(name)) {
	    // TODO
	    return;
	}
	
	super.setProperty(element, name, value);
    }
    
    @Override
    public Object getProperty(UIElement element, String name) {
	javafx.scene.control.TableView<?> xTable = asTable(element.getDelegate());
	if (xTable == null) {
	    return null;
	}
	if (Table.PROPERTY_SELECTION_INDEX.equals(name)) {
	    return getSelectedIndex(xTable);
	}
	return super.getProperty(element, name);
    }

    
    @Override
    public Object invoke(UIElement element, String methodName, Object[] args) {
	Table table = (Table) element;
	javafx.scene.control.TableView<?> xTable = asTable(element.getDelegate());
	if (eq(Table.METHOD_GET_SELECTION_INDEX, methodName)) {
	    if (xTable == null) {
		return -1;
	    }
	    return getSelectedIndex(xTable);
	} else if (eq(Table.METHOD_UPDATE_COLUMNS, methodName)) {
	    // Reset sort columns mode
	    // TODO
	    return null;
	}
	return super.invoke(element, methodName, args);
    }
    
    
    @Override
    public void addListener(UIElement element, String eventType, final Listener listener) {
	
	Control control = (Control) element;
	javafx.scene.control.TableView<?> xTable = asTable(element.getDelegate());
	if (xTable == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    // TODO
	    //xTable.addSelectionListener(createSelectionListener(control, listener));
	    return;
	} else if (eq(Events.Enter, eventType)) {
	    // TODO
	    //xTable.addKeyListener(createKEnterListener(control, listener));
	    //xTable.addMouseListener(createMEnterListener(control, listener));
	    return;
	} 

	
	super.addListener(element, eventType, listener);
    }

    @Override
    public void removeListener(UIElement element, String eventType, final Listener listener) {
	
	Control control = (Control) element;
	javafx.scene.control.TableView<?> xTable = asTable(element.getDelegate());
	if (xTable == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    // TODO
	    //xTable.removeSelectionListener(getSelectionListener(control, listener));
	    return;
	} else if (eq(Events.Enter, eventType)) {
	    // TODO
	    //xTable.removeKeyListener(getKeyListener(control, listener, 0));
	    //xTable.removeMouseListener(getMouseListener(control, listener, 1));
	    return;
	} 

	super.removeListener(element, eventType, listener);
    }

 
  
    
}
