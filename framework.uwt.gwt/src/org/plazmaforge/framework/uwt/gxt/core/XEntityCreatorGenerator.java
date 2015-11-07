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
package org.plazmaforge.framework.uwt.gxt.core;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.core.data.ModelMarker;

import com.extjs.gxt.ui.client.data.ModelData;
import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

/**
 * @author ohapon
 *
 */
public class XEntityCreatorGenerator extends Generator {

    protected TypeOracle oracle;
    
    /**
     * Bean marker by interface
     */
    protected JClassType beanMarkerType;
    
    
    /**
     * GXT ModelData type
     */
    protected JClassType modelDataType;
    
    /**
     * List of bean packages
     */
    protected String[] beanPackages;
    
    /**
     * List of bean super classes
     */
    protected String[] beanClasses;

    
    protected JClassType[] beanTypes;
    
    protected List<JClassType> beans;

    
    
    /**
     * Return marker class (interface)
     * Override to use other marker
     * @return
     */
    protected Class<?> getBeanMarkerClass() {
	return ModelMarker.class;
    }

    /**
     * Return GXT ModelData class
     * Override to use other class
     * @return
     */
    protected Class<?> getModelDataClass() {
	return ModelData.class;
    }
    
   
    @Override
    public String generate(TreeLogger logger, GeneratorContext context,  String typeName) throws UnableToCompleteException {
	
	oracle = context.getTypeOracle();
	      
	      // Bean marker interface
	      beanMarkerType = oracle.findType(getBeanMarkerClass().getName());
	      
	      // GXT ModelData
	      modelDataType = oracle.findType(getModelDataClass().getName());

	      // TODO: STUB
	      beanPackages = new String[] {
		      "org.plazmaforge.framework.config.object",
		      "org.plazmaforge.bs.base.shared.entities",
		      "org.plazmaforge.bs.organization.shared.entities",
		      "org.plazmaforge.bs.partner.shared.entities",
		      "org.plazmaforge.bs.contact.shared.entities",
		      "org.plazmaforge.bs.document.shared.entities",
		      "org.plazmaforge.bs.project.shared.entities"
		      };
	      beanClasses = new String[] {
		      "org.plazmaforge.framework.config.object.ObjectConfig",
		      "java.io.Serializable"
		      };
	      
	      if (beanClasses != null) {
		  beanTypes = new JClassType[beanClasses.length];
		  for (int i = 0; i < beanClasses.length; i++ ) {
		      beanTypes[i] = oracle.findType(beanClasses[i]);
		  }
	      }
	      
	try {
	    // final all beans and bean markers
	    beans = new ArrayList<JClassType>();
	    JClassType[] types = oracle.getTypes();
	    for (JClassType type : types) {

		if (isBeanMarker(type)) {
		    beans.add(type);
		}
	    }

	    final String genPackageName = XEntityCreator.class.getPackage().getName();
	    final String genClassName = "XEntityCreatorImpl";

	    ClassSourceFileComposerFactory composer = new ClassSourceFileComposerFactory(genPackageName, genClassName);

	    composer.setSuperclass(XEntityCreator.class.getCanonicalName());
	    // composer.addImport(Map.class.getName());
	    // composer.addImport(LinkedHashMap.class.getName());

	    PrintWriter pw = context.tryCreate(logger, genPackageName, genClassName);

	    if (pw != null) {
		SourceWriter sw = composer.createSourceWriter(context, pw);

		// Generate createEntity method
		sw.indent();
		sw.println();
		sw.println("public Object createEntity(String n) {");
		sw.indent();
		sw.println("if (n == null) {");
		sw.indentln("return null;");
		sw.println("}");

		for (int i = 0; i < beans.size(); i++) {
		    JClassType bean = beans.get(i);
		    String name = bean.getQualifiedSourceName();
		    if (i > 0) {
			sw.print(" else ");
		    }
		    sw.println("if (" + name + ".class.getName().equals(n)) {");
		    sw.indentln("return new " + name + "();");
		    sw.print("}");
		}
		sw.println();
		sw.outdent();
		sw.println("return null;");
		sw.outdent();
		sw.println("}");

		sw.commit(logger);
	    }

	    return composer.getCreatedClassName();

	} catch (Exception e) {
	    logger.log(TreeLogger.ERROR, "Class " + typeName + " not found.", e);
	    throw new UnableToCompleteException();
	}

    }

    protected boolean isBeanMarker(JClassType type) {
	if (type.equals(beanMarkerType) || type.isAssignableTo(modelDataType)) {
	    // Return false because type is marker interface
	    // or type extends GXT ModelData
	    return false;
	}
	// Return true if type implements marker interface
	return type.isAssignableTo(beanMarkerType) || isBean(type);
	
    }
    
    protected boolean isBean(JClassType type) {
	if (beanPackages == null || beanTypes == null || !canInstance(type)) {
	    return false;
	}
	String typePackage = type.getPackage().getName();
	for (String beanPackage : beanPackages) {
	    if (!beanPackage.equals(typePackage)) {
		continue;
	    }
	    
	    for (JClassType beanType : beanTypes) {
		if (beanType == null) {
		    continue;
		}
		if (type.isAssignableTo(beanType)) {
		    //System.out.println("BEAN-TYPE=" + type.getPackage().getName() + "." + type.getName());
		    return true; 
		}
	    }
	}
	return false;
    }    
    protected boolean canInstance(JClassType type) {
	if (type == null) {
	    return false;
	}
	return !type.isAbstract() && type.isInterface() == null;
    }


}
