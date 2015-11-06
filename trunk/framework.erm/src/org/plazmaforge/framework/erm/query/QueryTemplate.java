package org.plazmaforge.framework.erm.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.erm.Configuration;
import org.plazmaforge.framework.erm.ErrorHandler;
import org.plazmaforge.framework.erm.LoadMode;
import org.plazmaforge.framework.erm.mapping.Attribute;
import org.plazmaforge.framework.erm.mapping.Entity;
import org.plazmaforge.framework.erm.mapping.ICollection;
import org.plazmaforge.framework.erm.mapping.IComposite;
import org.plazmaforge.framework.erm.mapping.IEntry;
import org.plazmaforge.framework.erm.mapping.IReference;
import org.plazmaforge.framework.erm.util.QueryUtils;


/**
 * The Query Template
 * 
 * @author ohapon
 *
 */
public abstract class QueryTemplate implements Serializable {

    
    public static final String ENTITY_SEPARATOR = ":";
    
    public static final String ATTRIBUTE_SEPARATOR = ".";

    public static final String TABLE_ALIAS_PREFIX = "c";
    
    
    // LOAD/SELECT/INSERT/UPDATE columns
    private List<ColumnDef> columns;
    
    private List<TableDef> tables;
    
    private TableDef entityTable;
    
    private List<FilterDef> filters;
    

    private String text;
    
    // LOAD/SELECT/INSERT/UPDATE/DELETE
    private QueryType type;
    
    private QueryInput queryInput;
    
    private EntityContext entityContext;
    
    private Configuration configuration;
    
    
    
