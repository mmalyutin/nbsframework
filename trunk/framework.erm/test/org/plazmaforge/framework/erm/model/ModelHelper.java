package org.plazmaforge.framework.erm.model;

import java.io.InputStream;

import org.plazmaforge.framework.erm.CascadeType;
import org.plazmaforge.framework.erm.Configuration;
import org.plazmaforge.framework.erm.ConfigurationRegister;
import org.plazmaforge.framework.erm.mapping.Attribute;
import org.plazmaforge.framework.erm.mapping.Collection;
import org.plazmaforge.framework.erm.mapping.Composite;
import org.plazmaforge.framework.erm.mapping.CompositeKey;
import org.plazmaforge.framework.erm.mapping.Entity;
import org.plazmaforge.framework.erm.mapping.Entry;
import org.plazmaforge.framework.erm.mapping.JoinedEntity;
import org.plazmaforge.framework.erm.mapping.Key;
import org.plazmaforge.framework.erm.mapping.Mapping;
import org.plazmaforge.framework.erm.mapping.Reference;
import org.plazmaforge.framework.erm.storage.XMLReader;

public class ModelHelper {

    
    public static String[] XML_FILES = {
	"MCountry.xml",
	"MCountryComplex.xml",
	"MLocality.xml",
	"MCity.xml",	
	"MUser.xml",
	"MRole.xml",
	"MUserRole.xml",
	"MDefPhone.xml",
	"MDefPartnerPhone.xml",
	"MPartner.xml",
	"MPerson.xml",
	"MPersonPart.xml",
	"MFigure.xml",
	"Mrectangle.xml"
	};
    
    
    public static InputStream getMappingStream(String fileName) {
	return ModelHelper.class.getResourceAsStream(fileName);
    }
    
    public static void addFileMapping(Mapping mapping, XMLReader reader, String fileName) throws Exception {
	InputStream is = getMappingStream(fileName);
	if (is == null) {
	    throw new RuntimeException("InputStream is empty of file '" + fileName + "'");
	}
	Mapping m = reader.readMapping(fileName, is);
	mapping.addMapping(m);
    }
    
    public static void addFileMappings(Mapping mapping, XMLReader reader, String[] fileNames) throws Exception {
	for (String fileName: fileNames) {
	    addFileMapping(mapping, reader, fileName);
	}
    }

    /*
    public static void createXMLModel() throws Exception {
	XMLReader reader = new XMLReader();
	Mapping mapping = new Mapping(); // General mapping container
	String[] files = ModelHelper.XML_FILES;
	addFileMappings(mapping, reader, files);
	
	EntityRegister.clear();
	EntityRegister.addMapping(mapping);
	
    }
    */

    public static Configuration createConfiguration() throws Exception {
	
	////
	ConfigurationRegister.clear();
	////
	
	Configuration configuration = Configuration.create();
	String pkg = "org/plazmaforge/framework/erm/model";
	String[] files = ModelHelper.XML_FILES;
	String[] pathList = new String[files.length];
	for (int i = 0; i < files.length; i++) {
	    pathList[i] = pkg + "/" + files[i]; 
	}
	configuration.addMapping(pathList);
	return configuration;
	
    }
    
    public static Configuration createEmptyConfiguration() throws Exception {

	////
	ConfigurationRegister.clear();
	////
	
	Configuration configuration = Configuration.create();

	return configuration;
    }

    /*
    public static void createSimpleModel() throws Exception {
	createConfiguration();
	//createXMLModel();
	//createDOMModel();
    }
    */
    
