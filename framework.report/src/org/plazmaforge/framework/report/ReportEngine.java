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

import java.util.HashMap;
import java.util.Map;

import org.plazmaforge.framework.report.export.ReportExporter;
import org.plazmaforge.framework.report.export.ReportExporterFactory;
import org.plazmaforge.framework.report.export.uwt.UWTCanvasExporterFactory;
import org.plazmaforge.framework.report.export.xls.XLSExporterFactory;
import org.plazmaforge.framework.report.export.xlsx.XLSXExporterFactory;
import org.plazmaforge.framework.report.export.xml.XMLExporterFactory;
import org.plazmaforge.framework.report.fill.ReportFiller;
import org.plazmaforge.framework.report.fill.ReportFillerFactory;
import org.plazmaforge.framework.report.fill.TemplateFiller;
import org.plazmaforge.framework.report.fill.TemplateFillerFactory;
import org.plazmaforge.framework.report.fill.base.BaseReportFillerFactory;
import org.plazmaforge.framework.report.fill.base.BaseTemplateFillerFactory;
import org.plazmaforge.framework.report.fill.base.SimpleTemplateFillerFactory;
import org.plazmaforge.framework.report.fill.base.TableTemplateFillerFactory;
import org.plazmaforge.framework.report.fill.script.ScriptProvider;
import org.plazmaforge.framework.report.fill.script.ns.NSScriptProvider;
import org.plazmaforge.framework.report.fill.script.ps.PSScriptProvider;
import org.plazmaforge.framework.util.StringUtils;


/**
 * @author ohapon
 *
 */
public class ReportEngine {

    public static String DEFAULT_DOCUMENT_FROMAT = "XML";
    
    
    public static String DEFAULT_REPORT_TYPE = "Simple";
    
    public static String DEFAULT_TEMPLATE_TYPE = "Simple";

    public static String DEFAULT_LANGUAGE = "PlazmaScript";
    
    public static String DEFAULT_NO_LANGUAGE = "NoScript";
    
	    
    private Map<String, ReportFillerFactory> reportFillers;
    
    private Map<String, TemplateFillerFactory> templateFillers;
    
    private Map<String, ReportExporterFactory> reportExporters;
    
    private Map<String, ScriptProvider> scriptProviders;
    
    
    private static ReportEngine instance; 
    
    private ReportEngine() {
	
    }
    
    private static ReportEngine getInstance() {
	if (instance == null) {
	    instance = new ReportEngine();
	    instance.init();
	}
	return instance;
    }
    
    
    // REPORT FILLERS

    public static ReportFiller getReportFiller(String type) {
	type = normalizeString(type);
	if (type == null) {
	    type = DEFAULT_REPORT_TYPE;
	}
	ReportFillerFactory factory = getInstance().reportFillers.get(type);
	return factory == null ? null : factory.getReportFiller();
    }

    
    // TEMPLATE FILLERS
    
    public static TemplateFiller getTemplateFiller(String type) {
	type = normalizeString(type);
	if (type == null) {
	    type = DEFAULT_TEMPLATE_TYPE;
	}
	TemplateFillerFactory factory = getInstance().templateFillers.get(type);
	return factory == null ? null : factory.getTemplateFiller();
    }

    
    // REPORT EXPORTERS

    public static ReportExporter getReportExporter(String type) {
	ReportExporterFactory factory = getInstance().reportExporters.get(normalizeKey(type));
	return factory == null ? null : factory.getReportExporter();
    }
    
    public static void registerReportExporterFactory(String type, ReportExporterFactory factory) {
	getInstance().reportExporters.put(normalizeKey(type), factory);
    }

    public static void unregisterReportExporterFactory(String type) {
	getInstance().reportExporters.remove(normalizeKey(type));
    }
    
    public static boolean supportsReportExporter(String type) {
	return getInstance().reportExporters.get(normalizeKey(type)) != null;
    }

    
    // SCRIPT PROVIDERS
    
    public static ScriptProvider getScriptProvider(String language) {
	language = normalizeString(language);
	if (language == null) {
	    language = DEFAULT_LANGUAGE;
	}
	ScriptProvider scriptProvider = getInstance().scriptProviders.get(language);
	if (scriptProvider == null) {
	    scriptProvider = getInstance().scriptProviders.get(DEFAULT_LANGUAGE);
	}
	return scriptProvider;
    }

    /**
     * Generates document file name by report file and output format
     * @param reportFile
     * @return
     */
    public static String generateDocumentFile(String reportFile, String format) {
	if (reportFile == null) {
	    return null;
	}
	if (format == null) {
	    format = DEFAULT_DOCUMENT_FROMAT;
	}
	String documentFile = null;
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
	String ext = format.toLowerCase();
	//TODO
	if (ext.equals("xml")) {
	    ext = "document." + ext;
	}
	documentFile = documentFile + "." + ext;
	return documentFile;
    }
    
    private void init() {
	
	reportFillers = new HashMap<String, ReportFillerFactory>();
	reportExporters = new HashMap<String, ReportExporterFactory>();
	templateFillers = new HashMap<String, TemplateFillerFactory>();
	

	// Report fillers
	reportFillers.put(BaseReportFillerFactory.TYPE, new BaseReportFillerFactory());
	reportFillers.put("Simple", new BaseReportFillerFactory());

	// Template fillers
	templateFillers.put(BaseTemplateFillerFactory.TYPE, new BaseTemplateFillerFactory());
	templateFillers.put(SimpleTemplateFillerFactory.TYPE, new SimpleTemplateFillerFactory());
	templateFillers.put(TableTemplateFillerFactory.TYPE, new TableTemplateFillerFactory());

	// Script providers
	scriptProviders = new HashMap<String, ScriptProvider>();
	scriptProviders.put(DEFAULT_LANGUAGE, new PSScriptProvider());
	scriptProviders.put(DEFAULT_NO_LANGUAGE, new NSScriptProvider());

	// Report exporters
	registerDefaultReportExporters();
    }
    
    private void registerDefaultReportExporters(){
	registerReportExporterFactory(XMLExporterFactory.TYPE, new XMLExporterFactory());		// XML
	registerReportExporterFactory(XLSExporterFactory.TYPE, new XLSExporterFactory());		// XLS
	registerReportExporterFactory(XLSXExporterFactory.TYPE, new XLSXExporterFactory());		// XLS
	registerReportExporterFactory(UWTCanvasExporterFactory.TYPE, new UWTCanvasExporterFactory());	// UWT
    }
    
    private static String normalizeString(String str) {
	return StringUtils.normalizeString(str);
    }
    
    private static String normalizeKey(String key) {
	key = normalizeString(key);
	return key == null ? null : key.toUpperCase();
    }

    
}
