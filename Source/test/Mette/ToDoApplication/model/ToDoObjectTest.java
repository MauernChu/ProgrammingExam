/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mette.ToDoApplication.model;

import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mette
 */
public class ToDoObjectTest {
    
    @Test
    public void shouldCreateToDoAndReturnTitle() {
    //Given
    String title = "testTitle";
    
    //When
    ToDo testToDo = new ToDo(1, null, title, "category", "description");
    
    //Then
    Assert.assertTrue(testToDo.getTitle().equals(title));
    
    } 
}
