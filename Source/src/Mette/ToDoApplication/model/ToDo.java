/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mette.ToDoApplication.model;

import java.util.Date;

/**
 *
 * @author Mette
 */
public class ToDo {
    public Date dateCreated;
    public String titel;
    public String category;
    public String description;

    public ToDo(Date dateCreated, String Titel, String category, String description) {
        this.dateCreated = dateCreated;
        this.titel = Titel;
        this.category = category;
        this.description = description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String Titel) {
        this.titel = Titel;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}


