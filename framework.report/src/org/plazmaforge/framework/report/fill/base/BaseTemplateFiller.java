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

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.plazmaforge.framework.core.datastorage.DSDataSet;
import org.plazmaforge.framework.core.datastorage.DSExpression;
import org.plazmaforge.framework.core.datastorage.DSField;
import org.plazmaforge.framework.core.datastorage.DSParameter;
import org.plazmaforge.framework.core.datastorage.DSResultSet;
import org.plazmaforge.framework.core.datastorage.DSVariable;
import org.plazmaforge.framework.core.datastorage.data.Scope;
import org.plazmaforge.framework.core.exception.DSException;
import org.plazmaforge.framework.report.exception.RTException;
import org.plazmaforge.framework.report.fill.AbstractTemplateFiller;
import org.plazmaforge.framework.report.fill.TemplateFiller;
import org.plazmaforge.framework.report.fill.process.ReportContext;
import org.plazmaforge.framework.report.fill.process.ReportScope;
import org.plazmaforge.framework.report.model.base.PageSetup;
import org.plazmaforge.framework.report.model.base.grid.Row;
import org.plazmaforge.framework.report.model.design.Band;
import org.plazmaforge.framework.report.model.design.GroupSection;
import org.plazmaforge.framework.report.model.design.Report;
import org.plazmaforge.framework.report.model.design.ReportGroup;
import org.plazmaforge.framework.report.model.design.ReportVariables;
import org.plazmaforge.framework.report.model.design.Template;
import org.plazmaforge.framework.report.model.design.TemplateStructure;
import org.plazmaforge.framework.report.model.document.Page;

/**
 * @author ohapon
 *
 */
public abstract class BaseTemplateFiller extends AbstractTemplateFiller implements TemplateFiller {
    

    public void fillTemplate(ReportContext context, Template template) throws RTException {
	
	PageSetup pageSetup = template.getPageSetup();

	context.resetTemplate();
	context.setPaging(template.isPaging());
	context.setStartX(pageSetup.getMargin().getLeft());
	context.setEndX(pageSetup.getSize().getWidth() - pageSetup.getMargin().getRight());
	context.setPageAreaWidth(context.getEndX() - context.getStartX());
	
	context.setStartY(pageSetup.getMargin().getTop());
	context.setEndY(pageSetup.getSize().getHeight() - pageSetup.getMargin().getBottom());
	context.setPageAreaHeight(context.getEndY() - context.getStartY());
	
	if (context.getPageAreaWidth() <= 0) {
	    throw new RuntimeException("PageAreaWidth <= 0");
	}
	if (context.getPageAreaHeight() <= 0) {
	    throw new RuntimeException("PageAreaHeight <= 0");
	}
	
	
	// Get report template structure
	TemplateStructure structure = TemplateStructure.create(template); 
	
	int templateHeight = TemplateStructure.calculateTemplateHeight(structure);
	if (templateHeight > context.getPageAreaHeight()) {
	    throw new RuntimeException("TemplateHeight > PageAreaHeight");
	}
	
	
	context.setTemplate(template);	
	context.setTemplateStructure(structure);
		
	Band reportHeader = structure.getReportHeader();
	Band pageHeader = structure.getPageHeader();
	Band columnHeader = structure.getColumnHeader();
	Band detail = structure.getDetail();
	Band columnFooter = structure.getColumnFooter();
	Band pageFooter = structure.getPageFooter();
	Band reportFooter = structure.getReportFooter();
	
	List<GroupSection> groupSections = structure.getGroups();
	
	context.setPageHeaderHeight(pageHeader == null ? 0 : TemplateStructure.calculateBandHeight(pageHeader, true));
	context.setPageFooterHeight(pageFooter == null ? 0 : TemplateStructure.calculateBandHeight(pageFooter, true));
	
	// Get report data
	DSResultSet reportData = context.getMainData();
	boolean isEmptyData = reportData == null;
	
	
	initParameters(context);
	initFields(context);
	initVariables(context);
	
	// ON-INIT-TEMPLATE
	onInitTemplatePage(context);
	
	// Get first record
	if (!isEmptyData) {
	    isEmptyData = !next(context);
	}
	
	// Add first page
	startNewPage(context);

	fillPageHeader(context, pageHeader);
	fillReportHeader(context, reportHeader);
	
	if (!isEmptyData) {
	
	    // Evaluate
	    evaluateVariableValues(context);
	    evaluateGroupValues(context, groupSections);
	    
	    // ON-RECORD
	    onRecord(context);
	    
	    // Fill column header
	    fillColumnHeader(context, columnHeader);
	    
	    // Fill group headers
	    fillGroupHeaders(context, groupSections, true);
	    
	    // Fill first detail of first record (because first record is loaded)
	    fillDetail(context, detail);
	    
	    // Fill next details
	    while (next(context)) {
		
		// Evaluate
		evaluateVariableValues(context);
		evaluateGroupValues(context, groupSections);

		// ON-RECORD
		onRecord(context);
		
		fillGroupFooters(context, groupSections);
		
		// Reset group values and recalculate (after group footers because footer use old values)
		resetGroupVariableValues(context, groupSections);
		
		fillGroupHeaders(context, groupSections);
		
		fillDetail(context, detail);
		
		
	    }
	    
	    // Fill group footers
	    fillGroupFooters(context, groupSections, true);
	    
	    fillColumnFooter(context, columnFooter);
	}
	
	fillReportFooter(context, reportFooter);
	fillPageFooter(context, pageFooter);	
	
    }
    
