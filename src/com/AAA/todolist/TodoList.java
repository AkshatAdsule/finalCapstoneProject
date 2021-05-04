package com.AAA.todolist;
import java.util.ArrayList;

/**
 * Represents a TodoList. A todolist simply contains a list of todos
 * of the todo
 * 
 * @author Akshat Adsule 
 * @date 4/29/21
 * @version 0.1 
 * Rev Notes: 
 *      0.1: Create a basic todolist implementation
 */
public class TodoList {
    /**
     * Represents the actual list of todos
     * 
     * @see ArrayList
     */
    private final ArrayList<Todo> todos;

    /**
     * Constructs a new TodoList with an empty list of todos
     */
    public TodoList() {
        todos = new ArrayList<Todo>();
    }

    /**
     * Constructs a new TodoList with a predefined list of todos
     * 
     * @param todos A predefined list of todos
     */
    public TodoList(ArrayList<Todo> todos) {
        this.todos = todos;
    }

    /**
     * Getter method to get todos
     * @return The list of todos
     */
    public ArrayList<Todo> getTodos() {
        return todos;
    }

    /**
     * Adds a new todo to the list of todos
     * 
     * @param newTodo The todo to add
     */
    public void addTodo(Todo newTodo) {
        todos.add(newTodo);
    }
    
    /**
     * Removes a todo from the todolist
     * @param index The index of the item
     */
    public void removeTodo(int index) {
        todos.remove(index);
    }
    
    public void changeTodoState(int index) {
    	// Get current state
    	boolean currentState = todos.get(index).getTodoState();
    	todos.get(index).setTodoState(!currentState);
    }
    
    /**
     * Prints the contents of the todolist to the console
     */
    public void printList() {
    	// Counter to show item number
    	int counter = 1;
    	
    	for(Todo item : todos) {
    		// Header is tab indented and shows the count of item
    		String header = "\t" + counter + ". ";
    		System.out.println(header + item.toString());
    		counter++;
    	}
    	// Print empty line after
    	System.out.println();
    }

}
