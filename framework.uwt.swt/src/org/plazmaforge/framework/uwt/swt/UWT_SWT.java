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

package org.plazmaforge.framework.uwt.swt;

import org.plazmaforge.framework.uwt.UIAdapterFactory;
import org.plazmaforge.framework.uwt.UWT;
import org.plazmaforge.framework.uwt.swt.adapter.SWTApplicationAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTButtonAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTCanvasAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTCardLayoutAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTCheckBoxAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTCollapsePanelAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTComboBoxAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTCompositeAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTCoolBarAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTDateFieldAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTDateTimeFieldAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTDesktopAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTDesktopItemAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTDialogAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTFitLayoutAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTGCAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTGridDataAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTGridLayoutAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTGroupPanelAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTHorizontalLayoutAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTImageBoxAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTIntegerFieldAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTLabelAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTLinkAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTListBoxAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTMenuAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTMenuBarAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTMenuItemAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTMenuSeparatorAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTMessageBoxAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTNumberFieldAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTBoxLayoutAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTPanelAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTPasswordFieldAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTRadioButtonAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTRadioGroupAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTFrameAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTScrollPanelAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTSeparatorAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTSliderAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTSpinnerFieldAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTSplitPanelAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTTabItemAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTTabPanelAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTTableAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTTableColumnAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTTextAreaAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTTextFieldAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTTimeFieldAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTTitlePanelAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTToggleButtonAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTToolBarAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTToolItemAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTToolSeparatorAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTTreeAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTVerticalLayoutAdapter;
import org.plazmaforge.framework.uwt.swt.adapter.SWTWindowAdapter;

public class UWT_SWT {
    
    private static boolean init;
    
    public static void init() {
	if (init) {
	    return;
	}
	init = true;
	initAdapters();
    }
    
    private static void initAdapters() {
	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.Application.class, new SWTApplicationAdapter());
	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Window.class, new SWTWindowAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Frame.class, new SWTFrameAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.dialog.Dialog.class, new SWTDialogAdapter());
	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Composite.class, new SWTCompositeAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.panel.Panel.class, new SWTPanelAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.panel.GroupPanel.class, new SWTGroupPanelAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.panel.TitlePanel.class, new SWTTitlePanelAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.panel.CollapsePanel.class, new SWTCollapsePanelAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.panel.SplitPanel.class, new SWTSplitPanelAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.panel.ScrollPanel.class, new SWTScrollPanelAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Label.class, new SWTLabelAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Button.class, new SWTButtonAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.ToggleButton.class, new SWTToggleButtonAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.RadioGroup.class, new SWTRadioGroupAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.RadioButton.class, new SWTRadioButtonAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.TextField.class, new SWTTextFieldAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.TextArea.class, new SWTTextAreaAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.PasswordField.class, new SWTPasswordFieldAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.NumberField.class, new SWTNumberFieldAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.IntegerField.class, new SWTIntegerFieldAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.SpinnerField.class, new SWTSpinnerFieldAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.DateField.class, new SWTDateFieldAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.DateTimeField.class, new SWTDateTimeFieldAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.TimeField.class, new SWTTimeFieldAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.CheckBox.class, new SWTCheckBoxAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.ComboBox.class, new SWTComboBoxAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.ListBox.class, new SWTListBoxAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Slider.class, new SWTSliderAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Link.class, new SWTLinkAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.ImageBox.class, new SWTImageBoxAdapter());
	
	//UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.FillLayout.class, new SWTFillLayoutAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.BoxLayout.class, new SWTBoxLayoutAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.HorizontalLayout.class, new SWTHorizontalLayoutAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.VerticalLayout.class, new SWTVerticalLayoutAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.GridLayout.class, new SWTGridLayoutAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.FitLayout.class, new SWTFitLayoutAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.CardLayout.class, new SWTCardLayoutAdapter());
	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.GridData.class, new SWTGridDataAdapter());
	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.table.Table.class, new SWTTableAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.table.TableColumn.class, new SWTTableColumnAdapter());
	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.tree.Tree.class, new SWTTreeAdapter());

	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.panel.TabPanel.class, new SWTTabPanelAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.panel.TabItem.class, new SWTTabItemAdapter());
	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.menu.MenuBar.class, new SWTMenuBarAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.menu.Menu.class, new SWTMenuAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.menu.MenuItem.class, new SWTMenuItemAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.menu.MenuSeparator.class, new SWTMenuSeparatorAdapter());
	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.tool.ToolBar.class, new SWTToolBarAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.tool.ToolItem.class, new SWTToolItemAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.tool.ToolSeparator.class, new SWTToolSeparatorAdapter());
	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.tool.CoolBar.class, new SWTCoolBarAdapter());
	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.MessageBox.class, new SWTMessageBoxAdapter());

	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.desktop.Desktop.class, new SWTDesktopAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.desktop.DesktopItem.class, new SWTDesktopItemAdapter());
	
	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.graphics.GC.class, new SWTGCAdapter());
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Canvas.class, new SWTCanvasAdapter());
	
	UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Separator.class, new SWTSeparatorAdapter());
	
	UWT.setUIType("SWT");
	
    }

}
