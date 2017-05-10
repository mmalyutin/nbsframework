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
    
    private static final String LINE = "============================================================";
    
    private boolean log;
    
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

	log = false;
	
	String inputFile = properties.getProperty("input-file");
	String outputFile = properties.getProperty("output-file");
	String outputFormat = properties.getProperty("output-format");
	log = properties.getProperty("log", "false").equalsIgnoreCase("true");
	
	if (inputFile == null || (outputFile == null && outputFormat == null)) {
	    if (inputFile == null) {
		error("'-input-file' is not setting");
	    }
	    if (outputFile == null && outputFormat == null) {
		error("'-output-file' is not setting");
	    }
	    printHelp();
	    return;
	}
	
	
	try {
	    if (log) {
		trace("\nInput log");
		trace(LINE);
		trace("input-file    = " + inputFile);
		trace("output-file   = " + outputFile);
		trace("output-format = " + outputFormat);
		trace("log           = " + log);
	    }
	    
	    boolean changeLog = false;
	    boolean changeOutputFormat = false;
	    boolean changeOutputFile = false;

	    if (outputFormat == null) {
		outputFormat = (outputFile == null ? null : SystemUtils.getFileNameSuffix(outputFile));
		outputFormat = StringUtils.normalizeString(outputFormat);
		if (outputFormat == null) {
		    error("Export format is empty. Can't get extension form output file");
		    return;
		}
		changeOutputFormat = true;
	    }
	    
	    if (!ReportEngine.supportsReportExporter(outputFormat)) {
		error("Unsupports export format '" + outputFormat + "'");
		return;
	    }
	    
	    if (outputFile == null) {
		outputFile = ReportEngine.generateOutputFile(inputFile, outputFormat);
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
	    }
	    
	    // Create ReportManager
	    ReportManager reportManager = ReportEngine.getReportManager();
	    
	    long time = 0;
	    long readTime = 0;
	    long exportTime = 0;
	    long totalTime = 0;
	    
	    
	    // Read the report form file
	    time = System.currentTimeMillis();
	    trace(log, "\nStart read the document...");
	    XMLDocumentReader documentReader = new XMLDocumentReader(); 
	    Document document = documentReader.readDocument(inputFile);
	    trace(log, "Document '" + inputFile + "' was read.");
	    readTime = System.currentTimeMillis() - time; 
	    
	    // Write the document to file
	    time = System.currentTimeMillis();
	    trace("Start export the document...");
	    reportManager.exportDocumentToFile(document, outputFormat, outputFile, null);
	    exportTime = System.currentTimeMillis() - time;
	    
	    trace("Document '" + inputFile + "' was exported to file '" + outputFile + "' with format '"  + outputFormat + "'");
	    
	    totalTime = readTime + exportTime;
		
	    trace("\nStatistics");
	    trace(LINE);
	    trace("Read document   : " + readTime + " ms");
	    trace("Export document : " + exportTime + " ms");
	    trace("Total         : " + totalTime + " ms");
	    
	} catch (Exception e) {
	    error(e);
	}
    }
    
    private void trace(String message) {
	System.out.println(message);
    }
    
    private void trace(boolean enabled, String message) {
	if (!enabled) {
	    return;
	}
	trace(message);
    }
    
    private void warning(String message) {
	System.out.println("WARNING: " + message);
    }
    
    private void error(String message) {
	System.out.println("ERROR:   " + message);
    }

    private void error(Throwable e) {
	error(getErrorMessage(e));
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
		+ "    -input-file <input file>\n"
		+ "    -output-file <output file> optional\n"
		+ "    -output-format <output format> optional\n");		
    }

}
