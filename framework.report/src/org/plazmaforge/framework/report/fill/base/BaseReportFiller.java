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
package org.plazmaforge.framework.report.fill.base;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.core.datastorage.DSDataSet;
import org.plazmaforge.framework.core.datastorage.DSDataSource;
import org.plazmaforge.framework.core.datastorage.DSEmptyDataSet;
import org.plazmaforge.framework.core.datastorage.DSExpression;
import org.plazmaforge.framework.core.datastorage.DSField;
import org.plazmaforge.framework.core.datastorage.DSResultSet;
import org.plazmaforge.framework.core.datastorage.DSSession;
import org.plazmaforge.framework.core.datastorage.DSStructuredResultSet;
import org.plazmaforge.framework.core.datastorage.DSWrappedDataSet;
import org.plazmaforge.framework.core.datastorage.DataManager;
import org.plazmaforge.framework.core.datastorage.data.AggregationCalculator;
import org.plazmaforge.framework.core.exception.DSException;
import org.plazmaforge.framework.report.ReportEngine;
import org.plazmaforge.framework.report.exception.RTException;
import org.plazmaforge.framework.report.fill.ReportFiller;
import org.plazmaforge.framework.report.fill.TemplateFiller;
import org.plazmaforge.framework.report.fill.process.ReportContext;
import org.plazmaforge.framework.report.fill.script.ExpressionEvaluator;
import org.plazmaforge.framework.report.fill.script.ScriptGenerator;
import org.plazmaforge.framework.report.fill.script.ScriptInfo;
import org.plazmaforge.framework.report.fill.script.ScriptProvider;
import org.plazmaforge.framework.report.model.design.Report;
import org.plazmaforge.framework.report.model.design.ReportParameters;
import org.plazmaforge.framework.report.model.design.Template;
import org.plazmaforge.framework.report.model.document.Document;


/**
 * @author ohapon
 *
 */
public class BaseReportFiller implements ReportFiller {

    // By ResultSet
    public Document fillReport(Report report, DSResultSet resultSet) throws RTException {
	return fillReport(report, resultSet, null);
    }
    
    // By ResultSet
    public Document fillReport(Report report, DSResultSet resultSet, Map<String, Object> parameters) throws RTException {
	if (resultSet != null) {
	    if (parameters == null) {
		parameters = createParameters();
	    }
	    parameters.put(ReportParameters.RESULT_SET, resultSet);
	}
	return fillReport(report, parameters);
    }    


    
    // By Connection
    public Document fillReport(Report report, Connection connection) throws RTException {
	return fillReport(report, connection, null);
    }
	
    // By Connection
    public Document fillReport(Report report, Connection connection, Map<String, Object> parameters) throws RTException {
	if (connection != null) {
	    if (parameters == null) {
		parameters = createParameters();
	    }
	    parameters.put(ReportParameters.JDBC_CONNECTION, connection);
	}
	return fillReport(report, parameters);
    }    

    // By Parameters (General)
    public Document fillReport(Report report, Map<String, Object> parameters) throws RTException {
	Document document = new Document();
	
	// If report is empty then return empty document
	if (report == null || report.isEmpty()) {
	    return document;
	}

	// Prepare template fillers
	List<Template> templates = report.getTemplates();
	Map<Template, TemplateFiller> templateFillers = new HashMap<Template, TemplateFiller>();
	for (Template template: templates) {
	    String templateType = template.getType();
	    if (templateType == null) {
		templateType = ReportEngine.DEFAULT_TEMPLATE_TYPE;
	    }
	    TemplateFiller templateFiller = getTemplateFiller(templateType);
	    if (templateFiller == null) {
		throw new RTException("Can't fill template. TemplateFiller not found. TemplateType = '" + templateType + "'");
	    }
	    templateFillers.put(template, templateFiller);
	}
	
	if (parameters == null) {
	    parameters = createParameters();
	}

	// If data is null then Set empty data
	DSResultSet resultSet = getResultSet(report, parameters);
	DSDataSet dataSet = null;
	if (resultSet != null) {
	    dataSet = createWrappedDataSet(resultSet, report, parameters);
	}
	
	if (dataSet == null) {
	    dataSet = new DSEmptyDataSet();
	}
	
	ReportContext context = new ReportContext();
	context.setReport(report);
	context.setMainData(dataSet);
	context.setParameters(parameters);
	context.setDocument(document);
	
	ScriptProvider scriptProvider = getScriptProvider(report);
	ExpressionEvaluator expressionEvaluator = scriptProvider.getExpressionEvaluator(); 
	context.setExpressionEvaluator(expressionEvaluator);
	context.setAggregationCalculator(new AggregationCalculator());
	
	prepareScript(context, scriptProvider);
	
	// Fill report templates
	for (Template template: templates) {
	    TemplateFiller templateFiller = templateFillers.get(template);
	    templateFiller.fillTemplate(context, template);
	}
	
	return document;
    }
    
