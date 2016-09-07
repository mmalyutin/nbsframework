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

    public List<Page> getPages() {
	if (pages == null) {
	    pages = new ArrayList<Page>();
	}
        return pages;
    }
    
    public Page getPage(int index) {
	return getPages().get(index);
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }
    
    public void addPage(Page page) {
	getPages().add(page);
    }

    public void removePage(Page page) {
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

}
