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
package org.plazmaforge.framework.uwt.builder.layout;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.builder.UIBuilder;
import org.plazmaforge.framework.uwt.layout.BorderData;
import org.plazmaforge.framework.uwt.widget.LayoutData;
import org.plazmaforge.framework.uwt.widget.Style.LayoutRegion;

/**
 * @author ohapon
 *
 */
public class BorderDataBuilder extends AbstractLayoutDataBuilder {

    @Override
    public boolean accept(String type) {
	return UIBuilder.BORDER_DATA_TYPE.equals(type) || "BorderData".equals(type);
    }
    
    protected LayoutData createLayoutData(IData data) {
	LayoutRegion layoutRegion = getLayoutRegion(data, BorderData.PROPERTY_REGION);
	return layoutRegion == null ? new BorderData() : new BorderData(layoutRegion);
    }

    
}
