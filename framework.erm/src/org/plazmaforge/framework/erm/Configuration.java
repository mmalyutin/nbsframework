package org.plazmaforge.framework.erm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import org.plazmaforge.framework.core.sql.type.BooleanType;
import org.plazmaforge.framework.core.sql.type.ByteType;
import org.plazmaforge.framework.core.sql.type.DateTimeType;
import org.plazmaforge.framework.core.sql.type.DateType;
import org.plazmaforge.framework.core.sql.type.DoubleType;
import org.plazmaforge.framework.core.sql.type.FloatType;
import org.plazmaforge.framework.core.sql.type.IntegerType;
import org.plazmaforge.framework.core.sql.type.LongType;
import org.plazmaforge.framework.core.sql.type.ObjectType;
import org.plazmaforge.framework.core.sql.type.StringType;
import org.plazmaforge.framework.core.sql.type.TimeType;
import org.plazmaforge.framework.core.sql.type.TimestampType;
import org.plazmaforge.framework.core.sql.type.Type;
import org.plazmaforge.framework.core.sql.type.YNBooleanType;
import org.plazmaforge.framework.core.validation.ContainerValidator;
import org.plazmaforge.framework.core.validation.ExpressionValidator;
import org.plazmaforge.framework.core.validation.NotEmpty;
import org.plazmaforge.framework.core.validation.NotEmptyTrim;
import org.plazmaforge.framework.core.validation.NotNull;
import org.plazmaforge.framework.core.validation.Required;
import org.plazmaforge.framework.core.validation.Size;
import org.plazmaforge.framework.core.validation.Validator;
import org.plazmaforge.framework.erm.mapping.Entity;
import org.plazmaforge.framework.erm.mapping.Mapping;
import org.plazmaforge.framework.erm.procedure.Procedure;
import org.plazmaforge.framework.erm.storage.XMLReader;
import org.plazmaforge.framework.sql.generator.Generator;
import org.plazmaforge.framework.sql.generator.IncrementGenerator;
import org.plazmaforge.framework.sql.generator.SequenceGenerator;
import org.plazmaforge.framework.util.StringUtils;

public class Configuration {

    
    // Configuration Properties

    /**
     * General extension of mapping file. For example '.entity.xml'
     * It overrides property 'mapping.file.extensions' 
     */
    public static final String MAPPING_FILE_EXTENSION_PROPERTY = "mapping.file.extension";

    /**
     * General list of extension of mapping file. For example '.entity.xml, .erm.xml'
     */
    public static final String MAPPING_FILE_EXTENSIONS_PROPERTY = "mapping.file.extensions";
    
    
    public static final String SQL_LOG_PROPERTY = "sql.log";
    
    public static final String FETCH_MODE_PROPERTY = "fetch.mode";
    
    public static final String LOAD_MODE_PROPERTY = "load.mode";
    
    public static final String JOIN_DEPTH_PROPERTY = "join.depth";
    
    
    
    /**
     * Default ERM configuration file
     */
    public static final String DEFAULT_CONFIGURATON_FILE = "erm.xml";
    
    /**
     * Default mapping file extensions
     */
    public static final String[] DEFAULT_MAPPING_FILE_EXTENSIONS = new String[] {".erm.xml", ".entity.xml", ".erm", ".entity"};
    
    public static final String DEFAULT_MAPPING_FILE_EXTENSION = DEFAULT_MAPPING_FILE_EXTENSIONS[0];
    
    
    
    
    public static final FetchMode DEFAULT_FETCH_MODE = FetchMode.JOIN;
    
    public static final LoadMode DEFAULT_LOAD_MODE = LoadMode.AUTO;
    
    public static final int DEFAULT_JOIN_DEPTH = 2;
    
    public static final int MIN_JOIN_DEPTH = 0;
    

    private Map<String, String> properties = new HashMap<String, String>();

    ////

    private Map<String, Entity> entityMap;
    
    private Map<String, Type> typeMap;
    
    private Map<String, Validator> validatorMap;

    private Map<String, Generator> generatorMap;
    
    private Map<String, Procedure> procedureMap;

    ////
    
    /**
     * Name of configuration
     */
    private String name;
    
