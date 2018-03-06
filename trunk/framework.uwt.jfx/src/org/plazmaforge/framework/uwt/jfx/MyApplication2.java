package org.plazmaforge.framework.uwt.jfx;

import java.util.Date;

import org.plazmaforge.framework.uwt.Application;
import org.plazmaforge.framework.uwt.UWTDesktopToolit;
import org.plazmaforge.framework.uwt.event.FocusEvent;
import org.plazmaforge.framework.uwt.event.FocusListener;
import org.plazmaforge.framework.uwt.event.MouseAdapter;
import org.plazmaforge.framework.uwt.event.MouseEvent;
import org.plazmaforge.framework.uwt.event.MouseListener;
import org.plazmaforge.framework.uwt.event.MouseMoveListener;
import org.plazmaforge.framework.uwt.event.SelectionEvent;
import org.plazmaforge.framework.uwt.event.SelectionListener;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.layout.FitLayout;
import org.plazmaforge.framework.uwt.layout.GridData;
import org.plazmaforge.framework.uwt.layout.GridLayout;
import org.plazmaforge.framework.uwt.layout.HorizontalLayout;
import org.plazmaforge.framework.uwt.layout.VerticalLayout;
import org.plazmaforge.framework.uwt.widget.Button;
import org.plazmaforge.framework.uwt.widget.CheckBox;
import org.plazmaforge.framework.uwt.widget.ComboBox;
import org.plazmaforge.framework.uwt.widget.DateField;
import org.plazmaforge.framework.uwt.widget.Frame;
import org.plazmaforge.framework.uwt.widget.ImageBox;
import org.plazmaforge.framework.uwt.widget.IntegerField;
import org.plazmaforge.framework.uwt.widget.Label;
import org.plazmaforge.framework.uwt.widget.Link;
import org.plazmaforge.framework.uwt.widget.ListBox;
import org.plazmaforge.framework.uwt.widget.MessageBox;
import org.plazmaforge.framework.uwt.widget.NumberField;
import org.plazmaforge.framework.uwt.widget.PasswordField;
import org.plazmaforge.framework.uwt.widget.RadioButton;
import org.plazmaforge.framework.uwt.widget.RadioGroup;
import org.plazmaforge.framework.uwt.widget.Slider;
import org.plazmaforge.framework.uwt.widget.TextArea;
import org.plazmaforge.framework.uwt.widget.TextField;
import org.plazmaforge.framework.uwt.widget.ToggleButton;
import org.plazmaforge.framework.uwt.widget.menu.Menu;
import org.plazmaforge.framework.uwt.widget.menu.MenuBar;
import org.plazmaforge.framework.uwt.widget.menu.MenuItem;
import org.plazmaforge.framework.uwt.widget.panel.TabItem;
import org.plazmaforge.framework.uwt.widget.panel.TabPanel;
import org.plazmaforge.framework.uwt.widget.table.Table;
import org.plazmaforge.framework.uwt.widget.table.TableColumn;
import org.plazmaforge.framework.uwt.widget.tool.CoolBar;
import org.plazmaforge.framework.uwt.widget.tool.ToolBar;
import org.plazmaforge.framework.uwt.widget.tool.ToolItem;
import org.plazmaforge.framework.uwt.widget.tool.ToolSeparator;
import org.plazmaforge.framework.uwt.widget.SpinnerField;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;


public class MyApplication2 extends Application {

