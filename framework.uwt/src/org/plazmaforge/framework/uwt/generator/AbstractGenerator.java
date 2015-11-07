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

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.core.data.ValuePresenter;
import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.util.StringUtils;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.builder.DataContainer;
import org.plazmaforge.framework.uwt.builder.UIBuilder;
import org.plazmaforge.framework.uwt.builder.UIBuilderHelper;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.widget.Style.Direction;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Style.LayoutRegion;
import org.plazmaforge.framework.uwt.widget.Style.Orientation;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;
import org.plazmaforge.framework.uwt.widget.tree.TreeItem;

public abstract class AbstractGenerator implements IUIGenerator {
    
  
    public enum ImportMode {
	CLASS,	// Full class name to import: com.mycompany.myproject.mypackage.MyClass; 
	PACKAGE	// Only package to import: com.mycompany.myproject.mypackage.*;
    }

    
    public String getBaseType(IData data) {
	return data == null ? null : (String) data.get(UIBuilder.SYS_PROPERTY_TYPE);
    }

    public String getType(IData data) {
	return data == null ? null : (String) data.get(UIObject.PROPERTY_TYPE);
    }

    /**
     * Return full class name by <code>IData</code>
     */
    public String getClassName(IData data) {
	
	// Base type (widget.Composite, widget.Label, winget.Button and etc.)
	String baseType = getBaseType(data);
	
	// Customization type (StylePanel, StyleLable, StyleButton)
	String type = getType(data);
	
	return getClassName(baseType, type);
	
    }
    
    
    /**
     * Return full class name by base type and customization type
     * @param baseType
     * @param type
     * @return
     */
    public abstract String getClassName(String baseType, String type); 
    

    /**
     * Default implementation get class name by base type and customization type
     * @param baseType
     * @param type
     * @return
     */
    protected String getDefaultClassName(String baseType, String type) {
	return baseType == null ? null : UIGenerator.UWT_PACKAGE + "." + baseType;
    }  

    /**
     * Return full super class name (with package) to generation
     * @param data
     * @return
     */
    protected String getGenerateClassName(String packageName, IData data) {
	if (data == null) {
	    return null;
	}
	// System property '$genname' is priority than class name
	String name = (String) data.get(UIBuilder.SYS_PROPERTY_GEN_NAME);
	if (name != null) {
	    return packageName == null ? name : (packageName + "." + name);
	}	
	name = (String) data.get(UIObject.PROPERTY_NAME);
	if (name == null) {
	    return null;
	}
	return packageName == null ? name : (packageName + "." + name);
    }
    
    /**
     * Return full super class name (with package) to generation
     * @param data
     * @return
     */
    protected String getGenerateSuperClassName(IData data) {
	if (data == null) {
	    return null;
	}
	// System property '$gensuperclass' is priority than class name
	String className = (String) data.get(UIBuilder.SYS_PROPERTY_GEN_SUPER_CLASS);
	if (className != null) {
	    return className;
	}
	return getClassName(data);
    }
    
        
    protected ClassSourceFactory createClassSourceFactory(GeneratorContext context, IData data) {
	ClassSourceFactory classSourceFactory = new ClassSourceFactory();
	return classSourceFactory;
    }

    /**
     * Create <code>ClassContext</code> by <code>GeneratorContext</code> and <code>IData</code>
     * 
     * @param context
     * @param data
     * @return
     */
    protected ClassContext createClassContext(GeneratorContext context, IData data) {
	
	// Get package name from generator context 
	String generatePackageName = context.getPackageName();
	
	// Get super class name from data (can override $superclassname)
	String generateSuperClassName = getGenerateSuperClassName(data);
	
	// get class name from data (can override $classname)) 
	String generateClassName = getGenerateClassName(generatePackageName, data);

	ClassContext classContext = new ClassContext();

	classContext.setClassName(generateClassName);
	classContext.setPackageName(generatePackageName);
	classContext.setSuperClassName(generateSuperClassName);

	return classContext;
    }

    /**
     * Return true if only 'new Constructor(...)' need to populate instance 
     * @param data
     * @return
     */
    public boolean isOnlyConstructor(IData data) {
	return false;
    }
    
    /**
     * Return simple instance ('new Constructor()')
     * @param context
     * @param className
     * @return
     */
    public String getInstance(ScopeContext context, IData data, String className) {
	return  "new " + className + "()";
    }

    /**
     * Return full instance ('new Constructor(...)')
     * @param context 
     * @param data
     * @param className
     * @return
     */
    public String getFullInstance(ScopeContext context, IData data, String className) {
	return getInstance(context, data, className);
    }
    
