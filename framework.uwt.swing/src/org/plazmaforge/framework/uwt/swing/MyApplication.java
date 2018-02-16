package org.plazmaforge.framework.uwt.swing;

import org.plazmaforge.framework.uwt.Application;
import org.plazmaforge.framework.uwt.UWTDesktopToolit;
import org.plazmaforge.framework.uwt.event.SelectionEvent;
import org.plazmaforge.framework.uwt.event.SelectionListener;
import org.plazmaforge.framework.uwt.widget.Button;
import org.plazmaforge.framework.uwt.widget.Frame;
import org.plazmaforge.framework.uwt.widget.Label;

public class MyApplication extends Application {

    public static void main(String[] args) {
	
	UWTDesktopToolit.initUWT("swing");
	
	// Create application
	MyApplication application = new MyApplication();
	
	Frame frame = application.getFrame();
	frame.setTitle("My Application");
	
	Label label = new Label("Hello World!");
	frame.add(label);
	
	Button button = new Button("Press Me");
	button.addSelectionListener(new SelectionListener() {
	    
	    @Override
	    public void select(SelectionEvent event) {
		label.setText("Hi all!");
		
	    }
	});
	
	frame.add(button);
	
	// Start application
	application.start();
    }
}
