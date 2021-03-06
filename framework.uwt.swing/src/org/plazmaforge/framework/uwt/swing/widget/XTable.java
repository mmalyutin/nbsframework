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

package org.plazmaforge.framework.uwt.swing.widget;

import java.awt.Dimension;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class XTable extends JTable implements IXComponent {

    /**
     * Preferred width
     */
    private int preferredWidth;
    
    /**
     * Preferred height
     */
    private int preferredHeight;
    
    /**
     * 
     */
    private int totalColumnWidth = -1;
    
    
    public XTable() {
	super();
    }

    public XTable(int numRows, int numColumns) {
	super(numRows, numColumns);
    }

    public XTable(Object[][] rowData, Object[] columnNames) {
	super(rowData, columnNames);
    }

    public XTable(TableModel dm, TableColumnModel cm, ListSelectionModel sm) {
	super(dm, cm, sm);
    }

    public XTable(TableModel dm, TableColumnModel cm) {
	super(dm, cm);
    }

    public XTable(TableModel dm) {
	super(dm);
    }

    public XTable(Vector rowData, Vector columnNames) {
	super(rowData, columnNames);
    }

    public int getPreferredWidth() {
        return preferredWidth;
    }

    public void setPreferredWidth(int preferredWidth) {
	if (preferredWidth < 0) {
	    throw new IllegalArgumentException("PreferredWidth must be >= 0");
	}
        this.preferredWidth = preferredWidth;
    }

    public int getPreferredHeight() {
        return preferredHeight;
    }

    public void setPreferredHeight(int preferredHeight) {
	if (preferredHeight < 0) {
	    throw new IllegalArgumentException("PreferredHeight must be >= 0");
	}
        this.preferredHeight = preferredHeight;
    }

    public Dimension getPreferredSize() {
	Dimension size = super.getPreferredSize();
	if (preferredWidth > 0) {
	    size.width = preferredWidth;
	} else {
	    size.width = getTotalColumnWidth();
	}
	if (preferredHeight > 0) {
	    size.height = preferredHeight;
	}
	return size;
    }

    @Override
    public Dimension getMinimumSize() {
	return getPreferredSize();
    }
    
    protected int getTotalColumnWidth() {
	//TODO Visible columns
	//return super.getColumnModel().getTotalColumnWidth();
	
	// We calculate columns width only one time !!!
	// because after relayout table/columns width of columns was changed
	// We use only design time width !!!
	if (totalColumnWidth == -1) {
	    totalColumnWidth = calculateTotalColumnWidth();
	}
	return totalColumnWidth;
    }
    
    protected int calculateTotalColumnWidth() {
	Enumeration enumeration = super.getColumnModel().getColumns();
	int totalColumnWidth = 0;
	while (enumeration.hasMoreElements()) {
	    int w = ((TableColumn)enumeration.nextElement()).getWidth();
	    totalColumnWidth += w;
	}
	return totalColumnWidth;
    }
}
