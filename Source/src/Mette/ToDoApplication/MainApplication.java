/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mette.ToDoApplication;

import Mette.ToDoApplication.controller.OverviewViewController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Mette
 */
public class MainApplication extends Application {
    
   
    public MainApplication mainApplication;
    public Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Mette/ToDoApplication/view/OverviewView.fxml"));
       // loader.setController(new OverviewViewController());
        Parent root = (Parent) loader.load();
        Scene homeScene = new Scene(root);
        primaryStage.getIcons().add(new Image("Mette/ToDoApplication/pictures/Elephant.png"));
        primaryStage.setTitle("Your Little Helper");
        primaryStage.setScene(homeScene);
        primaryStage.show();
        
        //Set the main application reference in the tableViewController
        //OverviewViewController overviewViewController = loader.getController();
        //OverviewViewController.setMainApplication(this);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

