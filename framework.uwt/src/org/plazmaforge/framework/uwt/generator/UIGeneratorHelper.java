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

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.util.CoreUtils;
import org.plazmaforge.framework.uwt.builder.DataContainer;
import org.plazmaforge.framework.uwt.builder.UIBuilderHelper;
import org.plazmaforge.framework.uwt.builder.UIPresenter;
import org.plazmaforge.framework.uwt.generator.AbstractGenerator.ImportMode;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.widget.Style.Direction;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Style.LayoutRegion;
import org.plazmaforge.framework.uwt.widget.Style.Orientation;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;

public class UIGeneratorHelper {

    public static String[] MONTH_LIST = new String[] {
	"JANUARY",
	"FEBRUARY",
	"MARCH", 
	"APRIL", 
	"MAY", 
	"JUNE", 
	"JULY", 
	"AUGUST", 
	"SEPTEMBER", 
	"OCTOBER", 
	"NOVEMBER",
	"DECEMBER"
    };
    
    
    public static String getSimpleClassName(String className) {
	return CoreUtils.getSimpleClassName(className);
    }
    
    public static String getPackageName(String className) {
	return CoreUtils.getPackageName(className);
    }
    
    /**
     * Create name of method by type (get/set) and property 
     * @param prefix (get/set/is/has)
     * @param property
     * @return
     */
    public static String createMethodName(String prefix, String property) {
	if (property == null) {
	    return null;
	}
	property = property.trim();
	if (property.isEmpty()) {
	    return null;
	}
	return prefix + property.substring(0, 1).toUpperCase() + property.substring(1);
    }

    /**
     * Create name of 'set' method by property
     * @param property
     * @return
     */
    public static String createSetMethodName(String property) {
	return createMethodName("set", property);
    }

    /**
     * Create name of 'get' method by property
     * @param property
     * @return
     */
    public static String createGetMethodName(String property) {
	return createMethodName("get", property);
    }
    
    /**
     * Return template of variable by class
     * @param className
     * For example:
     *  class = 'org.myproject.MyTableItem'
     *  template = 'myTableItem'
     * @return
     */
    public static String getVariableTemplate(String className) {
	if (className == null) {
	    return null;
	}
	String name = getSimpleClassName(className);

	name = name.substring(0, 1).toLowerCase() + name.substring(1);
	return name;
    }
    
    public static String prepareClassName(ScopeContext context, String className, ImportMode importMode) {
	if (className == null) {
	    return null;
	}
	ClassContext classContext = context.getClassContext();
	if (classContext == null) {
	    return className;
	}
	
	if (!classContext.isAutoImport()) { // MAYBE USE GeneratorContext
	    return className; 
 	}
	
	String packageName = UIGeneratorHelper.getPackageName(className);
	if (packageName == null) {
	    return className;
	}
	
	String importName = ImportMode.CLASS == importMode ? className : (packageName + ".*");
	className = UIGeneratorHelper.getSimpleClassName(className);
	
	
	// Add import to class
	classContext.addImport(importName);
	
	return className;
	
	
    }
    
    public static String prepareClassName(ScopeContext context, String className) {
	// Prepare class with current import mode
	return prepareClassName(context, className, context.getClassContext().getImportMode());
    }
    
    public static String prepareEnumName(ScopeContext context, String className) {
	return prepareClassName(context, className, ImportMode.CLASS);
    }

    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // GENERATE METHODS
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public static String generateInstanceString(ScopeContext context, String value) {
	if (value == null) {
	    return null;
	}    
	return "\"" + value + "\"";
    }
    
    public static String generateInstanceBoolean(ScopeContext context, Boolean value) {
	if (value == null) {
	    return null;
	}    
	return value.toString();
    }

    ////
    
    public static String generateInstanceNumber(ScopeContext context, Number value) {
	if (value == null) {
	    return null;
	}    
	return value.toString();
    }

    public static String generateInstanceByte(ScopeContext context, Byte value) {
	return generateInstanceNumber(context, value);
    }

    public static String generateInstanceShort(ScopeContext context, Short value) {
	return generateInstanceNumber(context, value);
    }

    public static String generateInstanceInteger(ScopeContext context, Integer value) {
	return generateInstanceNumber(context, value);
    }

    public static String generateInstanceFloat(ScopeContext context, Float value) {
	return generateInstanceNumber(context, value);
    }

