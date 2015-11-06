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

package org.plazmaforge.framework.config.configurer.xml;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.jdom.Document;
import org.jdom.Element;
import org.plazmaforge.framework.config.AppConfiguration;
import org.plazmaforge.framework.config.ConfigurerManager;
import org.plazmaforge.framework.config.configurer.MenuBarConfigurer;
import org.plazmaforge.framework.config.object.IMenuBarConfig;
import org.plazmaforge.framework.config.object.IMenuConfig;
import org.plazmaforge.framework.config.object.IMenuContainerConfig;
import org.plazmaforge.framework.config.object.IMenuItemConfig;
import org.plazmaforge.framework.config.object.IMenuSeparatorConfig;
import org.plazmaforge.framework.config.object.MenuBarConfig;
import org.plazmaforge.framework.config.object.MenuConfig;
import org.plazmaforge.framework.config.object.MenuItemConfig;
import org.plazmaforge.framework.config.object.MenuSeparatorConfig;
import org.plazmaforge.framework.util.IPropertiesStore;

/**
 * 
 * @author ohapon
 *
 */
public class XMLMenuBarConfigurer extends XMLObjectConfigurer<IMenuBarConfig> implements MenuBarConfigurer {

    public static String NAME = "XMLMenuBarConfigurer";

    public static final String XML_ROOT = "menu-bar-config";
    
    public static final String DEFAULT_CONFIG_FILE = XML_ROOT + ".xml";

    public static final String DEFAULT_CONFIG_PROPERTIES = XML_ROOT;
    
    public static final String XML_MENU_BAR = "menu-bar";

    public static final String XML_MENU = "menu";

    public static final String XML_MENU_ITEM = "menu-item";
    
    public static final String XML_MENU_SEPARATOR = "menu-separator";

    public static final String XML_ACTION = "action";

    public static final String XML_TEXT = "text";
    
    public static final String XML_IS_GLOBAL = "global";
    
    private Map<String, IMenuConfig> globalMenuMap;

    public XMLMenuBarConfigurer(AppConfiguration configuration) {
	super(configuration);
	setConfigFileName(createUIConfigFileName(DEFAULT_CONFIG_FILE));
	setPropertiesBaseName(createUIConfigFileName(DEFAULT_CONFIG_PROPERTIES));
    }


    /**
     * Load menu bars from file
     */
    protected void doLoadObjects() throws Exception {
	Element rootElement = getRootElement(getConfigInputStream());
	loadUserInterfaces(rootElement, store);
    }

    /**
     * Store menu bars to file
     */
    protected void doStoreObjects() throws Exception {
	Element root = createRootElement(XML_ROOT);
	Document doc = new Document(root);
	List<IMenuBarConfig> menuBars = getObjects();
	if (menuBars == null) {
	    storeWithNLS(doc, store);
	    return;
	}
	Map<String, List<IMenuBarConfig>> uis = createInterfaces(menuBars);
	if (uis.isEmpty()) {
	    storeMenuBars(root, menuBars, store);
	    storeWithNLS(doc, store);
	    return;
	}
	Element uiElement = null;
	List<Element> children = new ArrayList<Element>();
	Set<String> keys = uis.keySet();
	for (String u : keys) {
	    uiElement = new Element(XML_INTERFACE);
	    children.add(uiElement);
	    setAttribute(uiElement, XML_NAME, u);
	    menuBars = uis.get(u);
	    storeMenuBars(uiElement, menuBars, store);
	}
	root.setContent(children);
	storeWithNLS(doc, store);
    }

    
    
