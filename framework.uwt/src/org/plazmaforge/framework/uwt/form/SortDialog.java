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

package org.plazmaforge.framework.uwt.form;

import java.util.ArrayList;
import java.util.List;


import org.plazmaforge.framework.core.data.GetPropertyProvider;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Style.Orientation;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;
import org.plazmaforge.framework.uwt.dialog.Dialog;
import org.plazmaforge.framework.uwt.layout.GridData;
import org.plazmaforge.framework.uwt.layout.GridLayout;
import org.plazmaforge.framework.uwt.widget.Button;
import org.plazmaforge.framework.uwt.widget.CallbackResult;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.table.Table;
import org.plazmaforge.framework.uwt.widget.table.TableColumn;
import org.plazmaforge.framework.uwt.widget.tool.ButtonBar;
import org.plazmaforge.framework.uwt.event.EnterEvent;
import org.plazmaforge.framework.uwt.event.EnterListener;
import org.plazmaforge.framework.uwt.event.MouseAdapter;
import org.plazmaforge.framework.uwt.event.MouseEvent;
import org.plazmaforge.framework.uwt.event.SelectionEvent;
import org.plazmaforge.framework.uwt.event.SelectionListener;

/**
 * Sort Dialog
 * 
 * @author ohapon
 *
 */
public class SortDialog extends Dialog {

    private final static int TABLE_WIDTH = 200;
    
    private final static int TABLE_HEIGHT = 200;
    
    private final static String PROPERTY_LABEL = "label";
    
    /**
     * Display fields
     * For example: all display columns for table
     */
    private List<DisplayField> displayFields;
    
    /**
     * Input fields
     */
    private List<OrderField> inputFields;
    
    
    /**
     * Output fields
     */
    private List<OrderField> outputFields;
    
    
    /**
     * Input table
     */
    private Table<OrderField> inputTable;
    
    
    /**
     * Output table
     */
    private Table<OrderField> outputTable;

    
    
    /**
     * Button  bar (add, remove, add all, remove all)
     */
    private ButtonBar buttonBar;

    
    
    /**
     * Add button (>): input > output
     */
    private Button addButton;
    
    /**
     * Remove button (<): input < output
     */
    private Button removeButton;

    
    /**
     * Add all button (>>): input >> output
     */
    private Button addAllButton;
    
    /**
     * Remove button (<<): input << output
     */
    private Button removeAllButton;
    
    
    
    

    public SortDialog() {
	super();
	setNeedFixSize(false);
    }


    @Override
    protected void configure() {
	super.configure();
	setTitle("Sort Dialog");
	setPack(true);
	
    }
    
	
    @Override
    protected void buildContent(Composite parent) {
	GridLayout layout = new GridLayout(3); 
	parent.setLayout(layout);
	//parent.setData("debugLayout", true);

	inputTable = createInputTable();
	inputTable.setLayoutData(new GridData(1, 1, HorizontalAlign.FILL, VerticalAlign.FILL, false, false));
	parent.add(inputTable);
	buttonBar = createSelectionButtonBar();
	buttonBar.setLayoutData(new GridData(1, 1, HorizontalAlign.FILL, VerticalAlign.FILL, false, false));
	parent.add(buttonBar);
	outputTable = createOutputTable();
	outputTable.setLayoutData(new GridData(1, 1, HorizontalAlign.FILL, VerticalAlign.FILL, false, false));
	parent.add(outputTable);
    }

    protected Table<OrderField> createInputTable() {
	Table<OrderField> table = new Table<OrderField>();
	table.setHeight(TABLE_HEIGHT);
	
	TableColumn column = new TableColumn();
	column.setText("Input");
	column.setProperty(PROPERTY_LABEL);
	column.setWidth(TABLE_WIDTH);
	
	table.addColumn(column);
	
	table.setPropertyProvider(new OrderProvider());
	
	table.addEnterListener(new EnterListener() {
	    @Override
	    public void enter(EnterEvent e) {
		// Add by enter/double click
		doAddAction();
	    }
	});
	return table;
    }