    protected boolean next(ReportContext context) throws RTException {

	boolean flag = false;


	// Get main ResultSet
	DSResultSet resultSet = context.getMainData();

	try {
	    flag = resultSet == null ? false : resultSet.next();
	    if (flag) {
		ReportScope scope = context.getReportScope();
		
		// Calculate RecordNo
		int recordNo = scope.getRecordNo();
		scope.setVariableOldValue(ReportVariables.RECORD_NO, recordNo);

		recordNo++;
		scope.setRecordNo(recordNo);
		scope.setVariableValue(ReportVariables.RECORD_NO, recordNo);

		// ON-NEXT
		onNext(context);
		
		DSField[] fields = scope.getFields();
		
		if (fields == null) {
		    // TODO: Log warn: No fields in report data!
		    return flag;
		}

		for (DSField field: fields) {
		    
		    String fieldName = field.getName();
		    
		    if (fieldName == null){
			continue;
		    }
		    // 1. Simple field name
		    transferFieldValue(resultSet, scope, fieldName);
		    
		    // 2. Full field name (DataSource.Field)
		    String fieldFullName = field.getProperty("$fullName");
		    if (fieldFullName != null && !fieldFullName.equals(fieldName)) {
			transferFieldValue(resultSet, scope, fieldFullName);
		    }

		}
	    }

	} catch (DSException ex) {
	    throw new RTException(ex);

	}
	return flag;
    }
    
    protected void transferFieldValue(DSResultSet resultSet, ReportScope scope, String fieldName) throws DSException {
	// Get
	Object oldValue = scope.getFieldValue(fieldName);
	Object newValue = resultSet.getValue(fieldName);

	// Set
	scope.setFieldOldValue(fieldName, oldValue);
	scope.setFieldValue(fieldName, newValue);

    }
    
    protected Page createPage(ReportContext context) {
	
	Template template = context.getTemplate();
	PageSetup pageSetup = template.getPageSetup();
	
	Page page = new Page();
	
	// Set page size
	if (pageSetup.hasSize()) {
	    page.setSize(pageSetup.getSize().clone());
	}

	// Set page margin
	if (pageSetup.hasMargin()) {
	    page.setMargin(pageSetup.getMargin().clone());
	}
	
	return page;
    }

    protected void startNewPage(ReportContext context) {
	startNewPage(context, false);
    }
    
