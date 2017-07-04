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
import org.plazmaforge.framework.report.model.base.PageSetup;
import org.plazmaforge.framework.report.model.base.Pen;
import org.plazmaforge.framework.report.model.base.grid.CellBorderRule;
import org.plazmaforge.framework.report.model.base.grid.Column;
import org.plazmaforge.framework.report.model.design.Band;
import org.plazmaforge.framework.report.model.design.ReportGroup;
import org.plazmaforge.framework.report.model.design.Template;
import org.plazmaforge.framework.report.storage.xml.base.XMLColumnReader;
import org.plazmaforge.framework.report.storage.xml.base.XMLPageSetupReader;

/**
 * @author ohapon
 * 
 * Read template elements:
 * 
 * - page-setup
 * - columns
 * - report-groups
 * - bands
 *
 */
public class XMLTemplateReader extends XMLAbstractReportReader {

    public Template readTemplate(Element node) {
	Template template = new Template();
	readTemplateAttributes(node, template);
	readTemplateContent(node, template);
	return template;
    }
    
    ////

    protected void readTemplateAttributes(Element node, Template template) {
	
	readIdentifier(node, template);
	
	String value = null;
	Boolean booleanValue = null;
	
	// type
	value = getStringValue(node, XML_ATTR_TYPE);
	if (value != null) {
	    template.setType(value);
	}
	
	// cell-border-rule: ONLY FOR Table report
	CellBorderRule cellBorderRule = getCellBorderRule(node, XML_ATTR_CELL_BORDER_RULE);
	if (cellBorderRule != null) {
	    template.setCellBorderRule(cellBorderRule);
	}
	
	// cell-line: ONLY FOR Table report
	Pen cellLine = getBorderPenByAttributes(node, XML_ATTR_CELL_LINE);
	if (cellLine != null) {
	    template.setCellLine(cellLine);
	}

	// column-line: ONLY FOR Table report
	Pen columnLine = getBorderPenByAttributes(node, XML_ATTR_COLUMN_LINE);
	if (columnLine != null) {
	    template.setColumnLine(columnLine);
	}

	// row-line: ONLY FOR Table report
	Pen rowLine = getBorderPenByAttributes(node, XML_ATTR_ROW_LINE);
	if (rowLine != null) {
	    template.setRowLine(rowLine);
	}
	
	// paging
	booleanValue = getBooleanValue(node, XML_ATTR_PAGING);
	if (booleanValue != null) {
	   template.setPaging(booleanValue);
	}
	
	// report-header-on-page
	booleanValue = getBooleanValue(node, XML_ATTR_REPORT_HEADER_ON_PAGE);
	if (booleanValue != null) {
	    template.setReportHeaderOnPage(booleanValue);
	}
	
	// report-footer-on-page
	booleanValue = getBooleanValue(node, XML_ATTR_REPORT_FOOTER_ON_PAGE);
	if (booleanValue != null) {
	    template.setReportFooterOnPage(booleanValue);
	}
	
    }
    
    protected void readTemplateContent(Element node, Template template) {
	readPageSetup(node, template);
	readColumns(node, template); // ONLY FOR Table report 
	readGroups(node, template);
	readBands(node, template);
    }
    
    // PAGE-SETUP
    protected void readPageSetup(Element node, Template template) {
	Element child = getChild(node, XML_PAGE_SETUP);
	if (child == null) {
	    return;
	}
	XMLPageSetupReader reader = new XMLPageSetupReader();
	PageSetup pageSetup = reader.readPageSetup(child);
	template.setPageSetup(pageSetup);
    }
    
    // COLUMNS
    protected void readColumns(Element node, Template template) {
	List children = getNodeChildren(node, XML_COLUMNS, XML_COLUMN);
	if (children == null || children.isEmpty()) {
	    return;
	}
	int count = children.size();
	XMLColumnReader reader = new XMLColumnReader();
	for (int i = 0; i < count; i++) {
	    Column column = reader.readColumn((Element) children.get(i));
	    template.addColumn(column);
	}
    }

    // REPORT-GROUPS
    protected void readGroups(Element node, Template template) {
	List children = getNodeChildren(node, XML_REPORT_GROUPS, XML_REPORT_GROUP);
	if (children == null || children.isEmpty()) {
	    return;
	}
	int count = children.size();
	XMLGroupReader reader = new XMLGroupReader();
	for (int i = 0; i < count; i++) {
	    ReportGroup group = reader.readGroup((Element) children.get(i));
	    template.addGroup(group);
	}
    }
    
    // BANDS
    protected void readBands(Element node, Template template) {
	List children = getNodeChildren(node, XML_BANDS, XML_BAND);
	if (children == null || children.isEmpty()) {
	    return;
	}
	int count = children.size();
	XMLBandReader reader = new XMLBandReader();
	for (int i = 0; i < count; i++) {
	    Band band = reader.readBand((Element) children.get(i));
	    template.addBand(band);
	}
    }
    

}
