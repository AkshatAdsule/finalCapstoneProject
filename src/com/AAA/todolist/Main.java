package com.AAA.todolist;

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
		System.out.println("q.......................... Quit");
	}

	public static void main(String[] args) {
		TodoList todoList = null;
		DB db = null;
		Scanner scanner = new Scanner(System.in);

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
		boolean done = false;
		do {
			printOptions();
			String option = scanner.next();

			switch (option) {
				case "1":
					for (Todo todo : todoList.getTodos()) {
						System.out.println(todo);
					}
					break;
				case "2":
					Todo newTodo = TimedTodo.userInputTodo(scanner);
					todoList.addTodo(newTodo);
					try {
						db.writeToDB(todoList);
					} catch (Exception e) {
						System.err.println("Failed to write to DB!");
					}

					break;
				case "q":
					done = true;
					break;
				default:
					System.err.println("Invalid choice!");
					break;
			}
		} while (!done);
		scanner.close();
//		GUI window = new GUI(todoList);
//		window.setSize(500, 700);
//		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		window.setResizable(true);
//		window.setVisible(true);
	}

}
