/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mette.ToDoApplication.database;

import Mette.ToDoApplication.model.ToDo;
import java.util.ArrayList;

/**
 * Interface for getting data from database
 * @author Mette
 */
public interface ToDoDAO {

    /**
     * Get all the columns with ToDo items from the database
     * @return ArrayList with ToDo items
     */
    ArrayList<ToDo> getToDoList();

    /**
     * Add a new ToDo item with values from the UI.
     * @param toDo new ToDo item
     */
    void addNewToDo(ToDo toDo);

    /**
     * Deletes ToDo item from database based by it's id.
     * @param idToBeDeleted id to be deleted.
     */
    void deleteToDoById(int idToBeDeleted);

}
