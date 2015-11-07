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


/**
 * @author ohapon
 *
 */
public class ReportEngine {

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

    public static ReportFiller getReportFiller(String type) {
	type = normalyzeString(type);
	if (type == null) {
	    type = DEFAULT_REPORT_TYPE;
	}
	ReportFillerFactory factory = getInstance().reportFillers.get(type);
	return factory == null ? null : factory.getReportFiller();
    }
    
    public static TemplateFiller getTemplateFiller(String type) {
	type = normalyzeString(type);
	if (type == null) {
	    type = DEFAULT_TEMPLATE_TYPE;
	}
	TemplateFillerFactory factory = getInstance().templateFillers.get(type);
	return factory == null ? null : factory.getTemplateFiller();
    }
    

    public static ReportExporter getReportExporter(String type) {
	ReportExporterFactory factory = getInstance().reportExporters.get(type);
	return factory == null ? null : factory.getReportExporter();
    }

    public static ScriptProvider getScriptProvider(String language) {
	language = normalyzeString(language);
	if (language == null) {
	    language = DEFAULT_LANGUAGE;
	}
	ScriptProvider scriptProvider = getInstance().scriptProviders.get(language);
	if (scriptProvider == null) {
	    scriptProvider = getInstance().scriptProviders.get(DEFAULT_LANGUAGE);
	}
	return scriptProvider;
    }

    
    private void init() {
	
	reportFillers = new HashMap<String, ReportFillerFactory>();
	reportFillers.put(BaseReportFillerFactory.TYPE, new BaseReportFillerFactory());
	reportFillers.put("Simple", new BaseReportFillerFactory());

	templateFillers = new HashMap<String, TemplateFillerFactory>();
	templateFillers.put(BaseTemplateFillerFactory.TYPE, new BaseTemplateFillerFactory());
	templateFillers.put(SimpleTemplateFillerFactory.TYPE, new SimpleTemplateFillerFactory());
	templateFillers.put(TableTemplateFillerFactory.TYPE, new TableTemplateFillerFactory());

	reportExporters = new HashMap<String, ReportExporterFactory>();
	reportExporters.put(UWTCanvasExporterFactory.TYPE, new UWTCanvasExporterFactory());

	scriptProviders = new HashMap<String, ScriptProvider>();
	scriptProviders.put(DEFAULT_LANGUAGE, new PSScriptProvider());
	scriptProviders.put(DEFAULT_NO_LANGUAGE, new NSScriptProvider());

    }
    
    private static String normalyzeString(String str) {
	if (str == null) {
	    return null;
	}
	str = str.trim();
	return str.isEmpty() ? null : str;
    }

    
}
