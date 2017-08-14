package Mette.ToDoApplication.model;

import java.util.Date;

/**
 * The model for a ToDo item
 *
 * @author Mette
 */
public class ToDo {

    private int id;
    private Date dateCreated;
    private String title;
    private String category;
    private String description;

    /**
     * Constructor to create a new ToDo item
     *
     * @param id the id of the ToDo
     * @param dateCreated the date of when the ToDo was created
     * @param title the title of the ToDo
     * @param category the category of the ToDo
     * @param description the description of the ToDo
     */
    public ToDo(int id, Date dateCreated, String title, String category, String description) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.title = title;
        this.category = category;
        this.description = description;
    }

    /**
     * Gets the id of the ToDo item
     *
     * @return id of the ToDo item
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the ToDo item
     *
     * @param id of the ToDo item
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the creation date of the ToDo item
     *
     * @return creation date of the ToDo item
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets the creation date of the ToDo item
     *
     * @param dateCreated creation date of the ToDo item
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * Gets the title of the ToDo item
     *
     * @return title of the ToDo item
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the ToDo item
     *
     * @param title of the ToDo item
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the category of the ToDo item
     *
     * @return category of the ToDo item
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets category of the ToDo item
     *
     * @param category of the ToDo item
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets description of the ToDo item
     *
     * @return description of the ToDo item
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description of the ToDo item
     *
     * @param description of the ToDo item
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
