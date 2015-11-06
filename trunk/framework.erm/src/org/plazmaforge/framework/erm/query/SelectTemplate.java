package org.plazmaforge.framework.erm.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.erm.Configuration;
import org.plazmaforge.framework.erm.ErrorHandler;
import org.plazmaforge.framework.core.criteria.ContainerFilter;
import org.plazmaforge.framework.core.criteria.Criteria;
import org.plazmaforge.framework.core.criteria.Filter;
import org.plazmaforge.framework.core.criteria.Order;
import org.plazmaforge.framework.core.criteria.ValueFilter;
import org.plazmaforge.framework.core.criteria.ValuesFilter;
import org.plazmaforge.framework.erm.mapping.Attribute;
import org.plazmaforge.framework.erm.mapping.Column;
import org.plazmaforge.framework.erm.mapping.Discriminator;
import org.plazmaforge.framework.erm.mapping.Entity;
import org.plazmaforge.framework.erm.mapping.EntityHelper;
import org.plazmaforge.framework.erm.mapping.EntityType;
import org.plazmaforge.framework.erm.mapping.IComposite;
import org.plazmaforge.framework.erm.mapping.Entry;
import org.plazmaforge.framework.erm.mapping.Reference;
import org.plazmaforge.framework.erm.mapping.SubclassEntity;

public class SelectTemplate extends QueryTemplate {

    private List<JoinDef> joins;
    
    private List<OrderDef> orders;
    

    
    public SelectTemplate(Configuration configuration) {
	super(configuration);
    }

    public List<JoinDef> getJoins() {
	if (joins == null) {
	    joins = new ArrayList<JoinDef>();
	}
        return joins;
    }

    public JoinDef addJoin(JoinDef join) {
	getJoins().add(join);
	return join;
    }

    public JoinDef addJoin(TableDef table1, TableDef table2, Column[] columns1, Column[] columns2, JoinType joinType) {
	
	if (table1 == null) {
	    throw new IllegalArgumentException("Add Join error: Table1 must be not null");
	}
	if (table2 == null) {
	    throw new IllegalArgumentException("Add Join error: Table2 must be not null");
	}
	if (columns1 == null) {
	    throw new IllegalArgumentException("Add Join error: Columns1 must be not null");
	}
	if (columns1.length == 0) {
	    throw new IllegalArgumentException("Add Join error: Columns1 must be not empty");
	}
	if (columns2 == null) {
	    throw new IllegalArgumentException("Add Join error: Columns2 must be not null");
	}
	if (columns2.length == 0) {
	    throw new IllegalArgumentException("Add Join error: Columns2 must be not empty");
	}
	if (joinType == null) {
	    throw new IllegalArgumentException("Add Join error: Join Type must be not null");
	}
	
	JoinDef join = new JoinDef();
	join.setTable1(table1);
	join.setTable2(table2);
	join.setColumnName1(columns1[0].getName()); // STUB
	join.setColumnName2(columns2[0].getName()); // STUB
	join.setJoinType(joinType);
	getJoins().add(join);
	return join;
    }

    public List<OrderDef> getOrders() {
	if (orders == null) {
	    orders = new ArrayList<OrderDef>();
	}
        return orders;
    }

    public OrderDef addOrder(OrderDef order) {
	getOrders().add(order);
	return order;
    }

    
    public void setType(QueryType type) {
	if (type == null) {
	    throw new IllegalArgumentException("Type must be not null");
	}
	if (!QueryType.SELECT.equals(type) && !QueryType.LOAD.equals(type) && !QueryType.SELECT_FOR_DELETE.equals(type)) { 
	    throw new IllegalArgumentException("Type must be SELECT or LOAD or SELECT_FOR_DELETE");
	}
	super.setType(type);
    }
    
    public OrderDef addOrder(Attribute attribute, EntityContext context, boolean asc) {
	if (attribute == null) {
	    throw new IllegalArgumentException("Attribute must be not null");
	}
	if (attribute.getName() == null) {
	    throw new IllegalArgumentException("Attribute Name must be not null");
	}
	if (attribute.getColumnName() == null) {
	    throw new IllegalArgumentException("Attribute Column Name must be not null");
	}
	if (context == null) {
	    throw new IllegalArgumentException("Context must be not null");
	}
	
	Entity entity = attribute.getEntity(); // ATTRIBUTE ENTITY
	String entityIdentifier = entity == null ? null : entity.getIdentifier();
	
	TableDef table = context.findTableByEntity(entityIdentifier);
	
	OrderDef order = new OrderDef();
	order.setTable(table);
	order.setEntity(entity);
	order.setAttribute(attribute);
	order.setColumnName(attribute.getColumnName());
	order.setAsc(asc);
	
	getOrders().add(order);
	return order;
    }
    

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    // GENERAL POINT
    public void populateTemplate(Entity entity,  EntityConfig entityConfig) {
	populateTemplate(entity, entityConfig, null, null, null, null, null, null, null);
    }

