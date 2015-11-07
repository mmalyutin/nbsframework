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
import org.plazmaforge.framework.uwt.generator.SourceWriter;
import org.plazmaforge.framework.uwt.generator.UIGeneratorHelper;
import org.plazmaforge.framework.uwt.layout.BoxLayout;
import org.plazmaforge.framework.uwt.widget.Style.Orientation;

/**
 * 
 * @author ohapon
 *
 */
public class BoxLayoutGenerator extends AbstractLayoutGenerator {

    @Override
    public String getClassName(String baseType, String type) {
	return getDefaultLayoutClassName(baseType, type);
    }

    @Override
    public boolean accept(String type) {
	return type == null ? false: (UIBuilder.BOX_LAYOUT_TYPE.equals(type) || "BoxLayout".equals(type));
    }
    
    @Override
    public boolean isOnlyConstructor(IData data) {
	// Always only constructor
	// TODO: Need populate margins outside
	return true;
    }
    
    @Override
    public String getInstance(ScopeContext context, IData data, String className) {
	return  "new " + className + "()";
    }

    @Override
    public String getFullInstance(ScopeContext context, IData data, String className) {
	Orientation orientation = getOrientation(data);
	if (orientation == null) {
	    return getInstance(context, data, className);
	}
	return "new " + className + "(" + UIGeneratorHelper.generateInstanceOrientation(context, orientation) + ")";
    }
    
    @Override
    public void generatePopulate(ScopeContext context, IData data,  String bean, SourceWriter sw) {
	// TODO: Populate margins
    }

    protected Orientation getOrientation(IData data) {
	return getOrientation(data, BoxLayout.PROPERTY_ORIENTATION);
    }
}
