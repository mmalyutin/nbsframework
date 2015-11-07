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

package org.plazmaforge.framework.uwt.generator.widget;

import java.util.List;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.event.EnterEvent;
import org.plazmaforge.framework.uwt.event.EnterListener;
import org.plazmaforge.framework.uwt.event.FocusEvent;
import org.plazmaforge.framework.uwt.event.FocusListener;
import org.plazmaforge.framework.uwt.event.KeyEvent;
import org.plazmaforge.framework.uwt.event.KeyListener;
import org.plazmaforge.framework.uwt.event.MouseEvent;
import org.plazmaforge.framework.uwt.event.MouseListener;
import org.plazmaforge.framework.uwt.event.SelectionEvent;
import org.plazmaforge.framework.uwt.event.SelectionListener;
import org.plazmaforge.framework.uwt.generator.AbstractGenerator;
import org.plazmaforge.framework.uwt.generator.GenerateException;
import org.plazmaforge.framework.uwt.generator.GeneratorContext;
import org.plazmaforge.framework.uwt.generator.ScopeContext;
import org.plazmaforge.framework.uwt.generator.SourceWriter;
import org.plazmaforge.framework.uwt.generator.UIGenerator;
import org.plazmaforge.framework.uwt.generator.UIGeneratorHelper;
import org.plazmaforge.framework.uwt.widget.Widget;

public class WidgetGenerator extends AbstractGenerator {

    @Override
    public String getClassName(String baseType, String type) {
	return getDefaultClassName(baseType, type);
    }

    protected String getDefaultClassName(String baseType, String type) {
	return baseType == null ? null : UIGenerator.UWT_WIDGET_PACKAGE + "." + baseType;
    }
    
    @Override
    public void generatePopulate(ScopeContext context, IData data, String bean, SourceWriter sw) {
	super.generatePopulate(context, data, bean, sw);
	
	// Populate events
	generatePopulateEvents(context, data, bean, sw);
	
    }

    @Override
    public boolean accept(String type) {
	return false;
    }

    @Override
    public String generateClass(GeneratorContext context, IData data) {
	//TODO 
	return null;
    }

    protected void generatePopulateEvents(ScopeContext context, IData data, String bean, SourceWriter sw) {
	List<IData> events = getChildrenOfNode(data, Widget.PROPERTY_EVENTS);
	generatePopulateEvents(context, bean, events, sw);
    }
    
    protected void generatePopulateEvents(ScopeContext context, String bean, List<IData> events, SourceWriter sw) {
	 if (events == null || events.isEmpty()) {
	     return;
	 }	
	 for (IData child: events) {
	     String type = getString(child, Widget.PROPERTY_TYPE);
	     if (isEmpty(type)){
		 //ERROR
		 continue;
	     }
	     String name = getString(child, Widget.PROPERTY_NAME);
	     String handler = getString(child, Widget.PROPERTY_HANDLER);
	     String handleString = null;
	     
	     if (!isEmpty(handler)) {
		 
		 // Call handler
		 handleString = handler + "();"; //TODO

	     } else {
		 
		 // Generate method: generate script
		 IData scriptNode = getNode(child, Widget.PROPERTY_SCRIPT);
		 if (scriptNode == null) {
		     //WARN: No script
		     continue;
		     
		 }
		 String language = getString(scriptNode, "language");
		 String script = null;
		 
		 if (!isJavaLanguage(language)) {
		     //ERROR
		     //continue;
		     script = "/// Language '" + language + "' do not support";
		 } else {
		     script = (String) scriptNode.get("$value");
		 }
		 
		 if (isEmpty(script)) {
		     //WARN: No script 
		     continue;
		 }
		 
		 
		 //handleString = name + "();"; // TODO
		 handleString = script; // TODO
	     }
	     
	     generateAddListener(context, bean, type, handleString, sw);
	 }
   }
    
    protected boolean isJavaLanguage(String language) {
	return isEmpty(language) || "java".equalsIgnoreCase(language);
    }
    
    protected void generateAddListener(ScopeContext context, String bean, String type, String handleString, SourceWriter sw) {
	// do nothing by default
    }
    
    ////


    protected void generateAddKeyDownListener(ScopeContext context, String bean, String handleString, SourceWriter sw) {
	generateAddEventListener(context, bean, KeyListener.class, KeyEvent.class, null, "keyDown, -keyUp", handleString, sw);
    }
    
    protected void generateAddKeyUpListener(ScopeContext context, String bean, String handleString, SourceWriter sw) {
	generateAddEventListener(context, bean, KeyListener.class, KeyEvent.class, null, "-keyDown, keyUp", handleString, sw);
    }
    
    
    ////