    public QueryTemplate(Configuration configuration) {
	if (configuration == null) {
	    throw new RuntimeException("Configuration must be not null");
	}
	this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public QueryInput getQueryInput() {
        return queryInput;
    }

    public void setQueryInput(QueryInput queryInput) {
        this.queryInput = queryInput;
    }

    public List<ColumnDef> getColumns() {
	if (columns == null) {
	    columns = new ArrayList<ColumnDef>();
	}
        return columns;
    }

    public ColumnDef addColumn(ColumnDef column) {
	getColumns().add(column);
	return column;
    }

    public boolean hasColumns( ){
	return columns != null && !columns.isEmpty();
    }
    
    public List<ColumnDef> getQueryColumns() {
	List<ColumnDef> queryColumns = new ArrayList<ColumnDef>();
	for (ColumnDef column: getColumns()) {
	    if (column.isVirtual() ) {
		continue;
	    }
	    queryColumns.add(column);
	}
	return queryColumns;
    }

    
    public List<TableDef> getTables() {
	if (tables == null) {
	    tables = new ArrayList<TableDef>();
	}
        return tables;
    }

    public TableDef addTable(TableDef table) {
	getTables().add(table);
	return table;
    }

    public boolean hasTables() {
	return tables != null && !tables.isEmpty();
    }

    /**
     * Return first Table Name  
     * @return
     */
    public String getTableName() {
	if (!hasTables()){
	    return null;
	}
	return tables.get(0).getTableName();
    }
    


    public List<FilterDef> getFilters() {
	return doGetFilters();
    }

    protected List<FilterDef> doGetFilters() {
	if (filters == null) {
	    filters = new ArrayList<FilterDef>();
	}
	return filters;
    }
    
    public FilterDef addFilter(FilterDef filter) {
	doGetFilters().add(filter);
	return filter;
    }
    
    public FilterDef addFilter(Attribute attribute, EntityContext context) {
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
	
	ValueFilterDef filter = new ValueFilterDef();
	filter.setTable(table);
	filter.setEntity(entity);
	filter.setAttribute(attribute);
	filter.setType(attribute.getType());
	filter.setColumnName(attribute.getColumnName());
	
	doGetFilters().add(filter);
	return filter;
    }

    public boolean hasFilters() {
	return filters != null && !filters.isEmpty();
    }       
       
    

    //////////////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Add table by entity
     * @param entity
     * @return
     */
    public TableDef addTable(Entity entity) {
	return addTable(entity, false);
    }
    
    /**
     * Add table by entity with join
     * @param entity
     * @param join
     * @return
     */
    public TableDef addTable(Entity entity, boolean join) {
	if (entity == null) {
	    ErrorHandler.handleEntityError(entity, "Entity must be not null");
	}
	if (entity.getName() == null) {
	    ErrorHandler.handleEntityError(entity, "Entity Name must be not null");
	}
	if (entity.getTableName() == null) {
	    ErrorHandler.handleEntityError(entity, "Entity Table must be not null");
	}
	TableDef table = new TableDef();
	table.setEntity(entity);
	table.setTableAlias(generateNewTableAlias());
	table.setJoin(join);
	getTables().add(table);
	return table; 
    }
    
    /**
     * Add column by attribute
     * @param attribute
     * @param context
     * @return
     */
    public ColumnDef addColumn(Attribute attribute, EntityContext context) {
	return addColumn(attribute, context, false);
    }
    
    /**
     * Add column by attribute
     * @param attribute
     * @param context
     * @return
     */
    public ColumnDef addColumn(Attribute attribute, EntityContext context, boolean virtual) {
	if (attribute == null) {
	    throw new IllegalArgumentException("Attribute must be not null");
	}
	if (attribute.getName() == null) {
	    throw new IllegalArgumentException("Attribute Name must be not null");
	}
	if (attribute.getColumnName() == null) {
	    if (!virtual){
		throw new IllegalArgumentException("Attribute Column Name must be not null");
	    }
	}
	if (context == null) {
	    throw new IllegalArgumentException("Context must be not null");
	}
	
	Entity entity = attribute.getTableEntity(); // ATTRIBUTE ENTITY
	String entityIdentifier = entity == null ? null : entity.getIdentifier();
	
	TableDef table = context.findTableByEntity(entityIdentifier);
	
	ColumnDef column = new ColumnDef();
	column.setContext(context);
	column.setVirtual(virtual);
	
	column.setTable(table);
	column.setEntity(entity);
	column.setAttribute(attribute);
	column.setType(attribute.getType());
	column.setColumnName(attribute.getColumnName());
	
	getColumns().add(column);
	return column;
    }

    
    
    //////////////////////////////////////////////////////////////////////////////////////////
    
    public void prepare() {
	prepareColumns();
    }

    /**
     * Prepare columns
     * Calculate entity size for start column
     */
    private void prepareColumns() {
	List<ColumnDef> columns = getColumns();
	ColumnDef column = null;
	int count = columns.size();
	int index = -1; 
	for (int i = 0; i < count; i++) {
	    column = columns.get(i);
	    if (!column.isVirtual()) {
		index++;
		column.setIndex(index); // Real index in data row Object[] 
	    }
	    if (!column.isStart()) {
		continue;
	    }
	    int entitySize = 1; // 1 - because entity has start column 
	    String startPath = column.getPath();
	    if (startPath == null) {
		column.setBlockSize(entitySize);
		continue;
	    }
	    int k = i + 1; // next column
	    ColumnDef entityColumn = null;
	    String folder = getAttributeFolder(startPath);
	    String path = null;
	    while (k < count) {
		entityColumn = columns.get(k);
		path = entityColumn.getPath();
		if (!isContainPath(folder, path)) {
		    break;
		}
		entitySize++;
		k++;
	    }
	    column.setBlockSize(entitySize);
	}
    }
    
    
    private String generateNewTableAlias() {
	int count = tables == null ? 0 : tables.size();
	count++;
	return TABLE_ALIAS_PREFIX + count;
    }
    
    public String getQueryTableString(TableDef table) {
	if (table == null){
	    return null;
	}
	String tableAlias = table.getTableAlias();
	return getQueryTableString(table.getTableName(), tableAlias);
    }

    public String getQueryTableString(String tableName, String tableAlias) {
	if (tableName == null){
	    return null;
	}
	return (tableAlias == null) ? tableName : (tableName + " " + tableAlias);
    }

    public String getQueryColumnString(ColumnDef column) {
	if (column == null){
	    return null;
	}
	if (column.isVirtual()) {
	    return "NULL";
	}
		
	String columnName = column.getColumnName();
	String tableAlias = column.getTableAlias();
	//return getQueryColumnString(columnName, tableAlias) + " as " + tableAlias + "_" + columnName;
	return getQueryColumnString(columnName, tableAlias) + " as " + generateColumnAlias(tableAlias);
    }

    private Map<String, Integer> genMap = new HashMap<String, Integer>();  
    
    public void clearColumnAlias() {
	genMap.clear();
    }
    
    private String generateColumnAlias(String key) {
	Integer index = genMap.get(key);
	if (index == null) {
	    index = 0;
	}
	index++;
	genMap.put(key, index);
	return key + "_" + index;
    }

    public String getQueryFilterString(FilterDef filter) {
	if (filter == null){
	    return null;
	}
	return filter.toSqlString();
    }

    public String getQueryFilterString(String columnName, ValueFilterDef filter) {
	return filter.toSqlString(columnName);
    }

   public String getQueryOrderString(OrderDef order) {
	if (order == null){
	    return null;
	}
	String columnName = order.getColumnName();
	String tableName = order.getTableName();
	if (tableName == null) {
	    throw new RuntimeException("Order string error. Table name must be not null");
	}
	String tableAlias = order.getTableAlias();
	return getQueryColumnString(columnName, tableAlias) + (order.isAsc() ? "" : " DESC");
    }

    public String getQueryColumnString(String columnName, String tableAlias) {
	return QueryUtils.getQueryColumnString(columnName, tableAlias);
    }

    
    public String getQueryJoinString(JoinDef join) {
	if (join == null){
	    return null;
	}
	String jointTypeString = JoinType.INNER.equals(join.getJoinType()) ? "INNER JOIN" : "LEFT JOIN";
	
	String table1 = join.getTableName1();
	String table2 = join.getTableName2();

	String column1 = join.getColumnName1();
	String column2 = join.getColumnName2();

	String alias1 = join.getTable1().getTableAlias();
	String alias2 = join.getTable2().getTableAlias();

	StringBuffer buf = new StringBuffer();
	buf.append(jointTypeString);
	buf.append(" ");
	buf.append(getQueryTableString(table2, alias2));
	buf.append(" ON ");
	buf.append(getQueryColumnString(column1, alias1));
	buf.append(" = ");
	buf.append(getQueryColumnString(column2, alias2));
	
	// JOIN FILTERS
	if (join.hasFilters()) {
	    List<ValueFilterDef> filters = join.getFilters();
	    for (ValueFilterDef filter : filters) {
		buf.append(" AND "); // Always AND because we use LEFT/INNER JOIN before
		buf.append(getQueryFilterString(filter));
	    }
	}
	return buf.toString();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public QueryType getType() {
        return type;
    }

    public void setType(QueryType type) {
        this.type = type;
    }


    public boolean isLoadQuery() {
	return QueryType.LOAD.equals(type);
    }

    public TableDef getEntityTable() {
        return entityTable;
    }

    public void setEntityTable(TableDef entityTable) {
        this.entityTable = entityTable;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Utility methods
    //
    /////////////////////////////////////////////////////////////////////////////////////////////
    
    public static String addEntityPath(String path, String element) {
	return addPath(path, element, QueryTemplate.ENTITY_SEPARATOR);
    }

    public static String addAttributePath(String path, String element) {
	return addPath(path, element, QueryTemplate.ATTRIBUTE_SEPARATOR);
    }
    
    public static String addPath(String path, String element, String separator) {
	return ((path == null || path.length() == 0) ? "" : path + separator) + element;
    }

    public static String getAttributeFolder(String path) {
	if (path == null) {
	    return null;
	}
	int index = path.lastIndexOf(QueryTemplate.ATTRIBUTE_SEPARATOR);
	if (index < 0) {
	    return path;
	}
	return path.substring(0, index + 1);
    }
    
    public static boolean isContainPath(String folder, String path) {
	if (folder == null) {
	    return true;
	}
	if (path == null) {
	    return false;
	}
	return path.startsWith(folder);
    }


    ///////////////////////////////////////////////////////////////////////////////////////////
    
//    private FetchMode getConfigFetchMode() {
//	Configuration configuration = getConfiguration();
//	return configuration == null ? Configuration.DEFAULT_FETCH_MODE : configuration.getFetchMode(); 
//    }
    
    
    protected LoadMode getConfigLoadMode() {
	Configuration configuration = getConfiguration();
	return configuration == null ? Configuration.DEFAULT_LOAD_MODE : configuration.getLoadMode();
    }
    
    public static LoadMode getLoadMode(Entity entity) {
	if (entity == null) {
	    return Configuration.DEFAULT_LOAD_MODE;
	}
	Configuration configuration = entity.getConfiguration();
	return configuration == null ? Configuration.DEFAULT_LOAD_MODE : configuration.getLoadMode();
    }
    
    public static Attribute[] getAttributes(Entity entity, QueryType queryType) {
	LoadMode loadMode = getLoadMode(entity);
	return getAttributes(entity, queryType, loadMode);
    }
	
	
    public static Attribute[] getAttributes(Entity entity, QueryType queryType, LoadMode loadMode) {
	if (entity == null) {
	    return null;
	}
	
	//2012-07-16
	//if (!entity.hasAttributes()) {
	//    throw new IllegalArgumentException("Attributes of Entity must be not empty");
	//}
	
	Attribute[] attributes = entity.getAttributes();
	return getAttributes(attributes, queryType, loadMode);	
    }
   
    
    /*
    public static Attribute[] getAttributes(Attribute[] attributes, QueryType queryType) {
	return getAttributes(attributes, queryType, getConfigLoadMode());
    }
    */
	
   

    public static Attribute[] getAttributes(Attribute[] attributes, QueryType queryType, LoadMode loadMode) {
	boolean forceLoad = LoadMode.FORCE.equals(loadMode);
	List<Attribute> result = new ArrayList<Attribute>();
	for (Attribute a: attributes) {

	    if (a.getName() == null && a.isDiscriminatorType()) {
		// Ignore discriminator without name
		continue;
	    }

	    if (a.getColumnName() == null) {
		if (!a.isAnonym() && !a.isCompositeType() && !a.isCollectionType() && !a.isEntryType()) {
		    throw new IllegalArgumentException("Name of column must be not null");
		}
	    }
	    
	    if (QueryType.LOAD.equals(queryType)) { // Load entity by ID
		if (forceLoad || !a.isLazyload() || a.isReferenceType()) {
		    result.add(a);
		}
	    } else if (QueryType.SELECT.equals(queryType)) { // Select entities
		if (forceLoad || !a.isLazyload() || a.isReferenceType()) {
		    result.add(a);
		}
	    } else if (QueryType.INSERT.equals(queryType)) { // Insert entity
		// We can't insert DB auto key
		if (a.isInsertEditable()) {
		    result.add(a);
		}
	    } else if (QueryType.UPDATE.equals(queryType)) { // Update entity
		if (a.isUpdateEditable()) {
		    result.add(a);
		}
	    } else if (QueryType.DELETE.equals(queryType)) { // Delete entity
		if (a.isReferenceType() || a.isEntryType() || a.isCollectionType()) { // Only for analyze
		    result.add(a);
		}
	    } else if (QueryType.SELECT_FOR_DELETE.equals(queryType)) { // Delete entity
		if (a.isKey() || a.isReferenceType() || a.isCollectionType()) { // Only for analyze
		    result.add(a);
		}
	    }
	}
	return result.isEmpty() ? null : result.toArray(new Attribute[0]);
    }

    


    public EntityContext getEntityContext() {
        return entityContext;
    }

    public void setEntityContext(EntityContext entityContext) {
        this.entityContext = entityContext;
    }

    public Entity getEntity() {
	return entityContext == null ? null : entityContext.getEntity();
    }
    
    /////////////////////////////////////////////////////////////////////////
    
    protected boolean isEmpty(Attribute[] attributes) {
	return attributes == null || attributes.length == 0;
    }
    
    protected void checkEntity(Entity entity) {
	if (entity == null) {
	    ErrorHandler.handleEntityError(entity, "Entity must be not null");
	}
	if (entity.getName() == null) {
	    ErrorHandler.handleEntityError(entity, "Entity Name must be not null");
	}
	if (entity.getTableName() == null) {
	    ErrorHandler.handleEntityError(entity, "Entity Table must be not null");
	}
    }

    public void populateTemplate(EntityContext context, Attribute[] attributes) {
	processAttributes(context, attributes);
    }
 
    protected void processAttributes(EntityContext context, Attribute[] attributes) {
	if (isEmpty(attributes)) {
	    return;
	}
	for (Attribute attribute: attributes) {
	    processAttribute(context, attribute);
	}
    }

    protected void processAttribute(EntityContext context, Attribute attribute) {
	if (!isNeedLoad(attribute, context.getEntityConfig())) {
	    return;
	}
	if (attribute instanceof IComposite) {
	    processCompositeAttribute(context, attribute);
	} else if (attribute instanceof IReference) {
	    processReferenceAttribute(context, attribute);
	} else if (attribute instanceof IEntry) {
	    processEntryAttribute(context, attribute);
	} else if (attribute instanceof ICollection) {
	    processCollectionAttribute(context, attribute);
	} else if (attribute.isBasicType()) {
	    processBasicAttribute(context, attribute);
	}
    }
    
    abstract void processCompositeAttribute(EntityContext context, Attribute attribute);
    
    abstract void processReferenceAttribute(EntityContext context, Attribute attribute);
    
    abstract void processEntryAttribute(EntityContext context, Attribute attribute);
    
    abstract void processCollectionAttribute(EntityContext context, Attribute attribute);
    
    abstract void processBasicAttribute(EntityContext context, Attribute attribute);


    /////////////////////////////////////////////////////////////////////////////////////////////

    protected boolean isNeedLoad(Attribute attribute, EntityConfig entityConfig) {
	if (attribute == null) {
	    return false; // NOT LOAD
	}
	LoadMode loadMode = entityConfig.getLoadMode();
	if (LoadMode.FORCE.equals(loadMode)) {
	    return true; // ALWAYS LOAD
	}
	if (attribute.isLazyload()) {
	    return false; // NOT LOAD
	}
	if (attribute.isReferenceType()) {
	    return !entityConfig.isLazyReference();
	}
	if (attribute.isCollectionType()) {
	    return !entityConfig.isLazyCollection();
	}
	return true;
    }
}

