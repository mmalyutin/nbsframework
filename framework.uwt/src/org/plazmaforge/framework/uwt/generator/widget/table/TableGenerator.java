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
package org.plazmaforge.framework.uwt.generator.widget.table;

import java.util.List;

import org.plazmaforge.framework.core.data.ArrayPropertyProvider;
import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.generator.ScopeContext;
import org.plazmaforge.framework.uwt.generator.SourceWriter;
import org.plazmaforge.framework.uwt.generator.UIGenerator;
import org.plazmaforge.framework.uwt.generator.widget.ViewerGenerator;
import org.plazmaforge.framework.uwt.widget.table.Table;
import org.plazmaforge.framework.uwt.widget.table.TableColumn;

/**
 * @author ohapon
 *
 */
public class TableGenerator extends ViewerGenerator {
    
    @Override
    protected String getDefaultClassName(String baseType, String type) {
	return baseType == null ? null : UIGenerator.UWT_WIDGET_PACKAGE + ".table." + baseType;
    }

    @Override
    public void generatePopulate(ScopeContext context, IData data, String bean, SourceWriter sw) {
	
	generatePopulateCommon(context, data, bean, sw);
	sw.println();
	generatePopulateColumns(context, data, bean, sw);
	generatePopulateItems(context, data, bean, sw);
    }

    @Override
    protected void generatePopulateCommon(ScopeContext context, IData data, String bean, SourceWriter sw) {
	super.generatePopulateCommon(context, data, bean, sw);
	
	generateSetBooleanProperty(context, data, bean, Table.PROPERTY_LINES_VISIBLE, sw);
	generateSetBooleanProperty(context, data, bean, Table.PROPERTY_HEADER_VISIBLE, sw);
	generateSetBooleanProperty(context, data, bean, Table.PROPERTY_SORTABLE, sw);
	
	generateSetIntegerProperty(context, data, bean, Table.PROPERTY_SELECTION_INDEX, sw);
    }
    
    protected void generatePopulateColumns(ScopeContext context, IData data, String bean, SourceWriter sw) {
	generatePopulateColumns(context, TableColumn.class.getName(), data, bean, sw);
    }
    
    
    
    
    @Override
    protected void generatePopulateItems(ScopeContext context, IData data, String bean, SourceWriter sw) {
	
	Integer value = (Integer) data.get("$columnCount");
	int columnCount = value == null ? 0: value;
	
	Class[] types = new Class[columnCount];
	
	for (int i = 0; i < columnCount; i++) {
	    types[i] = String.class; // TODO Use column data type
	}
	List<Object[]> items = getItemRecords(types, data);
	if (items == null || items.isEmpty()) {
	    return;
	}
	
	String thisRef = (bean == null ? "" : (bean + "."));
	
	Object propertyProvider = data.get("propertyProvider");
	if  (propertyProvider == null) {

	    String providerClass = prepareClassName(context, ArrayPropertyProvider.class.getName());
	    sw.println(thisRef + "setPropertyProvider(new " + providerClass + "());");

	    String columnClass = prepareClassName(context, TableColumn.class.getName());
	    String columnVariable = context.generateVariable("column");
	    
	    sw.println(columnClass + " " + columnVariable + " = null;");
	    String columnCountVariable = context.generateVariable("columnCount");
	    sw.println("int " + columnCountVariable + " = " + columnCount + ";");
	    
	    sw.println("for (int i = 0; i < " + columnCountVariable + "; i++) {");
	    sw.indent();
	    sw.println(columnVariable + " = " + thisRef + "getColumn(i);");
	    sw.println("if (" + columnVariable + ".getProperty() == null) {");
	    sw.indent();
	    sw.println(columnVariable + ".setProperty(\"\" + i);");
	    sw.outdent();
	    sw.println("}");
	    sw.outdent();
	    sw.println("}");
	    
	    
	}

	
	String itemsVariable = context.generateVariable("items");
	String itemsVariableClass = prepareClassName(context, "java.util.List");
	String itemsVariableImpl = prepareClassName(context, "java.util.ArrayList");
	
	generateSetLine(itemsVariableClass + "<Object[]>", itemsVariable, "new  "+ itemsVariableImpl + "<Object[]>()", sw);
	
	sw.println();
	
	StringBuffer buf = null;
	String itemStr = null;
	for (Object[] item: items) {
	    if (columnCount == 0) {
		itemStr = "new Object[0]";
	    } else {
		buf = new StringBuffer("new Object[] {");
		for (int i = 0; i < columnCount; i++) {
		    if (i > 0) {
			buf.append(", ");
		    }
		    Object v = item[i];
		    //TODO: Must use tClass for i column
		    String str = (v == null ? "null" : ("\"" + v.toString() + "\""));
		    buf.append(str);

		}
		buf.append("}");
		itemStr = buf.toString();
	    }
	    sw.println(itemsVariable + "." + "add(" + itemStr + ");");
	}
	
	sw.println();
	sw.println(thisRef + "setItems(" + itemsVariable + ");");

    }

    @Override
    protected void generateAddListener(ScopeContext context, String bean, String type, String handleString, SourceWriter sw) {
	if (eq(type, Events.Enter)) {
	    generateAddEnterListener(context, bean, handleString, sw);
	    return;
	}
	
	super.generateAddListener(context, bean, type, handleString, sw);
    }

}
