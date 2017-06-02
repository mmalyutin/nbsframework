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
import java.io.OutputStream;
import java.io.Writer;
import java.util.List;

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
import org.plazmaforge.framework.report.storage.ReportWriter;
import org.plazmaforge.framework.report.storage.xml.datastorage.XMLDSDataConnectorWriter;
import org.plazmaforge.framework.report.storage.xml.datastorage.XMLDSDataSourceWriter;
import org.plazmaforge.framework.report.storage.xml.datastorage.XMLDSParameterWriter;
import org.plazmaforge.framework.report.storage.xml.datastorage.XMLDSVariableWriter;

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
public class XMLReportWriter extends XMLAbstractReportWriter implements	ReportWriter {

    private PropertyProviderFactory propertyProviderFactory;
    
    
    @Override
    public void wrireReport(Report report, String fileName) throws RTException {
	org.jdom.Document doc = buildXMLDocument(report);
        writeXMLDocument(doc, fileName);
    }

    public void writeReport(Report report, File file) throws RTException {
	org.jdom.Document doc = buildXMLDocument(report);
        writeXMLDocument(doc, file);
    }

    public void writeReport(Report report, OutputStream os) throws RTException {
	org.jdom.Document doc = buildXMLDocument(report);
        writeXMLDocument(doc, os);
    }

    public void writeReport(Report report, Writer writer) throws RTException {
	org.jdom.Document doc = buildXMLDocument(report);
	writeXMLDocument(doc, writer);
    }
    
    ////

    private PropertyProviderFactory getPropertyProviderFactory() {
	if (propertyProviderFactory == null) {
	    propertyProviderFactory = new ClassPropertyProviderFactory2();
	}
	return propertyProviderFactory;
    }
    
    ////
    
    protected org.jdom.Document buildXMLDocument(Report report) {
	Element root = createElement(XML_REPORT);
	org.jdom.Document doc = new org.jdom.Document(root);
	
	writeReportAttributes(report, root);
	writeReportContent(report, root);
	
	return doc;

    }
    
    
    protected void writeReportAttributes(Report report, Element node) {
	
	writeIdentifier(report, node);
	
   	// type
   	if (report.getType() != null) {
   	    setStringValue(node, XML_ATTR_TYPE, report.getType());
   	}
	
    }
    
    protected void writeReportContent(Report report, Element node) {
	//writeProperties(report, node);
	
	writeParameters(report, node);
	writeVariables(report, node);
	writeDataConnectors(report, node);
	writeDataSources(report, node);
	//writeStyles(report, node);
	writeTemplates(report, node);
    }
    
    
    // PARAMETERS    
    protected void writeParameters(Report report, Element node) {
	Element childNode = buildParametersNode(report);
	if (childNode != null) {
	    addChild(node, childNode);
	}
    }

    protected Element buildParametersNode(Report report) {
	if (!report.hasParameters()) {
	    return null;
	}
	List<DSParameter> parameters = report.getParameters();
	Element parentNode = createElement(XML_PARAMETERS);
	Element childNode = null;
	XMLDSParameterWriter writer = new XMLDSParameterWriter();
	for (DSParameter parameter : parameters) {
	    childNode =	createElement(XML_PARAMETER);
	    writer.writeParameter(parameter, childNode);
	    addChild(parentNode, childNode);
	}
	return parentNode;
    }

    // VARIABLES    
    protected void writeVariables(Report report, Element node) {
	Element childNode = buildVariablesNode(report);
	if (childNode != null) {
	    addChild(node, childNode);
	}
    }

    protected Element buildVariablesNode(Report report) {
	if (!report.hasVariables()) {
	    return null;
	}
	List<DSVariable> variables = report.getVariables();
	Element parentNode = createElement(XML_VARIABLES);
	Element childNode = null;
	XMLDSVariableWriter writer = new XMLDSVariableWriter();
	for (DSVariable variable : variables) {
	    childNode =	createElement(XML_VARIABLE);
	    writer.writeVariable(variable, childNode);
	    addChild(parentNode, childNode);
	}
	return parentNode;
    }
    
    // DATA-CONNECTORS    
    protected void writeDataConnectors(Report report, Element node) {
	Element childNode = buildDataConnectorsNode(report);
	if (childNode != null) {
	    addChild(node, childNode);
	}
    }

    protected Element buildDataConnectorsNode(Report report) {
	if (!report.hasDataConnectors()) {
	    return null;
	}
	List<DSDataConnector> dataConnectors = report.getDataConnectors();
	Element parentNode = createElement(XML_DATA_CONNECTORS);
	Element childNode = null;
	XMLDSDataConnectorWriter writer = new XMLDSDataConnectorWriter(getPropertyProviderFactory());
	for (DSDataConnector dataConnector : dataConnectors) {
	    childNode = createElement(XML_DATA_CONNECTOR);
	    writer.writeDataConnector(dataConnector, childNode);
	    addChild(parentNode, childNode);
	}
	return parentNode;
    }    

    // DATA-SOURCES    
    protected void writeDataSources(Report report, Element node) {
	Element childNode = buildDataSourcesNode(report);
	if (childNode != null) {
	    addChild(node, childNode);
	}
    }

    protected Element buildDataSourcesNode(Report report) {
	if (!report.hasDataSources()) {
	    return null;
	}
	List<DSDataSource> dataSources = report.getDataSources();
	Element parentNode = createElement(XML_DATA_SOURCES);
	Element childNode = null;
	XMLDSDataSourceWriter writer = new XMLDSDataSourceWriter();
	for (DSDataSource dataSource : dataSources) {
	    childNode = createElement(XML_DATA_SOURCE);
	    writer.writeDataSource(dataSource, childNode);
	    addChild(parentNode, childNode);
	}
	return parentNode;
    }    
    
    // TEMPLATES    
    protected void writeTemplates(Report report, Element node) {
	Element childNode = buildTemplatesNode(report);
	if (childNode != null) {
	    addChild(node, childNode);
	}
    }

    protected Element buildTemplatesNode(Report report) {
	if (!report.hasTemplates()) {
	    return null;
	}
	List<Template> templates = report.getTemplates();
	Element parentNode = createElement(XML_TEMPLATES);
	Element childNode = null;
	XMLTemplateWriter writer = new XMLTemplateWriter();
	for (Template template : templates) {
	    childNode = createElement(XML_TEMPLATE);
	    writer.writeTemplate(template, childNode);
	    addChild(parentNode, childNode);
	}
	return parentNode;
    }
    
    
}