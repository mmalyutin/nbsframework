/*
 * Copyright (C) 2012-2013 Oleh Hapon ohapon@users.sourceforge.net
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * Oleh Hapon
 * Kyiv, UKRAINE
 * ohapon@users.sourceforge.net
 */
package org.plazmaforge.framework.util;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.plazmaforge.framework.util.StringUtils;

/**
 * 
 * @author ohapon
 *
 */
public class PropertiesStore implements IPropertiesStore {

    private static final long serialVersionUID = -4342200071580779850L;


    public static final String DEFAULT_KEY = "NewKey";
    
    
    public static final String DEFAULT_VALUE = "*New Value";
    
    
    /** Current locale **/
    private Locale locale;
    
    /** Base name (common) **/
    private String baseName;
    
    /** Current Bundle name **/
    private String bundleName;
    
    /** Folder name (common) **/
    private String folderName;    
    
    
    /** Array of all locales **/
    private List<Locale> locales;
    
   
    
    /** Set of all keys **/
    private Set<String> keys;
    
    private Map<String, PropertyElement> keyMap;
    
    /** Property elements **/
    private List<PropertyElement> propertyElements;
    
    
    private Map<String, LocalePropertiesStore> localeStores;

    
    private boolean modify;
    
    private boolean autoAddLocale;
    
    
    public List<Locale> getLocales() {
	if (locales == null) {
	    locales = new ArrayList<Locale>();
	}
        return locales;
    }

   

    
    public void addLocale(Locale locale, Locale baseLocale) {
	if (locale == null) {
	    throw new IllegalArgumentException("Locale must be not null");
	}
	
	LocalePropertiesStore propertiesStore = _addLocale(locale);
	
	if (baseLocale == null) {
	    return;
	}
	LocalePropertiesStore basePropertiesStore = getLocaleStore(baseLocale);
	if (basePropertiesStore == null) {
	    return;
	}
	Set<String> keys = getKeys();
	for (String key : keys) {
	    PropertyValue pv = basePropertiesStore.getPropertyValue(key);
	    if (pv == null) {
		continue;
	    }
	    String value = pv.getValue();
	    //String displayValue = pv.getDisplayValue();
	    propertiesStore.setValue(key, value, null);
	    
	}
    }

    public void addLocale(Locale locale) {
	addLocale(locale, null);
    }
    
    /**
     * Add locale
     * @param locale
     * @return <code>LocalePropertiseStore</code>
     */
    private LocalePropertiesStore _addLocale(Locale locale) {
	getLocales().add(locale);
	LocalePropertiesStore propertiesStore = new LocalePropertiesStore(locale);
	getLocaleStores().put(getLocaleKey(locale), propertiesStore);
	return propertiesStore;
    }

    private String getLocaleKey(Locale locale) {
	return locale == null ? null : locale.toString(); 
    }

    
    public Set<String> getKeys() {
	if (keys == null) {
	    keys = new HashSet<String>();
	}
	return keys;
    }
    
    public Map<String, PropertyElement> getKeyMap() {
	if (keyMap == null) {
	    keyMap = new HashMap<String, PropertyElement>();
	}
	return keyMap;
    }
    
    public boolean containsKey(String key) {
	return getKeys().contains(key);
    }
    
    
    public void renameKey(String oldKey, String newKey) {
	
	if (containsKey(newKey)) {
	    throw new IllegalArgumentException("Duplicate key: " + newKey);
	}
	
	if (getKeyMap().get(newKey) != null) {
	    throw new IllegalArgumentException("Duplicate key: " + newKey);
	}
	
	getKeys().remove(oldKey);
	getKeys().add(newKey);
	
	PropertyElement element = getKeyMap().get(oldKey);
	getKeyMap().remove(oldKey);
	getKeyMap().put(newKey, element);

	
	List<Locale> locales = getLocales();
	for (Locale locale : locales) {
	    LocalePropertiesStore localeStore = getLocaleStore(locale);
	    localeStore.renameKey(oldKey, newKey);
	}
	
    }
    
