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

package org.plazmaforge.framework.uwt.builder;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.builder.dialog.DialogBuilder;
import org.plazmaforge.framework.uwt.builder.form.EditFormBuilder;
import org.plazmaforge.framework.uwt.builder.form.ListFormBuilder;
import org.plazmaforge.framework.uwt.builder.layout.BorderDataBuilder;
import org.plazmaforge.framework.uwt.builder.layout.BorderLayoutBuilder;
import org.plazmaforge.framework.uwt.builder.layout.BoxDataBuilder;
import org.plazmaforge.framework.uwt.builder.layout.BoxLayoutBuilder;
import org.plazmaforge.framework.uwt.builder.layout.CardLayoutBuilder;
import org.plazmaforge.framework.uwt.builder.layout.FitLayoutBuilder;
import org.plazmaforge.framework.uwt.builder.layout.GridDataBuilder;
import org.plazmaforge.framework.uwt.builder.layout.GridLayoutBuilder;
import org.plazmaforge.framework.uwt.builder.layout.HorizontalLayoutBuilder;
import org.plazmaforge.framework.uwt.builder.layout.VerticalLayoutBuilder;
import org.plazmaforge.framework.uwt.builder.widget.ButtonBuilder;
import org.plazmaforge.framework.uwt.builder.widget.CheckBoxBuilder;
import org.plazmaforge.framework.uwt.builder.widget.ComboBoxBuilder;
import org.plazmaforge.framework.uwt.builder.widget.CompositeBuilder;
import org.plazmaforge.framework.uwt.builder.widget.CurrencyFieldBuilder;
import org.plazmaforge.framework.uwt.builder.widget.DateFieldBuilder;
import org.plazmaforge.framework.uwt.builder.widget.DateTimeFieldBuilder;
import org.plazmaforge.framework.uwt.builder.widget.FrameBuilder;
import org.plazmaforge.framework.uwt.builder.widget.ImageBoxBuilder;
import org.plazmaforge.framework.uwt.builder.widget.IntegerFieldBuilder;
import org.plazmaforge.framework.uwt.builder.widget.LabelBuilder;
import org.plazmaforge.framework.uwt.builder.widget.LinkBuilder;
import org.plazmaforge.framework.uwt.builder.widget.ListBoxBuilder;
import org.plazmaforge.framework.uwt.builder.widget.LookupFieldBuilder;
import org.plazmaforge.framework.uwt.builder.widget.NumberFieldBuilder;
import org.plazmaforge.framework.uwt.builder.widget.PasswordFieldBuilder;
import org.plazmaforge.framework.uwt.builder.widget.PercentFieldBuilder;
import org.plazmaforge.framework.uwt.builder.widget.QuantityFieldBuilder;
import org.plazmaforge.framework.uwt.builder.widget.RadioButtonBuilder;
import org.plazmaforge.framework.uwt.builder.widget.RadioGroupBuilder;
import org.plazmaforge.framework.uwt.builder.widget.SeparatorBuilder;
import org.plazmaforge.framework.uwt.builder.widget.SliderBuilder;
import org.plazmaforge.framework.uwt.builder.widget.SpinnerFieldBuilder;
import org.plazmaforge.framework.uwt.builder.widget.TextAreaBuilder;
import org.plazmaforge.framework.uwt.builder.widget.TextBoxBuilder;
import org.plazmaforge.framework.uwt.builder.widget.TextFieldBuilder;
import org.plazmaforge.framework.uwt.builder.widget.TimeFieldBuilder;
import org.plazmaforge.framework.uwt.builder.widget.ToggleButtonBuilder;
import org.plazmaforge.framework.uwt.builder.widget.WindowBuilder;
import org.plazmaforge.framework.uwt.builder.widget.menu.MenuBarBuilder;
import org.plazmaforge.framework.uwt.builder.widget.menu.MenuBuilder;
import org.plazmaforge.framework.uwt.builder.widget.menu.MenuItemBuilder;
import org.plazmaforge.framework.uwt.builder.widget.menu.MenuSeparatorBuilder;
import org.plazmaforge.framework.uwt.builder.widget.panel.CollapsePanelBuilder;
import org.plazmaforge.framework.uwt.builder.widget.panel.GroupPanelBuilder;
import org.plazmaforge.framework.uwt.builder.widget.panel.HorizontalPanelBuilder;
import org.plazmaforge.framework.uwt.builder.widget.panel.PanelBuilder;
import org.plazmaforge.framework.uwt.builder.widget.panel.SplitPanelBuilder;
import org.plazmaforge.framework.uwt.builder.widget.panel.TabItemBuilder;
import org.plazmaforge.framework.uwt.builder.widget.panel.TabPanelBuilder;
import org.plazmaforge.framework.uwt.builder.widget.panel.TitlePanelBuilder;
import org.plazmaforge.framework.uwt.builder.widget.panel.VerticalPanelBuilder;
import org.plazmaforge.framework.uwt.builder.widget.table.TableBuilder;
import org.plazmaforge.framework.uwt.builder.widget.tool.ToolBarBuilder;
import org.plazmaforge.framework.uwt.builder.widget.tool.ToolItemBuilder;
import org.plazmaforge.framework.uwt.builder.widget.tool.ToolSeparatorBuilder;
import org.plazmaforge.framework.uwt.builder.widget.tree.TreeBuilder;


