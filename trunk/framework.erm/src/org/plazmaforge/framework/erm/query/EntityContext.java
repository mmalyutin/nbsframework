package org.plazmaforge.framework.erm.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.erm.mapping.Attribute;
import org.plazmaforge.framework.erm.mapping.Entity;
import org.plazmaforge.framework.erm.mapping.EntityHelper;

public class EntityContext {
    
    /**
     * The entity
     */
    private Entity entity; // REAL ENTITY

    /**
     * 
     * Current path
     */
    private String path;

    /**
     * Start column of the entity 
     */
    private ColumnDef startColumn;

    /**
     * All hierarchy tables of entity
     */
    private TableDef[] tables; // HIERARHY OF REAL TABLES

    /**
     * All hierarchy entities of entity 
     */
    private Entity[] entities; // HIERARHY OF REAL ENTITY

    /**
     * All attributes of entity
     */
    private Attribute[] attributes; // ATTRIBUTES OF REAL ENTITY

    
    /**
     * Map of references Map<atribute, Map<id, object>
     */
    private Map<String, Map<Object, Object>> referenceMap;

    /**
     * Parent context (null if root)
     */
    private EntityContext parent;
    
    /**
     * Children contexts
     */
    private List<EntityContext> children;
    
    
    /**
     * Configuration of entity
     */
    private EntityConfig entityConfig;
    
    /**
     * Pre/Post processing attributes
     */
    private List<Attribute> processingArttributes;
    
    /**
     * Special query container for attributes
     */
    private Map<String, QueryContainer> attributeQueries;
    
    /**
     * Level in tree (0 if root)
     */
    private int level;

    /**
     * Name of attribute that create context (null if root)
     */
    private String attributeName;
    
    
    private String[] includeAttributes;
    
    
    private String[] excludeAttributes;

    
    
    public EntityContext(Entity entity) {
	if (entity == null) {
	    throw new IllegalArgumentException("Entity must be not null");
	}
	this.entity = entity;
    }

    
    public EntityContext(Entity entity, TableDef table) {
	this(entity);
	//if (entity == null) {
	//    throw new IllegalArgumentException("Entity must be not null");
	//}
	if (table == null) {
	    throw new IllegalArgumentException("Table must be not null");
	}
	//this.entity = entity;
	this.entities = new Entity[] {entity};
	this.tables = new TableDef[] {table};
    }


    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ColumnDef getStartColumn() {
        return startColumn;
    }

    public void setStartColumn(ColumnDef startColumn) {
        this.startColumn = startColumn;
    }

    public TableDef[] getTables() {
        return tables;
    }

    public void setTables(TableDef[] tables) {
        this.tables = tables;
    }

    public TableDef getTable(int index) {
        return tables[index];
    }

    public Entity[] getEntities() {
        return entities;
    }

    public void setEntities(Entity[] entities) {
        this.entities = entities;
    }

    public Entity getEntity(int index) {
	return entities[index];
    }
    
    public Attribute[] getAttributes() {
        return attributes;
    }

    public void setAttributes(Attribute[] attributes) {
        this.attributes = attributes;
    }	

    ////
    
    public void initStartColumn(ColumnDef column) {
	if (startColumn != null) {
	    return;
	}
	column.setStart(true);
	setStartColumn(column);
    }
    
    public TableDef findTableByEntity(String entityIdentifier) {
	if (entityIdentifier == null || entities == null) {
	    return null;
	}
	int count = entities.length;
	if (entityIdentifier != null) {
	    for (int i = 0; i < count; i++) {
		if (entityIdentifier.equals(entities[i].getIdentifier())) {
		    return tables[i];
		}
	    }
	}
	return null;
    }
    
    public TableDef findTableInTree(Map<String, EntityContext> map, String propertyName, String entityIdentifier) {
	if (map == null || propertyName == null || entityIdentifier == null) {
	    return null;
	}
	int index = propertyName.indexOf("."); 
	if (index == -1 ) { // not found '.' - root
	    return findTableByEntity(entityIdentifier);
	}
	index = propertyName.lastIndexOf(".");
	String path = propertyName.substring(0, index);
	EntityContext ctx = map.get(path);
	if (ctx == null) {
	    return null;
	}
	return ctx.findTableByEntity(entityIdentifier);
    }

    public static Map<String, EntityContext> getFlatContextTree(EntityContext root) {
	if (root == null) {
	    throw new IllegalArgumentException("EntityContext must be not null");
	}
	if (root.getParent() != null) {
	    throw new IllegalArgumentException("EntityContext must be root");
	}
	String path = null;
	Map<String, EntityContext> map = new HashMap<String, EntityContext>();
	List<EntityContext> contexts = root.getChildren();
	populate(path, map, contexts);
	return map;
    }
    
    private static void populate(String path, Map<String, EntityContext> map, List<EntityContext> contexts) {
	if (contexts == null || contexts.isEmpty()) {
	    return;
	}
	path = EntityHelper.getNormalizePath(path);
	for (EntityContext context : contexts) {
	    if(context.getAttributeName() == null) {
		throw new IllegalArgumentException("AttributeName of EntityContext must be not null");
	    }
	    String p = path + context.getAttributeName(); 
	    map.put(p, context);
	    populate(p, map, context.getChildren());
	}
    }
    
