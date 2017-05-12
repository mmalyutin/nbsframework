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

import org.plazmaforge.framework.core.datastorage.DSDataConnector;
import org.plazmaforge.framework.core.datastorage.DSDataHelper;
import org.plazmaforge.framework.core.datastorage.DSDataSet;
import org.plazmaforge.framework.core.datastorage.DSDataSource;
import org.plazmaforge.framework.core.datastorage.DSEmptyDataSet;
import org.plazmaforge.framework.core.datastorage.DSExpression;
import org.plazmaforge.framework.core.datastorage.DSExpressionEvaluator;
import org.plazmaforge.framework.core.datastorage.DSField;
import org.plazmaforge.framework.core.datastorage.DSParameter;
import org.plazmaforge.framework.core.datastorage.DSResultSet;
import org.plazmaforge.framework.core.datastorage.DSSession;
import org.plazmaforge.framework.core.datastorage.DSStructuredResultSet;
import org.plazmaforge.framework.core.datastorage.DSWrappedDataSet;
import org.plazmaforge.framework.core.datastorage.DataManager;
import org.plazmaforge.framework.core.datastorage.data.AggregationCalculator;
import org.plazmaforge.framework.core.datastorage.data.Scope;
import org.plazmaforge.framework.core.exception.DSEvaluateException;
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
import org.plazmaforge.framework.report.model.base.PageSetup;
import org.plazmaforge.framework.report.model.design.Report;
import org.plazmaforge.framework.report.model.design.ReportParameters;
import org.plazmaforge.framework.report.model.design.Template;
import org.plazmaforge.framework.report.model.document.Document;


/**
 * @author ohapon
 *
 */
public class BaseReportFiller implements ReportFiller {

    private boolean trace;
    
    
    protected DSDataHelper dataHelper;
    
    public BaseReportFiller() {
	super();
	dataHelper = new DSDataHelper();
    }

    public boolean isTrace() {
        return trace;
    }

    public void setTrace(boolean trace) {
        this.trace = trace;
    }

    // By ResultSet
    @Override
    public Document fillReport(Report report, DSResultSet resultSet) throws RTException {
	return fillReport(report, resultSet, null);
    }
    
    // By ResultSet
    @Override
    public Document fillReport(Report report, DSResultSet resultSet, Map<String, Object> parameters) throws RTException {
	if (resultSet != null) {
	    if (parameters == null) {
		parameters = createParameters();
	    }
	    parameters.put(ReportParameters.RESULT_SET, resultSet);
	}
	return fillReport(report, parameters);
    }    


    // By JDBC Connection
    @Override
    public Document fillReport(Report report, Connection connection) throws RTException {
	return fillReport(report, connection, null);
    }
	
    // By JDBC Connection
    @Override
    public Document fillReport(Report report, Connection connection, Map<String, Object> parameters) throws RTException {
	if (connection != null) {
	    if (parameters == null) {
		parameters = createParameters();
	    }
	    parameters.put(ReportParameters.JDBC_CONNECTION, connection);
	}
	return fillReport(report, parameters);
    }    

    
    // By DataConnector
    @Override
    public Document fillReport(Report report, DSDataConnector dataConnector) throws RTException {
	return fillReport(report, dataConnector, null);
    }
    
    // By DataConnector
    @Override
    public Document fillReport(Report report, DSDataConnector dataConnector, Map<String, Object> parameters) throws RTException {
	if (dataConnector != null) {
	    if (parameters == null) {
		parameters = createParameters();
	    }
	    parameters.put(ReportParameters.DATA_CONNECTOR, dataConnector);
	}
	return fillReport(report, parameters);
    }    

    
    // By Connection string
    @Override
    public Document fillReport(Report report, String connectionString) throws RTException {
	return fillReport(report, connectionString, null);
    }
    
