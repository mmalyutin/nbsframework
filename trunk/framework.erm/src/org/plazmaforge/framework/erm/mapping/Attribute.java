package org.plazmaforge.framework.erm.mapping;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.core.sql.type.Type;
import org.plazmaforge.framework.core.validation.Validator;
import org.plazmaforge.framework.erm.Accessor;
import org.plazmaforge.framework.erm.Configuration;
import org.plazmaforge.framework.sql.generator.Generator;
import org.plazmaforge.framework.util.ClassUtils;

public class Attribute implements Serializable {

    private static final long serialVersionUID = 7704093134531850344L;

    public static String COMPOSITE_TYPE = "composite";
    
    public static String REFERENCE_TYPE = "reference";
    
    public static String COLLECTION_TYPE = "collection";
    
    
    /**
     * Name of attribute
     */
    private String name;
    
    /**
     * Name of data type
     */
    private String typeName;
    
    /**
     * Data type
     */
    private Type type; // ONLY FOR COMPILE //
    
    /**
     * Name of class
     */
    private String className;

    /**
     * Name of implementer class
     */
    private String implementerClassName;
    
    
    private Class implementerClass;
    
    /**
     * List of columns
     */
    private List<Column> columns;
    
    /**
     * Type of generator
     */
    private String generatorType;
    
    /**
     * Name of generator class
     */
    private String generatorClassName;
    
    /**
     * Parameters of generator
     */
    private Map<String, String> generatorParameters;
    
    
    /**
     * Value generator
     */
    private Generator generator; // ONLY FOR COMPILE //

    /**
     * Check expression
     */
    private String check; 
    
    /**
     * Type of validator
     */
    private String validatorType;
    
    /**
     * Name of validator class
     */
    private String validatorClassName;
    
    /**
     * Value validator
     */
    private Validator validator; // ONLY FOR COMPILE //

    
    private boolean readonly;

    private boolean required;
    
    private boolean unique;
    
    private boolean insert = true;

    private boolean update = true;

    private boolean lazyload;
    
    /**
     * Output data format
     */
    private String format;
    
    
    private String defaultValue;
    
    private String nullValue; // If value of attribute is null return the 'nullValue' 
    
    private String unsavedValue; // (Empty value) Only for primary key. Use to indicate that primary key is new (unsaved)   
    
    
    private String formula; // SQL Expression by default
    
    
    private INode parent;
    
