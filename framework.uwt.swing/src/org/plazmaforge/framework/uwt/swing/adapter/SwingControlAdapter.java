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

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;



import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.layout.GridData;
import org.plazmaforge.framework.uwt.layout.GridLayout;
import org.plazmaforge.framework.uwt.swing.layout.XCardLayout;
import org.plazmaforge.framework.uwt.swing.widget.IXComponent;
import org.plazmaforge.framework.uwt.swing.widget.XStrut;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.menu.Menu;

public abstract class SwingControlAdapter extends SwingWidgetAdapter {


    protected void addToParent(java.awt.Container xParent, java.awt.Component xElement, UIObject element) {
	LayoutManager xLayout = xParent.getLayout();
	
	/*
	if (xParent instanceof JSplitPane) {
	    JSplitPane splitPanel = (JSplitPane) xParent;
	    int count = splitPanel.getComponentCount();
	    count--; // Fix count (divider is first component)
	    if (count == 0) {
		xParent.add(xElement, JSplitPane.LEFT);
		return;
	    } else if (count == 1) {
		xParent.add(xElement, JSplitPane.RIGHT);
		return;
	    } else {
		//TODO
		return;
	    }
	    
	}
	*/
	
	if (xLayout == null) {
	    xParent.add(xElement);
	    return;
	}
	
	if (xLayout instanceof GridBagLayout) {
	    Composite composite = (Composite) element.getUIParent();
	    //GridData gd = (GridData) ((Control) element).getLayoutData();
	    xParent.add(xElement);
	    //xParent.add(xElement, gd.getDelegate());
	    calculateCell(xParent, composite);
	    return;
	} else if (xLayout instanceof java.awt.CardLayout || xLayout instanceof XCardLayout) {
	    xParent.add(xElement, "");
	    return;
	}
	xParent.add(xElement);
    }
    
    public void disposeDelegate(UIObject parent, UIObject element) {
	java.awt.Container xParent = getContent(parent.getDelegate());
	java.awt.Component xElement = getComponent(element.getDelegate());
	if (xParent != null) {
	    xParent.remove(xElement);
	}
    }

    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	
	
	java.awt.Component xControl = getComponent(element.getDelegate());
	if (xControl == null) {
	    return;
	}
	javax.swing.JComponent xJControl = null;
	