    public static String generateInstanceDouble(ScopeContext context, Double value) {
	return generateInstanceNumber(context, value);
    }

    ////
    
    public static String generateInstanceDate(ScopeContext context, Date value) {
	if (value == null) {
	    return null;
	}
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(value);
	
	int year = calendar.get(Calendar.YEAR);
	int month = calendar.get(Calendar.MONTH);
	int day = calendar.get(Calendar.DAY_OF_MONTH);
	
	String monthStr = prepareClassName(context, UIGenerator.CALENDAR_CLASS) + "." + MONTH_LIST[month];
	String className = prepareClassName(context, UIGenerator.GREGORIAN_CALENDAR_CLASS);
	return "new " + className + "(" + year + ", " + monthStr + ", " + day + ").getTime()";
    }
    
    public static String generateInstanceTime(ScopeContext context, Date value) {
	
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(value);

	int year = 0;
	int month = 0;
	int day = 0;
	int hours = calendar.get(Calendar.HOUR_OF_DAY);
	int minutes = calendar.get(Calendar.MINUTE);
	int seconds = calendar.get(Calendar.SECOND);
	
	String className = prepareClassName(context, UIGenerator.GREGORIAN_CALENDAR_CLASS);
	return "new " + className + "(" + year + ", " + month + ", " + day + ", " + hours + ", " + minutes + ", " + seconds + ").getTime()";
    }

    public static String generateInstanceDateTime(ScopeContext context, Date value) {

	Calendar calendar = Calendar.getInstance();
	calendar.setTime(value);

	int year = calendar.get(Calendar.YEAR);
	int month = calendar.get(Calendar.MONTH);
	int day = calendar.get(Calendar.DAY_OF_MONTH);
	int hours = calendar.get(Calendar.HOUR_OF_DAY);
	int minutes = calendar.get(Calendar.MINUTE);
	int seconds = calendar.get(Calendar.SECOND);
	
	String monthStr = prepareClassName(context, UIGenerator.CALENDAR_CLASS) + "." + MONTH_LIST[month];
	String className = prepareClassName(context, UIGenerator.GREGORIAN_CALENDAR_CLASS);
	return "new " + className + "(" + year + ", " + monthStr + ", " + day + ", " + hours + ", " + minutes + ", " + seconds + ").getTime()";
    }

    ////
    
    public static String generateInstanceColor(ScopeContext context, Color value) {
	if (value == null) {
	    return null;
	}
	String className = prepareClassName(context, UIGenerator.COLOR_CLASS);
	return "new " + className + "(" + value.getRed() + ", " + value.getGreen() + ", " + value.getBlue() + ")";
    }

    public static String generateInstanceFont(ScopeContext context, Font value) {
	if (value == null) {
	    return null;
	}
	String className = prepareClassName(context, UIGenerator.FONT_CLASS);
	return "new "  + className + "(\"" + value.getName() + "\", " + value.getSize() + ", " + value.getStyle() + ")";
    }

    ////
    
    public static String generateInstanceHorizontalAlign(ScopeContext context, HorizontalAlign value) {
  	if (value == null) {
  	    return null;
  	}    
  	String className = prepareEnumName(context, UIGenerator.HORIZONTAL_ALIGN_CLASS);
  	String instance = className + "." + value.toString();
  	return instance;
      }
    
    public static String generateInstanceVerticalAlign(ScopeContext context, VerticalAlign value) {
  	if (value == null) {
  	    return null;
  	}    
  	String className = prepareEnumName(context, UIGenerator.VERTICAL_ALIGN_CLASS);
  	String instance = className + "." + value.toString();
  	return instance;
      }
    
    public static String generateInstanceOrientation(ScopeContext context, Orientation value) {
  	if (value == null) {
  	    return null;
  	}    
  	String className = prepareEnumName(context, UIGenerator.ORIENTATION_CLASS);
  	String instance = className + "." + value.toString();
  	return instance;
      }
    
    public static String generateInstanceLayoutRegion(ScopeContext context, LayoutRegion value) {
	if (value == null) {
	    return null;
	}    
	String className = prepareEnumName(context, UIGenerator.LAYOUT_REGION_CLASS);
	String instance = className + "." + value.toString();
	return instance;
    }
    
    ////
    