    protected Map<String, List<IMenuBarConfig>> createInterfaces(List<IMenuBarConfig> menuBars) {
	HashMap<String, List<IMenuBarConfig>> uis = new LinkedHashMap<String, List<IMenuBarConfig>>();
	if (menuBars.isEmpty()) {
	    return uis;
	}
	String category = null;
	for (IMenuBarConfig m: menuBars) {
	    category = m.getCategory();
	    if (isEmpty(category)) {
		// We lost MenuBar without category !!!
		continue;
	    }
	    List<IMenuBarConfig> list = uis.get(category);
	    if (list == null) {
		list = new ArrayList<IMenuBarConfig>();
		uis.put(category, list);
	    }
	    list.add(m);
	}
	return uis;
    }
    
    
    protected void storeMenuBars(Element parentElement, List<IMenuBarConfig> menuBars, IPropertiesStore store) {
	if (menuBars == null || menuBars.isEmpty()) {
	    return;
	}
	Element menuBarElement = null;
	List<Element> children = new ArrayList<Element>();
	for (IMenuBarConfig menuBar : menuBars) {
	    
	    menuBarElement = new Element(XML_MENU_BAR);

	    children.add(menuBarElement);

	    setAttributeId(menuBarElement, menuBar.getId());
	    setAttribute(menuBarElement, XML_NAME, menuBar.getName());
	    setAttribute(menuBarElement, XML_TYPE, menuBar.getType());
	    setAttribute(menuBarElement, XML_UI_TYPE, menuBar.getUiType());
	    
	    storeMenus(menuBar, menuBarElement, store);
	    
	    // NLS
	    if (store != null) {
		storeNLSCaption(store, menuBar);
		storeNLSDescription(store, menuBar);
	    }
	}
	parentElement.setContent(children);
	
    }
    
    
    protected void storeMenus(IMenuBarConfig parent, Element parentElement, IPropertiesStore store)  {
	List<IMenuItemConfig> menus = parent.getChildren();
	if (menus == null || menus.size() == 0) {
	    return;
	}
	for (IMenuItemConfig menu : menus) {
	    storeMenu(menu, parentElement, store);
	}
    } 
    
    protected void storeMenu(IMenuItemConfig menu, Element parentElement, IPropertiesStore store) {
	
	Element menuElement = null;
	if (menu instanceof IMenuSeparatorConfig) {
	    
	    // SEPARATOR
	    menuElement = new Element(XML_MENU_SEPARATOR);
	    setAttributeId(menuElement, menu.getId());
	    setAttribute(menuElement, XML_NAME, menu.getName());
	    //setAttribute(menuElement, XML_TEXT, MENU_SEPARATOR);
	    
	} else if (menu instanceof IMenuConfig) {
	    
	    // MENU
	    menuElement = new Element(XML_MENU);
	    setAttributeId(menuElement, menu.getId());
	    setAttribute(menuElement, XML_NAME, menu.getName());
	    
	    IMenuConfig m = (IMenuConfig) menu;
	       
	    // Fixed type of the menu when type is empty
	    if (!m.isGlobal() && isEmpty(menu.getType())) {
		
		// Find a global menu by ID of the menu
		// We can have situation when ID of the current menu equals ID of global menu
		
		IMenuConfig globalMenu = findGlobalMenu(menu.getId());
		if (globalMenu != null) {
		    menu.setType(globalMenu.getId()); 
		}
	    }
	    
	    setBooleanAttribute(menuElement, XML_IS_GLOBAL, m.isGlobal());
	    setAttribute(menuElement, XML_TYPE, menu.getType());
	    
	    // [Global] or [non global and type is null]
	    if (isNLSStorableMenu(m)) {
		storeNLSText(store, menu);
	    }
	    
	    // Store children of menu
	    storeMenuChildren(m, menuElement, store);
	    
	} else {
	    
	    // ITEM
	    menuElement = new Element(XML_MENU_ITEM);
	    setAttributeId(menuElement, menu.getId());
	    setAttribute(menuElement, XML_ACTION, menu.getAction());
	    storeNLSText(store, menu);
	}
	
	parentElement.getChildren().add(menuElement);
    }
    
    
    protected void storeMenuChildren(IMenuConfig parent, Element parentElement, IPropertiesStore store) {
	List<IMenuItemConfig>  children = parent.getChildren();
	if (isEmpty(children)) {
	    return;
	}
	for (IMenuItemConfig menu : children) {
	    storeMenu(menu, parentElement, store);
	}
    }
    
    protected void storeNLSText(IPropertiesStore store, IMenuItemConfig menu) {
	storeNLSText(store, menu, XML_TEXT);
    }
    
    protected void storeNLSText(IPropertiesStore store, IMenuItemConfig menu, String propertyName) {
	setNLSString(store, menu, propertyName, menu.getText());
    }
    
