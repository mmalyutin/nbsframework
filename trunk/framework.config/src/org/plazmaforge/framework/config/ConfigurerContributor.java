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

/**
 * 
 */
package org.plazmaforge.framework.config;

import java.util.List;

import org.plazmaforge.framework.config.configurer.ActionConfigurer;
import org.plazmaforge.framework.config.configurer.ConfigurerInfo;
import org.plazmaforge.framework.config.configurer.FormConfigurer;
import org.plazmaforge.framework.config.configurer.MenuBarConfigurer;
import org.plazmaforge.framework.config.configurer.ReportConfigurer;
import org.plazmaforge.framework.config.configurer.ToolBarConfigurer;
import org.plazmaforge.framework.config.object.IActionConfig;
import org.plazmaforge.framework.config.object.IFormConfig;
import org.plazmaforge.framework.config.object.IMenuBarConfig;
import org.plazmaforge.framework.config.object.IMenuContainerConfig;
import org.plazmaforge.framework.config.object.IMenuItemConfig;
import org.plazmaforge.framework.config.object.IReportConfig;
import org.plazmaforge.framework.config.object.IToolBarConfig;
import org.plazmaforge.framework.config.object.IToolItemConfig;
import org.plazmaforge.framework.util.StringUtils;

/**
 * @author ohapon
 *
 */
public class ConfigurerContributor {

    //TODO: Must use external command resolver
    private CommandResolver commandResolver;// = new CommandResolverImpl(); 

    /**
     * List of configurers
     */
    private List<ConfigurerInfo<?>> configurers;

    private ConfigurerInfo<IMenuBarConfig> menuBarConfigurer;
    private ConfigurerInfo<IToolBarConfig> toolBarConfigurer;
    
    private ConfigurerInfo<IActionConfig> actionConfigurer;
    private ConfigurerInfo<IFormConfig> formConfigurer;    
    private ConfigurerInfo<IReportConfig> reportConfigurer;
    
    public ConfigurerContributor(List<ConfigurerInfo<?>> configurers) {
	this(configurers, null);
    }
    
    public ConfigurerContributor(List<ConfigurerInfo<?>> configurers, CommandResolver commandResolver) {
	
	this.configurers = configurers;
	this.commandResolver = commandResolver;
	
	menuBarConfigurer = (ConfigurerInfo<IMenuBarConfig>) getConfigurer(configurers, MenuBarConfigurer.NAME);
	toolBarConfigurer = (ConfigurerInfo<IToolBarConfig>) getConfigurer(configurers, ToolBarConfigurer.NAME);
	
	actionConfigurer = (ConfigurerInfo<IActionConfig>) getConfigurer(configurers, ActionConfigurer.NAME);
	formConfigurer = (ConfigurerInfo<IFormConfig>) getConfigurer(configurers, FormConfigurer.NAME);
	reportConfigurer = (ConfigurerInfo<IReportConfig>) getConfigurer(configurers, ReportConfigurer.NAME);
    }

    public CommandResolver getCommandResolver() {
        return commandResolver;
    }

    public void setCommandResolver(CommandResolver commandResolver) {
        this.commandResolver = commandResolver;
    }

    public void contribute() {
	if (configurers == null || configurers.isEmpty()) {
	    return;
	}
	if ((actionConfigurer == null && formConfigurer == null && reportConfigurer == null)
		|| (menuBarConfigurer == null && toolBarConfigurer == null)) {
	    return;
	}
	contributeMenuBarActions();
	contributeToolBarActions();
    }
    
    protected ConfigurerInfo<?> getConfigurer(List<ConfigurerInfo<?>> configurers, String name) {
	if (name == null || configurers == null || configurers.isEmpty()) {
	    return null;
	}
	for (ConfigurerInfo<?> configurer : configurers) {
	    if (name.equals(configurer.getName())) {
		return configurer;
	    }
	}
	return null;
    }
    
    protected void contributeMenuBarActions() {
	if (menuBarConfigurer == null) {
	    return;
	}
	List<IMenuBarConfig> menuBars = menuBarConfigurer.getObjects();
	if (menuBars == null || menuBars.isEmpty()) {
	    return;
	}
	for (IMenuBarConfig menuBar: menuBars) {
	    contributeMenuActions(menuBar);
	}
    }

    protected void contributeMenuActions(IMenuContainerConfig menu) {
	List<IMenuItemConfig> children = menu.getChildren();
	for (IMenuItemConfig menuItem : children) {
	    if  (menuItem instanceof IMenuContainerConfig){
		contributeMenuActions((IMenuContainerConfig) menuItem);
		continue;
	    }
	    String text = menuItem.getText();
	    String iconPath = menuItem.getIcon();
	    boolean emptyText = StringUtils.isEmpty(text);
	    boolean emptyIcon = StringUtils.isEmpty(iconPath);
	    if  (!emptyText && !emptyIcon) {
		// Item has text and icon - no contribute by action
		continue;
	    }
	    String actionName = menuItem.getAction();
	    if (actionName == null) {
		continue;
	    }
	    ActionItem actionItem = getActionItem(actionName);
	    if (actionItem == null) {
		continue;
	    }
	    if (emptyText) {
		text = actionItem.text;
	    }
	    if (emptyIcon) {
		iconPath = actionItem.icon;
	    }
	    
	    
	    if (!StringUtils.isEmpty(text)) {
		menuItem.setText(text);
	    }
	    if (!StringUtils.isEmpty(iconPath)) {
		menuItem.setIcon(iconPath);
	    }
	    
	    
	}
    }
   

