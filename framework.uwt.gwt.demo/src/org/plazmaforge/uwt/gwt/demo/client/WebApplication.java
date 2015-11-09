package org.plazmaforge.uwt.gwt.demo.client;

import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.core.data.Callback;
import org.plazmaforge.framework.core.data.DataWrapper;
import org.plazmaforge.framework.core.data.Initializer;
import org.plazmaforge.framework.core.data.Parameters;
import org.plazmaforge.framework.core.data.object.IData;
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
import org.plazmaforge.framework.uwt.gxt.UWT_GXT;
import org.plazmaforge.framework.uwt.storage.TemplateProviderAsync;
import org.plazmaforge.framework.uwt.widget.Frame;

import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

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
	
	RootPanel root = RootPanel.get("root");
	
	///////////////////////////////////////////////////////////////
	
	viewport = new Viewport();
	viewport.setStyleAttribute("margin", "0px");
        viewport.setLayout(new FitLayout());

        root.add(viewport);
        
        // Create UWT Application
        String locale = "en";
        
        //UWTApplication = new org.plazmaforge.framework.uwt.Application();
        getApplicationContext().addAttributes(UWT_GXT.getAttributes());
        getApplicationContext().setProperty("locale", locale);
        getApplicationContext().setProperty(ApplicationContext.CONFIG_FORMAT_DATE, "dd-MM-yyyy");
        getApplicationContext().setProperty(ApplicationContext.CONFIG_FORMAT_DATE_TIME, "dd-MM-yyyy HH:mm:ss");
        getApplicationContext().setProperty(ApplicationContext.CONFIG_FORMAT_TIME, "HH:mm:ss");
        
        getApplicationContext().setProperty("imageStorage", "resources/images");
        getApplicationContext().setAttribute(ApplicationContext.CONFIG_SERVICE_CALLER_FACTORY, new WebServiceCallerFactory());
        getApplicationContext().setAttribute(ApplicationContext.CONFIG_TEMPLATE_PROVIDER_ASYNC, new WebTemplateProvider());
        
        setInitializer(new WebApplicationInitializer());
        start();
        
	///////////////////////////////////////////////////////////////

    }
    
    class WebApplicationInitializer implements Initializer {

	@Override
	public void initialize(Object object, Callback<Object> callback) {
	    final String locale = getApplicationContext().getProperty("locale");
	    
	    // List of resources
	    String[] resourceNames = new String[] {
		    "org.plazmaforge.framework.uwt.resources.messages",
		    "org.plazmaforge.framework.uwt.demo.messages"
		    };
	    
	    resourceService.getResources(resourceNames, locale, new AsyncCallback<List<Resource>>() {

		    @Override
		    public void onFailure(Throwable caught) {
			System.out.println(caught);
			doStart(locale, null);
		    }

		    @Override
		    public void onSuccess(List<Resource> result) {
			
		        // Create Frame
			Resource[] resources = result.toArray(new Resource[0]);
			doStart(locale, resources);

		    }
	            
	        });
	    
	}
	
    }
    
    protected void doStart(String locale, Resource[] resources) {
	
	ResourceProvider resourceProvider = new CacheResourceProvider(resources);
	UWT.setResourceProvider(resourceProvider, locale);
	
	getApplicationContext().setAttribute(ApplicationContext.CONFIG_RESOURCE_PROVIDER, resourceProvider);
	Frame frame = getFrame();
	frame.setDelegate(viewport);
	DemoApplicationBuilder builder = new DemoApplicationBuilder();
	builder.populateFrame(frame);
	viewport.layout(true);
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
