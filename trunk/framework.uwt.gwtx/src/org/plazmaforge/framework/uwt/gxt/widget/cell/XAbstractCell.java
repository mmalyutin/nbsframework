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

import java.util.Set;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

/**
 * 
 * @author ohapon
 *
 * @param <C>
 */
public abstract class XAbstractCell<C>  extends AbstractCell<C> implements XCell<C> {

    private XCellRenderer cellRenderer;
    
    public XAbstractCell(Set<String> consumedEvents) {
	super(consumedEvents);
    }

    public XAbstractCell(String... consumedEvents) {
	super(consumedEvents);
    }

    public XCellRenderer getCellRenderer() {
        return cellRenderer;
    }

    public void setCellRenderer(XCellRenderer cellRenderer) {
        this.cellRenderer = cellRenderer;
    }

    public abstract String formatValue(C value);
    
    public void render(Context context, C value, SafeHtmlBuilder sb) {
	if (cellRenderer != null) {
	    renderCellValue(context, value, sb);
	    return;
	}
	if (value == null) {
	    renderNullValue(sb);
	    return;
	}
	renderFormatValue(context, value, sb);
    }
    
    protected void renderCellValue(Context context, C value, SafeHtmlBuilder sb) {
	cellRenderer.render(context, value, sb);
    }
    
    protected void renderFormatValue(Context context, C value, SafeHtmlBuilder sb) {
	String text = formatValue(value);
	if (text == null) {
	    return;
	}
	sb.append(SafeHtmlUtils.fromString(text));
    }
    
    protected void renderNullValue(SafeHtmlBuilder sb) {
	// do nothing by default
    }
    
    
}