    // By Connection string
    @Override
    public Document fillReport(Report report, String connectionString, Map<String, Object> parameters) throws RTException {
	if (connectionString != null) {
	    if (parameters == null) {
		parameters = createParameters();
	    }
	    parameters.put(ReportParameters.CONNECTION_STRING, connectionString);
	}
	return fillReport(report, parameters);
    }    
    
    
    // By Parameters (General)
    @Override
    public Document fillReport(Report report, Map<String, Object> parameters) throws RTException {
	Document document = new Document();
	
	// If report is empty then return empty document
	if (report == null || !report.hasTemplates()) {
	    return document;
	}

	// Prepare template fillers
	List<Template> templates = report.getTemplates();
	
	
	Map<Template, TemplateFiller> templateFillers = new HashMap<Template, TemplateFiller>();
	boolean first = true;
	for (Template template: templates) {
	    
	    // FIRST TEMPLATE
	    if (first) {
		first = false;
		PageSetup pageSetup = template.getPageSetup();
		if (pageSetup != null) {
		    document.setPageSetup(pageSetup);  
		}
	    }
	    
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
	
	// Defined report parameters
	List<DSParameter> reportParameters = report.getParameters();
	
	// Transfer DEFAULT report parameter values to general parameters map
	for (DSParameter parameter : reportParameters) {
	    Object value = parameter.getDefaultValue();
	    if (value == null) {
		continue;
	    }
	    String name = parameter.getName();
	    if (parameters.containsKey(name)) {
		continue;

	    }
	    parameters.put(name, value);
	}

	// Transfer ALL report parameter to data sources
	transferParametersToDataSources(report, parameters);

	// Initialize ReportContext
	ReportContext context = new ReportContext();

	context.setReport(report);
	context.setParameters(parameters);
	context.setDocument(document);
	
	// Initialize ScriptProvider
	ScriptProvider scriptProvider = getScriptProvider(report);
	
	// Initialize ExpressionEvaluator
	ExpressionEvaluator expressionEvaluator = scriptProvider.getExpressionEvaluator(); 
	context.setExpressionEvaluator(expressionEvaluator);
	
	// Initialize AggregationCalculator
	AggregationCalculator aggregationCalculator = createAggregationCalculator();
	context.setAggregationCalculator(aggregationCalculator);

	// Generate script (expression functions/ids)
	prepareScript(context, scriptProvider);
	
	// If data is null then Set empty data
	DSResultSet resultSet = openReportResultSet(context, parameters);
	DSDataSet dataSet = null;
	if (resultSet != null) {
	    dataSet = createWrappedDataSet(resultSet, report, parameters);
	}
	
	if (dataSet == null) {
	    dataSet = new DSEmptyDataSet();
	}
	
	context.setMainData(dataSet);
	
	// Fill report templates
	for (Template template: templates) {
	    TemplateFiller templateFiller = templateFillers.get(template);
	    templateFiller.fillTemplate(context, template);
	}
	
	return document;
    }
    
    protected AggregationCalculator createAggregationCalculator( ){
	AggregationCalculator aggregationCalculator =  new AggregationCalculator();
	aggregationCalculator.registerDefaultFunctions();
	return aggregationCalculator;
    }
    
    protected Map<String, Object> createParameters() {
	return  new HashMap<String, Object>();
    }
    
    protected TemplateFiller getTemplateFiller(String name) {
	return ReportEngine.getTemplateFiller(name);
    }
    
    protected void transferParametersToDataSources(Report report, Map<String, Object> parameters) {
	if (parameters == null || parameters.isEmpty() || !report.hasDataSources()) {
	    return;
	}
	List<DSDataSource> dataSources = report.getDataSources();
	List<DSParameter> reportParameters = report.getParameters();
	for (DSDataSource dataSource: dataSources) {
	    transferParametersToDataSource(dataSource, reportParameters, parameters);
	}
    }
    
    protected void transferParametersToDataSource(DSDataSource dataSource, List<DSParameter> generalParameters, Map<String, Object> parameters) {
	dataHelper.transferDefaultValues(dataSource, generalParameters, parameters);
    }
    
    protected DSResultSet openReportResultSet(ReportContext context, Map<String, Object> parameters) throws RTException {
	try {
	    
	    Report report = context.getReport();
	    Scope scope = context.getReportScope();
	    DSExpressionEvaluator evaluator = context.getExpressionEvaluator();
	    
	    // 1. ResultSet (priority)
	    DSResultSet resultSet = (DSResultSet) parameters.get(ReportParameters.RESULT_SET);
	    if (resultSet != null) {
		return resultSet;
	    }	    
	    
	    // DataSource
	    DSDataSource dataSource = (DSDataSource) parameters.get(ReportParameters.DATA_SOURCE);
	    if (dataSource == null) {
		dataSource = report.getDataSource();
	    }
	    if (dataSource == null) {
		// ???
		return null;
	    }
	    
	    

	    // 2. JDBC Connection 
	    Connection connection = (Connection) parameters.get(ReportParameters.JDBC_CONNECTION);
	    if (connection != null) {
		return openResultSet(connection, dataSource, scope, evaluator);
	    }
	    
	    // 3. DataConnector
	    DSDataConnector dataConnector = (DSDataConnector) parameters.get(ReportParameters.DATA_CONNECTOR);
	    if (dataConnector == null) {
		dataConnector = report.getDataConnector();
	    }
	    if (dataConnector != null) {
		return openResultSet(dataConnector, dataSource, scope, evaluator);
	    }

	    // 4. Connection string
	    String connectionString = (String) parameters.get(ReportParameters.CONNECTION_STRING);
	    if (connectionString != null) {
		return openResultSet(connectionString, dataSource, scope, evaluator);
	    }
	    
	    
	} catch (DSException ex) {
	    throw new RTException(ex);
	}
	return null;
    }
    
    /**
     * Open DSResultSet by JDBC Connection
     * @param connection 
     * @param dataSource
     * @return
     * @throws DSException
     */
    protected DSResultSet openResultSet(Connection connection, DSDataSource dataSource, Scope scope, DSExpressionEvaluator expressionEvaluator) throws DSException {
	if (connection == null) {
	    return null;
	}
	// Open Session by JDBC Connection
	DSSession session = DataManager.openSession(connection);
	return openResultSet(session, dataSource, scope, expressionEvaluator);
    }

    /**
     * Open DSResultSet by DataConnector
     * @param dataConnector
     * @param dataSource
     * @return
     * @throws DSException
     */
    protected DSResultSet openResultSet(DSDataConnector dataConnector, DSDataSource dataSource, Scope scope, DSExpressionEvaluator expressionEvaluator) throws DSException {
	if (dataConnector == null) {
	    return null;
	}
	// Open Session by DataConnector
	DSSession session = DataManager.openSession(dataConnector);
	return openResultSet(session, dataSource, scope, expressionEvaluator);
    }

    /**
     * Open DSResultSet by Connection String
     * @param connectionString
     * @param dataSource
     * @return
     * @throws DSException
     */
    protected DSResultSet openResultSet(String connectionString, DSDataSource dataSource, Scope scope, DSExpressionEvaluator expressionEvaluator) throws DSException {
	if (connectionString == null) {
	    return null;
	}
	// Open Session by Connection String
	DSSession session = DataManager.openSession(connectionString);
	return openResultSet(session, dataSource, scope, expressionEvaluator);
    }
    
    protected DSResultSet openResultSet(DSSession session, DSDataSource dataSource, Scope scope, DSExpressionEvaluator expressionEvaluator) throws DSException {
	if (session == null) {
	    return null;
	}
	return DataManager.openResultSet(session, dataSource, scope, expressionEvaluator);
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
	
	if (isTrace()) {
	    trace("Script: " + sourceCode);
	}
	
	ExpressionEvaluator expressionEvaluator = context.getExpressionEvaluator();
	if (expressionEvaluator == null) {
	    return;
	}
	expressionEvaluator.init(context.getReportScope(), scriptInfo);
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
    
    protected void trace(String message) {
	//TODO
	System.out.println(message);
    }
}
