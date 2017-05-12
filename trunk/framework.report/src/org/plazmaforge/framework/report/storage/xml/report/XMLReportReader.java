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
import org.plazmaforge.framework.core.data.ClassPropertyProviderFactory2;
import org.plazmaforge.framework.core.data.PropertyProviderFactory;
import org.plazmaforge.framework.core.datastorage.DSDataConnector;
import org.plazmaforge.framework.core.datastorage.DSDataSource;
import org.plazmaforge.framework.core.datastorage.DSParameter;
import org.plazmaforge.framework.core.datastorage.DSVariable;
import org.plazmaforge.framework.report.exception.RTException;
import org.plazmaforge.framework.report.model.design.Report;
import org.plazmaforge.framework.report.model.design.Template;
import org.plazmaforge.framework.report.storage.ReportReader;
import org.plazmaforge.framework.report.storage.xml.datastorage.XMLDSDataConnectorReader;
import org.plazmaforge.framework.report.storage.xml.datastorage.XMLDSDataSourceReader;
import org.plazmaforge.framework.report.storage.xml.datastorage.XMLDSParameterReader;
import org.plazmaforge.framework.report.storage.xml.datastorage.XMLDSVariableReader;

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

    private PropertyProviderFactory propertyProviderFactory;
    
    
    public XMLReportReader() {
	super();
    }

    private PropertyProviderFactory getPropertyProviderFactory() {
	if (propertyProviderFactory == null) {
	    propertyProviderFactory = new ClassPropertyProviderFactory2();
	}
	return propertyProviderFactory;
    }
    
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
	
	readReportAttributes(element, report);
	readReportContent(element, report);
	

	return report;
    }
    
    protected void readReportAttributes(Element element, Report report) {
	readIdentifier(element, report);
	
	String value = null;
	
	// type
	value = getStringValue(element, XML_ATTR_TYPE);
	if (value != null) {
	    report.setType(value);
	}
	
    }
    
    protected void readReportContent(Element element, Report report) {
	//readProperties(element, report);
	readParameters(element, report);
	readVariables(element, report);
	readDataConnectors(element, report);
	readDataSources(element, report);
	//readStyles(element, report);
	readTemplates(element, report);
    }

    // VARIABLES
    protected void readVariables(Element element, Report report) {
	Element node = getChild(element, XML_VARIABLES);
	if (node == null){
	    return;
	}

	List children = node.getChildren();
	if (children == null || children.isEmpty()) {
	    return;
	}
	int count = children.size();
	XMLDSVariableReader reader = new XMLDSVariableReader();
	for (int i = 0; i < count; i++) {
	    DSVariable variable = reader.readVariable((Element) children.get(i));
	    report.addVariable(variable);
	}
    }
    
    // PARAMETERS
    protected void readParameters(Element element, Report report) {
	Element node = getChild(element, XML_PARAMETERS);
	if (node == null){
	    return;
	}

	List children = node.getChildren();
	if (children == null || children.isEmpty()) {
	    return;
	}
	int count = children.size();
	XMLDSParameterReader reader = new XMLDSParameterReader();
	for (int i = 0; i < count; i++) {
	    DSParameter parameter = reader.readParameter((Element) children.get(i));
	    report.addParameter(parameter);
	}
    }

    // DATA-CONNECTORS
    protected void readDataConnectors(Element element, Report report) {
	Element node = getChild(element, XML_DATA_CONNECTORS);
	if (node == null) {
	    return;
	}

	List children = node.getChildren();
	if (children == null || children.isEmpty()) {
	    return;
	}
	int count = children.size();
	XMLDSDataConnectorReader reader = new XMLDSDataConnectorReader(getPropertyProviderFactory());
	for (int i = 0; i < count; i++) {
	    DSDataConnector dataConnector = reader.readDataConnector((Element) children.get(i));
	    report.addDataConnector(dataConnector);
	}
    }
    
    // DATA-SOURCES
    protected void readDataSources(Element element, Report report) {
	Element node = getChild(element, XML_DATA_SOURCES);
	if (node == null) {
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
    protected void readTemplates(Element element, Report report) {
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