    protected void startNewPage(ReportContext context, boolean forcePageFooter) {

	// Fill footer of old page
	if (!context.isFirstPage()) {
	    fillPageFooter(context, context.getTemplateStructure().getPageFooter(), forcePageFooter);
	}
	
	context.setFirstPage(false);
	context.setNewPage(true);
	
	
	
	
	// Reset offsetY
	context.setOffsetY(context.getStartY());
	 
	// Create new page
	Page page = createPage(context);
	context.getDocument().addPage(page);
	context.setPage(page);
	
	//TODO: Use events : onPage
	Integer pageNo = (Integer) context.getReportScope().getVariableValue(ReportVariables.PAGE_NO);
	if (pageNo == null) {
	    pageNo = 0;
	}
	pageNo++;
	context.getReportScope().setVariableValue(ReportVariables.PAGE_NO, pageNo);
	
	// Call type filler
	prepareNewPage(context);
	
	
	// Fill header of new page
	fillPageHeader(context, context.getTemplateStructure().getPageHeader());
    }
    
    protected abstract void prepareNewPage(ReportContext context);
    
    protected void fillPageHeader(ReportContext context, Band band) {
	fillPageHeader(context, band, false);
    }
	
    protected void fillPageHeader(ReportContext context, Band band, boolean force) {
	if (band == null) {
	    return;
	}
	
	// Reset offsetY
	context.setOffsetY(context.getStartY());
	
	fillBand(context, band, force, false);
    }

    protected void fillPageFooter(ReportContext context, Band band) {
	fillPageFooter(context, band, false);
    }
	
    protected void fillPageFooter(ReportContext context, Band band, boolean force) {
	if (band == null) {
	    return;
	}
	context.setBand(band);
	
	boolean isPrint = force ? true : evaluatePrintExpression(context, DSExpression.EVALUATION_DEFAULT, band);
	
	if (!isPrint) {
	    return;
	}
	
	context.setNewPage(false);
	
	
	// Call type filler 
	preparePageFooter(context, band);
	
	
	fillBand(context, band, isPrint, false);
    }

    protected abstract void preparePageFooter(ReportContext context, Band band);
    
    protected void fillReportHeader(ReportContext context, Band band) {
	fillBand(context, band);
    }

    protected void fillReportFooter(ReportContext context, Band band) {
	fillBand(context, band);
    }

    protected void fillColumnHeader(ReportContext context, Band band) {
	fillBand(context, band);
    }

    protected void fillColumnFooter(ReportContext context, Band band) {
	fillBand(context, band);
    }

    protected void fillDetail(ReportContext context, Band band) {
	
	if (band == null) {
	    return;
	}
	
	context.setBand(band);
	
	boolean isPrint = evaluatePrintExpression(context, DSExpression.EVALUATION_DEFAULT, band);
	
	if (!isPrint) {
	    return;
	}

	context.setNewPage(false);
	fillBand(context, band, isPrint, context.isPaging());
    }
    
    protected void fillBand(ReportContext context, Band band) {
	fillBand(context, band, false, context.isPaging());
    }
    
    
    protected void fillGroupFooter(ReportContext context, int evaluation, Band band, boolean force, boolean paging) {
	
	if (band == null) {
	    return;
	}
	
	context.setBand(band);
	
	boolean isPrint = force ? true : evaluatePrintExpression(context, evaluation, band);
	
	if (!isPrint) {
	    return;
	}

	context.setNewPage(false);
	fillBand(context, evaluation, band, isPrint, context.isPaging());
    }
    
    protected void fillBand(ReportContext context, Band band, boolean force, boolean paging) {
	fillBand(context, DSExpression.EVALUATION_DEFAULT, band, force, paging);
    }
	
    protected void fillBand(ReportContext context, int evaluation, Band band, boolean force, boolean paging) {
	if (band == null) {
	    return;
	}
	
	context.setBand(band);
	
	boolean isPrint = force ? true : evaluatePrintExpression(context, evaluation, band);
	
	if (!isPrint) {
	    return;
	}
		
	Band fillContainer = createFillContainer(context, evaluation, band);
	if (fillContainer == null) {
	    return;
	}
	
	fillContainer(context, evaluation, fillContainer, paging);
    }
    
