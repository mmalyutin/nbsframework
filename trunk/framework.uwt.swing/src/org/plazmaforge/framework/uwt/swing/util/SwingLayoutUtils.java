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
package org.plazmaforge.framework.uwt.swing.util;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.uwt.layout.GridData;
import org.plazmaforge.framework.uwt.layout.GridLayout;
import org.plazmaforge.framework.uwt.swing.widget.XStrut;
import org.plazmaforge.framework.uwt.widget.Composite;

/**
 * 
 * @author ohapon
 *
 */
public class SwingLayoutUtils {

    private SwingLayoutUtils() {
    }

    
    public static void calculateCell(java.awt.Container xContainer, Composite composite) {
	
 	Object[] layoutData  = composite.getChildrenLayoutData().toArray(new Object[0]);
 	int lCount = layoutData.length;
 	int xCount = xContainer.getComponentCount();
 	
 	Component vStrut = null;
 	Component hStrut = null;
 	List<Component> componentList = new ArrayList<Component>();
 	for (int i = 0; i < xCount; i++) {
 	    Component c = xContainer.getComponent(i);
 	    if (c instanceof XStrut) {
 		if (vStrut == null) {
 		    vStrut = c;
 		} else if (hStrut == null) {
 		    hStrut = c;
 		} else {
 		    //error
 		}
 	    } else {
 		componentList.add(c);
 	    }
 	}
 	
 	Component[] components = componentList.toArray(new Component[0]);
 	xCount = components.length;
 	
 	if (vStrut == null) {
 	    vStrut = new XStrut();
 	    //vStrut.setBackground(Color.RED);
 	    xContainer.add(vStrut);
 	}
 	if (hStrut == null) {
 	    hStrut = new XStrut();
 	    //hStrut.setBackground(Color.GREEN);
 	    xContainer.add(hStrut);
 	}
 	
 	int count = Math.min(lCount,  xCount);
 	
 	vStrut.setVisible(false);
 	hStrut.setVisible(false);
 	
 	int column = 0; // current column
 	int row = 0; // current row
 	int columnSpan = 1; // current column span
 	int rowSpan = 1; // current row span

 	int rowCount = 1;
 	int prevColumnSpan = 0;

 	GridBagLayout xGridLayout = (GridBagLayout) xContainer.getLayout();
 	GridLayout gridLayout = (GridLayout) composite.getLayout();
 	int columnCount = gridLayout.getColumnCount();
 	
 	int maxColumn = 0;
 	int maxRow = 0;
 	
 	boolean weightx = false;
 	boolean weighty = false;
 	
 	GridBagConstraints[] constraints = new GridBagConstraints[count];
 	//Component[] components = new Component[count];
 	List<Cell> cells = new ArrayList<Cell>();
 	for (int i = 0; i < count; i++) {
 	    Object ld = layoutData[i];
 	    GridData gd = null;
 	    if (ld != null && ld instanceof GridData) {
 		gd = (GridData) ld;
 	    }
 	    columnSpan = gd == null ? 1 : gd.getColumnSpan();
 	    if (columnSpan <= 0 ) {
 		columnSpan = 1;
 	    }
 	    rowSpan = gd == null ? 1 : gd.getRowSpan();
 	    if (rowSpan <= 0 ) {
 		rowSpan = 1;
 	    }
 	    
 	    
 	    // If last column
 	    //if (column == columnCount - 1) {
 	    if (column + prevColumnSpan >= columnCount) {
 		prevColumnSpan = 0;
 		column = 0; // reset column
 		row++; // start new row
 		
 		// increment rowCount if need 
 		if (rowCount < (row + 1)) {
 		    rowCount = row + 1;
 		}
 	    } else {
 		column += prevColumnSpan;
 	    }
 	    
 	    
 	    // Fix column span
 	    if (column + columnSpan > columnCount) {
 		columnSpan = columnCount - column;
 	    }
 	    if (rowSpan > 1) {
 		rowCount = rowCount + (rowSpan - 1);
 	    }
 	    
 	    prevColumnSpan = columnSpan;
 	    
 	    // Find free space for cell if need
 	    // Previous components can fill current cell
 	    
 	    if  (!isFree(cells, column, row, columnSpan, rowSpan)) {
 		boolean isFindSpace = false;
 		while (!isFindSpace) {
 		    // last column
 		    if  (column == columnCount - 1) {
 			column = 0; // reset column
 			row++; // start new row
 		    } else {
 			column++;
 		    }
 		    isFindSpace = isFree(cells, column, row, columnSpan, rowSpan);
 		}
 		
 		// increment rowCount if need 
 		if (rowCount < (row + 1)) {
 		    rowCount = row + 1;
 		}
 	    }
 	    
 	    Component comp =  components[i]; //xContainer.getComponent(i);

 	    GridBagConstraints xConstraints = xGridLayout.getConstraints(comp);
 	    if (xConstraints == null) {
 		xConstraints = new GridBagConstraints();
 		
 		// Set align: horizontal=left, vertical=center
 		xConstraints.anchor = GridBagConstraints.WEST;
 	    }
 	    
 	    // Special fix
 	    if (gd == null && xConstraints.anchor == GridBagConstraints.CENTER) {
 		// Set align: horizontal=left, vertical=center
 		xConstraints.anchor = GridBagConstraints.WEST;
 	    }
 	    

 	    // Set cell info
 	    xConstraints.gridx = column;
 	    xConstraints.gridy = row;
 	    xConstraints.gridwidth = columnSpan;
 	    xConstraints.gridheight = rowSpan;
 	    
 	    
 	    // Only for fix last column/row weight (if need)
 	    //if (gd != null && gd.isHorizontalFlex()) {
 	    if (xConstraints.weightx > 0) {
 		weightx = true;
 	    }
 	    
 	    //if (gd != null && gd.isVerticalFlex()) {
 	    if (xConstraints.weighty > 0) {
 		weighty = true;
 	    }
 	    maxColumn = Math.max(column, maxColumn);
 	    maxRow = Math.max(row, maxRow);
 	    
 	    
 	    //xGridLayoyt.setConstraints(comp, xConstraints);
 	    
 	    components[i] = comp;
 	    constraints[i] = xConstraints;
 	    
 	    // Create cell of component
 	    Cell cell = new Cell();
 	    cell.column = column;
 	    cell.row = row;
 	    cell.columnSpan = columnSpan;
 	    cell.rowSpan = rowSpan;
 	    cells.add(cell);
 		
 	}
 	
 	// Post iteration
 	for (int i = 0; i < count; i++) {
 	    GridBagConstraints xConstraints = constraints[i];
 	    
 	    column = xConstraints.gridx;
 	    row = xConstraints.gridy;
 	    columnSpan = xConstraints.gridwidth;
 	    rowSpan = xConstraints.gridheight;
 	    
 	    Insets insets = new Insets(0, 0, 0, 0);
 	    
 	    // First row
 	    if (row == 0) {
 		insets.top = gridLayout.getMarginTop();
 	    }
 	    
 	    // Last row
 	    if (row + rowSpan - 1 == maxRow) {
 		//xConstraints.weighty = 100;
 		insets.bottom = gridLayout.getMarginBottom();
 	    }
 	    
 	    // Non first row
 	    if (row > 0) {
 		insets.top = gridLayout.getVerticalSpacing();
 	    }
 	    

 	    // First column
 	    if (column == 0) {
 		insets.left = gridLayout.getMarginLeft();
 	    }
 	    
 	    // Last column
 	    if (column + columnSpan - 1 == maxColumn) {
 		//xConstraints.weightx = 100;
 		insets.right = gridLayout.getMarginRight();
 	    }
 	    
 	    // Non first column
 	    if (column > 0) {
 		insets.left = gridLayout.getHorizontalSpacing();
 	    }

 	    xConstraints.insets = insets;
 	    
 	    Component comp = components[i];
 	    xGridLayout.setConstraints(comp, xConstraints);
 	}
 	
 	
 	if (count > 0) {
 	    if (!weightx) {
 		GridBagConstraints xConstraints = xGridLayout.getConstraints(vStrut);
 		if (xConstraints == null) {
 			xConstraints = new GridBagConstraints();
 			xConstraints.anchor = GridBagConstraints.CENTER;
 		}
 		xConstraints.gridx = maxColumn + 1;
 		xConstraints.gridy = 0;
 		xConstraints.gridwidth = 1;
 		xConstraints.gridheight = maxRow;
 		xConstraints.weightx = 1000000000;
 		xConstraints.fill = xConstraints.BOTH; 
 		xGridLayout.setConstraints(vStrut, xConstraints);
 		vStrut.setVisible(true);
 	    }
 	    
 	    if (!weighty) {
 		GridBagConstraints xConstraints = xGridLayout.getConstraints(hStrut);
 		if (xConstraints == null) {
 			xConstraints = new GridBagConstraints();
 			xConstraints.anchor = GridBagConstraints.CENTER;
 		}
 		xConstraints.gridx = 0; 
 		xConstraints.gridy = maxRow + 1;
 		xConstraints.gridwidth = maxColumn + 1;
 		xConstraints.gridheight = 1;
 		xConstraints.weighty = 100000000;
 		xConstraints.fill = xConstraints.BOTH;
 		xGridLayout.setConstraints(hStrut, xConstraints);
 		hStrut.setVisible(true);
 	    }
 	}
 	
 	//////////////////////////////////////////////////////////////////////
 	//
 	// Try fix last column/row weight
 	//
 	//////////////////////////////////////////////////////////////////////


 	/*
 	if (!weightx) {
 	    if (maxColumn == 0) {
 		xGridLayout.columnWeights = null;
 	    } else {
 		//xGridLayout.columnWeights = new double[maxColumn + 1];
 		xGridLayout.columnWeights[maxColumn] = 100;
 	    }
 	}
 	
 	if (!weighty) {
 	    if (maxRow == 0) {
 		xGridLayout.rowWeights = null;
 	    } else {
 		//xGridLayout.rowWeights = new double[maxRow + 1];
 		xGridLayout.rowWeights[maxRow] = 100;
 	    }
 	}
 	*/
 	
     }
  
     /**
      * Return true if cell is free
      * @param cells
      * @param column
      * @param row
      * @param columnSpan
      * @param rowSpan
      * @return
      */
     public static boolean isFree(List<Cell> cells, int column, int row, int columnSpan, int rowSpan) {
 	if (cells == null || cells.isEmpty()) {
 	    return true;
 	}
 	for (Cell c: cells) {
 	    if (column >= c.column && row >= c.row 
 		    && column + columnSpan <= c.column + c.columnSpan
 		    && row + rowSpan <= c.row + c.rowSpan) {
 		return false;
 	    }
 	}
 	return true;
     } 
     
     
     public static class Cell {
 	int column;
 	int row;
 	int columnSpan;
 	int rowSpan;
     }
 	     
}
