package org.plazmaforge.framework.uwt.swt.widget;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.MenuListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TypedListener;


/**
 * Calendar widget. Presents the monthly view of a calendar for date picking.<p>
 * 
 * Calendar is composed of a header and a grid for date selection. The header
 * display the current month and year, and the two buttons for navigation
 * (previous and next month)<p>
 * 
 * Features:
 * <ul>
 *   <li>Month names, weekday names and first day of week depend of the locale
 *     setted on the calendar</li>
 *   <li>Colors are customizable through color themes (3 provided)</li>
 *   <li>Shows days from adjacent months</li>
 * </ul><p>
 * 
 * To know which date as been selected in the calendar, there is to means :
 * <ul>
 *   <li>The <code>getSelectedDate()</code> method returns the currently
 *     selected date</li>
 *   <li>Add a <code>CalendarListener</code> to the calendar. This listener
 *     will be notified of selection and double click events.</li>
 * </ul>
 */
//[WD]
public class CCalendar extends Composite {
    
	private static final int HEADER_SPACING = 3;

	/** Locale used for localized names and formats */
	protected Locale locale;
	/** The today date */
	protected Calendar todayDate;
	/** Date of 1st day of the currently displayed month */
	protected Calendar curDate;
	/** Date of the currently selected date */
	protected Calendar selDate;
	/** Format for the display of month and year in the header */
	protected SimpleDateFormat sdf;
	/** Index of the first day of week */
	protected int firstDayOfWeek;
	/** Flag to set grid visible or not */
	protected boolean gridVisible = true;

	/** Header panel */
	private Composite header;
	/** Navigation button for previous month */
	private Button prevMonth;
	/** Label for the display of current month and year (corresponding to curDate) */
	private Label currentMonth;
	/** Navigation button for next month */
	private Button nextMonth;
	/** Popup menu for month selection */
	private Menu monthsMenu;

	/** Days labels panel */
	private Composite daysLabels;
	/** Days numbers panel */
	private Composite daysNumbers;
	/** Grid headers, displaying weekday names */
	private Label[] headers;
	/** Grid cells, displaying the days of the current month */
	private Label[] days;
	/** Color theme */
	private CalendarTheme colors;
	/** Currently selected cell (corresponding to selDate) */
	private Label selectedCell;
	/** Today cell (corresponding to todayDate) */
	private Label todayCell;

	/** Listener for mouse events */
	private MouseListener mouseListener;

	private boolean resize = false;
	

	/**
* Constructs a new instance of this class given its parent and a style value
* describing its behavior and appearance.<p>
* The calendar is initialized by default with the default Locale, and the
* current date for today and selected date attributes.
* 
* @param parent a composite control which will be the parent of the new instance (cannot be null)
* @param style the style of control to construct
*/
	public CCalendar(Composite parent, int style) {
		super(parent, style);

		initialize();
		setTheme(CalendarTheme.getDefaultTheme());

		setLocale(Locale.getDefault());
		Date today = new Date();
		setTodayDate(today);
		setSelectedDate(today);
	}

	/**
	 * Adds the listener to the collection of listeners who will
	 * be notified when the receiver's selection changes, by sending
	 * it one of the messages defined in the <code>SelectionListener</code>
	 * interface.
	 * <p>
	 * <code>widgetSelected</code> is called when the combo's list selection changes.
	 * </p>
	 *
	 * @param listener the listener which should be notified
	 * @see SelectionListener
	 * @see #removeSelectionListener
	 */
	public void addSelectionListener(SelectionListener listener) {
		checkWidget();
		if ( listener == null ) SWT.error(SWT.ERROR_NULL_ARGUMENT);
		TypedListener typedListener = new TypedListener(listener);
		addListener(SWT.Selection, typedListener);
	}

