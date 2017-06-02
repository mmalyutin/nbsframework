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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author ohapon
 *
 */
public class TemplateStructure implements Serializable {

    private static final long serialVersionUID = -2501055081056827094L;

    /*
    ReportHeader,
    PageHeader,
    ColumnHeader,
    GroupHeader,
    Detail,
    GroupFooter,
    ColumnFooter,
    PageFooter,
    ReportFooter,
    
    ReportBackground,
    NoData    
    */
    
    
    private Band reportHeader;
    
    private Band pageHeader;

    private Band columnHeader;
    
    private Band detail;
    
    private Band columnFooter;
    
    private Band pageFooter;
    
    private Band reportFooter;
    
    
    private Band reportBackground;
    
    private Band noData;

    private List<GroupSection> groups;
    
    private Map<BandType, BandLayout> bandLayouts;
    
    
    public TemplateStructure() {
	bandLayouts = new HashMap<BandType, BandLayout>();
    }

    
    public Band getReportHeader() {
        return reportHeader;
    }


    public void setReportHeader(Band reportHeader) {
        this.reportHeader = reportHeader;
    }


    public Band getPageHeader() {
        return pageHeader;
    }


    public void setPageHeader(Band pageHeader) {
        this.pageHeader = pageHeader;
    }


    public Band getColumnHeader() {
        return columnHeader;
    }


    public void setColumnHeader(Band columnHeader) {
        this.columnHeader = columnHeader;
    }


    public Band getDetail() {
        return detail;
    }


    public void setDetail(Band detail) {
        this.detail = detail;
    }


    public Band getColumnFooter() {
        return columnFooter;
    }


    public void setColumnFooter(Band columnFooter) {
        this.columnFooter = columnFooter;
    }


    public Band getPageFooter() {
        return pageFooter;
    }


    public void setPageFooter(Band pageFooter) {
        this.pageFooter = pageFooter;
    }


    public Band getReportFooter() {
        return reportFooter;
    }


    public void setReportFooter(Band reportFooter) {
        this.reportFooter = reportFooter;
    }


    public Band getReportBackground() {
        return reportBackground;
    }


    public void setReportBackground(Band reportBackground) {
        this.reportBackground = reportBackground;
    }


    public Band getNoData() {
        return noData;
    }


    public void setNoData(Band noData) {
        this.noData = noData;
    }


