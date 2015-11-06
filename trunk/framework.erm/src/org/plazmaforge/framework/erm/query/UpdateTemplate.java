package org.plazmaforge.framework.erm.query;

import org.plazmaforge.framework.erm.Configuration;

public class UpdateTemplate extends ModifyTemplate {

    
    public UpdateTemplate(Configuration configuration) {
	super(configuration);
    }

    public QueryType getType() {
	return QueryType.UPDATE; 
    }
    
    public void setType(QueryType type) {
	throw new UnsupportedOperationException("Type is UPDATE always");
    }
    
}
