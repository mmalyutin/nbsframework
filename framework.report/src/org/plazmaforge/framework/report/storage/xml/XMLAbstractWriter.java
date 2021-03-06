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
import org.plazmaforge.framework.core.data.LocalizedIdentifier;
import org.plazmaforge.framework.core.datastorage.DSExpression;
import org.plazmaforge.framework.report.exception.RTException;
import org.plazmaforge.framework.report.model.base.Border;
import org.plazmaforge.framework.report.model.base.Margin;
import org.plazmaforge.framework.report.model.base.Padding;
import org.plazmaforge.framework.report.model.base.Pen;
import org.plazmaforge.framework.report.model.base.Size;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;

/**
 * 
 * @author ohapon
 *
 */
public class XMLAbstractWriter extends XMLWorker implements XMLInfo {

    
    //// COMMON-BEGIN
    
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
    
    protected Element createElement(String name) {
	Element node = new Element(name);
	return node;
    }
    
    protected void addChild(Element parent, Element child) {
	parent.getChildren().add(child);
    }
    
    //// COMMON-END

    
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

  	// font
  	Font font = element.getFont();
  	if (font != null) {
  	    setFont(node, XML_ATTR_FONT, font);
  	}
  	
    }    
    
    ////
    
    protected void setSizeByAttributes(Size size, Element node) {
  	if (size == null || size.isEmpty()) {
  	    return;
  	}
  	if (size.hasWidth()) {
  	    setIntegerValue(node, XML_ATTR_WIDTH, size.getWidth());
  	}
  	if (size.hasHeight()) {
  	    setIntegerValue(node, XML_ATTR_HEIGHT, size.getHeight());
  	}
    }
    
    protected void setMarginByAttributes(Margin margin, Element node) {
   	if (margin == null || margin.isEmpty()) {
   	    return;
   	}
   	if (margin.hasLeft()) {
   	    setIntegerValue(node, XML_ATTR_MARGIN_LEFT, margin.getLeft());
   	}
   	if (margin.hasTop()) {
   	    setIntegerValue(node, XML_ATTR_MARGIN_TOP, margin.getTop());
   	}
   	if (margin.hasRight()) {
   	    setIntegerValue(node, XML_ATTR_MARGIN_RIGHT, margin.getRight());
   	}
   	if (margin.hasBottom()) {
   	    setIntegerValue(node, XML_ATTR_MARGIN_BOTTOM, margin.getBottom());
   	}
    }

    protected void setPaddingByAttributes(Padding padding, Element node) {
   	if (padding == null || padding.isEmpty()) {
   	    return;
   	}
   	if (padding.hasLeft()) {
   	    setIntegerValue(node, XML_ATTR_PADDING_LEFT, padding.getLeft());
   	}
   	if (padding.hasTop()) {
   	    setIntegerValue(node, XML_ATTR_PADDING_TOP, padding.getTop());
   	}
   	if (padding.hasRight()) {
   	    setIntegerValue(node, XML_ATTR_PADDING_RIGHT, padding.getRight());
   	}
   	if (padding.hasBottom()) {
   	    setIntegerValue(node, XML_ATTR_PADDING_BOTTOM, padding.getBottom());
   	}
    }
    
    ////

    protected void setBorder(Border border, Element node) {
	setBorder(border, node, null);
    }
    
    protected void setBorder(Border border, Element node, String prefixAttribute) {
	setBorderByAttributes(border, node, prefixAttribute);
    }
    
    
    protected void setBorderByAttributes(Border border, Element node) {
	setBorderByAttributes(border, node, null);
    }
	
    protected void setBorderByAttributes(Border border, Element node, String prefixAttribute) {
   	if (border == null) {
   	    return;
   	}
   	prefixAttribute = prefixAttribute == null ? "" : (prefixAttribute  + "-");
   	
   	if (border.isNormalize()) {
   	    setBorderPenByAttributes(border.getLeft(), node, prefixAttribute + XML_ATTR_BORDER);
   	    return;
   	}
   	
   	//if (border.isEmpty()) {
   	//    return;
   	//}
   	
   	if (border.hasLeft()) {
   	    setBorderPenByAttributes(border.getLeft(), node, prefixAttribute + XML_ATTR_BORDER_LEFT);
   	}
   	if (border.hasTop()) {
   	    setBorderPenByAttributes(border.getTop(), node, prefixAttribute + XML_ATTR_BORDER_TOP);
   	}
   	if (border.hasRight()) {
   	    setBorderPenByAttributes(border.getRight(), node, prefixAttribute + XML_ATTR_BORDER_RIGHT);
   	}
   	if (border.hasBottom()) {
   	    setBorderPenByAttributes(border.getBottom(), node, prefixAttribute + XML_ATTR_BORDER_BOTTOM);
   	}
    }
    
    protected void setBorderPenByAttributes(Pen pen, Element element,
	    String borderAttribute) {
	if (pen == null) {
	    return;
	}

	if (borderAttribute == null) {
	    borderAttribute = XML_ATTR_BORDER;
	}

	setPenByAttributes(pen, element, borderAttribute);

    }

    protected void setPenByAttributes(Pen pen, Element element, String penAttribute) {
	if (pen == null) {
	    return;
	}

	if (pen == Pen.NONE) {
	    setStringValue(element, penAttribute, NONE);
	    return;
	}

	if (pen.isEmpty()) {
	    return;
	}

	// width
	float width = pen.getLineWidth() <= 0 ? 1f : pen.getLineWidth();
	// setFloatValue(element, borderAttribute, width);

	// TODO: Temp solution. Fix after float 'Size' and 'Point'
	width = Math.round(width);
	setIntegerValue(element, penAttribute, (int) width);

	// style
	int lineStyle = pen.getLineStyle();
	String style = formatLineStyle(lineStyle, true);
	setStringValue(element, penAttribute + "-style", style);

	// color
	Color color = pen.getLineColor();
	setColor(element, penAttribute + "-color", color);
    }

    ////

    protected void setExpression(DSExpression expression, Element node) {
   	setExpression(expression, node, true);
    }

    protected void setExpression(DSExpression expression, Element node, boolean isUseDataType) {
   	if (expression == null) {
   	    return;
   	}
   	// content
   	setContentValue(node, expression.getText());
   	
   	// dataType
   	if (isUseDataType) {
   	    setStringValue(node, XML_ATTR_DATA_TYPE, expression.getDataType());
   	}
   	
    }
    
    
    protected void writeIdentifier(LocalizedIdentifier identifier, Element node) {

     	// id
  	if (identifier.getId() != null) {
  	    setStringValue(node, XML_ATTR_ID, identifier.getId());
  	}
  	
     	// name
  	if (identifier.getName() != null) {
  	    setStringValue(node, XML_ATTR_NAME, identifier.getName());
  	}

     	// caption  	
  	if (identifier.getCaption() != null) {
  	    setStringValue(node, XML_ATTR_CAPTION, identifier.getCaption());
    	}

     	// description  	
  	if (identifier.getCaption() != null) {
  	    setStringValue(node, XML_ATTR_DESCRIPTION, identifier.getDescription());
    	}
  	
     	
      }
    
}
