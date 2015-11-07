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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.plazmaforge.framework.uwt.UWT;
import org.plazmaforge.framework.uwt.form.IForm;

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
public class XFormCreatorGenerator extends Generator {

    
    protected TypeOracle oracle;

    /**
     * IForm type
     */
    protected JClassType fromType;

    /**
     * All forms
     */
    protected List<JClassType> forms;

    /**
     * All forms to use
     */
    protected List<JClassType> useForms;

    /**
     * Only base forms (non generated)
     */
    protected List<JClassType> baseForms;

    /**
     * Only generated forms
     */
    protected List<JClassType> genForms;

    /**
     * Form map <BaseForm, GenForm>
     */
    protected Map<String, String> formMap;

    @Override
    public String generate(TreeLogger logger, GeneratorContext context,
	    String typeName) throws UnableToCompleteException {
	oracle = context.getTypeOracle();

	// Find form interface (IForm)
	fromType = oracle.findType(IForm.class.getName());

	try {
	    // Find all forms
	    forms = new ArrayList<JClassType>();
	    JClassType[] types = oracle.getTypes();
	    for (JClassType type : types) {
		if (isForm(type)) {
		    forms.add(type);
		}
	    }

	    baseForms = new ArrayList<JClassType>();
	    genForms = new ArrayList<JClassType>();
	    useForms = new ArrayList<JClassType>();

	    // Separate forms (base/generated)
	    for (JClassType form : forms) {
		//System.out.println("FORM-TYPE=" + form.getPackage().getName() + "." + form.getName());
		String formName = form.getQualifiedSourceName();
		if (!formName.endsWith(UWT.GEN_SUFFIX)) {
		    // Base form
		    baseForms.add(form);
		} else {
		    // Generated form
		    genForms.add(form);
		    
		    if (canInstance(form)) {
			useForms.add(form);
			continue;
		    }
		    
		}
	    }

	    formMap = new LinkedHashMap<String, String>();

	    // Navigate base forms and find GEN_SUFFIX form candidate
	    for (JClassType baseForm : baseForms) {
		String baseFormName = baseForm.getQualifiedSourceName();
		String genFormName = baseFormName + UWT.GEN_SUFFIX;
		boolean find = false;
		for (JClassType genForm : genForms) {
		    if (genFormName.equals(genForm.getQualifiedSourceName())) {
			find = true;
			break;
		    }
		}
		if (find) {
		    formMap.put(baseFormName, genFormName);
		} else {
		    if (canInstance(baseForm)) {
			useForms.add(baseForm);
			continue;
		    }
		}
	    }
	    
	    // Navigate gen forms and find base form (without GEN_SUFFIX) candidate
	    for (JClassType genForm : genForms) {
		String genFormName = genForm.getQualifiedSourceName();
		String baseFormName = genFormName.substring(0, genFormName.length() - UWT.GEN_SUFFIX.length());
		if (formMap.containsKey(baseFormName)) {
		    continue;
		}
		formMap.put(baseFormName, genFormName);
	    }
	    

	    final String genPackageName = IForm.class.getPackage().getName();
	    final String genClassName = "XFormCreatorImpl";

	    ClassSourceFileComposerFactory composer = new ClassSourceFileComposerFactory(
		    genPackageName, genClassName);

	    composer.setSuperclass(XFormCreator.class.getCanonicalName());
	    composer.addImport(IForm.class.getName());
	    composer.addImport(Map.class.getName());
	    composer.addImport(LinkedHashMap.class.getName());

	    PrintWriter pw = context.tryCreate(logger, genPackageName, genClassName);

	    if (pw != null) {
		SourceWriter sw = composer.createSourceWriter(context, pw);

		StringBuffer sb = new StringBuffer();

		// Generate getName method to buffer
		sb.append("private String getName(String n) {\n");
		sb.append("    if (n == null) {\n");
		sb.append("        return null;\n");
		sb.append("    }\n");
		sb.append("    if (m == null) {\n");
		sb.append("        m = new LinkedHashMap<String, String>();\n");

		// Forms: populate map
		Set<String> formNames = formMap.keySet();
		for (String formName : formNames) {
		    sb.append("    m.put(\"" + formName + "\", \"" + formMap.get(formName) + "\");\n");
		}

		sb.append("    }\n");
		sb.append("    String s = m.get(n);\n");
		sb.append("    return s == null ? n: s;\n");
		sb.append("}\n");

		

		// Generate createFrom method 
		sw.indent();
		sw.println("private Map<String, String> m;");
		sw.println();
		sw.println("public IForm<?> createForm(String n) {");
		sw.indent();
		sw.println("if (n == null) {");
		sw.indentln("return null;");
		sw.println("}");

		sw.println("n = getName(n);");

		for (int i = 0; i < useForms.size(); i++) {
		    JClassType form = useForms.get(i);
		    //System.out.println("USE-FORM-TYPE=" + form.getPackage().getName() + "." + form.getName());
		    String name = form.getQualifiedSourceName();
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

		sw.println(sb.toString());

		sw.commit(logger);
	    }

	    return composer.getCreatedClassName();

	} catch (Exception e) {
	    logger.log(TreeLogger.ERROR, "Class " + typeName + " not found.", e);
	    throw new UnableToCompleteException();
	}

    }

    protected boolean isForm(JClassType type) {
	
	if (type.equals(fromType)) {
	    // Return false because type is form interface
	    return false;
	}
	// Return true if type implements form interface
	return type.isAssignableTo(fromType);
    }
    
    protected boolean canInstance(JClassType type) {
	if (type == null) {
	    return false;
	}
	return !type.isAbstract() && type.isInterface() == null;
    }
}