    public static void generateSetPropertyLine(String bean, String property, String method, String value, SourceWriter sw) {
	if (value == null) {
	    return;
	}
	if (method == null) {
	    // generate setter method by property
	    method = createSetMethodName(property);
	}
	sw.println((bean == null ? "" : (bean + ".")) + method + "(" + value + ");");
    }

    public static void generateSetLine(String valueClass, String variable, String value, SourceWriter sw) {
	sw.println((valueClass == null ? "" : (valueClass + " ")) + variable + " = " + value + ";");
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public static void generateSetStringProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	String value = getString(data, property);
	if (value == null) {
	    return;
	}    
	generateSetPropertyLine(bean, property, method, generateInstanceString(context, value), sw);
    }

    public static void generateSetStringProperty(ScopeContext context, IData data, String bean, String property, SourceWriter sw) {
	generateSetStringProperty(context, data, bean, property, null, sw);
    }

    
    public static void generateSetRSStringProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	String value = getString(data, property);
	if (value == null) {
	    return;
	}
	String instance = null;
	String rsKey = UIBuilderHelper.getRSKey(value);
	if (rsKey == null) {
	    instance =  generateInstanceString(context, value);
	} else { 
	    if (rsKey.isEmpty()) {
		instance = ""; // (?)
	    } else {
		instance = "getString(\"" + rsKey + "\")"; // getResourceString()
	    }
	}
	generateSetPropertyLine(bean, property, method, instance, sw);
    }

    public static void generateSetRSStringProperty(ScopeContext context, IData data, String bean, String property, SourceWriter sw) {
	generateSetRSStringProperty(context, data, bean, property, null, sw);
    }

    
    public static void generateSetImagePathProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	String value = getRSString(data, property);
	if (value == null) {
	    return;
	}
	generateSetPropertyLine(bean, property, method, generateInstanceString(context, value), sw);
    }

    public static void generateSetImagePathProperty(ScopeContext context, IData data, String bean, String property, SourceWriter sw) {
	generateSetRSStringProperty(context, data, bean, property, null, sw);
    }

    
    public static void generateSetBooleanProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	Boolean value = getBoolean(data, property);
	if (value == null) {
	    return;
	}
	generateSetPropertyLine(bean, property, method, generateInstanceBoolean(context, value), sw);
    }

    public static void generateSetBooleanProperty(ScopeContext context, IData data, String bean, String property, SourceWriter sw) {
	generateSetBooleanProperty(context, data, bean, property, null, sw);
    }

    public static void generateSetByteProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	Byte value = getByte(data, property);
	if (value == null) {
	    return;
	}
	generateSetPropertyLine(bean, property, method, generateInstanceByte(context, value), sw);
    }

    public static void generateSetByteProperty(ScopeContext context, IData data, String bean, String property, SourceWriter sw) {
	generateSetByteProperty(context, data, bean, property, null, sw);
    }

    
    public static void generateSetShortProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	Short value = getShort(data, property);
	if (value == null) {
	    return;
	}
	generateSetPropertyLine(bean, property, method, generateInstanceShort(context, value), sw);
    }

    public static void generateSetShortProperty(ScopeContext context, IData data, String bean, String property, SourceWriter sw) {
	generateSetByteProperty(context, data, bean, property, null, sw);
    }

       
    public static void generateSetIntegerProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	Integer value = getInteger(data, property);
	if (value == null) {
	    return;
	}
	generateSetPropertyLine(bean, property, method, generateInstanceInteger(context, value), sw);
    }

    public static void generateSetIntegerProperty(ScopeContext context, IData data, String bean, String property, SourceWriter sw) {
	generateSetIntegerProperty(context, data, bean, property, null, sw);
    }


    public static void generateSetFloatProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	Float value = getFloat(data, property);
	if (value == null) {
	    return;
	}
	generateSetPropertyLine(bean, property, method, generateInstanceFloat(context, value), sw);
    }

    public static void generateSetFloatProperty(ScopeContext context, IData data, String bean, String property, SourceWriter sw) {
	generateSetFloatProperty(context, data, bean, property, null, sw);
    }


    public static void generateSetDoubleProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	Double value = getDouble(data, property);
	if (value == null) {
	    return;
	}
	generateSetPropertyLine(bean, property, method, generateInstanceDouble(context, value), sw);
    }

    public static void generateSetDoubleProperty(ScopeContext context, IData data, String bean, String property, SourceWriter sw) {
	generateSetDoubleProperty(context, data, bean, property, null, sw);
    }

    
    public static void generateSetDateProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	Date value = getDate(data, property);
	if (value == null) {
	    return;
	}
	String instance = generateInstanceDate(context, value);
	generateSetPropertyLine(bean, property, method, instance, sw);
    }

    public static void generateSetDateProperty(ScopeContext context, IData data, String bean, String property, SourceWriter sw) {
	generateSetDateProperty(context, data, bean, property, null, sw);
    }

    public static void generateSetTimeProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	Date value = getTime(data, property);
	if (value == null) {
	    return;
	}
	String instance = generateInstanceTime(context, value);
	generateSetPropertyLine(bean, property, method, instance, sw);
    }

    public static void generateSetTimeProperty(ScopeContext context, IData data, String bean, String property, SourceWriter sw) {
	generateSetTimeProperty(context, data, bean, property, null, sw);
    }

    
    public static void generateSetDateTimeProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	Date value = getDateTime(data, property);
	if (value == null) {
	    return;
	}
	String instance = generateInstanceDateTime(context, value);
	generateSetPropertyLine(bean, property, method, instance, sw);
    }

    public static void generateSetDateTimeProperty(ScopeContext context, IData data, String bean, String property, SourceWriter sw) {
	generateSetDateTimeProperty(context, data, bean, property, null, sw);
    }    
    
    ////
    
    public static void generateSetColorProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	Color value = getColor(data, property);
	if (value == null) {
	    return;
	}
	generateSetPropertyLine(bean, property, method,	generateInstanceColor(context, value), sw);
    }

    public static void generateSetColorProperty(ScopeContext context, IData data, String bean, String property, SourceWriter sw) {
	generateSetColorProperty(context, data, bean, property, null, sw);
    }

    public static void generateSetFontProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	Font value = getFont(data, property);
	if (value == null) {
	    return;
	}
	generateSetPropertyLine(bean, property, method,	generateInstanceFont(context, value), sw);
    }

    public static void generateSetFontProperty(ScopeContext context, IData data, String bean, String property, SourceWriter sw) {
	generateSetFontProperty(context, data, bean, property, null, sw);
    }

    public static void generateSetHorizontalAlignProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	HorizontalAlign value = getHorizontalAlign(data, property);
	if (value == null) {
	    return;
	}
	String instance = generateInstanceHorizontalAlign(context, value);
	generateSetPropertyLine(bean, property, method, instance, sw);
    }

    public static void generateSetVerticalAlignProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	VerticalAlign value = getVerticalAlign(data, property);
	if (value == null) {
	    return;
	}
	String instance = generateInstanceVerticalAlign(context, value);
	generateSetPropertyLine(bean, property, method, instance, sw);
    }
     

    public static void generateSetOrientationProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	Orientation value = getOrientation(data, property);
	if (value == null) {
	    return;
	}
	String instance = generateInstanceOrientation(context, value);
	generateSetPropertyLine(bean, property, method, instance, sw);
    }

    public static void generateSetLayoutRegionProperty(ScopeContext context, IData data, String bean, String property, String method, SourceWriter sw) {
	LayoutRegion value = getLayoutRegion(data, property);
	if (value == null) {
	    return;
	}
	String instance = generateInstanceLayoutRegion(context, value);
	generateSetPropertyLine(bean, property, method, instance, sw);
    }
    
    ////

    public static void generateSetValueProperty(String type, ScopeContext context,  IData data, String bean, String property, String method,  SourceWriter sw) {
	if (type == null) {
	    return;
	}
	if (UIPresenter.STRING_TYPE.equals(type)) {
	    generateSetStringProperty(context, data, bean, property, method, sw);
	    return;
	} else if (UIPresenter.BOOLEAN_TYPE.equals(type)) {
	    generateSetBooleanProperty(context, data, bean, property, method, sw);
	    return;
	} else if (UIPresenter.BYTE_TYPE.equals(type)) {
	    generateSetByteProperty(context, data, bean, property, method, sw);
	    return;
	} else if (UIPresenter.SHORT_TYPE.equals(type)) {
	    generateSetShortProperty(context, data, bean, property, method, sw);
	    return;
	} else if (UIPresenter.INTEGER_TYPE.equals(type)) {
	    generateSetIntegerProperty(context, data, bean, property, method, sw);
	    return;
	} else if (UIPresenter.FLOAT_TYPE.equals(type)) {
	    generateSetFloatProperty(context, data, bean, property, method, sw);
	    return;
	} else if (UIPresenter.DOUBLE_TYPE.equals(type)) {
	    generateSetDoubleProperty(context, data, bean, property, method, sw);
	    return;
	} else if (UIPresenter.DATE_TYPE.equals(type)) {
	    generateSetDateProperty(context, data, bean, property, method, sw);
	    return;
	} else if (UIPresenter.TIME_TYPE.equals(type)) {
	    generateSetTimeProperty(context, data, bean, property, method, sw);
	    return;
	} else if (UIPresenter.DATE_TIME_TYPE.equals(type)) {
	    generateSetDateTimeProperty(context, data, bean, property, method, sw);
	    return;
	}

	// TODO: Must impl other types/classes

    }
    
    public static String generateInstanceValue(String type, ScopeContext context, Object value) {
	if (type == null) {
	    return null;
	}
	
	if (UIPresenter.STRING_TYPE.equals(type)) {
	    return generateInstanceString(context, (String) value);
	} else if (UIPresenter.BOOLEAN_TYPE.equals(type)) {
	    return generateInstanceBoolean(context, (Boolean) value);
	} else if (UIPresenter.BYTE_TYPE.equals(type)) {
	    return generateInstanceByte(context, (Byte) value);
	} else if (UIPresenter.SHORT_TYPE.equals(type)) {
	    return generateInstanceShort(context, (Short) value);
	} else if (UIPresenter.INTEGER_TYPE.equals(type)) {
	    return generateInstanceInteger(context, (Integer) value);
	} else if (UIPresenter.FLOAT_TYPE.equals(type)) {
	    return generateInstanceFloat(context, (Float) value);
	} else if (UIPresenter.DOUBLE_TYPE.equals(type)) {
	    return generateInstanceDouble(context, (Double) value);
	} else if (UIPresenter.DATE_TYPE.equals(type)) {
	    return generateInstanceDate(context, (Date) value);
	} else if (UIPresenter.TIME_TYPE.equals(type)) {
	    return generateInstanceTime(context, (Date) value);
	} else if (UIPresenter.DATE_TIME_TYPE.equals(type)) {
	    return generateInstanceDateTime(context, (Date) value);
	}

	// TODO: Must impl other types/classes
	
	return null;

    }
    

    public static void generateSetValueProperty(String type, ScopeContext context, IData data, String bean, String property, SourceWriter sw) {
	generateSetValueProperty(type, context, data, bean, property, null, sw);
    }       

    ////
    
    
    public static String generateCreateData(ScopeContext context, String variable, IData data, SourceWriter sw) {
	
 	if (data == null) {
 	    throw new GenerateException("Date must be not null");
 	}
 	
 	IUIGenerator generator = getGenerator(data);
 	if (generator == null) {
 	    throw new GenerateException("Generator not found");
 	}
 	
 	String valueClass = generator.getClassName(data);
 	valueClass = prepareClassName(context, valueClass);
 	
 	boolean isOnlyConstructor = generator.isOnlyConstructor(data);
 	boolean needPopulate = !isOnlyConstructor; 
 	String instanceRef = needPopulate ? generator.getInstance(context, data, valueClass) : generator.getFullInstance(context, data, valueClass);
 	
 	boolean forceVariable = needPopulate;
 	boolean useVariable = variable != null || forceVariable; 
 	
 	// Create VariableContext
 	VariableContext variableContext = new VariableContext();
 	
 	// Initialize  VariableContext
 	variableContext.setVariable(variable);
 	variableContext.setVariableClass(valueClass);
 	variableContext.setInstanceRef(instanceRef);
 	variableContext.setForceVariable(forceVariable);
 	variableContext.setNeedPopulate(needPopulate);
 	
 	instanceRef = generateInstanceVariable(context, variableContext, data, sw);
 	if (useVariable) {
 	    generator.generatePopulate(context, data, variableContext.getVariable(), sw);
 	}
 	
 	return instanceRef;

     }
     
    /**
     * Generate create instance of data and modify VariableContext (generate variable if need, create or modify instanceRef)
     * @param context
     * @param variableContext
     * @param data
     * @param sw
     * @return
     */
    public static String generateInstanceVariable(ScopeContext context, VariableContext variableContext, IData data, SourceWriter sw) {
	
 	if (data == null) {
 	    throw new GenerateException("Date must be not null");
 	}
 	if (variableContext == null) {
 	    throw new GenerateException("VariableContext must be not null");
 	}
 	if (variableContext.getVariableClass() == null) {
 	    throw new GenerateException("Class of value must be not null");
 	}
 	if (variableContext.getInstanceRef() == null) {
 	    throw new GenerateException("Instance of value must be not null");
 	}

 	
 	// 1. Generate variable
 	// 2. Create (no variable)
 	// 3. Create Line (variable)
 	// 4. Populate (variable)
 	// 5. Return variable or instance (new <Constructor>(...))
 	
 	variableContext.setVariableClass(prepareClassName(context, variableContext.getVariableClass()));
 	boolean useVariable = false;
 	
 	if (variableContext.getVariable() == null) {
 	    if (variableContext.isForceVariable()) {
 		String variableTemplate = getVariableTemplate(variableContext.getVariableClass());
 		variableContext.setVariable(context.generateVariable(VariableScope.METHOD, variableTemplate));
 		useVariable = true;
 	    }
 	} else {
 	    
 	    String variableTemplate = null;
 	    
 	    if (variableContext.getVariable().startsWith("%")) {
 		
 		// Variable by template
 		
 		
 		if (variableContext.getVariable().equals("%")) {
 		    // Variable is '%' 
 		    // Generate variable name by type as template
 		    variableTemplate = getVariableTemplate(variableContext.getVariableClass());
 		} else {
 		    // Variable is '%name'
 		    // Generate variable name by name as template
 		    variableTemplate = variableContext.getVariable().substring(1);
 		}
 		variableContext.setVariable(context.generateVariable(VariableScope.METHOD, variableTemplate));
 		
 	    } else if (variableContext.getVariable().startsWith("$")) {
 		
 		// Variable codes:
 		// $	- by name
 		// $%	- by name, by type
 		// $#	- by name, check member
 		
 		// Variable by name of element
 		String name = (String) data.get("name");
 		if (variableContext.getVariable().equals("$")) {
 		    // Variable is '$'
 		    
 		    if (name == null) {
 			throw new GenerateException("Name of element must be not null when use variable by name");
 		    }
 		    
 		    // Set variable name by name of element
 		   variableContext.setVariable(name);

 		    
 		} else if (variableContext.getVariable().equals("$%")) {
 		    
 		    if (name == null) {
 			// Generate variable name by type as template
 			variableTemplate = getVariableTemplate(variableContext.getVariableClass());
 			variableContext.setVariable(context.generateVariable(VariableScope.METHOD, variableTemplate));
 		    } else {
 			// Set variable name by name of element
 			variableContext.setVariable(name);
 		    }
 		    
 		} else if (variableContext.getVariable().equals("$#")) {

 		    String member = (String) data.get("member");
 		    String predefined = (String) data.get("predefined");
 		    boolean isMember = "true".equals(member);
 		    boolean isPredefined = "true".equals(predefined);

 		    if (name == null  && (isPredefined || isMember)) {
 			throw new GenerateException("Name of element must be not null when element is predefined | member");
 		    }
 		    
 		    if (name == null) {
 			// Use '%'
 			// Generate variable name by type as template
 			variableTemplate = getVariableTemplate(variableContext.getVariableClass());
 			variableContext.setVariable(context.generateVariable(VariableScope.METHOD, variableTemplate));
 		    } else {
 			// Set variable name by name of element
 			variableContext.setVariable(name);
 		    }
 		    
 		    if (isPredefined) {
 			variableContext.setVariableClass(null);
 		    } else if (isMember) {
 			//TODO: add member to class
 			variableContext.setVariableClass(null);
 		    }

 		    
 		} else {
 		    // Variable is '$name'
 		    // Generate variable name by name as template
 		   variableContext.setVariable(variableContext.getVariable().substring(1));
 		}
 		
 		
 	    }
 	    
 	    useVariable = true;
 	}
 	
 	if (useVariable) {
 	    generateSetLine(variableContext.getVariableClass(), variableContext.getVariable(), variableContext.getInstanceRef(), sw);
 	    variableContext.setInstanceRef(variableContext.getVariable());
 	}
 	
 	return variableContext.getInstanceRef();

     }
    
    
     public static void generateSetData(ScopeContext context, String variable, IData data, String bean, String property, String method, SourceWriter sw) {
 	if (data == null) {
 	    throw new GenerateException("Date must be not null");
 	}
 	if (property == null) {
 	    throw new GenerateException("Property must be not null");
 	}
 	
 	String value = getString(data, property);
 	if (value == null) {
 	    return;
 	}
 	
 	IData valueData = createData(value);
 	if (valueData == null) {
 	    throw new GenerateException("Date of value must be not null");
 	}

 	String instanceRef = generateCreateData(context, variable, valueData, sw);
 	
 	// If name of setter method is empty then create the name by property
 	if (method == null) {
 	    method = createSetMethodName(property);
 	}
 	
 	sw.println((bean == null ? "" : (bean + ".")) + method + "(" + instanceRef + ");");
 	    
     }
     
     
     
    ////
    
    public static IUIGenerator getGenerator(IData data) {
	return UIGenerator.getGenerator(data, true);
    }

    public static IUIGenerator getGenerator(String baseType, String type) {
	return UIGenerator.getGenerator(baseType, type, true);
    }
    
    ////
    
    public static Object getValue(IData data, String property) {
	return UIBuilderHelper.getValue(data, property);
    }

    public static String getString(IData data, String property) {
	return (String) getValue(data, property);
    }

    public static String getRSString(IData data, String property) {
	// TODO: Must replace
	return getString(data, property);
    }

    public static Boolean getBoolean(IData data, String property) {
	return UIBuilderHelper.getBoolean(data, property);
    }

    public static Byte getByte(IData data, String property) {
	return UIBuilderHelper.getByte(data, property);
    }

    public static Short getShort(IData data, String property) {
	return UIBuilderHelper.getShort(data, property);
    }

    public static Integer getInteger(IData data, String property) {
	return UIBuilderHelper.getInteger(data, property);
    }

    public static Float getFloat(IData data, String property) {
	return UIBuilderHelper.getFloat(data, property);
    }

    public static Double getDouble(IData data, String property) {
	return UIBuilderHelper.getDouble(data, property);
    }

    // //

    public static Date getDate(IData data, String property) {
	return UIBuilderHelper.getDate(data, property);
    }

    public static Date getTime(IData data, String property) {
	return UIBuilderHelper.getTime(data, property);
    }

    public static Date getDateTime(IData data, String property) {
	return UIBuilderHelper.getDateTime(data, property);
    }

    // //

    public static Color getColor(IData data, String property) {
	return UIBuilderHelper.getColor(data, property);
    }

    public static Font getFont(IData data, String property) {
	return UIBuilderHelper.getFont(data, property);
    }

    // //

    public static HorizontalAlign getHorizontalAlign(IData data, String property) {
	return UIBuilderHelper.getHorizontalAlign(data, property);
    }

    public static VerticalAlign getVerticalAlign(IData data, String property) {
	return UIBuilderHelper.getVerticalAlign(data, property);
    }

    public static Direction getDirection(IData data, String property) {
	return UIBuilderHelper.getDirection(data, property);
    }

    public static Orientation getOrientation(IData data, String property) {
	return UIBuilderHelper.getOrientation(data, property);
    }

    public static LayoutRegion getLayoutRegion(IData data, String property) {
	return UIBuilderHelper.getLayoutRegion(data, property);
    }   
   
    ////
    
    public static IData getNode(IData data, String name) {
	return UIBuilderHelper.getNode(data, name);
    }
    
    public static List<IData> getChildrenOfNode(IData data, String name) {
  	return UIBuilderHelper.getChildrenOfNode(data, name);
    }
    

    public static IData createData() {
  	return UIBuilderHelper.createData();
    }
    
    public static IData createData(Map<String, Object> values) {
  	return UIBuilderHelper.createData(values);
    }

    
    /**
     * Parse string data and create <code>IData</code>
     * @param data
     * @return <code>IData</code>
     */
    public static IData createData(String data) {
	return UIBuilderHelper.createData(data);
    }
    
    /**
     * Create <code>IData</code> by <code>DataContainer</code>
     * @param dataContainer
     * @return <code>IData</code>
     */
    public static IData createData(DataContainer dataContainer) {
	return UIBuilderHelper.createData(dataContainer);
    }

}
