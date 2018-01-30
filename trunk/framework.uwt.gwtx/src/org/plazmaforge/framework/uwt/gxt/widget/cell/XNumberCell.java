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
package org.plazmaforge.framework.uwt.gxt.widget.cell;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.text.shared.SafeHtmlRenderer;
import com.sencha.gxt.cell.core.client.NumberCell;

/**
 * 
 * @author ohapon
 *
 * @param <N>
 */
public class XNumberCell<N extends Number> extends NumberCell<N> {

    public XNumberCell() {
	super();
    }

    public XNumberCell(NumberFormat format, SafeHtmlRenderer<String> renderer) {
	super(format, renderer);
    }

    public XNumberCell(NumberFormat format) {
	super(format);
    }

    public XNumberCell(SafeHtmlRenderer<String> renderer) {
	super(renderer);
    }

}
