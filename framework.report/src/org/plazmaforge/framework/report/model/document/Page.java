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
package org.plazmaforge.framework.report.model.document;

import org.plazmaforge.framework.report.model.base.Container;
import org.plazmaforge.framework.report.model.base.Margin;

/**
 * @author ohapon
 *
 */
public class Page extends Container {

    private static final long serialVersionUID = -9205094676201936864L;

    private Margin margin;
    
    
    public Page() {
    }
    
    public Margin getMargin() {
	if (margin == null) {
	    margin = new Margin();
	}
        return margin;
    }

    public void setMargin(Margin margin) {
        this.margin = margin;
    }
    
    public boolean hasMargin() {
	return margin != null && !margin.isEmpty();
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((margin == null) ? 0 : margin.hashCode());
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
	Page other = (Page) obj;
	if (margin == null) {
	    if (other.margin != null)
		return false;
	} else if (!margin.equals(other.margin))
	    return false;
	return true;
    }    
    
}
