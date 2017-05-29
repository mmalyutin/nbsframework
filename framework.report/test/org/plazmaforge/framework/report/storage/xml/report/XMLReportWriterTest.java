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

/**
 * 
 */
package org.plazmaforge.framework.report.storage.xml.report;



import java.io.StringWriter;

import org.plazmaforge.framework.report.model.base.grid.Column;
import org.plazmaforge.framework.report.model.design.Report;
import org.plazmaforge.framework.report.model.design.Template;

import junit.framework.TestCase;

/**
 * @author ohapon
 *
 */
public class XMLReportWriterTest extends TestCase {

    public void testWrite() throws Exception {
	XMLReportWriter writer = new XMLReportWriter();
	
	Report report1 = createTestReport();
	
	// Write the report
	StringWriter sw = new StringWriter();
	writer.writeReport(report1, sw);
	
	String reportString1 = sw.toString(); 
	System.out.println(reportString1);

    }
    
    private Report createTestReport() {
	
	Report report = new Report();
	
	report.setName("Report1");
	report.setCaption("Report 1");
	report.setDescription("Report Description 1");

	
	Template template = new Template();
	template.setType("Table");
	
	template.getPageSetup().getMargin().setLeft(20);
	template.getPageSetup().getMargin().setTop(20);
	template.getPageSetup().getMargin().setBottom(20);
	
	template.addColumn(new Column(150));
	template.addColumn(new Column(250));
	template.addColumn(new Column(100));
	
	report.addTemplate(template);
	
	return report;
    }
    
}
