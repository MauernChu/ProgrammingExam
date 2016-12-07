/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mette.ToDoApplication.controller;

import Mette.ToDoApplication.database.SqliteConnectionImpl;
import Mette.ToDoApplication.database.ToDoDAO;
import Mette.ToDoApplication.model.ToDo;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Mette
 */
public class OverviewViewController implements Initializable {

     private ObservableList<ToDo> toDoList;
     
     private ToDoDAO toDoDAO;
     
    @FXML
    TableView<ToDo> toDoTable;
    @FXML
    TableColumn<ToDo, String> toDoTitel;
    @FXML
    TableColumn<ToDo, String> toDoDateCreated;
  
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
}
