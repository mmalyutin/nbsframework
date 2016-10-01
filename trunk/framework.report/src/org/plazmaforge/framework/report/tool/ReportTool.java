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

import java.util.Map;
import java.util.Properties;

import org.plazmaforge.framework.core.datastorage.DSResultSet;
import org.plazmaforge.framework.report.ReportEngine;
import org.plazmaforge.framework.report.ReportManager;
import org.plazmaforge.framework.report.model.design.Report;
import org.plazmaforge.framework.report.model.document.Document;
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

    
    public static void main(String[] args) {
	Properties properties = SystemUtils.loadProperties(args);
	if (properties.getProperty("?") != null) {
	    printHelp();
	    System.exit(0);
	}
	ReportTool tool = new ReportTool();
	tool.execute(properties);
	System.exit(0);
    }

    public void execute(Properties properties) {

	String reportFile = properties.getProperty("report");
	String documentFile = properties.getProperty("document");
	String exportFormat = properties.getProperty("format");
	String datastorageFile = properties.getProperty("datastorage");	// TODO: Not implemented
	boolean log = properties.getProperty("log", "false").equalsIgnoreCase("true");
	
	if (reportFile == null) {
	    trace("Error: -report is not setting");
	    printHelp();
	    return;
	}
	
	
	try {
	    if (log) {
		trace("Input data");
		trace("============================================================");
		trace("report   = " + reportFile);
		trace("document = " + documentFile);
		trace("format   = " + exportFormat);
		trace("log      = " + log);
	    }

	    if (exportFormat == null) {
		exportFormat = ReportEngine.DEFAULT_DOCUMENT_FROMAT;
	    }
	    
	    if (!ReportEngine.supportsReportExporter(exportFormat)) {
		error("Unsupports export format: " + exportFormat);
		return;
	    }
	    
	    if (documentFile == null) {
		documentFile = ReportEngine.generateDocumentFile(reportFile, exportFormat);
	    }


	    trace("\n");
	    trace("Modify data");
	    trace("============================================================");
	    trace("document = " + documentFile);
	    trace("format   = " + exportFormat);
	    trace("\n");
	    
	    
	    // Create ReportManager
	    ReportManager reportManager = new ReportManager();
	    
	    // Read the report form file
	    Report report = reportManager.readReport(reportFile);
	    
	    // Fill the report
	    Document document = reportManager.fillReport(report, (DSResultSet) null, (Map<String, Object>) null);
	    
	    // Write the document to file
	    reportManager.exportDocumentToFile(document, exportFormat, documentFile, null);
	    
	} catch (Exception e) {
	    error("ReportTool.init error: " + e.getMessage());
	    //e.printStackTrace();
	}
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
    
    private static void printHelp() {

	System.out.println("Usage: java ReportTool [-options]\n"
		+ "where options include:\n"
		+ "    -report <report file>\n"
		+ "    -document <document file> optional\n"
		+ "    -format <export format> optional\n"		
		+ "    -datastorage <report file> optional\n")		
		;
    }

}
