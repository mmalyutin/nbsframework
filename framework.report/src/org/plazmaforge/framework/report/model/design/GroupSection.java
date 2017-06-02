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

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((group == null) ? 0 : group.hashCode());
	result = prime * result
		+ ((groupFooter == null) ? 0 : groupFooter.hashCode());
	result = prime * result
		+ ((groupHeader == null) ? 0 : groupHeader.hashCode());
	result = prime * result + index;
	result = prime * result + (modify ? 1231 : 1237);
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
	GroupSection other = (GroupSection) obj;
	if (group == null) {
	    if (other.group != null)
		return false;
	} else if (!group.equals(other.group))
	    return false;
	if (groupFooter == null) {
	    if (other.groupFooter != null)
		return false;
	} else if (!groupFooter.equals(other.groupFooter))
	    return false;
	if (groupHeader == null) {
	    if (other.groupHeader != null)
		return false;
	} else if (!groupHeader.equals(other.groupHeader))
	    return false;
	if (index != other.index)
	    return false;
	if (modify != other.modify)
	    return false;
	return true;
    }
    
    
}
