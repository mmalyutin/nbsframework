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

package org.plazmaforge.framework.report.fill.script.ps;

import java.io.IOException;

import org.plazmaforge.framework.core.datastorage.DSExpression;
import org.plazmaforge.framework.report.fill.process.ReportContext;
import org.plazmaforge.framework.report.fill.script.ScriptGenerator;

/**
 * Plazma Script Generator
 * 
 * @author ohapon
 *
 */
public class PSScriptGenerator implements ScriptGenerator {

    @Override
    public void generateHeader(Appendable buf, ReportContext context) {
	// No header
    }

    @Override
    public void generateFooter(Appendable buf, ReportContext context) {
	// No footer
    }

    @Override
    public void generateScript(Appendable buf, ReportContext context,  String sourceCode) {
	// TODO: Must implement
    }

    @Override
    public void generateExpression(Appendable buf, DSExpression expression) throws IOException {
	String expressionId = expression.getId();
	String expressionText = expression.getText();
	String functioName = PSUtils.getExpressionFunctionName(expressionId);
	buf.append("\n");
	buf.append("\ndef " + functioName  + "() {");
	buf.append("\n  return " + expressionText + ";");
	buf.append("\n}");
    }

    @Override
    public void generateEvent(Appendable buf, ReportContext context, String type, String sourceCode) {
	// TODO: Must implement
    }

}
