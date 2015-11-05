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
package org.plazmaforge.framework.core.datastorage.data;

import junit.framework.TestCase;

/**
 * @author ohapon
 *
 */
public class QueryAnalyzerTest extends TestCase {
    
    public void testQuery() {
	QueryAnalyzer analyzer = new QueryAnalyzer();
	
	QueryInfo queryInfo = null;
	QueryParameter parameter = null; 
		
	queryInfo = analyzer.analyzeQuery(null);
	assertNotNull(queryInfo);
	assertNull(queryInfo.getOriginalQuery());
	assertNull(queryInfo.getCompileQuery());
	assertEquals(false, queryInfo.hasParameters());
	
	queryInfo = analyzer.analyzeQuery("");
	assertNotNull(queryInfo);
	assertEquals("" , queryInfo.getOriginalQuery());
	assertNull(queryInfo.getCompileQuery());
	assertEquals(false, queryInfo.hasParameters());

	queryInfo = analyzer.analyzeQuery(" ");
	assertNotNull(queryInfo);
	assertEquals(" " , queryInfo.getOriginalQuery());
	assertNull(queryInfo.getCompileQuery());
	assertEquals(false, queryInfo.hasParameters());

	queryInfo = analyzer.analyzeQuery("SELECT A, B, C FROM T");
	assertNotNull(queryInfo);
	assertEquals("SELECT A, B, C FROM T" , queryInfo.getOriginalQuery());
	assertEquals("SELECT A, B, C FROM T", queryInfo.getCompileQuery());
	assertEquals(false, queryInfo.hasParameters());

	queryInfo = analyzer.analyzeQuery(" SELECT A, B, C FROM T ");
	assertNotNull(queryInfo);
	assertEquals(" SELECT A, B, C FROM T " , queryInfo.getOriginalQuery());
	assertEquals("SELECT A, B, C FROM T", queryInfo.getCompileQuery());
	assertEquals(false, queryInfo.hasParameters());

	// 1 Parameter
	queryInfo = analyzer.analyzeQuery("SELECT A, B, C FROM T WHERE A = :QWERTY");
	assertNotNull(queryInfo);
	assertEquals("SELECT A, B, C FROM T WHERE A = :QWERTY" , queryInfo.getOriginalQuery());
	assertEquals("SELECT A, B, C FROM T WHERE A = ?", queryInfo.getCompileQuery());
	assertEquals(true, queryInfo.hasParameters());
	assertEquals(1, queryInfo.getParameterCount());
	
	parameter = queryInfo.getParameters().get(0);
	assertEquals("QWERTY", parameter.getName());
	System.out.println(queryInfo);

	// 1 Parameter
	queryInfo = analyzer.analyzeQuery("SELECT A, B, C FROM T WHERE A = :QWERTY ");
	assertNotNull(queryInfo);
	assertEquals("SELECT A, B, C FROM T WHERE A = :QWERTY " , queryInfo.getOriginalQuery());
	assertEquals("SELECT A, B, C FROM T WHERE A = ?", queryInfo.getCompileQuery());
	assertEquals(true, queryInfo.hasParameters());
	assertEquals(1, queryInfo.getParameterCount());
	
	parameter = queryInfo.getParameters().get(0);
	assertEquals("QWERTY", parameter.getName());
	System.out.println(queryInfo);

	// 1 Parameter (no name)
	queryInfo = analyzer.analyzeQuery("SELECT A, B, C FROM T WHERE A = :");
	assertNotNull(queryInfo);
	assertEquals("SELECT A, B, C FROM T WHERE A = :" , queryInfo.getOriginalQuery());
	assertEquals("SELECT A, B, C FROM T WHERE A = ?", queryInfo.getCompileQuery());
	assertEquals(true, queryInfo.hasParameters());
	assertEquals(1, queryInfo.getParameterCount());
	
	parameter = queryInfo.getParameters().get(0);
	assertEquals("", parameter.getName());
	System.out.println(queryInfo);

	// 2 Parameter (no name)
	queryInfo = analyzer.analyzeQuery("SELECT A, B, C FROM T WHERE A = :: ");
	assertNotNull(queryInfo);
	assertEquals("SELECT A, B, C FROM T WHERE A = :: " , queryInfo.getOriginalQuery());
	assertEquals("SELECT A, B, C FROM T WHERE A = ??", queryInfo.getCompileQuery());
	assertEquals(true, queryInfo.hasParameters());
	assertEquals(2, queryInfo.getParameterCount());
	
	parameter = queryInfo.getParameters().get(0);
	assertEquals("", parameter.getName());
	System.out.println(queryInfo);
	
	// 2 Parameter
	queryInfo = analyzer.analyzeQuery("SELECT A, B, C FROM T WHERE A = :QWERTY and B = :ASDFG");
	assertNotNull(queryInfo);
	assertEquals("SELECT A, B, C FROM T WHERE A = :QWERTY and B = :ASDFG" , queryInfo.getOriginalQuery());
	assertEquals("SELECT A, B, C FROM T WHERE A = ? and B = ?", queryInfo.getCompileQuery());
	assertEquals(true, queryInfo.hasParameters());
	assertEquals(2, queryInfo.getParameterCount());
	
	parameter = queryInfo.getParameters().get(0);
	assertEquals("QWERTY", parameter.getName());
	parameter = queryInfo.getParameters().get(1);
	assertEquals("ASDFG", parameter.getName());
	
	System.out.println(queryInfo);

	// 2 Parameter
	queryInfo = analyzer.analyzeQuery("SELECT A, B, C FROM T WHERE A = :QWERTY and B = :ASDFG and C = 23");
	assertNotNull(queryInfo);
	assertEquals("SELECT A, B, C FROM T WHERE A = :QWERTY and B = :ASDFG and C = 23" , queryInfo.getOriginalQuery());
	assertEquals("SELECT A, B, C FROM T WHERE A = ? and B = ? and C = 23", queryInfo.getCompileQuery());
	assertEquals(true, queryInfo.hasParameters());
	assertEquals(2, queryInfo.getParameterCount());
	
	parameter = queryInfo.getParameters().get(0);
	assertEquals("QWERTY", parameter.getName());
	parameter = queryInfo.getParameters().get(1);
	assertEquals("ASDFG", parameter.getName());
	
	System.out.println(queryInfo);

	// 2 Parameter
	queryInfo = analyzer.analyzeQuery("SELECT A, B, C FROM T WHERE X = ':param' A = :QWERTY and B = :ASDFG and C = ':'");
	assertNotNull(queryInfo);
	assertEquals("SELECT A, B, C FROM T WHERE X = ':param' A = :QWERTY and B = :ASDFG and C = ':'" , queryInfo.getOriginalQuery());
	assertEquals("SELECT A, B, C FROM T WHERE X = ':param' A = ? and B = ? and C = ':'", queryInfo.getCompileQuery());
	assertEquals(true, queryInfo.hasParameters());
	assertEquals(2, queryInfo.getParameterCount());
	
	parameter = queryInfo.getParameters().get(0);
	assertEquals("QWERTY", parameter.getName());
	parameter = queryInfo.getParameters().get(1);
	assertEquals("ASDFG", parameter.getName());
	
	System.out.println(queryInfo);
	
	// 2 Parameter
	queryInfo = analyzer.analyzeQuery("SELECT A, B, C FROM T WHERE X = ':param' A = :QWERTY and B = :ASDFG and C = ':' ");
	assertNotNull(queryInfo);
	assertEquals("SELECT A, B, C FROM T WHERE X = ':param' A = :QWERTY and B = :ASDFG and C = ':' " , queryInfo.getOriginalQuery());
	assertEquals("SELECT A, B, C FROM T WHERE X = ':param' A = ? and B = ? and C = ':'", queryInfo.getCompileQuery());
	assertEquals(true, queryInfo.hasParameters());
	assertEquals(2, queryInfo.getParameterCount());
	
	parameter = queryInfo.getParameters().get(0);
	assertEquals("QWERTY", parameter.getName());
	parameter = queryInfo.getParameters().get(1);
	assertEquals("ASDFG", parameter.getName());
	
	System.out.println(queryInfo);

	
	// 2 Parameter
	queryInfo = analyzer.analyzeQuery("SELECT A, B, C FROM T WHERE X = ':param' A = :QWERTY and B = :ASDFG and C = ':param'");
	assertNotNull(queryInfo);
	assertEquals("SELECT A, B, C FROM T WHERE X = ':param' A = :QWERTY and B = :ASDFG and C = ':param'" , queryInfo.getOriginalQuery());
	assertEquals("SELECT A, B, C FROM T WHERE X = ':param' A = ? and B = ? and C = ':param'", queryInfo.getCompileQuery());
	assertEquals(true, queryInfo.hasParameters());
	assertEquals(2, queryInfo.getParameterCount());
	
	parameter = queryInfo.getParameters().get(0);
	assertEquals("QWERTY", parameter.getName());
	parameter = queryInfo.getParameters().get(1);
	assertEquals("ASDFG", parameter.getName());
	
	System.out.println(queryInfo);

    }

}
