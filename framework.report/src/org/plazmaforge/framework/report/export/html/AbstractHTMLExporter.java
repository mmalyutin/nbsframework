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

import org.plazmaforge.framework.report.exception.RTException;
import org.plazmaforge.framework.report.export.AbstractTextExporter;
import org.plazmaforge.framework.report.model.base.Border;
import org.plazmaforge.framework.report.model.base.Insets;
import org.plazmaforge.framework.report.model.base.Padding;
import org.plazmaforge.framework.report.model.base.Pen;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;

/**
 * 
 * @author ohapon
 *
 */
public abstract class AbstractHTMLExporter extends AbstractTextExporter {

    
    public static String DEFAULT_FONT_FAMILY = "Arial, Helvetica, sans-serif";
    
    public static int DEFAULT_FONT_SIZE = 12;

    
    
    protected int level;
    
    

    
    protected void writeHeader() throws IOException {

	String encoding = getEncoding();

	write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
	write("<html>\n");
	write("<head>\n");
	write("  <title></title>\n");
	write("  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=" + encoding + "\"/>\n");
	write("  <style type=\"text/css\">\n");
	write("    a {text-decoration: none}\n");
	write("  </style>\n");
	write("</head>\n");
	write("<body text=\"#000000\" link=\"#000000\" alink=\"#000000\" vlink=\"#000000\">\n");
	//write("<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n");
	//write("<tr><td width=\"50%\">&nbsp;</td><td align=\"center\">\n");
	write("\n");
	
    }
    
    protected void writeFooter() throws IOException {
	//writer.write("</td><td width=\"50%\">&nbsp;</td></tr>\n");
	//writer.write("</table>\n");
	writer.write("</body>\n");
	writer.write("</html>\n");
    }
    
    protected void write(String str) throws IOException {
	if (level > 0) {
	    String tab = "  "; //"\t"
	    for (int i = 0; i < level; i++) {
		super.write(tab);
	    }
	}
	super.write(str);
    }

    protected void writeText(String text, Integer x, Integer y, Integer width, Integer height, Insets margin, Font font, Color foreground, HorizontalAlign horizontalAlign, VerticalAlign verticalAlign) throws RTException, IOException {
	if (text == null) {
	    return;
	}
	
	Attributes styleAttributes = new Attributes();

	setPosition(styleAttributes, x, y);
	setSize(styleAttributes, width, height);
	setHorizontalAlign(styleAttributes, horizontalAlign);
	setVerticalAlign(styleAttributes, verticalAlign);
	setMargin(styleAttributes, margin);
	
	if (verticalAlign != null) {
	    styleAttributes.addAttribute("display", "table-cell");
	}
	
	String style = styleAttributes.toStyleAttribute("style");
	
	levelInc();
	
	writeStartTag("div", style);
	write(text + "\n");
	writeEndTag("div");
	
	levelDec();
	
    }
    
    protected void levelInc() {
	level++;
    }

    protected void levelDec() {
	level--;
    }
    
    protected void writeStartTag(String tag, String attr) throws IOException {
	write("<" + tag + (attr == null ? "" : (" " + attr)) + ">\n");
    }

