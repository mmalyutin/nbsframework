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

package org.plazmaforge.framework.uwt.generator.layout;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.builder.UIBuilder;
import org.plazmaforge.framework.uwt.generator.ScopeContext;
import org.plazmaforge.framework.uwt.generator.UIGeneratorHelper;
import org.plazmaforge.framework.uwt.layout.GridData;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;

public class GridDataGenerator extends AbstractLayoutDataGenerator  {

    @Override
    public boolean accept(String type) {
	return UIBuilder.GRID_DATA_TYPE.equals(type) || "GridData".equals(type);
    }

    @Override
    public String getClassName(String baseType, String type) {
	return getDefaultLayoutDataClassName(baseType, type);
    }
    
    @Override
    public boolean isOnlyConstructor(IData data) {
	// Always only constructor
	return true;
    }

    @Override
    public String getInstance(ScopeContext context, IData data, String className) {
	return  "new " + className + "()";
    }

    @Override
    public String getFullInstance(ScopeContext context, IData data, String className) {
	
	// Span
	Integer columnSpan = getInteger(data, GridData.PROPERTY_COLUMN_SPAN);
	Integer rowSpan = getInteger(data, GridData.PROPERTY_ROW_SPAN);
	
	// Align
	HorizontalAlign horizontalAlign = getHorizontalAlign(data, GridData.PROPERTY_HORIZONTAL_ALIGN);
	VerticalAlign verticalAlign = getVerticalAlign(data, GridData.PROPERTY_VERTICAL_ALIGN);
	
	// Flex
	Boolean horizontalFlex = getBoolean(data, GridData.PROPERTY_HORIZONTAL_FLEX);
	Boolean verticalFlex = getBoolean(data, GridData.PROPERTY_VERTICAL_FLEX);
	

	if (columnSpan == null 
		&& rowSpan == null 
		&& horizontalAlign == null 
		&& verticalAlign == null 
		&& horizontalFlex == null 
		&& verticalFlex == null) {
	    return getInstance(context, data, className);
	}
	
	String instanceColumnSpan = columnSpan == null ? null : columnSpan.toString();
	String instanceRowSpan = rowSpan == null ? null : rowSpan.toString();
	String instanceHorizontalAlign = horizontalAlign == null ? null : UIGeneratorHelper.generateInstanceHorizontalAlign(context, horizontalAlign);
	String instanceVerticalAlign = verticalAlign == null ? null : UIGeneratorHelper.generateInstanceVerticalAlign(context, verticalAlign);
	String instanceHorizontalFlex = horizontalFlex == null ? null : horizontalFlex.toString();
	String instanceVerticalFlex = verticalFlex == null ? null : verticalFlex.toString();
	
	String[] args = new String[] {instanceColumnSpan, instanceRowSpan, instanceHorizontalAlign, instanceVerticalAlign, instanceHorizontalFlex, instanceVerticalFlex};
	
	
	// All arguments
	if (instanceColumnSpan != null 
		&& instanceRowSpan != null 
		&& instanceHorizontalAlign != null 
		&& instanceVerticalAlign != null 
		&& instanceHorizontalFlex != null 
		&& instanceVerticalFlex != null) {
	    return generateConstructor(className, args); 
	}

	// 4 arguments (Column/Row span, Horizontal/Vertical align)
	if (instanceColumnSpan != null 
		&& instanceRowSpan != null 
		&& instanceHorizontalAlign != null 
		&& instanceVerticalAlign != null) {
	    
	    if (instanceHorizontalFlex == null && instanceVerticalFlex == null) {
		return generateConstructor(className, args, 4); 
	    }
	    
	}

	// 2 arguments (Column/Row Span)
	if (instanceColumnSpan != null 
		&& instanceRowSpan != null) {
	    
	    if (instanceHorizontalAlign == null && instanceVerticalAlign == null && instanceHorizontalFlex == null && instanceVerticalFlex == null) {
		return generateConstructor(className, new String[] {instanceColumnSpan, instanceRowSpan}); // 2 arguments
	    }
	}

	// 2 arguments (Horizontal/Vertical Align)
	if (instanceHorizontalAlign != null 
		&& instanceVerticalAlign != null) {
	    
	    if (instanceColumnSpan == null && instanceRowSpan == null && instanceHorizontalFlex == null && instanceVerticalFlex == null) {
		return generateConstructor(className, new String[] {instanceHorizontalAlign, instanceVerticalAlign}); // 2 arguments
	    }
	}

	// 1 argument (Horizontal Align)
	if (instanceHorizontalAlign != null) {
	    if (instanceVerticalAlign == null && instanceColumnSpan == null && instanceRowSpan == null && instanceHorizontalFlex == null && instanceVerticalFlex == null) {
		return generateConstructor(className, new String[] {instanceHorizontalAlign}); // 1 argument
	    }
	}

	// 1 argument (Vertical Align)
	if (instanceVerticalAlign != null) {
	    if (instanceHorizontalAlign == null && instanceColumnSpan == null && instanceRowSpan == null && instanceHorizontalFlex == null && instanceVerticalFlex == null) {
		return generateConstructor(className, new String[] {instanceVerticalAlign}); // 1 argument
	    }
	}

	
	// Other
	// Set default values
	// All arguments
	instanceColumnSpan = instanceColumnSpan == null ? "1" : instanceColumnSpan;
	instanceRowSpan = instanceRowSpan == null ? "1" : instanceRowSpan;
	
	instanceHorizontalAlign = instanceHorizontalAlign == null ? (className + ".DEFAULT_HORIZONTAL_ALIGN") : instanceHorizontalAlign;
	instanceVerticalAlign = instanceVerticalAlign == null ? (className + ".DEFAULT_VERTICAL_ALIGN") : instanceVerticalAlign;
	
	instanceHorizontalFlex = instanceHorizontalFlex == null ? "false" : instanceHorizontalFlex;
	instanceVerticalFlex = instanceVerticalFlex == null ? "false" : instanceVerticalFlex;
	return generateConstructor(className, new String[] {instanceColumnSpan, instanceRowSpan, instanceHorizontalAlign, instanceVerticalAlign, instanceHorizontalFlex, instanceVerticalFlex}); // all arguments
	
    }
    
    protected String generateConstructor(String className) {
	return generateConstructor(className, null, 0);
    }
    
    protected String generateConstructor(String className, String[] args) {
	return generateConstructor(className, args, args == null ? 0 : args.length);
    }
    
    protected String generateConstructor(String className, String[] args, int count) {
	if (className == null) {
	    return null;
	}
	StringBuffer buf = new StringBuffer();
	buf.append("new " + className + "(");
	if (args != null && count >= 0) {
	    for (int i = 0; i < count; i++) {
		if (i > 0) {
		    buf.append(", ");
		}
		buf.append(args[i]);
	    }
	}
	buf.append(")");
	return buf.toString();
    }
    
}
