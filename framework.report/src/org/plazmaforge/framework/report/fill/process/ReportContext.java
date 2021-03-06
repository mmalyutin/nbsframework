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
package org.plazmaforge.framework.report.fill.process;

import java.util.HashMap;
import java.util.Map;

import org.plazmaforge.framework.core.datastorage.DSDataSet;
import org.plazmaforge.framework.core.datastorage.data.AggregationCalculator;
import org.plazmaforge.framework.report.fill.script.ExpressionEvaluator;
import org.plazmaforge.framework.report.model.base.grid.Grid;
import org.plazmaforge.framework.report.model.design.Band;
import org.plazmaforge.framework.report.model.design.BandType;
import org.plazmaforge.framework.report.model.design.Report;
import org.plazmaforge.framework.report.model.design.Template;
import org.plazmaforge.framework.report.model.design.TemplateStructure;
import org.plazmaforge.framework.report.model.document.Document;
import org.plazmaforge.framework.report.model.document.Page;

/**
 * @author ohapon
 *
 */
public class ReportContext {

    private Report report;
    
    private DSDataSet mainData;
    
    private Map<String, Object> parameters;
    
    private Template template;
    
    private Band band;
    
    private Document document;
    
    private Page page;
    
    private boolean paging;
    
    private boolean endData;
    
    //Only for Table and Crosstab report
    private Grid grid;
    
    
    private TemplateStructure templateStructure;
    
    /**
     * Current X position
     */
    private int offsetX;
    
    /**
     * Current Y position
     */
    private int offsetY;

    /**
     * Start X position
     */
    private int startX;
    
    /**
     * End X position
     */
    private int endX;
    
    
    /**
     * Start Y position
     */
    private int startY;
    
    /**
     * End Y position
     */
    private int endY;

    
    private int totalHeight;
    
    /**
     * Page width 
     */
    private int pageWidth;
    
    /**
     * Page height
     */
    private int pageHeight;
    
    
    /**
     * Page margin left
     */
    private int pageMarginLeft;
    

    /**
     * Page margin top
     */
    private int pageMarginTop;
    
    
    /**
     * Page margin right
     */
    private int pageMarginRight;


    /**
     * Page margin bottom
     */
    private int pageMarginBottom;

    
    /**
     * Printable width 
     */
    private int pageAreaWidth;
    
    /**
     * Printable height
     */
    private int pageAreaHeight;
    
    
    private int pageHeaderHeight;
    
    
    private int pageFooterHeight;
    
    
    private int columnHeaderHeight;
    
    
    private int columnFooterHeight;

    
    private ReportScope reportScope;
    
    private boolean newPage;
    
    private boolean newColumn;
    
    private boolean firstPage;
    
    /**
     * True if need force new page
     */
    private boolean forcePage;
    
    /**
     * Band where new page was forced 
     */
    private BandType forcePageBand;
    
    
    private ExpressionEvaluator expressionEvaluator;
    
    private AggregationCalculator aggregationCalculator;

    /**
     * Push flag of bands. True if band was pushed.
     */
    private Map<BandType, Boolean> pushBandMap; 
    
    /**
     * Fill flag of bands. True if band was filled.
     */
    private Map<BandType, Boolean> fillBandMap;
    

    /**
     * Push flag of bands. True if band was pushed.
     */
    private Map<BandType, Boolean> pushBandInTemplateMap; 

