package org.plazmaforge.framework.uwt.swt.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;


/**
 * Theme for <code>CCalendar</code> widgets. Defines the colors applied to the
 * differents elements of the calendar.<p>
 * 
 * Some default themes are provided as constants of this class. The GRAY theme
 * is defined as the default for all new calendars. This can be changed with
 * the <code>setDefaultTheme()</code> method.<p>
 * 
 * To define a new theme, instantiate a new <code>CalendarTheme</code>. It
 * takes by default the same colors as the GRAY theme. Each color can then be
 * changed by the corresponding setter. Setters exist under 2 forms:
 * <ul>
 *   <li>A setter taking a <code>Color</code> parameter</li>
 *   <li>A setter taking an <code>int</code> parameter, corresponding to the SWT
 *     colors code</li>
 * </ul>
 */
//[WD]

public class CalendarTheme {
    
    
    /** GRAY theme. Default */
    public static final CalendarTheme GRAY;
    /** BLUE theme */
    public static final CalendarTheme BLUE;
    /** YELLOW theme */
    public static final CalendarTheme YELLOW;

    /** Default theme */
    public static CalendarTheme defaultTheme;

    /** Color for month header background */
    Color monthBack;
    /** Color for month header foreground */
    Color monthForg;
    /** Color for grid header (day names) background */
    Color headerBack;
    /** Color for grid header (day names) foreground */
    Color headerForg;
    /** Color for lines of grid */
    Color gridBack;
    /** Color for cells background */
    Color cellBack;
    /** Color for worked days cells foreground */
    Color dayForg;
    /** Color for adjascent days foreground */
    Color dayAdjForg;
    /** Color for week end foreground */
    Color dayWEForg;
    /** Color for today cell background */
    Color todayBack;
    /** Color for today cell foreground */
    Color todayForg;
    /** Color for selected cell background */
    Color selectBack;
    /** Color for selected cell foreground */
    Color selectForg;

    static {
	GRAY = new CalendarTheme();
	BLUE = createBlueTheme();
	YELLOW = createYellowTheme();
	defaultTheme = GRAY;
    }

