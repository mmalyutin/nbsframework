package org.plazmaforge.framework.sql.generator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public interface Generator {
    
    String PARAMETER_DIALECT = "dialect";
    
    String PARAMETER_TABLE = "table";
    
    String PARAMETER_COLUMN = "column";
    
    String PARAMETER_SEQUENCE = "sequence";

    Object generate(Connection cn, Map<String, Object> parameters) throws SQLException; 
    
}
