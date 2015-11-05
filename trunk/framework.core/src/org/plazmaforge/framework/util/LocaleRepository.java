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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * 
 * @author ohapon
 *
 */
public class LocaleRepository implements Serializable {

    private static final long serialVersionUID = -6478730388725633979L;
    
    private static Locale[] locales;
    

    private static Locale[] createLocales() {

	Locale[] locales = Locale.getAvailableLocales();
	List<Locale> list = new ArrayList<Locale>();
	for (int i = 0; i < locales.length; i++) {
	    list.add(locales[i]);
	}

	Collections.sort(list, new Comparator<Locale>() {
	    public int compare(Locale o1, Locale o2) {
		return o1.toString().compareTo(o2.toString());
	    }

	});
	return (Locale[]) list.toArray(new Locale[list.size()]);
    }

    
    /**
     * Return sorted locales
     * @return
     */
    public static Locale[] getSortedLocales() {
	if (locales == null) {
	    locales = createLocales();
	}
	return locales;
    }

    public static LocalePartInfo[] getCountries() {
	return getCountries(false);
    }
    
    public static LocalePartInfo[] getCountries(boolean useBlankCountry) {
        Set<LocalePartInfo> countriesSet = new HashSet<LocalePartInfo>();
        if (useBlankCountry) {
            countriesSet.add(new LocalePartInfo("", "(none)"));
        }
        Locale locales[] = getSortedLocales();
	for (int i = 0; i < locales.length; i++) {
	    Locale locale = locales[i];
	    countriesSet.add(new LocalePartInfo(locale.getCountry(), locale.getDisplayCountry()));
	}
        LocalePartInfo[] countries = (LocalePartInfo[])countriesSet.toArray(new LocalePartInfo[countriesSet.size()]);
        Arrays.sort(countries);
        return countries;
    }

    
    public static LocalePartInfo[] getLanguages() {
	return getLanguages(false);
    }
    
    public static LocalePartInfo[] getLanguages(boolean useBlankLanguage) {
	Set<LocalePartInfo> languagesSet = new HashSet<LocalePartInfo>();
        if (useBlankLanguage) {
            languagesSet.add(new LocalePartInfo("", "(none)"));
        }
        Locale locales[] = getSortedLocales();
	for (int i = 0; i < locales.length; i++) {
	    Locale locale = locales[i];
	    languagesSet.add(new LocalePartInfo(locale.getLanguage(), locale.getDisplayLanguage()));
	}
        LocalePartInfo[] languages = (LocalePartInfo[])languagesSet.toArray(new LocalePartInfo[languagesSet.size()]);
        Arrays.sort(languages);
        return languages;
    }
    
    public static int indexOfName(String name, LocalePartInfo[] array) {
	if (name == null || array == null) {
	    return -1;
	}
	for (int i = 0; i < array.length; i++) {
	    if (name.equals(array[i].getName())) {
		return i;
	    }
	}
	return -1;
    }

    
}
