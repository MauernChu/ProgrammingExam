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
 *
 * @author Mette
 */
public class ToDoDAO {

    private final DbConnection dbConnection;

    public ToDoDAO(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public ArrayList<ToDo> GetToDoList() {
        ArrayList<ToDo> ToDo = new ArrayList<ToDo>();

        Statement stmt = null;

        try {
            stmt = dbConnection.createConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ToDo");

            while (rs.next()) {
                ToDo.add(new ToDo(rs.getInt(4), rs.getDate(1), rs.getString(2), rs.getString(3), rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ToDoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ToDo;
    }

    public void addNewToDo(ToDo toDo) {
        PreparedStatement statement = null;

        try {
            statement = dbConnection.createConnection().prepareStatement("INSERT INTO ToDo (dateCreated, titel, category, description) VALUES(strftime('%J', 'NOW', 'localtime'),?,?,?)");
            statement.setString(1, toDo.getTitle());
            statement.setString(2, toDo.getCategory());
            statement.setString(3, toDo.getDescription());
            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ToDoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteToDoById(int idToBeDeleted) {
        PreparedStatement statement = null;

        try {
            statement = dbConnection.createConnection().prepareStatement("DELETE FROM ToDo WHERE id = ?");
            statement.setInt(1, idToBeDeleted);
            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ToDoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
