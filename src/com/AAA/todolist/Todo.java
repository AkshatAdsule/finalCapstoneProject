package com.AAA.todolist;

/**
 * Represents a basic Todo. A Todo is, at its most basic, a name and the state
 * of the todo
 * 
 * @author Akshat Adsule
 * @date 4/29/21
 * @version 0.1 
 * Rev Notes: 
 *      0.1: Create a basic todo implementation
 */
public abstract class Todo {
    /**
     * Represents the title of the todo. The title of the todo must not change, so
     * it is final.
     */
    private final String title;

    /**
     * Represents the state of the todo; true represents done, false represents not done
     */
    private boolean todoState;

    /**
     * Constructs a new todo object with a given title and done state
     *
     * @param title The title of the todo
     * @param todoState  The current state of the todo
     */
    public Todo(String title, boolean todoState) {
        this.title = title;
        this.todoState = todoState;
    }

    /**
     * Constructs a new todo object with a given name and a default done state of
     * false
     * 
     * @param title The title of the todo
     */
    public Todo(String title) {
        this.title = title;
        this.todoState = false;
    }

    /**
     * Getter method to get the title of the todo
     * 
     * @return The title of the todo
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter method to get the current state of the todo
     * 
     * @return The todo state; true is done, false is not done
     */
    public boolean getTodoState() {
        return todoState;
    }

    /**
     * Setter method to set the state of the todo
     * 
     * @param newState The new state of the todo; true is done, false is not done
     */
    public void setTodoState(Boolean newState) {
        todoState = newState;
    }

    /**
     * Returns a string that can be placed in a database
     *
     * @see DB
     * @return A formatted string to be placed in the DB
     */
    abstract String getDBString();

    // Override of the toString method to make it easier to print the todo
    @Override
    public String toString() {
        // Format the string to look like this: {title}: {todoState}
        return title + ": " + todoState;
    }
}
