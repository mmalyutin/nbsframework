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

package org.plazmaforge.framework.uwt.wizard;

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.uwt.layout.CardLayout;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Control;

public class Wizard extends Composite implements IWizard {

    
    private List<IWizardPage> pages = new ArrayList<IWizardPage>();
    
    private int pageIndex;
    
    
    public Wizard() {
	super();
	pageIndex = 0;
	CardLayout layout = new CardLayout();
	setLayout(layout);
    }

    @Override
    public IWizardPage[] getPages() {
	return pages.toArray(new IWizardPage[0]);
    }

    @Override
    public IWizardPage getPage(String pageName) {
	if (pageName == null || !hasPages()) {
	    return null;
	}
	for (IWizardPage page: pages) {
	    if (pageName.equals(page.getName())) {
		return page;
	    }
	}
	return null;
    }
    
    @Override
    public IWizardPage getPage(int index) {
	if (!hasPages()) {
	    return null;
	}
	return pages.get(index);
    }


    @Override
    public int getPageCount() {
	return pages.size();
    }

    @Override
    public boolean hasPages() {
	return pages != null && !pages.isEmpty();
    }
    
    @Override
    public int getPageIndex() {
	return pageIndex;
    }

    @Override
    public boolean isFirstPage() {
	return pageIndex == 0;
    }

    @Override
    public boolean isLastPage() {
	return pageIndex == getPageCount() - 1;
    }
    
    
    @Override
    public IWizardPage createPage() {
	WizardPage page = new WizardPage();
	Control content = page.getContent();
	
	// Add page content to card container
	add(content);
	pages.add(page);
	return page;
    }

    public boolean moveFirst() {
	if (!canFirst()) {
	    return false;
	}
	pageIndex = 0;
	getCardLayout().first();
	return true;
    }


    public boolean moveBack() {
	if (!canBack()) {
	    return false;
	}
	pageIndex--;
	getCardLayout().prev();
	return true;
    }

    public boolean moveNext() {
	if (!canNext()) {
	    return false;
	}
	pageIndex++;
	getCardLayout().next();
	return true;
    }

    public boolean moveLast() {
	if (!canLast()) {
	    return false;
	}
	pageIndex = getPageCount() - 1;
	getCardLayout().last();
	return true;
    }
    
    
    
    public boolean canFirst() {
	return hasPages() && pageIndex != 0;
    }

    public boolean canBack() {
	return hasPages() && pageIndex > 0;
    }
    
    public boolean canNext() {
	return hasPages() && pageIndex < getPageCount() - 1;
    }

    public boolean canLast() {
	return hasPages() && pageIndex != getPageCount() - 1;
    }


    protected CardLayout getCardLayout() {
	return (CardLayout) getLayout();
    }

}
