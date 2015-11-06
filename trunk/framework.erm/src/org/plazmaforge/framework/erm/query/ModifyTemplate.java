package org.plazmaforge.framework.erm.query;

import org.plazmaforge.framework.erm.Configuration;
import org.plazmaforge.framework.erm.mapping.Attribute;
import org.plazmaforge.framework.erm.mapping.Entity;
import org.plazmaforge.framework.erm.mapping.IComposite;
import org.plazmaforge.framework.erm.mapping.IReference;
import org.plazmaforge.framework.erm.mapping.Key;

public abstract class ModifyTemplate extends QueryTemplate {


    public ModifyTemplate(Configuration configuration) {
	super(configuration);
    }


    // BASIC
    protected void processBasicAttribute(EntityContext context, Attribute attribute) {
	ColumnDef column = addColumn(attribute, context);
	column.setPath(QueryTemplate.addAttributePath(context.getPath(), attribute.getName()));
    }
    
    
    // COMPOSITE
    protected void processCompositeAttribute(EntityContext context, Attribute attribute) {
	IComposite composite = (IComposite) attribute;
	    if (!composite.hasAttributes()){
		return;
	    }
	    attribute.getEntity();
	    Attribute[] attributes = QueryTemplate.getAttributes(composite.getAttributes(), getType(), getConfigLoadMode());
	    if (composite.isPseudoComposite()) {
		processAttributes(context, attributes);
	    } else {
		
		String nodePath = QueryTemplate.addAttributePath(context.getPath(), attribute.getName()); // Composite path
		EntityContext nodeContext = new EntityContext(context.getEntity()); // 2012-07-16
		context.addChild(nodeContext);
		
		nodeContext.setAttributeName(attribute.getName()); // NAME
		//nodeContext.setEntity(context.getEntity()); // 2012-07-16
		nodeContext.setPath(nodePath);
		nodeContext.setStartColumn(context.getStartColumn());
		nodeContext.setTables(context.getTables());
		nodeContext.setEntities(context.getEntities());
		nodeContext.setAttributes(attributes);
		nodeContext.setEntityConfig(context.cloneEntityConfig());
		
		processAttributes(nodeContext, attributes);
		    
	    }
    }
    
    // REFERENCE
    protected void processReferenceAttribute(EntityContext context, Attribute attribute) {
	IReference reference = (IReference) attribute;

	String nodePath = QueryTemplate.addAttributePath(context.getPath(), attribute.getName()); // Reference path

	Entity joinEntity = reference.getJoinEntity();
	if (joinEntity == null) {
	    // TODO
	    return;
	}
	Key joinKey = joinEntity.getGlobalKey();
	if (joinKey == null) {
	    // TODO
	    return;
	}

	// TODO: Must create more columns for more values for Reference ID
	ColumnDef column = addColumn(joinKey, context);
	nodePath = QueryTemplate.addAttributePath(context.getPath(), attribute.getName()); // Reference path
	nodePath = QueryTemplate.addAttributePath(nodePath, joinKey.getName()); // TODO: Multi column
	column.setPath(nodePath);
	column.setColumnName(attribute.getColumnName()); // TODO: Multi column

    }
    
    // ENTRY
    protected void processEntryAttribute(EntityContext context, Attribute attribute) {
	// Add PRE PROCESSING for ENTRY (CASCADE)
	context.addProcessingArttribute(attribute);
    }


    // COLLECTION
    protected void processCollectionAttribute(EntityContext context, Attribute attribute) {
	// Add POST PROCESSING for COLLECTION (CASCADE)
	context.addProcessingArttribute(attribute);
    }
}
