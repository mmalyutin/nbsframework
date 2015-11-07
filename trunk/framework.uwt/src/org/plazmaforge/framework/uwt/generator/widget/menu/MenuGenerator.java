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
package org.plazmaforge.framework.uwt.generator.widget.menu;

import java.util.List;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.builder.UIBuilder;
import org.plazmaforge.framework.uwt.generator.ScopeContext;
import org.plazmaforge.framework.uwt.generator.SourceWriter;

/**
 * @author ohapon
 *
 */
public class MenuGenerator extends MenuItemGenerator {

    @Override
    public void generatePopulate(ScopeContext context, IData data, String bean, SourceWriter sw) {
	super.generatePopulate(context, data, bean, sw);

	List<IData> children = (List<IData>) getValue(data, UIBuilder.SYS_PROPERTY_CHILDREN);
	generateMenuChildren(context, bean, children, sw);
    }
    
    protected void generateMenuChildren(ScopeContext context, String bean,
	    List<IData> children, SourceWriter sw) {
	if (children == null || children.isEmpty()) {
	    return;
	}
	for (IData child : children) {
	    sw.println();
	    String instanceRef = generateCreateData(context, "$#", child, sw);
	    if (instanceRef == null) {
		continue;
	    }
	    sw.println((bean == null ? "" : (bean + ".")) + "addItem(" + instanceRef + ");");
	}
    }
}
