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

package org.plazmaforge.framework.report.storage.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.plazmaforge.framework.report.exception.RTException;

/**
 * 
 * @author ohapon
 *
 */
public class XMLAbstractWriter implements XMLInfo {

    
    protected void writeXMLDocument(Document doc, String fileName) throws RTException {
        writeXMLDocument(doc, new File(fileName));
    }

    protected void writeXMLDocument(Document doc, File file) throws RTException {
	try {
	    writeXMLDocument(doc, new FileOutputStream(file));
	} catch (FileNotFoundException ex) {
	    throw new RTException(ex);
	}
    }

    protected void writeXMLDocument(Document doc, OutputStream os) throws RTException {
	try {
	    writeXMLDocument(doc, new OutputStreamWriter(os, "UTF-8"));
	} catch (UnsupportedEncodingException ex) {
	    throw new RTException(ex);
	}
    }

    protected void writeXMLDocument(Document doc, Writer writer) throws RTException {
	try {
	    Format format = Format.getPrettyFormat();
	    format.setEncoding("UTF-8");
	    XMLOutputter outputter = new XMLOutputter(format);
	    outputter.output(doc, writer);
	    writer.flush();
	} catch (IOException ex) {
	    throw new RTException(ex);
	}
    }
    
    
    ////
    
    protected Element createXMLRootElement(String name) {
	Element root = new Element(name);
	return root;
    }
    
}
