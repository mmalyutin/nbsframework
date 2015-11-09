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

package org.plazmaforge.framework.uwt.swing;

import javax.swing.UIManager;

import org.plazmaforge.framework.uwt.UIAdapterFactory;
import org.plazmaforge.framework.uwt.UWT;
import org.plazmaforge.framework.uwt.swing.adapter.SwingApplicationAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingBoxLayoutAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingButtonAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingCanvasAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingCardLayoutAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingCheckBoxAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingCollapsePanelAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingComboBoxAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingCompositeAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingCoolBarAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingDateFieldAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingDateTimeFieldAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingDesktopAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingDesktopItemAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingDialogAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingFitLayoutAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingGCAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingGridDataAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingGridLayoutAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingGroupPanelAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingHorizontalLayoutAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingImageBoxAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingIntegerFieldAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingLabelAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingLinkAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingListBoxAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingMenuAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingMenuBarAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingMenuItemAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingMenuSeparatorAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingMessageBoxAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingNumberFieldAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingPanelAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingPasswordFieldAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingRadioButtonAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingRadioGroupAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingFrameAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingSeparatorAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingSliderAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingSpinnerFieldAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingSplitPanelAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingTabItemAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingTabPanelAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingTableAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingTableColumnAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingTextAreaAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingTextFieldAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingTimeFieldAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingTitlePanelAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingToggleButtonAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingToolBarAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingToolItemAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingToolSeparatorAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingTreeAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingVerticalLayoutAdapter;
import org.plazmaforge.framework.uwt.swing.adapter.SwingWindowAdapter;

public class UWT_Swing {

    private static boolean init;
    
    public static void init() {
   	if (init) {
   	    return;
   	}
   	init = true;
   	initAdapters();
       }
       
       private static void initAdapters() {

	   try {
	       
	       String cn = UIManager.getSystemLookAndFeelClassName();
	       //String cn = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
	       //String cn = UIManager.getCrossPlatformLookAndFeelClassName();
	       UIManager.setLookAndFeel(cn);
	       //JFrame.setDefaultLookAndFeelDecorated(true);
	   } catch (Exception ex) {
	       ex.printStackTrace();
	   }
	         
	         
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.Application.class, new SwingApplicationAdapter());
	   
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Window.class, new SwingWindowAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Frame.class, new SwingFrameAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.dialog.Dialog.class, new SwingDialogAdapter());
	   
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Composite.class, new SwingCompositeAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.panel.Panel.class, new SwingPanelAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.panel.GroupPanel.class, new SwingGroupPanelAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.panel.TitlePanel.class, new SwingTitlePanelAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.panel.CollapsePanel.class, new SwingCollapsePanelAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.panel.SplitPanel.class, new SwingSplitPanelAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Label.class, new SwingLabelAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Button.class, new SwingButtonAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.ToggleButton.class, new SwingToggleButtonAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.RadioGroup.class, new SwingRadioGroupAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.RadioButton.class, new SwingRadioButtonAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.TextField.class, new SwingTextFieldAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.TextArea.class, new SwingTextAreaAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.PasswordField.class, new SwingPasswordFieldAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.NumberField.class, new SwingNumberFieldAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.IntegerField.class, new SwingIntegerFieldAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.SpinnerField.class, new SwingSpinnerFieldAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.DateField.class, new SwingDateFieldAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.DateTimeField.class, new SwingDateTimeFieldAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.TimeField.class, new SwingTimeFieldAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.CheckBox.class, new SwingCheckBoxAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.ComboBox.class, new SwingComboBoxAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.ListBox.class, new SwingListBoxAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Slider.class, new SwingSliderAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Link.class, new SwingLinkAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.ImageBox.class, new SwingImageBoxAdapter());
	   
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.BoxLayout.class, new SwingBoxLayoutAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.HorizontalLayout.class, new SwingHorizontalLayoutAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.VerticalLayout.class, new SwingVerticalLayoutAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.GridLayout.class, new SwingGridLayoutAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.FitLayout.class, new SwingFitLayoutAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.CardLayout.class, new SwingCardLayoutAdapter());
	   
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.layout.GridData.class, new SwingGridDataAdapter());
	   
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.table.Table.class, new SwingTableAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.table.TableColumn.class, new SwingTableColumnAdapter());

	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.tree.Tree.class, new SwingTreeAdapter());
	   
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.panel.TabPanel.class, new SwingTabPanelAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.panel.TabItem.class, new SwingTabItemAdapter());
	   
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.menu.MenuBar.class, new SwingMenuBarAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.menu.Menu.class, new SwingMenuAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.menu.MenuItem.class, new SwingMenuItemAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.menu.MenuSeparator.class, new SwingMenuSeparatorAdapter());
	   
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.tool.ToolBar.class, new SwingToolBarAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.tool.ToolItem.class, new SwingToolItemAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.tool.ToolSeparator.class, new SwingToolSeparatorAdapter());
	   
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.tool.CoolBar.class, new SwingCoolBarAdapter());
	   
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.MessageBox.class, new SwingMessageBoxAdapter());
	   
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.desktop.Desktop.class, new SwingDesktopAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.desktop.DesktopItem.class, new SwingDesktopItemAdapter());
	   
	   
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.graphics.GC.class, new SwingGCAdapter());
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Canvas.class, new SwingCanvasAdapter());
	   
	   UIAdapterFactory.addAdapter(org.plazmaforge.framework.uwt.widget.Separator.class, new SwingSeparatorAdapter());

	   UWT.setUIType("Swing");
       }
}
