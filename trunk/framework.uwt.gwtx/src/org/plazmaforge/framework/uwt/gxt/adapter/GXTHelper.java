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

package org.plazmaforge.framework.uwt.gxt.adapter;


import java.util.Date;

import org.plazmaforge.framework.core.type.TypeUtils;
import org.plazmaforge.framework.util.StringUtils;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.gxt.adapter.viewer.ProviderModelData;
import org.plazmaforge.framework.uwt.gxt.adapter.viewer.XLabelProvider;
import org.plazmaforge.framework.uwt.gxt.adapter.viewer.XModelKeyProvider;
import org.plazmaforge.framework.uwt.gxt.adapter.viewer.XValueProvider;
import org.plazmaforge.framework.uwt.gxt.core.XBeanModelFactory;
import org.plazmaforge.framework.uwt.gxt.core.XBeanModelLookup;
import org.plazmaforge.framework.uwt.gxt.data.BaseModel;
import org.plazmaforge.framework.uwt.gxt.data.BeanModel;
import org.plazmaforge.framework.uwt.gxt.data.ModelData;
import org.plazmaforge.framework.uwt.util.StorageUtils;
import org.plazmaforge.framework.uwt.util.UWTHelper;
import org.plazmaforge.framework.uwt.widget.CellEditor;
import org.plazmaforge.framework.uwt.widget.DefaultCellEditor;
import org.plazmaforge.framework.uwt.widget.table.TableColumn;

import com.sencha.gxt.core.client.util.IconHelper;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.DateTimePropertyEditor;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.TimeField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.BigDecimalPropertyEditor;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.BigIntegerPropertyEditor;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.DoublePropertyEditor;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.FloatPropertyEditor;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.IntegerPropertyEditor;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.LongPropertyEditor;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.ShortPropertyEditor;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;


/**
 * 
 * @author ohapon
 *
 */
public class GXTHelper {

    
    
    
    
    /**
     * Create special GXT Model Factory by Java Class
     * @param beanClass
     * @return
     */
    public static XBeanModelFactory getBeanModelFactory(Class<?> beanClass) {
	if (beanClass == null) {
	    return null;
	}
	
	//TODO: DISABLE
	//Primitive types and wrappers have not BeanModelFactory 
	//if (beanClass.getName().startsWith(("java.lang."))) {
	//    return null;
	//}
	
	return XBeanModelLookup.get().getFactory(beanClass);
    }
    
    /**
     * Create special GXT Model by Java Bean
     * @param bean
     * @return
     */
    public static BeanModel createBeanModel(Object bean) {
	if (bean == null) {
	    return null;
	}
	if (bean instanceof BeanModel) {
	    return (BeanModel) bean;
	}
	XBeanModelFactory factory = getBeanModelFactory(bean.getClass());
	if (factory == null) {
	    System.err.println("XBeanModelFactory not found of class " + bean.getClass());
	    return null;
	}
	BeanModel model = factory.createModel(bean);
	if (model == null) {
	    System.err.println("Can't create model by bean " + bean);
	}
	return model;
    }
    
    /**
     * Get bean from model
     * @param model
     * @return
     */
    public static Object getBean(ModelData model) {
	if (model == null) {
	    return null;
	}
	if (model instanceof BeanModel) {
	    return ((BeanModel) model).getBean();
	}
	if (model instanceof ProviderModelData) {
	    return ((ProviderModelData) model).getBean();
	}
	
	//DISABLE:MIGRATION
	//if (model instanceof GXTTreeDataModel) {
	//    return getBean(((GXTTreeDataModel) model).getModel());
	//}
	
	
	if (model instanceof BaseModel) {
	    return null; //((BaseModel) model).get("toString");
	}
	return null;
    }
    
    
    /**
     * Return value of model by property
     * @param model
     * @param property
     * @return
     */
    public static Object getValue(ModelData model, String property) {
	return model == null ? null : model.get(property);
    }
    
    
    /**
     * Add style attribute to base style
     * @param baseStyle
     * @param attribute
     * @param value
     * @return
     */
    public static String applyStyle(String baseStyle, String attribute, String value) {
	String style = null;
	if (attribute != null && value != null) {
	    style = attribute + ": " + value;
	}
	return applyStyle(baseStyle, style);
    }

    /**
     * Add style to base style
     * @param baseStyle
     * @param style
     * @return
     */
    public static String applyStyle(String baseStyle, String style) {
	if (StringUtils.isEmpty(style, true)) {
	    return baseStyle;
	}
	baseStyle = StringUtils.nullIfEmpty(baseStyle, true);
	if (baseStyle != null && !baseStyle.endsWith(";")) {
	    baseStyle += "; ";
	}
	return (baseStyle == null ? "" : baseStyle) + style + "; ";
    }
    
