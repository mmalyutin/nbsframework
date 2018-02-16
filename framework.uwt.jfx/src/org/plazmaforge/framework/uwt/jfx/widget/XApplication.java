package org.plazmaforge.framework.uwt.jfx.widget;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class XApplication extends Application {

    public static Application currentApplication;
    
    public static Stage currentStage;
    
    public static Scene currentScene;
    
    
    public static org.plazmaforge.framework.uwt.Application application;
    
    public static org.plazmaforge.framework.uwt.widget.Frame frame;
    
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
	
	// creating a Group object
	FlowPane group = new FlowPane();

	// Creating a Scene by passing the group object, height and width
	Scene scene = new Scene(group);
	//group.setFill(Color.BROWN);

	// setting color to the scene
	//scene.setFill(Color.BROWN);

	// Setting the title to Stage.
	//primaryStage.setTitle("Sample Application");

	// Adding the scene to Stage
	primaryStage.setScene(scene);
	

	// Displaying the contents of the stage
	
	currentApplication = this;
	currentStage = primaryStage;
	currentScene = scene;
	
	frame.setDelegate(currentStage);
	frame.activateUI(true);
	
	//primaryStage.setWidth(frame.getWidth());
	//primaryStage.setHeight(frame.getHeight());
	
	primaryStage.show();

    }
}