public class UIBuilder {

    //////////////////////////////////////////////////////////////////////
    // SYS PROPERTIES
    //////////////////////////////////////////////////////////////////////

    
    public static final String SYS_PROPERTY_TYPE = "$type";
    public static final String SYS_PROPERTY_NAME = "$name";
    public static final String SYS_PROPERTY_VALUE = "$value";
    public static final String SYS_PROPERTY_ELEMENT = "$element";
    public static final String SYS_PROPERTY_RESOURCE = "$resource";
    public static final String SYS_PROPERTY_PARENT = "$parent";
    public static final String SYS_PROPERTY_CHILDREN = "$children";
    public static final String SYS_PROPERTY_GEN_SUPER_CLASS = "$gensuperclass";
    public static final String SYS_PROPERTY_GEN_NAME = "$genname";
    


    //////////////////////////////////////////////////////////////////////
    // TYPES
    //////////////////////////////////////////////////////////////////////
    
    public static final String COMPOSITE_TYPE = "Composite";
    public static final String PANEL_TYPE = "Panel";
    public static final String HORIZONTAL_PANEL_TYPE = "HorizontalPanel";
    public static final String VERTICAL_PANEL_TYPE = "VerticalPanel";
    public static final String GROUP_PANEL_TYPE = "GroupPanel";
    public static final String TITLE_PANEL_TYPE = "TitlePanel";
    public static final String COLLAPSE_PANEL_TYPE = "CollapsePanel";
    public static final String SPLIT_PANEL_TYPE = "SplitPanel";
    public static final String TAB_PANEL_TYPE = "TabPanel";
    public static final String TAB_ITEM_TYPE = "TabItem";
    
    public static final String LABEL_TYPE = "Label";
    public static final String SEPARATOR_TYPE = "Separator";
    public static final String BUTTON_TYPE = "Button";
    public static final String TOGGLE_BUTTON_TYPE = "ToggleButton";
    public static final String LINK_TYPE = "Link";
    public static final String IMAGE_BOX_TYPE = "ImageBox";
    
    public static final String GRID_LAYOUT_TYPE = "GridLayout";
    public static final String BOX_LAYOUT_TYPE = "BoxLayout";
    public static final String HORIZONTAL_LAYOUT_TYPE = "HorizontalLayout";
    public static final String VERTICAL_LAYOUT_TYPE = "VerticalLayout";
    public static final String BORDER_LAYOUT_TYPE = "BorderLayout";
    public static final String FIT_LAYOUT_TYPE = "FitLayout";
    public static final String CARD_LAYOUT_TYPE = "CardLayout";
    
    public static final String GRID_DATA_TYPE = "GridData";
    public static final String BOX_DATA_TYPE = "BoxData";
    public static final String BORDER_DATA_TYPE = "BorderData";

    
    // Fields
    public static final String FIELD_TYPE = "Field";
    
    // Text Fields
    public static final String TEXT_BOX_TYPE = "TextBox";
    public static final String TEXT_FIELD_TYPE = "TextField";
    public static final String TEXT_AREA_TYPE = "TextArea";
    public static final String PASSWORD_FIELD_TYPE = "PasswordField";
    
    // Number Fields
    public static final String NUMBER_FIELD_TYPE = "NumberField";
    public static final String INTEGER_FIELD_TYPE = "IntegerField";
    public static final String CURRENCY_FIELD_TYPE = "CurrencyField";
    public static final String PERCENT_FIELD_TYPE = "PercentField";
    public static final String QUANTITY_FIELD_TYPE = "QuantityField";
    public static final String SPINNER_FIELD_TYPE = "SpinnerField";
    
    public static final String SLIDER_TYPE = "Slider";

    // Date/Time Fields
    public static final String DATE_FIELD_TYPE = "DateField";
    public static final String TIME_FIELD_TYPE = "TimeField";
    public static final String DATE_TIME_FIELD_TYPE = "DateTimeField";    

    // CheckBox/RadioButton
    public static final String CHECK_BOX_TYPE = "CheckBox";
    public static final String RADIO_BUTTON_TYPE = "RadioButton";
    public static final String RADIO_GROUP_TYPE = "RadioGroup";

    // ComboBox/EditBox
    public static final String COMBO_BOX_TYPE = "ComboBox";
    public static final String LOOKUP_FIELD_TYPE = "LookupField";

