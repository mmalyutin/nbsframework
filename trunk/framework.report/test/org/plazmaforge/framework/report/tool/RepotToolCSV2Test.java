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

package org.plazmaforge.framework.report.tool;

import org.plazmaforge.framework.report.AbstractTestCase;



public class RepotToolCSV2Test extends AbstractTestCase {

    
    public void testReportCSV2() {
	
	String reportFile = getResourcesFileName("reports/Orders.report.xml");
	String dataFile = getResourcesFileName("data/orders.csv");
	String documentFile = getTestFileName("OrdersCSV_DC.pdf");
	String connection = format("csv::{0}->(firstRowHeader=true; rowDelimiter=LF; encoding=UTF-8)", dataFile);
	//String connection = format("csv::{0}->(firstRowHeader=true)", dataFile);
	
	String[] args = new String[] {
		"-report", reportFile, 
		"-document", documentFile,
		"-log", "true", 
		"-format", "PDF", 
		"-connection", connection};
	
	ReportTool.main(args);
    }
    
    
}
