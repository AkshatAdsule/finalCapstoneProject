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
 *      0.1: Create a basic todolist implemetation
 */
public class TodoList {
    /**
     * Represents the actual list of todos
     * 
     * @see ArrayList
     */
    private ArrayList<Todo> todos;

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
        todos = this.todos;
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

}
