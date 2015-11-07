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
package org.plazmaforge.framework.reportviewer;

import java.util.HashMap;
import java.util.Map;

import org.plazmaforge.framework.core.logging.Logger;
import org.plazmaforge.framework.report.ReportEngine;
import org.plazmaforge.framework.report.ReportSamples;
import org.plazmaforge.framework.report.exception.RTException;
import org.plazmaforge.framework.report.export.ReportExporter;
import org.plazmaforge.framework.report.model.document.Document;
import org.plazmaforge.framework.report.model.document.Page;
import org.plazmaforge.framework.uwt.AbstractDesktopApplication;
import org.plazmaforge.framework.uwt.ApplicationView;
import org.plazmaforge.framework.uwt.event.SelectionEvent;
import org.plazmaforge.framework.uwt.event.SelectionListener;
import org.plazmaforge.framework.uwt.graphics.GC;
import org.plazmaforge.framework.uwt.layout.FitLayout;
import org.plazmaforge.framework.uwt.layout.GridData;
import org.plazmaforge.framework.uwt.widget.Canvas;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Frame;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;
import org.plazmaforge.framework.uwt.widget.panel.ScrollPanel;
import org.plazmaforge.framework.uwt.widget.tool.ToolBar;
import org.plazmaforge.framework.uwt.widget.tool.ToolItem;

/**
 * @author ohapon
 *
 */
public class ViewerApp extends AbstractDesktopApplication {

    private static Logger logger = Logger.getLogger(ViewerApp.class.getName());

     
	    
    private String fileName;
    
    private Document document;
    
    private Page page;
    
    private int pageIndex;
    
    private int pageCount;
    
    private ReportExporter reportExporter;

    private ToolItem firstPageItem;
    private ToolItem prevPageItem;
    private ToolItem nextPageItem;
    private ToolItem lastPageItem;

    private ScrollPanel scrollPanel;
    private Canvas canvas;

    private int marginLeft = 20;
    private int marginTop = 50;

    
    
    public static void main(String[] args) {
	// Get application properties
	Map<String, String> properties = getProperties(args);

	// Get UI
	String ui = properties.get("ui");
	if (ui == null) {
	    System.err.println("UI is not setting. Use '-ui' argument in command line. For example '-ui swing'");
	    return;
	}
	// Initialize UWT by UI type
	if (!initUWT(ui)) {
	    return;
	}

	// Create and start application
	ViewerApp application = new ViewerApp();
	application.start(properties);
    }

    public void start(Map<String, String> properties) {

	getApplicationContext().setProperty("imageStorage", "org/plazmaforge/framework/uwt/resources/images");
	getApplicationContext().setProperty("imageStorage/widget", "org/plazmaforge/framework/uwt/resources/images/widget"); // UWT WIDGET
	getApplicationContext().setProperty("imageStorage/form", "org/plazmaforge/framework/uwt/resources/images/form"); // UWT FORM
	
	// Get frame
	Frame frame = getFrame();
	frame.setWidth(640);
	frame.setHeight(480);
	frame.setTitle("Plazma Report Viewer");
	frame.setLayout(new FitLayout());
	
	ApplicationView appView = new ApplicationView();
	appView.create();
	frame.add(appView);
	
	Composite content = appView.getContent();
	content.setLayout(new FitLayout());
	
	scrollPanel = new ScrollPanel();
	//scrollPanel.setContentWidth(500);
	//scrollPanel.setContentHeight(500);

	scrollPanel.setLayout(new FitLayout());
	content.add(scrollPanel);
	
	
	ToolBar toolBar = createToolBar();
	appView.setToolBar(toolBar);
	
	canvas = createCanvas();
	canvas.setWidth(500);
	canvas.setHeight(500);
	
	scrollPanel.add(canvas);
	
	frame.setCenter(true);

	
	reportExporter = ReportEngine.getReportExporter("UWTCanvas");
	if (reportExporter == null) {
	    logger.warn("UWTCanvasReportExporter not found");
	}
	

	updateState();
	
	frame.layout();
	
	start();
	
	
    }
    