    public PropertyValue getPropertyValue(Locale locale, String key) {
	LocalePropertiesStore ls = getLocaleStore(locale);
	if (ls == null) {
	    return null;
	}
	return ls.getPropertyValue(key);
    }
    
    public LocalePropertiesStore getLocaleStore(Locale locale) {
	LocalePropertiesStore localePropertiesStore = getLocaleStores().get(getLocaleKey(locale));
	if (localePropertiesStore != null) {
	    return localePropertiesStore;
	}
	if (!autoAddLocale) {
	    return null;
	}
	localePropertiesStore = _addLocale(locale);
	return localePropertiesStore;
    }

    
    public LocalePropertiesStore getLocaleStore(String localeKey) {
	return getLocaleStores().get(localeKey);
    }

    public LocalePropertiesStore getLocaleStore(int index) {
	Locale locale = getLocales().get(index);
	return getLocaleStore(locale);
    }

    
    public Map<String, LocalePropertiesStore> getLocaleStores() {
	if (localeStores == null) {
	    localeStores = new HashMap<String, LocalePropertiesStore>();
	}
        return localeStores;
    }


    public List<PropertyElement> getPropertyElements() {
	if (propertyElements == null) {
	    propertyElements = new ArrayList<PropertyElement>();
	}
        return propertyElements;
    }


    
    public void addPropertyElement(PropertyElement element) {
	if (element == null) {
	    throw new IllegalArgumentException("PropertyElement must be not null");
	}
	String key = element.getKey();
	if (key == null) {
	    throw new IllegalArgumentException("Key must be not null");
	}
	
	getPropertyElements().add(element);
	getKeys().add(key);
	getKeyMap().put(key, element);
    }
    
    public void removePropertyElement(PropertyElement element) {
	if (element == null) {
	    throw new IllegalArgumentException("PropertyElement must be not null");
	}
	String key = element.getKey();
	if (key == null) {
	    throw new IllegalArgumentException("Key must be not null");
	}
	
	getPropertyElements().remove(element);
	getKeys().remove(key);
	getKeyMap().remove(key);
    }

    
    ////
    
    public String getValue(Locale locale, String key) {
	PropertyValue propertyValue = getPropertyValue(locale, key); 
	return propertyValue == null ? null : propertyValue.getValue();
    }
    
    public String getDisplayValue(Locale locale, String key) {
	PropertyValue propertyValue = getPropertyValue(locale, key); 
	return propertyValue == null ? null : propertyValue.getDisplayValue();
    }

    
    public void setValue(Locale locale, String key, String value) {
	setValue(locale, key, value, null);
    }
    
    
    public void setValue(Locale locale, String key, String value, List<String> comments) {
	PropertyElement element = null;
	if (containsKey(key)) {
	    element = getKeyMap().get(key);
	    setValue(locale, element, value, comments);
	    return;
	}
	
	element = new PropertyElement(this, key);
	setValue(locale, element, value, comments);
	
	addPropertyElement(element);
    }

    protected void setValue(Locale locale, PropertyElement element, String value, List<String> comments) {
	if (locale == null) {
	    throw new IllegalArgumentException("Locale must be not null");
	}
	if (element == null) {
	    throw new IllegalArgumentException("PropertyElement must be not null");
	}
	LocalePropertiesStore localeStore = getLocaleStore(locale);
	if (localeStore == null) {
	    throw new IllegalArgumentException("Locale \"" + locale.toString() + "\" not found in PropertiesStore");
	}
	PropertyValue propertyValue = localeStore.getPropertyValue(element.getKey());
	propertyValue.setValue(value);
	setElementComments(propertyValue, comments);
	propertyValue.loadValue();
    }

    
    
    
    public void setDisplayValue(Locale locale, String key, String value) {
	setDisplayValue(locale, key, value, null, false);
    }
    
    public void setDisplayValue(Locale locale, String key, String value, boolean forceModify) {
	setDisplayValue(locale, key, value, null, forceModify);
    }
 
