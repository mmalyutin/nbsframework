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
package org.plazmaforge.framework.report.fill;

import org.plazmaforge.framework.report.model.base.PageFormat;
import org.plazmaforge.framework.report.model.base.PageSetup;
import org.plazmaforge.framework.report.model.base.Size;
import org.plazmaforge.framework.util.StringUtils;

/**
 * @author ohapon
 *
 */
public class AbstractTemplateFiller extends AbstractFiller {

    
    
    protected Size getPageSize(PageSetup pageSetup) {
	
	if (pageSetup == null) {
	    return getPageSize(PageSetup.DEFAULT_PAGE_FORMAT);
	}
	
	String format = pageSetup.getFormat();
	format = StringUtils.normalizeString(format);
	if (format == null || format.equalsIgnoreCase("Custom")) {
	    return pageSetup.getSize();
	}

	PageFormat pageFormat = PageFormat.find(format);
	
	return getPageSize(pageFormat);
    }
    
    protected Size getPageSize(PageFormat pageFormat) {
	if (pageFormat == null) {
	    pageFormat = PageSetup.DEFAULT_PAGE_FORMAT;
	}
	int width = pageFormat.getWidth();
	int height = pageFormat.getHeight();
	Size size = new Size((int) PageFormat.toPT(width), (int) PageFormat.toPT(height));
	return size;
    }
    
}
