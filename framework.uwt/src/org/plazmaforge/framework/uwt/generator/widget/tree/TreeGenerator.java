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
package org.plazmaforge.framework.uwt.generator.widget.tree;

import java.util.List;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.generator.ScopeContext;
import org.plazmaforge.framework.uwt.generator.SourceWriter;
import org.plazmaforge.framework.uwt.generator.UIGenerator;
import org.plazmaforge.framework.uwt.generator.widget.ViewerGenerator;
import org.plazmaforge.framework.uwt.widget.tree.Tree;
import org.plazmaforge.framework.uwt.widget.tree.TreeColumn;
import org.plazmaforge.framework.uwt.widget.tree.TreeItem;
import org.plazmaforge.framework.uwt.widget.tree.TreeItemDataProvider;
import org.plazmaforge.framework.uwt.widget.tree.TreeItemPropertyProvider;

/**
 * @author ohapon
 *
 */
public class TreeGenerator extends ViewerGenerator {

    @Override
    protected String getDefaultClassName(String baseType, String type) {
	return baseType == null ? null : UIGenerator.UWT_WIDGET_PACKAGE + ".tree." + baseType;
    }

    
    @Override
    public void generatePopulate(ScopeContext context, IData data, String bean, SourceWriter sw) {
	
	generatePopulateCommon(context, data, bean, sw);
	sw.println();
	//generatePopulateColumns(context, data, bean, sw);
	generatePopulateItems(context, data, bean, sw);
    }

    @Override
    protected void generatePopulateCommon(ScopeContext context, IData data, String bean, SourceWriter sw) {
	super.generatePopulateCommon(context, data, bean, sw);
	
	generateSetBooleanProperty(context, data, bean, Tree.PROPERTY_LINES_VISIBLE, sw);
	generateSetBooleanProperty(context, data, bean, Tree.PROPERTY_HEADER_VISIBLE, sw);
	
	generateSetStringProperty(context, data, bean, Tree.PROPERTY_DISPLAY_PROPERTY, sw);
	generateSetStringProperty(context, data, bean, Tree.PROPERTY_DISPLAY_FORMAT, sw);
	
	
	generateSetImagePathProperty(context, data, bean, Tree.PROPERTY_LEAF_ICON, sw);
	generateSetImagePathProperty(context, data, bean, Tree.PROPERTY_NODE_ICON, sw);
	generateSetImagePathProperty(context, data, bean, Tree.PROPERTY_OPEN_ICON, sw);
	generateSetImagePathProperty(context, data, bean, Tree.PROPERTY_CLOSE_ICON, sw);
	
	//TODO:
	// - LabelProvider
    }
    
    
    protected void generatePopulateColumns(ScopeContext context, IData data, String bean, SourceWriter sw) {
	generatePopulateColumns(context, TreeColumn.class.getName(), data, bean, sw);
    }
    
    @Override
    protected void generatePopulateItems(ScopeContext context, IData data, String bean, SourceWriter sw) {
	
	Integer value = (Integer) data.get("$columnCount");
	int columnCount = value == null ? 0: value;
	int length = columnCount == 0 ? 1 : columnCount;
	
	Class[] types = new Class[length];
	
	for (int i = 0; i < length; i++) {
	    types[i] = String.class; // TODO Use column data type
	}
	List<TreeItem> items = getTreeItems(types, data);
	if (items == null || items.isEmpty()) {
	    return;
	}
	
	String thisRef = (bean == null ? "" : (bean + "."));
	String columnCountVariable = context.generateVariable("columnCount");
	
	
	Object propertyProvider = data.get("propertyProvider");
	if  (propertyProvider == null) {
	    
	    String providerClass = prepareClassName(context, TreeItemPropertyProvider.class.getName());
	    sw.println(thisRef + "setPropertyProvider(new " + providerClass + "());");


	    String columnClass = prepareClassName(context, TreeColumn.class.getName());
	    String columnVariable = context.generateVariable("column");
	    
	    if (columnCount > 0) {
		sw.println("int " + columnCountVariable + " = " + columnCount + ";");
		sw.println(columnClass + " " + columnVariable + " = null;");
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
	    } else {
		sw.println("if (" + thisRef + "getDisplayProperty() == null) {");
		sw.indent();
		sw.println(thisRef + "setDisplayProperty(" + providerClass + ".PROPERTY_VALUE);");
		sw.outdent();
		sw.println("}");
	    }
	}
	
	String itemsVariable = context.generateVariable("items");
	String itemsVariableClass = prepareClassName(context, "java.util.List");
	String itemsVariableImpl = prepareClassName(context, "java.util.ArrayList");
	
	String modelClass = prepareClassName(context, TreeItem.class.getName());
	
	generateSetLine(itemsVariableClass + "<" + modelClass + ">", itemsVariable, "new  "+ itemsVariableImpl + "<" + modelClass + ">()", sw);
	
	sw.println();
	
	// Reset columnCount (if columnCount = 0 then 1)
	columnCount = length;
	
	populateItems(context, itemsVariable, items, columnCount, modelClass, sw, true);
	
	sw.println();
	String dataProviderClass = prepareClassName(context, TreeItemDataProvider.class.getName());
	sw.println(thisRef + "setDataProvider(new " + dataProviderClass + "());");
	
	sw.println();
	sw.println(thisRef + "setItems(" + itemsVariable + ");");

    }
    
    private void populateItems(ScopeContext context, String parentVariable, List<TreeItem> items, int columnCount, String modelClass, SourceWriter sw, boolean root) {
	if (items == null || items.isEmpty()) {
	    return;
	}
	
	StringBuffer buf = null;
	String itemStr = null;
	
	for (TreeItem item: items) {
	    itemStr = "new " + modelClass + "()";
	    
	    if (columnCount > 0) {
		
		buf = new StringBuffer(columnCount == 1 ? ".setValue(" : ".setValues(new Object[] {");
		for (int i = 0; i < columnCount; i++) {
		    if (i > 0) {
			buf.append(", ");
		    }
		    Object v = item.getValue(i);
		    //TODO: Must use tClass for i column
		    String str = (v == null ? "null" : ("\"" + v.toString() + "\""));
		    buf.append(str);

		}
		buf.append(columnCount == 1 ? ")" : "})");
	    }
	    
	    // Generate variable
	    String itemVariable = context.generateVariable("item");
	    
	    // Initialize variable
	    sw.println(modelClass + " " + itemVariable + " = " + itemStr + ";");
	    
	    // Set value(s)
	    sw.println(itemVariable + buf.toString() + ";");
	    
	    if (root) {
		sw.println(parentVariable + "." + "add(" + itemVariable + ");");
	    } else {
		sw.println(parentVariable + "." + "addItem(" + itemVariable + ");");
	    }
	    
	    
	    if (!item.hasItems()) {
		continue;
	    }
	    
	    populateItems(context, itemVariable, item.getItems(), columnCount, modelClass, sw, false);
	}	
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