	if (Control.PROPERTY_VISIBLE.equals(name)) {
	    xControl.setVisible(getBoolean(value));
	} else if (Control.PROPERTY_ENABLED.equals(name)) {
	    xControl.setEnabled(getBoolean(value));
	} else if (Control.PROPERTY_WIDTH.equals(name)) {
	    int width = (Integer) value;
	    
	    if (xControl instanceof IXComponent) {
		((IXComponent) xControl).setPreferredWidth(width);
		return;
	    }
	    
	    // TODO Why getSize. May be getPrefferdSize if use javax.swing.JComponent
	    java.awt.Dimension size = xControl.getSize();  
	    size.width = width;
	    
	    if (xControl instanceof javax.swing.JComponent) {
		xControl.setPreferredSize(size);
	    } else {
		xControl.setSize(size);
	    }
		  
	    
	    return;
	} else if (Control.PROPERTY_HEIGHT.equals(name)) {
	    int height = (Integer) value;

	    if (xControl instanceof IXComponent) {
		((IXComponent) xControl).setPreferredHeight(height);
		return;
	    }

	    // TODO Why getSize. May be getPrefferdSize if use javax.swing.JComponent
	    java.awt.Dimension size = xControl.getSize();
	    size.height = (Integer) value;
	    
	    
	    if (xControl instanceof javax.swing.JComponent) {
		xControl.setPreferredSize(size);
	    } else {
		xControl.setSize(size);
	    }
	    
	    return;
	} else if (Control.PROPERTY_LAYOUT_DATA.equals(name)) {
	    UIObject layoutData = (UIObject) value;
	    
	    layoutData.activateUI();
	    java.awt.Container xParent = xControl.getParent();
	    if (xParent == null) {
		return;
	    }
	    
	    LayoutManager xLayout = xParent.getLayout();
	    if (xLayout == null) {
		return;
	    }
	    if (xLayout instanceof GridBagLayout) {
		GridBagLayout xGridLayout = (GridBagLayout) xLayout;
		GridBagConstraints xConstraints = (GridBagConstraints) layoutData.getDelegate();
		xGridLayout.setConstraints(xControl, xConstraints);
		Control control = (Control) element;
		Composite parent = (Composite) control.getParent();
		calculateCell(xParent, parent);
	    }
	    return;
	} else if (Control.PROPERTY_TOOL_TIP.equals(name)) {
	    xControl = getViewContent(xControl);
	    if (xControl instanceof javax.swing.JComponent) {
		xJControl = (javax.swing.JComponent) xControl;
		xJControl.setToolTipText(getSafeString(value));
	    }
	    return;
	} else if (Composite.PROPERTY_BACKGROUND.equals(name)) {
	    xControl = getViewContent(xControl);
	    Color color = (Color) value;
	    xControl.setBackground(getColor(color));
	    return;
	} else if (Composite.PROPERTY_FOREGROUND.equals(name)) {
	    xControl = getViewContent(xControl);
	    Color color = (Color) value;
	    xControl.setForeground(getColor(color));
	    return;
	} else if (Control.PROPERTY_FONT.equals(name)) {
	    Font font = (Font) value;
	    xControl.setFont(getFont(font));
	    return;
	} else if (Control.PROPERTY_CONTEXT_MENU.equals(name)) {
	    xControl = getViewContent(xControl);
	    if (xControl instanceof javax.swing.JComponent) {
		xJControl = (javax.swing.JComponent) xControl;
		Menu menu = (Menu) value;
		menu.activateUI();
		xJControl.setComponentPopupMenu((javax.swing.JPopupMenu) menu.getDelegate());
	    }
	    return;
	}

	
	super.setProperty(element, name, value);
	 
	
    }
    
    
    @Override
    public Object getProperty(UIObject element, String name) {
	
	java.awt.Component xControl = getComponent(element.getDelegate());
	if (xControl == null) {
	    return null;
	}
	if (Control.PROPERTY_VISIBLE.equals(name)) {
	    return xControl.isVisible();
	} else if (Control.PROPERTY_ENABLED.equals(name)) {
	    return xControl.isEnabled();
	}
	
	return super.getProperty(element, name);
    }
    
    
    protected void calculateCell(java.awt.Container xContainer, Composite composite) {
	
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
    
    class Cell {
	int column;
	int row;
	int columnSpan;
	int rowSpan;
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
    private boolean isFree(List<Cell> cells, int column, int row, int columnSpan, int rowSpan) {
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
    
    @Override
    public void addListener(UIObject element, String eventType, Listener listener) {
	
	Control control = (Control) element;
	java.awt.Component xControl = getViewComponent(element.getDelegate());
	if (xControl == null) {
	    return;
	}

	if (eq(Events.KeyDown, eventType)) {
	    xControl.addKeyListener(createKeyDownListener(control, listener));
	    return;
	} else if (eq(Events.KeyUp, eventType)) {
	    xControl.addKeyListener(createKeyUpListener(control, listener));
	    return;
	} else if (eq(Events.MouseDown, eventType)) {
	    xControl.addMouseListener(createMouseDownListener(control, listener));
	    return;
	} else if (eq(Events.MouseUp, eventType)) {
	    xControl.addMouseListener(createMouseUpListener(control, listener));
	    return;
	} else if (eq(Events.MouseClick, eventType)) {
	    xControl.addMouseListener(createMouseClickListener(control, listener));
	    return;
	} else if (eq(Events.MouseDoubleClick, eventType)) {
	    xControl.addMouseListener(createMouseDoubleClickListener(control, listener));
	    return;
	} else if (eq(Events.MouseMove, eventType)) {
	    xControl.addMouseListener(createMouseMoveListener(control, listener));
	    return;
	} else if (eq(Events.MouseIn, eventType)) {
	    xControl.addMouseListener(createMouseInListener(control, listener));
	    return;
	} else if (eq(Events.MouseOut, eventType)) {
	    xControl.addMouseListener(createMouseOutListener(control, listener));
	    return;
	} else if (eq(Events.FocusIn, eventType)) {
	    xControl.addFocusListener(createFocusInListener(control, listener));
	    return;
	} else if (eq(Events.FocusOut, eventType)) {
	    xControl.addFocusListener(createFocusOutListener(control, listener));
	    return;
	}
	
	super.addListener(element, eventType, listener);
    }
    
    @Override
    public void removeListener(UIObject element, String eventType, Listener listener) {
	
	Control control = (Control) element;
	java.awt.Component xControl = getViewComponent(element.getDelegate());
	if (xControl == null) {
	    return;
	}

	if (eq(Events.KeyDown, eventType)) {
	    xControl.removeKeyListener(getKeyListener(control, listener));
	    return;
	} else if (eq(Events.KeyUp, eventType)) {
	    xControl.removeKeyListener(getKeyListener(control, listener));
	    return;
	} else if (eq(Events.MouseDown, eventType)) {
	    xControl.removeMouseListener(getMouseListener(control, listener));
	    return;
	} else if (eq(Events.MouseUp, eventType)) {
	    xControl.removeMouseListener(getMouseListener(control, listener));
	    return;
	} else if (eq(Events.MouseClick, eventType)) {
	    xControl.removeMouseListener(getMouseListener(control, listener));
	    return;
	} else if (eq(Events.MouseDoubleClick, eventType)) {
	    xControl.removeMouseListener(getMouseListener(control, listener));
	    return;
	} else if (eq(Events.MouseMove, eventType)) {
	    xControl.removeMouseListener(getMouseListener(control, listener));
	    return;
	} else if (eq(Events.MouseIn, eventType)) {
	    xControl.removeMouseListener(getMouseListener(control, listener));
	    return;
	} else if (eq(Events.MouseOut, eventType)) {
	    xControl.removeMouseListener(getMouseListener(control, listener));
	    return;
	} else if (eq(Events.FocusIn, eventType)) {
	    xControl.removeFocusListener(getFocusListener(control, listener));
	    return;
	} else if (eq(Events.FocusOut, eventType)) {
	    xControl.removeFocusListener(getFocusListener(control, listener));
	    return;
	}
	
	super.removeListener(element, eventType, listener);
    }

    @Override
    public Object invoke(UIObject element, String methodName, Object[] args) {
	if (Control.METHOD_REPAINT.equals(methodName)) {
	    java.awt.Component xControl = getViewComponent(element.getDelegate());
	    if (xControl == null) {
		return null;
	    }
	    xControl.repaint();
	}
	return super.invoke(element, methodName, args);
    }
   
}
