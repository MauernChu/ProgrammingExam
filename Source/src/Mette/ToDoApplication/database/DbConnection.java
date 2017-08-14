/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mette.ToDoApplication.database;

import java.sql.Connection;

/**
 * Interface for database connection
 *
 * @author Mette
 */
public interface DbConnection {

    /**
     * Creates a connection to the database
     *
     * @return connection to database
     */
    public Connection createConnection();

}
