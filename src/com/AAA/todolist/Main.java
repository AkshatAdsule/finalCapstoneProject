package com.AAA.todolist;
import com.AAA.todolist.gui.GUI;
import javax.swing.*;
import java.io.IOException;

/**
 * Represents the start point of the program
 * 
 * @author Akshat Adsule
 * @date 5/14/21
 * @version 0.3 
 * Rev Notes: 
 *      0.1: Basic main method to test Todo Class
 *      0.2: Add CLI to interact with todolist
 *      0.3: Show GUI instead of CLI
 *
*/

public class Main {

	
	/**
	 * Tries to establish a DB connection. DB not connecting is a critical error, and the program exits in that case 
	 * @return an instance of DB
	 */
	private static DB acquireDB() {
		try {
			DB db = new DB("todos.txt");
			return db;
		} catch (IOException e) {
			// DB failed to initialize 
			System.err.println("Failed to acquire DB!");
			System.exit(1); // Exit
			return null;
		}
	}
	
	
	/**
	 * Tries to read from the DB and generate a TodoList instance
	 * 
	 * @param db The DB to read from
	 * @return A generated TodoList based on the contents of the DB
	 */
	private static TodoList readDB(DB db) {
		TodoList todoList;

		// Try to establish DB and read todos. If it fails, exit the program
		try {
			todoList = db.readDB();
			return todoList;
		} catch (IOException e) {
			System.err.println("Failed to read DB!");
			// Not being able to read DB is a critical error, so we should exit in that scenario
			System.exit(1);
			return null;
		}
	}
	
	public static void main(String[] args) {
		// Get DB and todoList
		final DB db = acquireDB();
		final TodoList todoList = readDB(db);

		// Setup GUI
		GUI window = new GUI(todoList);
		window.setSize(500, 700);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setVisible(true);

		// Setup listener on window close events to ask the user to save
		window.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				// Show a popup asking the user if they want to save and check if they said Yes
		        if (JOptionPane.showConfirmDialog(window, 
		            "Do you want to save changes before exiting?", "Save changes?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		            	try {
							db.writeToDB(todoList);
						} catch (IOException e) {
							JOptionPane.showMessageDialog(window, "Failed to write to DB");
							e.printStackTrace();
						}
		        }
		    }
		});
	}

}
