/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mette.ToDoApplication.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mette
 */
public class SqliteConnectionImpl implements DbConnection {

      @Override
    public Connection createConnection() {
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:ToDo.db");
            return conn;
        }catch (ClassNotFoundException | SQLException e){
            return null;
        }
    }
}
