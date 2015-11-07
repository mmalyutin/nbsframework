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

package org.plazmaforge.framework.uwt.widget.table;

import org.plazmaforge.framework.uwt.widget.Column;
import org.plazmaforge.framework.uwt.widget.Widget;

/**
 * Table column
 * 
 * @author ohapon
 *
 */
public class TableColumn extends Column {

    public void setParent(Widget parent) {
  	if (!(parent instanceof Table)) {
  	    throw new IllegalArgumentException("Parent must be Table");
  	}
  	super.setParent(parent);
      }
      
      public Table getTable() {
  	return (Table) getParent();
      }
    
    
}
