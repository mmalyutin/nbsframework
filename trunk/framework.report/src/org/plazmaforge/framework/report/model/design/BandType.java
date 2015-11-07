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
package org.plazmaforge.framework.report.model.design;

/**
 * @author ohapon
 *
 */
public enum BandType {

    ReportHeader(true, false),
    PageHeader(true, true),		// fixed height
    ColumnHeader(true, false),
    GroupHeader(true, false),
    Detail(true, false),
    GroupFooter(true, false),
    ColumnFooter(true, false),
    PageFooter(true, true),		// fixed height
    ReportFooter(true, false),
    
    ReportBackground(false, false),
    NoData(false, false);
    
    private boolean structured;
    
    private boolean fixed;

    private BandType(boolean structured, boolean fixed) {
	this.structured = structured;
	this.fixed = fixed;
    }
    
    public boolean isStructured() {
        return structured;
    }

    public boolean isFixed() {
        return fixed;
    }

    public static BandType find(String type) {
	if (type == null) {
	    return null;
	}
	for (BandType bandType: values()) {
	    if (type.equals(bandType.name())) {
		return bandType;
	    }
	}
	return null;
    }
    
    
}