    private ClassLoader classLoader;
    
    // Only for test
    private Class resourceClass;
    
    private XMLReader reader;
    
    ////

    private String mappingFileExtension;
    
    private String[] mappingFileExtensions;

    private FetchMode fetchMode;
    
    private LoadMode loadMode;
    
    private Integer joinDepth;
    
    ////
    
    private boolean fileLog;
    
    private boolean entityLog;    
    
    
    private Configuration(String name) {
	this.name = name;
	init();
    }

    private void init() {
	mappingFileExtension = null; // By default we use list of extensions
	mappingFileExtensions = DEFAULT_MAPPING_FILE_EXTENSIONS;

	fetchMode = DEFAULT_FETCH_MODE;
	loadMode = DEFAULT_LOAD_MODE;
	joinDepth = DEFAULT_JOIN_DEPTH;
	
	initProperties();
	initTypes();
	initValidators();
	initGenerators();
    }
    
    /**
     * Create Configuration with auto generated name
     * @return
     */
    public static Configuration create() {
	String name = ConfigurationRegister.generateConfigurationName();
	return Configuration.create(name);
    }

    /**
     * Create Configuration with name
     * @param name
     * @return
     */
    public static Configuration create(String name) {
	Configuration configuration = new Configuration(name);
	ConfigurationRegister.registerConfiguration(name, configuration);
	return configuration;
    }

    /**
     * Load properties from <code>InputStream</code> and configure
     * @param is
     * @throws IOException
     */
    public void configure(InputStream is) throws IOException {
	readConfiguration(this, is);
    }
    
    /**
     * Load properties from file and configure
     * @param file
     * @throws IOException
     */
    public void configure(String file) throws IOException {
	FileInputStream is = new FileInputStream(file);
	readConfiguration(this, is);
    }
    

    private static void readConfiguration(Configuration configuration, InputStream is) throws IOException {
	XMLReader reader = new XMLReader();
	reader.readConfiguration(configuration, is);
    }
    


    
    /*
     * key.generator = assigned | increment
     * 
     * collection.query-load.lazy = false
     * collection.query-select.lazy = true
     * 
     * reference.query-load.lazy = false
     * reference.query-select.lazy = false
     * 
     */
    
    public String getProperty(String name) {
	return properties.get(name);
    }
    public void setProperty(String name, String value) {
	properties.put(name, value == null ? null : value.trim()); // Trim value
	notifyChangeProperty(name, value);
    }

    public boolean getBooleanProperty(String name) {
	return getBooleanProperty(name, false);
    }
    
    public boolean getBooleanProperty(String name, boolean def) {
	String value = getProperty(name);
	if (value == null) {
	    return def;
	}
	return "true".equalsIgnoreCase(value) 
		|| "yes".equalsIgnoreCase(value)
		|| "y".equalsIgnoreCase(value)
		|| "1".equalsIgnoreCase(value);
    }
    
    public String getStringProperty(String name) {
	return getStringProperty(name, null);
    }
    public String getStringProperty(String name, String def) {
	String value = getProperty(name);
	return value == null ? def : value;
    }

    

    public int getIntProperty(String name) {
	return getIntProperty(name, 0);
    }
    
    public int getIntProperty(String name, int def) {
	String value = getProperty(name);
	return toInt(value, def);
    }

    ////
    
    protected int toInt(String value, int def) {
	if (value == null) {
	    return def;
	}
	try {
	    return Integer.valueOf(value);
	} catch (NumberFormatException ex ) {
	}
	return def;
    }
    
    protected String toUpper(String value, String def) {
   	if (value == null) {
   	    return def;
   	}
   	return value == null ? null : value.toUpperCase(); 
    }

    protected String toLower(String value, String def) {
   	if (value == null) {
   	    return def;
   	}
   	return value == null ? null : value.toUpperCase(); 
    }

    ////
    