    protected void contributeToolBarActions() {
	if (toolBarConfigurer == null) {
	    return;
	}
	List<IToolBarConfig> toolBars = toolBarConfigurer.getObjects();
	if (toolBars == null || toolBars.isEmpty()) {
	    return;
	}
	for (IToolBarConfig toolBar: toolBars) {
	    contributeToolBarActions(toolBar);
	}
    }

    protected void contributeToolBarActions(IToolBarConfig toolBar) {
	List<IToolItemConfig> children = toolBar.getChildren();
	for (IToolItemConfig toolItem: children) {
	    String text = toolItem.getText();
	    String iconPath = toolItem.getIcon();
	    boolean emptyText = StringUtils.isEmpty(text);
	    boolean emptyIcon = StringUtils.isEmpty(iconPath);
	    if  (!emptyText && !emptyIcon) {
		// Item has text and icon - no contribute by action
		continue;
	    }
	    String actionId = toolItem.getAction();
	    if (actionId == null) {
		continue;
	    }
	    ActionItem actionItem = getActionItem(actionId);
	    if (actionItem == null) {
		continue;
	    }
	    if (emptyText) {
		text = actionItem.text;
	    }
	    if (emptyIcon) {
		iconPath = actionItem.icon;
	    }
	    
	    
	    if (!StringUtils.isEmpty(text)) {
		toolItem.setText(text);
	    }
	    if (!StringUtils.isEmpty(iconPath)) {
		toolItem.setIcon(iconPath);
	    }

	}
    }

    protected IActionConfig getActionConfigByName(String actionName) {
	if (actionConfigurer == null || actionName == null) {
	    return null;
	}
	IActionConfig actionConfig = actionConfigurer.getObjectByName(actionName);
	return actionConfig; 
    }
    
    protected IReportConfig getReportConfigByName(ConfigurerInfo<IReportConfig> reportConfigurer, String reportName) {
	if (reportConfigurer == null || reportName == null) {
	    return null;
	}
	IReportConfig reportConfig = reportConfigurer.getObjectByName(reportName);
	return reportConfig; 
    }
    
    
    protected ActionItem getActionItem(String actionName) {
	if (actionConfigurer == null || actionName == null) {
	    return null;
	}
	IActionConfig actionConfig = getActionConfigByName(actionName);
	if  (actionConfig == null) {
	    return null;
	}
	ActionItem actionItem = new ActionItem();
	actionItem.text = actionConfig.getCaption();
	actionItem.icon = actionConfig.getIcon();
	
	if (actionItem.text != null && actionItem.icon != null) {
	    return actionItem;
	}
	
	String command = actionConfig.getCommand();
	if (command == null) {
	    return actionItem;
	}
	
	ConfigItem configItem = getConfigItem(command);
	if (configItem == null || configItem.type == null || configItem.name == null) {
	    return actionItem;
	}
	
	if ("Report".equals(configItem.type)) {
	    if (reportConfigurer == null) {
		return actionItem;
	    }
	    IReportConfig reportConfig = getReportConfigByName(reportConfigurer, configItem.name);
	    if  (reportConfig == null) {
		return actionItem;
	    }
	    
	    if (actionItem.text == null) {
		actionItem.text = reportConfig.getCaption();
	    }
	    if (actionItem.icon == null) {
		//actionItem.icon = reportConfig.getIcon();
	    }
	}
	return actionItem;
    }
    
    protected ConfigItem getConfigItem(String commandLine) {
	if (commandLine == null || commandResolver == null) {
	    return null;
	}
	return commandResolver.getConfigItem(commandLine);
    }

    public static class ActionItem {
	public String text;
	public String icon;
    }
    
    public static class ConfigItem {
	public String type;
	public String name;
    }
    
    public static interface CommandResolver {
	
	/**
	 * Parse command line and return type and name by command
	 * @param commandLine
	 * @return
	 */
	ConfigItem getConfigItem(String commandLine);
	
    }
    
    //TODO: Must use external command resolver
    public static class CommandResolverImpl implements CommandResolver {

	@Override
	public ConfigItem getConfigItem(String commandLine) {
	    if (commandLine == null) {
		return null;
	    }
	    commandLine = commandLine.trim();
	    if (commandLine.isEmpty()) {
		return null;
	    }
	    
	    //TODO: Must use real command parser
	    int index = commandLine.indexOf(" ");
	    if (index < 0) {
		return null;
	    }
	    String commandType = commandLine.substring(0, index);
	    String name = commandLine.substring(index + 1);
	    ConfigItem configItem = null;
	    
	    //TODO: Only for open Report
	    //Must implement for From, View...
	    if ("PREVIEW_REPORT".equals(commandType)) {
		configItem = new ConfigItem();
		configItem.type = "Report";
		configItem.name = name;
	    }
	    return configItem;
	}
	
    }
}
