package org.plazmaforge.framework.erm.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.core.logging.Logger;
import org.plazmaforge.framework.core.sql.type.ObjectType;
import org.plazmaforge.framework.core.sql.type.Type;
import org.plazmaforge.framework.erm.Configuration;
import org.plazmaforge.framework.erm.EntityAccessor;
import org.plazmaforge.framework.erm.EntityCreator;
import org.plazmaforge.framework.erm.mapping.Attribute;
import org.plazmaforge.framework.erm.mapping.Entity;
import org.plazmaforge.framework.erm.mapping.EntityHelper;
import org.plazmaforge.framework.erm.mapping.IComposite;
import org.plazmaforge.framework.erm.mapping.IEntry;
import org.plazmaforge.framework.erm.mapping.IReference;
import org.plazmaforge.framework.erm.mapping.Key;
import org.plazmaforge.framework.util.DBUtils;

public class Query {

    private QueryTemplate template;
    
    private PreparedStatement statement;
    
    private ResultSet resultSet;
    

    private boolean logPrepare;
    
    private boolean logExecute;

    
    /**
     * Total row. In paging mode totalRowCount >= rowCount
     */
    private int totalRowCount;
    
    
    private int rowCount;
    
    
    ///////////////////////////////////////////////


    //TODO: STUB
    protected Type DEFAULT_TYPE = new ObjectType();
    
    private static final Logger logger = Logger.getLogger(Query.class.getName());

    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Query(QueryTemplate template) {
	if (template == null){
	    throw new IllegalArgumentException("QueryTemplate must be not null");
	}
	this.template = template;
	init();
    }

    private void init() {
	Configuration configuration = getConfiguration();
	if (configuration == null) {
	    return;
	}
	logExecute = configuration.getBooleanProperty(Configuration.SQL_LOG_PROPERTY, false);
    }
    
    public Configuration getConfiguration() {
	return template == null ? null : template.getConfiguration();
    }
    
    public QueryTemplate getTemplate() {
        return template;
    }


    public void prepareStatement(Connection cn) throws SQLException {
	String sql = template.getText();
	checkSQL(sql);
	close(statement);
	statement = cn.prepareStatement(sql);
	logPrepare("Prepared statement: " + sql);
    }
    
    public void prepareStatementIfNeed(Connection cn) throws SQLException {
	if (statement != null) {
	    return;
	}
	prepareStatement(cn);
    }
    
    public ResultSet executeQuery() throws SQLException {
	close(resultSet);
	resultSet = statement.executeQuery();
	logExecute("Execute query: " + template.getText());
	return resultSet;
    }
    
    public boolean existsResult() throws SQLException {
	ResultSet rs = executeQuery();
	return rs.next();
    }
    
    public int executeUpdate() throws SQLException {
	int count = statement.executeUpdate();
	logExecute("Execute update: " + template.getText());
	return count;
    }
    
    public int executeUpdate(Connection cn) throws SQLException {
	prepareStatementIfNeed(cn);
	return executeUpdate();
    }
    
    public Object getResultValue() throws SQLException {
	executeQuery();
	if (!resultSet.next()){
	    return null;
	}
	return resultSet.getObject(1);
    }

    public int intValue() throws SQLException {
	Object value = getResultValue();
	if (value == null) {
	    return 0;
	}
	if (value instanceof Number) {
	    return ((Number) value).intValue();
	}
	throw new RuntimeException("Result value is not Number. Class=" + value.getClass().getName());
    }
    
    public <T> T getSingleResult() throws SQLException {
	List<T> list = getListResult();
	if (list == null || list.isEmpty()) {
	    return null;
	}
	return list.get(0);
    }
    
    public <T> List<T> getListResult() throws SQLException {
	executeQuery();
	String alias = template.getEntityTable().getTableAlias();
	Entity entity = template.getEntityContext().getEntity();
	List<T> dataList = loadListResult(template, "", entity, alias, resultSet, true);
	setRowCount(dataList == null ? 0 : dataList.size());
	return dataList;
    }

