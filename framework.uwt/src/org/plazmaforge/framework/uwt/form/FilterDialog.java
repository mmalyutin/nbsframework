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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.core.criteria.Filter;
import org.plazmaforge.framework.core.criteria.Operation;
import org.plazmaforge.framework.core.data.PropertyProvider;
import org.plazmaforge.framework.core.type.TypeUtils;
import org.plazmaforge.framework.uwt.dialog.Dialog;
import org.plazmaforge.framework.uwt.layout.GridLayout;
import org.plazmaforge.framework.uwt.widget.CallbackResult;
import org.plazmaforge.framework.uwt.widget.ComboBox;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Label;
import org.plazmaforge.framework.uwt.widget.TextField;

/**
 * Filter dialog
 * 
 * @author ohapon
 *
 */
public class FilterDialog extends Dialog {

    
    /**
     * Input display field
     */
    private List<DisplayField> displayFields;
    
    /**
     * Filter controls
     */
    private List<FilterControl> controls;
    
    
    
    private Map<String, String> operationMap;
    
    /**
     * Operation by Type
     * Key: Type, Value: List of OpearationItems
     */
    private Map<String, List<OperationItem>> opearationTypeMap; 
    
    
    /**
     * PropertyProvider for OperationItem
     */
    private PropertyProvider<OperationItem> operationProvider;
    
    
    @Override
    protected void configure() {
	super.configure();
	setTitle("Filter Dialog");
	setPack(true);
	
	loadOperations();
    }
    
    protected void loadOperations() {
	operationMap = new HashMap<String, String>();
	operationMap.put(Operation.EQ, "eq");
	operationMap.put(Operation.NE, "ne");
	operationMap.put(Operation.LIKE, "like");
	operationMap.put(Operation.NOTLIKE, "notlike");
	operationMap.put(Operation.LT, "lt");
	operationMap.put(Operation.LE, "le");
	operationMap.put(Operation.GT, "gt");
	operationMap.put(Operation.GE, "ge");
	operationMap.put(Operation.IN, "in");
	//operationMap.put(Operation.NIN, "nin");
	operationMap.put(Operation.ISNULL, "isnull");
	operationMap.put(Operation.ISNOTNULL, "isnotnull");
	
	opearationTypeMap = new HashMap<String, List<OperationItem>>(); 
	
	operationProvider = new OperationProvider();
    }
    
    /**
     * Get operations list by data type
     * @param type
     * @return
     */
    protected List<OperationItem> getOperationByType(String type) {
	if (type == null) {
	    type = "Unknown";
	}
	List<OperationItem> operations = opearationTypeMap.get(type);
	if (operations != null) {
	    return operations;
	}
	
	if (TypeUtils.isLikeStringType(type)) {
	    operations = getStringOperations();
	} else if (TypeUtils.isLikeNumberType(type)) { 
	    operations = getNumberOperations();
	} else if (TypeUtils.isLikeCalendarType(type)) { 
	    operations = getCalendarOperations();
	} else {
	    operations = getDefaultOperations();
	}
	opearationTypeMap.put(type, operations);
	return operations;
    }
    
    
    protected List<OperationItem> getDefaultOperations() {
	List<OperationItem> list = new ArrayList<OperationItem>();
	list.add(new OperationItem(Operation.EQ));
	list.add(new OperationItem(Operation.NE));
	list.add(new OperationItem(Operation.LT));
	list.add(new OperationItem(Operation.GT));
	list.add(new OperationItem(Operation.LE));
	list.add(new OperationItem(Operation.GE));
	return list;
    }

    protected List<OperationItem> getStringOperations() {
	List<OperationItem> list = new ArrayList<OperationItem>();
	list.add(new OperationItem(Operation.EQ));
	list.add(new OperationItem(Operation.NE));
	list.add(new OperationItem(Operation.LIKE));
	list.add(new OperationItem(Operation.NOTLIKE));
	return list;
    }

