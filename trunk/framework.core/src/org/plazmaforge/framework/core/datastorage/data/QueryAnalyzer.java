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

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.plazmaforge.framework.util.StringUtils;

/**
 * @author ohapon
 *
 */
public class QueryAnalyzer {

    public QueryInfo analyzeQuery(String query) {
	QueryInfo queryInfo = new QueryInfo();
	if (query == null) {
	    return queryInfo;
	}
	queryInfo.setOriginalQuery(query);
	query = StringUtils.normalizeString(query);
	if (query == null) {
	    return queryInfo;
	}
	prepareQuery(queryInfo, query);
	return queryInfo;
    }
    
    protected void prepareQuery(QueryInfo queryInfo, String query) {
	
	// TODO: simple analyzer (use ANTLR)
	
	StringBuilder builder = new StringBuilder();
	char[] array = query.toCharArray();
	boolean b1 = false;
	boolean b2 = false;
	boolean marker = false;
	int index = -1;
	int lastIndex = 0; // last flush index
	int markIndex = -1;
	List<String> parameters = new ArrayList<String>();
	for (char ch : array) {
	    index++;
	    switch (ch) {
	    case '\'': {
		if (!b1 && !b2 && marker) {
		    
		    // end mark
		    flushString(array, builder, lastIndex, markIndex - 1);
		    builder.append("?");
		    int parameterIndex = markIndex + 1;
		    String parameter = new String(array, parameterIndex, index - parameterIndex);
		    parameters.add(parameter);
		    
		    marker = false;
		    lastIndex = index;
		    markIndex = -1;
		    
		} else {
		    marker = false;
		    b1 = !b1;
		}
		break;
	    }
	    case '\"': {
		if (!b1 && !b2 && marker) {
		    // end mark
		    flushString(array, builder, lastIndex, markIndex - 1);
		    builder.append("?");
		    int parameterIndex = markIndex + 1;
		    String parameter = new String(array, parameterIndex, index - parameterIndex);
		    parameters.add(parameter);
		    
		    marker = false;
		    lastIndex = index;
		    markIndex = -1;
		} else {
		    marker = false;
		    b2 = !b2;
		}
		break;
	    }
	    case ':': {
		if (!b1 && !b2) {
		    if (marker) {
			    // end mark
			    flushString(array, builder, lastIndex, markIndex - 1);
			    builder.append("?");
			    int parameterIndex = markIndex + 1;
			    String parameter = new String(array, parameterIndex, index - parameterIndex);
			    parameters.add(parameter);
			    
			    // start mark
			    //marker = false;
			    lastIndex = index;
			    //markIndex = -1;

			    // start mark
			    marker = true;
			    markIndex = index;


		    } else {
			// start mark
			marker = true;
			markIndex = index;
			
		    }
		}
		break;
	    }
	    case '(':
	    case ')':
	    case '.':
	    case ',':
	    case ';':
	    case ' ': {
		if (!b1 && !b2 && marker) {
		    // end mark
		    flushString(array, builder, lastIndex, markIndex - 1);
		    builder.append("?");
		    int parameterIndex = markIndex + 1;
		    String parameter = new String(array, parameterIndex, index - parameterIndex);
		    parameters.add(parameter);
		    
		    marker = false;
		    lastIndex = index;
		    markIndex = -1;
		}
		break;
	    }

	    default:
		break;
	    }
	}
	
	if (lastIndex >= 0 ) {
	    
	    if (marker) {
		
		// Move to last position
		index++;
		
		 // end mark
		    flushString(array, builder, lastIndex, markIndex - 1);
		    builder.append("?");
		    int parameterIndex = markIndex + 1;
		    String parameter = new String(array, parameterIndex, index - parameterIndex);
		    parameters.add(parameter);
	    } else {
		builder.append(array, lastIndex, array.length - lastIndex);
	    }
	}
	
	List<QueryParameter> queryParameters = new ArrayList<QueryParameter>();
	Set<QueryParameter> queryUniqueParameters = new LinkedHashSet<QueryParameter>();
	Set<String> uniqueParameters = new LinkedHashSet<String>();
	
	// All query parameters
	for (String name : parameters) {
	    QueryParameter parameter = new QueryParameter();
	    parameter.setName(name);
	    queryParameters.add(parameter);
	    uniqueParameters.add(name); // unique
	}
	queryInfo.setParameters(queryParameters);

	// Unique query parameters
	for (String name : uniqueParameters) {
	    QueryParameter parameter = new QueryParameter();
	    parameter.setName(name);
	    queryUniqueParameters.add(parameter);
	}
	queryInfo.setUniqueParameters(queryUniqueParameters);

	queryInfo.setCompileQuery(builder.toString());
    }
    
    protected void flushString(char[] array, StringBuilder builder, int from, int to) {
	builder.append(array, from, to - from + 1);
    }
    
  
}
