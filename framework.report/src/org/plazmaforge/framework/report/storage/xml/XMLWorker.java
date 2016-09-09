/*
 * Copyright (C) 2012-2015 Oleh Hapon ohapon@users.sourceforge.net
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

package org.plazmaforge.framework.report.storage.xml;

import org.plazmaforge.framework.core.data.formatter.FormatterManager;
import org.plazmaforge.framework.uwt.builder.formatter.type.ColorFormatter;

public class XMLWorker {

    protected static final ColorFormatter COLOR_FORMATTER = new ColorFormatter();
    
    private FormatterManager formatterManager;
    
    ////

    protected  FormatterManager getFormatterManager() {
	if (formatterManager == null) {
	    formatterManager = new FormatterManager();
	    formatterManager.registerDefaultFormatters();
	}
	return formatterManager;
    }
    
    protected String normalizeString(String str) {
	if (str == null) {
	    return null;
	}
	str = str.trim();
	return str.isEmpty() ? null : str;
    }


}
