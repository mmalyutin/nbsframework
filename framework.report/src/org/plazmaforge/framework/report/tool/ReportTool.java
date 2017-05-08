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

package org.plazmaforge.framework.report.tool;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.plazmaforge.framework.core.data.ClassPropertyProviderFactory2;
import org.plazmaforge.framework.core.data.PropertyProvider;
import org.plazmaforge.framework.core.data.PropertyProviderFactory;
import org.plazmaforge.framework.core.datastorage.DSDataConnector;
import org.plazmaforge.framework.core.datastorage.DataManager;
import org.plazmaforge.framework.core.datastorage.DataProducer;
import org.plazmaforge.framework.datastorage.DataStorage;
import org.plazmaforge.framework.report.ReportEngine;
import org.plazmaforge.framework.report.ReportManager;
import org.plazmaforge.framework.report.model.design.Report;
import org.plazmaforge.framework.report.model.design.ReportParameters;
import org.plazmaforge.framework.report.model.document.Document;
import org.plazmaforge.framework.util.CoreUtils;
import org.plazmaforge.framework.util.SystemUtils;

/**
 * 
 * ReportTool steps:
 *  1. Read Report (report XML format)
 *  2. Fill Report (report object)
 *  3. Export Document (custom format, by default: document XML format)
 * 
 * @author ohapon
 *
 */
public class ReportTool {
    
    private static final String LINE = "============================================================"; 
    
    private boolean log;
    
    private PropertyProviderFactory propertyProviderFactory;
    
    public static void main(String[] args) {
	//dumpArgs(args);
	Properties properties = SystemUtils.loadProperties(args);
	if (properties.getProperty("?") != null) {
	    printHelp();
	    System.exit(0);
	}
	ReportTool tool = new ReportTool();
	tool.execute(properties);
	//System.exit(0);
    }

    public void execute(Properties properties) {
	
	log = false;

	String reportFile = properties.getProperty("report-file");
	String outputFile = properties.getProperty("output-file");
	String outputFormat = properties.getProperty("output-format");
	
	String datastorageFile = properties.getProperty("datastorage");	// TODO: Not implemented
	String connectionString = properties.getProperty("connection");
	log = properties.getProperty("log", "false").equalsIgnoreCase("true");
	
	if (reportFile == null) {
	    trace("Error: '-report-file' is not setting");
	    printHelp();
	    return;
	}
	
	try {
	    if (log) {
		trace("Input log");
		trace(LINE);
		trace("report-file   = " + reportFile);
		trace("output-file   = " + outputFile);
		trace("output-format = " + outputFormat);
		trace("log           = " + log);
	    }

	    boolean changeLog = false;
	    boolean changeOutputFormat = false;
	    boolean changeOutputFile = false;
	    
	    if (outputFormat == null) {
		outputFormat = ReportEngine.DEFAULT_DOCUMENT_FORMAT;
		changeOutputFormat = true;		
	    }
	    
	    if (!ReportEngine.supportsReportExporter(outputFormat)) {
		error("Unsupports export format: " + outputFormat);
		return;
	    }
	    
	    if (outputFile == null) {
		outputFile = ReportEngine.generateDocumentFile(reportFile, outputFormat);
		changeOutputFile = true;		
	    }

	    changeLog = changeOutputFormat || changeOutputFile;

	    if (changeLog) {
		trace("\n");
		trace("Change log");
		trace(LINE);
		if (changeOutputFile) {
		    trace("output-file   = " + outputFile);
		}
		if (changeOutputFormat) {
		    trace("output-format = " + outputFormat);
		}
		trace("\n");
	    }
	    
	    // Initialize DataStorage: Register base DataProducer factories 
	    DataStorage.init();
	    
	    
	    DSDataConnector dataConnector = loadDataConnector(properties);
	    Map<String, Object> parameters = new HashMap<String, Object>();
	    
	    parameters.put(ReportParameters.DATA_CONNECTOR, dataConnector);
	    parameters.put(ReportParameters.CONNECTION_STRING, connectionString);
	    
	    // Create ReportManager
	    ReportManager reportManager = new ReportManager();
	    
	    // Read the report form file
	    Report report = reportManager.readReport(reportFile);
	    
	    
	    // Fill the report
	    Document document = reportManager.fillReport(report, parameters);
	    
	    // Write the document to file
	    reportManager.exportDocumentToFile(document, outputFormat, outputFile, null);
	    
	    trace("Report '" + reportFile + "' was exported to file '" + outputFile + "' with format '"  + outputFormat + "'");
	    
	} catch (Exception e) {
	    error("ReportTool.init error: " + getErrorMessage(e));
	    //e.printStackTrace();
	}
    }