    public List<GroupSection> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupSection> groups) {
        this.groups = groups;
    }

    public List<Band> getBands() {
	List<Band> bands = new ArrayList<Band>();
	if (reportHeader != null) {
	    bands.add(reportHeader);
	}
	if (pageHeader != null) {
	    bands.add(pageHeader);
	}
	if (columnHeader != null) {
	    bands.add(columnHeader);
	}
	if (detail != null) {
	    bands.add(detail);
	}
	if (columnFooter != null) {
	    bands.add(columnFooter);
	}
	if (pageFooter != null) {
	    bands.add(pageFooter);
	}
	if (reportFooter != null) {
	    bands.add(reportFooter);
	}
	return bands.isEmpty() ? null : bands;
    }
    
    public BandLayout getBandLayout(BandType bandType) {
        return bandLayouts.get(bandType);
    }


    public void setBandLayout(BandType bandType, BandLayout bandlayout) {
        bandLayouts.put(bandType, bandlayout);
    }


    /**
     * Create <code>TemplateStructure</code> by <code>Template</code>
     *  
     * @param template
     * @return
     */
    public static TemplateStructure create(Template template) {
	if (template == null) {
	    return null;
	}
	
	TemplateStructure structure = new TemplateStructure();
	structure.setReportHeader(findBand(template, BandType.ReportHeader));
	structure.setPageHeader(findBand(template, BandType.PageHeader));
	structure.setColumnHeader(findBand(template, BandType.ColumnHeader));
	structure.setDetail(findBand(template, BandType.Detail));
	structure.setColumnFooter(findBand(template, BandType.ColumnFooter));
	structure.setPageFooter(findBand(template, BandType.PageFooter));
	structure.setReportFooter(findBand(template, BandType.ReportFooter));
	
	structure.setReportBackground(findBand(template, BandType.ReportFooter));
	structure.setNoData(findBand(template, BandType.NoData));
	
	if (template.hasGroups()) {
	    List<ReportGroup> groups = template.getGroups();
	    List<GroupSection> groupSections = new ArrayList<GroupSection>();
	    for (int i = 0; i < groups.size(); i++) {
		ReportGroup group = groups.get(i); 
		GroupSection groupSection = new GroupSection(group);
		groupSections.add(groupSection);
		groupSection.setIndex(i);
		groupSection.setGroupHeader(findBand(group, BandType.GroupHeader));
		groupSection.setGroupFooter(findBand(group, BandType.GroupFooter));
	    }
	    structure.setGroups(groupSections);
	}
	
	return structure;
	
    }
    
    /**
     * Find only visible band by type
     * 
     * @param bandModel
     * @param type
     * @return
     */
    public static Band findBand(BandModel bandModel, BandType type) {
	if (bandModel == null || type == null) {
	    return null;
	}
	Band band = bandModel.findBandByType(type);
	return (band == null || !band.isVisible()) ? null : band;
    }


    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((bandLayouts == null) ? 0 : bandLayouts.hashCode());
	result = prime * result
		+ ((columnFooter == null) ? 0 : columnFooter.hashCode());
	result = prime * result
		+ ((columnHeader == null) ? 0 : columnHeader.hashCode());
	result = prime * result + ((detail == null) ? 0 : detail.hashCode());
	result = prime * result + ((groups == null) ? 0 : groups.hashCode());
	result = prime * result + ((noData == null) ? 0 : noData.hashCode());
	result = prime * result
		+ ((pageFooter == null) ? 0 : pageFooter.hashCode());
	result = prime * result
		+ ((pageHeader == null) ? 0 : pageHeader.hashCode());
	result = prime
		* result
		+ ((reportBackground == null) ? 0 : reportBackground.hashCode());
	result = prime * result
		+ ((reportFooter == null) ? 0 : reportFooter.hashCode());
	result = prime * result
		+ ((reportHeader == null) ? 0 : reportHeader.hashCode());
	return result;
    }


    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	TemplateStructure other = (TemplateStructure) obj;
	if (bandLayouts == null) {
	    if (other.bandLayouts != null)
		return false;
	} else if (!bandLayouts.equals(other.bandLayouts))
	    return false;
	if (columnFooter == null) {
	    if (other.columnFooter != null)
		return false;
	} else if (!columnFooter.equals(other.columnFooter))
	    return false;
	if (columnHeader == null) {
	    if (other.columnHeader != null)
		return false;
	} else if (!columnHeader.equals(other.columnHeader))
	    return false;
	if (detail == null) {
	    if (other.detail != null)
		return false;
	} else if (!detail.equals(other.detail))
	    return false;
	if (groups == null) {
	    if (other.groups != null)
		return false;
	} else if (!groups.equals(other.groups))
	    return false;
	if (noData == null) {
	    if (other.noData != null)
		return false;
	} else if (!noData.equals(other.noData))
	    return false;
	if (pageFooter == null) {
	    if (other.pageFooter != null)
		return false;
	} else if (!pageFooter.equals(other.pageFooter))
	    return false;
	if (pageHeader == null) {
	    if (other.pageHeader != null)
		return false;
	} else if (!pageHeader.equals(other.pageHeader))
	    return false;
	if (reportBackground == null) {
	    if (other.reportBackground != null)
		return false;
	} else if (!reportBackground.equals(other.reportBackground))
	    return false;
	if (reportFooter == null) {
	    if (other.reportFooter != null)
		return false;
	} else if (!reportFooter.equals(other.reportFooter))
	    return false;
	if (reportHeader == null) {
	    if (other.reportHeader != null)
		return false;
	} else if (!reportHeader.equals(other.reportHeader))
	    return false;
	return true;
    }

    
}
