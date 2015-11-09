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

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.plazmaforge.framework.uwt.widget.table.Table;
import org.plazmaforge.framework.uwt.widget.table.TableColumn;

public class SWTTableLabelProvider extends SWTLabelProvider implements ITableLabelProvider {

    
    
    /**
     * UWT Table
     */
    private Table table;
    
    
    public SWTTableLabelProvider(Table table) {
	this.table = table;
    }

    public String getColumnText(Object element, int columnIndex) {
	
	// Get column
	TableColumn column = table.getColumn(columnIndex);
	return getText(element, table, column);
    }

    public org.eclipse.swt.graphics.Image getColumnImage(Object element, int columnIndex) {
	
	// Get column
	TableColumn column = table.getColumn(columnIndex);
	return getImage(element, table, column);
    }
    
  
   
}
