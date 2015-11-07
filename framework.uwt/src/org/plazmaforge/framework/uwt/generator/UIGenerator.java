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

package org.plazmaforge.framework.uwt.generator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.builder.UIBuilder;
import org.plazmaforge.framework.uwt.generator.dialog.DialogGenerator;
import org.plazmaforge.framework.uwt.generator.form.EditFormGenerator;
import org.plazmaforge.framework.uwt.generator.form.ListFormGenerator;
import org.plazmaforge.framework.uwt.generator.layout.BorderLayoutGenerator;
import org.plazmaforge.framework.uwt.generator.layout.BoxDataGenerator;
import org.plazmaforge.framework.uwt.generator.layout.BoxLayoutGenerator;
import org.plazmaforge.framework.uwt.generator.layout.CardLayoutGenerator;
import org.plazmaforge.framework.uwt.generator.layout.FitLayoutGenerator;
import org.plazmaforge.framework.uwt.generator.layout.GridDataGenerator;
import org.plazmaforge.framework.uwt.generator.layout.GridLayoutGenerator;
import org.plazmaforge.framework.uwt.generator.layout.HorizontalLayoutGenerator;
import org.plazmaforge.framework.uwt.generator.layout.VerticalLayoutGenerator;
import org.plazmaforge.framework.uwt.generator.widget.ButtonGenerator;
import org.plazmaforge.framework.uwt.generator.widget.CheckBoxGenerator;
import org.plazmaforge.framework.uwt.generator.widget.ComboBoxGenerator;
import org.plazmaforge.framework.uwt.generator.widget.CompositeGenerator;
import org.plazmaforge.framework.uwt.generator.widget.CurrencyFieldGenerator;
import org.plazmaforge.framework.uwt.generator.widget.DateFieldGenerator;
import org.plazmaforge.framework.uwt.generator.widget.DateTimeFieldGenerator;
import org.plazmaforge.framework.uwt.generator.widget.FrameGenerator;
import org.plazmaforge.framework.uwt.generator.widget.ImageBoxGenerator;
import org.plazmaforge.framework.uwt.generator.widget.IntegerFieldGenerator;
import org.plazmaforge.framework.uwt.generator.widget.LabelGenerator;
import org.plazmaforge.framework.uwt.generator.widget.LinkGenerator;
import org.plazmaforge.framework.uwt.generator.widget.ListBoxGenerator;
import org.plazmaforge.framework.uwt.generator.widget.LookupFieldGenerator;
import org.plazmaforge.framework.uwt.generator.widget.NumberFieldGenerator;
import org.plazmaforge.framework.uwt.generator.widget.PasswordFieldGenerator;
import org.plazmaforge.framework.uwt.generator.widget.PercentFieldGenerator;
import org.plazmaforge.framework.uwt.generator.widget.QuantityFieldGenerator;
import org.plazmaforge.framework.uwt.generator.widget.RadioButtonGenerator;
import org.plazmaforge.framework.uwt.generator.widget.RadioGroupGenerator;
import org.plazmaforge.framework.uwt.generator.widget.SeparatorGenerator;
import org.plazmaforge.framework.uwt.generator.widget.SliderGenerator;
import org.plazmaforge.framework.uwt.generator.widget.SpinnerFieldGenerator;
import org.plazmaforge.framework.uwt.generator.widget.TextAreaGenerator;
import org.plazmaforge.framework.uwt.generator.widget.TextBoxGenerator;
import org.plazmaforge.framework.uwt.generator.widget.TextFieldGenerator;
import org.plazmaforge.framework.uwt.generator.widget.TimeFieldGenerator;
import org.plazmaforge.framework.uwt.generator.widget.ToggleButtonGenerator;
import org.plazmaforge.framework.uwt.generator.widget.WindowGenerator;
import org.plazmaforge.framework.uwt.generator.widget.menu.MenuBarGenerator;
import org.plazmaforge.framework.uwt.generator.widget.menu.MenuGenerator;
import org.plazmaforge.framework.uwt.generator.widget.menu.MenuItemGenerator;
import org.plazmaforge.framework.uwt.generator.widget.menu.MenuSeparatorGenerator;
import org.plazmaforge.framework.uwt.generator.widget.panel.CollapsePanelGenerator;
import org.plazmaforge.framework.uwt.generator.widget.panel.GroupPanelGenerator;
import org.plazmaforge.framework.uwt.generator.widget.panel.HorizontalPanelGenerator;
import org.plazmaforge.framework.uwt.generator.widget.panel.PanelGenerator;
import org.plazmaforge.framework.uwt.generator.widget.panel.SplitPanelGenerator;
import org.plazmaforge.framework.uwt.generator.widget.panel.TabItemGenerator;
import org.plazmaforge.framework.uwt.generator.widget.panel.TabPanelGenerator;
import org.plazmaforge.framework.uwt.generator.widget.panel.TitlePanelGenerator;
import org.plazmaforge.framework.uwt.generator.widget.panel.VerticalPanelGenerator;
import org.plazmaforge.framework.uwt.generator.widget.table.TableGenerator;
import org.plazmaforge.framework.uwt.generator.widget.tool.ToolBarGenerator;
import org.plazmaforge.framework.uwt.generator.widget.tool.ToolItemGenerator;
import org.plazmaforge.framework.uwt.generator.widget.tool.ToolSeparatorGenerator;
import org.plazmaforge.framework.uwt.generator.widget.tree.TreeGenerator;