    private void populateTemplate(Entity entity,
	    EntityConfig entityConfig, 
	    EntityContext parentContext,
	    String path, 
	    String attributeName, 
	    TableDef startTable,
	    Column[] startColumns,
	    String joinAttributeName,
	    Column[] joinColumns) {

	boolean mainMode = parentContext == null; // column == null;
	boolean referenceMode = !mainMode; // REFERENCE COLUMN

	if (referenceMode && (startTable == null || startColumns == null)) {
	    throw new RuntimeException("Invalid parameters");
	}

	JoinType joinType = mainMode ? JoinType.INNER : JoinType.LEFT;

	// Create hierarchy. For example: 0 - Figure, 1 - Ellipse, 2 - Circle
	Entity[] entities = entity.getHierarchy();
	int count = entities.length;
	TableDef[] tables = new TableDef[count];

	String entityAlias = null;

	Entity prevEntity = null;
	Entity currEntity = null;
	Entity joinEntity = null; // Only for joinAttributeName and referenceMode

	TableDef prevTable = null;
	TableDef currTable = null;

	Column[] prevColumns = null;
	Column[] currColumns = null;
	//Column[] joinColumns = null; // Only for joinAttributeName and referenceMode

	Attribute[] globalAttributes = entity.getGlobalAttributes();
	
	if (referenceMode) {
	    prevTable = startTable;
	    prevColumns = startColumns;
	    if (joinAttributeName != null) {
		Attribute joinAttribute = EntityHelper.getAttributeByName(globalAttributes, joinAttributeName); //EntityHelper.getGlobalAttributeByName(entities, joinAttributeName);
		if (joinAttribute == null) {
		    throw new RuntimeException("Join attribute '" + joinAttributeName + "' not found in entity=" + entity.getIdentifier());
		}
		joinColumns = joinAttribute.getDetailColumns();
		if (joinColumns == null || joinColumns.length == 0) {
		    throw new RuntimeException("Join columns not found in attribute='" + joinAttribute + "', entity=" + entity.getIdentifier());
		}
		joinEntity = joinAttribute.getEntity();
		if (joinEntity == null) {
		    throw new RuntimeException("Join Entity not found by attribute=" + joinAttribute + "', entity=" + entity.getIdentifier());
		}
	    } else if (joinColumns != null) {
		joinEntity = entities[0]; // TODO: STUB: Must find JoinEntity by joinColumns 
	    }
	}

	for (int i = 0; i < count; i++) {
	    currEntity = entities[i];
	    
	    boolean ownTable = currEntity.hasOwnTable();
	    if (!ownTable) {
		throw new RuntimeException("Invalid hierarchy");
	    }

	    
	    if (referenceMode && joinColumns != null && currEntity == joinEntity) {
		currColumns = joinColumns;
	    } else {
		currColumns = currEntity.getGlobalDetailKeyColumns(); // Own columns (non global) - Ops... we use GLOBAL KEY
	    }
	    
	    boolean first = i == 0;
	    boolean join = referenceMode || !first;
	    currTable = addTable(currEntity, join);
	    
	    DiscriminatorValue[] discriminatorValues = findSubclassDiscriminators(entities, i);
	    boolean discriminatorMode = discriminatorValues != null; 
	    if (discriminatorMode) {
		// Found subclasses
		int len = discriminatorValues.length;
		for (int k = 0; k < len; k++) {
		    tables[i + k] = currTable; // Shift tables for subclasses
		}
		i += len; 
	    } else {
		// Simple mode: current entity hasn't subclass by discriminator
		tables[i] = currTable;
	    }
	    JoinDef joinDef = null;
	    if (join) { // join
		joinDef = addJoin(prevTable, currTable, prevColumns, currColumns, joinType);
	    }
	    if (discriminatorMode) {
		ValueFilterDef[] filters = toFilters(discriminatorValues);
		for (ValueFilterDef filter : filters) {
		    
		    if (filter == null) {
			// Virtual filter: discriminator value is null
			continue;
		    }
		    filter.setTable(currTable);
		    // If we use JOIN then we add filter to JOIN
		    if (joinDef != null) {
			joinDef.addFilter(filter);
		    } else {
			addFilter(filter);
		    }
		}
	    }

	    prevEntity = currEntity;
	    prevTable = currTable;
	    prevColumns = currColumns;

	}

	EntityContext entityContext = new EntityContext(entity); //2012-07-16

	entityAlias = currTable.getTableAlias();
	if (mainMode) {
	    setEntityContext(entityContext);
	    setEntityTable(currTable);
	}

	//entityContext.setEntity(entity); //2012-07-16
	entityContext.setEntityConfig(entityConfig);

	if (parentContext != null) {
	    parentContext.addChild(entityContext);
	}

	// TODAY
	QueryInput input = getQueryInput();

	if (mainMode) {
	    
	    
	    // Set start alias to path (c1)
	    entityContext.setPath(entityAlias);
	    
	    if (input.getIncludeAttributes() != null){
		entityContext.setIncludeAttributes(input.getIncludeAttributes());
	    }
	    if (input.getExcludeAttributes() != null){
		entityContext.setExcludeAttributes(input.getExcludeAttributes());
	    }
	    
	} else {
	    // Add path (c1.contact.address)
	    entityContext.setPath(QueryTemplate.addAttributePath(path, attributeName));
	    entityContext.setAttributeName(attributeName);

	    List<String> includeList = new ArrayList<String>();
	    List<String> excludeList = new ArrayList<String>();
	    
	    if (joinAttributeName != null) {
		excludeList.add(joinAttributeName);
	    }
	    
	    transferAttributes(parentContext, attributeName, includeList, excludeList);
	    
	    setIncludeAttributes(entityContext, includeList);
	    setExcludeAttributes(entityContext, excludeList);
	    
	}

	Attribute[] attributes = globalAttributes;
	attributes = QueryTemplate.getAttributes(attributes, getType(), getConfigLoadMode());

	attributes = EntityHelper.getFilterAttributes(attributes, entityContext);

	entityContext.setTables(tables);
	entityContext.setEntities(entities);
	entityContext.setAttributes(attributes);

	processAttributes(entityContext, entityContext.getAttributes());
	
	if (mainMode) {
	    populateCriteria(entityContext);
	}
	
    }
    
