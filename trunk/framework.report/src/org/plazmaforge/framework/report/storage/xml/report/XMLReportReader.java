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

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.plazmaforge.framework.core.datastorage.DSDataSource;
import org.plazmaforge.framework.report.exception.RTException;
import org.plazmaforge.framework.report.model.design.Report;
import org.plazmaforge.framework.report.model.design.Template;
import org.plazmaforge.framework.report.storage.ReportReader;
import org.plazmaforge.framework.report.storage.xml.datastorage.XMLDSDataSourceReader;

/**
 * @author ohapon
 * 
 * Read report elements:
 * 
 * - properties
 * - parameters
 * - variables
 * - data-connectors
 * - data-sources
 * - styles
 * - templates
 * 
 *
 */
public class XMLReportReader extends XMLAbstractReportReader implements ReportReader {

    
    public Report readReport(String fileName) throws RTException {
	Document doc = readXMLDocument(fileName);
	return readReport(fileName, doc);
    }

    public Report readReport(File file) throws RTException {
	Document doc = readXMLDocument(file);
	return readReport(file.getName(), doc);
    }

    public Report readReport(InputStream is) throws RTException {
	Document doc = readXMLDocument(is);
	return readReport(null, doc);
    }

    ////


    protected Report readReport(String fileName, Document doc) {
	Element root = doc.getRootElement();
	return readReport(fileName, root);
    }

    protected Report readReport(String fileName, Element element) {
	String name = element.getName();

	if (!XML_REPORT.equals(name)) {
	    return null;
	}
	Report report = new Report();
	
	readReportAttributes(report, element);
	readReportContent(report, element);
	

	return report;
    }
    
    protected void readReportAttributes(Report report, Element element) {
	String value = null;
	
	// name
	value = getStringValue(element, XML_ATTR_NAME);
	if (value != null) {
	    report.setName(value);
	}
	
	// caption
	value = getStringValue(element, XML_ATTR_CAPTION);
	if (value != null) {
	    report.setCaption(value);
	}

	// description
	value = getStringValue(element, XML_ATTR_DESCRIPTION);
	if (value != null) {
	    report.setDescription(value);
	}
	
	// type
	value = getStringValue(element, XML_ATTR_TYPE);
	if (value != null) {
	    report.setType(value);
	}
	
    }
    
    protected void readReportContent(Report report, Element element) {
	//readProperties(report, element);
	//readParameters(report, element);
	//readVariables(report, element);
	//readDataConnectors(report, element);
	readDataSources(report, element);
	//readStyles(report, element);
	readTemplates(report, element);
    }

    // DATA-SOURCES
    protected void readDataSources(Report report, Element element) {
	Element node = getChild(element, XML_DATA_SOURCES);
	if (node == null){
	    return;
	}

	List children = node.getChildren();
	if (children == null || children.isEmpty()) {
	    return;
	}
	int count = children.size();
	XMLDSDataSourceReader reader = new XMLDSDataSourceReader();
	for (int i = 0; i < count; i++) {
	    DSDataSource dataSource = reader.readDataSource((Element) children.get(i));
	    report.addDataSource(dataSource);
	}
    }
    
    // TEMPLATES
    protected void readTemplates(Report report, Element element) {
	Element node = getChild(element, XML_TEMPLATES);
	if (node == null){
	    return;
	}

	List children = node.getChildren();
	if (children == null || children.isEmpty()) {
	    return;
	}
	int count = children.size();
	XMLTemplateReader reader = new XMLTemplateReader();
	for (int i = 0; i < count; i++) {
	    Template template = reader.readTemplate((Element) children.get(i));
	    report.addTemplate(template);
	}
    }


  
    

}
