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
     * 
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
     * Gets a todo at a specified index from todo list's arraylist 
     * 
     * @param index The index of the Todo to get
     * @return The Todo object at the given index
     */
    public Todo getTodo(int index) {
    	return todos.get(index);
    }
    
    /**
     * Removes a todo from the todolist
     * 
     * @param index The index of the item
     */
    public void removeTodo(int index) {
        todos.remove(index);
    }
    
    /**
     * Changes the done state of a todo at a given index
     * 
     * @param index The index at which the todoState should be changed
     */
    public void changeTodoState(int index) {
    	// Get current state
    	boolean currentState = todos.get(index).getTodoState();
    	todos.get(index).setTodoState(!currentState);
    }
}