    public PreparedStatement getStatement() {
        return statement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    protected void checkSQL(String sql) {
	if (sql == null || sql.length() == 0) {
	    throw new RuntimeException("Error generate SQL. Query is empty");
	}
    }

    public void close() {
	close(resultSet);
	close(statement);
    }
    
    protected void close(Statement stm) {
	DBUtils.close(stm);
    }
    
    protected void close(ResultSet rs) {
	DBUtils.close(rs);
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////
    
    protected EntityAccessor getEntityAccessor(Entity entity) {
	return EntityHelper.getEntityAccessor(entity);
    }

    protected EntityCreator getEntityCreator(Entity entity) {
	return EntityHelper.getEntityCreator(entity);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    
    public int setParameters(TypeValue[] typeValues, int startPosition) throws SQLException {
	int position = startPosition;
	for (int i = 0; i < typeValues.length; i++) {
	    setSQLValue(statement, typeValues[i].getType(), typeValues[i].getValue(), position);
	    position++;
	}
	return position;
    }
    
    public int setParameters(TypeValue[] typeValues) throws SQLException {
	return setParameters(typeValues, 1);
    }
    
    ////
    
    public int setParameters(Type[] types, Object[] values, int startPosition) throws SQLException {
	int position = startPosition;
	for (int i = 0; i < values.length; i++) {
	    setSQLValue(statement, types == null ? null: types[i], values[i], position);
	    position++;
	}
	return position;
    }
    
    public int setParameters(Type[] types, Object[] values) throws SQLException {
	return setParameters(types, values, 1);
    }
    
    ////
    
    public TypeValue[] getTypeValues(Entity entity, EntityAccessor accessor, List<ColumnDef> columns, Object obj) throws SQLException {
	
	int count = columns.size();
	ColumnDef column = null;
	List<TypeValue> typeValues = new ArrayList<TypeValue>();
	for (int i = 0; i < count; i++) {
	    column = columns.get(i);
	    Type type = column.getType();
	    Object value = accessor.getPathValue(entity, obj, column.getPath());
	    TypeValue typeValue = new TypeValue(type, value);
	    typeValues.add(typeValue);
	}
	return typeValues.toArray(new TypeValue[0]);
    }

    /////////////////////////////////////////////////////////////////////////////////
    
    public void setCriteriaParameters() throws SQLException {
	setCriteriaParameters(1);
    }
    
    public void setCriteriaParameters(int startPosition) throws SQLException {
	List<FilterDef> filters = getTemplate().getFilters();
	if (filters == null || filters.isEmpty()) {
	    return;
	}
	List<TypeValue> typeValueList = new ArrayList<TypeValue>();
	for (FilterDef filter: filters) {
	    if (!FilterDef.CRITERIA.equals(filter.getMarker())) {
		// TODO: Only CRITERIA filter (no DISCRIMINATOR)
		continue;
	    }
	    if (!filter.isParameter()) {
		// Filter is not parameter ( name = 'Tiger', but need name = ?)
		continue;
	    }
	    int parameterCount = filter.getParameterCount();
	    if (parameterCount == 1) {
		typeValueList.add(filter.getTypeValue());
	    } else {
		TypeValue[] cTypeValues = filter.getTypeValues();
		for (TypeValue cTypeValue : cTypeValues) {
		    typeValueList.add(cTypeValue);
		}
	    }
	    //typeValueList.add(new TypeValue(filter.getType(), filter.getValue()));
	}
	
	if (typeValueList.isEmpty()) {
	    return;
	}
	TypeValue[] typeValues = typeValueList.toArray(new TypeValue[0]);
	setParameters(typeValues, startPosition);
    }
    
    /////////////////////////////////////////////////////////////////////////////////
    
    protected Object loadSingleResult(QueryTemplate query, String entityPath, Entity entity, String entityAlias, ResultSet rs, boolean isObject) throws SQLException {
	List<Object> list = loadListResult(query, entityPath, entity, entityAlias, rs, isObject);
	if (list == null || list.isEmpty()) {
	    return null;
	}
	return list.get(0);
    }
    

    protected <T> List<T> loadListResult(QueryTemplate template, String entityPath, Entity entity, String entityAlias, ResultSet rs, boolean isObject) throws SQLException {
	ResultSetMetaData md = rs.getMetaData();
	int columnCount = md.getColumnCount();
	ColumnDef[] allColumns = template.getColumns().toArray(new ColumnDef[0]); // Columns
	ColumnDef[] columns = template.getQueryColumns().toArray(new ColumnDef[0]); // QueryColumns
	if (columnCount != columns.length) {
	    throw new RuntimeException("Invalid query structure");
	}
	Object[] row = null;
	T obj = null;
	List<T> result = new ArrayList<T>();
	while (rs.next()) {
	    row = new Object[columnCount];
	    for (int columnIndex = 0; columnIndex < columnCount; columnIndex++ ) {
		row[columnIndex] = getSQLValue(rs, columns[columnIndex].getType(), columnIndex + 1);
	    }
	    obj = (T) (isObject ? createObject(template, allColumns, row, 0, entityPath, entity, entityAlias) : row);
	    result.add(obj);
	}
	return result;
    }
    

    ////
    
    protected Object getSQLValue(ResultSet rs, Type type, int index) throws SQLException {
	if (type == null) {
	    type = DEFAULT_TYPE;
	}
	Object value = type.get(rs, index);
	if (rs.wasNull()) {
	    return null;
	}
	return value;
    }
    
    protected void setSQLValue(PreparedStatement stm, Type type, Object value, int index) throws SQLException {
	if (type == null) {
	    type = DEFAULT_TYPE;
	}
	if (value == null) {
	    stm.setNull(index, type.getSqlType());
	    return;
	}
	type.set(stm, value, index);
    }
    
    ////
    
    


    protected <T> T createObject(QueryTemplate query, ColumnDef[] columns, Object[] row, int startPosition, String entityPath, Entity entity, String entityAlias) {
	T obj = getEntityCreator(entity).newObject(entity);
	fetchObject(query, columns, row, startPosition, obj, entityAlias);
	return obj;
    }

//    protected Object newObject(Entity entity) {
//    }
//    
//    protected Object newObject(String className) {
//    }
//
//    protected Object nullObject(Entity entity) {
//	return null;
//    }
    
    protected int fetchNullObject(QueryTemplate query, ColumnDef[] columns, Object[] row, int startPosition) {
	int columnCount = row.length;
	int position = startPosition;
	if (position >= columnCount){
	    return 0;
	}
	ColumnDef column = columns[position];
	if (!column.isStart() || column.getBlockSize() <= 0 ) {
	    throw new RuntimeException("Invalid query structure");
	}
	return startPosition + column.getBlockSize();
	
    }
    
    /**
     * Fetch values of Object from value row
     * 
     * @param template
     * @param row
     * @param startPosition
     * @param obj
     * @param path
     * @return
     */
    protected int fetchObject(QueryTemplate template, ColumnDef[] columns, Object[] row, int startPosition, Object obj, String path) {
	
	int columnCount = columns.length;
	
	ColumnDef column = null;
	Object value = null;
	Entity attrEntity = null;
	Attribute attr = null;
	EntityCreator creator = null;
	EntityAccessor accessor = null;
	String attrName = null;
	
	int position = startPosition;
	
	String folder = path == null ? null : (path + QueryTemplate.ATTRIBUTE_SEPARATOR);
	Object nodeObj = null;
	EntityContext context = null;
	int index = 0;
	while (position < columnCount) {    
	    
	    column = columns[position];
	    context = column.getContext();
	    
	    index = column.getIndex();
	    value = index < 0  || index >= row.length ? null : row[index];
	    
	    attr = column.getAttribute();
	    attrEntity = column.getEntity();
	    
	    if (!QueryTemplate.isContainPath(folder, column.getPath())) {
		// don't increment i
		return position; // break. return to up
	    }
	    
	    position++;
	    
	    if (attrEntity == null) { // may be system data
		// STUB
		//position++;
		continue;
	    }
	    
	    if (attr == null) {
		// STUB: Error: Attribute not found
		//position++;
		continue;
	    }
	    
	    accessor = getEntityAccessor(attrEntity); // ACCESOR
	    attrName = attr.getName();
	    
	    // RECONNECT OBJECT TO UP
	    IComposite parentAttr = column.getParentAttribute();
	    if (parentAttr != null && !((IComposite) parentAttr).isPseudoComposite()) {
		obj = accessor.getValue(obj, (Attribute) parentAttr);
	    }

	    if (attr instanceof IComposite && !((IComposite) attr).isPseudoComposite()) {
		
		// NODE (COMPOSITE)
		creator = getEntityCreator(attrEntity); // CREATOR-COMPOSITE
		String nodeClassName = attr.getClassName();
		nodeObj = creator.newObject(nodeClassName);
		accessor.setValue(obj, attr, nodeObj); // Set Node
		
	    } else if (attr instanceof IReference) {
		
		// NODE (REFERENCE)
		IReference reference = (IReference) attr; 
		Entity joinEntity = reference.getJoinEntity();
		creator = getEntityCreator(joinEntity); // CREATOR-REFERENCE
		if (value == null) { // Reference ID is null
		    nodeObj = creator.nullObject(joinEntity);
		    position = fetchNullObject(template, columns, row, position);
		} else {
		    
		    // Get reference object from context by value (key)
		    nodeObj = context.getReferenceObject(attrName, value);
		    if (nodeObj == null) {
			String newPath = QueryTemplate.addAttributePath(path, attr.getName());
			nodeObj = creator.newObject(joinEntity);
			// Put reference object to context
			context.putReferenceObject(attrName, value, nodeObj);
			position = fetchObject(template, columns, row, position, nodeObj, newPath);
		    } else {
			position = fetchNullObject(template, columns, row, position);
		    }
		    
		}
		accessor.setValue(obj, attr, nodeObj); // Set Node
		
	
	} else if (attr instanceof IEntry) {
		
		// NODE (ENTRY)
		IEntry entry = (IEntry) attr; 
		Entity joinEntity = entry.getJoinEntity();
		String joinAttributeName = entry.getJoinAttributeName();
		if (joinAttributeName == null) {
		    // TODO: EXCEPTION
		}
		Attribute joinAttribute = joinEntity.getGlobalAttributeByName(joinAttributeName);
		if (joinAttribute == null) {
		    // TODO: EXCEPTION
		}
		EntityAccessor joinAccessor = getEntityAccessor(joinEntity);
		Key joinKey = joinEntity.getGlobalKey();
		creator = getEntityCreator(joinEntity); // CREATOR-JOIN
		
		// TODO: (label-1) May be analyze value of key of join object before loading, if value is null then load null object
		//if (value == null) { // Reference ID is null
		//    nodeObj = creator.nullObject(joinEntity);
		//    position = fetchNullObject(template, columns, row, position);
		//} else {
		    String newPath = QueryTemplate.addAttributePath(path, attr.getName());
		    nodeObj = creator.newObject(joinEntity);
		    position = fetchObject(template, columns, row, position, nodeObj, newPath);
		//}
		    
		if (joinKey != null) {
		    Object joinId = joinAccessor.getValue(nodeObj, joinKey); 
		    if (joinId == null) {
			nodeObj = null; // Destroy object TODO: May by non create if id is null. See 'label-1'
		    }
		}
		  
		if (nodeObj != null && joinAttribute != null) {
		    joinAccessor.setValue(nodeObj, joinAttribute, obj); // Assign Parent
		}
		accessor.setValue(obj, attr, nodeObj); // Set Node
		
		
		
	    } else {
		accessor.setValue(obj, attr, value);
	    }
	    
	}
	return position;
    }
    
    
    protected void initObject(Object obj, Entity entity) {

    }
    
    protected void logPrepare(String message) {
	if (!logPrepare) {
	    return;
	}
	log(message);
    }

    protected void logExecute(String message) {
	if (!logExecute) {
	    return;
	}
	log(message);
    }

    protected void log(String message) {
	logger.log(message);
    }


    public int getTotalRowCount() {
        return totalRowCount;
    }


    public void setTotalRowCount(int totalRowCount) {
        this.totalRowCount = totalRowCount;
    }


    public int getRowCount() {
        return rowCount;
    }


    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }
    
    
}
