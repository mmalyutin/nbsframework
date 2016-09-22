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

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.report.model.base.PageSetup;

/**
 * @author ohapon
 *
 */
public class Document {

    /**
     * Name of report
     */
    private String name;
    
    /**
     * Caption (NLS display name) of document
     */
    private String caption;

    /**
     * Caption (NLS description) of document
     */
    private String description;

    /**
     * Page Setup
     */
    private PageSetup pageSetup;
    
    
    private List<Page> pages;

    public Document() {
	super();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<Page> getPages() {
	if (pages == null) {
	    pages = new ArrayList<Page>();
	}
        return pages;
    }
    
    public Page getPage(int index) {
	return getPages().get(index);
    }

    public void addPage(Page page) {
	if (page != null) {
	    page.setDocument(this);
	}
	getPages().add(page);
    }

    public void removePage(Page page) {
	if (page != null) {
	    page.setDocument(null);
	}
	getPages().remove(page);
    }

    public boolean hasPages() {
	return pages != null && !pages.isEmpty();
    }

    public int getPageCount() {
	return pages == null ? 0 : pages.size();
    }
    
    public boolean isEmpty() {
	return !hasPages();
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((caption == null) ? 0 : caption.hashCode());
	result = prime * result
		+ ((description == null) ? 0 : description.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result
		+ ((pageSetup == null) ? 0 : pageSetup.hashCode());
	result = prime * result + ((pages == null) ? 0 : pages.hashCode());
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
	Document other = (Document) obj;
	if (caption == null) {
	    if (other.caption != null)
		return false;
	} else if (!caption.equals(other.caption))
	    return false;
	if (description == null) {
	    if (other.description != null)
		return false;
	} else if (!description.equals(other.description))
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (pageSetup == null) {
	    if (other.pageSetup != null)
		return false;
	} else if (!pageSetup.equals(other.pageSetup))
	    return false;
	if (pages == null) {
	    if (other.pages != null)
		return false;
	} else if (!pages.equals(other.pages))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Document[" 
		+ "name=" + name 
		+ ", caption=" + caption
		+ ", description=" + description 
		+ ", pageSetup=" + pageSetup
		+ ", pages=" + (pages == null ? null : ("[" + pages.size() + "]"))
		+ "]";
    }
    
    

}
