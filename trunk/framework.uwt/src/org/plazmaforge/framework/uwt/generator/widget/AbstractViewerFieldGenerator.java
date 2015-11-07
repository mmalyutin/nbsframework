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

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.builder.UIBuilder;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.generator.ScopeContext;
import org.plazmaforge.framework.uwt.generator.SourceWriter;
import org.plazmaforge.framework.uwt.generator.VariableScope;
import org.plazmaforge.framework.uwt.widget.CheckBox;
import org.plazmaforge.framework.uwt.widget.Widget;



/**
 * @author ohapon
 *
 */
public abstract class AbstractViewerFieldGenerator extends AbstractFieldGenerator {

  
    @Override
    public void generatePopulate(ScopeContext context, IData data, String bean, SourceWriter sw) {
	super.generatePopulate(context, data, bean, sw);
	generateSetItems(context, data, bean, sw);
    }
    
    protected void generateSetValue(ScopeContext context, IData data, String bean, SourceWriter sw) {
	String dataType = getString(data, Widget.PROPERTY_DATA_TYPE);
	if (dataType == null) {
	    dataType = "String";
	}
	generateSetValueProperty(dataType, context, data, bean, CheckBox.PROPERTY_VALUE, sw);
    }
    
    
    protected void generateSetItems(ScopeContext context, IData data, String bean, SourceWriter sw) {
	
 	// TODO Only for string
 	List<String> items = getItems(String.class, data);
 	if (items == null || items.isEmpty()) {
 	    return;
 	}
 	String listInterface = "java.util.List";
 	String listClass = "java.util.ArrayList";
 	listInterface = prepareClassName(context, listInterface);
 	listClass = prepareClassName(context, listClass);
 	
 	String variable = context.generateVariable(VariableScope.METHOD, "items");
 	sw.println(listInterface + " " + variable + " = new " + listClass + "();");
 	
 	String instanceRef = null;
 	for (String item: items) {
 	    instanceRef = item;
 	    sw.println(variable + ".add(\"" + instanceRef + "\");"); // TODO: Only for string
 	}
 	
 	sw.println((bean == null ? "" : (bean + ".")) + "setItems(" + variable + ");");
 	 
     }
         
    protected List<IData> getDataItems(IData data) {
	return getChildrenOfNode(data, "items");
    }

    protected <T> List<T> getItems(Class<T> tClass, IData data) {
	List<IData> items = getDataItems(data);
	if (items == null) {
	    return null;
	}
	List<T> list = new ArrayList<T>();
	for (IData item : items) {
	    T object = getItem(tClass, item);
	    list.add(object);
	}
	return list;
    }

    protected <T> T getItem(Class<T> tClass, IData item) {
	String s = (String) item.get(UIBuilder.SYS_PROPERTY_VALUE);
	return (T) s;
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
