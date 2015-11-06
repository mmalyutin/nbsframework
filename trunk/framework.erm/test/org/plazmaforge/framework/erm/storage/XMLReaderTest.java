package org.plazmaforge.framework.erm.storage;

import java.util.List;

import org.junit.Test;
import org.plazmaforge.framework.erm.BaseTestCase;
import org.plazmaforge.framework.erm.Configuration;
import org.plazmaforge.framework.erm.mapping.Mapping;
import org.plazmaforge.framework.erm.model.MCountry;
import org.plazmaforge.framework.erm.model.ModelHelper;
import org.plazmaforge.framework.erm.sql.SQLExecutor;
import org.plazmaforge.framework.sql.dialect.Dialect;
import org.plazmaforge.framework.sql.dialect.DialectFactory;

public class XMLReaderTest extends BaseTestCase {

    private Configuration configuration;
    private SQLExecutor executor;
    
    protected void setUp() {
	
	////////////////////////////////////////////////////////////////////////
	// INITIALIZE MAPPING
	////////////////////////////////////////////////////////////////////////
	
	try {
	    configuration = ModelHelper.createEmptyConfiguration();
	    Dialect dialect = DialectFactory.getDialect(getConnection()); 
	    executor = new SQLExecutor(dialect);
	    executor.setConfiguration(configuration);
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
    
    private void addFileMappings(Mapping mapping, XMLReader reader, String[] files) throws Exception {
	ModelHelper.addFileMappings(mapping, reader, files);
    }
    
    @Test
    public void testReader() throws Exception {
	XMLReader reader = new XMLReader();
	Mapping mapping = new Mapping(); // General mapping container
	String[] files = ModelHelper.XML_FILES;
	addFileMappings(mapping, reader, files);
	
	assertTrue(mapping.getEntities().size() > 0);
	configuration.addMapping(mapping);
	
	List countries = executor.findAll(getConnection(), MCountry.class);
	assertNotNull(countries);
	
    }
}
