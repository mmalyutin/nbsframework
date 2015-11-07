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
public class ReportGroup extends DSExpressionGroup implements HasBands, HasExpressionBuilder {

    private static final long serialVersionUID = -8187650058128605525L;
    
    private boolean startOnNewPage;
    

    /**
     * List of bands
     */
    private List<Band> bands;


    @Override
    public List<Band> getBands() {
	if (bands == null) {
	    bands = new ArrayList<Band>();
	}
	return bands;
    }
    
    @Override
    public Band getBand(int index) {
	return getBands().get(0);
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
	    for (Band band: bands) {
		Element.populateExpressions(expressions, band);
	    }
	}
    }
}
