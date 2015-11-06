package org.plazmaforge.framework.erm.query;

import org.plazmaforge.framework.erm.Configuration;
import org.plazmaforge.framework.erm.mapping.Attribute;

public class DeleteTemplate extends ModifyTemplate {

    
    public DeleteTemplate(Configuration configuration) {
	super(configuration);
    }

    public QueryType getType() {
	return QueryType.DELETE; 
    }
    
    public void setType(QueryType type) {
	throw new UnsupportedOperationException("Type is DELETE always");
    }

    // BASIC
    protected void processBasicAttribute(EntityContext context, Attribute attribute) {
	// DON'T PROCESSING
    }
    
    // COMPOSITE
    protected void processCompositeAttribute(EntityContext context, Attribute attribute) {
	// DON'T PROCESSING
    }
    
    // REFERENCE
    protected void processReferenceAttribute(EntityContext context, Attribute attribute) {
	// DON'T PROCESSING
    }
    
    // ENTRY
    protected void processEntryAttribute(EntityContext context, Attribute attribute) {
	// Add PRE PROCESSING for ENTRY (CASCADE)
	context.addProcessingArttribute(attribute);
    }

    // COLLECTION
    protected void processCollectionAttribute(EntityContext context, Attribute attribute) {
	// Add PRE PROCESSING for COLLECTION (CASCADE)
	context.addProcessingArttribute(attribute);
    }
}
