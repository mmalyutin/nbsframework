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
import org.plazmaforge.framework.uwt.graphics.Color;

/**
 * 
 * @author ohapon
 *
 */
public class XMLAbstractWriter extends XMLWorker implements XMLInfo {

    
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
    
    protected void writeElementAttributes(org.plazmaforge.framework.report.model.base.Element element, Element node) {

  	// position
  	if (element.hasPosition()) {
  	    if (element.getPosition().hasX()) {
  		setIntegerValue(node, XML_ATTR_X, element.getPosition().getX());
  	    }
  	    if (element.getPosition().hasY()) {
  		setIntegerValue(node, XML_ATTR_Y, element.getPosition().getY());
  	    }
  	}

  	//size
  	if (element.hasSize()) {
  	    if (element.getSize().hasWidth()) {
  		setIntegerValue(node, XML_ATTR_WIDTH, element.getSize().getWidth());
  	    }
  	    if (element.getSize().hasHeight()) {
  		setIntegerValue(node, XML_ATTR_HEIGHT, element.getSize().getHeight());
  	    }
  	}
  	
  	// background
  	Color background = element.getBackground();
  	if (background != null) {
  	    setColor(node, XML_ATTR_BACKGROUND, background);
  	}
  	    
  	// foreground
  	Color foreground = element.getForeground();
  	if (foreground != null) {
  	    setColor(node, XML_ATTR_FOREGROUND, foreground);
  	}

    }    
    
    
    ////
    
    protected Element createElement(String name) {
	Element node = new Element(name);
	return node;
    }
    
    protected void addChild(Element parent, Element child) {
	parent.getChildren().add(child);
    }
    
    protected void setValue(Element element, String name, Object value) {
	if (value == null) {
	    return;
	}
	// TODO: dataType
   	element.setAttribute(name, value.toString());
    }

    protected void setIntegerValue(Element element, String name, Integer value) {
	// TODO: dataType
   	setValue(element, name, value);
    }
    
    protected void setColor(Element element, String name, Color color) {
	setValue(element, name, COLOR_FORMATTER.format(color));
    }
    
    protected void setContentValue(Element element, Object value) {
	setContentValue(element, value, null);
    }
    
    protected void setContentValue(Element element, Object value, String dataType) {
	if (value == null) {
	    return;
	}
	String str = toString(value, dataType);
	if (str == null) {
	    return;
	}
	element.setText(str);
    }
    
    
    protected String toString(Object value, String dataType) {
	if (value == null) {
	    return null;
	}
	// TODO: dataType
	String str = value.toString();
	return normalizeString(str);
    }
    
    
}