public class UIGenerator {

    
    /**
     * General UWT package
     */
    public static final String UWT_PACKAGE = "org.plazmaforge.framework.uwt";
    
    /**
     * UWT Widget package
     */
    public static final String UWT_WIDGET_PACKAGE = UWT_PACKAGE + ".widget"; 

    /**
     * UWT Layout package
     */
    public static final String UWT_LAYOUT_PACKAGE = UWT_PACKAGE + ".layout";
    
    
    /**
     * UWT Graphics package
     */
    public static final String UWT_GRAPHICS_PACKAGE = UWT_PACKAGE + ".graphics";
    

    /**
     * UWT Panel package
     */
    public static final String UWT_PANEL_PACKAGE = UWT_WIDGET_PACKAGE + ".panel"; 

    /**
     * UWT Dialog package
     */
    public static final String UWT_DIALOG_PACKAGE = UWT_PACKAGE + ".dialog"; 

    /**
     * UWT Wizard package
     */
    public static final String UWT_WIZARD_PACKAGE = UWT_PACKAGE + ".wizard"; 
    
    /**
     * UWT View package
     */
    public static final String UWT_VIEW_PACKAGE = UWT_PACKAGE + ".view"; 

    /**
     * UWT Form package
     */
    public static final String UWT_FORM_PACKAGE = UWT_PACKAGE + ".form"; 
    
    
    

    /**
     * Java classes
     */
    public static final String DATE_CLASS = "java.util.Date";
    
    public static final String LIST_CLASS = "java.util.List";
    
    public static final String SET_CLASS = "java.util.Set";
    
    public static final String MAP_CLASS = "java.util.Map";
    
    public static final String CALENDAR_CLASS = "java.util.Calendar";
    
    public static final String GREGORIAN_CALENDAR_CLASS = "java.util.GregorianCalendar";

    
    /**
     * UWT Graphics classes
     */
    
    public static final String COLOR_CLASS = UWT_GRAPHICS_PACKAGE + ".Color";
    
    public static final String FONT_CLASS = UWT_GRAPHICS_PACKAGE + ".Font";
    
    
    /**
     * UWT Style classes/enums
     */
    
    public static final String STYLE_CLASS = UWT_WIDGET_PACKAGE + ".Style";
    
    public static final String HORIZONTAL_ALIGN_CLASS = STYLE_CLASS + ".HorizontalAlign";
    
    public static final String VERTICAL_ALIGN_CLASS = STYLE_CLASS + ".VerticalAlign";
    
    public static final String DIRECTION_CLASS = STYLE_CLASS + ".Direction";
    
    public static final String ORIENTATION_CLASS = STYLE_CLASS + ".Orientation";
    
    public static final String LAYOUT_REGION_CLASS = STYLE_CLASS + ".LayoutRegion";
    
    


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // $type			'widget.Composite'
    // type			'StyleComposite'
    // name			'MyComposite'
    // genearatePackage		'com.mycompany.myproject.mypackage'
    // generateClass		'com.mycompany.myproject.mypackage.MyComposite' (genearatePackage + '.' + name)
    // generateSuperClass	'org.plazmaforge.uwt.widget.Composite' (getClass by 'widget.Composite' and 'StyleComposite')
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    
    //////////////////////////////////////////////////////////////////////
    // GENERATORS
    //////////////////////////////////////////////////////////////////////
    
    
    private static Map<String, IUIGenerator> generators = new HashMap<String, IUIGenerator>();
    
    
    