    ////
    
    
    protected void loadUserInterfaces(Element rootElement, IPropertiesStore store) {
	List uiList = getChildren(rootElement, XML_INTERFACE);
	if (isEmpty(uiList)) {
	    loadMenuBars(rootElement, null, store);
	    return;
	}
	Iterator i = uiList.iterator();
	while (i.hasNext()) {
	    Element uiElement = (Element) i.next();
	    String name = uiElement.getAttributeValue(XML_NAME);
	    loadMenuBars(uiElement, name, store);
	}
    }
    
    /**
     * Load MenuBars by UI (category)
     * @param parentElement
     * @param category
     * @param store
     */
    protected void loadMenuBars(Element parentElement, String category, IPropertiesStore store) {
	List barList = getChildren(parentElement, XML_MENU_BAR);
	if (barList == null) {
	    return;
	}
	Iterator i = barList.iterator();
	while (i.hasNext()) {
	    IMenuBarConfig menuBar = new MenuBarConfig();
	    Element menuBarElement = (Element) i.next();

	    String id = getAttributeId(menuBarElement);
	    String name = menuBarElement.getAttributeValue(XML_NAME);
	    String type = menuBarElement.getAttributeValue(XML_TYPE);
	    String uiType = menuBarElement.getAttributeValue(XML_UI_TYPE);

	    menuBar.setId(id);
	    menuBar.setName(name);
	    menuBar.setType(type);
	    menuBar.setUiType(uiType);
	    menuBar.setCategory(category);

	    // NLS
	    if (store != null) {
		loadNLSCaption(store, menuBar);
		loadNLSDescription(store, menuBar);
	    }
	    
	    loadMenus(menuBar, menuBarElement, store);
	    addObject(menuBar);
	}

    }

    protected void loadMenus(IMenuBarConfig parent, Element parentElement,  IPropertiesStore store) {
	List list = getChildren(parentElement, XML_MENU);
	if (list == null) {
	    return;
	}
	Iterator i = list.iterator();
	while (i.hasNext()) {
	    Element menuElement = (Element) i.next();
	    loadMenu(parent, menuElement, store);
	}
    }

    /**
     * Load children of the menu
     * @param parent
     * @param parentElement
     * @param store
     */
    protected void loadMenuChildren(IMenuConfig parent, Element parentElement, IPropertiesStore store) {
	List list = parentElement.getChildren();
	if (list == null) {
	    return;
	}
	Iterator i = list.iterator();
	while (i.hasNext()) {
	    Element menuElement = (Element) i.next();
	    String type = menuElement.getName().trim();

	    if (!(XML_MENU.equals(type)) && !(XML_MENU_ITEM.equals(type)) && !(XML_MENU_SEPARATOR.equals(type))) {
		return;
	    }

	    if (XML_MENU_ITEM.equals(type)) {
		loadMenuItem(parent, menuElement, store);
	    } else if (XML_MENU_SEPARATOR.equals(type)) {
		loadMenuSeparator(parent, menuElement);
	    } else {
		loadMenu(parent, menuElement, store);
	    }
	}
    }

    protected void loadMenu(IMenuContainerConfig parent, Element menuElement, IPropertiesStore store) {
	IMenuConfig menu = new MenuConfig();
	populateMenuItem(menu, menuElement);
	String globalStr = menuElement.getAttributeValue(XML_IS_GLOBAL);
	String type = menuElement.getAttributeValue(XML_TYPE);
	
	menu.setType(type);
	if ("true".equals(globalStr)) {
	    menu.setGlobal(true);
	}
	
	loadNLSText(store, menu);
	if (isEmpty(menu.getText())) {
	    return;
	}
	parent.addChild(menu);
	loadMenuChildren(menu, menuElement, store);
    }

    protected void loadMenuItem(IMenuConfig parent, Element menuElement, IPropertiesStore store) {
	IMenuItemConfig mi = createMenuItem(menuElement);
	populateMenuItem(mi, menuElement);
	parent.addChild(mi);
    }

    protected void loadMenuSeparator(IMenuConfig parent, Element menuElement) {
	    
	// ID of separator is very important because without ID 'equals' method works incorrect
	String id = getAttributeId(menuElement);
	if (isEmpty(id)) {
	    id = generateId();
	}
	MenuSeparatorConfig separator = new MenuSeparatorConfig();
	separator.setId(id);
	parent.addChild(separator);
    }
    
