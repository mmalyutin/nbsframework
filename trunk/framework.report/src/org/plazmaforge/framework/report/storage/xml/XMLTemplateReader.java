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
package org.plazmaforge.framework.report.storage.xml;

import java.util.List;

import org.jdom.Element;
import org.plazmaforge.framework.report.model.base.Margin;
import org.plazmaforge.framework.report.model.base.PageSetup;
import org.plazmaforge.framework.report.model.base.Size;
import org.plazmaforge.framework.report.model.base.grid.Column;
import org.plazmaforge.framework.report.model.design.Band;
import org.plazmaforge.framework.report.model.design.Template;

/**
 * @author ohapon
 *
 */
public class XMLTemplateReader extends XMLAbstractReportReader {

    public void readTemplate(Template template, Element element) {
	readTemplateAttributes(template, element);
	readTemplateContent(template, element);
    }
    
    ////

    protected void readTemplateAttributes(Template template, Element element) {
	String value = null;
	
	/*
	// name
	value = getValue(element, XML_ATTR_NAME);
	if (value != null) {
	    template.setName(value);
	}
	
	// caption
	value = getValue(element, XML_ATTR_CAPTION);
	if (value != null) {
	    template.setDisplayName(value);
	}
	*/
	
	// type
	value = getValue(element, XML_ATTR_TYPE);
	if (value != null) {
	    template.setType(value);
	}
	
    }
    
    protected void readTemplateContent(Template template, Element element) {
	readPageSetup(template, element);
	readTemplateColumns(template, element);
	readTemplateBands(template, element);
    }
    
    protected void readPageSetup(Template template, Element element) {
	Element node = element.getChild(XML_PAGE_SETUP);
	if (node == null) {
	    return;
	}
	PageSetup pageSetup = new PageSetup();
	template.setPageSetup(pageSetup);

	// Page format
	String sValue = getValue(element, XML_ATTR_FORMAT);
	if (sValue != null) {
	    pageSetup.setFormat(sValue);
	}

	// Size
	Size size = getSizeByAttributes(element);
	if (size != null) {
	   pageSetup.setSize(size); 
	}
	
	// Margin
	Margin margin = getMargin(node);
	if (margin != null) {
	    pageSetup.setMargin(margin);
	}
	
    }
    
    protected void readTemplateColumns(Template template, Element element) {
	Element node = getChild(element, XML_COLUMNS);
	if (node == null){
	    return;
	}
	List children = node.getChildren();
	if (children == null || children.isEmpty()) {
	    return;
	}
	int count = children.size();
	Element columnNode = null;
	Integer iValue = null;
	for (int i = 0; i < count; i++) {
	    columnNode = (Element) children.get(i);
	    Column column = new Column();
	    iValue = getIntegerValue(columnNode, XML_ATTR_WIDTH);
	    if (iValue != null) {
		column.setWidth(iValue);
	    }
	    template.addColumn(column);
	}
    }

    protected void readTemplateBands(Template template, Element element) {
	Element node = getChild(element, XML_BANDS);
	if (node == null) {
	    return;
	}
	List children = node.getChildren();
	if (children == null || children.isEmpty()) {
	    return;
	}
	int count = children.size();
	XMLBandReader bandReader = new XMLBandReader();
	for (int i = 0; i < count; i++) {
	    Band band = new Band();
	    template.addBand(band);
	    bandReader.readBand(band, (Element) children.get(i));
	}
    }
    

}