    ///////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Register generators
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////
    
    static {
	
	// Base types
	generators.put(UIBuilder.COMPOSITE_TYPE, new CompositeGenerator());
	generators.put(UIBuilder.PANEL_TYPE, new PanelGenerator());
	generators.put(UIBuilder.HORIZONTAL_PANEL_TYPE, new HorizontalPanelGenerator());
	generators.put(UIBuilder.VERTICAL_PANEL_TYPE, new VerticalPanelGenerator());
	generators.put(UIBuilder.GROUP_PANEL_TYPE, new GroupPanelGenerator());
	generators.put(UIBuilder.TITLE_PANEL_TYPE, new TitlePanelGenerator());
	generators.put(UIBuilder.COLLAPSE_PANEL_TYPE, new CollapsePanelGenerator());
	generators.put(UIBuilder.SPLIT_PANEL_TYPE, new SplitPanelGenerator());
	generators.put(UIBuilder.TAB_PANEL_TYPE, new TabPanelGenerator());
	generators.put(UIBuilder.TAB_ITEM_TYPE, new TabItemGenerator());
	
	
	generators.put(UIBuilder.LABEL_TYPE, new LabelGenerator());
	generators.put(UIBuilder.SEPARATOR_TYPE, new SeparatorGenerator());
	generators.put(UIBuilder.BUTTON_TYPE, new ButtonGenerator());
	generators.put(UIBuilder.TOGGLE_BUTTON_TYPE, new ToggleButtonGenerator());
	generators.put(UIBuilder.LINK_TYPE, new LinkGenerator());
	generators.put(UIBuilder.IMAGE_BOX_TYPE, new ImageBoxGenerator());
	
	
	// Layout types
	generators.put(UIBuilder.GRID_LAYOUT_TYPE, new GridLayoutGenerator());
	generators.put(UIBuilder.BOX_LAYOUT_TYPE, new BoxLayoutGenerator());
	generators.put(UIBuilder.HORIZONTAL_LAYOUT_TYPE, new HorizontalLayoutGenerator());
	generators.put(UIBuilder.VERTICAL_LAYOUT_TYPE, new VerticalLayoutGenerator());
	generators.put(UIBuilder.BORDER_LAYOUT_TYPE, new BorderLayoutGenerator());
	generators.put(UIBuilder.FIT_LAYOUT_TYPE, new FitLayoutGenerator());
	generators.put(UIBuilder.CARD_LAYOUT_TYPE, new CardLayoutGenerator());
	
	// Layout Data types
	generators.put(UIBuilder.GRID_DATA_TYPE, new GridDataGenerator());
	generators.put(UIBuilder.BOX_DATA_TYPE, new BoxDataGenerator());


	// Fields
	generators.put(UIBuilder.TEXT_BOX_TYPE, new TextBoxGenerator());
	generators.put(UIBuilder.TEXT_FIELD_TYPE, new TextFieldGenerator());
	generators.put(UIBuilder.TEXT_AREA_TYPE, new TextAreaGenerator());
	generators.put(UIBuilder.PASSWORD_FIELD_TYPE, new PasswordFieldGenerator());
	
	generators.put(UIBuilder.NUMBER_FIELD_TYPE, new NumberFieldGenerator());
	generators.put(UIBuilder.INTEGER_FIELD_TYPE, new IntegerFieldGenerator());
	generators.put(UIBuilder.CURRENCY_FIELD_TYPE, new CurrencyFieldGenerator());
	generators.put(UIBuilder.PERCENT_FIELD_TYPE, new PercentFieldGenerator());
	generators.put(UIBuilder.QUANTITY_FIELD_TYPE, new QuantityFieldGenerator());
	generators.put(UIBuilder.SPINNER_FIELD_TYPE, new SpinnerFieldGenerator());
	
	generators.put(UIBuilder.SLIDER_TYPE, new SliderGenerator());

	generators.put(UIBuilder.DATE_FIELD_TYPE, new DateFieldGenerator());
	generators.put(UIBuilder.TIME_FIELD_TYPE, new TimeFieldGenerator());
	generators.put(UIBuilder.DATE_TIME_FIELD_TYPE, new DateTimeFieldGenerator());
	
	
	generators.put(UIBuilder.CHECK_BOX_TYPE, new CheckBoxGenerator());
	generators.put(UIBuilder.RADIO_BUTTON_TYPE, new RadioButtonGenerator());
	generators.put(UIBuilder.RADIO_GROUP_TYPE, new RadioGroupGenerator());
	
	generators.put(UIBuilder.COMBO_BOX_TYPE, new ComboBoxGenerator());
	generators.put(UIBuilder.LOOKUP_FIELD_TYPE, new LookupFieldGenerator());
	generators.put(UIBuilder.LIST_BOX_TYPE, new ListBoxGenerator());
	
	generators.put(UIBuilder.TABLE_TYPE, new TableGenerator());
	generators.put(UIBuilder.TREE_TYPE, new TreeGenerator());
	
	generators.put(UIBuilder.WINDOW_TYPE, new WindowGenerator());
	generators.put(UIBuilder.FRAME_TYPE, new FrameGenerator());
	generators.put(UIBuilder.DIALOG_TYPE, new DialogGenerator());
	
	generators.put(UIBuilder.LIST_FORM_TYPE, new ListFormGenerator());
	generators.put(UIBuilder.EDIT_FORM_TYPE, new EditFormGenerator());
	
	generators.put(UIBuilder.MENU_BAR_TYPE, new MenuBarGenerator());
	generators.put(UIBuilder.MENU_TYPE, new MenuGenerator());
	generators.put(UIBuilder.MENU_ITEM_TYPE, new MenuItemGenerator());
	generators.put(UIBuilder.MENU_SEPARATOR_TYPE, new MenuSeparatorGenerator());

	generators.put(UIBuilder.TOOL_BAR_TYPE, new ToolBarGenerator());
	generators.put(UIBuilder.TOOL_ITEM_TYPE, new ToolItemGenerator());
	generators.put(UIBuilder.TOOL_SEPARATOR_TYPE, new ToolSeparatorGenerator());
    }