    // NEW
    public void setDisplayValue(Locale locale, String key, String value, List<String> comments, boolean forceModify) {
	PropertyElement element = null;
	if (containsKey(key)) {
	    element = getKeyMap().get(key);
	    setDisplayValue(locale, element, value, comments, forceModify);
	    return;
	}
	
	element = new PropertyElement(this, key);
	setDisplayValue(locale, element, value, comments, forceModify);
	
	addPropertyElement(element);
    }

    
    
    // NEW
    protected void setDisplayValue(Locale locale, PropertyElement element, String value, List<String> comments, boolean forceModify) {
	if (locale == null) {
	    throw new IllegalArgumentException("Locale must be not null");
	}
	if (element == null) {
	    throw new IllegalArgumentException("PropertyElement must be not null");
	}
	LocalePropertiesStore localeStore = getLocaleStore(locale);
	if (localeStore == null) {
	    throw new IllegalArgumentException("Locale \"" + locale.toString() + "\" not found in PropertiesStore");
	}
	PropertyValue propertyValue = localeStore.getPropertyValue(element.getKey());
	propertyValue.setDisplayValue(value);
	setElementComments(propertyValue, comments);
	propertyValue.saveValue();
	if (forceModify) {
	    localeStore.setModify(true);
	}
    }
    
    //////////////////////////////////////////////// HELPERS ///////////////////////////////////////////////////////////
    
    protected PropertyValue getPropertyValue(Locale locale, PropertyElement element) {
	LocalePropertiesStore localeStore = getLocaleStore(locale);
	return localeStore.getPropertyValue(element.getKey());
    }
    
