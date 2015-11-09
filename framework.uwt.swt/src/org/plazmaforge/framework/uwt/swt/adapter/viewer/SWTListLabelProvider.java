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

package org.plazmaforge.framework.uwt.swt.adapter.viewer;

import org.eclipse.swt.graphics.Image;
import org.plazmaforge.framework.uwt.util.UWTUtils;

public class SWTListLabelProvider extends SWTLabelProvider {

    
    /**
     * UWT Table
     */
    private org.plazmaforge.framework.uwt.widget.ListBox list;
    
    
    public SWTListLabelProvider(org.plazmaforge.framework.uwt.widget.ListBox list) {
	this.list = list;
    }

    
    public String getText(Object element) {
	
	Object value = UWTUtils.getSimpleValue(element, list.getDisplayProperty(), list.getPropertyProvider(), list);
	return value == null ? EMPTY_STRING : value.toString();
	        
	// TODO: Disable
	/*
	// Get value
	TreeColumn column = tree.getColumn(columnIndex);
	Accessor accessor = getAccessor(element, column);
	Object value = getValue(element, accessor);
	
	// Text value (format)
	String text = getTextValue(value, column);
	if (text == null) {
	    text = EMPTY_STRING;
	}
	*/
	
	// TODO: 
	// Must analyze CellRenderer
	
    }

    public Image getColumnImage(Object element, int columnIndex) {
	// TODO:
	// Must analyze CellRenderer
	return null;  
    }
    

}
