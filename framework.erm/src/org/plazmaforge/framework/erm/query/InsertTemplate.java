package org.plazmaforge.framework.erm.query;

import org.plazmaforge.framework.erm.Configuration;

public class InsertTemplate extends ModifyTemplate {

    
    public InsertTemplate(Configuration configuration) {
	super(configuration);
    }

    public QueryType getType() {
	return QueryType.INSERT; 
    }
    
    public void setType(QueryType type) {
	throw new UnsupportedOperationException("Type is INSERT always");
    }

}