    /**
     * Transfer include and exclude attributes from parent context to includeList and excludeList
     * @param parentContext
     * @param attributeName
     * @param includeList
     * @param excludeList
     */
    protected void transferAttributes(EntityContext parentContext, String attributeName, List<String> includeList, List<String> excludeList) {
	

	if (parentContext == null || attributeName == null) {
	    return;
	}
	String[] include = null;
	String[] exclude = null;
	String attributePrefix = attributeName + ".";
	int attributePrefixLen = attributePrefix.length();

	include = parentContext.getIncludeAttributes();
	if (include != null && include.length > 0) {
	    for (String a : include) {
		if (a.startsWith(attributePrefix)) {
		    includeList.add(a.substring(attributePrefixLen));
		}
	    }
	}

	exclude = parentContext.getExcludeAttributes();
	if (exclude != null && exclude.length > 0) {
	    for (String a : exclude) {
		if (a.startsWith(attributePrefix)) {
		    excludeList.add(a.substring(attributePrefixLen));
		}
	    }
	}

    }
    
    protected void transferAttributes(EntityContext parentContext, EntityContext entityContext, String attributeName) {
	if (parentContext == null || attributeName == null) {
	    return;
	}
	List<String> includeList = new ArrayList<String>();
	List<String> excludeList = new ArrayList<String>();
	transferAttributes(parentContext, attributeName, includeList, excludeList);
	setIncludeAttributes(entityContext, includeList);
	setExcludeAttributes(entityContext, excludeList);
    }
    
    protected void setIncludeAttributes(EntityContext entityContext, List<String> includeList) {
	if (entityContext == null || includeList == null || includeList.isEmpty()){
	    return;
	}
	entityContext.setIncludeAttributes(includeList.toArray(new String[0]));
    }
    