    private void notifyChangeProperty(String name, String value) {
	if (name == null) {
	    return;
	}
	
	if (FETCH_MODE_PROPERTY.equals(name)) {
	    fetchMode = null;
	    try {
		fetchMode = FetchMode.valueOf(toUpper(value, DEFAULT_FETCH_MODE.toString()));
	    } catch (IllegalArgumentException ex) {
	    }
	    if (fetchMode == null){
		fetchMode = DEFAULT_FETCH_MODE;
	    }	    
	    return;
	} else if (LOAD_MODE_PROPERTY.equals(name)) {
	    loadMode = null;
	    try {
		loadMode = LoadMode.valueOf(toUpper(value, DEFAULT_LOAD_MODE.toString()));
	    } catch (IllegalArgumentException ex) {
	    }
	    if (loadMode == null){
		loadMode = DEFAULT_LOAD_MODE;
	    }
	    return;
	} else if (JOIN_DEPTH_PROPERTY.equals(name)) {
	    joinDepth = toInt(value, DEFAULT_JOIN_DEPTH);
	    if (joinDepth < MIN_JOIN_DEPTH) {
		joinDepth = MIN_JOIN_DEPTH;
	    }
	} else if (MAPPING_FILE_EXTENSION_PROPERTY.equals(name)) {
	    mappingFileExtension = value;
	} else if (MAPPING_FILE_EXTENSIONS_PROPERTY.equals(name)) {
	    mappingFileExtensions = value == null ? null : value.split(",");
	}
    }
    
    /////
    
    public String getMappingFileExtension() {
        return mappingFileExtension;
    }

    public String[] getMappingFileExtensions() {
        return mappingFileExtensions;
    }

    
    public FetchMode getFetchMode() {
        return fetchMode;
    }


