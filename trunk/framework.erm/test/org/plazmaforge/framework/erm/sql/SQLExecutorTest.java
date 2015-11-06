package org.plazmaforge.framework.erm.sql;


import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.plazmaforge.framework.erm.BaseTestCase;
import org.plazmaforge.framework.erm.LoadMode;
import org.plazmaforge.framework.core.criteria.Criteria;
import org.plazmaforge.framework.core.criteria.Order;
import org.plazmaforge.framework.erm.model.MCity;
import org.plazmaforge.framework.erm.model.MCountry;
import org.plazmaforge.framework.erm.model.MCountryComplex;
import org.plazmaforge.framework.erm.model.MDefPartnerPhone;
import org.plazmaforge.framework.erm.model.MDefPhone;
import org.plazmaforge.framework.erm.model.MFigure;
import org.plazmaforge.framework.erm.model.MPartner;
import org.plazmaforge.framework.erm.model.MPerson;
import org.plazmaforge.framework.erm.model.MRectangle;
import org.plazmaforge.framework.erm.model.MRole;
import org.plazmaforge.framework.erm.model.MUser;
import org.plazmaforge.framework.erm.model.MUserRole;
import org.plazmaforge.framework.erm.model.ModelHelper;
import org.plazmaforge.framework.sql.dialect.Dialect;
import org.plazmaforge.framework.sql.dialect.DialectFactory;

public class SQLExecutorTest extends BaseTestCase {

    private SQLExecutor sqlExecutor;
    private boolean init;
    