    protected ToolBar createToolBar() {
	ToolBar toolBar = new ToolBar();
	
	// OPEN FILE
	ToolItem toolItem = new ToolItem();
	toolItem.setIcon("widget::folder-open.gif");
	toolItem.setToolTip("Open");
	toolItem.addSelectionListener(new SelectionListener() {

	    @Override
	    public void select(SelectionEvent event) {
		doOpenAction();
	    }
	    
	});
	toolBar.addItem(toolItem);
	
	toolBar.addSeparator();
	
	// PAGE FIRST
	firstPageItem = new ToolItem();
	firstPageItem.setIcon("form::page_first.gif");
	firstPageItem.setToolTip("First page");
	firstPageItem.addSelectionListener(new SelectionListener() {

	    @Override
	    public void select(SelectionEvent event) {
		doFirstPageAction();
	    }
	    
	});
	toolBar.addItem(firstPageItem);

	// PAGE PREV
	prevPageItem = new ToolItem();
	prevPageItem.setIcon("form::page_prev.gif");
	prevPageItem.setToolTip("Previous page");
	prevPageItem.addSelectionListener(new SelectionListener() {

	    @Override
	    public void select(SelectionEvent event) {
		doPrevPageAction();
	    }
	    
	});
	toolBar.addItem(prevPageItem);

	// PAGE NEXT
	nextPageItem = new ToolItem();
	nextPageItem.setIcon("form::page_next.gif");
	nextPageItem.setToolTip("Next page");
	nextPageItem.addSelectionListener(new SelectionListener() {

	    @Override
	    public void select(SelectionEvent event) {
		doNextPageAction();
	    }
	    
	});
	toolBar.addItem(nextPageItem);

	// PAGE LAST
	lastPageItem = new ToolItem();
	lastPageItem.setIcon("form::page_last.gif");
	lastPageItem.setToolTip("Last page");
	lastPageItem.addSelectionListener(new SelectionListener() {

	    @Override
	    public void select(SelectionEvent event) {
		doLastPageAction();
	    }
	    
	});
	toolBar.addItem(lastPageItem);

	return toolBar;
    }
    
    private Canvas createCanvas() {
	Canvas canvas = new Canvas() {
	    protected void paint(GC gc) {
		paintCanvas(gc);
	    }

	};
	canvas.setLayoutData(new GridData(1, 1, HorizontalAlign.FILL, VerticalAlign.FILL, true, true));
	return canvas;
    }

    protected void paintCanvas(GC gc) {
	
	//if (fileName != null) {
	//    gc.drawText("View Report: " + fileName, 10, 10);
	//}
	
	if (reportExporter == null || page == null) {
	    return;
	}
	
	int pageX = marginLeft;
	int pageY = marginTop;
	
	int pageWidth = page.getWidth();
	int pageHeight = page.getHeight();
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
    
    protected boolean hasData() {
	return document != null && pageCount > 0;
    }
    
    private void openDocument(String fileName) {
	document = null;
	page = null;
	pageIndex = -1;
	pageCount = 0;
	
	try {
	    //TODO
	    Map<String, Object> parameters = new HashMap<String, Object>();
	    parameters.put("PARAM1", "Today");
	    document = ReportSamples.createTableReportDocument(parameters);
	    pageCount = document.getPageCount();
	    if (document != null && document.hasPages()){
		pageIndex = 0;
		setPage(pageIndex);
	    }
	} catch (RTException ex) {
	    logger.error("Open document error", ex);
	}
	
    }
    
    protected void doOpenAction() {
	//TODO
	fileName = "MyReport.report.xml";
	openDocument(fileName);
    }
    
    protected void doFirstPageAction() {
	if (!hasData()) {
	    return;
	}
	if (pageIndex == 0) {
	    return;
	}
	pageIndex = 0;
	setPage(pageIndex);
    }

    protected void doPrevPageAction() {
	if (!hasData()) {
	    return;
	}
	if (pageIndex == 0) {
	    return;
	}
	pageIndex--;
	setPage(pageIndex);
    }

    protected void doNextPageAction() {
	if (!hasData()) {
	    return;
	}
	if (pageIndex == pageCount - 1) {
	    return;
	}
	pageIndex++;
	setPage(pageIndex);
    }

    protected void doLastPageAction() {
	if (!hasData()) {
	    return;
	}
	if (pageIndex == pageCount - 1) {
	    return;
	}
	pageIndex = pageCount - 1;
	setPage(pageIndex);
    }
    
    protected void setPage(int pageIndex) {
	page = document.getPages().get(pageIndex);
	
	scrollPanel.setContentWidth(marginLeft * 2 + page.getWidth());
	scrollPanel.setContentHeight(marginTop * 2 + page.getHeight());
	
	canvas.repaint();
	updateState();
    }
    
    protected void updateState() {
	if (!hasData() || pageIndex < 0) {
	    firstPageItem.setEnabled(false);
	    prevPageItem.setEnabled(false);
	    nextPageItem.setEnabled(false);
	    lastPageItem.setEnabled(false);
	    return;
	}
	
	firstPageItem.setEnabled(pageIndex > 0);
	prevPageItem.setEnabled(pageIndex > 0);
	nextPageItem.setEnabled(pageIndex < pageCount - 1);
	lastPageItem.setEnabled(pageIndex < pageCount - 1);
    }
    
}