    protected abstract Band createFillContainer(ReportContext context, int evaluation, Band band);
    
    protected abstract void fillContainer(ReportContext context, int evaluation, Band fillContainer, boolean paging);
    
    
    protected Row createRow(ReportContext context) {
	Row row = new Row();
	Band band = context.getBand();
	if (band == null) {
	    return row;
	}
	
	// Transfer band attributes to row
	if (band.getBackground() != null) {
	    row.setBackground(band.getBackground());
	}
	if (band.getForeground() != null) {
	    row.setForeground(band.getForeground());
	}
	if (band.getFont() != null) {
	    row.setFont(band.getFont());
	}
	
	return row;
    }
    
    

    protected void fillGroupHeaders(ReportContext context, List<GroupSection> groupSections) {
	fillGroupHeaders(context, groupSections, false);
    }
    
    protected void fillGroupHeaders(ReportContext context, List<GroupSection> groupSections, boolean fillAll) {
	if (groupSections == null || groupSections.isEmpty()) {
	    return;
	}
	int count = groupSections.size();
	GroupSection groupSection = null;
	Band groupHeader = null;
	for (int i = 0; i < count; i++) {
	    groupSection = groupSections.get(i);
	    groupHeader = groupSection.getGroupHeader();
	    if (groupHeader == null || (!fillAll && !groupSection.isModify())) {
		continue;
	    }
	    
	    if (!context.isNewPage() && groupSection.getGroup().isStartOnNewPage()) {
		startNewPage(context);
	    }
	    
	    fillBand(context, groupHeader, false, context.isPaging());
	    
	}
    }
    
    protected void fillGroupFooters(ReportContext context, List<GroupSection> groupSections) {
	fillGroupFooters(context, groupSections, false);
    }
    
    protected void fillGroupFooters(ReportContext context, List<GroupSection> groupSections, boolean fillAll) {
	if (groupSections == null || groupSections.isEmpty()) {
	    return;
	}
	int count = groupSections.size();
	GroupSection groupSection = null;
	Band groupFooter = null;
	int evaluation = fillAll ? DSExpression.EVALUATION_DEFAULT : DSExpression.EVALUATION_OLD;
	for (int i = count - 1; i >= 0; i--) {
	    groupSection = groupSections.get(i);
	    groupFooter = groupSection.getGroupFooter();
	    if (groupFooter == null || (!fillAll && !groupSection.isModify())) {
		continue;
	    }
	    fillGroupFooter(context, evaluation, groupFooter, false, context.isPaging());
	}	
	
    }
    
    
    protected void initParameters(ReportContext context) {
	Report report = context.getReport();
	ReportScope scope = context.getReportScope();
	
	// Transfer report parameters to scope
	scope.setParameters(report.getParameters().toArray(new DSParameter[0]));
	
	// Transfer report parameter values to scope
	Map<String, Object> parameters = context.getParameters();
	Set<Map.Entry<String, Object>> entries = parameters.entrySet();
	for (Map.Entry<String, Object> entry: entries){
	    String name = entry.getKey();
	    Object value = entry.getValue();
	    scope.setScopeValue(Scope.PARAMETER, name, DSExpression.EVALUATION_DEFAULT, value);
	}

    }
    