    public static SafeUri toSafeUri(String s) {
	return UriUtils.fromString(s);
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // RESOURCES
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    

    /**
     * Return hex presentation of color
     * @param color
     * @return
     */
    public static String getColorString(Color color) {
	return  color == null ? null : "#" + color.toHexString();
    }

    public static String getRGBColorString(Color color) {
	return  color == null ? null : "rgb(" + color.getRed() + ", " + color.getGreen() + ", " + color.getBlue() + ")";
    }

    public static String getRGBAColorString(Color color) {
	return  color == null ? null : color.toRGBAString();
    }

    public static String getFontStyle(Font font) {
	if (font == null) {
	    return null;
	}
	String fontString = getFontString(font);
	if (fontString == null) {
	    return null;
	}
	String name = normalizeFontName(font);
	int size = normalizeFontSize(font);
	
	// Empty font
	if (name == null && size == 0 && font.isNormal()) {
	    return null;
	}
	String style = " font: " + fontString;
	return  style + ";";
    }

    public static String getFontString(Font font) {
	if (font == null) {
	    return null;
	}
	String name = normalizeFontName(font);
	int size = normalizeFontSize(font);
	
	// Empty font
	if (name == null && size == 0 && font.isNormal()) {
	    return null;
	}
	// TODO: Use StringBuffer
	
	String style = ""; // TODO BAD
	if (font.isBold()) {
	    style += " bold";
	}
	if (font.isItalic()) {
	    style += " italic";
	}
	if (size > 0) {
	    style += " " + size + "px";
	}
	
	if (name != null) {
	    style += " " + name;
	}
	//return  style + ";";
	return  style;
    }

    public static boolean isEmpty(Font font) {
	if (font == null) {
	    return true;
	}
	String name = normalizeFontName(font);
	int size = normalizeFontSize(font);
	return name == null && size == 0 && font.isNormal(); 
    }
    
   
    private static String normalizeFontName(Font font) {
	if (font == null) {
	    return null;
	}
	String name = font.getName();
	if (name != null) {
	    name = name.trim();
	    if (name.isEmpty() /*|| name.equals("Default")*/) {
		name = null;
	    }
	}
	return name;
    }
    
    private static int normalizeFontSize(Font font) {
	if (font == null) {
	    return 0;
	}
	int size = font.getSize();
	return size < 0 ? 0 : size;
    }


    public static ImageResource createImage(UIObject element, Image image) {
	if (image == null) {
	    return null;
	}
	String path = image.getPath();
	return createImage(element, path);
    }

    public static ImageResource createImage(UIObject element, String path) {
	if (path == null) {
	    return null;
	}
	String storage = StorageUtils.getImageStorage(element, path);
	return createImage(storage, path);
    }
    
    public static ImageResource createImage(String storage, String path) {
	String fullPath = StorageUtils.getPath(storage, path);
	return createImage(fullPath);
    }

    public static ImageResource createImage(String path) {
  	return createImage(path, 16, 16);
    }
    
    public static ImageResource createImage(String path, int width, int height) {
	return path == null ? null : IconHelper.getImageResource(toSafeUri(path), width, height);
    }

    
    public static NumberFormat getNumberFormat(String pattern) {
	if (pattern == null) {
	    return null;
	}
	return NumberFormat.getFormat(pattern);
    }
    
    public static NumberPropertyEditor<?> createNumberPropertyEditor(Class<?> type, String format) {
   	if (type == null) {
   	    // My be BigDecimal by default
	    type = Double.class;
	}   	
   	// Get simple class name (Double, Float, Long, Integer...)
   	String className =  TypeUtils.getType(type.getName());
   	NumberFormat numberFormat = getNumberFormat(format);

   	if ("BigDecimal".equals(className)) {
   	    return numberFormat == null ? new BigDecimalPropertyEditor() : new BigDecimalPropertyEditor(numberFormat);
   	} else if ("BigInteger".equals(className)) {
    	    return numberFormat == null ? new BigIntegerPropertyEditor() : new BigIntegerPropertyEditor(numberFormat);
    	    
   	} else if ("Double".equals(className)) {
   	    return numberFormat == null ? new DoublePropertyEditor() : new DoublePropertyEditor(numberFormat);
   	} else if ("Float".equals(className)) {
   	    
   	    return numberFormat == null ? new FloatPropertyEditor() : new FloatPropertyEditor(numberFormat);
   	} else if ("Long".equals(className)) {
   	    return numberFormat == null ? new LongPropertyEditor() : new LongPropertyEditor(numberFormat);
   	} else if ("Integer".equals(className)) {
   	    return numberFormat == null ? new IntegerPropertyEditor() : new IntegerPropertyEditor(numberFormat);
   	} else if ("Short".equals(className)) {
   	    return numberFormat == null ? new ShortPropertyEditor() : new ShortPropertyEditor(numberFormat);
   	}
   	
   	return new DoublePropertyEditor();
    }
    
    public static DateTimePropertyEditor createDateTimePropertyEditor(String format) {
	return format == null ? new DateTimePropertyEditor() : new DateTimePropertyEditor(format);
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // FACTORY
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    // DISABLE:MIGRATION
//    public static com.sencha.gxt.widget.core.client.grid.CellEditor createCellEditor(com.sencha.gxt.widget.core.client.grid.Grid xGrid, TableColumn tableColumn, CellEditor cellEditor) {
//	if (cellEditor == null) {
//	    return null; 
//	}
//	
//	// Create native CellEditor by data type. Only for DefaultCellEditor
//	if (cellEditor instanceof DefaultCellEditor) {
//	    
//	    String dataType = cellEditor.getDataType();
//	    // If data type is unknown then use data type of column
//	    if (dataType == null) {
//		dataType = tableColumn.getDataType();
//	    }
//	    
//	    Class<?> dataClass = TypeUtils.getClass(dataType);
//	    
//	    String format = tableColumn.getFormat(); 
//	    
//	    // TODO: Only for String, Text, boolean data type
//	    if (dataType == null || TypeUtils.isLikeStringType(dataType)) {
//		return createCellEditor(new TextField<String>());
//	    } else if (TypeUtils.isBooleanType(dataType)) {
//		return createCellEditor(new CheckBox());
//	    } else if (TypeUtils.isLikeNumberType(dataType)) {
//		NumberField xNumberField = new NumberField();
//		xNumberField.setPropertyEditorType(dataClass);
//		String numberFormat = format;
//		if (numberFormat == null) {
//		    numberFormat = UWTHelper.getNumberFormat(tableColumn);
//		}
//		if (numberFormat != null) {
//		    xNumberField.setFormat(NumberFormat.getFormat(numberFormat));
//		}
//		return createCellEditor(xNumberField);
//		
//	    } else if (TypeUtils.isDateType(dataType)) {
//		DateField xDateField = new DateField();
//		String dateFormat = format;
//		if (dateFormat == null) {
//		    dateFormat = UWTHelper.getDateFormat(tableColumn);
//		}
//		if (dateFormat != null) {
//		    xDateField.getPropertyEditor().setFormat(DateTimeFormat.getFormat(dateFormat));
//		}
//		return createCellEditor(xDateField);
//		
//	    } else if (TypeUtils.isTimeType(dataType)) {
//		TimeField xTimeField = new TimeField();
//		String timeFormat = format;
//		if (timeFormat == null) {
//		    timeFormat = UWTHelper.getTimeFormat(tableColumn);
//		}
//		if (timeFormat != null) {
//		    xTimeField.setFormat(DateTimeFormat.getFormat(timeFormat));
//		}		
//		return createCellEditor(xTimeField);
//		
//	    } else if (TypeUtils.isDateTimeType(dataType) || TypeUtils.isTimestampType(dataType)) {
//		DateField xDateTimeField = new DateField();
//		String dateTimeFormat = format;
//		if (dateTimeFormat == null) {
//		    dateTimeFormat = UWTHelper.getDateTimeFormat(tableColumn);
//		}
//		if (dateTimeFormat != null) {
//		    xDateTimeField.getPropertyEditor().setFormat(DateTimeFormat.getFormat(dateTimeFormat));
//		}
//		return createCellEditor(xDateTimeField);
//	    }
//
//	    return null;
//	}
//	
//	//TODO: Must implemented other native CellEditor by class of UWT CellEditor 
//	return null;
//	
//    }
//    
//    public static <D> com.sencha.gxt.widget.core.client.grid.CellEditor createCellEditor(com.sencha.gxt.widget.core.client.form.Field<D> filed) {
//	return new com.sencha.gxt.widget.core.client.grid.CellEditor(filed);
//    }
    
    
    
    //// GXT ////
    
    public static <T> com.sencha.gxt.data.shared.ModelKeyProvider<T> createDefaultModelKeyProvider(Class<T> type) {
	return new com.sencha.gxt.data.shared.ModelKeyProvider<T>() {
	    @Override
	    public String getKey(T item) {
		if (item == null) {
		    return null;
		}
		return item.toString();
	    }
	};

    }
       
    public static <T> ListStore<T> createDefaultListStore(Class<T> type) {
	return new ListStore<T>(createDefaultModelKeyProvider(type));
    }

    public static <T> com.sencha.gxt.data.shared.LabelProvider<T> createDefaultLabelProvider(Class<T> type) {
 	return new com.sencha.gxt.data.shared.LabelProvider<T>() {
     
 	    @Override
 	    public String getLabel(T item) {
 		if (item == null) {
 		    return null;
 		}
 		return item.toString();
 	    }
 	    
 	};
    }
    
    //// Model //// 
    
    public static XModelKeyProvider createXDefaultModelKeyProvider() {
	return new XModelKeyProvider();
    }
       
    public static ListStore<ModelData> createXDefaultListStore() {
  	return new ListStore<ModelData>(createXDefaultModelKeyProvider());
    }    
    
    public static XLabelProvider createXLabelProvider(String property) {
 	return new XLabelProvider(property);
    }
    
    
    public static XValueProvider createXValueProvider(String property) {
 	return new XValueProvider(property);
     }    
    
}
