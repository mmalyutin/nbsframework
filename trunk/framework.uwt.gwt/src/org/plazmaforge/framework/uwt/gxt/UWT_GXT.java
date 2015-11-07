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

package org.plazmaforge.framework.uwt.gxt;

import java.util.HashMap;
import java.util.Map;


import org.plazmaforge.framework.uwt.ApplicationContext;
import org.plazmaforge.framework.uwt.UIAdapterFactory;
import org.plazmaforge.framework.uwt.UWT;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTApplicationAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTBoxLayoutAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTButtonAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTCanvasAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTCardLayoutAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTCheckBoxAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTCollapsePanelAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTComboBoxAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTCompositeAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTCoolBarAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTDateFieldAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTDesktopAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTDesktopItemAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTDialogAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTFitLayoutAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTGCAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTGridDataAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTGridLayoutAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTGroupPanelAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTHorizontalLayoutAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTImageBoxAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTIntegerFieldAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTLabelAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTLinkAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTListBoxAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTMenuAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTMenuBarAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTMenuItemAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTMenuSeparatorAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTMessageBoxAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTNumberFieldAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTPanelAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTPasswordFieldAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTRadioButtonAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTRadioGroupAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTFrameAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTSeparatorAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTSliderAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTSpinnerFieldAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTSplitPanelAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTTabItemAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTTabPanelAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTTableAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTTableColumnAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTTextAreaAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTTextFieldAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTTimeFieldAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTTitlePanelAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTToggleButtonAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTToolBarAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTToolItemAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTToolSeparatorAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTTreeAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTVerticalLayoutAdapter;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTWindowAdapter;
import org.plazmaforge.framework.uwt.gxt.core.GXTPropertyProviderFactory;

public class UWT_GXT {

    private static boolean init;

    private static Map<String, Object> attributes = new HashMap<String, Object>();
    
    public static void init() {
	if (init) {
	    return;
	}
	init = true;
	initAdapters();
    }

    private static void initAdapters() {
	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.Application.class, new GXTApplicationAdapter());
	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Window.class, new GXTWindowAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Frame.class, new GXTFrameAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.dialog.Dialog.class, new GXTDialogAdapter());
	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Composite.class, new GXTCompositeAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.panel.Panel.class, new GXTPanelAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.panel.GroupPanel.class, new GXTGroupPanelAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.panel.TitlePanel.class, new GXTTitlePanelAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.panel.CollapsePanel.class, new GXTCollapsePanelAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.panel.SplitPanel.class, new GXTSplitPanelAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Label.class, new GXTLabelAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Button.class, new GXTButtonAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.ToggleButton.class, new GXTToggleButtonAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.RadioGroup.class, new GXTRadioGroupAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.RadioButton.class, new GXTRadioButtonAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.TextField.class, new GXTTextFieldAdapter()); 
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.TextArea.class, new GXTTextAreaAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.PasswordField.class, new GXTPasswordFieldAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.NumberField.class, new GXTNumberFieldAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.IntegerField.class, new GXTIntegerFieldAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.SpinnerField.class, new GXTSpinnerFieldAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.DateField.class, new GXTDateFieldAdapter());
	//UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.DateTimeField.class, new GXTDateTimeFieldAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.TimeField.class, new GXTTimeFieldAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.CheckBox.class, new GXTCheckBoxAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.ComboBox.class, new GXTComboBoxAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.ListBox.class, new GXTListBoxAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Slider.class, new GXTSliderAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Link.class, new GXTLinkAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.ImageBox.class, new GXTImageBoxAdapter());
	
	//UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.FillLayout.class, new GWTFillLayoutAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.BoxLayout.class, new GXTBoxLayoutAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.HorizontalLayout.class, new GXTHorizontalLayoutAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.VerticalLayout.class, new GXTVerticalLayoutAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.GridLayout.class, new GXTGridLayoutAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.FitLayout.class, new GXTFitLayoutAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.CardLayout.class, new GXTCardLayoutAdapter());
	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.GridData.class, new GXTGridDataAdapter());

	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.table.Table.class, new GXTTableAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.table.TableColumn.class, new GXTTableColumnAdapter());
	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.tree.Tree.class, new GXTTreeAdapter());
	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.panel.TabPanel.class, new GXTTabPanelAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.panel.TabItem.class, new GXTTabItemAdapter());
	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.menu.MenuBar.class, new GXTMenuBarAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.menu.Menu.class, new GXTMenuAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.menu.MenuItem.class, new GXTMenuItemAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.menu.MenuSeparator.class, new GXTMenuSeparatorAdapter());
	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.tool.ToolBar.class, new GXTToolBarAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.tool.ToolItem.class, new GXTToolItemAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.tool.ToolSeparator.class, new GXTToolSeparatorAdapter());
	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.tool.CoolBar.class, new GXTCoolBarAdapter());
	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.MessageBox.class, new GXTMessageBoxAdapter());

	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.desktop.Desktop.class, new GXTDesktopAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.desktop.DesktopItem.class, new GXTDesktopItemAdapter());
	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.graphics.GC.class, new GXTGCAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Canvas.class, new GXTCanvasAdapter());
	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Separator.class, new GXTSeparatorAdapter());

	getAttributes().put(ApplicationContext.CONFIG_PROPERTY_PROVIDER_FACTORY, new GXTPropertyProviderFactory());
	
	UWT.setUIType("GXT");
    }

    public static Map<String, Object> getAttributes() {
        return attributes;
    }
    
    

}
