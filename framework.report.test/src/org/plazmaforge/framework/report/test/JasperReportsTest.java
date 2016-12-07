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

package org.plazmaforge.framework.report.test;

import java.io.InputStream;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.xml.JRPrintXmlLoader;

public class JasperReportsTest extends DataTestCase {

    public void test() throws Exception {
	
	String storage= "C:\\Dir\\Plazma\\test.storage";

	String reportFile = "resources/reports/DataSourceReport.jrxml";
	String documentFile = storage + "/DataSourceReport.jrprint";

	InputStream is = getClass().getResourceAsStream(reportFile);
	long start = System.currentTimeMillis();

	JasperReport report = JasperCompileManager.compileReport(is);
	logDeltaTime("Compile time", start);
	
	//.fillReportToFile("build/reports/DataSourceReport.jasper", parameters, new CustomDataSource());
	//JasperFillManager.fillReport(sourceFileName, params)
	
	start = System.currentTimeMillis();
	JasperPrint jasperPrint = JasperFillManager.fillReport(report, (Map) null, getConnection());
	logDeltaTime("Fill time   ", start);
	
	start = System.currentTimeMillis();
	JasperExportManager.exportReportToXmlFile(jasperPrint, documentFile, false);
	logDeltaTime("Export time ", start);
	
	start = System.currentTimeMillis();
	JasperPrint document = JRPrintXmlLoader.load(documentFile);
	logDeltaTime("Load time  ", start);
	
    }
    
    private float timeMs(long time ) {
	return (float) time / 1000;
    }
    
    private float timeDeltaMs(long start) {
	return timeMs(System.currentTimeMillis() - start);
    }
 
    private void logDeltaTime(String text, long start) {
	System.out.println(text + ": " + timeDeltaMs(start)  + " s");
    }
}