    public EntityContext getParent() {
        return parent;
    }

    private void setParent(EntityContext parent) {
        this.parent = parent;
    }

    public List<EntityContext> getChildren() {
	if (children == null) {
	    children = new ArrayList<EntityContext>();
	}
        return children;
    }

    public void addChild(EntityContext child) {
	if (child == null) {
	    throw new IllegalArgumentException("Child EntityContext must be not null");
	}
	if (child.getLevel() != 0) {
	    throw new IllegalArgumentException("Start level of EntityContext must be 0");
	}
	child.setParent(this);
        getChildren().add(child);
        child.setLevel(level + 1); // Child level = Parent level + 1
    }
    
    public boolean hasChildren() {
	return children != null && !children.isEmpty(); 
    }
    
    ////
    
    /**
     * Get reference object by attribute name and object id 
     * @param attributeName
     * @param id
     * @return
     */
    public Object getReferenceObject(String attributeName, Object id) {
	if (attributeName == null) {
	    throw new IllegalArgumentException("AttributeName must be not null");
	}
	if (id == null) {
	    throw new IllegalArgumentException("ID must be not null");
	}
	if (referenceMap == null){
	    return null;
	}
	Map<Object, Object> m = referenceMap.get(attributeName);
	if (m == null) {
	    return null;
	}
	return m.get(id);
    }

    /**
     * Put reference object to reference map
     * @param attributeName
     * @param id
     * @param obj
     */
    public void putReferenceObject(String attributeName,  Object id, Object obj) {
	if (attributeName == null) {
	    throw new IllegalArgumentException("AttributeName must be not null");
	}
	if (id == null) {
	    throw new IllegalArgumentException("ID must be not null");
	}
	if (obj == null) {
	    throw new IllegalArgumentException("Object must be not null");
	}
	Map<Object, Object> m = null;
	if (referenceMap == null){
	    referenceMap = new HashMap<String, Map<Object, Object>>();
	} else {
	    m = referenceMap.get(attributeName);
	}
	if (m == null) {
	    m = new HashMap<Object, Object>();
	    referenceMap.put(attributeName, m);
	}
	m.put(id, obj);
    }


    public EntityConfig getEntityConfig() {
        return entityConfig;
    }


    public void setEntityConfig(EntityConfig entityConfig) {
        this.entityConfig = entityConfig;
    }

    public EntityConfig cloneEntityConfig() {
	if (entityConfig == null) {
	    return null;
	}
	return (EntityConfig) entityConfig.clone();
    }

    public boolean hasProcessingAttributes() {
        return processingArttributes != null && !processingArttributes.isEmpty();
    }


    public Attribute[] getProcessingArttributes() {
        return processingArttributes.toArray(new Attribute[0]);
    }

    private List<Attribute> doGetProcessingArttributes() {
	if (processingArttributes == null) {
	    processingArttributes = new ArrayList<Attribute>();
	}
        return processingArttributes;
    }


    /**
     * Add Pre/Post processing attribute
     * @param attribute
     */
    public void addProcessingArttribute(Attribute attribute) {
	doGetProcessingArttributes().add(attribute);
    }


    private Map<String, QueryContainer> getAttributeQueries() {
	if (attributeQueries == null) {
	    attributeQueries = new HashMap<String, QueryContainer>(); 
	}
        return attributeQueries;
    }


    public void addAttributeQuery(String attributeName, Query query) {
	if (attributeName == null) {
	    throw new IllegalArgumentException("AttributeName must be not null");
	}
	if (query == null) {
	    throw new IllegalArgumentException("Query must be not null");
	}
	Map<String, QueryContainer> queryMap = getAttributeQueries();
	QueryContainer queryContainer = queryMap.get(attributeName);
	if (queryContainer == null) {
	    queryContainer = new QueryContainer();
	    queryMap.put(attributeName, queryContainer);
	}
	queryContainer.addQuery(query);
    }


    public List<Query> getAttributeQueries(String attributeName, QueryType queryType) {
	if (attributeName == null) {
	    throw new IllegalArgumentException("AttributeName must be not null");
	}
	if (queryType == null) {
	    throw new IllegalArgumentException("QueryType must be not null");
	}
	Map<String, QueryContainer> queryMap = getAttributeQueries();
	QueryContainer queryContainer = queryMap.get(attributeName);
	if (queryContainer == null) {
	    return null;
	}
	return queryContainer.getQueries(queryType);
    }


    public int getLevel() {
        return level;
    }

    private void setLevel(int level) {
        this.level = level;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }
    
    ////
    
    public String[] getIncludeAttributes() {
        return includeAttributes;
    }

    public void setIncludeAttributes(String[] includeAttributes) {
        this.includeAttributes = includeAttributes;
    }

    public String[] getExcludeAttributes() {
        return excludeAttributes;
    }

    public void setExcludeAttributes(String[] excludeAttributes) {
        this.excludeAttributes = excludeAttributes;
    }
}