    protected void setExcludeAttributes(EntityContext entityContext, List<String> excludeList) {
	if (entityContext == null || excludeList == null || excludeList.isEmpty()){
	    return;
	}
	entityContext.setExcludeAttributes(excludeList.toArray(new String[0]));
    }
    
    
    protected ValueFilterDef[] toFilters(DiscriminatorValue[] discriminators) {
	if (discriminators == null || discriminators.length == 0) {
	    return null;
	}
	ValueFilterDef[] filters = new ValueFilterDef[discriminators.length];
	ValueFilterDef filter = null;
	DiscriminatorValue discriminator = null;
	for (int i = 0; i < discriminators.length; i++) {
	    discriminator = discriminators[i];
	    if (discriminator == null) {
		// Virtual filter: discriminator value is null
		continue;
	    }
	    filter = new ValueFilterDef();
	    filters[i] = filter;
	    filter.setMarker(ValueFilterDef.DISCRIMINATOR);
	    filter.setParameter(false); // NON PARAMETER
	    filter.setEntity(discriminator.getEntity());
	    filter.setColumnName(discriminator.getColumn().getName());
	    filter.setOperation("=");
	    filter.setValue(discriminator.getStringValue());
	}
	return filters;
    }
    
    protected DiscriminatorValue[] findSubclassDiscriminators(Entity[] entities, int position) {
	if (entities == null || position >= entities.length - 1) {
	    return null;
	}
	Entity startEntity = entities[position];
	Discriminator discriminator = startEntity.getDiscriminator();
	List<DiscriminatorValue> discriminators = new ArrayList<DiscriminatorValue>();
	Entity entity = null;
	for (int i = position + 1; i < entities.length; i++) {
	    entity = entities[i];
	    if (entity.hasOwnTable()) {
		break;
	    }
	    if (!EntityType.SubclassEntity.equals(entity.getEntityType())) {
		throw new RuntimeException("Only '" + EntityType.SubclassEntity.toString() + "' can be in hierarchy without table");
	    }
	    SubclassEntity subclassEntity = (SubclassEntity) entity;
	    String stringValue = subclassEntity.getDiscriminatorValue();
	    if (stringValue == null) {
		// Virtual discriminator: value is null
		discriminators.add(null);
		continue;
	    }
	    if (discriminator == null) {
		throw new RuntimeException("Discriminator not found for entity: '" + startEntity + "'");
	    }
	    Column column = discriminator.getColumn();
	    DiscriminatorValue discriminatorValue = new DiscriminatorValue();
	    discriminatorValue.setEntity(subclassEntity);
	    discriminatorValue.setColumn(column);
	    discriminatorValue.setStringValue(stringValue);
	    discriminators.add(discriminatorValue);
	}
	if (discriminators.isEmpty()) {
	    return null;
	}
	return discriminators.toArray(new DiscriminatorValue[0]); 
    }
    
 

    protected void populateCriteria(EntityContext entityContext) {
	QueryInput input = getQueryInput();
	if (input == null) {
	    return;
	}
	Criteria criteria = input.getCriteria();
	if (criteria == null) {
	    return;
	}
	
	
	List<Filter> filters = criteria.getFilters();
	int filterCount = filters.size();

	List<Order> orders = criteria.getOrders();
	int orderCount = orders.size();
	
	if (filterCount == 0 && orderCount == 0) {
	    return;
	}
	
	Attribute[] attributePath = null;
	Attribute attribute = null;
	String propertyName = null;
	Map<String, EntityContext> contextMap = EntityContext.getFlatContextTree(entityContext);

	boolean hasAliases = entityContext.getEntity().hasGlobalAttributeAliases();
		
	// Populate filter
	for (Filter filter : filters) {
	    FilterDef filterDef = createFilter(entityContext, contextMap, filter, hasAliases);
	    getFilters().add(filterDef);
	}
	
	// Populate order
	for (Order order : orders) {
	    
	    propertyName = order.getPropertyName();
	    if (hasAliases) {
		propertyName = EntityHelper.replaceByAlias(entityContext.getEntity(), propertyName, Criteria.ORDER);
	    }
	    attributePath = EntityHelper.getAttributePath(entityContext.getEntity(), propertyName);
	    if (attributePath == null) {
		ErrorHandler.handleEntityError(entityContext.getEntity(), "Order property not found: property=" + propertyName);
		//throw new RuntimeException("Order property not found: property=" + propertyName);
	    }
	    attribute = attributePath[attributePath.length - 1]; // last attribute in path
	    
	    Entity entity = attribute.getEntity(); // ATTRIBUTE ENTITY
	    String entityIdentifier = entity == null ? null : entity.getIdentifier();
		
	    TableDef table = entityContext.findTableInTree(contextMap, propertyName, entityIdentifier);
		
	    OrderDef orderDef = new OrderDef();
	    orderDef.setTable(table);
	    orderDef.setEntity(entity);
	    orderDef.setAttribute(attribute);
	    orderDef.setColumnName(attribute.getColumnName());
	    orderDef.setAsc(order.isAsc());
		
	    getOrders().add(orderDef);

	}
    }
 

