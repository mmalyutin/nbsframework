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

import java.util.Properties;

import org.plazmaforge.framework.report.ReportEngine;
import org.plazmaforge.framework.report.ReportManager;
import org.plazmaforge.framework.report.model.document.Document;
import org.plazmaforge.framework.report.storage.xml.document.XMLDocumentReader;
import org.plazmaforge.framework.util.StringUtils;
import org.plazmaforge.framework.util.SystemUtils;

/**
 * Export data from Document XML format to custom format 
 * 
 * @author ohapon
 *
 */
public class ExportTool {

    
    public static void main(String[] args) {
	Properties properties = SystemUtils.loadProperties(args);
	if (properties.getProperty("?") != null) {
	    printHelp();
	    System.exit(0);
	}
	ExportTool tool = new ExportTool();
	tool.execute(properties);
	System.exit(0);
    }

    public void execute(Properties properties) {

	String inputFile = properties.getProperty("input");
	String outputFile = properties.getProperty("output");
	String exportFormat = properties.getProperty("format");
	boolean log = properties.getProperty("log", "false").equalsIgnoreCase("true");
	
	if (inputFile == null || (outputFile == null && exportFormat == null)) {
	    if (inputFile == null) {
		trace("Error: -input is not setting");
	    }
	    if (outputFile == null && exportFormat == null) {
		trace("Error: -output/format is not setting");
	    }
	    printHelp();
	    return;
	}
	
	
	try {
	    if (log) {
		trace("Input data");
		trace("============================================================");
		trace("input  = " + inputFile);
		trace("output = " + outputFile);
		trace("format = " + exportFormat);
		trace("log    = " + log);
	    }

	    if (exportFormat == null) {
		exportFormat = (outputFile == null ? null : SystemUtils.getFileNameSuffix(outputFile));
		exportFormat = StringUtils.normalizeString(exportFormat);
		if (exportFormat == null) {
		    error("Error: Export format is empty. Can't get extension form output file");		    
		}
	    }
	    
	    if (!ReportEngine.supportsReportExporter(exportFormat)) {
		error("Error: Unsupports export format '" + exportFormat + "'");
		return;
	    }
	    
	    if (outputFile == null) {
		outputFile = ReportEngine.generateOutputFile(inputFile, exportFormat);
	    }


	    trace("\n");
	    trace("Modify data");
	    trace("============================================================");
	    trace("output = " + outputFile);
	    trace("format = " + exportFormat);
	    trace("\n");
	    
	    
	    // Create ReportManager
	    ReportManager reportManager = new ReportManager();
	    
	    // Read the report form file
	    XMLDocumentReader documentReader = new XMLDocumentReader(); 
	    Document document = documentReader.readDocument(inputFile);
	    
	    // Write the document to file
	    reportManager.exportDocumentToFile(document, exportFormat, outputFile, null);
	    
	    trace("Document '" + inputFile + "' was exported to file '" + outputFile + "' with format '"  + exportFormat + "'");
	    
	} catch (Exception e) {
	    error("ExportTool.init error: " + getErrorMessage(e));
	    //e.printStackTrace();
	}
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
         
    private static void printHelp() {

	System.out.println("Usage: java ExportTool [-options]\n"
		+ "where options include:\n"
		+ "    -input <input file>\n"
		+ "    -output <output file> optional\n"
		+ "    -format <export format> optional if -output is setting\n");		
    }

}