    /*
    public static void createDOMModel() throws Exception {

	Entity countryEntity;
	Entity localityEntity;
	JoinedEntity cityEntity;
	Entity countryComplexEntity;
	
	Entity userEntity;
	Entity roleEntity;
	Entity userRoleEntity;

	Entity partnerEntity;
	Entity defPhoneEntity;

	Entity personEntity;
	Entity personPartEntity;

	Attribute attribute = null;
	Key key = null;
	CompositeKey compositeKey = null;
	Reference reference = null;
	Collection collection = null;
	
	// Country
	countryEntity = new Entity();
	countryEntity.setClassName("org.plazmaforge.framework.erm.model.MCountry");
	countryEntity.setTableName("country");
	
	attribute = new Key();
	attribute.setName("id");
	attribute.setColumnName("id");
	countryEntity.addAttribute(attribute);

	attribute = new Attribute();
	attribute.setName("code");
	attribute.setColumnName("code");
	countryEntity.addAttribute(attribute);

	attribute = new Attribute();
	attribute.setName("name");
	attribute.setColumnName("name");
	countryEntity.addAttribute(attribute);
	

	
	
	// Location
	localityEntity = new Entity();
	localityEntity.setClassName("org.plazmaforge.framework.erm.model.MLocality");
	localityEntity.setTableName("locality");

	attribute = new Key();
	attribute.setName("id");
	attribute.setColumnName("id");
	localityEntity.addAttribute(attribute);

	attribute = new Attribute();
	attribute.setName("name");
	attribute.setColumnName("name");
	localityEntity.addAttribute(attribute);
	
	reference = new Reference();
	reference.setName("country");
	reference.setColumnName("country_id");
	reference.setClassName("org.plazmaforge.framework.erm.model.MCountry");
	localityEntity.addAttribute(reference);


	attribute = new Attribute();
	attribute.setName("localityTypeId");
	attribute.setColumnName("locality_type_id");
	localityEntity.addAttribute(attribute);


	// City
	cityEntity = new JoinedEntity();
	cityEntity.setClassName("org.plazmaforge.framework.erm.model.MCity");
	cityEntity.setTableName("city");
	cityEntity.setExtendsClassName("org.plazmaforge.framework.erm.model.MLocality");
	
	attribute = new Key();
	attribute.setName("id");
	attribute.setColumnName("id");
	cityEntity.addAttribute(attribute);


	attribute = new Attribute();
	attribute.setName("phoneCode");
	attribute.setColumnName("phone_code");
	cityEntity.addAttribute(attribute);

	///////////////////////////////////////////////////////////////////////////
	
	// Country Complex (KEY= [id, code])
	countryComplexEntity = new Entity();
	countryComplexEntity.setClassName("org.plazmaforge.framework.erm.model.MCountryComplex");
	countryComplexEntity.setTableName("country");
	
	compositeKey = new CompositeKey();
	
	key = new Key();
	key.setName("id");
	key.setColumnName("id");
	compositeKey.addAttribute(key);
	
	key = new Key();
	key.setName("code");
	key.setColumnName("code");
	compositeKey.addAttribute(key);

	countryComplexEntity.addAttribute(compositeKey); // COMPOSITE KEY
	
	Composite compositeAttribute = new Composite();
	compositeAttribute.setName("composite");
	compositeAttribute.setClassName("org.plazmaforge.framework.erm.model.MComposite");
	
	attribute = new Attribute();
	attribute.setName("name");
	attribute.setColumnName("name");
	compositeAttribute.addAttribute(attribute);
	
	countryComplexEntity.addAttribute(compositeAttribute); // COMPOSITE ATTRIBUTE

	///////////////////////////////////////////////////////////////////////////
	
	// User
	userEntity = new Entity();
	userEntity.setClassName("org.plazmaforge.framework.erm.model.MUser");
	userEntity.setTableName("SYS_USER");
	
	attribute = new Key();
	attribute.setName("id");
	attribute.setColumnName("id");
	userEntity.addAttribute(attribute);

	attribute = new Attribute();
	attribute.setName("userName");
	attribute.setColumnName("USER_NAME");
	userEntity.addAttribute(attribute);

	attribute = new Attribute();
	attribute.setName("password");
	attribute.setColumnName("USER_PASSWORD");
	userEntity.addAttribute(attribute);

	attribute = new Attribute();
	attribute.setName("registerDate");
	attribute.setColumnName("REG_DATE");
	userEntity.addAttribute(attribute);
	
	collection = new Collection();
	collection.setName("roles");
	collection.setJoinAttributeName("user");
	collection.setJoinClassName("org.plazmaforge.framework.erm.model.MUserRole");
	collection.setCascadeType(CascadeType.DELETE);
	userEntity.addAttribute(collection);

	
	
	// Role
	roleEntity = new Entity();
	roleEntity.setClassName("org.plazmaforge.framework.erm.model.MRole");
	roleEntity.setTableName("SYS_ROLE");
	
	attribute = new Key();
	attribute.setName("id");
	attribute.setColumnName("id");
	roleEntity.addAttribute(attribute);

	attribute = new Attribute();
	attribute.setName("name");
	attribute.setColumnName("NAME");
	roleEntity.addAttribute(attribute);

	
	
	// UserRole
	userRoleEntity = new Entity();
	userRoleEntity.setClassName("org.plazmaforge.framework.erm.model.MUserRole");
	userRoleEntity.setTableName("SYS_USER_ROLE");

	attribute = new Key();
	attribute.setName("id");
	attribute.setColumnName("id");
	userRoleEntity.addAttribute(attribute);

	
	reference = new Reference();
	reference.setName("user");
	reference.setColumnName("USER_ID");
	reference.setClassName("org.plazmaforge.framework.erm.model.MUser");
	userRoleEntity.addAttribute(reference);

	reference = new Reference();
	reference.setName("role");
	reference.setColumnName("ROLE_ID");
	reference.setClassName("org.plazmaforge.framework.erm.model.MRole");
	userRoleEntity.addAttribute(reference);
	
	///////////////////////////////////////////////////////////////////////////

	// DefPhone
	defPhoneEntity = new Entity();
	defPhoneEntity.setClassName("org.plazmaforge.framework.erm.model.MDefPhone");
	defPhoneEntity.setTableName("V_DEF_CONTACTABLE_PHONE");
	
	attribute = new Key();
	attribute.setName("id");
	attribute.setColumnName("CONTACTABLE_ID");
	defPhoneEntity.addAttribute(attribute);

	attribute = new Attribute();
	attribute.setName("phoneString");
	attribute.setColumnName("PHONE_STRING");
	defPhoneEntity.addAttribute(attribute);
	
	reference = new Reference();
	reference.setName("contactable");
	reference.setColumnName("CONTACTABLE_ID");
	reference.setClassName("org.plazmaforge.framework.erm.model.MPartner");
	defPhoneEntity.addAttribute(reference);


	// Partner
	partnerEntity = new Entity();
	partnerEntity.setClassName("org.plazmaforge.framework.erm.model.MPartner");
	partnerEntity.setTableName("PARTNER");
	
	attribute = new Key();
	attribute.setName("id");
	attribute.setColumnName("ID");
	partnerEntity.addAttribute(attribute);

	attribute = new Attribute();
	attribute.setName("name");
	attribute.setColumnName("NAME");
	partnerEntity.addAttribute(attribute);

	Entry entry = new Entry();
	entry.setName("phone");
	entry.setClassName("org.plazmaforge.framework.erm.model.MDefPhone");
	entry.setJoinAttributeName("contactable");
	partnerEntity.addAttribute(entry);

	////
	
	
	
	
	
	// Person
	personEntity = new Entity();
	personEntity.setClassName("org.plazmaforge.framework.erm.model.MPerson");
	personEntity.setTableName("PERSON");

	attribute = new Key();
	attribute.setName("id");
	attribute.setColumnName("ID");
	personEntity.addAttribute(attribute);

	attribute = new Attribute();
	attribute.setName("code");
	attribute.setColumnName("CODE");
	personEntity.addAttribute(attribute);

	entry = new Entry();
	entry.setName("personPart");
	entry.setClassName("org.plazmaforge.framework.erm.model.MPersonPart");
	entry.setJoinAttributeName("person");
	personEntity.addAttribute(entry);


	// Person Part
	
	personPartEntity = new Entity();
	personPartEntity.setClassName("org.plazmaforge.framework.erm.model.MPersonPart");
	personPartEntity.setTableName("PERSON_MILITARY");

	attribute = new Key();
	attribute.setName("id");
	attribute.setColumnName("ID");
	personPartEntity.addAttribute(attribute);

	attribute = new Attribute();
	attribute.setName("record");
	attribute.setColumnName("SPECIAL_RECORD");
	personPartEntity.addAttribute(attribute);

	reference = new Reference();
	reference.setName("person");
	reference.setColumnName("PERSON_ID");	
	reference.setClassName("org.plazmaforge.framework.erm.model.MPerson");
	personPartEntity.addAttribute(reference);


	///////////////////////////////////////////////////////////////////////////
		
	EntityRegister.clear();
	EntityRegister.addEntity(countryEntity);
	EntityRegister.addEntity(localityEntity);
	EntityRegister.addEntity(cityEntity);
	EntityRegister.addEntity(countryComplexEntity);
	
	EntityRegister.addEntity(userEntity);
	EntityRegister.addEntity(roleEntity);
	EntityRegister.addEntity(userRoleEntity);
	
	EntityRegister.addEntity(defPhoneEntity);
	EntityRegister.addEntity(partnerEntity);
	
	EntityRegister.addEntity(personEntity);
	EntityRegister.addEntity(personPartEntity);

    }
    */
    
    
}
