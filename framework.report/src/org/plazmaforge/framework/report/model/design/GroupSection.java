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

/**
 * @author ohapon
 *
 */
public class GroupSection implements Serializable {

    private static final long serialVersionUID = 7252404075553664749L;
    
    private ReportGroup group;
    
    private Band groupHeader;
    
    private Band groupFooter;

    private int index;
    
    private boolean modify;
    
    public GroupSection() {
    }

    public GroupSection(ReportGroup group) {
	this.group = group;
    }

    public ReportGroup getGroup() {
        return group;
    }

    public void setGroup(ReportGroup group) {
        this.group = group;
    }

    public Band getGroupHeader() {
        return groupHeader;
    }

    public void setGroupHeader(Band groupHeader) {
        this.groupHeader = groupHeader;
    }

    public Band getGroupFooter() {
        return groupFooter;
    }

    public void setGroupFooter(Band groupFooter) {
        this.groupFooter = groupFooter;
    }

    public List<Band> getBands() {
	List<Band> bands = new ArrayList<Band>();
	if (groupHeader != null) {
	    bands.add(groupHeader);
	}
	if (groupFooter != null) {
	    bands.add(groupFooter);
	}
	return bands.isEmpty() ? null : bands;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isModify() {
        return modify;
    }

    public void setModify(boolean modify) {
        this.modify = modify;
    }
    
    
}
