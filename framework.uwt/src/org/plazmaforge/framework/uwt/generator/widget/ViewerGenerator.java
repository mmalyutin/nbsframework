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

/**
 * 
 */
package org.plazmaforge.framework.uwt.generator.widget;

import java.util.List;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.generator.ScopeContext;
import org.plazmaforge.framework.uwt.generator.SourceWriter;
import org.plazmaforge.framework.uwt.generator.VariableContext;
import org.plazmaforge.framework.uwt.widget.Column;
import org.plazmaforge.framework.uwt.widget.Viewer;


/**
 * @author ohapon
 *
 */
public abstract class ViewerGenerator extends CompositeGenerator {

    protected void generatePopulateColumn(ScopeContext context, IData data, String bean, SourceWriter sw) {
	
	generateSetRSStringProperty(context, data, bean, Column.PROPERTY_TEXT, sw);
	generateSetImagePathProperty(context, data, bean, Column.PROPERTY_ICON, sw);
	
	generateSetIntegerProperty(context, data, bean, Column.PROPERTY_WIDTH, sw);
	generateSetStringProperty(context, data, bean, Column.PROPERTY_PROPERTY, sw);
	generateSetStringProperty(context, data, bean, Column.PROPERTY_DATA_TYPE, sw);
	generateSetStringProperty(context, data, bean, Column.PROPERTY_FORMAT, sw);
	generateSetHorizontalAlignProperty(context, data, bean, Column.PROPERTY_HORIZONTAL_ALIGN, "setAlign", sw);
	
	generateSetBooleanProperty(context, data, bean, Column.PROPERTY_SORTABLE, sw);
	generateSetBooleanProperty(context, data, bean, Column.PROPERTY_FILTERABLE, sw);
	generateSetBooleanProperty(context, data, bean, Column.PROPERTY_RESIZABLE, sw);
	generateSetBooleanProperty(context, data, bean, Column.PROPERTY_MOVEABLE, sw);
	generateSetBooleanProperty(context, data, bean, Column.PROPERTY_EDITABLE, sw);
	
	// TODO:
	// - CellRenderer
	// - CellEditor
	// - ValuePresenter
	// - ValueProvider
	// - LabelProvider	
	

    }
    
    protected void generatePopulateColumns(ScopeContext context, String columnClass, IData data, String bean, SourceWriter sw) {
	List<IData> columns = getChildrenOfNode(data, Viewer.PROPERTY_COLUMNS);
	if (columns == null || columns.isEmpty()) {
	    return;
	}
	
	String thisRef = (bean == null ? "" : (bean + "."));
	int columnCount = columns == null ? 0: columns.size();
	data.set("$columnCount", columnCount);
	
	VariableContext columnContext = new VariableContext();
	String columnVariable = null;
	columnClass = prepareClassName(context, columnClass);
	String columnInstance = "new " + columnClass + "()";
	
	
	//List<String> columnVariables = new ArrayList<String>();
	
	for (IData columnData: columns) {
	    
	    columnContext.reset();
	    
	    columnContext.setVariable("%column"); //TODO
	    columnContext.setForceVariable(true);
	    columnContext.setVariableClass(columnClass);
	    columnContext.setInstanceRef(columnInstance);
	    
	    columnVariable = generateInstanceVariable(context, columnContext, columnData, sw);
	    //columnVariables.add(columnVariable);
	    
	    generatePopulateColumn(context, columnData, columnVariable, sw);
	    
	    sw.println(thisRef + "addColumn(" + columnVariable + ");");
	    
	    sw.println();
	}
	
	//String columnsVariable = context.generateVariable("columns");
	//String columnsVariableClass = prepareClassName(context, "java.util.List");
	//String columnsVariableImpl = prepareClassName(context, "java.util.ArrayList");
	
	//generateSetLine(columnsVariableClass, columnsVariable, "new  "+ columnsVariableImpl + "()", sw);
	
	//sw.println();
	
	//for (String c: columnVariables) {
	//    sw.println(columnsVariable + "." + "add(" + c + ");");
	//}
	
	//sw.println(thisRef + "setColumns(" + columnsVariable + ");");
	
    }
    
    
    protected void generatePopulateItems(ScopeContext context, IData data, String bean, SourceWriter sw) {
	
	List<String> items = getItemValues(String.class, data);
	if (items == null || items.isEmpty()) {
	    return;
	}
	String itemsVariable = context.generateVariable("items");
	String itemsVariableClass = prepareClassName(context, "java.util.List");
	String itemsVariableImpl = prepareClassName(context, "java.util.ArrayList");
	
	generateSetLine(itemsVariableClass, itemsVariable, "new  "+ itemsVariableImpl + "()", sw);
	
	sw.println();
	
	for (String item: items) {
	    sw.println(itemsVariable + "." + "add(" + item + ");");
	}
	
	sw.println((bean == null ? "" : (bean + ".")) + "setItems(" + itemsVariable + ");");

    }
    
    @Override
    protected void generatePopulateBody(ScopeContext context, IData data, String bean, SourceWriter sw) {
	// do nothing
    }
    
    @Override
    protected void generateAddListener(ScopeContext context, String bean, String type, String handleString, SourceWriter sw) {
	if (eq(type, Events.Selection)) {
	    generateAddSelectionListener(context, bean, handleString, sw);
	    return;
	}
	
	super.generateAddListener(context, bean, type, handleString, sw);
    }


}