	/**
	 * Returns the preferred size of the receiver.
	 * 
	 * @param wHint the width hint (can be SWT.DEFAULT)
	 * @param hHint the height hint (can be SWT.DEFAULT)
	 * @param changed <code>true</code> if the control's contents have changed, and <code>false</code> otherwise
	 * @return the preferred size of the control.
	 */
	@Override
	public Point computeSize(int wHint, int hHint, boolean changed) {
		if ( resize ) {
			GC gc = new GC(currentMonth);
			int width = 0;
			for (String m : sdf.getDateFormatSymbols().getMonths()) {
				width = Math.max(width, gc.textExtent(m).x);
			}
			width += prevMonth.computeSize(SWT.DEFAULT, SWT.DEFAULT, changed).x * 2
							 + HEADER_SPACING * 4 + gc.textExtent(" 9999").x;
			width = Math.max(width, gc.textExtent("99").x * 7 + 8);
			int cw = (width - 8) / 7 + 1;
			width = cw * 7 + 8;
			((GridData) header.getLayoutData()).widthHint = width;

			for (int i = 0; i < 7; i++) {
				((GridData) headers[i].getLayoutData()).widthHint = cw;
				((GridData) days[i].getLayoutData()).widthHint = cw;
			}

			gc.dispose();
			resize = false;
		}

		return super.computeSize(wHint, hHint, changed);
	}

	/**
	 * Creates the grid of labels displying the days numbers. The grid is composed
	 * of a header row, displaying days initiales, and 6 row for the days numbers.
	 * All labels are empty (no text displayed), the content depending of both the
	 * locale (for first day of week) and the current displayed month.
	 */
	private void createGrid() {
		// Days labels panel
		daysLabels = new Composite(this, SWT.NONE);
		GridLayout layout1 = new GridLayout(7, true);
		layout1.horizontalSpacing = 1;
		layout1.marginWidth = 1;
		layout1.verticalSpacing = 1;
		layout1.marginTop = 1;
		layout1.marginHeight = 0;
		daysLabels.setLayout(layout1);
		headers = new Label[7];
		for (int i = 0; i < 7; i++) {
			headers[i] = new Label(daysLabels, SWT.CENTER);
			headers[i].setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		}

		// Grid of days numbers
		daysNumbers = new Composite(this, SWT.NONE);
		GridLayout layout2 = new GridLayout(7, true);
		layout2.horizontalSpacing = 1;
		layout2.marginWidth = 1;
		layout2.verticalSpacing = 1;
		layout2.marginHeight = 1;
		daysNumbers.setLayout(layout2);
		days = new Label[42];
		for (int i = 0; i < 42; i++) {
			days[i] = new Label(daysNumbers, SWT.CENTER);
			days[i].setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			days[i].addMouseListener(mouseListener);
		}
	}

