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
import org.plazmaforge.framework.uwt.generator.AbstractGenerator;
import org.plazmaforge.framework.uwt.generator.GeneratorContext;
import org.plazmaforge.framework.uwt.generator.ScopeContext;
import org.plazmaforge.framework.uwt.generator.SourceWriter;
import org.plazmaforge.framework.uwt.generator.UIGenerator;

public abstract class AbstractLayoutDataGenerator extends AbstractGenerator  {

    @Override
    public String generateClass(GeneratorContext context, IData data) {
	// do nothing by default
	return null;
    }

    protected String getDefaultLayoutDataClassName(String baseType, String type) {
	if (type == null) {
	    return getDefaultClassName(baseType, type);
	}
	return UIGenerator.UWT_PACKAGE + ".layout." + type;
    }
    
    @Override
    public void generatePopulate(ScopeContext context, IData data,  String bean, SourceWriter sw) {
	// do nothing
    }

   

}
