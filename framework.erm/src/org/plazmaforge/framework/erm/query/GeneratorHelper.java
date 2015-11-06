package org.plazmaforge.framework.erm.query;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.plazmaforge.framework.core.sql.type.Type;
import org.plazmaforge.framework.erm.mapping.Attribute;
import org.plazmaforge.framework.sql.dialect.Dialect;
import org.plazmaforge.framework.sql.generator.Generator;

public class GeneratorHelper {

    public static Object generateValue(Dialect dialect, Connection cn, Attribute attribute) throws SQLException {
   	String table = attribute.getTableName();
   	String column = attribute.getColumnName();
   	Generator generator = attribute.getGenerator();
   	if (generator == null) {
   	    throw new RuntimeException("Generator is not set of attribute '" + attribute.getName() + "'");
   	}
   	Map<String, Object> parameters = new HashMap<String, Object>();
   	parameters.put(Generator.PARAMETER_DIALECT, dialect);
   	parameters.put(Generator.PARAMETER_TABLE, table);
   	parameters.put(Generator.PARAMETER_COLUMN, column);
   	
   	if (attribute.hasGeneratorParameters()) {
   	    Map<String, String> generatorParameters = attribute.getGeneratorParameters();
   	    Set<String> names = generatorParameters.keySet();
   	    for (String name: names) {
   		String value = generatorParameters.get(name);
   		parameters.put(name, value);
   	    }
   	}
   	Object value = generator.generate(cn, parameters);
   	Type type = attribute.getType();
   	if (type != null) {
   	    value = type.toValue(value);
   	}
   	return value;
    }
    
}
