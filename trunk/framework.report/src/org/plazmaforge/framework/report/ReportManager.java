/*
 * Copyright (C) 2012-2013 Oleh Hapon ohapon@users.sourceforge.net
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
package org.plazmaforge.framework.report;


import java.sql.Connection;
import java.util.Map;
import java.util.Set;

import org.plazmaforge.framework.core.datastorage.DSResultSet;
import org.plazmaforge.framework.report.exception.RTException;
import org.plazmaforge.framework.report.export.ReportExporter;
import org.plazmaforge.framework.report.fill.ReportFiller;
import org.plazmaforge.framework.report.model.design.Report;
import org.plazmaforge.framework.report.model.document.Document;

/**
 * General Report Manager
 * 
 * - Fill Report
 * 
 * - Export Document
 * 
 * @author ohapon
 *
 */
public class ReportManager {

    
    
    public Document fillReport(Report report, DSResultSet reportData) throws RTException {
	return fillReport(report, reportData, null);
    }
    
    public Document fillReport(Report report, DSResultSet reportData, Map<String, Object> parameters) throws RTException {
	// Get report type
	String reportType = getReportType(report);
	
	// Get report filler
	ReportFiller reportFiller = ReportEngine.getReportFiller(reportType);
	if (reportFiller == null) {
	    throw new RTException("Can't fill report. ReportFiller not found. ReportType = '" + reportType + "'");
	}
	
	// Fill report
	return reportFiller.fillReport(report, reportData, parameters);
    }

    public Document fillReport(Report report, Connection connection) throws RTException {
	return fillReport(report, connection, null);
    }
    
    public Document fillReport(Report report, Connection connection, Map<String, Object> parameters) throws RTException {
	// Get report type
	String reportType = getReportType(report);
	
	// Get report filler
	ReportFiller reportFiller = ReportEngine.getReportFiller(reportType);
	if (reportFiller == null) {
	    throw new RTException("Can't fill report. ReportFiller not found. ReportType = '" + reportType + "'");
	}
	
	// Fill report
	return reportFiller.fillReport(report, connection, parameters);
    }
    
    public Document fillReport(Report report, Map<String, Object> parameters) throws RTException {
	// Get report type
	String reportType = getReportType(report);
	
	// Get report filler
	ReportFiller reportFiller = ReportEngine.getReportFiller(reportType);
	if (reportFiller == null) {
	    throw new RTException("Can't fill report. ReportFiller not found. ReportType = '" + reportType + "'");
	}
	
	// Fill report
	return reportFiller.fillReport(report, parameters);
    }
    
    
    
    public void exportDocument(Document document, String exportType, Map<String, Object> exportData) throws RTException {
	if (exportType == null) {
	    throw new RTException("Can't export report. ExportType is null");
	}
	
	// Get report exporter
	ReportExporter reportExporter = ReportEngine.getReportExporter(exportType);
	if (reportExporter == null) {
	    throw new RTException("Can't export report. ReportExporter not found. ExportType = '"  + exportType + "'");
	}

	// Populate export data
	if (exportData != null) {
	    Set<Map.Entry<String, Object>> entries = exportData.entrySet();
	    for (Map.Entry<String, Object> entry: entries) {
		reportExporter.setData(entry.getKey(), entry.getValue());
	    }
	}
	
	// Export report
	reportExporter.exportDocument(document);
    }
    
    protected String getReportType(Report report) {
	return report == null ? null : report.getType();
    }
    
}
