package org.plazmaforge.uwt.gwt.demo.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.core.data.Callback;
import org.plazmaforge.framework.core.data.ClassPropertyProviderFactory;
import org.plazmaforge.framework.core.data.DataWrapper;
import org.plazmaforge.framework.core.data.Initializer;
import org.plazmaforge.framework.core.data.Parameters;
import org.plazmaforge.framework.core.data.ValueProvider;
import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.core.data.provider.TreeProvider;
import org.plazmaforge.framework.core.logging.Logger;
import org.plazmaforge.framework.core.resource.CacheResourceProvider;
import org.plazmaforge.framework.core.resource.Resource;
import org.plazmaforge.framework.core.resource.ResourceProvider;
import org.plazmaforge.framework.core.service.ServiceCaller;
import org.plazmaforge.framework.core.service.ServiceCallerFactory;
import org.plazmaforge.framework.uwt.Application;
import org.plazmaforge.framework.uwt.ApplicationContext;
import org.plazmaforge.framework.uwt.UWT;
import org.plazmaforge.framework.uwt.builder.UIBuilderHelper;
import org.plazmaforge.framework.uwt.demo.DemoApplicationBuilder;
import org.plazmaforge.framework.uwt.demo.DemoApplicationInitializer;
import org.plazmaforge.framework.uwt.demo.DemoServiceCallerFactory;
import org.plazmaforge.framework.uwt.demo.model.Group;
import org.plazmaforge.framework.uwt.demo.model.Product;
import org.plazmaforge.framework.uwt.event.EnterEvent;
import org.plazmaforge.framework.uwt.event.EnterListener;
import org.plazmaforge.framework.uwt.event.FocusEvent;
import org.plazmaforge.framework.uwt.event.FocusListener;
import org.plazmaforge.framework.uwt.event.MouseEvent;
import org.plazmaforge.framework.uwt.event.MouseListener;
import org.plazmaforge.framework.uwt.event.SelectionEvent;
import org.plazmaforge.framework.uwt.event.SelectionListener;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.gxt.UWT_GXT;
import org.plazmaforge.framework.uwt.layout.GridData;
import org.plazmaforge.framework.uwt.layout.GridLayout;
import org.plazmaforge.framework.uwt.layout.HorizontalLayout;
import org.plazmaforge.framework.uwt.layout.VerticalLayout;
import org.plazmaforge.framework.uwt.storage.TemplateProviderAsync;
import org.plazmaforge.framework.uwt.widget.Button;
import org.plazmaforge.framework.uwt.widget.CheckBox;
import org.plazmaforge.framework.uwt.widget.ComboBox;
import org.plazmaforge.framework.uwt.widget.Container;
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
import org.plazmaforge.framework.uwt.widget.SpinnerField;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.TextArea;
import org.plazmaforge.framework.uwt.widget.TextField;
import org.plazmaforge.framework.uwt.widget.TimeField;
import org.plazmaforge.framework.uwt.widget.ToggleButton;
import org.plazmaforge.framework.uwt.widget.menu.Menu;
import org.plazmaforge.framework.uwt.widget.menu.MenuBar;
import org.plazmaforge.framework.uwt.widget.menu.MenuItem;
import org.plazmaforge.framework.uwt.widget.menu.MenuSeparator;
import org.plazmaforge.framework.uwt.widget.panel.GroupPanel;
import org.plazmaforge.framework.uwt.widget.panel.SplitPanel;
import org.plazmaforge.framework.uwt.widget.panel.TabItem;
import org.plazmaforge.framework.uwt.widget.panel.TabPanel;
import org.plazmaforge.framework.uwt.widget.panel.TitlePanel;
import org.plazmaforge.framework.uwt.widget.table.Table;
import org.plazmaforge.framework.uwt.widget.table.TableColumn;
import org.plazmaforge.framework.uwt.widget.tool.CoolBar;
import org.plazmaforge.framework.uwt.widget.tool.ToolBar;
import org.plazmaforge.framework.uwt.widget.tool.ToolItem;
import org.plazmaforge.framework.uwt.widget.tree.Tree;

