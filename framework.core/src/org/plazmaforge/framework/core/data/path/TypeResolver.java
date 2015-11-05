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
package org.plazmaforge.framework.core.data.path;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.plazmaforge.framework.util.StringUtils;

/**
 * @author ohapon
 *
 */
public class TypeResolver {
    
    /**
     * Base java package
     */
    // type.resolver.package = {java.package}
    private String basePackage; 
    
    /**
     * Base java packages <Biz package, Java package>
     */
    // :{package}:type.resolver.package = {java.package} 
    private Map<String, String> basePackages = new LinkedHashMap<String, String>();
    
    /**
     * Type packages (suffix) <Type, Package suffix>
     */
    // type.resolver.{Type} = {java.package.suffix}
    // :{package}:type.resolver.{Type} = {java.package.suffix}
    private Map<String, TypeDescriptor> typeDescriptors = new TreeMap<String, TypeDescriptor>(new TypeComparator());
    
    public void registerBasePackage(String bizPackage, String basePackage) {
	basePackages.put(bizPackage, basePackage);
    }
    
    public void registerTypePackage(String type, String packageSuffix) {
	registerTypePackage(type, packageSuffix, true);
    }
    
    public void registerTypePackage(String type, String packageSuffix, boolean typeAsSuffix) {
	typeDescriptors.put(type, new TypeDescriptor(packageSuffix, typeAsSuffix));
    }
    
    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public TypeInfo parsePath(String path) {
	return parsePath(path, null);
    }
    
    public TypeInfo parsePath(String path, String type) {
	if (path == null) {
	    return null;
	}
	
	// Normalize
	path = nullIfEmpty(path);
	type = nullIfEmpty(type);
	
	TypeInfo typeInfo = new TypeInfo();
	if (path == null) {
	    typeInfo.setType(type);
	    return typeInfo;
	}
	
	String packageName = null;
	String simpleName = null;
	String className = null;
	String associationName = null;
	
	// Parse path: {packageName}/{simpleName}
	int index = path.lastIndexOf("/");
	if (index == -1) {
	    simpleName = path;
	} else {
	    if (index > 0) {
		packageName = path.substring(0, index);
	    }
	    if (index < path.length() - 1) {
		simpleName = path.substring(index + 1);
	    }
	}
	
	type = normalizeType(simpleName, type);
	
	associationName = getAssociationName(simpleName, type);
	
	packageName = nullIfEmpty(packageName);
	simpleName = nullIfEmpty(simpleName);
	
	className = generateClassName(packageName, simpleName, type);
	
	typeInfo.setPackageName(packageName);
	typeInfo.setSimpleName(simpleName);
	typeInfo.setType(type);
	typeInfo.setClassName(className);
	typeInfo.setAssociationName(associationName);
	
	return typeInfo;
    }

    public TypeInfo parseClass(String className) {
	return parseClass(className, null);
    }
    
    public TypeInfo parseClass(String className, String type) {
	if (className == null) {
	    return null;
	}
	
	// Normalize
	className = nullIfEmpty(className);
	type = nullIfEmpty(type);	
	
	TypeInfo typeInfo = new TypeInfo();
	if (className == null) {
	    typeInfo.setType(type);
	    return typeInfo;
	}
	
	String packageName = null;
	String simpleName = null;
	String associationName = null;
	
	int index = className.lastIndexOf(".");
	if (index == -1) {
	    simpleName = className;
	} else {
	    if (index < className.length() - 1) {
		simpleName = className.substring(index + 1);
	    }
	}
	
	type = normalizeType(simpleName, type);
	
	associationName = getAssociationName(simpleName, type);
	
	//TODO: Must implement: packageName, simpleName
	// {base-package}.{biz-package}.{type}.{type-package-suffix}
	
	// Find start base package
	String baseJavaPackage = null; // nullIfEmpty(basePackages.get(packageName)); // TODO: Must implement find start package
	if (baseJavaPackage == null) {
	    baseJavaPackage = nullIfEmpty(basePackage);
	}
	
	String lastPath = className;
	if (baseJavaPackage != null) {
	    index = baseJavaPackage.length() + 1; // + 1 because + '.'
	    if (className.startsWith(baseJavaPackage) && index < className.length()) {
		lastPath = className.substring(index);
	    }
	}
	index = lastPath.indexOf(".");
	if (index > 0) {
	    packageName = lastPath.substring(0, index);
	}

	typeInfo.setPackageName(packageName);
	typeInfo.setSimpleName(simpleName);
	typeInfo.setType(type);
	typeInfo.setClassName(className);
	typeInfo.setAssociationName(associationName);
	
	return typeInfo;
    }
    
    /**
     * Return class name by path
     * @param path
     * @return
     */
    public String getClassNameByPath(String path) {
	return getClassNameByPath(path, null);
    }