    protected void setUp() throws Exception {
	
	////////////////////////////////////////////////////////////////////////
	// INITIALIZE MAPPING
	////////////////////////////////////////////////////////////////////////
	if (init) {
	    return;
	}
	
	ModelHelper.createConfiguration();
	
	try {
	    Dialect dialect = DialectFactory.getDialect(getConnection()); 
	    SQLEntityManager entityManager = new SQLEntityManager(null);
	    entityManager.setDialect(dialect);
	    sqlExecutor = entityManager.getExecutor();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	init = true;
    }

    
    @Test
    public void testSimpleLoadSelectEntity() throws Exception {
	Connection cn = getConnection();
	Integer id = 1;
	MCountry country = sqlExecutor.loadById(cn, MCountry.class, id);
	assertNotNull(country);
	println(LINE_SEPARATOR);
	println(country);
    }

    @Test
    public void testSimpleSelectEntity() throws Exception {
	Connection cn = getConnection();
	Criteria criteria = new Criteria();
	
	//Filter filter = new Filter("code", "BO", "=");
	//criteria.addCondition(filter);
	
	//Order order = new Order("name");
	//criteria.addOrder(order);
	
	criteria.setOffset(20);
	criteria.setLimit(10);
	
	List<MCountry> countries = sqlExecutor.findAll(cn, MCountry.class, criteria);
	assertNotNull(countries);
	int count = countries.size();
	MCountry country = null;
	println(LINE_SEPARATOR);
	for (int i = 0; i < count; i++) {
	    country = (MCountry) countries.get(i);
	    println(country);
	}
    }

    @Test
    public void testSimpleUpdateEntity() throws Exception {
	Connection cn = getConnection();
	Integer id = 1;
	MCountry country = (MCountry) sqlExecutor.loadById(cn, MCountry.class, id);
	assertNotNull(country);
	
	String name = country.getName();
	String oldName = name;
	String newName = null;

	int count = 3;
	println(LINE_SEPARATOR);
	for (int i = 1; i <= count; i++) {
	    
	    // Store old values
	    oldName = country.getName();
	    
	    // Generate new values
	    newName = "Name" + i;
	    
	    // Set new values
	    country.setName(newName);
	    
	    // Update country
	    sqlExecutor.update(cn, country);
	    
	    // Reload country
	    country = (MCountry) sqlExecutor.loadById(cn, MCountry.class, id);
	    assertNotNull(country);
	    assertEquals(newName, country.getName());
	    
	    println("Update country: name=[" + oldName + "]->[" + newName + "]");
	}

	// Restore values
	country.setName(name);
	
	sqlExecutor.update(cn, country);
	
    }

    @Test
    public void testSimpleInsertEntity() throws Exception {
	Connection cn = getConnection();

	MCountry country = new MCountry();
	country.setCode("NewCode");
	country.setName("NewName");
	
	Integer id = (Integer) sqlExecutor.insert(cn, country);
	assertNotNull(id);
	country = (MCountry) sqlExecutor.loadById(cn, MCountry.class, id);
	assertNotNull(country);
	assertEquals(id, country.getId());
	assertEquals("NewCode", country.getCode());
	assertEquals("NewName", country.getName());
    }

    @Test
    public void testSimpleSaveEntity() throws Exception {
	Connection cn = getConnection();

	MCountry country = new MCountry();
	country.setCode("NewCode");
	country.setName("NewName");
	
	sqlExecutor.save(cn, country);
	Integer id = country.getId(); 
	assertNotNull(id);
	country = (MCountry) sqlExecutor.loadById(cn, MCountry.class, id);
	assertNotNull(country);
	assertEquals(id, country.getId());
	assertEquals("NewCode", country.getCode());
	assertEquals("NewName", country.getName());
	
	country.setCode("NewCode-2");
	country.setName("NewName-2");
	sqlExecutor.save(cn, country);
	
	country = (MCountry) sqlExecutor.loadById(cn, MCountry.class, id);
	assertNotNull(country);
	assertEquals(id, country.getId());
	assertEquals("NewCode-2", country.getCode());
	assertEquals("NewName-2", country.getName());
    }
    
    
    @Test
    public void testSimpleDeleteEntity() throws Exception {
	Connection cn = getConnection();

	MCountry country = new MCountry();
	country.setCode("NewCode");
	country.setName("NewName");
	
	Integer id = (Integer) sqlExecutor.insert(cn, country);
	assertNotNull(id);
	country = (MCountry) sqlExecutor.loadById(cn, MCountry.class, id);
	assertNotNull(country);
	assertEquals(id, country.getId());
	
	sqlExecutor.delete(cn, country);
	
	country = (MCountry) sqlExecutor.loadById(cn, MCountry.class, id);
	assertNull(country); // Not found - null
	
    }
    
    @Test
    public void testHierarchyLoadEntity() throws Exception {
	// City extends Locality
	Connection cn = getConnection();
	Integer id = 1;
	MCity city = (MCity) sqlExecutor.loadById(cn, MCity.class, id);
	assertNotNull(city);
	println(LINE_SEPARATOR);
	println(city);
    }

    @Test
    public void testHierarchySelectEntity() throws Exception {
	// City extends Locality
	Connection cn = getConnection();
	Criteria criteria = new Criteria();
	//Filter filter = new Filter("country.code", "CA", "=");
	//criteria.addCondition(filter);
	
	Order order = new Order("country.code", true);
	criteria.addOrder(order);
	
	List cities = sqlExecutor.findAll(cn, MCity.class, criteria);
	assertNotNull(cities);
	int count = cities.size();
	MCity city = null;
	println(LINE_SEPARATOR);
	for (int i = 0; i < count; i++) {
	    city = (MCity) cities.get(i);
	    println(city);
	}
    }

    @Test
    public void testHierarchyUpdateEntity() throws Exception {
	// City extends Locality
	Connection cn = getConnection();
	Integer id = 1;
	MCity city = (MCity) sqlExecutor.loadById(cn, MCity.class, id);
	assertNotNull(city);
	
	String name = city.getName(); // Owner - Locality (Subclass)
	String phoneCode = city.getPhoneCode(); // Owner - City

	String oldName = name;
	String oldPhoneCode = phoneCode;

	String newName = null;
	String newPhoneCode = null;

	int count = 3;
	println(LINE_SEPARATOR);
	for (int i = 1; i <= count; i++) {
	    
	    // Store old values
	    oldName = city.getName();
	    oldPhoneCode = city.getPhoneCode();
	    
	    // Generate new values
	    newName = "Name" + i;
	    newPhoneCode = "123" + i;
	    
	    // Set new values
	    city.setName(newName);
	    city.setPhoneCode(newPhoneCode);
	    
	    // Update city
	    sqlExecutor.update(cn, city);
	    
	    // Reload city
	    city = (MCity) sqlExecutor.loadById(cn, MCity.class, id);
	    assertNotNull(city);
	    
	    assertEquals(newName, city.getName());
	    assertEquals(newPhoneCode, city.getPhoneCode());
	    
	    println("Update city: name=[" + oldName + "]->[" + newName + 
		    "], phoneCode=[" + oldPhoneCode + "]->[" + newPhoneCode + "]");
	}

	// Restore values
	city.setName(name);
	city.setPhoneCode(phoneCode);
	
	sqlExecutor.update(cn, city);
	
    }

    @Test
    public void testHierarchyInsertEntity() throws Exception {
	Connection cn = getConnection();

	// Get country (reference)
	MCountry country = (MCountry) sqlExecutor.loadById(cn, MCountry.class, 1);
	
	MCity city = new MCity();
	city.setName("NewName");   // Owner: Locality
	city.setPhoneCode("1234"); // Owner: City
	city.setCountry(country);  // Owner: Country
	city.setLocalityTypeId(1); // Owner: Locality
	
	
	Integer id = (Integer) sqlExecutor.insert(cn, city);
	assertNotNull(id);
	city = (MCity) sqlExecutor.loadById(cn, MCity.class, id);
	assertNotNull(city);
	assertEquals(id, city.getId());
	assertEquals("NewName", city.getName());
	assertEquals("1234", city.getPhoneCode());
    }
    
    
    
    @Test
    public void testHierarchyDeleteEntity() throws Exception {
	Connection cn = getConnection();

	// Get country (reference)
	MCountry country = (MCountry) sqlExecutor.loadById(cn, MCountry.class, 1);
	
	MCity city = new MCity();
	city.setName("NewName");   // Owner: Locality
	city.setPhoneCode("1234"); // Owner: City
	city.setCountry(country);  // Owner: Country
	city.setLocalityTypeId(1); // Owner: Locality
	
	
	Integer id = (Integer) sqlExecutor.insert(cn, city);
	assertNotNull(id);
	city = (MCity) sqlExecutor.loadById(cn, MCity.class, id);
	assertNotNull(city);
	
	
	sqlExecutor.delete(cn, city);
	city = (MCity) sqlExecutor.loadById(cn, MCity.class, id);
	assertNull(city); // Not found - null
	
	
    }
    
    
    @Test
    public void testComposite() throws Exception {

	Connection cn = getConnection();

	// Composite Key
	Object[] ids = new Object[2];
	ids[0] = 1;
	ids[1] = "AD";

	MCountryComplex countryComplex = (MCountryComplex) sqlExecutor.loadById(cn, MCountryComplex.class, ids);

	String name = countryComplex.getComposite().getName();
	countryComplex.getComposite().setName("678-2");
	sqlExecutor.update(cn, countryComplex);
	
	countryComplex = (MCountryComplex) sqlExecutor.loadById(cn, MCountryComplex.class, ids);
	
	println(LINE_SEPARATOR);
	println(countryComplex);

    }
    
    
    @Test
    public void testUserRole() throws Exception {

	Connection cn = getConnection();

	Integer id = 1;
	
	MUser user = (MUser) sqlExecutor.loadById(cn, MUser.class, id);
	
	List users = sqlExecutor.findAll(cn, MUser.class);
	
	
	println(LINE_SEPARATOR);
	for (Object u : users) {
	    println(u);
	}
	
	List forceUsers = sqlExecutor.findAll(cn, MUser.class, LoadMode.FORCE);
	

	List roles = sqlExecutor.findAll(cn, MRole.class);

	println(LINE_SEPARATOR);
	for (Object role : roles) {
	    println(role);
	}
	
	
	List userRoles = sqlExecutor.findAll(cn, MUserRole.class);

	println(LINE_SEPARATOR);
	for (Object userRole : userRoles) {
	    println(userRole);
	}
	
	MRole r = new MRole();
	r.setName("NEW-ROLE");
	
	Serializable rid = sqlExecutor.insert(cn, r);
	
	r = (MRole) sqlExecutor.loadById(cn, MRole.class, rid);
	
	MUserRole ur = new MUserRole();
	ur.setUser(user);
	ur.setRole(r);
	user.addRole(ur);
	
	sqlExecutor.update(cn, user);
	
	user = (MUser) sqlExecutor.loadById(cn, MUser.class, id);
	println(user);
    }

    
    @Test
    public void testDeleteUser() throws Exception {

	Connection cn = getConnection();

	Integer userId = null;
	MUserRole ur = null;
	
	MUser user = new MUser();
	user.setUserName("test-user");
	user.setPassword("test");
	user.setRegisterDate(new Date());
	
	userId = (Integer) sqlExecutor.insert(cn, user);
	user = (MUser) sqlExecutor.loadById(cn, MUser.class, userId);
	assertNotNull(user);
	
	MRole role = new MRole();
	role.setName("test-role-1");
	sqlExecutor.insert(cn, role);
	assertNotNull(role);
	
	ur = new MUserRole();
	ur.setUser(user);
	ur.setRole(role);
	user.addRole(ur);

	role = new MRole();
	role.setName("test-role-2");
	sqlExecutor.insert(cn, role);
	assertNotNull(role);
	
	ur = new MUserRole();
	ur.setUser(user);
	ur.setRole(role);
	user.addRole(ur);

	sqlExecutor.update(cn, user);
	
	MUser testUser = sqlExecutor.loadById(cn, MUser.class, userId);
	assertNotNull(testUser);
	sqlExecutor.delete(cn, testUser);
	
    }

    
    @Test
    public void testSimpleJoinEntity() throws Exception {
	Connection cn = getConnection();
	List<MPartner> partners = sqlExecutor.findAll(cn, MPartner.class);
	assertNotNull(partners);
	int count = partners.size();
	MPartner partner = null;
	println(LINE_SEPARATOR);
	for (int i = 0; i < count; i++) {
	    partner = partners.get(i);
	    println(partner);
	}
    }
    
    @Test
    public void testComplexJoinEntity() throws Exception {
	Connection cn = getConnection();
	List<MPerson> persons = sqlExecutor.findAll(cn, MPerson.class);
	assertNotNull(persons);
	int count = persons.size();
	MPerson person = null;
	println(LINE_SEPARATOR);
	MPerson firstPerson = null;
	for (int i = 0; i < count; i++) {
	    person = persons.get(i);
	    if (firstPerson == null) {
		firstPerson = person;
	    }
	    println(person);
	}
	person = firstPerson;
	Integer id = person.getId();
	
	// TODO
	if (person.getPersonPart() == null) {
	    return;
	}
	
	person.getPersonPart().setRecord("Record-1");
	sqlExecutor.update(cn, person);
	person = sqlExecutor.loadById(cn, MPerson.class, id);
	assertEquals("Record-1", person.getPersonPart().getRecord());
    }
    
    @Test
    public void testDiscriminatorEntity() throws Exception {
	Connection cn = getConnection();
	List<MDefPhone> phones = sqlExecutor.findAll(cn, MDefPhone.class);
	assertNotNull(phones);
	println(LINE_SEPARATOR);
	for (MDefPhone phone : phones) {
	    println(phone);
	}
	
	List<MDefPartnerPhone> partnerPhones = sqlExecutor.findAll(cn, MDefPartnerPhone.class);
	assertNotNull(partnerPhones);
	println(LINE_SEPARATOR);
	for (MDefPhone phone : partnerPhones) {
	    println(phone);
	}
    }
    
    /*
    public void testDiscriminatorFigure() throws Exception {
	Connection cn = getConnection();
	List<MFigure> figures = sqlExecutor.select(cn, MFigure.class);
	assertNotNull(figures);
	println(LINE_SEPARATOR);
	MFigure figure1 = null;
	for (MFigure figure : figures) {
	    if (figure1 == null) {
		figure1 = figure;
	    }
	    println(figure);
	}
	figure1.setName("Test-1");
	sqlExecutor.update(cn, figure1);
	MFigure figure = sqlExecutor.load(cn, MFigure.class, figure1.getId());
	println(figure);
	
	List<MRectangle> rectangles = sqlExecutor.select(cn, MRectangle.class);
	assertNotNull(figures);
	println(LINE_SEPARATOR);
	MRectangle rectangle1 = null;
	for (MRectangle rectangle : rectangles) {
	    if (rectangle1 == null) {
		rectangle1 = rectangle;
	    }
	    println(rectangle);
	}
	rectangle1.setName("New-Rectangle-1");
	sqlExecutor.update(cn, rectangle1);
	MRectangle rectangle = sqlExecutor.load(cn, MRectangle.class, rectangle1.getId());
	println(rectangle);
	
	MRectangle newRectangle = new MRectangle();
	newRectangle.setName("FOR-RECTANGLE");
	Serializable newId = sqlExecutor.insert(cn, newRectangle);
	
	rectangle = sqlExecutor.load(cn, MRectangle.class, newId);
	println(rectangle);
	
	sqlExecutor.delete(cn, rectangle);
	rectangle = sqlExecutor.load(cn, MRectangle.class, newId);
	assertNull(rectangle);
	
    }
    */
}
