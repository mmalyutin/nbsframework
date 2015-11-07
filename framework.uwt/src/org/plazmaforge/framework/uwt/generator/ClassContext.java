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

package org.plazmaforge.framework.uwt.generator;

import java.util.LinkedHashSet;
import java.util.Set;

import org.plazmaforge.framework.uwt.generator.AbstractGenerator.ImportMode;

public class ClassContext extends ScopeContext {

    private String classComment;

    private String packageName;
    
    private String superClassName;
    
    private String className;

    private Set<String> imports = new LinkedHashSet<String>();

    private Set<String> interfaces = new LinkedHashSet<String>();

    private boolean autoImport = true;
    
    private ImportMode importMode = ImportMode.CLASS;
    
    
    public void addInterface(String interfaceName) {
 	interfaces.add(interfaceName);
     }

     public String[] getInterfaces() {
 	return interfaces.toArray(new String[interfaces.size()]);
     }

     public void addImport(String importName) {
 	imports.add(importName);
     }
     
     public String[] getImports() {
    	return imports.toArray(new String[imports.size()]);
     }     



     public String getClassComment() {
         return classComment;
     }

     public void setClassComment(String classComment) {
         this.classComment = classComment;
     }

     public String getPackageName() {
         return packageName;
     }

     public void setPackageName(String packageName) {
         this.packageName = packageName;
     }

     public String getSuperClassName() {
         return superClassName;
     }

     public void setSuperClassName(String superClassName) {
         this.superClassName = superClassName;
     }

     public String getClassName() {
         return className;
     }

     public void setClassName(String className) {
         this.className = className;
     }

     public String getSimpleClassName() {
	 return UIGeneratorHelper.getSimpleClassName(className);
     }

    @Override
    public ClassContext getClassContext() {
	return this;
    }

    public boolean isAutoImport() {
        return autoImport;
    }

    public void setAutoImport(boolean autoImport) {
        this.autoImport = autoImport;
    }

    public ImportMode getImportMode() {
        return importMode;
    }

    public void setImportMode(ImportMode importMode) {
        this.importMode = importMode;
    }

    
}