    public UIGenerator() {
    }

    
    public String generateClass(GeneratorContext context, IData data) {
	if (context == null) {
	    throw new GenerateException("GeneratorContext must be not null");
	}
	IUIGenerator generator = getGenerator(data, true);
	return generator.generateClass(context, data);
    }
    
    public static IUIGenerator getGenerator(String type) {
	return getGenerator(type, true);
    }
    
    public static IUIGenerator getGenerator(String type, boolean check) {
	IUIGenerator generator = generators.get(type);
	if (generator == null) {
	    if (check) {
		throw new GenerateException("Generator not found: type='" + type + "'");
	    }
	}
	return generator;
    }
    
    public static IUIGenerator getGenerator(IData data, boolean check) {
	if (data == null) {
	    if (check) {
		throw new GenerateException("Generator not found: Data must be not null");
	    }
	    return null;
	}
	String type = (String) data.get(UIBuilder.SYS_PROPERTY_TYPE);
	String customType = (String) data.get(UIObject.PROPERTY_TYPE);
	
	if (type == null && customType == null) {
	    if (check) {
		String name = (String) data.get(UIBuilder.SYS_PROPERTY_NAME);
		throw new GenerateException("Generator not found: type or customType must be not null" + (name == null ? "": " of node '" + name + "'"));
	    }
	    return null;
	}
	
	return getGenerator(type, customType, check);
    }
    
    public static IUIGenerator getGenerator(String type, String customType, boolean check) {
	if (type == null && customType == null) {
	    if (check) {
		throw new GenerateException("Generator not found: type or customType must be not null");
	    }
	    return null;
	}
	if (customType != null) {
	    
	    // Use 'customType' because this type is priority 
	    IUIGenerator generator = findGenerator(customType);
	    if (generator == null) {
		
		// Safe mode: use 'type'
		if (type != null) {

		    // TODO: WARN: 'customType' not found, uses 'type'
		    generator = getGenerator(type, false);
		}				
				
		if (generator == null) {
		    if (check) {
			throw new GenerateException("Generator not found: type='" + type + "', customType='" + customType + "'");
		    }
		}
		
	    }
	    return generator;
	}
	
	return getGenerator(type, check);
    }
    
    private static IUIGenerator findGenerator(String type) {
	if (type == null) {
	    return null;
	}
	

	// Get generator by type is priority
	IUIGenerator generator = generators.get(type);
	if (generator != null) {
	    return generator;
	}
	
	// Find generator by type (use accept)
	Collection<IUIGenerator> gs = generators.values();
	for (IUIGenerator g: gs) {
	    if (g.accept(type)) {
		return g;
	    }
	}
	return null;
    }
}
