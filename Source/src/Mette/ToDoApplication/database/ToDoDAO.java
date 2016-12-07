/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mette.ToDoApplication.database;

import Mette.ToDoApplication.model.ToDo;
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
                ToDo.add(new ToDo(rs.getDate(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ToDoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ToDo;
    }
}
    

