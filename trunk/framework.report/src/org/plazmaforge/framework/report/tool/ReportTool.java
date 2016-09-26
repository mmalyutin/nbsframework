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
import org.plazmaforge.framework.report.ReportManager;
import org.plazmaforge.framework.report.model.design.Report;
import org.plazmaforge.framework.report.model.document.Document;
import org.plazmaforge.framework.report.storage.xml.document.XMLDocumentWriter;
import org.plazmaforge.framework.report.storage.xml.report.XMLReportReader;


public class ReportTool {

    private Properties pProperties = new Properties();

    
    public static void main(String[] arg) {

	for (int i = 0; i < arg.length; i++) {
	    String p = arg[i];
	    if (p.equals("-?")) {
		printHelp();
		System.exit(0);
	    }
	}

	ReportTool tool = new ReportTool();
	tool.execute(arg);
	System.exit(0);
    }

    public void execute(String[] arg) {

	for (int i = 0; i < arg.length; i++) {
	    String p = arg[i];

	    if (p.charAt(0) == '-') {
		pProperties.put(p.substring(1), arg[i + 1]);
		i++;
	    }
	}

	//BufferedReader in = null;
	Properties p = pProperties;

	String reportFile = p.getProperty("report");
	String documentFile = p.getProperty("document");
	String datastorageFile = p.getProperty("datastorage");	
	boolean log = p.getProperty("log", "false").equalsIgnoreCase("true");
	
	if (reportFile == null) {
	    trace("Error: -report is not setting");
	    printHelp();
	    return;
	}
	
	
	try {
	    if (log) {
		trace("report   = " + reportFile);
		trace("document = " + documentFile);
		trace("log      = " + log);
	    }
	    
	    if (documentFile == null) {
		int index =  reportFile.lastIndexOf(".");
		if (index > 0) {
		    documentFile = reportFile.substring(0, index);
		    index =  documentFile.lastIndexOf(".");
		    if (index > 0) {
			String ext = documentFile.substring(index + 1);
			if (ext.equals("report")) {
			    documentFile = reportFile.substring(0, index);
			}
		    }
		} else {
		    documentFile = reportFile;
		}
		documentFile = documentFile + ".document.xml";
	    }
	    
	    trace("document = " + documentFile);
	    
	    // Read the report form XML file
	    XMLReportReader reportReader = new XMLReportReader();
	    Report report = reportReader.readReport(reportFile);
	    
	    // Fill the report
	    ReportManager reportManager = new ReportManager();
	    Document document = reportManager.fillReport(report, (DSResultSet) null, (Map<String, Object>) null);
	    
	    // Write the document to XML file
	    XMLDocumentWriter documentWriter = new XMLDocumentWriter();
	    documentWriter.writeDocument(document, documentFile);
	    
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
		+ "    -datastorage <report file> optional\n")		
		;
    }

}
