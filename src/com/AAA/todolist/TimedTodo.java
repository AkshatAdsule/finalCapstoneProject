package com.AAA.todolist;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a Timed todo. A Timed todo is one that has a due data
 * of the todo
 * 
 * @author Akshat Adsule
 * @date 5/3/21
 * @version 0.2 
 * @see Todo
 * Rev Notes: 
 *      0.1: Create a basic TimedTodo implementation
 *      0.2: Add toDBString method
 */
public class TimedTodo extends Todo {
    /**
     * Represents the due date of the todo. This should not change, so it is final
     */
    private final Date dueDate;

    /**
     * To format Date objects into strings
     */
    private static final SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
 
    /**
     * Constructs a new todo with a provided title and due date
     * @param title The title of the todo
     * @param dueDate The due date of the todo
     */
    public TimedTodo(String title, Date dueDate) {
        super(title);
        this.dueDate = dueDate;
    }

    /**
     * Constructs a new todo object with a given title and done state
     *
     * @param title The title of the todo
     * @param todoState The current state of the todo
     * @param dueDate the due date of the todo
     */
    public TimedTodo(String title, boolean todoState, Date dueDate) {
        super(title, todoState);
        this.dueDate = dueDate;
    }

    /**
     * Creates a representation of the Todo that can be inserted into the DB
     * 
     * @return A representation of the Todo that can be inserted into the DB
     */
    @Override
    public String getDBString() {
    	String isDone = getTodoState() ? "Y" : "N";
        return getTitle() + "," + dueDate.toString() + "," + isDone + "\n";
    }

    /**
     * Creates a string representation of the todo
     * 
     * @return A string representation of the todo
     */
    @Override
    public String toString() {
        char doneState = getTodoState() ? DONE_ICON : NOT_DONE_ICON;
        String formattedDate = formatter.format(dueDate);
        return doneState + " " + super.getTitle()+ ", due on " + formattedDate;
    }
    
}
