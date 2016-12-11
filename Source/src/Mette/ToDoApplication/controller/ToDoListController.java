/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mette.ToDoApplication.controller;

import Mette.ToDoApplication.MainApplication;
import Mette.ToDoApplication.database.SqliteConnectionImpl;
import Mette.ToDoApplication.database.ToDoDAO;
import Mette.ToDoApplication.model.ToDo;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Mette
 */
public class ToDoListController implements Initializable {
    
    MainApplication mainApplication;
    
    
    //ObservarbleList made for containing ToDo objects. 
     private ObservableList<ToDo> toDoList;
     
     //New insta
     private ToDoDAO toDoDAO;
     
     
     //Making name for table in the tableView and the two columns showing the titel and the date of the created ToDo.
    @FXML
    TableView<ToDo> toDoTable;
    @FXML
    TableColumn<ToDo, String> toDoTitel;
    @FXML
    TableColumn<ToDo, String> toDoDateCreated;
    
    
    
    @FXML
    public MenuButton selectCategory;
    @FXML
    public MenuItem other;
    @FXML
    public MenuItem shopping;
    @FXML
    public MenuItem notes;
    
    
  
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        toDoDAO = new ToDoDAO(new SqliteConnectionImpl());
        loadToDoLists();
    }    
     private void loadToDoLists() {
        toDoList = FXCollections.observableArrayList();
        toDoList.addAll(toDoDAO.GetToDoList());

        toDoTitel.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(((String) cellData.getValue().titel)));
        toDoDateCreated.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().dateCreated)));
        
        toDoTable.setItems(null);
        toDoTable.setItems(toDoList);
        
    }
     //
     public void addNewButtonPushedInOverviewView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Mette/ToDoApplication/view/ToDoView.fxml"));
        loader.setController(this);
        Parent Parent = (Parent) loader.load();
        Scene Scene = new Scene(Parent);
        mainApplication.primaryStage.setScene(Scene);
    } 
     
       public void selectCategoryDropDown(ActionEvent event) throws IOException {
        MenuButton m = new MenuButton("selectCategory");
        m.getItems().addAll(new MenuItem("Other"), new MenuItem("Shopping"), new MenuItem("Notes"));
        MenuItem menu = (MenuItem) event.getSource();
        selectCategory.setText(menu.getText());
       
    }
       
       
     public void showSelectedCategoryInDropDown(ActionEvent event) throws IOException {
         MenuItem menu = (MenuItem) event.getSource();
        selectCategory.setText(menu.getText());
     }
     
     
        public void cancelButtonPushedInAddNewView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Mette/ToDoApplication/view/OverviewView.fxml"));
        loader.setController(this);
        Parent Parent = (Parent) loader.load();
        Scene Scene = new Scene(Parent);
        mainApplication.primaryStage.setScene(Scene);
    }
      // Is called by the main application to give a reference back to itself.
       public void setMainApplication(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }
}