    protected List<OperationItem> getNumberOperations() {
	List<OperationItem> list = new ArrayList<OperationItem>();
	list.add(new OperationItem(Operation.EQ));
	list.add(new OperationItem(Operation.NE));
	list.add(new OperationItem(Operation.LT));
	list.add(new OperationItem(Operation.GT));
	list.add(new OperationItem(Operation.LE));
	list.add(new OperationItem(Operation.GE));
	return list;
    }

    // Date, Time, DateTime
    protected List<OperationItem> getCalendarOperations() {
	List<OperationItem> list = new ArrayList<OperationItem>();
	list.add(new OperationItem(Operation.EQ));
	list.add(new OperationItem(Operation.NE));
	list.add(new OperationItem(Operation.LT));
	list.add(new OperationItem(Operation.GT));
	list.add(new OperationItem(Operation.LE));
	list.add(new OperationItem(Operation.GE));
	return list;
    }
    
    
    
    @Override
    protected void buildContent(Composite parent) {
	GridLayout layout = new GridLayout(3); 
	parent.setLayout(layout);
	List<DisplayField> displayFields = getDisplayFields();
	controls = new ArrayList<FilterDialog.FilterControl>();
	for (DisplayField displayField : displayFields) {
	    FilterControl control = createFilterControl(displayField);
	    controls.add(control);
	    
	    parent.add(control.label);
	    parent.add(control.operationField);
	    parent.add(control.valueField);
	}
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

    
    protected FilterControl createFilterControl(DisplayField displayField) {
	FilterControl filterControl = new FilterControl();

	// Create FilterField by DisplayField
	filterControl.filter = new FilterField();
	filterControl.filter.setProperty(displayField.getProperty());
	filterControl.filter.setLabel(displayField.getLabel());
	filterControl.filter.setType(displayField.getType());
	filterControl.filter.setValuePresenter(displayField.getValuePresenter());

	filterControl.label = new Label(displayField.getLabel());
	filterControl.operationField = new ComboBox<OperationItem>();
	//filterControl.operationField.setDisplayProperty("operation"); // TODO !!!
	filterControl.operationField.setPropertyProvider(operationProvider);
	filterControl.operationField.setItems(getOperationByType(displayField.getType()));
	filterControl.operationField.setSelectionFirst();
	
	filterControl.valueField = new TextField();
	return filterControl;
    }
    
    
    class FilterControl {
	
	FilterField filter;
	
	Label label;
	
	ComboBox<OperationItem> operationField;
	
	TextField valueField;
	
    }
    
    class OperationItem {
	
	String operation;
	
	String displayOperation;

	OperationItem() {
	}

	OperationItem(String operation) {
	    this.operation = operation;
	}

	public String toString() {
	    return displayOperation == null ? operation : displayOperation;
	}
	
    }
    
    class OperationProvider implements PropertyProvider<OperationItem> {

	@Override
	public Object getValue(OperationItem element, String property) {
	    return element == null ? null : element.toString();
	}

	@Override
	public void setValue(OperationItem element, String property, Object value) {
	}
	
    }
    
    @Override
    protected void processResult(CallbackResult result) {
	List<FilterField> selectionFields = new ArrayList<FilterField>();
	for (FilterControl control : controls) {
	    String stringValue = (String) control.valueField.getValue();
	    if (stringValue != null) {
		stringValue = stringValue.trim();
		if (stringValue.isEmpty()) {
		    stringValue = null;
		}
	    }
	    if (stringValue == null) {
		continue;
	    }
	    
	    // Set string value
	    control.filter.setStringValue(stringValue);
	    
	    // Parse string and set value
	    control.filter.loadValue();
	    
	    OperationItem item = (OperationItem) control.operationField.getValue();
	    control.filter.setOperation(item.operation);
	    
	    selectionFields.add(control.filter);
	}
	if (!selectionFields.isEmpty()) {
	    result.setValue(selectionFields);
	}
    }
    
    
}
