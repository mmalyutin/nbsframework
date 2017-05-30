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
import org.plazmaforge.framework.report.model.design.Band;
import org.plazmaforge.framework.report.model.design.ReportGroup;


/**
 * @author ohapon
 * 
 * Write group elements:
 * 
 * - expression
 * - bands
 *
 */
public class XMLGroupWriter extends XMLAbstractReportWriter {

    public void writeGroup(ReportGroup group, Element node) {
	writeGroupAttributes(group, node);
	writeGroupContent(group, node);
    }
    
    protected void writeGroupAttributes(ReportGroup template, Element node) {
	
   	// name
   	if (template.getName() != null) {
   	    setStringValue(node, XML_ATTR_NAME, template.getName());
   	}
    }
    
    protected void writeGroupContent(ReportGroup template, Element node) {
	
	// expression
	if (template.hasExpressionText()) {
	    Element expressionNode = createElement(XML_EXPRESSION);
	    setExpression(template.getExpression(), expressionNode, USE_DATA_TYPE_IN_EXPRESSION);
	}
	
	writeBands(template, node);
    }  
    
    // BANDS
    protected void writeBands(ReportGroup template, Element node) {
	Element childNode = buildBandsNode(template);
	if (childNode == null) {
	    return;
	}
	addChild(node, childNode);	
    }

    protected Element buildBandsNode(ReportGroup template) {
   	if (!template.hasBands()) {
   	    return null;
   	}
   	List<Band> bands = template.getBands();
   	Element parentNode = createElement(XML_BANDS);
   	Element childNode = null;
   	XMLBandWriter writer = new XMLBandWriter();
   	for (Band band : bands) {
   	    childNode = createElement(XML_BAND);
   	    writer.writeBand(band, childNode);
   	    addChild(parentNode, childNode);
   	}
   	return parentNode;
   }    
}
