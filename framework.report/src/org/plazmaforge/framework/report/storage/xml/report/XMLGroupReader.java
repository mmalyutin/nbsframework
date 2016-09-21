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

/**
 * 
 */
package org.plazmaforge.framework.report.storage.xml.report;

import java.util.List;

import org.jdom.Element;
import org.plazmaforge.framework.core.datastorage.DSExpression;
import org.plazmaforge.framework.report.model.design.Band;
import org.plazmaforge.framework.report.model.design.ReportGroup;


/**
 * @author ohapon
 * 
 * Read group elements:
 * 
 * - expression
 * - bands
 *
 */
public class XMLGroupReader extends XMLAbstractReportReader {

    public ReportGroup readGroup(Element element) {
	ReportGroup group = new ReportGroup();
	readGroupAttributes(element, group);
	readGroupContent(element, group);
	return group;
    }

    ////
    
    protected void readGroupAttributes(Element element, ReportGroup group) {
	String value = null;

	// name
	value = getStringValue(element, XML_ATTR_NAME);
	if (value != null) {
	    group.setName(value);
	}
	
    }
    
    protected void readGroupContent(Element element, ReportGroup group) {
	
	// expression
	DSExpression expression = getExpression(getChild(element, XML_EXPRESSION), false);
	if (expression != null) {
	    group.setExpression(expression);
	}

	readBands(element, group);
    }
    
    // BANDS
    protected void readBands(Element element, ReportGroup group) {
	Element node = getChild(element, XML_BANDS);
	if (node == null) {
	    return;
	}
	List children = node.getChildren();
	if (children == null || children.isEmpty()) {
	    return;
	}
	int count = children.size();
	XMLBandReader reader = new XMLBandReader();
	for (int i = 0; i < count; i++) {
	    Band band = reader.readBand((Element) children.get(i));
	    group.addBand(band);
	}
    }
    
    
}