    protected Map<String, Object> createParameters() {
	return  new HashMap<String, Object>();
    }
    
    protected TemplateFiller getTemplateFiller(String name) {
	return ReportEngine.getTemplateFiller(name);
    }
    
    protected DSResultSet getResultSet(Report report, Map<String, Object> parameters) throws RTException {
	DSResultSet resultSet = (DSResultSet) parameters.get(ReportParameters.RESULT_SET);
	if (resultSet != null) {
	    return resultSet;
	}
	try {
	    Connection connection = (Connection) parameters.get(ReportParameters.JDBC_CONNECTION);
	    if (connection == null) {
		return null;
	    }
	    DSSession session = DataManager.openSession(connection);
	    if (session == null) { // ???
		return null;
	    }
	    DSDataSource dataSource = (DSDataSource) parameters.get(ReportParameters.DATA_SOURCE);
	    if (dataSource == null) {
		dataSource = report.getDataSource();
	    }
	    if (dataSource == null) {
		return null;
	    }
	    resultSet = DataManager.openResultSet(session, dataSource);

	} catch (DSException ex) {
	    throw new RTException(ex);
	}
	return resultSet;
    }    
    
    protected DSDataSet createWrappedDataSet(DSResultSet resultSet, Report report, Map<String, Object> parameters) {
	if (resultSet == null) {
	    return null;
	}
	DSDataSource dataSource = (DSDataSource) parameters.get(ReportParameters.DATA_SOURCE);
	if (dataSource == null) {
	    dataSource = report.getDataSource();
	}
	List<DSField> fields = dataSource == null ? null : dataSource.getFields();
	if (fields != null && fields.isEmpty()) {
	    fields = null;
	}
	
	// 2. Get field names by Main Data
	if (fields == null && (resultSet instanceof DSStructuredResultSet)) {
	    fields = new ArrayList<DSField>();
	    List<String> fieldNames = ((DSStructuredResultSet) resultSet).getFieldNames();
	    if (fieldNames != null) {
		for (int i = 0; i < fieldNames.size(); i++) {
		    String fieldName = fieldNames.get(i);
		    DSField field = new DSField();
		    field.setName(fieldName);

		    fields.add(field);
		}
	    }
	}
		
	return new DSWrappedDataSet(fields, resultSet);

    }

    protected void prepareScript(ReportContext context, ScriptProvider scriptProvider) throws RTException {
	String sourceCode = generateScript(context, scriptProvider);
	ScriptInfo scriptInfo = new ScriptInfo();
	scriptInfo.setSourceCode(sourceCode);
	
	System.out.println("Script: " + sourceCode);
	
	ExpressionEvaluator expressionEvaluator = context.getExpressionEvaluator();
	if (expressionEvaluator == null) {
	    return;
	}
	expressionEvaluator.init(scriptInfo);
    }
    
    protected String generateScript(ReportContext context, ScriptProvider scriptProvider) throws RTException {
	    
	try {
	    boolean optimizeExpression = false;
	    if (scriptProvider == null) {
		// TODO
		return null;
	    }
	    ScriptGenerator scriptGenerator = scriptProvider.getScriptGenerator();
	    if (scriptGenerator == null) {
		// TODO
		return null;
	    }
	    StringBuffer buf = new StringBuffer();
	    scriptGenerator.generateHeader(buf, context);
	    Report report = context.getReport();


	    // Text, Key
	    Map<String, Integer> expressionMap = null;
	    
	    if (optimizeExpression) {
		expressionMap = new HashMap<String, Integer>();
	    }
	    
	    List<DSExpression> expressions = report.buildExpressions();
	    if (expressions != null && !expressions.isEmpty()) {
		int expressionId = 0;
		for (DSExpression expression : expressions) {
		    String expressionText = expression.getText();
		    
		    if (optimizeExpression) {
			
			// Find id by expression text
			Integer findExpressionId = expressionMap.get(expressionText);
			
			// If found then assign id
			if (findExpressionId != null) {
			    expression.setId("" + findExpressionId);
			    continue;
			}
		    }
		    
		    expressionId++;
		    expression.setId("" + expressionId);
		    if (optimizeExpression) {
			expressionMap.put(expressionText, expressionId);
		    }
		    scriptGenerator.generateExpression(buf, expression);
		}
	    }
	    scriptGenerator.generateFooter(buf, context);
	    return buf.toString();
	} catch (IOException e) {
	    throw new RTException(e);
	}
    }
    
    protected ScriptProvider getScriptProvider(Report report) {
	
	// Get program language (expression/script)
	String language = report.getLanguage();
	ScriptProvider scriptProvider = ReportEngine.getScriptProvider(language);
	
	return scriptProvider;
    }
    
}