    /**
     * Return class name by path and type
     * @param path
     * @param type
     * @return
     */
    public String getClassNameByPath(String path, String type) {
	TypeInfo typeInfo = parsePath(path, type);
	return typeInfo == null ? null : typeInfo.getClassName();
    }

    
    public String getPath(TypeInfo typeInfo, String type) {
	if (typeInfo.getAssociationName() == null) {
	    return null;
	}
	if (type == null) {
	    type = typeInfo.getType();
	}
	boolean isTypeAsSuffix = isTypeAsSuffix(type);
	return (typeInfo.getPackageName() == null ? "" : (typeInfo.getPackageName() + "/")) 
		+ typeInfo.getAssociationName() + (type == null || !isTypeAsSuffix ? "" : type);
	
    }

    public String getPath(TypeInfo typeInfo) {
	return getPath(typeInfo, null);
    }
    
    public String getTypePackageSuffix(String type) {
	if (type == null) {
	    return null;
	}
	TypeDescriptor typeDescriptor = typeDescriptors.get(type);
	return typeDescriptor == null ? null : typeDescriptor.packageSuffix;
    }
    
    public boolean isTypeAsSuffix(String type) {
	if (type == null) {
	    return false;
	}
	TypeDescriptor typeDescriptor = typeDescriptors.get(type);
	return typeDescriptor == null ? null : typeDescriptor.typeAsSuffix;
    }
    
    public String getClassName(TypeInfo typeInfo, String type) {
	if (typeInfo.getAssociationName() == null) {
	    return null;
	}
	if (type == null) {
	    type = typeInfo.getType();
	}
	boolean isTypeAsSuffix = isTypeAsSuffix(type);
	String simpleName = typeInfo.getAssociationName() + (type == null || !isTypeAsSuffix ? "" : type);
	String packageName = typeInfo.getPackageName();

	String className = generateClassName(packageName, simpleName, type);
	return className;
    }
    
    public String getClassName(TypeInfo typeInfo) {
	return getClassName(typeInfo, null);
    }
    
    
    /**
     * Return association name by simple name and type
     * @param simpleName
     * @param type
     * @return
     */
    public String getAssociationName(String simpleName, String type) {
	if (simpleName == null) {
	    return null;
	}
	String associationName = simpleName;
	if (type != null) {
	    if (simpleName.endsWith(type) && type.length() < simpleName.length()) {
		associationName = simpleName.substring(0, simpleName.length() - type.length());
	    }
	}
	return associationName;
    }
    
    public boolean existsType(String type) {
	if (type == null) {
	    return false;
	}
	return typeDescriptors.containsKey(type);
    }
    
    public String findTypeBySuffix(String simpleName) {
	if (simpleName == null) {
	    return null;
	}
	Set<String> types = typeDescriptors.keySet();
	for (String type: types) {
	    if  (simpleName.endsWith(type)){
		return type;
	    }
	}
	return null;
    }
    
    public List<String> getTypes() {
	return new ArrayList<String>(typeDescriptors.keySet());
    }
    
    protected String nullIfEmpty(String str) {
	return StringUtils.nullIfEmpty(str, true);
    }
    
    /**
     * Normalize type by simple name
     * @param simpleName
     * @param type
     * @return
     */
    protected String normalizeType(String simpleName, String type) {
	
	if (simpleName == null && type == null) {
	    return null;
	}

	if (simpleName == null && type != null) {
	    return type;
	}

	// Find real type
	String realType = findTypeBySuffix(simpleName);
	if (realType == null) {
	    return type;
	}
	
	// Override base type
	// For example:
	// realType = 'ListForm'
	// type     = 'Form'
	// ------------------------
	// result   =  'ListForm'
	if (type == null || realType.indexOf(type) > -1) {
	    return realType;
	}
	
	return type;
    }
    
    protected String generateClassName(String packageName, String simpleName, String type) {
	String className = simpleName;
	if (packageName == null) {
	    return className;
	}
	
	// Find base package in package storage
	// {base-package}.{biz-package}.{type}.{type-package-suffix}
	
	// Find start base package
	String baseJavaPackage = nullIfEmpty(basePackages.get(packageName));
	if (baseJavaPackage == null) {
	    baseJavaPackage = nullIfEmpty(basePackage);
	}
	String typeSuffixPackage = type == null ? null	: nullIfEmpty(getTypePackageSuffix(type));
	if (baseJavaPackage != null || typeSuffixPackage != null) {
	    className = (baseJavaPackage == null ? "" : (baseJavaPackage + "."))
		    + packageName
		    + (typeSuffixPackage == null ? "" : ("." + typeSuffixPackage)) + "." + simpleName;
	}
	return className;
    }
    
    public class TypeDescriptor {
	
	public TypeDescriptor(String packageSuffix, boolean typeAsSuffix) {
	    this.packageSuffix = packageSuffix;
	    this.typeAsSuffix = typeAsSuffix;
	}

	String packageSuffix;
	
	boolean typeAsSuffix;
	
    }
    
    public static class TypeComparator implements Comparator<String> {

	@Override
	public int compare(String s1, String s2) {
	    if (s1 == null && s2 == null) {
		return 0;
	    }
	    if (s1 == null) {
		return -1;
	    }
	    if (s2 == null) {
		return 1;
	    }
	    int len1 = s1.length();
	    int len2 = s2.length();
	    if (len1 == len2) {
		return s1.compareTo(s2) * -1; // inverse sorting
	    }
	    return len1 < len2 ? 1 : -1; // inverse sorting
	}
	
    }
}