    // ListBox
    public static final String LIST_BOX_TYPE = "ListBox";


    public static final String TABLE_TYPE = "Table";
    public static final String TREE_TYPE = "Tree";

    
    public static final String WINDOW_TYPE = "Window";
    public static final String FRAME_TYPE = "Frame";
    public static final String DIALOG_TYPE = "Dialog";
    
    public static final String FORM_TYPE = "Form";
    public static final String LIST_FORM_TYPE = "ListForm";
    public static final String EDIT_FORM_TYPE = "EditForm";
    
    public static final String MENU_BAR_TYPE = "MenuBar";
    public static final String MENU_TYPE = "Menu";
    public static final String MENU_ITEM_TYPE = "MenuItem";
    public static final String MENU_SEPARATOR_TYPE = "MenuSeparator";

    public static final String TOOL_BAR_TYPE = "ToolBar";
    public static final String TOOL_ITEM_TYPE = "ToolItem";
    public static final String TOOL_SEPARATOR_TYPE = "ToolSeparator";

    
    // All Field Types
    public static final  String[] FIELD_TYPES = new String[] {
  	TEXT_BOX_TYPE,
  	TEXT_FIELD_TYPE,
  	TEXT_AREA_TYPE,
  	PASSWORD_FIELD_TYPE,
  	NUMBER_FIELD_TYPE,
  	INTEGER_FIELD_TYPE
  	//TODO: must add other fields
  	};
    
    
    //////////////////////////////////////////////////////////////////////
    // BUILDERS
    //////////////////////////////////////////////////////////////////////
    
    
    private static Map<String, IUIBuilder> builders = new HashMap<String, IUIBuilder>();
    
    
    
    ///////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Register builders
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////
    
    static {
	
	// Base types
	builders.put(COMPOSITE_TYPE, new CompositeBuilder());
	builders.put(PANEL_TYPE, new PanelBuilder());
	builders.put(HORIZONTAL_PANEL_TYPE, new HorizontalPanelBuilder());
	builders.put(VERTICAL_PANEL_TYPE, new VerticalPanelBuilder());
	builders.put(GROUP_PANEL_TYPE, new GroupPanelBuilder());
	builders.put(TITLE_PANEL_TYPE, new TitlePanelBuilder());
	builders.put(COLLAPSE_PANEL_TYPE, new CollapsePanelBuilder());
	builders.put(SPLIT_PANEL_TYPE, new SplitPanelBuilder());
	builders.put(TAB_PANEL_TYPE, new TabPanelBuilder());
	builders.put(TAB_ITEM_TYPE, new TabItemBuilder());
	
	builders.put(LABEL_TYPE, new LabelBuilder());
	builders.put(SEPARATOR_TYPE, new SeparatorBuilder());
	builders.put(BUTTON_TYPE, new ButtonBuilder());
	builders.put(TOGGLE_BUTTON_TYPE, new ToggleButtonBuilder());
	builders.put(LINK_TYPE, new LinkBuilder());
	builders.put(IMAGE_BOX_TYPE, new ImageBoxBuilder());
	
	// Layout types
	builders.put(GRID_LAYOUT_TYPE, new GridLayoutBuilder());
	builders.put(BOX_LAYOUT_TYPE, new BoxLayoutBuilder());
	builders.put(HORIZONTAL_LAYOUT_TYPE, new HorizontalLayoutBuilder());
	builders.put(VERTICAL_LAYOUT_TYPE, new VerticalLayoutBuilder());
	builders.put(BORDER_LAYOUT_TYPE, new BorderLayoutBuilder());
	builders.put(FIT_LAYOUT_TYPE, new FitLayoutBuilder());
	builders.put(CARD_LAYOUT_TYPE, new CardLayoutBuilder());

	// Layout Data types
	builders.put(GRID_DATA_TYPE, new GridDataBuilder());
	builders.put(BOX_DATA_TYPE, new BoxDataBuilder());
	builders.put(BORDER_DATA_TYPE, new BorderDataBuilder());

	// Fields
	builders.put(TEXT_BOX_TYPE, new TextBoxBuilder());
	builders.put(TEXT_AREA_TYPE, new TextAreaBuilder());
	builders.put(TEXT_FIELD_TYPE, new TextFieldBuilder());
	builders.put(PASSWORD_FIELD_TYPE, new PasswordFieldBuilder());

	builders.put(NUMBER_FIELD_TYPE, new NumberFieldBuilder());
	builders.put(INTEGER_FIELD_TYPE, new IntegerFieldBuilder());
	builders.put(CURRENCY_FIELD_TYPE, new CurrencyFieldBuilder());
	builders.put(PERCENT_FIELD_TYPE, new PercentFieldBuilder());
	builders.put(QUANTITY_FIELD_TYPE, new QuantityFieldBuilder());
	builders.put(SPINNER_FIELD_TYPE, new SpinnerFieldBuilder());
	
	builders.put(SLIDER_TYPE, new SliderBuilder());
	
	builders.put(DATE_FIELD_TYPE, new DateFieldBuilder());
	builders.put(TIME_FIELD_TYPE, new TimeFieldBuilder());
	builders.put(DATE_TIME_FIELD_TYPE, new DateTimeFieldBuilder());
	
	builders.put(CHECK_BOX_TYPE, new CheckBoxBuilder());
	builders.put(RADIO_BUTTON_TYPE, new RadioButtonBuilder());
	builders.put(RADIO_GROUP_TYPE, new RadioGroupBuilder());
	
	builders.put(COMBO_BOX_TYPE, new ComboBoxBuilder());
	builders.put(LOOKUP_FIELD_TYPE, new LookupFieldBuilder());
	builders.put(LIST_BOX_TYPE, new ListBoxBuilder());

	builders.put(TABLE_TYPE, new TableBuilder());
	builders.put(TREE_TYPE, new TreeBuilder());
	
	builders.put(WINDOW_TYPE, new WindowBuilder());
	builders.put(FRAME_TYPE, new FrameBuilder());
	builders.put(DIALOG_TYPE, new DialogBuilder());
	
	//builders.put(FORM_TYPE, new FormBuilder());
	builders.put(LIST_FORM_TYPE, new ListFormBuilder());
	builders.put(EDIT_FORM_TYPE, new EditFormBuilder());
	
	builders.put(MENU_BAR_TYPE, new MenuBarBuilder());
	builders.put(MENU_TYPE, new MenuBuilder());
	builders.put(MENU_ITEM_TYPE, new MenuItemBuilder());
	builders.put(MENU_SEPARATOR_TYPE, new MenuSeparatorBuilder());

	builders.put(TOOL_BAR_TYPE, new ToolBarBuilder());
	builders.put(TOOL_ITEM_TYPE, new ToolItemBuilder());
	builders.put(TOOL_SEPARATOR_TYPE, new ToolSeparatorBuilder());

    }
    
    
    public UIBuilder() {
    }

