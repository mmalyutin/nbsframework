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
package org.plazmaforge.framework.uwt.generator.widget.panel;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.builder.UIBuilder;
import org.plazmaforge.framework.uwt.generator.ScopeContext;
import org.plazmaforge.framework.uwt.generator.SourceWriter;
import org.plazmaforge.framework.uwt.generator.UIGeneratorHelper;
import org.plazmaforge.framework.uwt.layout.BoxLayout;
import org.plazmaforge.framework.uwt.widget.Style.Orientation;

/**
 * @author ohapon
 *
 */
public class SplitPanelGenerator extends PanelGenerator {

    @Override
    public boolean accept(String type) {
	return eq(type, UIBuilder.SPLIT_PANEL_TYPE);
    }
    
    @Override
    public String getInstance(ScopeContext context, IData data, String className) {
	Orientation orientation = getOrientation(data);
	if (orientation == null) {
	    return "new " + className + "()";
	}
	return "new " + className + "(" + UIGeneratorHelper.generateInstanceOrientation(context, orientation) + ")";
    }
    
    @Override
    protected void generateSetLayout(ScopeContext context, IData data, String bean, SourceWriter sw) {
	// Layout is not supported
    }
    
    protected Orientation getOrientation(IData data) {
	return getOrientation(data, BoxLayout.PROPERTY_ORIENTATION);
    }

}
