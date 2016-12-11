/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mette.ToDoApplication.controller;

import Mette.ToDoApplication.MainApplication;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Mette
 */
public class ToDoListController implements Initializable {

    MainApplication mainApplication;

    //ObservarbleList made for containing ToDo objects. 
    private ObservableList<ToDo> toDoList;

    //New variable of ToDoDAO interface
    private ToDoDAO toDoDAO;

    //Making name for table in the tableView and the columns.
    @FXML
    TableView<ToDo> toDoTable;
    @FXML
    TableColumn<ToDo, String> toDoTitel;
    @FXML
    TableColumn<ToDo, String> toDoCategory;
    @FXML
    TableColumn<ToDo, String> toDoDescription;
    @FXML
    TableColumn<ToDo, String> toDoDateCreated;

    @FXML
    private TextField titel;
    @FXML
    private TextArea description;

    @FXML
    public MenuButton selectCategory;
    @FXML
    public MenuItem other;
    @FXML
    public MenuItem shopping;
    @FXML
    public MenuItem notes;

    private String selectedCategory = "Other";

    /**
     * Create a new ToDo controller object
     * @param toDoDAO interface of the ToDo database access
     */
    public ToDoListController(ToDoDAO toDoDAO) {
        this.toDoDAO = toDoDAO;
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadToDoLists();
    }

    //Load todolist from database
    private void loadToDoLists() {
        toDoList = FXCollections.observableArrayList();
        toDoList.addAll(toDoDAO.getToDoList());

        toDoTitel.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(((String) cellData.getValue().getTitle())));
        toDoCategory.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(((String) cellData.getValue().getCategory())));
        toDoDescription.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(((String) cellData.getValue().getDescription())));
        toDoDateCreated.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(new SimpleDateFormat("yyyy-MM-dd").format(cellData.getValue().getDateCreated())));

        toDoTable.setItems(null);
        toDoTable.setItems(toDoList);

    }

    /**
     * Loads ToDoView when "Add New" button is pushed in OverviewView.
     * @param event
     * @throws IOException
     */
    public void addNewButtonPushedInOverviewView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Mette/ToDoApplication/view/ToDoView.fxml"));
        loader.setController(this);
        Parent Parent = (Parent) loader.load();
        Scene Scene = new Scene(Parent);
        mainApplication.primaryStage.setScene(Scene);
    }

    /**
     * Shows selected category for making new ToDo Item in ToDoView, and stores the selected category in the variable "SelectedCategory"
     * @param event
     * @throws IOException
     */
    public void showSelectedCategoryInDropDown(ActionEvent event) throws IOException {
        MenuItem menu = (MenuItem) event.getSource();
        selectCategory.setText(menu.getText());
        selectedCategory = menu.getText();
    }

    /**
     * Loads OverviewView when "cancel" Button is pushed in ToDoView.
     * @param event
     * @throws IOException
     */
    public void cancelButtonPushedInToDoView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Mette/ToDoApplication/view/OverviewView.fxml"));
        loader.setController(this);
        Parent Parent = (Parent) loader.load();
        Scene Scene = new Scene(Parent);
        mainApplication.primaryStage.setScene(Scene);
    }

    /**
     * Stores the title and description entered by user, and makes new ToDo object containing the new values from user.
     * Adds the new object to the database, and loads OverviewView.
     * @param event
     * @throws IOException
     */
    public void addNewButtonPushedInToDoView(ActionEvent event) throws IOException {
        String newTitle = titel.getText();
        String newDescription = description.getText();

        ToDo todo = new ToDo(0, null, newTitle, selectedCategory, newDescription);
        toDoDAO.addNewToDo(todo);

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Mette/ToDoApplication/view/OverviewView.fxml"));
        loader.setController(this);
        Parent Parent = (Parent) loader.load();
        Scene Scene = new Scene(Parent);
        mainApplication.primaryStage.setScene(Scene);
    }

    /**
     * Stores the item by it's id that has been clicked on, in the variable idToBeDeleted.
     * Deletes the item from the database, and then updates the database.
     * @param event
     * @throws IOException
     */
    public void deleteButtonInToDOViewPushed(ActionEvent event) throws IOException {
        ToDo selectedToDo = toDoTable.getSelectionModel().getSelectedItem();
        int idToBeDeleted = selectedToDo.getId();

        toDoDAO.deleteToDoById(idToBeDeleted);
        loadToDoLists();
    }

    // Is called by the main application to give a reference back to itself.

    /**
     * Makes a new main application object.
     * @param mainApplication is the main application
     */
    
    public void setMainApplication(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }
}
