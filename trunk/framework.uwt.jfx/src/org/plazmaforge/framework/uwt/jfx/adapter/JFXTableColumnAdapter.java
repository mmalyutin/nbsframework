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


import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.CellEditor;
import org.plazmaforge.framework.uwt.widget.table.Table;
import org.plazmaforge.framework.uwt.widget.table.TableColumn;

import javafx.scene.control.cell.PropertyValueFactory;

/**
 * 
 * @author ohapon
 *
 */
public class JFXTableColumnAdapter extends JFXWidgetAdapter {

    public static final String SYS_PROPETY_TABLE_COLUMN = "$tableColmn";
    
    @Override
    public Object createDelegate(UIElement parent, UIElement element) {
	TableColumn tableColumn = (TableColumn) element;
	javafx.scene.control.TableView xTable = (javafx.scene.control.TableView) parent.getDelegate();
	javafx.scene.control.TableColumn xTableColumn = new javafx.scene.control.TableColumn();
	
	xTable.getColumns().add(xTableColumn);
	return xTableColumn;
    }

    @Override
    public void setProperty(UIElement element, String name, Object value) {
	javafx.scene.control.TableColumn xTableColumn = (javafx.scene.control.TableColumn) element.getDelegate();
	if (xTableColumn == null) {
	    return;
	}
	//javafx.scene.control.TableView xTable = (javafx.scene.control.TableView) parent.getDelegate();	
	//javafx.scene.control.TableView xTable = xTableColumn.getParent();
	
	if (TableColumn.PROPERTY_PROPERTY.equals(name)) {
	    xTableColumn.setCellValueFactory(new PropertyValueFactory<Object, Object>(asString(value)));
	    return;
	} else if (TableColumn.PROPERTY_TEXT.equals(name)) {
	    xTableColumn.setText(asString(value));
	    return;
	} else if (TableColumn.PROPERTY_WIDTH.equals(name)) {
	    xTableColumn.setPrefWidth(intValue(value));
	    return;
	} else if (TableColumn.PROPERTY_FORMAT.equals(name)) {
	    // TODO
	    return;
	} else if (TableColumn.PROPERTY_ALIGN.equals(name)) {
	    /*
	    HorizontalAlign align = (HorizontalAlign) value;
	    if (HorizontalAlign.LEFT.equals(align)) {
		xTableColumn.setAlignment(SWT.LEFT);
	    } else if (HorizontalAlign.RIGHT.equals(align)) {
		xTableColumn.setAlignment(SWT.RIGHT);
	    } else if (HorizontalAlign.CENTER.equals(align)) {
		xTableColumn.setAlignment(SWT.CENTER);
	    }
	    */
	    // TODO
	    return;
	} else if (TableColumn.PROPERTY_CELL_EDITOR.equals(name)) {
	    // TODO
	    return;    
	}
	
	super.setProperty(element, name, value);
    }
    
   
 
}
