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
import org.plazmaforge.framework.report.model.base.PageSetup;

/**
 * @author ohapon
 *
 */
public class Page extends Container {

    private static final long serialVersionUID = -9205094676201936864L;

    private Document document;
    
    public Page() {
    }
    
    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public PageSetup getPageSetup() {
	return document == null ? null : document.getPageSetup();
    }
    
    public int getDisplayWidth() {
	if (hasWidth()) {
	    return getWidth();
	}
	return getPageSetup() == null ? 0 : getPageSetup().getSize().getWidth();
    }
    
  
    public int getDisplayHeight() {
	if (hasHeight()) {
	    return getHeight();
	}
	return getPageSetup() == null ? 0 : getPageSetup().getSize().getHeight();
    }
    
//    @Override
//    public int hashCode() {
//	final int prime = 31;
//	int result = super.hashCode();
//	result = prime * result
//		+ ((document == null) ? 0 : document.hashCode());
//	return result;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//	if (this == obj)
//	    return true;
//	if (!super.equals(obj))
//	    return false;
//	if (getClass() != obj.getClass())
//	    return false;
//	Page other = (Page) obj;
//	if (document == null) {
//	    if (other.document != null)
//		return false;
//	} else if (!document.equals(other.document))
//	    return false;
//	return true;
//    }
    
    
}