    public LoadMode getLoadMode() {
        return loadMode;
    }
    
    
    public int getJoinDepth() {
        return joinDepth;
    }
    
    
    public ClassLoader getClassLoader() {
	if (classLoader == null) {
	    classLoader = Thread.currentThread().getContextClassLoader();
	    if (classLoader == null) {
		classLoader = getResourceClass().getClassLoader();
	    }
	}
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public Class getResourceClass() {
	if (resourceClass == null) {
	    resourceClass = getClass();
	}
        return resourceClass;
    }

    public void setResourceClass(Class resourceClass) {
        this.resourceClass = resourceClass;
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    
    private Map<String, Entity> getEntityMap() {
	if (entityMap == null) {
	    entityMap = new HashMap<String, Entity>();
	}
	return entityMap;
    }
    
    public void addMapping(Mapping mapping) {
	if (mapping == null) {
	    throw new IllegalArgumentException("Mapping must be not null");
	}
	List<Entity> entities = mapping.getEntities();
	for (Entity entity : entities) {
	    addEntity(entity);
	}
    }
  
    /**
     * Return identifier of <code>Entity</code>
     * @param entity
     * @return
     */
    public String getEntityIdentifier(Entity entity) {
	// WARNING !!! WE USE CLASS NAME FOR IDENTIFIER
	// TODO: WE CAN USE <name> OR <package>/<name> FOR INDENTIFIER
	return entity == null ? null : entity.getClassName(); 
    }
    
    /**
     * Return parent identifier of <code>Entity</code>
     * @param entity
     * @return
     */
    public String getParentEntityIdentifier(Entity entity) {
	// WARNING !!! WE USE CLASS NAME FOR IDENTIFIER
	// TODO: WE CAN USE <name> OR <package>/<name> FOR INDENTIFIER
	return entity == null ? null : entity.getExtendsClassName(); 
    }
    
    public void addEntity(Entity entity) {
	if (entity != null && entity.getConfiguration() != null) {
	    throw new RuntimeException("Entity already add to configuration");
	}
	checkEntity(entity, true);
	String entityIdentifier = getEntityIdentifier(entity);
	
	if (getEntityMap().containsKey(entityIdentifier)) {
	    throw new RuntimeException("Entity with identifier '" + entityIdentifier + "' already add to configuration");
	}
	
	entity.setConfiguration(this);
	getEntityMap().put(entityIdentifier, entity);

    }
    
    public Entity getEntityByIdentifier(String identifier) {
	if (identifier == null) {
	    throw new IllegalArgumentException("Identifier must be not null");
	}
	Entity entity = getEntityMap().get(identifier);
	if (entity != null && !entity.isCompiled()) {
	    entity.compile();
	}
	return entity;
    }

    public Entity getEntityByClassName(String className) {
	if (className == null) {
	    throw new IllegalArgumentException("Class Name must be not null");
	}
	return getEntityByIdentifier(className);
    }

    public Entity getEntityByClass(Class klass) {
	if (klass == null) {
	    throw new IllegalArgumentException("Class must be not null");
	}
	return getEntityByClassName(klass.getName());
    }    
    
    public Entity[] getEntityHierarchy(Entity entity) {
	if (entity == null) {
	    throw new IllegalArgumentException("Entity must be not null");
	}
	List<Entity> list = new ArrayList<Entity>();
	list.add(entity); // Add current entity
	addParentToHierarchy(entity, list); // Add parent entity (recursive)
	return inverse(list).toArray(new Entity[0]); // Inverse hierarchy - root is first element 
    }
    
    public Entity[] getEntityHierarchyByIdentifier(String identifier) {
	if (identifier == null) {
	    throw new IllegalArgumentException("Identifier must be not null");
	}
	Entity entity = getEntityByIdentifier(identifier);
	if (entity == null) {
	    return null;
	}
	return getEntityHierarchy(entity);
    }

    private void addParentToHierarchy(Entity entity, List<Entity> list) {
	Entity parent = getParentEntity(entity);
	if (parent == null) {
	    return;
	}
	list.add(parent);
	addParentToHierarchy(parent, list);
    }
    
    /**
     * Return super entity.
     * @param entity
     * @return
     */
    public Entity getParentEntity(Entity entity) {
	if (entity == null) {
	    return null;
	}
	String parentIdentifier = getParentEntityIdentifier(entity);
	if (parentIdentifier == null) {
	    return null;
	}
	Entity parent = getEntityByIdentifier(parentIdentifier);
	if (parent == null) {
	    ErrorHandler.handleParentEntityNotMapped(entity, parentIdentifier);
	}
	return parent;
    }

    private static List<Entity> inverse(List<Entity> input){
	if (input == null) {
	    return null;
	}
	List<Entity> output = new ArrayList<Entity>();
	int count = input.size();
	for (int i = count - 1; i >=0; i--) {
	    output.add(input.get(i));
	}
	return output;
    }
    
    public static void checkEntity(Entity entity) {
	checkEntity(entity, false);
    }
    
    public static void checkEntity(Entity entity, boolean autoInit) {
	if (entity == null) {
	    throw new IllegalArgumentException("Entity must be not null");
	}
	String name = entity.getName();
	String className = entity.getClassName();
	
	if (autoInit) {
	    if (name == null && className == null) {
		throw new IllegalArgumentException("Entity Name or Class must be not null");
	    }
	    if (name == null) {
		int index = className.lastIndexOf(".");
		name = index > -1 ? className.substring(index) : className;
		entity.setName(name);
	    }
	    if (className == null) {
		className = name;
		entity.setClassName(className);
	    }
	} else {
	    if (name == null) {
		throw new IllegalArgumentException("Entity Name must be not null");
	    }
	    if (className == null) {
		throw new IllegalArgumentException("Entity Class Name must be not null");
	    }
	}
    }
    
    
    ////
    
    
    public void addMapping(String[] pathList) {
	if (pathList == null || pathList.length == 0) {
	    throw new IllegalArgumentException("Mapping list must be not empty");
	}
	XMLReader reader = getReader();
	for (String path : pathList) {
	    addMapping(reader, path);
	}
    }

    public void addMapping(String path) {
	XMLReader reader = getReader();
	addMapping(reader, path);
    }
    
    ////
    
    private void addMapping(XMLReader reader, String path) {
	if (path == null) {
	    throw new IllegalArgumentException("Mapping path must be not empty");
	}
	
	
	// TODO: See FileUtils.getResource(path)
	
	try {
	    int protocolPos = path.indexOf(":");
	    String pathProtocol = null;
	    if (protocolPos > -1) {
		// Get path protocol (classpath: , file:...)
		pathProtocol = path.substring(0, protocolPos);
		if (pathProtocol.isEmpty()) {
		    //Reset protocol if empty
		    pathProtocol = null;
		}
		// Normalize path: remove protocol with ':'
		path = path.substring(protocolPos + 1);
	    }

	    if (pathProtocol == null || pathProtocol.equals("classpath")) {

		//////////////////////////////////////////////////////////////////////
		// CLASSPATH PROTOCOL (classpath:)
		//////////////////////////////////////////////////////////////////////

		////
		URL url = getResourceClass().getResource("/" + path);
		if (url == null) {
		    ClassLoader classLoader = getClassLoader(); // Thread.currentThread().getContextClassLoader();
		    if (classLoader != null) {
			url = classLoader.getResource(path);
		    }
		}
		////

		if (url == null) {
		    return;
		}
		String protocol = url.getProtocol();
		if ("file".equals(protocol)) {
		    File file = new File(toURI(url).getSchemeSpecificPart());
		    if (!file.exists()) {
			return;
		    }
		    if (file.isDirectory()) {
			addDirectory(reader, file);
		    } else {
			addFile(reader, file);
		    }
		} else if ("jar".equals(protocol)) {
		    String jarPath = url.getPath();
		    int index = jarPath.indexOf("!");
		    int dirIndex = index + 2;
		    if (index > 0 && dirIndex < jarPath.length()) {
			String entryPath = jarPath.substring(dirIndex);
			jarPath = jarPath.substring(0, index);
			jarPath = jarPath.replace("%20", " "); // fixed path: bad code
			url = new URL(jarPath);
			File jar = new File(url.getFile());
			addJarEntry(reader, jar, entryPath);
		    }
		}
	    } else if (pathProtocol.equals("file")) {
		
		//////////////////////////////////////////////////////////////////////
		// FILE PROTOCOL (file:)
		//////////////////////////////////////////////////////////////////////
		
		File file = new File(path);
		if (!file.exists()) {
		    return;
		}
		if (file.isDirectory()) {
		    addDirectory(reader, file);
		} else {
		    addFile(reader, file);
		}
	    }

	} catch (Exception ex) {
	    throw new RuntimeException("Mapping error: " + ex.getMessage());
	}

    }

    private boolean isAcceptMappingFile(String fileName) {
	if (fileName == null) {
	    return false;
	}
	
	// PRIORITY 1 - file extension
	if (mappingFileExtension != null) {
	    return fileName.endsWith(mappingFileExtension);
	}

	// PRIORITY 2 - list of file extension
	if (mappingFileExtensions != null) {
	    return isAcceptMappingFile(fileName, mappingFileExtensions);
	}

	// PRIORITY 3 - list of default file extension
	return isAcceptMappingFile(fileName, DEFAULT_MAPPING_FILE_EXTENSIONS);
	
    }
    
    private boolean isAcceptMappingFile(String fileName, String[] extensions) {
	if (fileName == null || extensions == null) {
	    return false;
	}
	for (String ext: extensions) {
	    if (fileName.endsWith(ext)) {
		return true;
	    }
	}
	return false;
    }
    
    
    private void addDirectory(XMLReader reader, File dir) {
	File[] files = dir.listFiles();
	for (int i = 0; i < files.length; i++) {
	    if (files[i].isDirectory()) {
		addDirectory(reader, files[i]);
	    } else if (isAcceptMappingFile(files[i].getName())) { 
		addFile(reader, files[i]);
	    }
	}
    }
    
    private void addJarEntry(XMLReader reader, File jar, String path) {
	JarFile jarFile = null;
	try {
	    jarFile = createJarFile(jar);
	    Enumeration<JarEntry> jarEntries = jarFile.entries();

	    // Normalize path
	    path = StringUtils.nullIfEmpty(path);
	    
	    while (jarEntries.hasMoreElements()) {
		ZipEntry ze = jarEntries.nextElement();
		String name = ze.getName();
		
		// We use only file
		if (ze.isDirectory()) {
		    continue;
		}
		
		boolean accept = false;
		if (path == null) {
		    accept = isAcceptMappingFile(name);
		} else {
		    accept = name.equals(path) || (name.startsWith(path) && isAcceptMappingFile(name)); 
		}
		if (!accept){
		    continue;
		}
		try {
		    addInputStream(reader, jarFile.getName(), jarFile.getInputStream(ze));
		} catch (Exception ex) {
		    throw new RuntimeException("Could not read mapping documents from '" + jar.getName() + ": " + ex.getMessage());
		}
	    }
	} finally {
	   closeJarFile(jarFile);
	}
    }
    
    private void addFile(XMLReader reader, File xmlFile) {
	 try {
	    Mapping mapping = new Mapping();
	    Mapping m = reader.readMapping(xmlFile);
	    mapping.addMapping(m);
	    
	    //EntityRegister.addMapping(mapping);
	    addMapping(mapping);
	 } catch (Exception ex) {
	     throw new RuntimeException("Mapping error: " + ex.getMessage());
	 }
    }
    
    private void addInputStream(XMLReader reader, String fileName, InputStream is) {
	 try {
	    Mapping mapping = new Mapping();
	    Mapping m = reader.readMapping(fileName, is);
	    mapping.addMapping(m);
	    //EntityRegister.addMapping(mapping);
	    addMapping(mapping);
	 } catch (Exception ex) {
	     throw new RuntimeException("Mapping error: " + ex.getMessage());
	 }
    }
    
    
    private URI toURI(URL url) throws URISyntaxException {
	return toURI(url.toString());
    }

    private URI toURI(String location) throws URISyntaxException {
	return new URI(StringUtils.replaceAll(location, " ", "%20"));
    }

    public XMLReader getReader() {
	if (reader == null) {
	    reader = new XMLReader();
	    reader.setFileLog(fileLog);
	    reader.setEntityLog(entityLog);
	}
        return reader;
    }
    
    ////
    
    private JarFile createJarFile(File file) {
	try {
	    return new JarFile(file);
	} catch (IOException ex) {
	    throw new RuntimeException("Could not read jar file '" + file.getName() + "': " + ex.getMessage());
	}
    }    

    private void closeJarFile(JarFile jarFile) {
	try {
	    if (jarFile != null) {
		jarFile.close();
	    }
	} catch (IOException ioe) {
	    // logger.error("could not close jar", ioe);
	}
    }

    
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    private void initProperties() {
	
    }
    
    /**
     * Initialize basic types
     */
    private void initTypes() {
	addType(ObjectType.NAME, new ObjectType());
	addType(BooleanType.NAME, new YNBooleanType());
	addType(ByteType.NAME, new ByteType());
	addType(IntegerType.NAME, new IntegerType());
	addType(LongType.NAME, new LongType());
	addType(FloatType.NAME, new FloatType());
	addType(DoubleType.NAME, new DoubleType());
	addType(StringType.NAME, new StringType());
	addType(DateType.NAME, new DateType());
	addType(DateTimeType.NAME, new DateTimeType());
	addType(TimestampType.NAME, new TimestampType());
	addType(TimeType.NAME, new TimeType());
    }

    
    private Map<String, Type> getTypeMap() {
	if (typeMap == null) {
	    typeMap = new HashMap<String, Type>();
	}
        return typeMap;
    }

    public void addType(String name, Type type) {
	if (name == null) {
	    throw new IllegalArgumentException("Type Name must be not null");
	}
	if (type == null) {
	    throw new IllegalArgumentException("Type must be not null");
	}
	getTypeMap().put(name, type);
    }
    
    public Type getType(String name) {
        return getTypeMap().get(name);
    }
    
    
    
    /**
     * Initialize basic validators
     */
    private void initValidators() {
	addValidator(NotNull.NAME, new NotNull());
	addValidator(NotEmpty.NAME, new NotEmpty());
	addValidator(NotEmptyTrim.NAME, new NotEmptyTrim());
	addValidator(Required.NAME, new Required());
	addValidator(Size.NAME, new Size());
    }
    
    private Map<String, Validator> getValidatorMap() {
	if (validatorMap == null) {
	    validatorMap = new HashMap<String, Validator>();
	}
        return validatorMap;
    }
    
    public void addValidator(String name, Validator validator) {
	if (name == null) {
	    throw new IllegalArgumentException("Validator Name must be not null");
	}
	if (validator == null) {
	    throw new IllegalArgumentException("Validator must be not null");
	}
	getValidatorMap().put(name, validator);
    }
    
    public Validator getValidator(String name) {
        return getValidatorMap().get(name);
    }
    
    public Validator createValidatorByExpression(String expression) {
	if (expression == null) {
	    return null;
	}
	expression = expression.trim();
	Collection<Validator> validators = getValidatorMap().values();
	if (validators.isEmpty()) {
	    return null;
	}
	String[] expressions = StringUtils.split(expression, "|");
	Validator validator = null;
	if (expressions.length == 1) {
	    validator = findValidatorByExpression(validators, expressions[0]);
	    if (validator == null) {
		throw new RuntimeException("Validator not found for expression '" + expression + "'");
	    }
	    return validator;
	}
	ContainerValidator containerValidator = new ContainerValidator(); 
	for (String exp: expressions) {
	    validator = findValidatorByExpression(validators, exp);
	    if (validator == null) {
		throw new RuntimeException("Validator not found for expression '" + exp + "' in expression list  '" + expression + "'");
	    }
	    containerValidator.addValidator(validator);
	}
	return containerValidator;
    }
    
    private Validator findValidatorByExpression(Collection<Validator> validators, String expression) {
	if (expression == null) {
	    return null;
	}
	expression = expression.trim();
	for (Validator validator: validators ) {
	    if (!(validator instanceof ExpressionValidator)) {
		continue;
	    }
	    ExpressionValidator expressionValidator = ((ExpressionValidator) validator);
	    // if accept expression then create validator
	    if (!expressionValidator.accept(expression)) {
		continue;
	    }
	    return expressionValidator.create(expression);
	}
	return null;
    }


    /**
     * Initialize basic generators
     */
    private void initGenerators() {
	addGenerator(IncrementGenerator.NAME, new IncrementGenerator());
	addGenerator(SequenceGenerator.NAME, new SequenceGenerator());
    }
    
    private Map<String, Generator> getGeneratorMap() {
	if (generatorMap == null) {
	    generatorMap = new HashMap<String, Generator>();
	}
        return generatorMap;
    }    
    
    public void addGenerator(String name, Generator generator) {
	if (name == null) {
	    throw new IllegalArgumentException("Generator Name must be not null");
	}
	if (generator == null) {
	    throw new IllegalArgumentException("Generator must be not null");
	}
	getGeneratorMap().put(name, generator);
    }
    
    public Generator getGenerator(String name) {
        return getGeneratorMap().get(name);
    }
    
    public Generator getKeyGenerator() {
        return null; // TODO;
    }


    
    private Map<String, Procedure> getProcedureMap() {
	if (procedureMap == null) {
	    procedureMap = new HashMap<String, Procedure>();
	}
        return procedureMap;
    }  
    
    
    public void addProcedure(String name, Procedure procedure) {
	if (name == null) {
	    throw new IllegalArgumentException("Procedure Name must be not null");
	}
	if (procedure == null) {
	    throw new IllegalArgumentException("Procedure must be not null");
	}
	getProcedureMap().put(name, procedure);
    }

    public Procedure getProcedure(String name) {
        return getProcedureMap().get(name);
    }

    
    /////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    public static FetchMode getFetchMode(Configuration configuration) {
	return configuration == null ? Configuration.DEFAULT_FETCH_MODE	: configuration.getFetchMode();
    }

    public static LoadMode getLoadMode(Configuration configuration) {
	return configuration == null ? Configuration.DEFAULT_LOAD_MODE	: configuration.getLoadMode();
    }
    
    public static int getJoinDepth(Configuration configuration) {
	return configuration == null ? Configuration.DEFAULT_JOIN_DEPTH	: configuration.getJoinDepth();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    
    public boolean isFileLog() {
        return fileLog;
    }

    public void setFileLog(boolean fileLog) {
        this.fileLog = fileLog;
    }

    public boolean isEntityLog() {
        return entityLog;
    }

    public void setEntityLog(boolean entityLog) {
        this.entityLog = entityLog;
    }
    
    /////

    
    
    
    
    
}
