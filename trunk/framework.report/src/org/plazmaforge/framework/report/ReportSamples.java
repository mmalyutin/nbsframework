/*
 * Copyright (C) 2012-2013 Oleh Hapon ohapon@users.sourceforge.net
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
package org.plazmaforge.framework.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.core.datastorage.DSArrayResultSet;
import org.plazmaforge.framework.core.datastorage.DSExpression;
import org.plazmaforge.framework.core.datastorage.DSResultSet;
import org.plazmaforge.framework.core.datastorage.DSVariable;
import org.plazmaforge.framework.report.exception.RTException;
import org.plazmaforge.framework.report.model.base.grid.Cell;
import org.plazmaforge.framework.report.model.base.grid.Column;
import org.plazmaforge.framework.report.model.base.grid.Row;
import org.plazmaforge.framework.report.model.design.Band;
import org.plazmaforge.framework.report.model.design.Report;
import org.plazmaforge.framework.report.model.design.ReportGroup;
import org.plazmaforge.framework.report.model.design.Template;
import org.plazmaforge.framework.report.model.document.Document;
import org.plazmaforge.framework.uwt.graphics.Color;

/**
 * @author ohapon
 *
 */
public class ReportSamples {

    
    public static Report createTableReport() throws RTException {

	Report report = new Report();
	
	DSVariable variable = new DSVariable();
	variable.setName("VAR1");
	
	DSExpression expression = new DSExpression();
	expression.setText("$F{PRICE}");
	
	variable.setExpression(expression);
	variable.setAggregation("SUM");
	variable.setResetType("Group");
	variable.setResetValue("Group1");
	report.addVariable(variable);
	
	
	Template template = new Template();
	template.setType("Table");
	
	template.getPageSetup().getMargin().setLeft(20);
	template.getPageSetup().getMargin().setTop(20);
	template.getPageSetup().getMargin().setBottom(20);
	
	template.addColumn(new Column(150));
	template.addColumn(new Column(250));
	template.addColumn(new Column(100));
	
	report.addTemplate(template);

	// REPORT HEADER
	Band band = new Band();
	band.setType("ReportHeader");
	template.addBand(band);
	
	Row row = new Row();
	row.setHeight(15);
	band.addRow(row);
	
	Cell cell = new Cell();
	cell.setColspan(2);
	cell.setValue("My Report");
	row.addCell(cell);

	cell = new Cell();
	cell.setExpression("$P{PARAM1} + \" \" + PI()");
	row.addCell(cell);

	
	// COLUMN HEADER
	band = new Band();
	band.setType("ColumnHeader");
	template.addBand(band);
	
	row = new Row();
	row.setHeight(30);
	band.addRow(row);

	cell = new Cell();
	cell.setValue("Group");
	row.addCell(cell);

	cell = new Cell();
	cell.setValue("Product");
	row.addCell(cell);

	cell = new Cell();
	cell.setValue("Price");
	row.addCell(cell);
	
	
	// GROUP
	
	ReportGroup group = new ReportGroup();
	group.setStartOnNewPage(true);
	
	template.addGroup(group);
	group.setName("Group1");
	group.setExpressionText("$F{GROUP_NAME}");
	
	// GROUP HEADER
	band = new Band();
	band.setType("GroupHeader");
	band.setBackground(Color.GRAY);
	group.addBand(band);

	row = new Row();
	row.setHeight(25);
	band.addRow(row);

	cell = new Cell();
	row.addCell(cell);
	cell.setValue("Group Header:");

	cell = new Cell();
	row.addCell(cell);
	cell.setExpression("$F{GROUP_NAME}");

	// DETAIL
	band = new Band();
	band.setType("Detail");
	template.addBand(band);

	row = new Row();
	row.setHeight(30);
	band.addRow(row);

	cell = new Cell();
	cell.setExpression("$F{GROUP_NAME}");
	row.addCell(cell);

	cell = new Cell();
	cell.setExpression("$F{PRODUCT_NAME}");
	row.addCell(cell);

	cell = new Cell();
	cell.setDataType("Float");
	cell.setFormat("##0.00");
	cell.setExpression("$F{PRICE}");
	row.addCell(cell);
	
	
	// GROUP FOOTER
	band = new Band();
	band.setType("GroupFooter");
	band.setBackground(Color.GRAY);
	group.addBand(band);

	row = new Row();
	row.setHeight(15);
	band.addRow(row);

	cell = new Cell();
	row.addCell(cell);
	cell.setValue("Group Footer:");

	cell = new Cell();
	row.addCell(cell);
	cell.setExpression("$F{GROUP_NAME}");
	
	cell = new Cell();
	row.addCell(cell);
	cell.setExpression("$V{VAR1}");
		
	// COLUMN FOOTER
	band = new Band();
	band.setType("ColumnFooter");
	template.addBand(band);
	
	row = new Row();
	row.setHeight(50);
	band.addRow(row);

	cell = new Cell();
	cell.setValue("");
	row.addCell(cell);	

	cell = new Cell();
	cell.setValue("Total records");
	row.addCell(cell);

	cell = new Cell();
	cell.setExpression("$V{RECORD_NO}");
	row.addCell(cell);	

	
	// REPORT FOOTER
	band = new Band();
	band.setType("ReportFooter");
	template.addBand(band);

	row = new Row();
	row.setHeight(25);
	band.addRow(row);

	cell = new Cell();
	cell.setColspan(3);
	cell.setValue("Summary");
	row.addCell(cell);

		
	// PAGE FOOTER
	band = new Band();
	band.setType("PageFooter");
	template.addBand(band);
	
	row = new Row();
	band.addRow(row);
	row.setHeight(20);

	cell = new Cell();
	row.addCell(cell);

	cell = new Cell();
	cell.setValue("Page No");
	row.addCell(cell);

	cell = new Cell();
	cell.setExpression("$V{PAGE_NO}");
	row.addCell(cell);
	
	return report;
    }
    
    public static DSResultSet createProductResultSet() throws RTException {
	List<String> fields = new ArrayList<String>();
	fields.add("GROUP_NAME");
	fields.add("PRODUCT_NAME");
	fields.add("PRICE");
	
	List<Object[]> records = new ArrayList<Object[]>();
	Object[] dataRow = null;
	for (int i = 1; i <= 30; i++) {
	    dataRow = new Object[3];
	    dataRow[0] = "Group " + (i <= 3 ? "1" : (i <= 10? "2" : "3"));
	    dataRow[1] = "Product " + i;
	    dataRow[2] = 100 + (i / 100.0);
	    records.add(dataRow);
	}
	DSResultSet reportData = new DSArrayResultSet(fields, records);
	
	return reportData;
	
    }
    
    public static Document createTableReportDocument() throws RTException {
	return createTableReportDocument(null);
    }
    
    public static Document createTableReportDocument(Map<String, Object> parameters) throws RTException {
	
	Report report = createTableReport();
	DSResultSet reportData = createProductResultSet();
	
	ReportManager manager = new ReportManager();
	Document document = manager.fillReport(report, reportData, parameters);
	
	return document;
    }
}
