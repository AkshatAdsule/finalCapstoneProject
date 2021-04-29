package com.AAA.todolist;

import java.util.Date;

/**
 * Represents a Timed todo. A Timed todo is one that has a due data
 * of the todo
 * 
 * @author Akshat Adsule
 * @date 4/29/21
 * @version 0.1 
 * @see Todo
 * Rev Notes: 
 *      0.1: Create a basic TimedTodo implementation
 */
public class TimedTodo extends Todo {
    /**
     * Represents the due date of the todo. This should not change, so it is final
     */
    private final Date dueDate;

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
     * @param done The current state of the todo
     * @param dueDate the due date of the todo
     */
    public TimedTodo(String title, boolean todoState, Date dueDate) {
        super(title, todoState);
        this.dueDate = dueDate;
    }

    // Override toString
    @Override
    public String toString() {
        String doneState = super.getTodoState() ? "done" : "not done";
        return " Due on " + dueDate.toString()  + ", " + super.getTitle() + ": " + doneState;
    }
    
}
