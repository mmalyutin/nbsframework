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

import org.plazmaforge.framework.report.export.AbstractTextExporter;

public abstract class AbstractHTMLExporter extends AbstractTextExporter {

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
    
}
