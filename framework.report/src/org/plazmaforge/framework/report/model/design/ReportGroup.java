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
import org.plazmaforge.framework.core.datastorage.DSExpressionGroup;
import org.plazmaforge.framework.core.datastorage.HasExpressionBuilder;
import org.plazmaforge.framework.report.model.base.Element;

/**
 * @author ohapon
 *
 */
public class ReportGroup extends DSExpressionGroup implements BandModel, HasExpressionBuilder {

    private static final long serialVersionUID = -8187650058128605525L;
    
    private boolean startOnNewPage;
    

    /**
     * Band model 
     */
    private BandModel bandModel;


    public ReportGroup() {
	super();
	bandModel = new BaseBandModel();
    }

    public ReportGroup(DSExpression expression) {
	super(expression);
	bandModel = new BaseBandModel();
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

    public boolean isStartOnNewPage() {
        return startOnNewPage;
    }

    public void setStartOnNewPage(boolean startOnNewPage) {
        this.startOnNewPage = startOnNewPage;
    }

    @Override
    public List<DSExpression> buildExpressions() {
	List<DSExpression> expressions = new ArrayList<DSExpression>();
	populateExpressions(expressions);
	return expressions;
    }

    @Override
    public void populateExpressions(List<DSExpression> expressions) {
	if (hasExpressionText()) {
	    expressions.add(getExpression());
	}
	if (hasBands()) {
	    for (Band band: getBands()) {
		Element.populateExpressions(expressions, band);
	    }
	}
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result
		+ ((bandModel == null) ? 0 : bandModel.hashCode());
	result = prime * result + (startOnNewPage ? 1231 : 1237);
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	ReportGroup other = (ReportGroup) obj;
	if (bandModel == null) {
	    if (other.bandModel != null)
		return false;
	} else if (!bandModel.equals(other.bandModel))
	    return false;
	if (startOnNewPage != other.startOnNewPage)
	    return false;
	return true;
    }
    
    
}
