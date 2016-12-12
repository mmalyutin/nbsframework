/*
 * Copyright (C) 2012-2015 Oleh Hapon ohapon@users.sourceforge.net
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

package org.plazmaforge.framework.reportviewer;

import org.plazmaforge.framework.report.exception.RTException;
import org.plazmaforge.framework.report.export.ReportExporter;
import org.plazmaforge.framework.report.model.base.PageSetup;
import org.plazmaforge.framework.report.model.document.Page;
import org.plazmaforge.framework.uwt.graphics.GC;
import org.plazmaforge.framework.uwt.widget.Canvas;

public class DocumentCanvas extends Canvas {


    private Page page;
    
    private ReportExporter reportExporter;

    private int marginLeft = 20;
    private int marginTop = 20;

    private boolean loading;
    
    public DocumentCanvas() {
	super();
    }
    
    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
        this.loading = false;
    }

    public ReportExporter getReportExporter() {
        return reportExporter;
    }

    public void setReportExporter(ReportExporter reportExporter) {
        this.reportExporter = reportExporter;
    }

    protected void paint(GC gc) {
	paintCanvas(gc);
    }
    
    public void startLoading() {
        this.loading = true;
        repaint();
    }

    public void stopLoading() {
        this.loading = false;
        repaint();
    }
    
    protected void paintCanvas(GC gc) {
	
  	//if (fileName != null) {
  	//    gc.drawText("View Report: " + fileName, 10, 10);
  	//}
  	//if (loading) {
  	//  gc.drawText("Loading...", 100, 100);
  	//  return;
  	//}
  	
  	if (reportExporter == null || page == null) {
  	    return;
  	}
  	
  	int pageX = marginLeft;
  	int pageY = marginTop;
  	
  	int pageWidth = getPageWidth(page);
  	int pageHeight = getPageHeight(page);
  	gc.drawRectangle(pageX - 1, pageY - 1, pageWidth + 1, pageHeight + 1);
  	gc.fillRectangle(pageX, pageY, pageWidth, pageHeight);
  	
  	try {
  	    reportExporter.setData("gc", gc);
  	    reportExporter.setData("offsetX", pageX);
  	    reportExporter.setData("offsetY", pageY);
  	    reportExporter.exportPage(page);
  	    reportExporter.setData("gc", null);
  	} catch (RTException ex) {
  	    //
  	}
    }
    
    
    protected int getPageWidth(Page page) {
	return page == null ? 0 : page.getDisplayWidth(); 
    }
    
    protected int getPageHeight(Page page) {
	return page == null ? 0 : page.getDisplayHeight(); 
    }
}
