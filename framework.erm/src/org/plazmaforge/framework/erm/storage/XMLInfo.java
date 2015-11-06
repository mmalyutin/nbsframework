package org.plazmaforge.framework.erm.storage;

public interface XMLInfo {

    //////////////////////////////////////////////////////////////
    // CONFIGURATION
    //////////////////////////////////////////////////////////////
    
    String XML_CONFIGURATION = "configuration";
    
    String XML_MAPPING = "mapping";
    
    String XML_PATH = "path";
    
    
    
    //////////////////////////////////////////////////////////////
    // DOCUMENT
    //////////////////////////////////////////////////////////////
    
    String XML_ENTITY_MAPPING = "entity-mapping";
    
    
    //////////////////////////////////////////////////////////////
    // ENTITY
    //////////////////////////////////////////////////////////////
    
    String XML_ENTITY = "entity";
    
    String XML_JOINED_ENTITY = "joined-entity";
    
    String XML_SUBCLASS_ENTITY = "subclass-entity";
    
    String XML_EMBEDDED_ENTITY = "embedded-entity";
    

    //////////////////////////////////////////////////////////////
    // ENTITY CHILDREN
    //////////////////////////////////////////////////////////////

    String XML_CONFIG = "config";
    
    String XML_ALIASES = "aliases";
    
    String XML_ATTRIBUTES = "attributes";
    
    String XML_QUERIES = "queries";
    
    String XML_EVENTS = "events";
    
    String XML_TRIGGERS = "triggers";
    
    String XML_PROPERTY = "property";
    
    String XML_PROPERTIES = "properties";
    
    //////////////////////////////////////////////////////////////
    // ATTRIBUTE
    //////////////////////////////////////////////////////////////
    
    String XML_ATTRIBUTE = "attribute";
    
    String XML_REFERENCE = "reference";
    
    String XML_ENTRY = "entry";
    
    String XML_COMPOSITE = "composite";
    
    String XML_COLLECTION = "collection";
    
    String XML_DISCRIMINATOR = "discriminator";
    
    String XML_DISCRIMINATOR_ATTR = "discriminator-value";
    

    //////////////////////////////////////////////////////////////
    // ALIAS
    //////////////////////////////////////////////////////////////
    
    String XML_ALIAS_NAME_ATTR = "name";
    
    String XML_ALIAS_ATTRIBUTE_ATTR = "attribute";
    
    String XML_ALIAS_ORDER_ATTRIBUTE_ATTR = "order-attribute";
    
    String XML_ALIAS_FILTER_ATTRIBUTE_ATTR = "filter-attribute";

    
    //////////////////////////////////////////////////////////////
    // EVENT
    //////////////////////////////////////////////////////////////
    
    String XML_EVENT = "event";
    

    //////////////////////////////////////////////////////////////
    // TRIGGER
    //////////////////////////////////////////////////////////////
    
    String XML_TRIGGER = "trigger";
    
    String XML_SCRIPT = "script";
    
    String XML_LANGUAGE_ATTR = "language";
    
    
    
    
    
    String XML_KEY = "key";
    
    String XML_COMPOSITE_KEY = "composite-key";
    
    
    String XML_TABLE = "table";
    
    String XML_COLUMN = "column";
    
    String XML_COLUMNS = "columns";
    
    
    String XML_ID_ATTR = "id";
    
    String XML_NAME_ATTR = "name";
    
    String XML_TYPE_ATTR = "type";
    
    String XML_CLASS_ATTR = "class";
    
    
    String XML_TABLE_ATTR = "table";
    
    String XML_COLUMN_ATTR = "column";
    
    
    String XML_EXTENDS_ENTITY_ATTR = "extends-entity";
    
    String XML_EXTENDS_CLASS_ATTR = "extends-class";
    
    String XML_HEADER_CLASS_ATTR = "header-class";
    
    String XML_IMPLEMENTER_CLASS_ATTR = "implementer-class";
    
    
    String XML_CASCADE_ATTR = "cascade";
    

    //////////////////////////////////////////////////////////////
    // JOIN
    //////////////////////////////////////////////////////////////

    String XML_JOIN_ENTITY_ATTR = "join-entity";
    
    String XML_JOIN_CLASS_ATTR = "join-class";
    
    String XML_JOIN_ATTRIBUTE_ATTR = "join-attribute";
    
    String XML_JOIN_TABLE_ATTR = "join-table";
    
    String XML_JOIN_COLUMN_ATTR = "join-column";
    
    String XML_JOIN_COLUMN = "join-columns";
    
    String XML_JOIN_DEPTH = "join-depth";
    
    //////////////////////////////////////////////////////////////
    // MODIFY
    //////////////////////////////////////////////////////////////
    
    String XML_READONLY_ATTR = "readonly";
    
    String XML_INSERT_ATTR = "insert";
    
    String XML_UPDATE_ATTR = "update";
    
    String XML_REQUIRED_ATTR = "required";

    

    //////////////////////////////////////////////////////////////
    // PARAMETER
    //////////////////////////////////////////////////////////////
    
    String XML_PARAMETERS = "parameters";
    
    String XML_PARAMETER = "parameter";

    
    //////////////////////////////////////////////////////////////
    // TYPE
    //////////////////////////////////////////////////////////////
    
    String XML_TYPES = "types";
    
    String XML_TYPE = "type";
    
    
    //////////////////////////////////////////////////////////////
    // GENERATOR
    //////////////////////////////////////////////////////////////
    
    String XML_GENERATORS = "generators";
    
    String XML_GENERATOR = "generator";
    
    String XML_GENERATOR_TYPE_ATTR = "generator-type";
    
    String XML_GENERATOR_CLASS_ATTR = "generator-class";
    

    //////////////////////////////////////////////////////////////
    // VALIDATOR
    //////////////////////////////////////////////////////////////

    String XML_CHECK_ATTR = "check";
    
    String XML_VALIDATORS = "validators";
    
    String XML_VALIDATOR = "validator";
    
    String XML_VALIDATOR_TYPE_ATTR = "validator-type";
    
    String XML_VALIDATOR_CLASS_ATTR = "validator-class";



    //////////////////////////////////////////////////////////////
    // PROCEDURE
    //////////////////////////////////////////////////////////////
    
    String XML_PROCEDURES = "procedures";
    
    String XML_PROCEDURE = "procedure";
    
    String XML_ENTRY_POINT_ATTR = "entry-point";

}
