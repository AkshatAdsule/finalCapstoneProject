package com.AAA.todolist;

import java.util.Date;
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

	private static TimedTodo getUserInputTodo(Scanner scanner) {
		System.out.println("\tWhat is the name? ");
		scanner.useDelimiter("\n");
		String name = scanner.next();

		System.out.println("\tWhen is it due?");

		return new TimedTodo(name, new Date());
	}

	public static void main(String[] args) {
		TodoList todoList = new TodoList();

		Scanner scanner = new Scanner(System.in);
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
					getUserInputTodo(scanner);
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
	}

}