    /**
     * True if ColumnHeader on first page only
     */
    private boolean columnHeaderOnFirstPage;
    
    
    public ReportContext() {
	// Set first page by default
	firstPage = true;
	forcePage = false;
	pushBandMap = new HashMap<BandType, Boolean>();
	fillBandMap = new HashMap<BandType, Boolean>();
	
	pushBandInTemplateMap = new HashMap<BandType, Boolean>();
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public DSDataSet getMainData() {
        return mainData;
    }

    public void setMainData(DSDataSet mainData) {
        this.mainData = mainData;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public boolean isPaging() {
        return paging;
    }

    public void setPaging(boolean paging) {
        this.paging = paging;
    }

    public boolean isEndData() {
        return endData;
    }

    public void setEndData(boolean endData) {
        this.endData = endData;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public TemplateStructure getTemplateStructure() {
        return templateStructure;
    }

    public void setTemplateStructure(TemplateStructure templateStructure) {
        this.templateStructure = templateStructure;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }
    
    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }
    
    public int getTotalHeight() {
        return totalHeight;
    }

    public void setTotalHeight(int totalHeight) {
        this.totalHeight = totalHeight;
    }

    public int getPageWidth() {
        return pageWidth;
    }

    public void setPageWidth(int pageWidth) {
        this.pageWidth = pageWidth;
    }

    public int getPageHeight() {
        return pageHeight;
    }

    public void setPageHeight(int pageHeight) {
        this.pageHeight = pageHeight;
    }

    public int getPageMarginLeft() {
        return pageMarginLeft;
    }

    public void setPageMarginLeft(int pageMarginLeft) {
        this.pageMarginLeft = pageMarginLeft;
    }

    public int getPageMarginTop() {
        return pageMarginTop;
    }

    public void setPageMarginTop(int pageMarginTop) {
        this.pageMarginTop = pageMarginTop;
    }

    public int getPageMarginRight() {
        return pageMarginRight;
    }

    public void setPageMarginRight(int pageMarginRight) {
        this.pageMarginRight = pageMarginRight;
    }

    public int getPageMarginBottom() {
        return pageMarginBottom;
    }

    public void setPageMarginBottom(int pageMarginBottom) {
        this.pageMarginBottom = pageMarginBottom;
    }

    public int getPageAreaWidth() {
        return pageAreaWidth;
    }

    public void setPageAreaWidth(int pageAreaWidth) {
        this.pageAreaWidth = pageAreaWidth;
    }

    public int getPageAreaHeight() {
        return pageAreaHeight;
    }

    public void setPageAreaHeight(int pageAreaHeight) {
        this.pageAreaHeight = pageAreaHeight;
    }

    public int getPageHeaderHeight() {
        return pageHeaderHeight;
    }

    public void setPageHeaderHeight(int pageHeaderHeight) {
        this.pageHeaderHeight = pageHeaderHeight;
    }

    public int getPageFooterHeight() {
        return pageFooterHeight;
    }

    public void setPageFooterHeight(int pageFooterHeight) {
        this.pageFooterHeight = pageFooterHeight;
    }

    public int getColumnHeaderHeight() {
        return columnHeaderHeight;
    }

    public void setColumnHeaderHeight(int columnHeaderHeight) {
        this.columnHeaderHeight = columnHeaderHeight;
    }

    public int getColumnFooterHeight() {
        return columnFooterHeight;
    }

    public void setColumnFooterHeight(int columnFooterHeight) {
        this.columnFooterHeight = columnFooterHeight;
    }

    public ReportScope getReportScope() {
	if (reportScope == null) {
	    reportScope = new ReportScope();
	}
        return reportScope;
    }

    public void setReportScope(ReportScope reportScope) {
        this.reportScope = reportScope;
    }

    public boolean isNewPage() {
        return newPage;
    }

    public void setNewPage(boolean newPage) {
        this.newPage = newPage;
        this.forcePage = false;
        this.forcePageBand = null;
        this.pushBandMap.clear(); // TODO: Maybe only for page bands (PageHeader/Footer, ColumnHeader/Footer)
        
    }

    public boolean isNewColumn() {
        return newColumn;
    }

    public void setNewColumn(boolean newColumn) {
        this.newColumn = newColumn;
    }

    public boolean isFirstPage() {
        return firstPage;
    }

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

    public boolean isForcePage() {
        return forcePage;
    }

    public void setForcePage(boolean forcePage) {
        this.forcePage = forcePage;
    }

    public BandType getForcePageBand() {
        return forcePageBand;
    }

    public void setForcePageBand(BandType forcePageBand) {
        this.forcePageBand = forcePageBand;
    }

    public void resetTemplate() {
	this.template = null;
	this.templateStructure = null;
	this.band = null;

	this.startX = 0;
	this.endX = 0;
	this.startY = 0;
	this.endY = 0;

	this.totalHeight = 0;
	
	this.pageWidth = 0;
	this.pageHeight = 0;
	this.pageMarginLeft = 0;
	this.pageMarginTop = 0;
	this.pageMarginRight = 0;
	this.pageMarginBottom = 0;
	
	this.pageAreaWidth = 0;
	this.pageAreaHeight = 0;
	
	this.pageHeaderHeight = 0;
	this.pageFooterHeight = 0;

	this.newPage = false;
	this.newColumn = false;
	this.firstPage = true; // Set first page by default
	this.forcePage = false;
	
	columnHeaderOnFirstPage = false;
	
	resetPage();
	resetBands();
    }

    private void resetPage() {
	this.offsetX = 0;
	this.offsetY = 0;
    }
    
    private void resetBands() {
	pushBandMap.clear();
	fillBandMap.clear();
	
	pushBandInTemplateMap.clear();
    }
    
    public Object getScopeValue(String context, String name, int evaluation) {
	if (reportScope == null) {
	    return null;
	}
	return reportScope.getScopeValue(context, name, evaluation);
    }

    public ExpressionEvaluator getExpressionEvaluator() {
        return expressionEvaluator;
    }

    public void setExpressionEvaluator(ExpressionEvaluator expressionEvaluator) {
        this.expressionEvaluator = expressionEvaluator;
    }

    public AggregationCalculator getAggregationCalculator() {
        return aggregationCalculator;
    }

    public void setAggregationCalculator(AggregationCalculator aggregationCalculator) {
        this.aggregationCalculator = aggregationCalculator;
    }

    public boolean isColumnHeaderOnFirstPage() {
        return columnHeaderOnFirstPage;
    }

    public void setColumnHeaderOnFirstPage(boolean columnHeaderOnFirstPage) {
        this.columnHeaderOnFirstPage = columnHeaderOnFirstPage;
    }

    public void pushBand(BandType type, boolean flag) {
	pushBandMap.put(type, flag);
	pushBandInTemplateMap.put(type, flag);
    }
    
    public void pushBand(BandType type) {
	pushBand(type, true);
    }
    
    public boolean isPushBand(BandType type) {
	Boolean flag = pushBandMap.get(type);
	return flag == null ? false: flag;
    }

    public boolean isPushBandInTemplate(BandType type) {
	Boolean flag = pushBandInTemplateMap.get(type);
	return flag == null ? false: flag;
    }
    
    public void fillBand(BandType type, boolean flag) {
	fillBandMap.put(type, flag);
    }
    
    public void fillBand(BandType type) {
	fillBand(type, true);
    }
    
    public boolean isFillBand(BandType type) {
	Boolean flag = fillBandMap.get(type);
	return flag == null ? false: flag;
    }
    
}
