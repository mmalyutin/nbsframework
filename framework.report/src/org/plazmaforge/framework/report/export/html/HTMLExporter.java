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

package org.plazmaforge.framework.report.export.html;

import java.io.IOException;
import java.util.List;

import org.plazmaforge.framework.report.exception.RTException;
import org.plazmaforge.framework.report.model.base.Element;
import org.plazmaforge.framework.report.model.base.grid.Grid;
import org.plazmaforge.framework.report.model.document.Document;
import org.plazmaforge.framework.report.model.document.Page;

public class HTMLExporter extends AbstractHTMLExporter {

    @Override
    public void exportDocument(Document document) throws RTException {
	try {
	    ensureOutput();
	    checkOutput();

	    writeHeader();
	    writeDocument(document);
	    writeFooter();

	    flushOutput();
	    closeOutput();
	    
	} catch (IOException ex) {
	    throw new RTException(ex);
	}
    }

    @Override
    public void exportPage(Page page) throws RTException {
	// TODO Auto-generated method stub
    }
    
    protected void ensureOutput() throws RTException {

	String outputType = getOutputType();
	if (!isValidOutputType(outputType)) {
	    throw new RTException("Invalid output type: '" + outputType + "'");
	}
	
	String encoding = getEncoding();
	if (isFileNameOutputType(outputType)) {
	    String fileName = (String) getData(PROPERTY_OUTPUT_FILE_NAME);
	    ensureOutput(fileName, encoding);
	} else {
	    //TODO
	    throw new RTException("Invalid output type: '" + outputType + "'");
	}
    }
   
    protected void ensureOutput(String fileName, String encoding) throws RTException {
	if (fileName == null) {
	    throw new RTException("Output file name is empty");
	}
	try {
	    writer = createWriter(fileName, encoding);	    
	} catch (IOException ex){
	    throw new RTException(ex);
	}
    }
    
    protected void checkOutput() throws RTException {
	if (writer == null) {
	    throw new RTException("Output is not ready");
	}
    }

    protected void flushOutput() throws RTException {
	try {
	    writer.flush();
	} catch (IOException ex) {
	    throw new RTException(ex);
	}
    }

    protected void closeOutput() throws RTException {
	try {
	    writer.close();
	} catch (IOException ex) {
	    throw new RTException(ex);
	}
    }

    protected void writeDocument(Document document) throws RTException, IOException {
	if (document == null || !document.hasPages()) {
	    return;
	}
	int pageCount = document.getPageCount();
	List<Page> pages = document.getPages();
	for (int pageIndex = 0; pageIndex < pageCount; pageIndex++) {
	    Page page = pages.get(pageIndex);
	    writePage(page, pageIndex);
	}
    }

    protected void writePage(Page page, int pageIndex) throws RTException, IOException {
	writePageStart(page);
	write("Page " + (pageIndex + 1)); // TODO
	writePageBody(page);
	writePageEnd(page);
    }
    
    protected void writePageStart(Page page) throws RTException, IOException {
	int pageWidth = page.getDisplayWidth();
	int pageHeight = page.getDisplayHeight();
	
	String style = "style='width: " + pageWidth + "px" + "; height: " + pageHeight + "px" + "'";
	write("<div " + style + ">\n");
    }
    
    protected void writePageBody(Page page) throws RTException, IOException {
	if (!page.hasChildren()) {
	    return;
	}

	List<Element> children = page.getChildren();

	for (Element element : children) {
	    if (element instanceof Grid) {
		writeGrid((Grid) element);
	    }
	}
    }
    
    protected void writePageEnd(Page page) throws RTException, IOException {
	write("\n");
	write("</div>\n");
    }

    protected void writeGrid(Grid grid) throws RTException, IOException {
	write("\nGrid");
    }
}