    protected void populateMenuItem(IMenuItemConfig menu, Element menuElement) {
	if (menu == null || menuElement == null) {
	    return;
	}
	menu.setId(getAttributeId(menuElement));
	menu.setName(menuElement.getAttributeValue(XML_NAME));
	menu.setText(menuElement.getAttributeValue(XML_TEXT));
    }

    protected IMenuItemConfig createMenuItem(Element e) {
	IMenuItemConfig mi = new MenuItemConfig();
	mi.setAction(e.getAttributeValue(XML_ACTION));
	return mi;
    }

    protected void loadNLSText(IPropertiesStore store, IMenuConfig menu) {
	if (menu == null) {
	    return;
	}
	String text = null;
	String type = menu.getType();
	boolean hasType = !isEmpty(type);
	
	String key = hasType ? type : menu.getName();

	// NON GLOBAL MENU AND TYPE IS NOT EMPTY: FIND GLOBAL MENU BY TYPE
	if (!menu.isGlobal() && hasType) {
	    IMenuConfig globalMenu = getGlobalMenu(type);
	    if (globalMenu == null) {
		setEmptyMenuText(menu);
		return;
	    } else {
		text = globalMenu.getText();
		if (isEmpty(text)) {
		    setEmptyMenuText(menu);
		    return;
		}
		menu.setText(text);
		return;
	    }
	}
	
	// KEY IS EMPTY
	if (isEmpty(key)) {
	    setEmptyMenuText(menu);
	    return;
	}
	 
	// LOAD TEXT FROM RESOURCE
	if (isEmpty(menu.getText())) {
	    text = getNLSString(store, createPropertyKey(key, XML_TEXT));
	} else {
	    text = menu.getText();
	}
	
	if (isEmpty(text)) {
	    setEmptyMenuText(menu);
	    return;
	}
	
	menu.setText(nullIfEmpty(text));

    }

    protected void setEmptyMenuText(IMenuConfig menu) {
	if (menu == null) {
	    return;
	}
	menu.setText(getEmptyMenuText(menu));
    }

    protected String getEmptyMenuText(IMenuConfig menu) {
	return "<Empty>";
    }
    
    
   
    
    /**
     * Return all menus with type (non global)
     * @return
     */
    public List<IMenuConfig> getMenusWithType() {
	
	List<IMenuConfig> menuList = new ArrayList<IMenuConfig>();
	
	List<IMenuBarConfig> menuBars = getObjects();
	if (menuBars == null || menuBars.isEmpty()) {
	    return menuList;
	}
	for (IMenuBarConfig bar : menuBars) {
	    loadMenusWithType(bar, menuList);
	}
	
	return menuList;
    }
    
    public List<IMenuConfig> getMenusByType(String type) {
	if (isEmpty(type)) {
	    return new ArrayList<IMenuConfig>();
	}
	List<IMenuConfig> menuList = getMenusWithType();
	if (isEmpty(menuList)) {
	    return menuList;
	}
	List<IMenuConfig> resultList = new ArrayList<IMenuConfig>();
	for (IMenuConfig m : menuList) {
	    if (type.equals(m.getType())) {
		resultList.add(m);
	    }
	}
	return resultList;
    }

    public void updateTextByGlobalMenu(IMenuConfig globalMenu) {
	if (globalMenu == null) {
	    throw new IllegalArgumentException("Global Menu must be not null");
	}
	String type = globalMenu.getType();
	if (isEmpty(type)) {
	    throw new IllegalArgumentException("Type of Global Menu must be not empty");
	}
	
	String text = globalMenu.getText();
	// Get non global menus with the type
	List<IMenuConfig> menus = getMenusByType(type);
	for (IMenuConfig m : menus) {
	    m.setText(text);
	}
    }
    