    /**
     * Constructs a new instance of this class. All colors elements are
     * initialized with the default GRAY theme.
     */
    public CalendarTheme() {
	Display display = Display.getCurrent();
	this.monthBack = display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND);
	this.monthForg = display.getSystemColor(SWT.COLOR_WIDGET_FOREGROUND);
	this.headerBack = this.monthBack;
	this.headerForg = this.monthForg;
	this.gridBack = display.getSystemColor(SWT.COLOR_GRAY);
	this.cellBack = display.getSystemColor(SWT.COLOR_WHITE);
	this.dayForg = this.monthForg;
	this.dayAdjForg = this.gridBack;
	this.dayWEForg = display.getSystemColor(SWT.COLOR_DARK_RED);
	this.todayBack = this.monthBack;
	this.todayForg = this.monthForg;
	this.selectBack = display.getSystemColor(SWT.COLOR_YELLOW);
	this.selectForg = this.monthForg;
    }

    /**
     * Creates the BLUE theme.
     * 
     * @return BLUE theme
     */
    private static CalendarTheme createBlueTheme() {
	Display display = Display.getCurrent();
	CalendarTheme theme = new CalendarTheme();

	theme.monthBack = new Color(display, 170, 190, 220);
	theme.headerBack = theme.monthBack;
	theme.cellBack = new Color(display, 190, 220, 240);
	theme.dayAdjForg = display.getSystemColor(SWT.COLOR_DARK_GRAY);
	theme.dayWEForg = display.getSystemColor(SWT.COLOR_RED);
	theme.todayBack = display.getSystemColor(SWT.COLOR_WHITE);
	theme.selectBack = display.getSystemColor(SWT.COLOR_YELLOW);

	return theme;
    }

    /**
     * Creates the YELLOW theme.
     * 
     * @return YELLOW theme
     */
    private static CalendarTheme createYellowTheme() {
	Display display = Display.getCurrent();
	CalendarTheme theme = new CalendarTheme();

	theme.monthBack = new Color(display, 190, 180, 60);
	theme.headerBack = theme.monthBack;
	theme.cellBack = new Color(display, 255, 255, 170);
	theme.dayAdjForg = display.getSystemColor(SWT.COLOR_DARK_GRAY);
	theme.dayWEForg = display.getSystemColor(SWT.COLOR_RED);
	theme.todayBack = display.getSystemColor(SWT.COLOR_GRAY);
	theme.selectBack = display.getSystemColor(SWT.COLOR_DARK_GREEN);
	theme.selectForg = display.getSystemColor(SWT.COLOR_WHITE);

	return theme;
    }

    /**
     * Returns the default theme.
     * 
     * @return default theme
     */
    public static CalendarTheme getDefaultTheme() {
	return defaultTheme;
    }

    /**
     * Sets a new default theme for all new <code>CCalendar</code> widgets.
     * 
     * @param defaultTheme
     *            new default theme
     */
    public static void setDefaultTheme(CalendarTheme defaultTheme) {
	CalendarTheme.defaultTheme = defaultTheme;
    }

    public void setCellBack(Color cellBack) {
	this.cellBack = cellBack;
    }

    public void setCellBack(int cellBack) {
	this.cellBack = Display.getCurrent().getSystemColor(cellBack);
    }

    public void setDayAdjForg(Color dayAdjForg) {
	this.dayAdjForg = dayAdjForg;
    }

    public void setDayAdjForg(int dayAdjForg) {
	this.dayAdjForg = Display.getCurrent().getSystemColor(dayAdjForg);
    }

    public void setDayForg(Color dayForg) {
	this.dayForg = dayForg;
    }

    public void setDayForg(int dayForg) {
	this.dayForg = Display.getCurrent().getSystemColor(dayForg);
    }

    public void setDayWEForg(Color dayWEForg) {
	this.dayWEForg = dayWEForg;
    }

    public void setDayWEForg(int dayWEForg) {
	this.dayWEForg = Display.getCurrent().getSystemColor(dayWEForg);
    }

    public void setGridBack(Color gridBack) {
	this.gridBack = gridBack;
    }

    public void setGridBack(int gridBack) {
	this.gridBack = Display.getCurrent().getSystemColor(gridBack);
    }

    public void setHeaderBack(Color headerBack) {
	this.headerBack = headerBack;
    }

    public void setHeaderBack(int headerBack) {
	this.headerBack = Display.getCurrent().getSystemColor(headerBack);
    }

    public void setHeaderForg(Color headerForg) {
	this.headerForg = headerForg;
    }

    public void setHeaderForg(int headerForg) {
	this.headerForg = Display.getCurrent().getSystemColor(headerForg);
    }

    public void setMonthBack(Color monthBack) {
	this.monthBack = monthBack;
    }

    public void setMonthBack(int monthBack) {
	this.monthBack = Display.getCurrent().getSystemColor(monthBack);
    }

    public void setMonthForg(Color monthForg) {
	this.monthForg = monthForg;
    }

    public void setMonthForg(int monthForg) {
	this.monthForg = Display.getCurrent().getSystemColor(monthForg);
    }

    public void setSelectBack(Color selectBack) {
	this.selectBack = selectBack;
    }

    public void setSelectBack(int selectBack) {
	this.selectBack = Display.getCurrent().getSystemColor(selectBack);
    }

    public void setSelectForg(Color selectForg) {
	this.selectForg = selectForg;
    }

    public void setSelectForg(int selectForg) {
	this.selectForg = Display.getCurrent().getSystemColor(selectForg);
    }

    public void setTodayBack(Color todayBack) {
	this.todayBack = todayBack;
    }

    public void setTodayBack(int todayBack) {
	this.todayBack = Display.getCurrent().getSystemColor(todayBack);
    }

    public void setTodayForg(Color todayForg) {
	this.todayForg = todayForg;
    }

    public void setTodayForg(int todayForg) {
	this.todayForg = Display.getCurrent().getSystemColor(todayForg);
    }
    
}
