package com.AAA.todolist;
import com.AAA.todolist.gui.GUI;
import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the start point of the program
 * 
 * @author Akshat Adsule
 * @date 5/14/21
 * @version 0.3 
 * Rev Notes: 
 *      0.1: Basic main method to test Todo Class
 *      0.2: Add CLI to interact with todo
 *      0.3: Show GUI instead of CLI
 *
*/

public class Main {

	
	public static void main(String[] args) {
		TodoList todoList = null;
		Scanner scanner = new Scanner(System.in);
		DB db = null;

		// Try to establish DB and read todos. If it fails, exit the program
		try {
			db = new DB("todos.txt");
			todoList = db.readDB();
		} catch (IOException e) {
			System.err.println("Failed to acquire DB!");
			// DB not working is a critical error, so we should exit in that scenario
			System.exit(1);
		}

		// Show GUI
		GUI window = new GUI(todoList);
		window.setSize(500, 700);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setVisible(true);

		// Instantiate final versions of DB and Todolist so that the compiler is happy
		DB finalDb = db;
		TodoList finalTodoList = todoList;
		window.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(window, 
		            "Do you want to save changes before exiting?", "Save changes?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		            	try {
							finalDb.writeToDB(finalTodoList);
						} catch (IOException e) {
							JOptionPane.showMessageDialog(window, "Failed to write to DB");
							e.printStackTrace();
						}
		        }
		    }
		});
	}

}
