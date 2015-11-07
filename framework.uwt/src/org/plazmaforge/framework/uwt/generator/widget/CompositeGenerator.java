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
import org.plazmaforge.framework.uwt.builder.UIBuilder;
import org.plazmaforge.framework.uwt.generator.ClassContext;
import org.plazmaforge.framework.uwt.generator.ClassSourceFactory;
import org.plazmaforge.framework.uwt.generator.GenerateException;
import org.plazmaforge.framework.uwt.generator.GeneratorContext;
import org.plazmaforge.framework.uwt.generator.IUIGenerator;
import org.plazmaforge.framework.uwt.generator.MethodContext;
import org.plazmaforge.framework.uwt.generator.ScopeContext;
import org.plazmaforge.framework.uwt.generator.SourceWriter;
import org.plazmaforge.framework.uwt.widget.Composite;

public class CompositeGenerator extends ControlGenerator implements IUIGenerator {

    @Override
    public boolean accept(String type) {
	return eq(type, UIBuilder.COMPOSITE_TYPE);
    }

    @Override
    public String generateClass(GeneratorContext context, IData data) {

	ClassSourceFactory classSourceFactory = createClassSourceFactory(context, data);
	SourceWriter classWriter = classSourceFactory.createSourceWriter(context);
	SourceWriter bodyWriter = classSourceFactory.createSourceWriter(context);
	
	ClassContext classContext = createClassContext(context, data);
	
	// Check ClassContext
	if  (classContext.getClassName() == null || classContext.getClassName().trim().isEmpty()) {
	    throw new GenerateException("ClassName must be not empty");
	}
	
	
	// Add import list to ClassContext
	addImports(classContext, data);
	
	// Add base UWT packages
	//classContext.addImport(UIGenerator.UWT_PACKAGE + ".*");
	//classContext.addImport(UIGenerator.UWT_WIDGET_PACKAGE + ".*");
	//classContext.addImport(UIGenerator.UWT_LAYOUT_PACKAGE + ".*");
	//classContext.addImport(UIGenerator.UWT_GRAPHICS_PACKAGE + ".*");
	
	// Generate body: bodyWriter
	generateConstructor(classContext, bodyWriter);
	generateCreateUI(classContext, data, bodyWriter);
	generateMethods(classContext, data, null, bodyWriter);

	// Generate class: classWriter
	generateBegin(classContext, classWriter);
	classWriter.print(bodyWriter.getSource()); // add body
	generateEnd(classContext, classWriter);
	
	String source = classWriter.getSource();
	
	return source; 
    }
    
    protected void addImports(ClassContext classContext, IData data) {
	if (data == null) {
	    return;
	}
	List<IData> imports = getChildrenOfNode(data, "imports");
	if (imports == null) {
	    return;
	}
	for (IData importData: imports) {
	    String importPath = getString(importData, "path");
	    if (isEmpty(importPath)) {
		continue;
	    }
	    classContext.addImport(importPath);
	}
    }
 
    
    protected void doGenerateCreateUI(ClassContext classContext, IData data, SourceWriter sw) {
	
	MethodContext context = new MethodContext(classContext);
	
	sw.indent();
	sw.println();
	sw.println("super.createUI();");
	sw.println();
	generatePopulate(context, data,  null, sw); // We can use "this" as variable name
	sw.outdent();

    }
    
    protected void generateMethods(ClassContext classContext, IData data, String bean, SourceWriter sw) {
	// do nothing by default
    }

    
    @Override
    public String getClassName(String baseType, String type) {
	return getDefaultClassName(baseType, type);
    }


    @Override
    public void generatePopulate(ScopeContext context, IData data, String bean, SourceWriter sw) {
	generatePopulateCommon(context, data, bean, sw);
	sw.println();
	generatePopulateBody(context, data, bean, sw);
    }
    
    protected void generatePopulateCommon(ScopeContext context, IData data, String bean, SourceWriter sw) {
	super.generatePopulate(context, data, bean, sw);
    }
    
    protected void generatePopulateBody(ScopeContext context, IData data, String bean, SourceWriter sw) {
	generateSetLayout(context, data, bean, sw);
	generateContentChildren(context, data, bean, sw);
    }
    
    protected void generateSetLayout(ScopeContext context, IData data, String bean, SourceWriter sw) {
	generateSetData(context, "%layout", data, bean, Composite.PROPERTY_LAYOUT, null, sw);
    }
    
    protected void generateContentChildren(ScopeContext context, IData data, String bean, SourceWriter sw) {
	List<IData> children = getChildrenOfNode(data, Composite.PROPERTY_CHILDREN);
	generateContentChildren(context, bean, children, sw);
    }
    
    protected void generateContentChildren(ScopeContext context, String bean, List<IData> children, SourceWriter sw) {
	 if (children == null || children.isEmpty()) {
	     return;
	 }	
	 for (IData child: children) {
	     sw.println();
	     String instanceRef = generateCreateData(context, "$#", child, sw);
	     if  (instanceRef == null) {
		 continue;
	     }
	     sw.println((bean == null ? "" : (bean + ".")) + "add(" + instanceRef + ");");
	 }
    }


}