    ////

    protected String generateInstanceVariable(ScopeContext context, VariableContext variableContext, IData data, SourceWriter sw) {
	return UIGeneratorHelper.generateInstanceVariable(context, variableContext, data, sw);
    }

    
    protected String generateCreateData(ScopeContext context, String variable, IData data, SourceWriter sw) {
	return UIGeneratorHelper.generateCreateData(context, variable, data, sw);
    }
    
    protected void generateSetData(ScopeContext context, String variable, IData data, String bean, String property, String method, SourceWriter sw) {
	UIGeneratorHelper.generateSetData(context, variable, data, bean, property, method, sw);
    }
    
    protected void generateSetData(ScopeContext context, String variable, IData data, String property, SourceWriter sw) {
	generateSetData(context, variable, data, null, property, null, sw);
    }
    
    ////
    
    protected void generateSetIntegerProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	UIGeneratorHelper.generateSetIntegerProperty(context, data, bean, property, method, sw);
    }

    protected void generateSetIntegerProperty(ScopeContext context, IData data, String bean, String property, SourceWriter sw) {
	UIGeneratorHelper.generateSetIntegerProperty(context, data, bean, property, sw);
    }
    
    ////
    
    protected void generateSetFloatProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	UIGeneratorHelper.generateSetFloatProperty(context, data, bean, property, method, sw);
    }

    protected void generateSetFloatProperty(ScopeContext context, IData data, String bean, String property, SourceWriter sw) {
	UIGeneratorHelper.generateSetFloatProperty(context, data, bean, property, sw);
    }

    ////
    
    protected void generateSetDoubleProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	UIGeneratorHelper.generateSetDoubleProperty(context, data, bean, property, method, sw);
    }

    protected void generateSetDoubleProperty(ScopeContext context, IData data, String bean, String property, SourceWriter sw) {
	UIGeneratorHelper.generateSetDoubleProperty(context, data, bean, property, sw);
    }

    ////
    
    protected void generateSetDateProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	UIGeneratorHelper.generateSetDateProperty(context, data, bean, property, method, sw);
    }

    protected void generateSetDateProperty(ScopeContext context, IData data, String bean, String property, SourceWriter sw) {
	UIGeneratorHelper.generateSetDateProperty(context, data, bean, property, sw);
    }
    
    ////
    
    protected void generateSetTimeProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	UIGeneratorHelper.generateSetTimeProperty(context, data, bean, property, method, sw);
    }

    protected void generateSetTimeProperty(ScopeContext context, IData data, String bean, String property, SourceWriter sw) {
	UIGeneratorHelper.generateSetTimeProperty(context, data, bean, property, sw);
    }
    
    ////

    protected void generateSetDateTimeProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	UIGeneratorHelper.generateSetDateTimeProperty(context, data, bean, property, method, sw);
    }
    
    protected void generateSetDateTimeProperty(ScopeContext context, IData data, String bean, String property, SourceWriter sw) {
	UIGeneratorHelper.generateSetDateTimeProperty(context, data, bean, property, sw);
    }

    ////
    
    protected void generateSetStringProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	UIGeneratorHelper.generateSetStringProperty(context, data, bean, property, method, sw);
    }

    protected void generateSetStringProperty(ScopeContext context, IData data, String bean, String property, SourceWriter sw) {
	UIGeneratorHelper.generateSetStringProperty(context, data, bean, property, sw);
    }

    ////
    
    protected void generateSetRSStringProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	UIGeneratorHelper.generateSetRSStringProperty(context, data, bean, property, method, sw);
    }

    protected void generateSetRSStringProperty(ScopeContext context, IData data, String bean, String property, SourceWriter sw) {
	UIGeneratorHelper.generateSetRSStringProperty(context, data, bean, property, sw);
    }

    ////
    
    protected void generateSetImagePathProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	UIGeneratorHelper.generateSetImagePathProperty(context, data, bean, property, method, sw);
    }

    protected void generateSetImagePathProperty(ScopeContext context, IData data, String bean, String property, SourceWriter sw) {
	UIGeneratorHelper.generateSetImagePathProperty(context, data, bean, property, sw);
    }
    
    ////
    
    protected void generateSetBooleanProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	UIGeneratorHelper.generateSetBooleanProperty(context, data, bean, property, method, sw);
    }

    protected void generateSetBooleanProperty(ScopeContext context, IData data, String bean, String property, SourceWriter sw) {
	UIGeneratorHelper.generateSetBooleanProperty(context, data, bean, property, sw);
    }

    ////
    
    protected void generateSetValueProperty(String type, ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	UIGeneratorHelper.generateSetValueProperty(type, context, data, bean, property, method, sw);
    }

    protected void generateSetValueProperty(String type, ScopeContext context, IData data, String bean, String property, SourceWriter sw) {
	UIGeneratorHelper.generateSetValueProperty(type, context, data, bean, property, sw);
    }

    ////
    
    protected void generateSetColorProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	UIGeneratorHelper.generateSetColorProperty(context, data, bean, property, method, sw);
    }

    protected void generateSetColorProperty(ScopeContext context, IData data, String bean, String property, SourceWriter sw) {
	UIGeneratorHelper.generateSetColorProperty(context, data, bean, property, sw);
    }

    protected void generateSetFontProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	UIGeneratorHelper.generateSetFontProperty(context, data, bean, property, method, sw);
    }

    protected void generateSetFontProperty(ScopeContext context, IData data, String bean, String property, SourceWriter sw) {
	UIGeneratorHelper.generateSetFontProperty(context, data, bean, property, sw);
    }

    ////

    protected void generateSetHorizontalAlignProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	UIGeneratorHelper.generateSetHorizontalAlignProperty(context, data, bean, property, method, sw);
    }

    protected void generateSetVerticalAlignProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	UIGeneratorHelper.generateSetVerticalAlignProperty(context, data, bean, property, method, sw);
    }

    protected void generateSetOrientationProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	UIGeneratorHelper.generateSetOrientationProperty(context, data, bean, property, method, sw);
    }

    protected void generateSetLayoutRegionProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	UIGeneratorHelper.generateSetLayoutRegionProperty(context, data, bean, property, method, sw);
    }

    ////
    
    protected void generateSetPropertyLine(String bean, String property, String method, String value, SourceWriter sw) {
	UIGeneratorHelper.generateSetPropertyLine(bean, property, method, value, sw);
    }

    protected void generateSetLine(String valueClass, String variable, String value, SourceWriter sw) {
	UIGeneratorHelper.generateSetLine(valueClass, variable, value, sw);
    }

    

    
    /////////////////////////////////////////////////////////////////////////////////
    //
    // UTIL METHODS
    //
    /////////////////////////////////////////////////////////////////////////////////
    
    protected String getVariableTemplate(String className) {
	return UIGeneratorHelper.getVariableTemplate(className);
    }

    protected String createSetMethodName(String property) {
	return UIGeneratorHelper.createSetMethodName(property);
    }

    protected String createGetMethodName(String property) {
	return UIGeneratorHelper.createGetMethodName(property);
    }

    protected String createMethodName(String prefix, String property) {
	return UIGeneratorHelper.createMethodName(prefix, property);
    }

    ////

    protected IUIGenerator getGenerator(IData data) {
	return UIGenerator.getGenerator(data, true);
    }
    
    protected IUIGenerator getGenerator(String baseType, String type) {
	return UIGenerator.getGenerator(baseType, type, true);
    }

    ////
    
    protected ValuePresenter getPresenter(String type) {
	return UIBuilderHelper.getPresenter(type);
    }

    ////

    protected IData getDataValue(IData data, String property) {
	return (IData) getValue(data, property);
    }

    protected List<IData> getDataList(IData data, String property) {
	return (List<IData>) getValue(data, property);
    }

    protected Object getValue(IData data, String property) {
	return UIBuilderHelper.getValue(data, property);
    }

    protected String getString(IData data, String property) {
	return (String) getValue(data, property);
    }

    protected String getRSString(IData data, String property) {
	//TODO: Must replace
	return getString(data, property);
    }

    protected Boolean getBoolean(IData data, String property) {
	return UIBuilderHelper.getBoolean(data, property);
    }

    protected Byte getByte(IData data, String property) {
	return UIBuilderHelper.getByte(data, property);
    }

    protected Short getShort(IData data, String property) {
	return UIBuilderHelper.getShort(data, property);
    }
    
    protected Integer getInteger(IData data, String property) {
	return UIBuilderHelper.getInteger(data, property);
    }

    protected Float getFloat(IData data, String property) {
	return UIBuilderHelper.getFloat(data, property);
    }

    protected Double getDouble(IData data, String property) {
	return UIBuilderHelper.getDouble(data, property);
    }

    ////
    
    protected Date getDate(IData data, String property) {
	return UIBuilderHelper.getDate(data, property);
    }

    protected Date getTime(IData data, String property) {
	return UIBuilderHelper.getTime(data, property);
    }

    protected Date getDateTime(IData data, String property) {
	return UIBuilderHelper.getDateTime(data, property);
    }

    
    ////
    
    protected Color getColor(IData data, String property) {
	return UIBuilderHelper.getColor(data, property);
    }
    
    protected Font getFont(IData data, String property) {
	return UIBuilderHelper.getFont(data, property);
    }

    ////
    
    protected HorizontalAlign getHorizontalAlign(IData data, String property) {
	return UIBuilderHelper.getHorizontalAlign(data, property);
    }
    
    protected VerticalAlign getVerticalAlign(IData data, String property) {
	return UIBuilderHelper.getVerticalAlign(data, property);
    }

    protected Direction getDirection(IData data, String property) {
	return UIBuilderHelper.getDirection(data, property);
    }
    
    protected Orientation getOrientation(IData data, String property) {
	return UIBuilderHelper.getOrientation(data, property);
    }

    protected LayoutRegion getLayoutRegion(IData data, String property) {
	return UIBuilderHelper.getLayoutRegion(data, property);
    }
    

    ////
    
    protected IData getNode(IData data, String name) {
	return UIBuilderHelper.getNode(data, name);
    }
    
    protected List<IData> getChildrenOfNode(IData data, String name) {
  	return UIBuilderHelper.getChildrenOfNode(data, name);
    }
    

    protected IData createData() {
  	return UIBuilderHelper.createData();
    }
    
    protected IData createData(Map<String, Object> values) {
  	return UIBuilderHelper.createData(values);
    }

    
    /**
     * Parse string data and create <code>IData</code>
     * @param data
     * @return <code>IData</code>
     */
    protected IData createData(String data) {
	return UIBuilderHelper.createData(data);
    }
    
    /**
     * Create <code>IData</code> by <code>DataContainer</code>
     * @param dataContainer
     * @return <code>IData</code>
     */
    protected IData createData(DataContainer dataContainer) {
	return UIBuilderHelper.createData(dataContainer);
    }

   
    /**
     * Generate begin of class
     * @param classContext
     * @param sw
     */
    protected void generateBegin(ClassContext classContext, SourceWriter sw) {
	
	String packageName = classContext.getPackageName();
	String simpleClassName = classContext.getSimpleClassName();
	String superClassName = classContext.getSuperClassName();
	
	superClassName = prepareClassName(classContext, superClassName);
	
	sw.println("package " + packageName + ";");
	sw.println();
	
	String[] imports = classContext.getImports();
	for (String importName : imports) {
	    sw.println("import " + importName + ";");
	}
	sw.println();
	sw.println("public class " + simpleClassName + " extends " + superClassName + " {");
	sw.println();

    }

    /**
     * Generate end of class
     * @param classContext
     * @param sw
     */
    protected void generateEnd(ClassContext classContext, SourceWriter sw) {
	// TODO: Reset ident
	sw.println();
	sw.print("}");
    }
    
    /**
     * Generate constructor of class
     * @param classContext
     * @param sw
     */
    protected void generateConstructor(ClassContext classContext, SourceWriter sw) {
	sw.println();
	sw.indent();
	sw.print("public " + classContext.getSimpleClassName() + "() {");
	sw.println();
	sw.print("}");
	sw.outdent();
	sw.println();
	
    }

    
    
    
    
    
    
    
    protected void generatePreConfigure(ClassContext classContext, SourceWriter sw) {

	sw.indent();
	sw.println();
	sw.print("@Override");
	sw.println();
	sw.print("public void preConfigure() {");
	sw.println();
	
	doGeneratePreConfigure(classContext, sw);
	
	sw.println();
	sw.print("}");
	sw.outdent();
	
	sw.println();
	
    }

    protected void generateConfigure(ClassContext classContext, SourceWriter sw) {

	sw.indent();
	sw.println();
	sw.print("@Override");
	sw.println();
	sw.print("public void configure() {");
	sw.println();
	
	doGenerateConfigure(classContext, sw);
	
	sw.println();
	sw.print("}");
	sw.outdent();
	
	sw.println();
	
    }
    
    protected void generatePostConfigure(ClassContext classContext, SourceWriter sw) {

	sw.indent();
	sw.println();
	sw.print("@Override");
	sw.println();
	sw.print("public void postConfigure() {");
	sw.println();
	
	doGeneratePostConfigure(classContext, sw);
	
	sw.println();
	sw.print("}");
	sw.outdent();
	
	sw.println();
	
    }
    
    
    protected void generateCreateUI(ClassContext classContext, IData data, SourceWriter sw) {

	sw.indent();
	sw.println();
	sw.print("@Override");
	sw.println();
	sw.print("public void createUI() {");
	sw.println();
	
	doGenerateCreateUI(classContext, data, sw);
	
	sw.println();
	sw.print("}");
	sw.outdent();
	
	sw.println();
	
    }
    
    public void generatePopulate(ScopeContext context, IData data, String bean, SourceWriter sw) {
	
	generateSetStringProperty(context, data, bean, UIObject.PROPERTY_ID, sw);
	generateSetStringProperty(context, data, bean, UIObject.PROPERTY_NAME, sw);
	generateSetStringProperty(context, data, bean, UIObject.PROPERTY_TYPE, sw);
	generateSetStringProperty(context, data, bean, UIObject.PROPERTY_LOCALE, "setLocaleName", sw);
	generateSetStringProperty(context, data, bean, UIObject.PROPERTY_RESOURCE, "setResourceName", sw);
	
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////

    protected void doGeneratePreConfigure(ClassContext classContext, SourceWriter sw) {
	
    }

    protected void doGenerateConfigure(ClassContext classContext, SourceWriter sw) {
	
    }
    
    protected void doGeneratePostConfigure(ClassContext classContext, SourceWriter sw) {
	
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    protected void doGenerateCreateUI(ClassContext classContext, IData data,  SourceWriter sw) {
	
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Prepare and return class name
     * If AutoImport mode then add import of class name and change class name (simple class name)
     *  
     * @param context
     * @param className
     * @return
     */
    protected String prepareClassName(ScopeContext context, String className) {
	return UIGeneratorHelper.prepareClassName(context, className);
    }

    protected String prepareClassName(ScopeContext context, Class<?> klass) {
	return UIGeneratorHelper.prepareClassName(context, klass.getName());
    }

    
    /**
     * Prepare and return class name
     * If AutoImport mode then add import of class name and change class name (simple class name)
     * 
     * @param context
     * @param className
     * @param importMode
     * @return
     */
    protected String prepareClassName(ScopeContext context, String className, ImportMode importMode) {
	return UIGeneratorHelper.prepareClassName(context, className, importMode);
    }

    protected String prepareEnumName(ScopeContext context, String className) {
	return prepareClassName(context, className, ImportMode.CLASS);
    }

    ////
    
    protected List<IData> getDataItems(IData data) {
	return UIBuilderHelper.getDataItems(data);
    }

    protected <T> T getDataValue(Class<T> tClass, IData item) {
	return UIBuilderHelper.getDataValue(tClass, item);
    }

    /**
     * Return list of item value (one value of item)
     * 
     * @param tClass
     * @param data
     * @return
     */
    protected <T> List<T> getItemValues(Class<T> tClass, IData data) {
	return UIBuilderHelper.getItemValues(tClass, data);
    }

    /**
     * Return item value (one value of item)
     * 
     * @param tClass
     * @param item
     * @return
     */
    protected <T> T getItemValue(Class<T> tClass, IData item) {
	return UIBuilderHelper.getItemValue(tClass, item);
    }

    /**
     * Return list of item records (more values of item)
     * 
     * @param tClass
     * @param data
     * @return
     */
    protected List<Object[]> getItemRecords(Class[] tClass, IData data) {
	return UIBuilderHelper.getItemRecords(tClass, data);
    }

    protected List<TreeItem> getTreeItems(Class[] tClass, IData data) {
	return UIBuilderHelper.getTreeItems(tClass, data);
    }
    
    /**
     * Return record of item (more values of item)
     * 
     * @param tClass
     * @param item
     * @return
     */
    protected Object[] getItemRecord(Class[] tClass, IData item) {
	return UIBuilderHelper.getItemRecord(tClass, item);
    }

    ////
    
    protected boolean in(String str, String[] array) {
	if (str == null || array == null || array.length == 0) {
	    return false;
	}
	for (String e: array) {
	    if (str.equals(e)) {
		return true;
	    }
	}
	return false;
    }
    
    protected boolean in(String str, List<String> array) {
	if (str == null || array == null || array.isEmpty()) {
	    return false;
	}
	for (String e: array) {
	    if (str.equals(e)) {
		return true;
	    }
	}
	return false;
    }
    
    protected boolean eq(Object obj1, Object obj2) {
	if (obj1 == null || obj2 == null) {
	    return false;
	}
	return obj1.equals(obj2);
    }

    protected boolean isEmpty(String str) {
	return StringUtils.isEmpty(str);
    }

}