    protected void generateAddMouseClickListener(ScopeContext context, String bean, String handleString, SourceWriter sw) {
	generateAddEventListener(context, bean, MouseListener.class, MouseEvent.class, null, "mouseClick, -mouseDoubleClick, -mouseDown, -mouseUp", handleString, sw);
    }
    
    protected void generateAddMouseDoubleClickListener(ScopeContext context, String bean, String handleString, SourceWriter sw) {
	generateAddEventListener(context, bean, MouseListener.class, MouseEvent.class, null, "-mouseClick, mouseDoubleClick, -mouseDown, -mouseUp", handleString, sw);
    }
    

    protected void generateAddMouseDownListener(ScopeContext context, String bean, String handleString, SourceWriter sw) {
	generateAddEventListener(context, bean, MouseListener.class, MouseEvent.class, null, "-mouseClick, -mouseDoubleClick, mouseDown, -mouseUp", handleString, sw);
    }
    
    protected void generateAddMouseUpListener(ScopeContext context, String bean, String handleString, SourceWriter sw) {
	generateAddEventListener(context, bean, MouseListener.class, MouseEvent.class, null, "-mouseClick, -mouseDoubleClick, -mouseDown, mouseUp", handleString, sw);
    }
    
    ////

    protected void generateAddFocusInListener(ScopeContext context, String bean, String handleString, SourceWriter sw) {
	generateAddEventListener(context, bean, FocusListener.class, FocusEvent.class, null, "focusIn, -focusOut", handleString, sw);
    }
    
    protected void generateAddFocusOutListener(ScopeContext context, String bean, String handleString, SourceWriter sw) {
	generateAddEventListener(context, bean, FocusListener.class, FocusEvent.class, null, "-focusIn, focusOut", handleString, sw);
    }    
    
    ////
    

    protected void generateAddSelectionListener(ScopeContext context, String bean, String handleString, SourceWriter sw) {
	generateAddEventListener(context, bean, SelectionListener.class, SelectionEvent.class, null, "select", handleString, sw);
    }
    
    ////

    protected void generateAddEnterListener(ScopeContext context, String bean, String handleString, SourceWriter sw) {
	generateAddEventListener(context, bean, EnterListener.class, EnterEvent.class, null, "enter", handleString, sw);
    }
    
    ////

    protected void generateAddEventListener(ScopeContext context, String bean, Class<?> listenerClass, Class<?> eventClass, String addMethod, String listenerMethod, String handleString, SourceWriter sw) {
	generateAddEventListener(context, bean, prepareClassName(context, listenerClass), prepareClassName(context, eventClass), addMethod, listenerMethod, handleString, sw);
    }
    
    protected void generateAddEventListener(ScopeContext context, String bean, String listenerClass, String eventClass, String addMethod, String listenerMethod, String handleString, SourceWriter sw) {
	
	if (listenerClass == null) {
	    throw new GenerateException("Listener class must be not null");
	}
	if (eventClass == null) {
	    throw new GenerateException("Event class must be not null");
	}
	if (listenerMethod == null) {
	    throw new GenerateException("Listener method must be not null");
	}

	// KeyListener(keyDown): '-keyUp, keyDown'  MouseListener(mouseDown): '-mouseClick, -mouseDoubleClick, mouseDown, -mouseUp'
	// Use '-' to declare empty method of the listener interface
	String[] listenerMethods = listenerMethod.split(","); 
	if (listenerMethods.length > 0) {
	    for (int i = 0; i < listenerMethods.length; i++) {
		listenerMethods[i] = listenerMethods[i].trim(); 
	    }
	}
		
	if (addMethod == null) {
	    addMethod = "add" +  UIGeneratorHelper.getSimpleClassName(listenerClass);
	}
	sw.println((bean == null ? "" : (bean + ".")) + addMethod + "(new " + listenerClass + "() {");
	sw.indent();
	
	for (String method : listenerMethods) {
	    boolean emptyMethod = method.startsWith("-");
	    if (emptyMethod) {
		method = method.length() > 1 ? method.substring(1) : null;
	    }
	    
	    if (method == null) {
		//ERROR
		continue;
	    }
	    
	    sw.println("public void " + method + "(" + eventClass + " event) {");
	    if (!emptyMethod && !isEmpty(handleString)) {
		sw.indent();
		sw.println(handleString);
		sw.outdent();

	    }
	    sw.println("}");
	}	
	
	sw.outdent();
	sw.println("});");
    }
    

}
