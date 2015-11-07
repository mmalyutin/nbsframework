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

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.core.datastorage.DSExpression;
import org.plazmaforge.framework.core.datastorage.HasExpressionBuilder;
import org.plazmaforge.framework.report.model.base.Element;
import org.plazmaforge.framework.report.model.base.PageSetup;
import org.plazmaforge.framework.report.model.base.grid.BaseColumnModel;
import org.plazmaforge.framework.report.model.base.grid.Column;
import org.plazmaforge.framework.report.model.base.grid.ColumnModel;

/**
 * @author ohapon
 * 
 * The Report Template has bands
 *
 */
public class Template implements ColumnModel, HasBands, HasExpressionBuilder {

    /**
     * Template type: Table, Crosstab or null
     */
    private String type;
    
    /**
     * List of bands
     */
    private List<Band> bands;
    
    /**
     * List of report groups
     */
    private List<ReportGroup> groups;

    /**
     * Own ColumnModel. Only for TableReport
     */
    private ColumnModel columnModel;
    
    /**
     * Page Setup
     */
    private PageSetup pageSetup;
    
    /**
     * True if need paging
     */
    private boolean paging;
    
    public Template() {
	columnModel = new BaseColumnModel();
	paging = true;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public List<Band> getBands() {
	if (bands == null) {
	    bands = new ArrayList<Band>();
	}
        return bands;
    }
    
    @Override
    public Band getBand(int index) {
	return getBands().get(index);
    }

    @Override
    public void setBands(List<Band> bands) {
        this.bands = bands;
    }
    
    @Override
    public void addBand(Band band) {
	getBands().add(band);
    }

    @Override
    public void removeBand(Band band) {
	getBands().remove(band);
    }
    
    @Override
    public boolean hasBands() {
	return bands != null && !bands.isEmpty();
    }

    @Override
    public int getBandCount() {
	return bands == null ? 0 : bands.size();
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
	return findBandByType(type == null ? null : type.name());
    }
    
    /**
     * Return first band by type
     * @param type
     * @return
     */
    @Override
    public Band findBandByType(String type) {
	if (type == null || !hasBands()) {
	    return null;
	}
	for (Band band : bands) {
	    if (type.equals(band.getType())) {
		return band;
	    }
	}
	return null;
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

    public boolean isPaging() {
        return paging;
    }

    public void setPaging(boolean paging) {
        this.paging = paging;
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
	    for (Band band: bands) {
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
