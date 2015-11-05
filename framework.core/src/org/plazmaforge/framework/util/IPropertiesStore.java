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

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Describes Properties store (files)
 * 
 * @author ohapon
 */
public interface IPropertiesStore extends Serializable {

    /**
     * Return all locales
     * @return
     */
    List<Locale> getLocales();
    
    
    void addLocale(Locale locale);
    
    
    void addLocale(Locale locale, Locale baseLocale);
    
    
    /**
     * Return all keys
     * @return
     */
    Set<String> getKeys();
    
    
    boolean containsKey(String key);
    
    
    void renameKey(String oldKey, String newKey);
    
    
    /**
     * Return value by locale and key (/u1234)
     * @param locale
     * @param key
     * @return
     */
    String getValue(Locale locale, String key);
    
    /**
     * Get real display value by locale and key (convert /u1234 to real string)
     * @param locale
     * @param key
     * @return
     */
    String getDisplayValue(Locale locale, String key);
    
    /**
     * Set value
     * @param locale
     * @param key
     * @param value
     */
    void setValue(Locale locale, String key, String value);
    
    /**
     * Set value
     * @param locale
     * @param key
     * @param value
     * @param comments
     */
    void setValue(Locale locale, String key, String value, List<String> comments);
    
    
    void setDisplayValue(Locale locale, String key, String value);
    
    
    void setDisplayValue(Locale locale, String key, String value, boolean forceModify);
    
    
    LocalePropertiesStore getLocaleStore(Locale locale);

    
    LocalePropertiesStore getLocaleStore(String localeKey);
    
    
    LocalePropertiesStore getLocaleStore(int index);
    
    
    
    List<PropertyElement> getPropertyElements();


    void addPropertyElement(PropertyElement element);
    
    void removePropertyElement(PropertyElement element);
    
    PropertyValue getPropertyValue(Locale locale, String key);
    
    
    
    String generateNewKey();
    
    String generateNewValue();
    
    
    String[] generateNewKeyAndValue();

    
    boolean isModify();

    void setModify(boolean modify);
    
    /**
     * Set modify false for the store and all locales
     */
    void resetModify();

    
    
    void setLocalePropertiesText(Locale locale, String text);
    
    String getLocalePropertiesText(Locale locale);
    
    String getLocalePropertiesText(Locale locale, boolean isDisplay);
    
    
    
    Locale getLocale();

    void setLocale(Locale locale);

    String getBaseName();

    void setBaseName(String baseName);

    String getBundleName();

    void setBundleName(String bundleName);

    String getFolderName();

    void setFolderName(String folderName);
    
    
    
    
    void initLocales(String absoluteFileName);
	
    void initLocales(String absoluteFileName, boolean forceLoadOtherLocales);

    
    void load();
    
    void store();
    
    void resetLocale();
    
    boolean isKeysEmpty();
    
    void sortLocales();

    boolean isAutoAddLocale();

    void setAutoAddLocale(boolean autoAddLocale);

}
