/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mette.ToDoApplication.database;

import Mette.ToDoApplication.model.ToDo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for getting data from database
 *
 * @author Mette
 */
public class ToDoDAOImpl implements ToDoDAO {

    private final DbConnection dbConnection;

    /**
     * Constructor to create new ToDoDAO object
     *
     * @param dbConnection
     */
    public ToDoDAOImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    /**
     * Get all the columns with ToDo items from the database
     *
     * @return ArrayList with ToDo items
     */
    @Override
    public ArrayList<ToDo> getToDoList() {
        ArrayList<ToDo> ToDo = new ArrayList<>();

        Statement stmt;

        try {
            stmt = dbConnection.createConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ToDo");

            while (rs.next()) {
                ToDo.add(new ToDo(rs.getInt(4), rs.getDate(1), rs.getString(2), rs.getString(3), rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ToDoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ToDo;
    }

    /**
     * Add a new ToDo item with values from the UI.
     *
     * @param toDo new ToDo item
     */
    @Override
    public void addNewToDo(ToDo toDo) {
        PreparedStatement statement = null;

        try {
            statement = dbConnection.createConnection().prepareStatement("INSERT INTO ToDo (dateCreated, titel, category, description) VALUES(strftime('%J', 'NOW', 'localtime'),?,?,?)");
            statement.setString(1, toDo.getTitle());
            statement.setString(2, toDo.getCategory());
            statement.setString(3, toDo.getDescription());
            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ToDoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Deletes ToDo item from database based by it's id (primary key).
     *
     * @param idToBeDeleted id to be deleted.
     */
    @Override
    public void deleteToDoById(int idToBeDeleted) {
        PreparedStatement statement = null;

        try {
            statement = dbConnection.createConnection().prepareStatement("DELETE FROM ToDo WHERE id = ?");
            statement.setInt(1, idToBeDeleted);
            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ToDoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

//---------------Test code----------------------
//Method for mapping database information to new object -> for the popupwindow

/*   @Override
    public ToDo detailsPopUpToDo(int selectedShowToDo){             
            PreparedStatement statement = null;
            ToDo toDoDatabase = null;
            
            try{
            statement = dbConnection.createConnection().prepareStatement("SELECT * FROM ToDo WHERE id = ?");
            statement.setInt(1, selectedShowToDo);
            ResultSet rs = statement.executeQuery();
            int id = rs.getInt(4);
            Date dateCreated = rs.getDate(1);
            String title = rs.getString(2);
            String category = rs.getString(3);
            String description = rs.getString(5);
            toDoDatabase = new ToDo(id, dateCreated, title, category, description);
            
           } catch (SQLException ex) {
            Logger.getLogger(ToDoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
            return toDoDatabase;
    } */
