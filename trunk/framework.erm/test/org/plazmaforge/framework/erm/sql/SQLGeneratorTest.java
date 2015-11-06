package org.plazmaforge.framework.erm.sql;



import org.junit.Test;
import org.plazmaforge.framework.erm.BaseTestCase;
import org.plazmaforge.framework.erm.Configuration;
import org.plazmaforge.framework.erm.ConfigurationRegister;
import org.plazmaforge.framework.erm.mapping.Entity;
import org.plazmaforge.framework.erm.model.MCity;
import org.plazmaforge.framework.erm.model.MCountry;
import org.plazmaforge.framework.erm.model.MCountryComplex;
import org.plazmaforge.framework.erm.model.MLocality;
import org.plazmaforge.framework.erm.model.ModelHelper;
import org.plazmaforge.framework.erm.query.DeleteTemplate;
import org.plazmaforge.framework.erm.query.InsertTemplate;
import org.plazmaforge.framework.erm.query.QueryBuilder;
import org.plazmaforge.framework.erm.query.SelectTemplate;
import org.plazmaforge.framework.erm.query.UpdateTemplate;
import org.plazmaforge.framework.sql.dialect.Dialect;
import org.plazmaforge.framework.sql.dialect.GenericDialect;

public class SQLGeneratorTest extends BaseTestCase {

    private Dialect dialect;
    private QueryBuilder builder;
    private SQLGenerator generator;
    private String sql;
    
    private Entity countryEntity;
    private Entity countryComplexEntity;
    private Entity localityEntity;
    private Entity cityEntity;
    
    private SelectTemplate selectTemplate;
    private InsertTemplate insertTemplate;
    private UpdateTemplate updateTemplate;
    private DeleteTemplate deleteTemplate;
    private boolean init;
    
    protected void setUp() throws Exception {
	
	
	////////////////////////////////////////////////////////////////////////
	// INITIALIZE MAPPING
	////////////////////////////////////////////////////////////////////////
	if (init) {
	    return;
	}
	Configuration configuration = ModelHelper.createConfiguration();
	
	countryEntity = configuration.getEntityByClassName(MCountry.class.getName());
	countryComplexEntity = configuration.getEntityByClassName(MCountryComplex.class.getName());
	localityEntity = configuration.getEntityByClassName(MLocality.class.getName());
	cityEntity = configuration.getEntityByClassName(MCity.class.getName());
	
	
	dialect = new GenericDialect();
	builder = new QueryBuilder(ConfigurationRegister.getConfiguration());
	generator = new SQLGenerator(dialect);
	
	init = true;

    }
  
    @Test
    public void testSimpleEntity() {
	
	selectTemplate = builder.createLoadTemplate(countryEntity);
	sql = generator.getLoadSQL(selectTemplate);
	println("\nCountry: LOAD QUERY");
	println(LINE_SEPARATOR);
	println(sql);
	
	selectTemplate = builder.createSelectTemplate(countryEntity);
	sql = generator.getSelectSQL(selectTemplate);
	println("\nCountry: SELECT QUERY:");
	println(LINE_SEPARATOR);
	println(sql);
	
	insertTemplate = builder.createInsertTemplate(countryEntity);
	sql = generator.getInsertSQL(insertTemplate);
	println("\nCountry: INSERT QUERY:");
	println(LINE_SEPARATOR);
	println(sql);

	updateTemplate = builder.createUpdateTemplate(countryEntity);
	sql = generator.getUpdateSQL(updateTemplate);
	println("\nCountry: UPDATE QUERY:");
	println(LINE_SEPARATOR);
	println(sql);

	deleteTemplate = builder.createDeleteTemplate(countryEntity);
	sql = generator.getDeleteSQL(deleteTemplate);
	println("\nCountry: DELETE QUERY:");
	println(LINE_SEPARATOR);
	println(sql);

    }

    public void testCompositeEntity() {
	selectTemplate = builder.createLoadTemplate(countryComplexEntity);
	sql = generator.getLoadSQL(selectTemplate);
	println("\nCountryComplex: LOAD QUERY");
	println(LINE_SEPARATOR);
	println(sql);

	selectTemplate = builder.createSelectTemplate(countryComplexEntity);
	sql = generator.getSelectSQL(selectTemplate);
	println("\nCountryComplex: SELECT QUERY:");
	println(LINE_SEPARATOR);
	println(sql);

	insertTemplate = builder.createInsertTemplate(countryComplexEntity);
	sql = generator.getInsertSQL(insertTemplate);
	println("\nCountryComplex: INSERT QUERY:");
	println(LINE_SEPARATOR);
	println(sql);

	updateTemplate = builder.createUpdateTemplate(countryComplexEntity);
	sql = generator.getUpdateSQL(updateTemplate);
	println("\nCountryComplex: UPDATE QUERY:");
	println(LINE_SEPARATOR);
	println(sql);

	deleteTemplate = builder.createDeleteTemplate(countryComplexEntity);
	sql = generator.getDeleteSQL(deleteTemplate);
	println("\nCountryComplex: DELETE QUERY:");
	println(LINE_SEPARATOR);
	println(sql);
	
    }
    
    public void testHierarchyEntity() {
	selectTemplate = builder.createLoadTemplate(cityEntity);
	sql = generator.getLoadSQL(selectTemplate);
	println("\nCity: LOAD QUERY");
	println(LINE_SEPARATOR);
	println(sql);

	selectTemplate = builder.createSelectTemplate(cityEntity);
	sql = generator.getSelectSQL(selectTemplate);
	println("\nCity: SELECT QUERY");
	println(LINE_SEPARATOR);
	println(sql);

	insertTemplate = builder.createInsertTemplate(localityEntity);
	sql = generator.getInsertSQL(insertTemplate);
	println("\nLocality: INSERT QUERY");
	println(LINE_SEPARATOR);
	println(sql);

	insertTemplate = builder.createInsertTemplate(cityEntity);
	sql = generator.getInsertSQL(insertTemplate);
	println("\nCity: INSERT QUERY");
	println(LINE_SEPARATOR);
	println(sql);
	
    }
    

}