    public static void main(String[] args) {
	
	UWTDesktopToolit.initUWT("jfx");
	
	// Create application
	MyApplication2 application = new MyApplication2();

	
	Frame frame = application.getFrame();
	frame.setTitle("My Application");

	final Label label = new Label("Hello World!");
	frame.add(label);
	
	ImageBox imageBox = new ImageBox("/org/plazmaforge/framework/uwt/resources/images/widget/folder-open.gif");
	frame.add(imageBox);
	
	final TextField textField = new TextField();
	frame.add(textField);
	
	
	final NumberField numberField = new NumberField();
	numberField.setValue(123.45);
	frame.add(numberField);
	
	final IntegerField integerField = new IntegerField();
	integerField.setValue(1234567890);
	frame.add(integerField);
	
	final DateField dateField = new DateField();
	dateField.setValue(new Date());
	dateField.setFormat("dd.MM.yyyy");
	frame.add(dateField);
	
	
	
	Button button = new Button("Press Me");
	button.setBackground(Color.RED);
	boolean[] flag = new boolean[] {false};
	button.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClick(MouseEvent e) {
		String text = flag[0]  ? "Hello !" : "Hi !";
		flag[0] = !flag[0];
		label.setText(text);
	    }
	});
	
	button.setIcon("/org/plazmaforge/framework/uwt/resources/images/widget/folder-open.gif");
	button.addSelectionListener(new SelectionListener() {
	    
	    @Override
	    public void select(SelectionEvent event) {
		label.setText("Hi all!");
		
	    }
	});
	
	frame.add(button);
	
	TabPanel tabPanel = new TabPanel();
	
	// TAB 1
	TabItem tabItem = new TabItem("TabItem 1");
	tabItem.setLayout(new VerticalLayout());
	
	tabItem.add(new Button("Button 1.1"));
	tabItem.add(new Button("Button 1.2"));
	tabItem.add(new Button("Button 1.3"));
	tabItem.add(new Button("Button 1.4"));
	
	tabPanel.add(tabItem);
	
	// TAB 2
	tabItem = new TabItem("TabItem 2");
	tabItem.setLayout(new HorizontalLayout());
	
	tabItem.add(new Button("Button 2.1"));
	tabItem.add(new Button("Button 2.2"));
	tabItem.add(new Button("Button 2.3"));
	tabItem.add(new Button("Button 2.4"));
	
	tabPanel.add(tabItem);
	
	// TAB 3
	tabItem = new TabItem("TabItem 3");
	tabItem.setLayout(new FitLayout());
	
	tabItem.add(new Button("Big Button"));
	
	tabPanel.add(tabItem);
	
	// TAB 4
	tabItem = new TabItem("TabItem 4", new Image("/org/plazmaforge/framework/uwt/resources/images/widget/folder-open.gif"));
	tabItem.setLayout(new GridLayout(4));

	tabItem.add(new Button("Big Button dddddddddddd"), new GridData(4, 1, HorizontalAlign.LEFT, VerticalAlign.MIDDLE, true, false));
	tabItem.add(new Button("sas 1"), new GridData(1, 1));
	tabItem.add(new Button("sfasdfasd 2"), new GridData(2, 1));
	tabPanel.add(tabItem);
	
	frame.add(tabPanel);
	
	
	
	final SpinnerField spinner = new SpinnerField();
	frame.add(spinner);	
	
	final Slider slider = new Slider();
	frame.add(slider);
	
	final PasswordField passwordField = new PasswordField();
	frame.add(passwordField);
	
	final TextArea textArea = new TextArea();
	frame.add(textArea);
	

	label.addMouseListener(new MouseListener() {
	    
	    @Override
	    public void mouseUp(MouseEvent e) {
		// TODO Auto-generated method stub
		textArea.append("mouseUp\n");
		
	    }
	    
	    @Override
	    public void mouseDown(MouseEvent e) {
		textArea.append("mouseDown\n");
		
	    }
	    
	    @Override
	    public void mouseDoubleClick(MouseEvent e) {
		textArea.append("mouseDoubleClick\n");
		
	    }
	    
	    @Override
	    public void mouseClick(MouseEvent e) {
		MessageBox.information("WAU!!!!!!!!!!!!!!!!!!!!!");
		textArea.append("mouseClick\n");
		
	    }
	});
	
	label.addMouseMoveListener(new MouseMoveListener() {
	    
	    @Override
	    public void mouseOut(MouseEvent e) {
		textArea.append("mouseOut\n");
		
	    }
	    
	    @Override
	    public void mouseMove(MouseEvent e) {
		textArea.append("mouseMove\n");
		
	    }
	    
	    @Override
	    public void mouseIn(MouseEvent e) {
		textArea.append("mouseIn\n");
		
	    }
	});
	
	textField.setText("Focus");
	
	textField.addFocusListener(new FocusListener() {
	    
	    @Override
	    public void focusOut(FocusEvent e) {
		textArea.append("TestField: focusOut\n");
		
	    }
	    
	    @Override
	    public void focusIn(FocusEvent e) {
		textArea.append("TestField: focusIn\n");
		
	    }
	});
	
	final CheckBox checkBox = new CheckBox();
	frame.add(checkBox);
	
	
	final Link link = new Link("www,sourceforge.net");
	frame.add(link);
	
	/////////////////////////////////////////////////////
	//
	// TOOL BARP
	//
	/////////////////////////////////////////////////////	
	
	ToolBar toolBar = new ToolBar();
	
	toolBar.add(new ToolSeparator());
	
	ToolItem toolItem = new ToolItem();
	toolItem.setIcon("/org/plazmaforge/framework/uwt/resources/images/widget/leaf.gif");
	toolBar.add(toolItem);
	
	toolItem = new ToolItem();
	toolItem.setIcon("/org/plazmaforge/framework/uwt/resources/images/widget/folder-open.gif");
	toolBar.add(toolItem);
	//frame.add(toolBar);
	
	
	ToolBar toolBar2 = new ToolBar();
	toolBar2.add(new ToolSeparator());
	
	toolItem = new ToolItem();
	toolItem.setIcon("/org/plazmaforge/framework/uwt/resources/images/widget/refresh.gif");
	toolBar2.add(toolItem);
	
	CoolBar coolBar = new CoolBar();
	coolBar.addItem(toolBar);
	coolBar.addItem(toolBar2);