    ////////////////////////////////////////////////////////////////////////////////////
    
    protected FilterDef createFilter(EntityContext entityContext, Map<String, EntityContext> contextMap, Filter filter, boolean hasAliases) {
	Attribute[] attributePath = null;
	Attribute attribute = null;
	String propertyName = null;

	if (filter instanceof ValueFilter) {
	    ValueFilter valueFilter = (ValueFilter) filter;
	    propertyName = valueFilter.getPropertyName();
	    if (hasAliases) {
		propertyName = EntityHelper.replaceByAlias(entityContext.getEntity(), propertyName, Criteria.FILTER);
	    }
	    
	    attributePath = EntityHelper.getAttributePath(entityContext.getEntity(), propertyName);
	    if (attributePath == null) {
		ErrorHandler.handleEntityError(entityContext.getEntity(), "Filter property not found: property=" + propertyName);
		throw new RuntimeException("Filter property not found: property=" + propertyName);
	    }
	    attribute = attributePath[attributePath.length - 1]; // last attribute in path
	    
	    Entity entity = attribute.getEntity(); // ATTRIBUTE ENTITY
	    String entityIdentifier = entity == null ? null : entity.getIdentifier();
		
	    TableDef table = entityContext.findTableInTree(contextMap, propertyName, entityIdentifier);
		
	    ValueFilterDef filterDef = new ValueFilterDef();
	    filterDef.setMarker(ValueFilterDef.CRITERIA);
	    filterDef.setTable(table);
	    filterDef.setEntity(entity);
	    filterDef.setAttribute(attribute);
	    filterDef.setColumnName(attribute.getColumnName());
	    filterDef.setOperation(valueFilter.getOperation());
	    
	    filterDef.setParameter(valueFilter.isParameter());
	    
	    // Add value(s)
	    if (filter instanceof ValuesFilter) {
		filterDef.setValues(((ValuesFilter) filter).getValues());
	    } else {
		filterDef.setValue(valueFilter.getValue());
	    }
	    filterDef.setIgnoreCase(valueFilter.isIgnoreCase());
	    
	    return filterDef;
	} else if (filter instanceof ContainerFilter) {
	    ContainerFilter containerFilter = (ContainerFilter) filter;
	    ContainerFilterDef containerFilterDef = new ContainerFilterDef(containerFilter.getInnerConnector());
	    containerFilterDef.setMarker(FilterDef.CRITERIA);
	    for (Filter c: containerFilter.getFilters()) { 
		FilterDef cd = createFilter(entityContext, contextMap, c, hasAliases);
		containerFilterDef.addFilter(cd);
	    }
	    return containerFilterDef;
	}
	//TODO
	throw new RuntimeException("Unsupport filter:" + filter);
    }
    
    ////////////////////////////////////////////////////////////////////////////////////