    private Accessor accessor;
    
    
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getTypeName() {
        return typeName;
    }


    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }


    public Type getType() {
        return type;
    }

    public String getClassName() {
        return className;
    }


    public void setClassName(String className) {
        this.className = className;
    }


    public String getImplementerClassName() {
        return implementerClassName;
    }


    public void setImplementerClassName(String implementerClassName) {
        this.implementerClassName = implementerClassName;
    }


    public Class getImplementerClass() {
        return implementerClass;
    }


    public Column getColumn() {
	if (!hasColumns()) {
	    return null;
	}
        return doGetColumns().get(0);
    }


    public void setColumn(Column column) {
	if (hasColumns()) {
	    doGetColumns().set(0, column);
	} else {
	    doGetColumns().add(column);
	}
    }


    public String getColumnName() {
	Column column = getColumn();
        return column == null ? null : column.getName();
    }


    public void setColumnName(String columnName) {
	Column column = getColumn();
	if (column == null) {
	    column = new Column();
	}
	column.setName(columnName);
	setColumn(column);
    }

    protected List<Column> doGetColumns() {
	if (columns == null) {
	    columns = new ArrayList<Column>();
	}
        return columns;
    }

    public Column[] getColumns() {
	if (columns == null) {
	    return new Column[0];
	}
        return columns.toArray(new Column[0]);
    }

    
    public boolean hasDetailColumns() {
	if (!isCompositeType()){
	    return hasColumns();
	}
	Column[] columns = getDetailColumns();
	return columns != null && columns.length > 0;
    }
    

    public Attribute[] getDetailAttributes() {
	return EntityHelper.getDetailAttributes(this);
    }

    public Column[] getDetailColumns() {
	return EntityHelper.getColumns(getDetailAttributes());
    }

    
    public void addColumns(Column column) {
	doGetColumns().add(column);
    }
    
    public boolean isMultiColumn() {
	return hasColumns();
    }
    
    public boolean hasColumns() {
	return columns != null && !columns.isEmpty();
    }
    
    public String getGeneratorType() {
        return generatorType;
    }


    public void setGeneratorType(String generatorType) {
        this.generatorType = generatorType;
    }


    public String getGeneratorClassName() {
        return generatorClassName;
    }


    public void setGeneratorClassName(String generatorClassName) {
        this.generatorClassName = generatorClassName;
    }

    public Map<String, String> getGeneratorParameters() {
	if (generatorParameters == null) {
	    generatorParameters = new HashMap<String, String>();
	}
        return generatorParameters;
    }

    public void addGeneratorParameter(String name, String value) {
	getGeneratorParameters().put(name, value);
    }
    
    public boolean hasGeneratorParameters() {
	return generatorParameters != null;
    }

    public Generator getGenerator() {
        return generator;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getValidatorType() {
        return validatorType;
    }

    public void setValidatorType(String validatorType) {
        this.validatorType = validatorType;
    }

    public String getValidatorClassName() {
        return validatorClassName;
    }

    public void setValidatorClassName(String validatorClassName) {
        this.validatorClassName = validatorClassName;
    }

    public Validator getValidator() {
        return validator;
    }


    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    public boolean isRequired() {
        return required;
    }

    public boolean isEditable() {
        return !isReadonly();
    }
    
    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isKey() {
        return false;
    }

    public boolean isAutoKey() {
        return false;
    }
    
    public boolean isAssignKey() {
	return false;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public boolean isInsert() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }

    public boolean isInsertEditable() {
        return isEditable() && isInsert();
    }

    public boolean isUpdate() {
        return update;
    }


    public void setUpdate(boolean update) {
        this.update = update;
    }


    public boolean isUpdateEditable() {
        return isEditable() && isUpdate();
    }

    public boolean isLazyload() {
        return lazyload;
    }


    public void setLazyload(boolean lazyload) {
        this.lazyload = lazyload;
    }


    public String getFormat() {
        return format;
    }


    public void setFormat(String format) {
        this.format = format;
    }


    public String getDefaultValue() {
        return defaultValue;
    }


    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }


    public String getNullValue() {
        return nullValue;
    }


    public void setNullValue(String nullValue) {
        this.nullValue = nullValue;
    }


    public String getUnsavedValue() {
        return unsavedValue;
    }


    public void setUnsavedValue(String unsavedValue) {
        this.unsavedValue = unsavedValue;
    }


    public String getFormula() {
        return formula;
    }


    public void setFormula(String formula) {
        this.formula = formula;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////



    // TODO: Must compile (set TableEntity) before using.
    public Entity getTableEntity() {
	Entity entity = getEntity();
	if (entity == null || entity.hasOwnTable()) {
	    return entity;
	}
	if (!(entity instanceof SubclassEntity)) {
	    return entity;
	}

	Entity[] entities = entity.getHierarchy(); //EntityHelper.getEntityHierarchy(entity);
	if (entities.length < 2) {
	    return entity;
	}
	return entities[entities.length - 2]; // return before current in hierarchy 
    }

    
    public Entity getEntity() {
	return getRootEntity();
    }


    public void setParent(INode parent) {
        this.parent = parent;
    }
    
    public INode getParent() {
	return parent;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    
    public String getEntityIdentifier() {
        return getEntity() == null ? null : getEntity().getIdentifier();
    }

    public String getEntityName() {
        return getEntity() == null ? null : getEntity().getName();
    }

    public String getTableName() {
        return getEntity() == null ? null : getEntity().getTableName();
    }

    public EntityType getEntityType() {
	return getEntity() == null ? null : getEntity().getEntityType();
    }

    public Configuration getConfiguration() {
	return getEntity() == null ? null : getEntity().getConfiguration();
    }

    ////
    
    protected Entity getRootEntity() {
	if (parent == null){
	    return null;
	}
	if (parent instanceof Entity) {
	    return  (Entity) parent;
	}
	if (parent instanceof IComposite) {
	    return ((Attribute) parent).getRootEntity();
	}
	return null;
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean isDiscriminatorType() {
	return false;
    }

    // Base (Inner)
    public boolean isBasicType() {
	return isInnerType() && !isCompositeType(); 
    }
    
    // Composite (Inner)
    public boolean isCompositeType() {
	return false;
    }
    
    public boolean isInnerType() {
	return !isOuterType();
    }
    
    // Outer
    public boolean isOuterType() {
	return isReferenceType() || isEntryType() || isCollectionType();
    }

    // Reference (Outer)
    public boolean isReferenceType() {
	return false;
    }

    // Entry (Outer)
    public boolean isEntryType() {
	return false;
    }

    // Collection (Outer)
    public boolean isCollectionType() {
	return false;
    }

    public boolean isCascadeSensitive() {
	return isEntryType() || isCollectionType();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean isAnonym() {
	return false;
    }


    public String getNamePath() {
	return getName();
    }

    public String toString() {
	return "Basic Attribute: " + toPropertiesString(); 
    }
    
    public String toPropertiesString() {
	return "name=" + name;
    }


    public Accessor getAccessor() {
        return accessor;
    }


    public void setAccessor(Accessor accessor) {
        this.accessor = accessor;
    }

  


    protected void compile(Configuration configuration) {
	
	//Configuration configuration = getConfiguration();
	if (configuration == null) {
	    throw new RuntimeException("Configuration must be not null");
	}
	
	// TYPE
	String typeName = getTypeName();
	if (typeName != null) {
	    //type = EntityRegister.getType(typeName);
	    type = configuration.getType(typeName);
	}
	
	// IMPLEMENTER CLASS
	String implementerClassName = getImplementerClassName();
	if (implementerClassName != null) {
	    implementerClass = getClass(implementerClassName);
	    checkImplementerClass();
	}
	
	// ACCESSOR
	checkAccessor();
	
	// GENERATOR
	String generatorClassName = getGeneratorClassName();
	String generatorType = getGeneratorType();
	if (generatorClassName != null) {
	    // CREATE NEW INSTANCE BY CLASS
	    generator = (Generator) getInstance(generatorClassName);
	} else if (generatorType != null) {
	    // GET INSTANCE FROM CONFIGURATION
	    generator = configuration.getGenerator(generatorType);
	}
	
	// INIT DEFAULT KEY GENERATOR (IF NOT JOINED ENTITY)
	if (generator == null && isKey()) {
	    if (!EntityType.JoinedEntity.equals(getEntityType())) {
		generator = configuration.getKeyGenerator();
	    }
	}
	
	// VALIDATOR
	String validatorCalssName = getValidatorClassName();
	String validatorType = getValidatorType();
	String checkExpression = getCheck();
	if (validatorCalssName != null) {
	    // CREATE NEW INSTANCE BY CLASS
	    validator = (Validator) getInstance(validatorCalssName);
	} else if (validatorType != null) {
	    // GET INSTANCE FROM CONFIGURATION
	    validator = configuration.getValidator(validatorType);
	} else if (checkExpression != null) {
	    // CREATE NEW INSTANCE BY CLASS
	    validator = configuration.createValidatorByExpression(checkExpression);
	}
	
	
    }

    protected void checkAccessor() {
    }

    protected void checkImplementerClass() {
    }
    
    protected Class<?> getClass(String className) {
	return ClassUtils.getSafeClass(className);
    }

    protected Object getInstance(String className) {
	return ClassUtils.newSafeInstance(className);
    }
    
    
}
