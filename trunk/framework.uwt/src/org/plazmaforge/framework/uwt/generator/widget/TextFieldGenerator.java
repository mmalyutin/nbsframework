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
package org.plazmaforge.framework.uwt.generator.widget;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.builder.UIBuilder;
import org.plazmaforge.framework.uwt.generator.ScopeContext;
import org.plazmaforge.framework.uwt.generator.SourceWriter;
import org.plazmaforge.framework.uwt.widget.TextBox;

/**
 * @author ohapon
 *
 */
public class TextFieldGenerator extends AbstractFieldGenerator {

    @Override
    public boolean accept(String type) {
	return eq(type, UIBuilder.TEXT_FIELD_TYPE);
    }    

    protected void generateSetValue(ScopeContext context, IData data, String bean, SourceWriter sw) {
	generateSetRSStringProperty(context, data, bean, TextBox.PROPERTY_VALUE, sw);
	generateSetRSStringProperty(context, data, bean, TextBox.PROPERTY_TEXT, sw); // Property TEXT is priority than property VALUE
    }
    
}
