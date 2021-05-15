package com.AAA.todolist;

import com.AAA.todolist.gui.GUI;

import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	/**
	 * Prints the options the user could take when using the todolist
	 */
	private static void printOptions() {
		System.out.println("--- Welcome to your todolist ---");
		System.out.println("1..................... View list");
		System.out.println("2...................... Add item");
		System.out.println("3...... Mark item as done/undone");
		System.out.println("4................... Delete item");
		System.out.println("q.......................... Quit");
	}
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

		// Input loop
//		boolean done = false;
//		do {
//			printOptions();
//			String option = scanner.next();
//
//			switch (option) {
//				case "1": // View list
//					todoList.printList();
//					break;
//				case "2": // Add todo
//					Todo newTodo = TimedTodo.userInputTodo(scanner);
//					todoList.addTodo(newTodo);
//					break;
//				case "3": // Mark todo as done
//					// Print list for reference
//					todoList.printList();
//
//					// Ask user which todo to mark as done and check if it is valid
//					System.out.println("Which todo to change?");
//					int item = scanner.nextInt();
//
//					int maxItem = todoList.getTodos().size();
//					if (item > maxItem) {
//						System.err.println("Invalid item number!");
//						break;
//					}
//
//					// Subtract 1 as list starts counting at 0
//					todoList.changeTodoState(item - 1);
//					break;
//				case "4":
//					// Print list for reference
//					todoList.printList();
//
//					// Ask user which todo to mark as done and check if it is valid
//					System.err.println("Which todo to delete?");
//					int itemToDelete = scanner.nextInt();
//
//					int largestNumber = todoList.getTodos().size();
//					if (itemToDelete > largestNumber) {
//						System.err.println("Invalid item number!");
//						break;
//					}
//
//					// Subtract 1 as list starts counting at 0
//					todoList.removeTodo(itemToDelete - 1);
//					break;
//				case "q":
//					// Upon quit, write contents of todolist to the database
//					try {
//						db.writeToDB(todoList);
//					} catch (Exception e) {
//						System.err.println("Failed to write to DB!");
//					}
//					done = true;
//					break;
//				default:
//					System.err.println("Invalid choice!");
//					break;
//			}
//		} while (!done);
		scanner.close();
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