    // BASIC
    protected void processBasicAttribute(EntityContext context, Attribute attribute) {
	ColumnDef column = addColumn(attribute, context);
	column.setLoad(true);
	column.setPath(QueryTemplate.addAttributePath(context.getPath(), attribute.getName()));
	context.initStartColumn(column);
    }

    
    // COMPOSITE
    protected void processCompositeAttribute(EntityContext context, Attribute attribute) {
	ColumnDef column = null;
	IComposite composite = (IComposite) attribute;
	if (!composite.hasAttributes()) {
	    // No attributes
	    return;
	}
	Attribute[] attributes = QueryTemplate.getAttributes(composite.getAttributes(), getType(), context.getEntityConfig().getLoadMode());

	if (composite.isPseudoComposite()) {

	    // TODAY
	    attributes = EntityHelper.getFilterAttributes(attributes, context);
	    
	    processAttributes(context, attributes);

	} else {

	    // NODE COLUMN (VIRTUAL)
	    String attributeName = attribute.getName();
	    
	    column = addColumn(attribute, context, true); // Add only for store value (composite reference)
	    column.setPath(QueryTemplate.addAttributePath(context.getPath(), attributeName));
	    context.initStartColumn(column);

	    String nodePath = QueryTemplate.addAttributePath(context.getPath(), attributeName); // Composite path

	    // NEW CONTEXT FOR COMPOSITE
	    EntityContext nodeContext = new EntityContext(context.getEntity()); //2012-07-16
	    context.addChild(nodeContext);

	    // TODAY
	    transferAttributes(context, nodeContext, attributeName);
	    attributes = EntityHelper.getFilterAttributes(attributes, context);
	    
	    nodeContext.setAttributeName(attribute.getName()); // NAME
	    //nodeContext.setEntity(context.getEntity()); //2012-07-16
	    nodeContext.setPath(nodePath);
	    nodeContext.setStartColumn(context.getStartColumn()); // / WHY ???
	    nodeContext.setTables(context.getTables());
	    nodeContext.setEntities(context.getEntities());
	    nodeContext.setAttributes(attributes);
	    nodeContext.setEntityConfig(context.cloneEntityConfig());
	    
	    nodeContext.getEntityConfig().configure(attribute);

	    processAttributes(nodeContext, attributes);

	}
    }

    
    // REFERENCE
    protected void processReferenceAttribute(EntityContext context, Attribute attribute) {

	Reference reference = (Reference) attribute;

	Column[] keyColumns = context.getEntity().getGlobalDetailKeyColumns();

	// NODE COLUMN
	ColumnDef column = addColumn(attribute, context); // Add only for store value (reference ID)
	column.setPath(QueryTemplate.addAttributePath(context.getPath(), attribute.getName()));
	context.initStartColumn(column);

	Entity joinEntity = reference.getJoinEntity();
	if (joinEntity == null) {
	    ErrorHandler.handleJoinEntityNotMapped(attribute, reference.getJoinEntityIdentifier());
	}

	checkEntity(joinEntity);
	if (attribute.isLazyload()) {
	    // No load: Lazy
	    return;
	}
	column.setLoad(true);

	TableDef startTable = column.getTable();
	Column[] startColumns = attribute.getDetailColumns();
	if (startColumns == null || startColumns.length == 0) {
	    // If reference attribute has not columns we use primary key of entity
	    startColumns = keyColumns;
	}

	// String joinType = attribute.isRequired() ? QueryTemplate.INNER_JOIN : QueryTemplate.LEFT_JOIN;
	EntityConfig joinEntityConfig = context.cloneEntityConfig();
	joinEntityConfig.configure(reference);
	
	int level = context.getLevel() + 1;
	boolean isLazyDepth = !joinEntityConfig.isLoadJoinDepth(level);
	joinEntityConfig.setLazyReference(isLazyDepth);
	
	populateTemplate(joinEntity,
		joinEntityConfig,
		context,
		context.getPath(),
		attribute.getName(),
		startTable,
		startColumns,
		null,
		null);
    }

    // ENTRY
    protected void processEntryAttribute(EntityContext context, Attribute attribute) {
	
	Entry entry = (Entry) attribute;

	Column[] keyColumns = context.getEntity().getGlobalDetailKeyColumns();

	// NODE COLUMN
	ColumnDef column = addColumn(attribute, context, true); // Add only for store value (reference ID)
	column.setPath(QueryTemplate.addAttributePath(context.getPath(), attribute.getName()));
	context.initStartColumn(column);

	Entity joinEntity = entry.getJoinEntity();
	if (joinEntity == null) {
	    ErrorHandler.handleJoinEntityNotMapped(attribute, entry.getJoinEntityIdentifier());
	}

	checkEntity(joinEntity);
	if (attribute.isLazyload()) {
	    // No load: Lazy
	    return;
	}
	column.setLoad(true);

	TableDef startTable = column.getTable();
	Column[] startColumns = keyColumns;
	String joinAttributeName =  entry.getJoinAttributeName();
	Column[] joinColumns = entry.getJoinColumns();

	// String joinType = attribute.isRequired() ? QueryTemplate.INNER_JOIN : QueryTemplate.LEFT_JOIN;
	EntityConfig joinEntityConfig = context.cloneEntityConfig();
	joinEntityConfig.configure(entry);
	
	int level = context.getLevel() + 1;
	boolean isLazyDepth = !joinEntityConfig.isLoadJoinDepth(level);
	joinEntityConfig.setLazyReference(isLazyDepth);
	/* !!! LOL !!! */ joinEntityConfig.setLazyCollection(false);
	
	populateTemplate(joinEntity,
		joinEntityConfig,
		context, 
		context.getPath(),
		attribute.getName(), 
		startTable,
		startColumns,
		joinAttributeName,
		joinColumns);
    }
    
    // COLLECTION
    protected void processCollectionAttribute(EntityContext context, Attribute attribute) {
	if (isNeedLoad(attribute, context.getEntityConfig())) {
	    context.addProcessingArttribute(attribute);
	}
    }

    

}