    protected void initFields(ReportContext context) {

	ReportScope scope = context.getReportScope();

	// Get main data
	DSDataSet mainData = context.getMainData();

	List<DSField> fieldList = null;
	DSField[] fields = null;
	
	if (mainData != null) {
	    fieldList = mainData.getFields();
	    fields = fieldList == null ? new DSField[0] : fieldList.toArray(new DSField[0]);
	}
	
	// Transfer report fields to scope	
	scope.setFields(fields);

	
	/*

	// Get main data source
	DSDataSource dataSource = context.getMainDataSource();

	// Get main data
	DSResultSet data = context.getMainData();

	List<DSField> fieldList = null;
	DSField[] fields = null;

	// Get fields

	// 1. Get field names by Main Data Source
	if (dataSource != null && dataSource.hasFields()) {
	    fieldList = dataSource.getFields();
	    fields = fieldList == null ? new DSField[0] : fieldList.toArray(new DSField[0]);
	}

	// 2. Get field names by Main Data
	if (fields == null && (data instanceof DSStructuredResultSet)) {
	    List<String> fieldNames = ((DSStructuredResultSet) data).getFieldNames();
	    if (fieldNames == null) {
		fields = new DSField[0];
	    } else {
		fields = new DSField[fieldNames.size()];
		for (int i = 0; i < fieldNames.size(); i++) {
		    String fieldName = fieldNames.get(i);
		    DSField field = new DSField();
		    field.setName(fieldName);
		    fields[i] = field;
		}
	    }
	}

	// 3. Get field names by Template
	if (fields == null) {
	    // TODO: Find fields in template
	    fields = new DSField[0];
	}

	// Transfer report fields to scope	
	scope.setFields(fields);
	
	*/
    }    
    
    protected void initVariables(ReportContext context) {
	Report report = context.getReport();
	ReportScope scope = context.getReportScope();
	
	// Transfer report variables to scope
	scope.setVariables(report.getVariables().toArray(new DSVariable[0]));

	// Calculate system variables
	scope.setVariableValue(ReportVariables.COLUMN_NO, 1);
	scope.setVariableValue(ReportVariables.PAGE_NO, 0);
	scope.setVariableValue(ReportVariables.RECORD_NO, 0);
	
	// Initialize variable values
	evaluateVariableValues(context, DSExpression.EVALUATION_DEFAULT, true);

    }
    
    protected void evaluateGroupValues(ReportContext context, List<GroupSection> groupSections) {
	if (groupSections == null || groupSections.isEmpty()) {
	    return;
	}
	int count = groupSections.size();
	GroupSection groupSection = null;
	ReportGroup group = null;
	String groupName = null;
	ReportScope scope = context.getReportScope();
	
	int evaluation = DSExpression.EVALUATION_DEFAULT;
	// Evaluate values and set modify flags
	for (int i = 0; i < count; i++) {
	    groupSection = groupSections.get(i);
	    
	    // Reset modify flag
	    groupSection.setModify(false);
	    
	    group = groupSection.getGroup();
	    groupName = group.getName();
	    DSExpression expression = group.getExpression();
	    if (expression == null) {
		continue;
	    }
	    
	    // Shift cur value to old value
	    Object oldValue = scope.getGroupValue(groupName); 
	    Object newValue = evaluateExpression(context, evaluation, expression);

	    // Set modify flag
	    boolean modify = scope.isNewGroup(oldValue, newValue);
	    groupSection.setModify(modify);
	    
	    scope.setGroupOldValue(groupName, oldValue);
	    scope.setGroupValue(groupName, newValue);
	    
	    
	}
	
	// Set modify flags for child groups
	for (int i = 0; i < count; i++) {
	    groupSection = groupSections.get(i);
	    if (groupSection.isModify() && i < count - 1) {
		for (int j = 0; j < count; j++) {
		    groupSection = groupSections.get(j);
		    groupSection.setModify(true);
		}
	    }
	}
	    
    }

    
    protected void evaluateVariableValues(ReportContext context) {
	evaluateVariableValues(context, DSExpression.EVALUATION_DEFAULT, false);
    }

    protected void evaluateVariableValues(ReportContext context, int evaluation) {
	evaluateVariableValues(context, evaluation, false);
    }
	
    protected void evaluateVariableValues(ReportContext context, int evaluation, boolean init) {
	ReportScope scope = context.getReportScope();
	DSVariable[] variables = scope.getVariables();
	if (variables.length == 0) {
	    return;
	}
	
	for (DSVariable variable: variables) {
	    
	    // Skip system variables
	    if (ReportVariables.isSystemVariable(variable.getName())) {
		continue;
	    }
	    evaluateVariableValue(context, variable, evaluation, init);
	}
    }
    
