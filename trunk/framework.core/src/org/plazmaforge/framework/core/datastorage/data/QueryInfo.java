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

import java.util.List;
import java.util.Set;

/**
 * @author ohapon
 *
 */
public class QueryInfo {

    /**
     * Original (input) query 
     */
    private String originalQuery;
    
    /**
     * Query after compilation
     */
    private String compileQuery;
    
    /**
     * Unique query parameters
     */
    private Set<QueryParameter> uniqueParameters;
    
    /**
     * All query parameters
     */
    private List<QueryParameter> parameters;

    
    public String getOriginalQuery() {
        return originalQuery;
    }

    public void setOriginalQuery(String originalQuery) {
        this.originalQuery = originalQuery;
    }

    public String getCompileQuery() {
        return compileQuery;
    }

    public void setCompileQuery(String compileQuery) {
        this.compileQuery = compileQuery;
    }

    public Set<QueryParameter> getUniqueParameters() {
        return uniqueParameters;
    }

    public void setUniqueParameters(Set<QueryParameter> uniqueParameters) {
        this.uniqueParameters = uniqueParameters;
    }

    public int getUniqueParameterCount() {
	return uniqueParameters == null ? 0 : uniqueParameters.size();
    }
    
    public List<QueryParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<QueryParameter> parameters) {
        this.parameters = parameters;
    }
    
    public boolean hasParameters() {
	return parameters != null && !parameters.isEmpty();
    }

    public int getParameterCount() {
	return parameters == null ? 0 : parameters.size();
    }

    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("QueryInfo=["
		+ "\n  originalQuery='" + originalQuery + "'"		
		+ "\n, compileQuery='" + compileQuery + "'"
		+ "\n, parameters=");
	if (parameters != null) {
	    int i = 0;
	    for (QueryParameter parameter : parameters) {
		builder.append("\n\t" + (i + ": ") + parameter.getName());
		i++;
	    }
	}
		
	builder.append("\n]");
		
	return builder.toString();
    }
    
}