    protected Table<OrderField> createOutputTable() {
	Table<OrderField> table = new Table<OrderField>();
	table.setHeight(TABLE_HEIGHT);
	TableColumn column = new TableColumn();
	column.setText("Output");
	column.setProperty(PROPERTY_LABEL);
	column.setWidth(TABLE_WIDTH);
	table.addColumn(column);
	table.setPropertyProvider(new OrderProvider(true));
	
	table.addEnterListener(new EnterListener() {
	    @Override
	    public void enter(EnterEvent e) {
		// Add by enter/double click
		doRemoveAction();
	    }
	});
	
	table.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClick(MouseEvent e) {
		// TODO: Disable - Use other column (first) to change 'ASC' flag of order filed
		// Resort by click
		//doResortAction();
	    }
	});
	
	return table;
    }

    protected ButtonBar createSelectionButtonBar() {
	ButtonBar buttonBar = new ButtonBar(Orientation.VERTICAL);
	
	addButton = new Button(">");
	buttonBar.addButton(addButton);
	addButton.addSelectionListener(new SelectionListener() {
	    
	    @Override
	    public void select(SelectionEvent event) {
		doAddAction();
	    }
	});
	
	removeButton = new Button("<");
	buttonBar.addButton(removeButton);
	removeButton.addSelectionListener(new SelectionListener() {
	    
	    @Override
	    public void select(SelectionEvent event) {
		doRemoveAction();
	    }
	});
	
	
	addAllButton = new Button(">>");
	buttonBar.addButton(addAllButton);
	addAllButton.addSelectionListener(new SelectionListener() {
	    
	    @Override
	    public void select(SelectionEvent event) {
		doAddAllAction();
	    }
	});

	
	removeAllButton = new Button("<<");
	buttonBar.addButton(removeAllButton);
	removeAllButton.addSelectionListener(new SelectionListener() {
	    
	    @Override
	    public void select(SelectionEvent event) {
		doRemoveAllAction();
	    }
	});

	
	return buttonBar;
    }

    @Override
    protected void load() {
	if (inputFields != null) {
	    inputFields.clear();
	}
	if (outputFields != null) {
	    outputFields.clear();
	}
	
	inputFields = new ArrayList<OrderField>();
	outputFields = new ArrayList<OrderField>();
	
	if (displayFields != null) {
	    for (DisplayField displayField : displayFields) {
		// Create OrderField by DisplayField
		OrderField orderField = new OrderField(displayField.getProperty(), displayField.getLabel());
		if (orderField.isSelected()) {
		    outputFields.add(orderField);
		} else {
		    inputFields.add(orderField);
		}
	    }
	}
	
	inputTable.setItems(inputFields);
	outputTable.setItems(outputFields);
    }

    public List<DisplayField> getDisplayFields() {
	if (displayFields == null) {
	    displayFields = new ArrayList<DisplayField>();
	}
        return displayFields;
    }

    public void setDisplayFields(List<DisplayField> displayFields) {
        this.displayFields = displayFields;
    }
    


    class OrderProvider extends GetPropertyProvider<OrderField> {
	

	/**
	 * Direction flag
	 */
	private boolean direction;
	
	
	public OrderProvider() {
	    super();
	}
	
	public OrderProvider(boolean direction) {
	    super();
	    this.direction = direction;
	}

	@Override
	public Object getValue(OrderField element, String property) {
	    OrderField orderField = (OrderField) element;
	    if (PROPERTY_LABEL.equals(property)) {
		if (!direction) {
		    return orderField.getLabel();
		}
		return (orderField.isAsc() ? ">"  : "<") + " " + orderField.getLabel(); 
	    }
	    return null;
	}
	
    }
    
    protected boolean isValidContent() {
	return inputTable != null && outputTable != null;
    }
    
    
    
    /**
     * Return selection field from table
     *  
     * @param table
     * @return
     */
    protected OrderField getSelectionField(Table<OrderField> table) {
	if (table == null) {
	    return null;
	}
	int index = table.getSelectionIndex();
	if (index < 0) {
	    return null;
	}
	// TODO: Use getItem by index
	return table.getItems().get(index);
    }
    
    protected void addFiled(Table<OrderField> table, OrderField field){
	if (table == null || field == null) {
	    return;
	}
	
	// Set ASC
	field.setAsc(true);
	
	// TODO: Use addItem
	List<OrderField> items = table.getItems();
	items.add(field);
	table.setItems(items);
    }

    protected void removeFiled(Table<OrderField> table, OrderField field){
	if (table == null || field == null) {
	    return;
	}
	// TODO: Use removeItem
	List<OrderField> items = table.getItems();
	items.remove(field);
	table.setItems(items);
    }

    protected void addFileds(Table<OrderField> table, List<OrderField> fields){
	if (table == null || fields == null) {
	    return;
	}
	for (OrderField field : fields) {
	    addFiled(table, field);
	}
    }
    
    protected void removeFileds(Table<OrderField> table, List<OrderField> fields){
	if (table == null || fields == null) {
	    return;
	}
	for (OrderField field : fields) {
	    removeFiled(table, field);
	}
    }
    
    protected List<OrderField> getFields(Table<OrderField> table) {
	if (table == null) {
	    return null;
	}
	List<OrderField> fields = table.getItems();
	return fields == null ? null : (fields.isEmpty() ? null : fields);
    }


    /**
     * Refresh all tables (input, output)
     */
    protected void refresh() {
	if (inputTable != null) {
	    inputTable.refresh();
	}
	if (outputTable != null) {
	    outputTable.refresh();
	}
    }
    
    
    /**
     * Add selected order field to output table 
     */
    protected void doAddAction() {
	if (!isValidContent()) {
	    return;
	}
	OrderField field = getSelectionField(inputTable);
	if (field == null) {
	    return;
	}
	addFiled(outputTable, field); // Add to output
	removeFiled(inputTable, field); // Remove from input
	refresh();
    }
    
    
    /**
     * Remove selected order field from output table 
     */
    protected void doRemoveAction() {
	if (!isValidContent()) {
	    return;
	}
	OrderField field = getSelectionField(outputTable);
	if (field == null) {
	    return;
	}
	addFiled(inputTable, field); // Add to input
	removeFiled(outputTable, field); // Remove from output
	refresh();
    }
    
    /**
     * Add all order fields to output table
     */
    protected void doAddAllAction() {
	if (!isValidContent()) {
	    return;
	}
	// Get fields from inputTable
	List<OrderField> fields = getFields(inputTable);
	if (fields == null) {
	    return;
	}
	addFileds(outputTable, fields); // Add fields to output table
	removeFileds(inputTable, fields); // Remove fields from input table
	refresh();
    }
    
    /**
     * Remove all order fields from output table
     */
    protected void doRemoveAllAction() {
	if (!isValidContent()) {
	    return;
	}
	// Get fields from outputTable
	List<OrderField> fields = getFields(outputTable);
	if (fields == null) {
	    return;
	}
	addFileds(inputTable, fields); // Add fields to input table
	removeFileds(outputTable, fields); // Remove fields from output table
	refresh();
    }
    
    /**
     * Resort: Inverse 'ASC' flag of Order field
     */
    protected void doResortAction() {
	if (!isValidContent()) {
	    return;
	}
	OrderField field = getSelectionField(outputTable);
	if (field == null) {
	    return;
	}
	field.setAsc(!field.isAsc());
	outputTable.refresh();
    }



    @Override
    protected void processResult(CallbackResult result) {
	List<OrderField> selectionFields = getFields(outputTable);
	if (selectionFields == null || selectionFields.isEmpty()) {
	    return;
	}
	result.setValue(selectionFields);
    }
}