    private void loadMenusWithType(IMenuContainerConfig menuContainer, List<IMenuConfig> menuList) {
	
	if (menuContainer == null) {
	    return;
	}
	
	List<IMenuItemConfig> menus = menuContainer.getChildren();
	
	if (menus == null || menus.isEmpty()) {
	    return;
	}
	
	IMenuConfig m = null;
	String type = null;
	
	
	for (IMenuItemConfig menu : menus) {
	    
	    // Only Menu (non item and separator)
	    if (!(menu instanceof IMenuConfig)) {
		continue;
	    }
	    
	    m = (IMenuConfig) menu;
	    
	    // Only non Global 
	    if (m.isGlobal()) {
		continue;
	    }
	    
	    // Only with type
	    type = m.getType();
	    if (isEmpty(type)) {
		continue;
	    }

	    menuList.add(m);
	    
	    loadMenusWithType(m, menuList);
	}
	
    }
    
    
    
    /**
     * Return all global menus
     */
    public List<IMenuConfig> getGlobalMenus() {
	
	List<IMenuConfig> menuList = new ArrayList<IMenuConfig>();
	
	List<IMenuBarConfig> menuBars = getObjects();
	if (menuBars == null || menuBars.isEmpty()) {
	    return menuList;
	}

	
	Map<String, IMenuConfig> menuMap = new HashMap<String, IMenuConfig>();
	
	for (IMenuBarConfig bar : menuBars) {
	    loadGlobalMenu(bar, menuMap);
	}
	
	Collection<IMenuConfig> mcl = menuMap.values();
	for (IMenuConfig mc : mcl) {
	    menuList.add(mc);
	}
	
	return menuList;
    }
    
    /**
     * Return global menu by type
     */
    public IMenuConfig getGlobalMenu(String type) {
	if (type == null) {
	    return null;
	}
	if (globalMenuMap == null) {
	    globalMenuMap = new HashMap<String, IMenuConfig>();
	}
	IMenuConfig m = globalMenuMap.get(type);
	if (m != null) {
	    return m;
	}
	m = findGlobalMenu(type);
	if (m!= null) {
	    globalMenuMap.put(type, m);
	}
	return m;
    }
    
    
    /**
     * Load global menu (global = true and type is not empty)
     * @param menuContainer
     * @param menuMap
     * @return
     */
    private IMenuConfig loadGlobalMenu(IMenuContainerConfig menuContainer, Map<String, IMenuConfig> menuMap) {
	return loadGlobalMenu(menuContainer, menuMap, null);
    }
    
    /**
     * Load global menu (global = true and type is not empty)
     * @param menuContainer
     * @param menuMap
     * @param findType 
     */
    private IMenuConfig loadGlobalMenu(IMenuContainerConfig menuContainer, Map<String, IMenuConfig> menuMap, String findType) {

	if (menuContainer == null) {
	    return null;
	}

	List<IMenuItemConfig> menus = menuContainer.getChildren();
	
	if (menus == null || menus.isEmpty()) {
	    return null;
	}
	
	IMenuConfig m = null;
	String type = null;
	
	for (IMenuItemConfig menu : menus) {
	    if (!(menu instanceof IMenuConfig)) {
		continue;
	    }
	    m = (IMenuConfig) menu;
	    if (!m.isGlobal()) {
		continue;
	    }
	    type = m.getType();
	    if (isEmpty(type)) {
		continue;
	    }
	    
	    if (findType != null && findType.equals(type)) {
		return m;
	    }
	    if (menuMap != null) {
		menuMap.put(type, m);
	    }
	    
	    m = loadGlobalMenu(m, menuMap, findType);
	    if (m != null) {
		return m;
	    }
	}
	return null;
    }

    protected IMenuConfig findGlobalMenu(String findType) {

	MenuBarConfigurer c = (MenuBarConfigurer) ConfigurerManager.getConfigurer(MenuBarConfigurer.NAME);
	if (c == null) {
	    return null;
	}
	
	List<IMenuBarConfig> menuBars = c.getObjects();
	if (menuBars == null || menuBars.isEmpty()) {
	    return null;
	}
	IMenuConfig m = null;
	for (IMenuBarConfig bar : menuBars) {
	    m = loadGlobalMenu(bar, null, findType);
	    if (m != null) {
		return m;
	    }
	}
	
	return null;
    }
    
    /**
     * Return true if menu [is global] or [non global and type is empty]
     * @param menu
     * @return
     */
    protected boolean isNLSStorableMenu(IMenuConfig menu) {
	if (menu.isGlobal()) {
	    return true;
	}
	return isEmpty(menu.getType());
    }
    
}
