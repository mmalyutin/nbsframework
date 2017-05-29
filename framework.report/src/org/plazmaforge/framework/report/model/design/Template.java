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
package org.plazmaforge.framework.report.model.design;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.core.data.LocalizedIdentifier;
import org.plazmaforge.framework.core.datastorage.DSExpression;
import org.plazmaforge.framework.core.datastorage.HasExpressionBuilder;
import org.plazmaforge.framework.report.model.base.Element;
import org.plazmaforge.framework.report.model.base.PageSetup;
import org.plazmaforge.framework.report.model.base.Pen;
import org.plazmaforge.framework.report.model.base.grid.BaseColumnModel;
import org.plazmaforge.framework.report.model.base.grid.CellBorderRule;
import org.plazmaforge.framework.report.model.base.grid.Column;
import org.plazmaforge.framework.report.model.base.grid.ColumnModel;
import org.plazmaforge.framework.report.model.base.grid.HasCellBorderRule;

/**
 * @author ohapon
 * 
 * The Report Template has bands
 *
 */
public class Template implements Serializable, LocalizedIdentifier, ColumnModel, BandModel, HasExpressionBuilder, HasCellBorderRule {

    
    private static final long serialVersionUID = 5671682441505999936L;
    

    /**
     * Id of report
     */
    private String id;
    

    /**
     * Name of report
     */
    private String name;
    
    /**
     * Caption (NLS display name) of template
     */
    private String caption;
    
    /**
     * Caption (NLS description) of template
     */
    private String description;
    
    /**
     * Template type: Table, Crosstab or null
     */
    private String type;
    

    private BandModel bandModel;
    
    /**
     * List of report groups
     */
    private List<ReportGroup> groups;

    
    /**
     * Own ColumnModel. Only for TableReport
     */
    private ColumnModel columnModel;

    /**
     * Border rule of cell: column, column_inner, row, row_inner, inner, all. Only for TableReport
     */
    private CellBorderRule cellBorderRule;
    
    
    /**
     * Cell border line. Only for cell border rule and TableReport
     */
    private Pen cellLine;
    
    /**
     * Column border line. Only for cell border rule and TableReport
     */
    private Pen columnLine;
    
    /**
     * Row border line. Only for cell border rule and TableReport
     */
    private Pen rowLine;
    
    /**
     * Page Setup
     */
    private PageSetup pageSetup;
    
    /**
     * True if need paging
     */
    private boolean paging;
    
    
    private boolean reportHeaderOnPage;
    
