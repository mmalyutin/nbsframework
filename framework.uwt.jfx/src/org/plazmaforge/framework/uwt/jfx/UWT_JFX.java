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

package org.plazmaforge.framework.uwt.jfx;

import org.plazmaforge.framework.uwt.UIAdapterFactory;
import org.plazmaforge.framework.uwt.UWT;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXApplicationAdapter;

import org.plazmaforge.framework.uwt.jfx.adapter.JFXButtonAdapter;
//import org.plazmaforge.framework.uwt.jfx.adapter.JFXCanvasAdapter;
//import org.plazmaforge.framework.uwt.jfx.adapter.JFXCardLayoutAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXCheckBoxAdapter;
//import org.plazmaforge.framework.uwt.jfx.adapter.JFXCollapsePanelAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXComboBoxAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXContainerAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXCoolBarAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXDateFieldAdapter;
//import org.plazmaforge.framework.uwt.jfx.adapter.JFXDateTimeFieldAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXDesktopAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXDesktopItemAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXDialogAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXFitLayoutAdapter;
//import org.plazmaforge.framework.uwt.jfx.adapter.JFXGCAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXGridDataAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXGridLayoutAdapter;
//import org.plazmaforge.framework.uwt.jfx.adapter.JFXGroupPanelAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXHorizontalLayoutAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXVerticalLayoutAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXImageBoxAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXLabelAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXLinkAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXListBoxAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXMenuAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXMenuBarAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXMenuItemAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXMenuSeparatorAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXMessageBoxAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXNumberFieldAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXIntegerFieldAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXBoxLayoutAdapter;
//import org.plazmaforge.framework.uwt.jfx.adapter.JFXPanelAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXPasswordFieldAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXRadioButtonAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXRadioGroupAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXToggleButtonAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXFrameAdapter;
//import org.plazmaforge.framework.uwt.jfx.adapter.JFXScrollPanelAdapter;
//import org.plazmaforge.framework.uwt.jfx.adapter.JFXSeparatorAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXSliderAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXSpinnerFieldAdapter;
//import org.plazmaforge.framework.uwt.jfx.adapter.JFXSplitPanelAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXTabItemAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXTabPanelAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXTableAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXTableColumnAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXTextFieldAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXTextAreaAdapter;
//import org.plazmaforge.framework.uwt.jfx.adapter.JFXTimeFieldAdapter;
//import org.plazmaforge.framework.uwt.jfx.adapter.JFXTitlePanelAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXToolBarAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXToolItemAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXToolSeparatorAdapter;
//import org.plazmaforge.framework.uwt.jfx.adapter.JFXTreeAdapter;
import org.plazmaforge.framework.uwt.jfx.adapter.JFXWindowAdapter;

/**
 * 
 * @author ohapon
 *
 */
public class UWT_JFX {
    
    private static boolean init;
    
    public static void init() {
	if (init) {
	    return;
	}
	init = true;
	initAdapters();
    }
    
    private static void initAdapters() {
	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.Application.class, new JFXApplicationAdapter());
	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Window.class, new JFXWindowAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Frame.class, new JFXFrameAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.dialog.Dialog.class, new JFXDialogAdapter());
	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Container.class, new JFXContainerAdapter());
	//UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.panel.Panel.class, new JFXPanelAdapter());

	//UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.panel.GroupPanel.class, new JFXGroupPanelAdapter());
	//UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.panel.TitlePanel.class, new JFXTitlePanelAdapter());
	//UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.panel.CollapsePanel.class, new JFXCollapsePanelAdapter());
	//UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.panel.SplitPanel.class, new JFXSplitPanelAdapter());
	//UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.panel.ScrollPanel.class, new JFXScrollPanelAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Label.class, new JFXLabelAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Button.class, new JFXButtonAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.ToggleButton.class, new JFXToggleButtonAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.RadioGroup.class, new JFXRadioGroupAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.RadioButton.class, new JFXRadioButtonAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.TextField.class, new JFXTextFieldAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.TextArea.class, new JFXTextAreaAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.PasswordField.class, new JFXPasswordFieldAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.NumberField.class, new JFXNumberFieldAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.IntegerField.class, new JFXIntegerFieldAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.SpinnerField.class, new JFXSpinnerFieldAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.DateField.class, new JFXDateFieldAdapter());
	//UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.DateTimeField.class, new JFXDateTimeFieldAdapter());
	//UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.TimeField.class, new JFXTimeFieldAdapter());
	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.CheckBox.class, new JFXCheckBoxAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.ComboBox.class, new JFXComboBoxAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.ListBox.class, new JFXListBoxAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Slider.class, new JFXSliderAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Link.class, new JFXLinkAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.ImageBox.class, new JFXImageBoxAdapter());
	
	//UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.FillLayout.class, new JFXFillLayoutAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.BoxLayout.class, new JFXBoxLayoutAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.GridLayout.class, new JFXGridLayoutAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.HorizontalLayout.class, new JFXHorizontalLayoutAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.VerticalLayout.class, new JFXVerticalLayoutAdapter());	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.FitLayout.class, new JFXFitLayoutAdapter());
	
	//UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.CardLayout.class, new JFXCardLayoutAdapter());
	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.GridData.class, new JFXGridDataAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.table.Table.class, new JFXTableAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.table.TableColumn.class, new JFXTableColumnAdapter());
	
	//UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.tree.Tree.class, new JFXTreeAdapter());

	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.panel.TabPanel.class, new JFXTabPanelAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.panel.TabItem.class, new JFXTabItemAdapter());
	
	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.menu.MenuBar.class, new JFXMenuBarAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.menu.Menu.class, new JFXMenuAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.menu.MenuItem.class, new JFXMenuItemAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.menu.MenuSeparator.class, new JFXMenuSeparatorAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.tool.ToolBar.class, new JFXToolBarAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.tool.ToolItem.class, new JFXToolItemAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.tool.ToolSeparator.class, new JFXToolSeparatorAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.tool.CoolBar.class, new JFXCoolBarAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.MessageBox.class, new JFXMessageBoxAdapter());
	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.desktop.Desktop.class, new JFXDesktopAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.desktop.DesktopItem.class, new JFXDesktopItemAdapter());
	
	/*
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.graphics.GC.class, new JFXGCAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Canvas.class, new JFXCanvasAdapter());
	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Separator.class, new JFXSeparatorAdapter());
	*/
	
	UWT.setUIType("JFX");
	
    }

}