import com.sencha.gxt.widget.core.client.container.Viewport;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class WebApplication extends Application implements EntryPoint {

    /**
     * Call Service.
     */
    private final CallServiceAsync callService = GWT.create(CallService.class);
    
    /**
     * Resource Service
     */
    private final ResourceServiceAsync resourceService = GWT.create(ResourceService.class);

    /**
     * UIResource Service
     */
    private final TemplateServiceAsync templateService = GWT.create(TemplateService.class);

    
    /**
     * General viewport
     */
    private Viewport viewport;
    
    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
	
	///////////////////////////////////////////////////////////////
	
	UWT_GXT.init();
	
	///////////////////////////////////////////////////////////////
	
	RootPanel root =  RootPanel.get("root"); //RootPanel.get();
	
	///////////////////////////////////////////////////////////////
	
	viewport = new Viewport();
	//viewport.setStyleAttribute("margin", "0px");
        //viewport.setLayout(new FitLayout());

        root.add(viewport);
        
        // Create UWT Application
        String locale = "en";
        
        getApplicationContext().addAttributes(UWT_GXT.getAttributes());
        getApplicationContext().setProperty("locale", locale);
                  
        start();
        
	///////////////////////////////////////////////////////////////

    }
    
    @Override
    public void init() {
	setInitializer(new WebApplicationInitializer());
    }
    
    class WebApplicationInitializer extends DemoApplicationInitializer {

	
	@Override
	protected void initializeApplication(Application application, Callback<Object> callback) {
	    
	    final String locale = getApplicationContext().getProperty("locale");
	    ApplicationContext applicationContext = application.getApplicationContext();
	    
	    // Initialize Application Context
	    initializeApplicationContext(applicationContext);
	    
	    
	    // List of resources
	    String[] resourceNames = new String[] {
		    "org.plazmaforge.framework.uwt.resources.messages",
		    "org.plazmaforge.framework.uwt.demo.messages"
		    };
	    
	    resourceService.getResources(resourceNames, locale, new AsyncCallback<List<Resource>>() {

		    @Override
		    public void onFailure(Throwable caught) {
			System.out.println(caught);
			loadApplication(locale, null);
		    }

		    @Override
		    public void onSuccess(List<Resource> result) {
			
		        // Create Frame
			Resource[] resources = result.toArray(new Resource[0]);
			loadApplication(locale, resources);

		    }
	            
	        });
	    
	}
	
	@Override
	protected void initializeApplicationContext(ApplicationContext applicationContext) {
	
	    // Initialize locale (number/date/time formats)
	    initializeLocale(applicationContext);
	    
	    applicationContext.setProperty("imageStorage", "resources/images");
	    applicationContext.setAttribute(ApplicationContext.CONFIG_SERVICE_CALLER_FACTORY, new WebServiceCallerFactory());
	    applicationContext.setAttribute(ApplicationContext.CONFIG_TEMPLATE_PROVIDER_ASYNC, new WebTemplateProvider());
	        
	}	
	
	protected void loadApplication(String locale, Resource[] resources) {

	    ResourceProvider resourceProvider = new CacheResourceProvider(resources);
	    UWT.setResourceProvider(resourceProvider, locale);

	    getApplicationContext().setAttribute(ApplicationContext.CONFIG_RESOURCE_PROVIDER, resourceProvider);

	    // viewport.add(new Label("GWT migration"));

	    // TODAY
	    // addTestControls();

	    Frame frame = getFrame();
	    frame.setDelegate(viewport);

	    populateFrame(frame);
	    viewport.forceLayout();
	}
	
    }
    
   
    
    
    
    
    
    
    
    
    private void addTestControls() {
	org.plazmaforge.framework.uwt.widget.Container uwtContainer = new  org.plazmaforge.framework.uwt.widget.panel.Panel();
	uwtContainer.setLayout(new GridLayout(3));
	
	//uwtContainer.setLayout(new GridLayout(1));
	

	/*
	sp.activateUI(true);
	viewport.add((Widget) sp.getDelegate());
	*/
	
	
	//uwtContainer.add(sp);
	//uwtContainer.activateUI(true);
	
	//MessageBox.information("Application was started!");
	//viewport.add((Widget) uwtContainer.getDelegate());
	
	
	ToolBar toolBar = new ToolBar();
	toolBar.setLayoutData(new GridData(2, 1));
	ToolItem toolItem1 = new ToolItem("New", new Image("widget/leaf.gif"));
	toolItem1.addSelectionListener(new SelectionListener() {
	    
	    @Override
	    public void select(SelectionEvent event) {
		MessageBox.information("ToolItem: Test selection listener");
	    }
	});

	ToolItem toolItem2 = new ToolItem("Open", new Image("widget/folder.gif"));
	
	toolBar.addItem(toolItem1);
	toolBar.addItem(toolItem2);
	
	uwtContainer.add(toolBar);
	
	// MENU BAR
	MenuBar menuBar = new MenuBar();
	
	Menu menu1 = new Menu("Menu 1");
	MenuItem menuItem1_1 = new MenuItem("Menu 1.1");
	menuItem1_1.addSelectionListener(new SelectionListener() {
	    
	    @Override
	    public void select(SelectionEvent event) {
		MessageBox.information("MenuItem: Test selection listener");
	    }
	});

	MenuItem menuItem1_2 = new MenuItem("Menu 1.2");
	menu1.addItem(menuItem1_1);
	menu1.addItem(new MenuSeparator());
	menu1.addItem(menuItem1_2);
	
	MenuItem menuItem1 = new Menu("Menu Item 1");
	
	menuBar.addItem(menu1);
	menuBar.addItem(menuItem1);
	
	//menuBar.setLayoutData(new GridData(1, 1));
	uwtContainer.add(menuBar);
	
	org.plazmaforge.framework.uwt.widget.Label label = new org.plazmaforge.framework.uwt.widget.Label("UWT Migration!");
	label.addMouseListener(new MouseListener() {
	    
	    @Override
	    public void mouseUp(MouseEvent e) {
		GWT.log("Label: mouseUp: x=" + e.getX() +", y=" + e.getY());
		
	    }
	    
	    @Override
	    public void mouseDown(MouseEvent e) {
		GWT.log("Label: mouseDown: x=" + e.getX() +", y=" + e.getY());
		
	    }
	    
	    @Override
	    public void mouseDoubleClick(MouseEvent e) {
		GWT.log("Label: mouseDoubleClick: x=" + e.getX() +", y=" + e.getY());
		
	    }
	    
	    @Override
	    public void mouseClick(MouseEvent e) {
		GWT.log("Label: mouseClick: x=" + e.getX() +", y=" + e.getY());
		
	    }
	});
	uwtContainer.add(label);
	
	Button button = new Button("My Migration");
	button.addSelectionListener(new SelectionListener() {
	    
	    @Override
	    public void select(SelectionEvent event) {
		MessageBox.information("Button: Test selection listener");
	    }
	});
	button.setLayoutData(new GridData(HorizontalAlign.FILL));
	
	uwtContainer.add(button);
	
	ToggleButton toggleButton1 = new ToggleButton("Toogle Button 1");
	toggleButton1.setGroup("my");
	uwtContainer.add(toggleButton1);
		
	
	ToggleButton toggleButton2 = new ToggleButton("Toogle Button 2");
	
	toggleButton2.setGroup("my");
		

	uwtContainer.add(toggleButton2);
	
	
	RadioGroup radioGroup = new RadioGroup();
	RadioButton ratioButton1 = new RadioButton("Yes");
	RadioButton ratioButton2 = new RadioButton("No");
	
	radioGroup.add(ratioButton1);
	radioGroup.add(ratioButton2);
	
	uwtContainer.add(radioGroup);
	
	uwtContainer.add(new Slider());
	
	TextField textField = new TextField();
	textField.addFocusListener(new FocusListener() {
	    
	    @Override
	    public void focusOut(FocusEvent e) {
		GWT.log("TextField: Focus Lost");
		
	    }
	    
	    @Override
	    public void focusIn(FocusEvent e) {
		GWT.log("TextField: Focus Input");
		
	    }
	});
	uwtContainer.add(textField);
	
	TextArea textArea = new TextArea();
	textArea.setLayoutData(new GridData(HorizontalAlign.FILL));
	uwtContainer.add(textArea);
	
	uwtContainer.add(new PasswordField());
	uwtContainer.add(new DateField());
	uwtContainer.add(new TimeField());
	uwtContainer.add(new NumberField());
	uwtContainer.add(new IntegerField());
	uwtContainer.add(new SpinnerField());
	
	uwtContainer.add(new CheckBox());
	
	uwtContainer.add(new ImageBox(new Image("widget/leaf.gif")));
	
	CoolBar coolBar = new CoolBar();
	
	ToolBar toolBar2 = new ToolBar();
	ToolItem toolItem21 = new ToolItem("New", new Image("widget/leaf.gif"));
	toolItem21.addSelectionListener(new SelectionListener() {
	    
	    @Override
	    public void select(SelectionEvent event) {
		MessageBox.information("ToolItem-New: Test selection listener");
	    }
	});
	ToolItem toolItem22 = new ToolItem("Open", new Image("widget/folder.gif"));
	
	toolBar2.add(toolItem21);
	toolBar2.add(toolItem22);
	
	coolBar.add(toolBar2);
	
	ToolBar toolBar3 = new ToolBar();
	ToolItem toolItem31 = new ToolItem("New", new Image("widget/leaf.gif"));
	ToolItem toolItem32 = new ToolItem("Open", new Image("widget/folder.gif"));
	
	toolBar3.add(toolItem31);
	toolBar3.add(toolItem32);
	
	
	coolBar.add(toolBar3);
	
	
	
	uwtContainer.add(coolBar);
	
	uwtContainer.add(new Link("Test url"));
	
	
	ComboBox<Product> comboBox = new ComboBox<Product>();
	comboBox.addSelectionListener(new SelectionListener() {
	    
	    @Override
	    public void select(SelectionEvent event) {
		MessageBox.information("ComboBox: Test selection listener");
	    }
	});
	
	List<Product> dataList = new ArrayList<Product>();
	dataList.add(new Product("Red"));
	dataList.add(new Product("Green"));
	comboBox.setDisplayProperty("id");
	comboBox.setItems(dataList);
	
	uwtContainer.add(comboBox);
	
	
	ListBox<Product> listBox = new ListBox<Product>();
	listBox.addSelectionListener(new SelectionListener() {
	    
	    @Override
	    public void select(SelectionEvent event) {
		MessageBox.information("ListBox: Test selection listener");
		
	    }
	});
	
	List<Product> listBoxData = new ArrayList<Product>();
	listBoxData.add(new Product("Red++"));
	listBoxData.add(new Product("Green++"));
	
	listBox.setItems(listBoxData);
	listBox.setDisplayProperty("id");
	
	uwtContainer.add(listBox);
	
	
	Table<Product> table = new Table<Product>();
//	table.addSelectionListener(new SelectionListener() {
//	    
//	    @Override
//	    public void select(SelectionEvent event) {
//		MessageBox.information("Table: Test selection listener");
//	    }
//	});
	
	table.addEnterListener(new EnterListener() {
	    
	    @Override
	    public void enter(EnterEvent e) {
		MessageBox.information("Table: Test Enter event");
		
	    }
	});
	/*
	table.addMouseListener(new MouseListener() {
	    

	    @Override
	    public void mouseClick(MouseEvent e) {
		// TODO Auto-generated method stub
		MessageBox.information("Table: Test MouseClick event");
		
	    }

	    @Override
	    public void mouseDoubleClick(MouseEvent e) {
		// TODO Auto-generated method stub
		
	    }

	    @Override
	    public void mouseDown(MouseEvent e) {
		// TODO Auto-generated method stub
		
	    }

	    @Override
	    public void mouseUp(MouseEvent e) {
		// TODO Auto-generated method stub
		
	    }
	});
	*/

	TableColumn tableColumn = new TableColumn();
	tableColumn.setText("ID");
	tableColumn.setProperty("id");
	tableColumn.setValueProvider(new ValueProvider() {

	    @Override
	    public Object getValue(Object element) {
		// TODO Auto-generated method stub
		return "# " + ((Product) element).getId();
	    }

	    @Override
	    public void setValue(Object element, Object value) {
		// TODO Auto-generated method stub
		
	    }
	    
	});
	table.addColumn(tableColumn);
	
	TableColumn tableColumn2 = new TableColumn();
	tableColumn2.setText("Name");
	tableColumn2.setProperty("name");
	table.addColumn(tableColumn2);
	
	TableColumn tableColumn3 = new TableColumn();
	tableColumn3.setText("Price");
	tableColumn3.setProperty("price");
	tableColumn3.setDataType("Float");
	tableColumn3.setAlign(HorizontalAlign.RIGHT);
	table.addColumn(tableColumn3);
	
	List<Product> dataList2 = new ArrayList<Product>();
	dataList2.add(new Product("100", "C-100", "Name-100", 123.45f));
	dataList2.add(new Product("200", "C-200", "Name-200", 234.56f));
	
	table.setItems(dataList2);
	
	uwtContainer.add(table);
	
	
	final List<Group> groupList = new ArrayList<Group>();

	// Group 1
	Group group = new Group();
	group.setName("Group 1");
	groupList.add(group);

	Group group1_1 = new Group();
	group1_1.setName("Group 1.1");
	group.addChildren(group1_1);

	Group group1_2 = new Group();
	group1_2.setName("Group 1.2");
	group.addChildren(group1_2);

	
	// Group 2
	group = new Group();
	group.setName("Group 2");
	groupList.add(group);
	
	Group group2_1 = new Group();
	group2_1.setName("Group 2.1");
	group.addChildren(group2_1);

	Group group2_2 = new Group();
	group2_2.setName("Group 2.2");
	group.addChildren(group2_2);
	
	// Group 3
	group = new Group();
	group.setName("Group 3");
	groupList.add(group);

	TreeProvider<Group> dataProvider = new TreeProvider<Group>() {
	    
	    @Override
	    public List<Group> getList() {
		return groupList;
	    }
	    
	    @Override
	    public boolean hasChildren(Group element) {
		return element.hasChildren();
	    }
	    
	    @Override
	    public Group getParent(Group element) {
		return null;
	    }
	    
	    @Override
	    public List<Group> getChildren(Group parent) {
		return parent.getChildren();
	    }
	};
	
	Tree<Group> tree = new Tree<Group>();
	tree.addSelectionListener(new SelectionListener() {
	    
	    @Override
	    public void select(SelectionEvent event) {
		MessageBox.information("Tree: Test selection listener");
	    }
	});
	
	tree.setDisplayProperty("name");
	tree.setDataProvider(dataProvider, true);
	
	GroupPanel gp = new GroupPanel();
	gp.setTitle("GroupPanel");
	gp.add(new Label("Label"));
	gp.add(new Button("Button"));
	
	TitlePanel tp = new TitlePanel();
	tp.setTitle("TitlePanel");
	tp.add(new Label("Label"));
	tp.add(new Button("Button"));
	
	uwtContainer.add(tree);
	
	SplitPanel sp = new SplitPanel();
	sp.setLayoutData(new GridData(HorizontalAlign.FILL));
	sp.add(new Label("Button-1"));
	sp.add(new Label("Button-2"));
	
	
	uwtContainer.add(sp);
	
	TabPanel tabPanel = new TabPanel();
	TabItem tabItem = new TabItem("TabItem-1", new Image("widget/leaf.gif"));
	tabItem.add(new Label("TabLabel-1"));
	tabPanel.add(tabItem);
	
	tabItem = new TabItem("TabItem-2", new Image("widget/folder.gif"));
	tabItem.add(new Label("TabLabel-2"));
	tabPanel.add(tabItem);
	
	tabItem = new TabItem("TabItem-3", new Image("widget/folder.gif"));
	tabItem.add(new Label("TabLabel-3"));
	tabPanel.add(tabItem);
	
	tabPanel.setActiveItem(tabItem);
	
	TableColumn tableColumn41 = new TableColumn();
	tableColumn41.setText("Price-1");
	tableColumn41.setAlign(HorizontalAlign.RIGHT);
	table.addColumn(tableColumn41);
	
	uwtContainer.add(tabPanel);
	
	TableColumn tableColumn42 = new TableColumn();
	tableColumn42.setText("Price-2");
	tableColumn42.setAlign(HorizontalAlign.RIGHT);
	table.addColumn(tableColumn42);
	
	uwtContainer.activateUI(true);
	
	TableColumn tableColumn43 = new TableColumn();
	tableColumn43.setText("Price-3");
	tableColumn43.setAlign(HorizontalAlign.RIGHT);
	table.addColumn(tableColumn43);
	
	
	//MessageBox.information("Application was started!");
	
	viewport.add((Widget) uwtContainer.getDelegate());
	
	TableColumn tableColumn4 = new TableColumn();
	tableColumn4.setText("Price777");
	tableColumn4.setAlign(HorizontalAlign.RIGHT);
	table.addColumn(tableColumn4);
	

    }
    
    class WebServiceCallerFactory implements ServiceCallerFactory {

	private ServiceCaller serviceCaller;
	
	@Override
	public ServiceCaller getServiceCaller() {
	    if (serviceCaller == null) {
		serviceCaller = new WebServiceCaller();
	    }
	    return serviceCaller;
	}

	@Override
	public ServiceCaller getServiceCaller(String name) {
	    return getServiceCaller();
	}
	
    }
    
    class WebServiceCaller implements ServiceCaller {

	private final Logger logger = Logger.getLogger(WebServiceCaller.class.getName());
		
	public <T> void call(final String serviceName, final String methodName, final Object[] parameters, final Callback<T> callback) {
	    // By Index
	    callService.call(serviceName, methodName, Parameters.byIndex(parameters), Parameters.BY_INDEX, new AsyncCallback<DataWrapper>() {

		@Override
		public void onFailure(Throwable e) {
		    logger.error("Call service error: service=" + serviceName + ", method=" + methodName + ", parameters=[" + (parameters == null ? 0 : parameters.length) + "]", e);
		    callback.onFailure(e);
		}

		@Override
		public void onSuccess(DataWrapper result) {
		    Object data = result.getData();
		    callback.onSuccess((T) data);
		}
		
	    });
	}
	
	@Override
	public <T> void call(final String serviceName, final String methodName, final Map<String, Object> parameters, final Callback<T> callback) {
	    // By name
	    callService.call(serviceName, methodName, Parameters.byName(parameters), Parameters.BY_NAME, new AsyncCallback<DataWrapper>() {

		@Override
		public void onFailure(Throwable e) {
		    logger.error("Call service error: service=" + serviceName + ", method=" + methodName + ", parameters=[" + (parameters == null ? 0 : parameters.size()) + "]", e);
		    callback.onFailure(e);
		}

		@Override
		public void onSuccess(DataWrapper result) {
		    Object data = result.getData();
		    callback.onSuccess((T) data);
		}
		
	    });
	}
	
    }
    
    class WebTemplateProvider implements TemplateProviderAsync {

	@Override
	public void getData(String path, final Callback<IData> callback) {
	    templateService.getData(path, new AsyncCallback<IData>() {

		@Override
		public void onFailure(Throwable caught) {
		    callback.onFailure(caught);
		}

		@Override
		public void onSuccess(IData result) {
		    // Restore parent of children: JSON transfer problems
		    UIBuilderHelper.assingParent(result);
		    callback.onSuccess(result);
		}
		
	    });
	}
	
    }
}