	/**
	 * Creates the header of the calendar. The header contains the label
	 * displaying the current month and year, and the two buttons for navigation :
	 * previous and next month.
	 */
	private void createHeader() {
		header = new Composite(this, SWT.NONE);

		GridLayout layout = new GridLayout(3, false);
		layout.horizontalSpacing = HEADER_SPACING;
		layout.marginWidth = HEADER_SPACING;
		layout.verticalSpacing = 0;
		layout.marginHeight = 3;
		header.setLayout(layout);
		header.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		prevMonth = new Button(header, SWT.ARROW | SWT.LEFT | SWT.FLAT);
		prevMonth.addMouseListener(mouseListener);

		currentMonth = new Label(header, SWT.CENTER);
		currentMonth.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		nextMonth = new Button(header, SWT.ARROW | SWT.RIGHT | SWT.FLAT);
		nextMonth.addMouseListener(mouseListener);

		monthsMenu = new Menu(getShell(), SWT.POP_UP);
		currentMonth.setMenu(monthsMenu);
		SelectionListener sl = new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				curDate.set(Calendar.MONTH, (Integer) e.widget.getData());
				refreshDisplay();
			}

			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		};
		for (int i = 0; i < 12; i++) {
			MenuItem item = new MenuItem(monthsMenu, SWT.PUSH);
			item.addSelectionListener(sl);
			item.setData(i);
		}
		monthsMenu.addMenuListener(new MenuListener() {
			public void menuHidden(MenuEvent e) {
			}

			public void menuShown(MenuEvent e) {
				monthsMenu.setDefaultItem(monthsMenu.getItems()[curDate.get(Calendar.MONTH)]);
			}
		});
	}

	/**
	 * Displays a new month in the grid. The new month is specified by delta from
	 * the currently displayed one.
	 * 
	 * @param add delta
	 */
	protected void displayMonth(int add) {
		if ( add == 0 ) {
			return;
		}

		int year  = curDate.get(Calendar.YEAR);
		int month = curDate.get(Calendar.MONTH);

		if ( add == 12 || add == -12 ) {
			year += add / 12;
		} else if ( add == 1 && month == 11 ) {
			year++;
			month = 0;
		} else if ( add == -1 && month == 0 ) {
			year--;
			month=11;
		} else {
			month += add;
		}
		curDate.set(Calendar.YEAR, year);
		curDate.set(Calendar.MONTH, month);

		refreshDisplay();
	}

	/**
	 * Returns the selected date.
	 * 
	 * @return selected date
	 */
	public Date getSelectedDate() {
		return this.selDate.getTime();
	}

	/**
	 * Returns the today date.
	 * 
	 * @return today date
	 */
	public Date getTodayDate() {
		return this.todayDate.getTime();
	}

	/**
	 * Constructs and initializes all the GUI of the calendar.
	 */
	private void initialize() {
		GridLayout layout = new GridLayout(1, false);
		layout.horizontalSpacing = 0;
		layout.marginWidth = 0;
		layout.verticalSpacing = 0;
		layout.marginHeight = 0;
		this.setLayout(layout);

		mouseListener = new MouseListener() {
			public void mouseDoubleClick(MouseEvent e) {
			}

			public void mouseDown(MouseEvent e) {
			}

			public void mouseUp(MouseEvent e) {
				boolean ctrl = (e.stateMask & SWT.CTRL) != 0;
				if ( e.widget == prevMonth ) {
					displayMonth(ctrl ? -12 : -1);
				} else if ( e.widget == nextMonth ) {
					displayMonth(ctrl ? 12 : 1);
				} else {
					selectCell((Label) e.widget);
				}
			}
		};
		createHeader();
		createGrid();
		resize = true;
	}

	/**
	 * Returns true if grid is visible.
	 * 
	 * @return Returns the grid visible status.
	 */
	public boolean isGridVisible() {
		return this.gridVisible;
	}

	/**
	 * Refreshes the display of the grid and header. This can be need because of
	 * a month display, a locale or color model change.
	 */
	private void refreshDisplay() {
		if ( curDate == null ) {
			return;
		}
		this.setRedraw(false);
		currentMonth.setText(sdf.format(curDate.getTime()));

		selectedCell = null;
		todayCell		 = null;

		int maxDay = curDate.getActualMaximum(Calendar.DAY_OF_MONTH);
		Calendar cal = (Calendar) curDate.clone();
		int delta = -((cal.get(Calendar.DAY_OF_WEEK) - firstDayOfWeek + 7) % 7);
		cal.add(Calendar.DAY_OF_MONTH, delta);
		for (int i = 0; i < 42; i++) {
			int weekDay = cal.get(Calendar.DAY_OF_WEEK);
			days[i].setText("" + cal.get(Calendar.DAY_OF_MONTH));

			if ( cal.equals(selDate) ) {
				days[i].setBackground(colors.selectBack);
				days[i].setForeground(colors.selectForg);
			} else if ( cal.equals(todayDate) ) {
				days[i].setBackground(colors.todayBack);
				days[i].setForeground(colors.todayForg);
			} else {
				days[i].setBackground(colors.cellBack);
				days[i].setForeground(delta < 0 || delta >= maxDay
															? colors.dayAdjForg
															: ((weekDay > 1 && weekDay < 7)
																	? colors.dayForg
																	: colors.dayWEForg));
			}

			if ( cal.equals(selDate) ) {
				selectedCell = days[i];
			}
			if ( cal.equals(todayDate) ) {
				todayCell = days[i];
			}

			Calendar dayCal = (Calendar) days[i].getData();
			if ( dayCal == null ) {
				dayCal = (Calendar) cal.clone();
				days[i].setData(dayCal);
			} else {
				dayCal.setTimeInMillis(cal.getTimeInMillis());
			}

			cal.add(Calendar.DAY_OF_MONTH, 1);
			delta++;
		}
		this.setRedraw(true);
	}

	/**
	 * Removes the listener from the collection of listeners who will
	 * be notified when the receiver's selection changes.
	 *
	 * @param listener the listener which should no longer be notified
	 * @see SelectionListener
	 * @see #addSelectionListener
	 */
	public void removeSelectionListener(SelectionListener listener) {
		checkWidget();
		if ( listener == null ) SWT.error(SWT.ERROR_NULL_ARGUMENT);
		removeListener(SWT.Selection, listener);
	}

	/**
	 * Selects a new cell in the calendar.<p>
	 * 
	 * The previously selected day returns to his original state, and the new one
	 * is selected. A <code>CalendarEvent</code> is sent to all calendar listeners
	 * to notify the new selection.<p>
	 * 
	 * If navigation buttons in the header have been desactivated, the selection
	 * of an adjascent day is not allowed.
	 * 
	 * @param cell new selected cell
	 */
	protected void selectCell(Label cell) {
	    
	    
	    	// OHA: Disable because we have some problems with select current date (current date may be is not today)
	    	/*
		if ( cell == selectedCell ) {
			return;
		}
		*/

		// Get the date for the new selection
		Calendar cal = (Calendar) cell.getData();

		// Check for adjascent days if navigation is desactivated
		int month = selDate.get(Calendar.MONTH);
		int year  = selDate.get(Calendar.YEAR);
		if ( (month != cal.get(Calendar.MONTH)
				  || year != cal.get(Calendar.YEAR))
				  && ! prevMonth.isVisible() ) {
			return;
		}

		// Restore state of previously selected cell
		if ( selectedCell != null ) {
			if ( selectedCell == todayCell ) {
				selectedCell.setBackground(colors.todayBack);
				selectedCell.setForeground(colors.todayForg);
			} else {
				int weekDay = ((Calendar) selectedCell.getData()).get(Calendar.DAY_OF_WEEK);
				selectedCell.setBackground(colors.cellBack);
				selectedCell.setForeground((weekDay > 1 && weekDay < 7)
																	 ? colors.dayForg
																	 : colors.dayWEForg);

			}
		}

		// Store new selected date
		selDate.setTimeInMillis(cal.getTimeInMillis());

		// Mark the cell as selected, with month view change if needed
		selectedCell = cell;
		if ( month != selDate.get(Calendar.MONTH)
				 || year != selDate.get(Calendar.YEAR)) {
			curDate.set(Calendar.MONTH, selDate.get(Calendar.MONTH));
			curDate.set(Calendar.YEAR, selDate.get(Calendar.YEAR));
			refreshDisplay();
		} else {
			selectedCell.setBackground(colors.selectBack);
			selectedCell.setForeground(colors.selectForg);
		}

		// Send selection event to the listeners
		Event event	= new Event();
		event.data	= selDate.getTime();
		notifyListeners(SWT.Selection, event);
		
	}

	/**
	 * Sets the header's navigation buttons visibles or not.
	 * 
	 * @param visible true if visible, false else
	 */
	public void setButtonsVisibles(boolean visible) {
		prevMonth.setVisible(visible);
		nextMonth.setVisible(visible);
		if ( visible ) {
			currentMonth.setMenu(monthsMenu);
		} else {
			currentMonth.setMenu(null);
		}
	}

	/**
	 * Sets the font that the receiver will use to paint textual information to
	 * the font specified by the argument, or to the default font for that kind
	 * of control if the argument is null.<p>
	 * 
	 * The new font is applied to all elements (labels) composing the calendar.
	 * The width of cells is adjusted.
	 * 
	 * @param font the new font (or null)
	 */
	@Override
	public void setFont(Font font) {
		super.setFont(font);
		currentMonth.setFont(font);
		for (Label l : headers) {
			l.setFont(font);
		}
		for (Label l : days) {
			l.setFont(font);
		}
		resize = true;
	}

	/**
	 * Sets the grid visible or not. By default, the grid is visible.
	 * 
	 * @param gridVisible <code>true</code> to set grid visible, else <code>false</code>
	 */
	public void setGridVisible(boolean gridVisible) {
		if ( gridVisible != this.gridVisible ) {
			this.gridVisible = gridVisible;
			daysLabels.setBackground(gridVisible ? colors.gridBack : colors.headerBack);
			daysNumbers.setBackground(gridVisible ? colors.gridBack : colors.cellBack);
		}
	}

	/**
	 * Sets the initiales of days displayed in the header row of the grid.
	 * Depends of the <code>locale</code> attribute for days names and the first
	 * day of week.
	 */
	private void setHeaders() {
		DateFormatSymbols symboles = sdf.getDateFormatSymbols();
		String[] sn = symboles.getShortWeekdays();
		String[] ln = symboles.getWeekdays();
		int i = firstDayOfWeek;
		for (Label h : headers) {
			h.setText(sn[i].substring(0, 1).toUpperCase());
			h.setToolTipText(ln[i]);
			i = (i % 7) + 1;
		}
	}

	/**
	 * Sets a new locale to use for calendar. Locale will define the names of
	 * months and days, and the first day of week.
	 * 
	 * @param locale new locale (must not be null)
	 */
	public void setLocale(Locale locale) {
		if ( locale == null ) SWT.error(SWT.ERROR_NULL_ARGUMENT);

		this.locale = locale;
		sdf = new SimpleDateFormat("MMMM yyyy", locale);
		firstDayOfWeek = Calendar.getInstance(TimeZone.getDefault(), locale)
										 .getFirstDayOfWeek();

		if ( monthsMenu != null ) {
			setMenuItems();
		}

		setHeaders();
		refreshDisplay();
	}

	private void setMenuItems() {
		String[] months = sdf.getDateFormatSymbols().getMonths();
		MenuItem[] items = monthsMenu.getItems();
		for (int i = 0; i < 12; i++) {
			items[i].setText(months[i]);
		}
	}

	/**
	 * Sets the selected date. The grid is refreshed to display the corresponding
	 * month.
	 * 
	 * @param date new selected date (must not be null)
	 */
	public void setSelectedDate(Date date) {
		if ( date == null ) SWT.error(SWT.ERROR_NULL_ARGUMENT);

		if ( selDate == null ) {
			selDate = Calendar.getInstance(locale);
			selDate.setLenient(false);
		}
		selDate.setTime(date);
		trunc(selDate);

		if ( curDate == null ) {
			curDate = Calendar.getInstance(locale);
			curDate.setLenient(false);
		}
		curDate.setTime(date);
		trunc(curDate);
		curDate.set(Calendar.DAY_OF_MONTH, 1);
		refreshDisplay();
	}

	/**
	 * Sets the theme to apply to the calendar.
	 * 
	 * @param theme new theme (must not be null)
	 */
	public void setTheme(CalendarTheme theme) {
		if ( theme == null ) SWT.error(SWT.ERROR_NULL_ARGUMENT);

		this.colors = theme;
		this.setRedraw(false);
		header.setBackground(theme.monthBack);
		currentMonth.setBackground(theme.monthBack);
		currentMonth.setForeground(theme.monthForg);
		daysLabels.setBackground(gridVisible ? theme.gridBack : theme.headerBack);
		daysNumbers.setBackground(gridVisible ? theme.gridBack : theme.cellBack);
		for (Label l : headers) {
			l.setBackground(theme.headerBack);
			l.setForeground(theme.headerForg);
		}
		for (Label l : days) {
			l.setBackground(theme.cellBack);
		}
		refreshDisplay();
		this.setRedraw(true);
	}

	/**
	 * Sets the today date.<p>
	 * 
	 * By default the today date is initialized to the current system date. But it
	 * can be needed to ajust it for specifics needs.
	 * 
	 * @param date today date (must not be null)
	 */
	public void setTodayDate(Date date) {
		if ( date == null ) SWT.error(SWT.ERROR_NULL_ARGUMENT);

		if ( todayDate == null ) {
			todayDate = Calendar.getInstance(locale);
			todayDate.setLenient(false);
		}
		todayDate.setTime(date);
		trunc(todayDate);
		refreshDisplay();
	}

	/**
	 * Trunc a given <code>Calendar</code>. The time fields are all setted to 0.
	 * 
	 * @param cal Calendar
	 */
	private void trunc(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
	}

	

}
