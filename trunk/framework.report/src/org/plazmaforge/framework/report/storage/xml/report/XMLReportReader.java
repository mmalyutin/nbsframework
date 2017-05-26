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
import java.io.Reader;
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
import org.plazmaforge.framework.util.FileUtils;

/**
 * @author ohapon
 * 
 * Read report elements:
 * 
 * - properties*
 * - parameters
 * - variables
 * - data-connectors
 * - data-sources
 * - styles*
 * - templates
 * 
 *
 */
public class XMLReportReader extends XMLAbstractReportReader implements ReportReader {

    private PropertyProviderFactory propertyProviderFactory;
    
    
    public XMLReportReader() {
	super();
    }

    @Override
    public Report readReport(String fileName) throws RTException {
	Document doc = readXMLDocument(fileName);
	return readReport(fileName, doc);
    }

    @Override
    public Report readReport(File file) throws RTException {
	Document doc = readXMLDocument(file);
	return readReport(file.getName(), doc);
    }

    @Override
    public Report readReport(InputStream is) throws RTException {
	Document doc = readXMLDocument(is);
	return readReport(null, doc);
    }

    @Override
    public Report readReport(Reader reader) throws RTException {
	Document doc = readXMLDocument(reader);
	return readReport(null, doc);
    }
    
    ////

    private PropertyProviderFactory getPropertyProviderFactory() {
	if (propertyProviderFactory == null) {
	    propertyProviderFactory = new ClassPropertyProviderFactory2();
	}
	return propertyProviderFactory;
    }
    
    ////

    protected Report readReport(String fileName, Document doc) {
	Element root = doc.getRootElement();
	return readReport(fileName, root);
    }

    protected Report readReport(String fileName, Element node) {
	String name = node.getName();

	if (!XML_REPORT.equals(name)) {
	    return null;
	}
	Report report = new Report();
	
	try {
	    String reportFile = new File(fileName).getCanonicalPath();
	    String reportDir = FileUtils.getFolderName(reportFile);
	    
	    report.setReportFile(reportFile);
	    report.setReportDir(reportDir);
	    
	} catch (Exception e) {
	    
	}
	
	
	readReportAttributes(node, report);
	readReportContent(node, report);
	

	return report;
    }
    
    protected void readReportAttributes(Element node, Report report) {
	readIdentifier(node, report);
	
	String value = null;
	
	// type
	value = getStringValue(node, XML_ATTR_TYPE);
	if (value != null) {
	    report.setType(value);
	}
	
    }
    
    protected void readReportContent(Element node, Report report) {
	//readProperties(node, report);
	readParameters(node, report);
	readVariables(node, report);
	readDataConnectors(node, report);
	readDataSources(node, report);
	//readStyles(node, report);
	readTemplates(node, report);
    }

    // PARAMETERS
    protected void readParameters(Element node, Report report) {
	List children = getNodeChildren(node, XML_PARAMETERS, XML_PARAMETER);
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
    
    // VARIABLES
    protected void readVariables(Element node, Report report) {
	List children = getNodeChildren(node, XML_VARIABLES, XML_VARIABLE);
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

    // DATA-CONNECTORS
    protected void readDataConnectors(Element node, Report report) {
	List children = getNodeChildren(node, XML_DATA_CONNECTORS, XML_DATA_CONNECTOR);
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
    protected void readDataSources(Element node, Report report) {
	List children = getNodeChildren(node, XML_DATA_SOURCES, XML_DATA_SOURCE);
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
    protected void readTemplates(Element node, Report report) {
	List children = getNodeChildren(node, XML_TEMPLATES, XML_TEMPLATE);
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
