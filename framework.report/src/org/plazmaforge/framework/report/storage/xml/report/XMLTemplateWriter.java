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

package org.plazmaforge.framework.report.storage.xml.report;

import java.util.List;

import org.jdom.Element;
import org.plazmaforge.framework.report.model.base.PageSetup;
import org.plazmaforge.framework.report.model.base.Pen;
import org.plazmaforge.framework.report.model.base.grid.Column;
import org.plazmaforge.framework.report.model.design.Band;
import org.plazmaforge.framework.report.model.design.ReportGroup;
import org.plazmaforge.framework.report.model.design.Template;
import org.plazmaforge.framework.report.storage.xml.base.XMLColumnWriter;
import org.plazmaforge.framework.report.storage.xml.base.XMLPageSetupWriter;

/**
 * 
 * @author ohapon
 *
 */
public class XMLTemplateWriter extends XMLAbstractReportWriter {

    public void writeTemplate(Template template, Element node) {
	writeTemplateAttributes(template, node);
	writeTemplateContent(template, node);
    }
    
    
    protected void writeTemplateAttributes(Template template, Element node) {
	
	writeIdentifier(template, node);
	
   	// type
   	if (template.getType() != null) {
   	    setStringValue(node, XML_ATTR_TYPE, template.getType());
   	}
   	
	// cell-border-rule: ONLY FOR Table report
	if (template.getCellBorderRule() != null) {
	    setCellBorderRule(node, XML_ATTR_CELL_BORDER_RULE, template.getCellBorderRule());
	}
	
	// cell-line: ONLY FOR Table report
	if (template.getCellLine() != null) {
	   setPenByAttributes(template.getCellLine(), node, XML_ATTR_CELL_LINE);
	}

	// column-line: ONLY FOR Table report
	if (template.getColumnLine() != null) {
	    setPenByAttributes(template.getColumnLine(), node, XML_ATTR_COLUMN_LINE);
	}

	// row-line: ONLY FOR Table report
	if (template.getRowLine() != null) {
	   setPenByAttributes(template.getRowLine(), node, XML_ATTR_ROW_LINE);
	}
	
	// paging
	if (template.getPaging() != null) {
	   setBooleanValue(node, XML_ATTR_PAGING, template.getPaging());
	}
	
	// report-header-on-page
	if (template.isReportHeaderOnPage()) {
	    setBooleanValue(node, XML_ATTR_REPORT_HEADER_ON_PAGE, template.isReportHeaderOnPage());
	}

	// report-footer-on-page
	if (template.isReportFooterOnPage()) {
	    setBooleanValue(node, XML_ATTR_REPORT_FOOTER_ON_PAGE, template.isReportFooterOnPage());
	}
	
	
    }
    
    protected void writeTemplateContent(Template template, Element node) {
	writePageSetup(template, node);
	writeColumns(template, node); // ONLY FOR Table report 
	writeGroups(template, node);
	writeBands(template, node);
    }  

    
    // PAGE-SETUP
    protected void writePageSetup(Template template, Element node) {
	Element childNode = buildPageSetupNode(template);
	if (childNode != null) {
	    addChild(node, childNode);
	}
    }
    
    protected Element buildPageSetupNode(Template template) {
	if (!template.hasPageSetup()) {
	    return null;
	}
	PageSetup pageSetup = template.getPageSetup();
	if (pageSetup.isEmpty()) {
	    return null;
	}
	Element node = createElement(XML_PAGE_SETUP);
	XMLPageSetupWriter writer = new XMLPageSetupWriter();
	writer.writePageSetup(pageSetup, node);
	return node;
    }    
    
    // COLUMNS
    protected void writeColumns(Template template, Element node) {
	Element childNode = buildColumnsNode(template);
	if (childNode == null) {
	    return;
	}
	addChild(node, childNode);	
    }
    

    protected Element buildColumnsNode(Template template) {
	if (!template.hasColumns()) {
	    return null;
	}
	List<Column> columns = template.getColumns();
	Element parentNode = createElement(XML_COLUMNS);
	Element columnNode = null;
	XMLColumnWriter writer = new XMLColumnWriter();
	for (Column column : columns) {
	    columnNode = createElement(XML_COLUMN);
	    writer.writeColumn(column, columnNode);
	    addChild(parentNode, columnNode);
	}
	return parentNode;
    }
    
    
    // REPORT GROUPS
    protected void writeGroups(Template template, Element node) {
	Element childNode = buildGroupsNode(template);
	if (childNode == null) {
	    return;
	}
	addChild(node, childNode);	
    }
    
    protected Element buildGroupsNode(Template template) {
   	if (!template.hasGroups()) {
   	    return null;
   	}
   	List<ReportGroup> groups = template.getGroups();
   	Element parentNode = createElement(XML_REPORT_GROUPS);
   	Element childNode = null;
   	XMLGroupWriter writer = new XMLGroupWriter();
   	for (ReportGroup group : groups) {
   	    childNode = createElement(XML_REPORT_GROUP);
   	    writer.writeGroup(group, childNode);
   	    addChild(parentNode, childNode);
   	}
   	return parentNode;
   }

    // BANDS
    protected void writeBands(Template template, Element node) {
	Element childNode = buildBandsNode(template);
	if (childNode == null) {
	    return;
	}
	addChild(node, childNode);	
    }

    protected Element buildBandsNode(Template template) {
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