    protected void writeEndTag(String tag) throws IOException {
	write("</" + tag + ">\n");
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Convert utilities methods (to<Type>String)
    //
    /////////////////////////////////////////////////////////////////////////////////////////////

    protected String toUnitString(int value, String unit) {
	return "" + value + (unit == null ? "" : unit);
    }

    protected String toUnitString(float value, String unit) {
	return "" + value + (unit == null ? "" : unit);
    }
    
    protected String toPXString(int value) {
	return toUnitString(value, "px");
    }

    protected String toPXString(float value) {
	return toUnitString(value, "px");
    }
    
    protected String toDimensionString(int value) {
	return toPXString(value);
    }

    protected String toDimensionString(float value) {
	return toPXString(value);
    }
    
    // font: size
    protected String toFontSizeString(int value) {
	return toUnitString(value, "px");
    }
    
    // font: size
    protected String toFontSizeString(Font font) {
	if (font == null || font.isEmptySize()) {
	    return null;
	}
	return toFontSizeString(font.getSize());
    }
    
    // font: style (bold, italic, ...)
    protected String toFontStyleString(Font font) {
	if (font == null || font.isEmptyStyle()) {
	    return null;
	}
	StringBuffer buf = new StringBuffer();
	
	// bold
	if (font.isBold()) {
	    buf.append("bold");
	}
	
	// italic
	if (font.isItalic()) {
	    if (buf.length() > 0) {
		buf.append(" ");
	    }
	    buf.append("italic");
	}
	
	// 'font' attribute doesn't support 'underline', 'strikeout'
	// Use 'text-decoration' ('underline', 'line-through')
	
	if (buf.length() == 0) {
	    return null;
	}
	
	return buf.toString();
    }
    
    // font
    protected String toFontString(Font font) {
	if (font == null || font.isEmpty()) {
	    return null;
	}
	StringBuffer buf = new StringBuffer();
	String value = null;
	
	// name
	if (!font.isEmptyName()) {
	    buf.append(font.getName());
	}
	
	// size
	if (!font.isEmptySize()) {
	    value = toFontSizeString(font);
	    if (value != null) {
		if (buf.length() > 0) {
		    buf.append(" ");
		}
		buf.append(value);
	    }
	}

	// style
	if (!font.isEmptyStyle()) {
	    value = toFontStyleString(font);
	    if (value != null) {
		if (buf.length() > 0) {
		    buf.append(" ");
		}
		buf.append(value);
	    }

	    // 'font' attribute doesn't support 'underline', 'strikeout'
	    // Use 'text-decoration' ('underline', 'line-through')

	}
	
	if (buf.length() == 0) {
	    return null;
	}
	
	return buf.toString();
    }
    
    // color
    protected String toColorString(Color color) {
	return color == null ? null : ("#" + color.toHexString());
    }

    // horizontal align
    protected String toHorizontalAlign(HorizontalAlign horizontalAlign) {
   	if (horizontalAlign == null) {
   	    return null;
   	}
   	String value = null;
   	if (horizontalAlign == HorizontalAlign.LEFT) {
   	    value = "left";
   	} else if (horizontalAlign == HorizontalAlign.RIGHT) {
   	    value = "right";
   	} else if (horizontalAlign == HorizontalAlign.CENTER) {
   	    value = "center";
   	} else if (horizontalAlign == HorizontalAlign.FILL) {
   	    value = "justify";
   	}
   	return value;
    }
    
    // vertical align
    protected String toVerticalAlignString(VerticalAlign verticalAlign) {
	if (verticalAlign == null) {
	    return null;
	}
	String value = null;
	if (verticalAlign == VerticalAlign.TOP) {
	    value = "top";
	} else if (verticalAlign == VerticalAlign.BOTTOM) {
	    value = "bottom";
	} else if (verticalAlign == VerticalAlign.MIDDLE) {
	    value = "middle";
	}
	return value;
    }    

    
    /////////////////////////////////////////////////////////////////////////////////////////////
    //
    // 'Set' utilities methods
    //
    /////////////////////////////////////////////////////////////////////////////////////////////
    

    // font
    protected void setFont(Attributes styleAttributes, Font font) {
	if (font == null || font.isEmpty()) {
	    return;
	}
	
	String value = null;
	
	// name
   	if (!font.isEmptyName()) {
   	    styleAttributes.addAttribute("font-family", font.getName());
   	}

   	// size
   	if (!font.isEmptySize()) {
   	    value = toFontSizeString(font);
   	    if (value != null) {
   		styleAttributes.addAttribute("font-size", value);
   	    }
   	}

   	// style
   	if (!font.isEmptyStyle()) {
   	    
	    // bold
	    if (font.isBold()) {
		styleAttributes.addAttribute("font-weight", "bold");
	    }

	    // italic
	    if (font.isItalic()) {
		styleAttributes.addAttribute("font-style", "italic");
	    }
   		
	    // underline | strikeout
	    if (font.isUnderline() || font.isStrikeout()) {
		String textDecoration = null;
		if (font.isUnderline()) {
		    textDecoration = "underline";
		}
		if (font.isStrikeout()) {
		    textDecoration = (textDecoration == null ? "" : (textDecoration + " ")) + "line-through";
		}
		styleAttributes.addAttribute("text-decoration", textDecoration);
	    }
   	}
   	
	//styleAttributes.addAttribute("font", toFontString(font));
    }

    // color
    protected void setColor(Attributes styleAttributes, String name, Color color) {
	if (color == null) {
	    return;
	}	
	styleAttributes.addAttribute(name, toColorString(color));
    }
    
    // color: background
    protected void setBackground(Attributes styleAttributes, Color background) {
	setColor(styleAttributes, "background", background);
    }

    // color: foreground
    protected void setForeground(Attributes styleAttributes, Color foreground) {
	setColor(styleAttributes, "color", foreground);
    }

    // position
    protected void setPosition(Attributes styleAttributes, Integer x, Integer y) {
	if (x == null && y == null) {
	    return;
	}
	styleAttributes.addAttribute("position", "absolute");
	if (x != null) {
	    styleAttributes.addAttribute("left", toDimensionString(x));
	}
	if (y != null) {
	    styleAttributes.addAttribute("top", toDimensionString(y));
	}
    }
    
    // size
    protected void setSize(Attributes styleAttributes, Integer width, Integer height) {
	if (width == null && height == null) {
	    return;
	}
	if (width != null) {
	    styleAttributes.addAttribute("width", toDimensionString(width));
	}
	if (height != null) {
	    styleAttributes.addAttribute("height", toDimensionString(height));
	}
    }

    // height
    protected void setHeight(Attributes styleAttributes, Integer height) {
	setSize(styleAttributes, null, height);
    }

    // width
    protected void setWidth(Attributes styleAttributes, Integer width) {
	setSize(styleAttributes, width, null);
    }
    
    // border (left, top, right, bottom)
    protected void setBorder(Attributes styleAttributes, Border border) {
	if (border == null || border.isEmpty()) {
	    return;
	}

	// Left
	if (border.hasLeft()) {
	    setBorder(styleAttributes, "border-left", border.getLeft());
	}
	
	// Right
	if (border.hasRight()) {
	    setBorder(styleAttributes, "border-right", border.getRight());
	}

	// Top
	if (border.hasTop()) {
	    setBorder(styleAttributes, "border-top", border.getTop());
	}

	// Bottom
	if (border.hasBottom()) {
	    setBorder(styleAttributes, "border-bottom", border.getBottom());
	}
	
    }
    
    // border (one line)
    protected void setBorder(Attributes styleAttributes, String name, Pen pen) {
	if (pen == null || pen.isEmpty()) {
	    return;
	}
	float w = getLineWidth(pen);
	Color color = getLineColor(pen);
	styleAttributes.addAttribute(name, "" + toDimensionString(w) + " solid " + toColorString(color));
    }
    
    
    // margin (left, top, right, bottom)
    protected void setMargin(Attributes styleAttributes, Insets margin) {
	if (margin == null || margin.isEmpty()) {
	    return;
	}

	// Left
	setMargin(styleAttributes, "margin-left", margin.getLeft());
	
	// Right
	setMargin(styleAttributes, "margin-right", margin.getRight());

	// Top
	setMargin(styleAttributes, "margin-top", margin.getTop());

	// Bottom
	setMargin(styleAttributes, "margin-bottom", margin.getBottom());
	
    }

    // margin (one size)
    protected void setMargin(Attributes styleAttributes, String name, Integer value) {
	if (value == null) {
	    return;
	}
	styleAttributes.addAttribute(name, toDimensionString(value));
    }
    
    // padding (left, top, right, bottom)
    protected void setPadding(Attributes styleAttributes, Insets padding) {
	if (padding == null || padding.isEmpty()) {
	    return;
	}

	// Left
	setPadding(styleAttributes, "padding-left", padding.getLeft());
	
	// Right
	setPadding(styleAttributes, "padding-right", padding.getRight());

	// Top
	setPadding(styleAttributes, "padding-top", padding.getTop());

	// Bottom
	setPadding(styleAttributes, "padding-bottom", padding.getBottom());
	
    }

    // padding (one size)
    protected void setPadding(Attributes styleAttributes, String name, Integer value) {
	if (value == null) {
	    return;
	}
	styleAttributes.addAttribute(name, toDimensionString(value));
    }
    
    
    // horizontal align
    protected void setHorizontalAlign(Attributes styleAttributes, HorizontalAlign horizontalAlign) {
	if (horizontalAlign == null) {
	    return;
	}
	String value = toHorizontalAlign(horizontalAlign);
	if (value == null) {
	    return;
	}
	styleAttributes.addAttribute("text-align", value); // TODO
    }

    // vertical align
    protected void setVerticalAlign(Attributes styleAttributes, VerticalAlign verticalAlign) {
	if (verticalAlign == null) {
	    return;
	}
	String value = toVerticalAlignString(verticalAlign);
	if (value == null) {
	    return;
	}
	styleAttributes.addAttribute("vertical-align", value);
	
    }
    
    
}
