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
package org.plazmaforge.framework.uwt.generator.widget.tool;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.generator.GeneratorContext;
import org.plazmaforge.framework.uwt.generator.ScopeContext;
import org.plazmaforge.framework.uwt.generator.SourceWriter;
import org.plazmaforge.framework.uwt.generator.UIGenerator;
import org.plazmaforge.framework.uwt.generator.widget.AbstractItemGenerator;
import org.plazmaforge.framework.uwt.widget.tool.ToolItem;

/**
 * @author ohapon
 *
 */
public class ToolItemGenerator extends AbstractItemGenerator {

    @Override
    public boolean accept(String type) {
	return false;
    }

    @Override
    public String generateClass(GeneratorContext context, IData data) {
	return null;
    }

    protected String getDefaultClassName(String baseType, String type) {
	return baseType == null ? null : UIGenerator.UWT_WIDGET_PACKAGE + ".tool." + baseType;
    }
    
    @Override
    public void generatePopulate(ScopeContext context, IData data, String bean, SourceWriter sw) {
	super.generatePopulate(context, data, bean, sw);
	generateSetBooleanProperty(context, data, bean, ToolItem.PROPERTY_ENABLED, sw);
	generateSetRSStringProperty(context, data, bean, ToolItem.PROPERTY_TOOL_TIP, "setToolTip", sw);
	generateSetStringProperty(context, data, bean, ToolItem.PROPERTY_COMMAND, sw);
    }

    @Override
    protected void generateAddListener(ScopeContext context, String bean, String type, String handleString, SourceWriter sw) {
	if (eq(type, Events.Selection)) {
	    generateAddSelectionListener(context, bean, handleString, sw);
	    return;
	}
	
	super.generateAddListener(context, bean, type, handleString, sw);
    }
}
