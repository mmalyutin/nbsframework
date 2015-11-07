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


package org.plazmaforge.framework.uwt.demo.tabs;

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.core.data.PropertyProvider;
import org.plazmaforge.framework.core.data.ValueProvider;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.event.SelectionEvent;
import org.plazmaforge.framework.uwt.event.SelectionListener;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.layout.FitLayout;
import org.plazmaforge.framework.uwt.widget.CellContext;
import org.plazmaforge.framework.uwt.widget.CellRenderer;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.DefaultCellEditor;
import org.plazmaforge.framework.uwt.widget.DefaultCellRenderer;
import org.plazmaforge.framework.uwt.widget.LabelProvider;
import org.plazmaforge.framework.uwt.widget.MessageBox;
import org.plazmaforge.framework.uwt.widget.Viewer.SelectionMode;
import org.plazmaforge.framework.uwt.widget.menu.Menu;
import org.plazmaforge.framework.uwt.widget.menu.MenuItem;
import org.plazmaforge.framework.uwt.widget.panel.Panel;
import org.plazmaforge.framework.uwt.widget.table.Table;
import org.plazmaforge.framework.uwt.widget.table.TableColumn;

public class TableTab extends AbstractTab {

    public TableTab() {
    }

    @Override
    protected void createUI() {
	setLayout(new FitLayout());
	Panel panel = createTablePanel();
	add(panel);
    }
    
    
    private Panel createTablePanel() {
	Panel panel = new Panel();
	panel.setLayout(new FitLayout());
	
	Table<Object[]> table = new Table<Object[]>();
	table.setSelectionMode(SelectionMode.CELL);
	table.setCheckSelection(true);
	table.setAutoResizeColumns(Table.AutoResizeColumns.ALL);
	table.setToolTip("Table tooltip");
	
	TableColumn column1 = new TableColumn();
	column1.setText("Column 1");
	column1.setProperty("1");
	column1.setWidth(200);
	table.addColumn(column1);

	TableColumn column2 = new TableColumn();
	column2.setText("Column 2");
	column2.setProperty("2");
	column2.setCellEditor(new DefaultCellEditor());
	column1.setWidth(300);
	table.addColumn(column2);

	int count = 10;
	List<Object[]> items = new ArrayList<Object[]>();
	for (int i = 0; i < count; i++) {
	    int index = i + 1;
	    items.add(new Object[] {index, "Value " + index});
	}
	
	table.setPropertyProvider(new PropertyProvider<Object[]>() {
	    
	    @Override
	    public void setValue(Object[] element, String property, Object value) {
		if (element == null) {
		    return;
		}
		if ("1".equals(property)) {
		    element[0] = value;
		} else if ("2".equals(property)) {
		    element[1] = value;
		}
	    }
	    
	    @Override
	    public Object getValue(Object[] element, String property) {
		if (element == null) {
		    return null;
		}
		if ("1".equals(property)) {
		    return element[0];
		} else if ("2".equals(property)) {
		    return element[1];
		}
		return null;
	    }
	});
	
	final Image folderImage = new Image("widget/folder.gif");
	final Image leafImage = new Image("widget/leaf.gif"); 

	
	column1.setLabelProvider(new LabelProvider() {

	    @Override
	    public Image getImage(Object element) {
		Object[] array = (Object[]) element;
		Integer value = (Integer) array[0];
		int mod = value % 2; 
		return mod > 0 ?  folderImage: leafImage;
	    }

	    @Override
	    public String getText(Object element) {
		if (element == null) {
		    return null;
		}
		Object[] array = (Object[]) element;
		return "Row " + array[0];
	    }
	    
	   
	});

	//column2.setAlign(HorizontalAlign.RIGHT);
	
	/*
	column2.setValueProvider(new ValueProvider() {
	    
	    @Override
	    public void setValue(Object element, Object value) {
			
	    }
	    
	    @Override
	    public Object getValue(Object element) {
		if (element == null) {
		    return null;
		}
		Object[] array = (Object[]) element;
		return "" + array[1] + " NEW";
	    }
	});
	*/
	
	
	//DefaultCellRenderer cellRenderer2 = new DefaultCellRenderer();
	//cellRenderer2.setBackground(Color.GREEN);
	
	CellRenderer cellRenderer2 = new CellRenderer() {

	    @Override
	    public void render(CellContext cellContext) {
		if (/*!cellContext.selected && */ cellContext.getRow() % 2 == 0) {
		    cellContext.setBackground(Color.GRAY);
		    cellContext.setForeground(Color.RED);
		}
		//cellContext.background = cellContext.row % 2 == 0 ? Color.GRAY : Color.WHITE;
		return;
	    }
	    
	};
	
	column2.setCellRenderer(cellRenderer2);
	
	table.setItems(items);

	Menu contextMenu = new Menu();
	SelectionListener selectionListener = new SelectionListener() {
	    
	    @Override
	    public void select(SelectionEvent event) {
		MessageBox.information("Information", "Click " + ((MenuItem) event.getWidget()).getText());
	    }
	};
	
	MenuItem menuItem1 = new MenuItem("Item 1");
	menuItem1.addSelectionListener(selectionListener);
	
	contextMenu.addItem(menuItem1);
	
	MenuItem menuItem2 = new MenuItem("Item 2");
	contextMenu.addItem(menuItem2);
	menuItem2.addSelectionListener(selectionListener);

	table.setContextMenu(contextMenu);
	
	panel.add(table);
	
	return panel;
    }

}