    protected void evaluateVariableValue(ReportContext context, DSVariable variable, int evaluation, boolean init) {
	
	ReportScope scope = context.getReportScope();
	DSExpression expression = null;
	Object value = null;
	Object oldValue = null;

	expression = init ? variable.getInitExpression() : variable.getExpression();

	// Evaluate expression
	value = expression == null ? null : evaluateExpression(context, evaluation, expression);

	// Calculate aggregation value
	if (!init && variable.getAggregation() != null) {
	    value = context.getAggregationCalculator().calculateValue(context.getReportScope(), variable.getName(), variable.getAggregation(), value);
	}

	oldValue = scope.getVariableValue(variable.getName());

	if (init) {
	    scope.setVariableInitValue(variable.getName(), value);
	}
	scope.setVariableOldValue(variable.getName(), oldValue);
	scope.setVariableValue(variable.getName(), value);

    }
    
    
    protected void resetVariableValues(ReportContext context, String resetType, String resetValue) {
	if (resetType == null) {
	    return;
	}
	ReportScope scope = context.getReportScope();
	DSVariable[] variables = scope.getVariables();
	if (variables.length == 0) {
	    return;
	}
	Object oldValue = null;
	Object initValue = null;
	for (DSVariable variable : variables) {

	    // Skip system variables
	    if (ReportVariables.isSystemVariable(variable.getName())) {
		continue;
	    }

	    if (variable.getResetType() == null || !resetType.equals(variable.getResetType())) {
		continue;
	    }

	    if (resetValue != null && (variable.getResetValue() == null || !resetValue.equals(variable.getResetValue()))) {
		continue;
	    }

	    oldValue = scope.getVariableValue(variable.getName());
	    initValue = scope.getVariableInitValue(variable.getName());

	    scope.setVariableOldValue(variable.getName(), oldValue);
	    scope.setVariableValue(variable.getName(), initValue); // Reset value
	    scope.setVariableAggregationValue(variable.getName(), null); // Reset aggregation value

	    // Recalculation new values
	    evaluateVariableValue(context, variable, DSExpression.EVALUATION_DEFAULT, false);
	}
    }
    
    
    
    
    protected void resetGroupVariableValues(ReportContext context, List<GroupSection> groupSections) {
	if (groupSections == null || groupSections.isEmpty()) {
	    return;
	}
	int count = groupSections.size();
	GroupSection groupSection = null;
	ReportGroup group = null;
	String groupName = null;
	for (int i = 0; i < count; i++) {
	    groupSection = groupSections.get(i);

	    if (!groupSection.isModify()) {
		continue;
	    }

	    group = groupSection.getGroup();
	    groupName = group.getName();

	    resetVariableValues(context, "Group", groupName);

	}
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // EVENTS
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void onInitTemplatePage(ReportContext context) {
	// TODO: Must fire event
    }
    
    protected void onStartPage(ReportContext context) {
	// TODO: Must fire event
    }
    
    protected void onNext(ReportContext context) {
	context.getExpressionEvaluator().resetScope();
	// TODO: Must fire event
    }

    protected void onRecord(ReportContext context) {
	// TODO: Must fire event
    }

    
    protected void handleDSException(DSException ex) throws RTException {
	if (ex == null) {
	    return;
	}
	throw new RTException(ex);
    }

    protected Integer getCastInteger(Object value) {
	return getCastInteger(value, null);
    }
    
    protected Integer getCastInteger(Object value, Integer def) {
	return value == null || !(value instanceof Number) ? def : ((Number) value).intValue();
	
    }
    
    protected Double getCastDouble(Object value) {
	return getCastDouble(value, null);
    }
    
    protected Double getCastDouble(Object value, Double def) {
	return value == null || !(value instanceof Number) ? def : ((Number) value).doubleValue();
	
    }
}
