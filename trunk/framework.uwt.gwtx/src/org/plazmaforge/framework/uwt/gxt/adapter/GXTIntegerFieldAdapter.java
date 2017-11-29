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

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.IntegerField;


/**
 * 
 * @author ohapon
 *
 */
public class GXTIntegerFieldAdapter extends GXTNumberFieldAdapter {

    @Override
    public Object createDelegate(UIObject parent, UIObject element) {
	IntegerField integerField = (IntegerField) element;
	//String format = integerField.getFormat();
	
	com.sencha.gxt.widget.core.client.form.NumberField<?> xNumberField = createNumberField("Integer", null);
	//delegate.setStyleAttribute("align", "right");
	addChild(getContent(parent.getDelegate()), xNumberField, element); // Add to parent
	return xNumberField;
    }
    

}
