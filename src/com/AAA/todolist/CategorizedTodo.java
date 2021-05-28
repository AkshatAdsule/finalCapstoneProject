package com.AAA.todolist;

import java.util.Date;

/**
 * Represents a todo with Category. It has a category and  a due data
 * of the todo
 * 
 * @author Gevork Andreasyan
 * @date 5/27/21
 * @version 0.1 
 * @see TimedTodo
 * Rev Notes:
 *      0.1  Create CategorizedTodo
 */
public class CategorizedTodo extends TimedTodo {
    /**
     * Represents the Category of the todo
     */
    private final String category;


    /**
     * Constructor
     *
     * @param title The title of the todo
     * @param dueDate the due date of the todo
     * @param category. The category of the todo
     */
    public CategorizedTodo(String title, Date dueDate, String category) {
        super(title, dueDate);
         this.category = category;
    }

    /**
     * Constructs a new todo object with a given title and done state
     *
     * @param title The title of the todo
     * @param todoState The current state of the todo
     * @param dueDate the due date of the todo
     * @param Category The category of the todo
     */
    public CategorizedTodo(String title, boolean todoState, Date dueDate, String category) {
        super(title, todoState, dueDate);
        this.category = category;
    }

    /**
     * Creates a representation of the Todo that can be inserted into the DB
     * 
     * @return A representation of the Todo that can be inserted into the DB
     */
    @Override
    public String getDBString() {
    	String dbString = super.getDBString();
        return dbString + ","+ category + "\n";
    }

    /**
     * Creates a string representation of the todo
     * 
     * @return A string representation of the todo
     */
    @Override
    public String toString() {
        String toStr = super.toString();
        return toStr + ", " + category;
    }
    
}