//	
	frame.add(coolBar);
	
	//frame.add(toolBar);
	
	/////////////////////////////////////////////////////
	//
	// RADIO GROUP
	//
	/////////////////////////////////////////////////////
	RadioGroup radioGroup = new RadioGroup();

	RadioButton radioButton1 = new RadioButton("FM1");
	RadioButton radioButton2 = new RadioButton("FM2");
	RadioButton radioButton3 = new RadioButton("AM");
	
	radioGroup.add(radioButton1);
	radioGroup.add(radioButton2);
	radioGroup.add(radioButton3);

	frame.add(radioGroup);
	
	
	/////////////////////////////////////////////////////
	//
	// TOOGLE GROUP
	//
	/////////////////////////////////////////////////////	
	final ToggleButton redButton = new ToggleButton("Red");
	redButton.setGroup("group");
	frame.add(redButton);
	
	final ToggleButton blueButton = new ToggleButton("Blue");
	blueButton.setGroup("group");
	frame.add(blueButton);
	
	final ToggleButton greenButton = new ToggleButton("Green");
	greenButton.setGroup("group");
	frame.add(greenButton);
	
	final ComboBox<String> comboBox = new ComboBox<String>();
	comboBox.setItems(new String[] {"Apple", "Tomato", "Potato"});
	comboBox.setValue("Tomato");
	frame.add(comboBox);
	
	final ListBox<String> listBox = new ListBox<String>();
	listBox.setItems(new String[] {"Apple", "Tomato", "Potato"});
	listBox.setValue("Potato");
	
	frame.add(listBox);	
	
	/////////////////////////////////////////////////////
	//
	// MENU BAR
	//
	/////////////////////////////////////////////////////	
	MenuBar menuBar = new MenuBar();
	
	Menu menu = new Menu("File");
	
	MenuItem menuItem = new MenuItem("New file");
	menu.addItem(menuItem);

	menuItem = new MenuItem("Open file");
	menuItem.setIcon("/org/plazmaforge/framework/uwt/resources/images/widget/folder-open.gif");
	menu.addItem(menuItem);

	menuBar.addItem(menu);
	
	frame.add(menuBar);
	
	
	
	
	final Table table = new Table();
	table.setWidth(400);
	TableColumn nameColumn = new TableColumn();
	nameColumn.setText("First Name");
	nameColumn.setProperty("firstName");
	table.addColumn(nameColumn);
	
	TableColumn lastNameColumn = new TableColumn();
	lastNameColumn.setText("Last Name");
	lastNameColumn.setProperty("lastName");
	table.addColumn(lastNameColumn);
	
	
	TableColumn emailColumn = new TableColumn();
	emailColumn.setText("Email");
	emailColumn.setProperty("email");
	table.addColumn(emailColumn);
	
	TableColumn dateColumn = new TableColumn();
	dateColumn.setIcon("/org/plazmaforge/framework/uwt/resources/images/widget/folder-open.gif");
	dateColumn.setText("Date");
	dateColumn.setProperty("date");
	dateColumn.setFormat("dd.MM.yyyy");
	table.addColumn(dateColumn);
	
	table.setItems(new Object[] {
		new Person("Jacob", "Smith", "jacob.smith@example.com"),
		new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
		new Person("Ethan", "Williams", "ethan.williams@example.com"),
		new Person("Emma", "Jones", "emma.jones@example.com"),
		new Person("Michael", "Brown", "michael.brown@example.com")
	});
	
	frame.add(table);	

	
	// Start application
	application.start();
    }
    
    public static class Person {
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private Date date;
	

	public Person() {
	    super();
	}

	public Person(String firstName, String lastName, String email) {
	    super();
	    this.firstName = firstName;
	    this.lastName = lastName;
	    this.email = email;
	    
	    this.date = new Date();
	}

	public String getFirstName() {
	    return firstName;
	}

	public void setFirstName(String firstName) {
	    this.firstName = firstName;
	}

	public String getLastName() {
	    return lastName;
	}

	public void setLastName(String lastName) {
	    this.lastName = lastName;
	}

	public String getEmail() {
	    return email;
	}

	public void setEmail(String email) {
	    this.email = email;
	}

	public Date getDate() {
	    return date;
	}

	public void setDate(Date date) {
	    this.date = date;
	}
	
	
    }
}
