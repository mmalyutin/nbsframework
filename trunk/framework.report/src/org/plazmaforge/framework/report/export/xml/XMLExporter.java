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

package org.plazmaforge.framework.report.export.xml;

import org.plazmaforge.framework.report.exception.RTException;
import org.plazmaforge.framework.report.export.AbstractReportExporter;
import org.plazmaforge.framework.report.model.document.Document;
import org.plazmaforge.framework.report.model.document.Page;
import org.plazmaforge.framework.report.storage.xml.document.XMLDocumentWriter;

/**
 * 
 * @author ohapon
 *
 */
public class XMLExporter extends AbstractReportExporter {

    @Override
    public void exportDocument(Document document) throws RTException {
	String outputType = getOutputType();
	if (!isValidOutputType(outputType)) {
	    throw new RTException("Invalid output type: '" + outputType + "'");
	}
	if (isFileNameOutputType(outputType)) {
	    String fileName = (String) getData(PROPERTY_OUTPUT_FILE_NAME);
	    if (fileName == null) {
		throw new RTException("Output file name is empty");
	    }
	    // Write the document to XML file
	    XMLDocumentWriter documentWriter = new XMLDocumentWriter();
	    documentWriter.writeDocument(document, fileName);
	    return;
	}
	throw new RTException("Invalid output type: '" + outputType + "'");
	
    }

    @Override
    public void exportPage(Page page) throws RTException {
	// TODO Auto-generated method stub
	
    }

}