    /**
     * Build UIObject by IData
     * @param data
     * @return
     */
    public UIObject buildObject(IData data) {
	IUIBuilder builder = getBuilder(data, true);
	return builder.buildObject(data);
    }
    
    /**
     * Get UIBuilder by type
     * @param type
     * @return
     */
    public static IUIBuilder getBuilder(String type) {
	return getBuilder(type, true);
    }
    
    /**
     * Get UIBuilder by type
     * @param type
     * @param check
     * @return
     */
    public static IUIBuilder getBuilder(String type, boolean check) {
	IUIBuilder builder = builders.get(type);
	if (builder == null) {
	    if (check) {
		throw new BuildException("Builder not found: type='" + type + "'");
	    }
	}
	return builder;
    }
    
    public static IUIBuilder getBuilder(IData data, boolean check) {
	if (data == null) {
	    if (check) {
		throw new BuildException("Builder not found: Data must be not null");
	    }
	    return null;
	}
	String baseType = (String) data.get(UIBuilder.SYS_PROPERTY_TYPE);
	String type = (String) data.get(UIObject.PROPERTY_TYPE);
	return getBuilder(baseType, type, check);
    }

    public static IUIBuilder getBuilder(String baseType, String type, boolean check) {
	if (baseType == null && type == null) {
	    if (check) {
		throw new BuildException("Builder not found: baseType or type must be not null");
	    }
	    return null;
	}

	if (type != null) {    
	    
	    // Use 'type' because this type is priority 
	    IUIBuilder builder = findBuilder(type);
	    if (builder == null) {
		
		// Safe mode: use 'baseType'
		if (baseType != null) {
		    
		    //TODO: WARN: 'type' not found, uses 'baseType'
		    builder = getBuilder(baseType, false);
		}
		
		if (builder == null) {
		    if (check) {
			throw new BuildException("Builder not found: baseType='" + baseType + "', type='" + type + "'");
		    }
		}
		
	    }
	    return builder;
	}
	
	return getBuilder(baseType, check);
    }

    private static IUIBuilder findBuilder(String type) {
	if (type == null) {
	    return null;
	}
	
	// Get builder by type is priority
	IUIBuilder builder = builders.get(type);
	if (builder != null) {
	    return builder;
	}
	
	// Find builder by type (use accept)
	Collection<IUIBuilder> gs = builders.values();
	for (IUIBuilder g : gs) {
	    if (g.accept(type)) {
		return g;
	    }
	}
	
	return null;
    }

    public static String getFullType(String typePackage, String typeName) {
	if (typeName == null || typePackage == null) {
	    return typeName;
	}
	return typePackage + "." + typeName;
    }    
 
}
