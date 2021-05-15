package com.AAA.todolist;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

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

    private static final SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");

   /**
    * Returns a new Todo object by asking the user for the details of the todo; ie name and due date
    * @param scanner a scanner
    * @return The user inputted todo
    */
    public static Todo userInputTodo(Scanner scanner) {
        System.out.println("\tWhat is the name? ");
		// Set delimiter to new lines to process multi-word titles
		scanner.useDelimiter("\n");
		String name = scanner.next();
		// Reset delimiter to spaces

		System.out.println("\tWhat year is it due? ");
		int year = scanner.nextInt();
		System.out.println("\tWhat month is it due? (1-12)");
		// Subtract 1 because month counting starts from 0; ie jan -> 0 and dec -> 11
		int month = scanner.nextInt() - 1;
		System.out.println("\tWhat day is it due? ");
		int day = scanner.nextInt();

		Date dueDate = new Date(year, month, day);

		return new TimedTodo(name, dueDate);
    }
    
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

    @Override
    public String getDBString() {
    	String isDone = getTodoState() ? "Y" : "N";
        return getTitle() + "," + dueDate.toString() + "," + isDone + "\n";
    }

    // Override toString
    @Override
    public String toString() {
        char doneState = getTodoState() ? DONE_ICON : NOT_DONE_ICON;
        String formattedDate = formatter.format(dueDate);
        return doneState + " " + super.getTitle()+ ", due on " + formattedDate;
    }
    
}
