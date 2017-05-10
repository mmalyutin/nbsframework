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


import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

import org.plazmaforge.framework.core.datastorage.DSDataConnector;
import org.plazmaforge.framework.core.datastorage.DSResultSet;
import org.plazmaforge.framework.report.exception.RTException;
import org.plazmaforge.framework.report.model.design.Report;
import org.plazmaforge.framework.report.model.document.Document;

/**
 * General Report Manager
 * 
 * - Read Report
 * 
 * - Fill Report
 * 
 * - Export Document
 * 
 * @author ohapon
 *
 */
public interface ReportManager {


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // READ REPORT
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    
    Report readReport(String fileName) throws RTException;
    
    Report readReport(File file) throws RTException;

    Report readReport(InputStream is) throws RTException;
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // FILL REPORT
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    Document fillReport(Report report, DSResultSet reportData) throws RTException;
    
    Document fillReport(Report report, DSResultSet reportData, Map<String, Object> parameters) throws RTException;
    
    Document fillReport(Report report, Connection connection) throws RTException;
    
    Document fillReport(Report report, Connection connection, Map<String, Object> parameters) throws RTException;
    
    Document fillReport(Report report, DSDataConnector dataConnector) throws RTException;
	
    Document fillReport(Report report, DSDataConnector dataConnector, Map<String, Object> parameters) throws RTException;

    Document fillReport(Report report, String connectionString, Map<String, Object> parameters) throws RTException;
    
    Document fillReport(Report report, Map<String, Object> parameters) throws RTException;
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // EXPORT DOCUMNT
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    void exportDocumentToFile(Document document, String exportType, String outputFileName, Map<String, Object> exportData) throws RTException;
    
    void exportDocument(Document document, String exportType, Map<String, Object> exportData) throws RTException;

    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // READ DOCUMENT
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    
    Document readDocument(String fileName) throws RTException;

    Document readDocument(File file) throws RTException;

    Document readDocument(InputStream is) throws RTException;
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    
}
