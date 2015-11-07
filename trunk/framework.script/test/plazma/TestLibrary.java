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

package plazma;

import junit.framework.TestCase;

public class TestLibrary extends TestCase {

    public void testScript() throws Exception {
	
	ExpressionEvaluator evaluator = new ExpressionEvaluator(ExpressionEvaluator.loadLibraryFunctions());
	
	evaluate(evaluator, "1 + 2");
	evaluate(evaluator, "2 * 2");
	evaluate(evaluator, "7 - 5");
	evaluate(evaluator, "7 / 5");
	evaluate(evaluator, "7 / 5 ^ 2");

	evaluate(evaluator, "PI()");
	evaluate(evaluator, "E()");

	evaluate(evaluator, "min(7, 5)");
	evaluate(evaluator, "min(5, 7)");
	evaluate(evaluator, "min(7.0, 5)");
	evaluate(evaluator, "min(7.0, 5.0)");
	
	evaluate(evaluator, "max(7, 5)");
	evaluate(evaluator, "max(5, 7)");
	evaluate(evaluator, "max(7.0, 5)");
	evaluate(evaluator, "max(7.0, 5.0)");
	
	evaluate(evaluator, "percent(100, 20)");
	evaluate(evaluator, "percent(500, 10)");
	
	evaluate(evaluator, "substr(\"hello\", 1, 3)");
	
	evaluate(evaluator, "ltrim(\"hello\")");
	evaluate(evaluator, "ltrim(\" hello\")");
	evaluate(evaluator, "ltrim(\"  hello\")");
	evaluate(evaluator, "ltrim(\"  hello  \")");
	
    }

    private void evaluate(ExpressionEvaluator evaluator, String expression) throws EvaluateException {
	Object value = evaluator.evaluate(expression);
	System.out.println("expression='" + expression + "', value='" + value + "'");
    }

}
