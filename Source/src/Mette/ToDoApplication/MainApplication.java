/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mette.ToDoApplication;

import Mette.ToDoApplication.controller.ToDoListController;
import Mette.ToDoApplication.database.SqliteConnectionImpl;
import Mette.ToDoApplication.database.ToDoDAO;
import Mette.ToDoApplication.database.ToDoDAOImpl;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * The entry point for the application
 *
 * @author Mette
 */
public class MainApplication extends Application {

    public MainApplication mainApplication;
    public Stage primaryStage;

    /**
     * Starts the application
     * @param primaryStage the primary stage
     * @throws Exception 
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Mette/ToDoApplication/view/OverviewView.fxml"));
        createAndSetController(loader);
        createMainScene(loader, primaryStage);
    }

    private void createAndSetController(FXMLLoader loader) {
        ToDoDAO toDoDAO = new ToDoDAOImpl(new SqliteConnectionImpl());
        loader.setController(new ToDoListController(toDoDAO));
        ToDoListController overviewViewController = loader.getController();
        overviewViewController.setMainApplication(this);
    }

    private void createMainScene(FXMLLoader loader, Stage primaryStage1) throws IOException {
        Parent root = (Parent) loader.load();
        Scene homeScene = new Scene(root);
        primaryStage1.getIcons().add(new Image("Mette/ToDoApplication/view/Elephant.png"));
        primaryStage1.setTitle("Your Little Helper");
        primaryStage1.setScene(homeScene);
        primaryStage1.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