    private boolean reportFooterOnPage;
    
    
    public Template() {
	bandModel = new BaseBandModel();
	columnModel = new BaseColumnModel();
	paging = true;
    }
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDisplayName() {
  	return getCaption() == null ? getName() : getCaption();
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public List<Band> getBands() {
	return bandModel.getBands();
    }
    
    @Override
    public Band getBand(int index) {
	return bandModel.getBand(index);
    }

    @Override
    public void setBands(List<Band> bands) {
	bandModel.setBands(bands);
    }
    
    @Override
    public void addBand(Band band) {
	bandModel.addBand(band);
    }

    @Override
    public void removeBand(Band band) {
	bandModel.removeBand(band);
    }
    
    @Override
    public boolean hasBands() {
	return bandModel.hasBands();
    }

    @Override
    public int getBandCount() {
	return bandModel.getBandCount();
    }

    public boolean isEmpty() {
	return !hasBands(); 
    }
    
    /**
     * Return first band by type
     * @param type
     * @return
     */
    @Override
    public Band findBandByType(BandType type) {
	return bandModel.findBandByType(type);
    }
    
    /**
     * Return first band by type
     * @param type
     * @return
     */
    @Override
    public Band findBandByType(String type) {
	return bandModel.findBandByType(type);
    }

    @Override
    public List<Column> getColumns() {
	return columnModel.getColumns();
    }
    
    @Override
    public Column getColumn(int index) {
	return columnModel.getColumn(index);
    }

    @Override
    public void setColumns(List<Column> columns) {
	columnModel.setColumns(columns);
    }

    @Override
    public void addColumn(Column column) {
	columnModel.addColumn(column);
    }

    @Override
    public void removeColumn(Column column) {
	columnModel.removeColumn(column);
    }

    @Override
    public boolean hasColumns() {
	return columnModel.hasColumns();
    }

    @Override
    public int getColumnCount() {
	return columnModel.getColumnCount();
    }

    ////
    
    public CellBorderRule getCellBorderRule() {
        return cellBorderRule;
    }

    public void setCellBorderRule(CellBorderRule cellBorderRule) {
        this.cellBorderRule = cellBorderRule;
    }

    public Pen getCellLine() {
        return cellLine;
    }

    public void setCellLine(Pen cellLine) {
        this.cellLine = cellLine;
    }

    public boolean isEmptyCellLine() {
	return cellLine == null || cellLine.isEmpty();
    }
    
    public Pen getColumnLine() {
        return columnLine;
    }

    public void setColumnLine(Pen columnLine) {
        this.columnLine = columnLine;
    }

    public boolean hasColumnLine() {
	return columnLine != null;
    }

    public boolean isEmptyColumnLine() {
	return columnLine == null || columnLine.isEmpty();
    }
    
    public Pen getRowLine() {
        return rowLine;
    }

    public void setRowLine(Pen rowLine) {
        this.rowLine = rowLine;
    }

    public boolean isEmptyRowLine() {
	return rowLine == null || rowLine.isEmpty();
    }

    ////
    
    public ReportGroup getGroup(int index) {
        return getGroups().get(index);
    }

    public List<ReportGroup> getGroups() {
	if (groups == null) {
	    groups = new ArrayList<ReportGroup>();
	}
        return groups;
    }
    
    public void setGroups(List<ReportGroup> groups) {
        this.groups = groups;
    }
    
    public void addGroup(ReportGroup group) {
	getGroups().add(group);
    }

    public void removeGroup(ReportGroup group) {
	getGroups().remove(group);
    }

    public boolean hasGroups() {
	return groups != null && !groups.isEmpty();
    }

    public int getGroupCount() {
	return groups == null ? 0 : groups.size();
    }

    public PageSetup getPageSetup() {
	if (pageSetup == null) {
	    pageSetup = new PageSetup();
	}
        return pageSetup;
    }

    public void setPageSetup(PageSetup pageSetup) {
        this.pageSetup = pageSetup;
    }

    public boolean hasPageSetup() {
 	return pageSetup != null;
    }
    
    public boolean isPaging() {
        return paging;
    }

    public void setPaging(boolean paging) {
        this.paging = paging;
    }

    public boolean isReportHeaderOnPage() {
        return reportHeaderOnPage;
    }

    public void setReportHeaderOnPage(boolean reportHeaderOnPage) {
        this.reportHeaderOnPage = reportHeaderOnPage;
    }

    public boolean isReportFooterOnPage() {
        return reportFooterOnPage;
    }

    public void setReportFooterOnPage(boolean reportFooterOnPage) {
        this.reportFooterOnPage = reportFooterOnPage;
    }

    @Override
    public List<DSExpression> buildExpressions() {
	List<DSExpression> expressions = new ArrayList<DSExpression>();
	populateExpressions(expressions);
	return expressions;
    }

    @Override
    public void populateExpressions(List<DSExpression> expressions) {
	if (hasBands()) {
	    for (Band band: bandModel.getBands()) {
		Element.populateExpressions(expressions, band);
	    }
	}
	
	if (hasGroups()) {
	    for (ReportGroup group: groups) {
		Element.populateExpressions(expressions, group);
	    }
	}
	
	if (hasColumns()) {
	    for (Column column: getColumns()) {
		Element.populateExpressions(expressions, column);
	    }
	}
	
	
    }
    
}