    protected void setElementComments(PropertyValue propertyValue, List<String> comments) {
	if (propertyValue == null || comments == null || comments.isEmpty()) {
	    return;
	}
	propertyValue.setElementComments(comments);
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public String generateNewKey(String keyPrefix) {
	keyPrefix = isEmpty(keyPrefix) ? DEFAULT_KEY: keyPrefix; 
	Set<String> keys = getKeys();
	int i = 1;
	String newKey = keyPrefix + i;
	while (true) {
	    if (i > 1000000000) {
		return keyPrefix + "7777777777"; // STUB: Bad solution :-)
	    }
	    if (!keys.contains(newKey)) {
		return newKey; 
	    }
	    i++;
	    newKey = keyPrefix + i;
	}
    }
    
    public String generateNewValue(String valuePrefix) {
	valuePrefix = isEmpty(valuePrefix) ? DEFAULT_VALUE: valuePrefix;
	return valuePrefix; 
    }
    
    public String generateNewKey() {
	return generateNewKey(DEFAULT_KEY);
    }
    
    public String generateNewValue() {
	return generateNewValue(DEFAULT_VALUE);
    }
    
    
    public String[] generateNewKeyAndValue(String keyPrefix, String valuePrefix) {
	
	keyPrefix = isEmpty(keyPrefix) ? DEFAULT_KEY: keyPrefix;
	valuePrefix = isEmpty(valuePrefix) ? DEFAULT_VALUE: valuePrefix;
	
	String[] keyAndValue = new String[2];
	String key = generateNewKey(keyPrefix);
	String value = generateNewValue(valuePrefix);
	int len = keyPrefix.length(); 
	if (len < key.length()) {
	    value = value + key.substring(len);
	}
	keyAndValue[0] = key;
	keyAndValue[1] = value;
	
	return keyAndValue;
    }

    public String[] generateNewKeyAndValue() {
	return generateNewKeyAndValue(DEFAULT_KEY, DEFAULT_VALUE);
    }
    
    protected boolean isEmpty(String str) {
	return StringUtils.isEmpty(str);
    }


    public boolean isModify() {
        return modify;
    }

    public void setModify(boolean modify) {
        this.modify = modify;
    }
    
    public void resetModify() {
	setModify(false);
	List<Locale> locales = getLocales();
	for (Locale l : locales) {
	    getLocaleStore(l).setModify(false);
	}
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void setLocalePropertiesText(Locale locale, String text) {
	
	if (text == null) {
	    return;
	}
	
	LocalePropertiesStore localePropertiesStore = getLocaleStore(locale);
	
	List<String> titleComments = new ArrayList<String>();
	List<String> elementComments = null;
	
	try {
	    
	   

	    LineNumberReader reader = new LineNumberReader(new StringReader(text));
	    
	    boolean isLoadElement = false;
		
		
	    String line = null;
	    while ((line = reader.readLine()) != null) {

		line = line.trim();

		// If line is empty or line is comment (#) 
		// then add line to comment storage
		
		if (line.length() == 0 || line.startsWith("#")) {
		    
		    // If any element was loaded then we don't add comment line
		    // because it is not common comment
		    
		    // We add first comment to title comment 
		    // because when we will delete first element we lost first (title) comment 
		    
		    if (isLoadElement) {
			if (elementComments == null) {
			    elementComments = new ArrayList<String>();
			}
			elementComments.add(line);
		    } else {
			titleComments.add(line);
		    }
		    continue;
		}

		isLoadElement = true;

		String key = "";
		String value = "";
		int len = line.length();
		int index = line.indexOf("=");
		if (index > -1) {
		    key = line.substring(0, index).trim();
		    if (index + 1 < len) {
			value = line.substring(index + 1).trim();
		    }
		} else {
		    key = line.trim();
		}
		setValue(locale, key, value, elementComments);
		
		// Reset comment of element
		elementComments = null;
	    }
	    
	    
	} catch (IOException ex) {
	    // TODO
	} finally {
	    
	    if (localePropertiesStore != null) {
		
		// Set common title comments
		localePropertiesStore.setTitleComments(titleComments);
		
		
		// Set common summary comments
		localePropertiesStore.setSummaryComments(elementComments);		
		
	    }
	}
	
    }
    
    public String getLocalePropertiesText(Locale locale) {
	return  getLocalePropertiesText(locale, false);
    }
    
    
    public String getLocalePropertiesText(Locale locale, boolean isDisplay) {
	
	LocalePropertiesStore localePropertiesStore = getLocaleStore(locale);
	
	List<String> storageTitleComments = null;
	
	List<String> storageSummaryComments = null;
	
	if (localePropertiesStore != null) {
	    storageTitleComments = localePropertiesStore.getTitleComments();
	    storageSummaryComments = localePropertiesStore.getSummaryComments();
	}
	
	
	StringBuffer buf = new StringBuffer();
	
	// Generate common title comments
	if (storageTitleComments != null && storageTitleComments.size() > 0) {
	    for (String line : storageTitleComments) {
		buf.append(line + "\n");
	    }
	}

	List<PropertyElement> propertyElements = getPropertyElements();
	
	// If no elements then return
	if (propertyElements == null || propertyElements.size() == 0) {
	    return buf.toString();
	}
	
	// Generate property lines
	List<String> elementComments = null;
	for (PropertyElement element : propertyElements) {
	    
	    elementComments = element.getElementComments(locale);
	    if (elementComments != null && elementComments.size() > 0) {
		for (String comment : elementComments) {
		    buf.append(comment + "\n");
		}
	    }
	    
	    String line = isDisplay ? element.getPropertyDisplayText(locale) : element.getPropertyText(locale);
	    if (!element.isValid()) {
		continue;
	    }
	    
	    
	    buf.append(line + "\n");
	}
	
	// Generate common summary comments
	if (storageSummaryComments != null && storageSummaryComments.size() > 0) {
	    for (String line : storageSummaryComments) {
		buf.append(line + "\n");
	    }
	}
	
	return buf.toString();
    }


    ////


    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public String getBundleName() {
        return bundleName;
    }

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }
    
    ////
    
    public void initLocales(String absoluteFileName) {
	initLocales(absoluteFileName, true);
    }
	
    public void initLocales(String absoluteFileName, boolean forceLoadOtherLocales) {
	
	String fileName = absoluteFileName; 
	folderName = "";
	
	String[] path = FileUtils.parseFileName(fileName);
	if  (folderName != null) {
	    folderName = path[0];
	}
	fileName = path[1];
	
	bundleName = BundleUtils.getBundleName(fileName);
	
	baseName = bundleName;
	String localeName = "";
	int index = BundleUtils.indexOfStartLocale(bundleName);
	if (index > -1) {
	    localeName = bundleName.substring(index + 1);
	    baseName = bundleName.substring(0, index);
	}
	
	
        this.locale = BundleUtils.getLocaleByName(localeName);
        
        addLocale(this.locale);
        
        /////////////////////////////////////////////////////////////////////////////////////
        if (forceLoadOtherLocales) {
            List<Locale> ls = findLocales(fileName);
            for (Locale l : ls) {
                addLocale(l);
            }
        }
    
        
    }
    
    private List<Locale> findLocales(String exludeFileName) {
	return BundleUtils.findLocales(folderName, baseName, exludeFileName);
    }
    
    ////
    
    public void load() {
	List<Locale> ls = getLocales();
	if (ls == null || ls.isEmpty()) {
	    return;
	}
	String folderName = getFolderName();
	String baseName = getBaseName();
	
	String text = null;
	for (Locale l: ls) {
	    String afn = BundleUtils.generateAbsoluteFileName(folderName, baseName, l);
	    if (!FileUtils.exists(afn)) { // FILE NOT FOUND
		continue;
	    }
	    text = readTextFromFile(afn);
	    setLocalePropertiesText(l, text);
	}
    }
    
    public void store() {
	
	List<Locale> ls = getLocales();
	if (ls == null || ls.isEmpty()) {
	    return;
	}
	
	boolean globalModify = isModify();
	
	String folderName = getFolderName();
	String baseName = getBaseName();

	boolean isEmpty = isKeysEmpty();
	
	for (Locale l: ls) {
	    LocalePropertiesStore lps = getLocaleStore(l);
	    if (!globalModify && !lps.isModify()) {
		continue;
	    }
	    String text = getLocalePropertiesText(l);
	    String afn = BundleUtils.generateAbsoluteFileName(folderName, baseName, l);
	    if (isEmpty && !FileUtils.exists(afn)) { // KEY MAP IS EMPTY AND FILE NOT FOUND
		continue;
	    }
	    writeTextToFile(afn, text);
	}
    }    
    
    
    protected String readTextFromFile(String fileName) {
	try {
	    return FileUtils.readFileAsString(fileName);
	} catch (IOException ex) {
	    ex.printStackTrace();
	}
	return null;
    }
    
    protected void writeTextToFile(String fileName, String text) {
	try {
	    FileUtils.writeFileAsString(fileName, text);
	} catch (IOException ex) {
	    ex.printStackTrace();
	}
    }
    
    public void resetLocale() {
	Locale defaultLocale = Locale.getDefault();
	List<Locale> locales = getLocales();
	if (locales == null || locales.isEmpty()) {
	    return;
	}
	String[] combinations = BundleUtils.getLocaleCombinations(defaultLocale);
	for (String v : combinations) {
	    Locale l = findLocaleByName(locales, v);
	    if (l != null) {
		setLocale(l);
		return;
	    }
	}
    }
    
    private Locale findLocaleByName(List<Locale> locales, String localeName) {
	if (locales == null || localeName == null) {
	    return null;
	}
	for (Locale l: locales) {
	    if (l.toString().equals(localeName)) {
		return l;
	    }
	}
	return null;
    }
    
    public boolean isKeysEmpty() {
	return getKeyMap().isEmpty();
    }
    
    public void sortLocales() {
	if (locales == null) {
	    return;
	}
	BundleUtils.sortLocales(locales);
    }

    public boolean isAutoAddLocale() {
        return autoAddLocale;
    }

    public void setAutoAddLocale(boolean autoAddLocale) {
        this.autoAddLocale = autoAddLocale;
    }
    
    
}