    private DSDataConnector loadDataConnector(Properties properties) {
	if (properties == null) {
	    return null;
	}
	
	String prefix = "dataconnector.";
	Map<String, String> result = CoreUtils.toFilterMap(properties, prefix, true);
	if (result == null || result.isEmpty()) {
	    return null;
	}
	
	String type = result.get("type");
	if (type == null) {
	    type = "SQL";
	} else {
	    result.remove("type");
	}
	
	
	
	if (!DataManager.supportsDataProducer(type)) {
	    trace("Unsupports DataConnector type: " + type);
	    return null;
	}
	
	DataProducer dataProducer = DataManager.getDataProducer(type);
	if (dataProducer == null) {
	    trace("DataProducer is not initialized by type " + type);
	    return null;
	}
	
	DSDataConnector dataConnector = dataProducer.createDataConnector();
	if (dataConnector == null) {
	    trace("DataConnector is not initialized by type " + type);
	    return null;
	}
	
	PropertyProvider propertyProvider = getPropertyProviderFactory().getPropertyProvider(dataConnector.getClass());
	if (propertyProvider == null) {
	    trace("PropertyProvider is not initialized. PropertyProviderFactory: " + propertyProviderFactory.getClass());
	    return null;
	}

	Set<String> names = result.keySet();
	String value = null;
	
	if (log) {
	    trace("DataConnector properties");
	    trace("============================================================");
	}
	
	for (String name: names) {
	    value = result.get(name);
	    propertyProvider.setValue(dataConnector, name, value);
	    trace("" + name + "=" + value);
	}
 	return dataConnector;
    }

    
    private PropertyProviderFactory getPropertyProviderFactory() {
	if (propertyProviderFactory == null) {
	    propertyProviderFactory = new ClassPropertyProviderFactory2();
	}
	return propertyProviderFactory;
    }
    
    private String toNormalizeString(String str) {
	if (str == null) {
	    return null;
	}
	str = str.trim();
	return str.isEmpty() ? null : str; 
    }

    private void trace(String s) {
	System.out.println(s);
    }

    private void error(String s) {
	System.err.println(s);
    }
    
    private String getErrorMessage(Throwable e) {
	if (e == null) {
	    return null;
	}
	String message = e.getMessage();
	return message == null ? e.toString() : message;
    }
    
    private static void dumpArgs(String[] args) {
	if (args == null) {
	    System.out.println("Args is null");
	    return;
	}
	if (args.length == 0) {
	    System.out.println("Args is empty");
	    return;
	}
	
	System.out.println("Args");
	System.out.println("==============================================");
	for (int i = 0; i < args.length; i++ ) {
	    System.out.println("arg[" + i + "]='" + args[i] + "'");
	}
    }
    
    private static void printHelp() {

	System.out.println("Usage: java ReportTool [-options]\n"
		+ "where options include:\n"
		+ "    -report-file <report file>\n"
		+ "    -output-file <output file> optional\n"
		+ "    -output-format <output format> optional\n"
		
		+ "    -data-storage <datastorage file> optional\n"
		+ "    -data-connector.<property name> <property value> optional\n")	
		;
    }

}
