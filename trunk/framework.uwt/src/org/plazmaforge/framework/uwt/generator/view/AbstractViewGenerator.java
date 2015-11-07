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
package org.plazmaforge.framework.uwt.generator.view;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.dialog.Dialog;
import org.plazmaforge.framework.uwt.generator.ClassContext;
import org.plazmaforge.framework.uwt.generator.MethodContext;
import org.plazmaforge.framework.uwt.generator.ScopeContext;
import org.plazmaforge.framework.uwt.generator.SourceWriter;
import org.plazmaforge.framework.uwt.generator.UIGenerator;
import org.plazmaforge.framework.uwt.generator.widget.CompositeGenerator;
import org.plazmaforge.framework.uwt.view.View;

/**
 * @author ohapon
 *
 */
public abstract class AbstractViewGenerator extends CompositeGenerator {

    @Override
    public void generatePopulateCommon(ScopeContext context, IData data, String bean, SourceWriter sw) {
	super.generatePopulateCommon(context, data, bean, sw);
	
	generateSetRSStringProperty(context, data, bean, View.PROPERTY_TITLE, sw);
    }
    
    @Override
    protected String getDefaultClassName(String baseType, String type) {
	if (type != null) {
	    return UIGenerator.UWT_VIEW_PACKAGE + "." + type;
	}
	return baseType == null ? null : UIGenerator.UWT_VIEW_PACKAGE + "." + baseType;
    }

    
    @Override
    protected void generatePopulateBody(ScopeContext context, IData data, String bean, SourceWriter sw) {
	//TODO
    }
    
    protected void doGenerateCreateUI(ClassContext classContext, IData data,  SourceWriter sw) {
	MethodContext context = new MethodContext(classContext);
	sw.indent();
	sw.println();
	sw.println("super.createUI();");
	sw.println();
	generatePopulateCommon(context, data, null, sw); // We can use "this" as variable name
	sw.outdent();
    }
    
    @Override
    protected void generateMethods(ClassContext classContext, IData data, String bean, SourceWriter sw) {
	generateCreateContent(classContext, data, bean, sw);
    }
    
    protected void generateCreateContent(ClassContext classContext, IData data, String bean, SourceWriter sw) {
	
	IData contentNode = (IData) getValue(data, Dialog.PROPERTY_CONTENT);
	if (contentNode == null) {
	    return;
	}

	MethodContext context = new MethodContext(classContext);
	
	sw.indent();
	sw.println();
	sw.print("@Override");
	sw.println();
	sw.print("public Composite createContent() {");
	
	sw.println();
	sw.indent();
	sw.println();
	
	sw.println("Composite content = super.createContent();");
	
	generateSetLayout(context, contentNode, "content", sw);
	generateContentChildren(context, contentNode, "content", sw);
	
	sw.println();
	sw.println("return content;");
	sw.outdent();
	
	sw.println();
	sw.print("}");
	sw.outdent();
	
	sw.println();
    }    
}
