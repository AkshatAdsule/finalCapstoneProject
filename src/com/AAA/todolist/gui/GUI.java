package com.AAA.todolist.gui;
import com.AAA.todolist.TimedTodo;
import com.AAA.todolist.Todo;
import com.AAA.todolist.TodoList;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Represents the GUI of the todolist
 *
 * @author Akshat Adsule
 * @author Shaun Andrews
 * @date 5/14/21
 * @version 0.2
 * Rev Notes:
 *      0.1: Create non functional GUI
 *      0.2: Add working GUI with working selection models and list model
 */

public class GUI extends JFrame {
    private final TodoList todoList;

    private final DefaultListSelectionModel listSelectionModel;

    private final DefaultListModel<Todo> todoListModel;

    private int currentSelectedTodo = -1;

    public GUI(TodoList todoList) {
        // Set title of window
        super("To Do List");

        this.todoList = todoList;
        todoListModel = new DefaultListModel<Todo>();
        // Add items from todolist to todolist model
        todoListModel.addAll(todoList.getTodos());

        listSelectionModel = new DefaultListSelectionModel();
        // Only allow one item to be selected at a time
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        // Listen to changes to selections in the list
        listSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Make sure item is not being changed as listener is called
                if(!e.getValueIsAdjusting()) {
                    // Get the source of the event in the form of a ListSelectionModel
                    DefaultListSelectionModel eventSource = (DefaultListSelectionModel) e.getSource();
                    // Set currentSelectedTodo to the newly selected todo
                    currentSelectedTodo = eventSource.getLeadSelectionIndex();
                }
            }
        });

        // Instantiate root container
        Box rootContainer = Box.createVerticalBox();
        rootContainer.setPreferredSize(new Dimension(500, 700));

        // Add components to root container
        rootContainer.add(buildList());
        rootContainer.add(buildInput());
        rootContainer.add(buildTodoControlComponent());

        // Add root container to content pane
        Container c = getContentPane();
        c.add(rootContainer);
    }

    /**
     * Builds the ui to view todo items
     * @return List component
     */
    private JComponent buildList() {
        JList<Todo> list = new JList<Todo>(todoListModel);
        list.setSelectionModel(listSelectionModel);
        // Render each todo using TodoListModel
        list.setCellRenderer(new TodoListItem());

        JScrollPane todoRoot = new JScrollPane(list);
        todoRoot.setPreferredSize(new Dimension(500, 630));
        return todoRoot;
    }

    /**
     * Builds the ui to take user input
     *
     * @return Input component
     */
    private JComponent buildInput() {
        Box inputContainer= Box.createHorizontalBox();

        // Title input
        JTextField titleTextField = new JTextField();
        titleTextField.setPreferredSize(new Dimension(300, 70));
        
        // Get current year to prepopulate fields
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1; // +1 as month count starts at 0
        int day = Calendar.getInstance().get(Calendar.DATE);
        
        // Year input
        JTextField yearTextField = new JTextField(year + "");
        yearTextField.setPreferredSize(new Dimension(50, 70));

        JTextField monthTextField = new JTextField(month + "");
        monthTextField.setPreferredSize(new Dimension(50, 70));

        JTextField dayTextField = new JTextField(day + 1 + ""); // +1 because generally tasks start from next day onwards
        dayTextField.setPreferredSize(new Dimension(50, 70));

        JButton addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(50, 70));

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Parse input
                final String title = titleTextField.getText();
                final int year = Integer.parseInt(yearTextField.getText());
                final int month = Integer.parseInt(monthTextField.getText());
                final int day = Integer.parseInt(dayTextField.getText());

                // Create new todo
                TimedTodo newTodo = new TimedTodo(title, new Date(year, month, day));

                // Push new todo to list
                todoList.addTodo(newTodo);
                todoListModel.addElement(newTodo);
                repaint();
            }
        });

        // Add components to inputContainer
        inputContainer.add(titleTextField);
        inputContainer.add(monthTextField);
        inputContainer.add(dayTextField);
        inputContainer.add(yearTextField);
        inputContainer.add(addButton);

        // Set input size to 10% of the height
        inputContainer.setPreferredSize(new Dimension(500, 70));
        return inputContainer;
    }

    /**
     * Builds the UI to interact with individual todos
     *
     * @return A TodoControl component
     */
    private JComponent buildTodoControlComponent() {
        Box controlRoot = Box.createHorizontalBox();

        // Button to mark todos as done or not done
        JButton toggleTodoStateButton = new JButton("Toggle Done");
        toggleTodoStateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Todo selectedTodo = todoListModel.get(currentSelectedTodo);
                selectedTodo.setTodoState(!selectedTodo.getTodoState());
                todoListModel.set(currentSelectedTodo, selectedTodo);
                repaint();
            }
        });

        // Button to delete selected todo
        JButton deleteTodoButton = new JButton("Delete");
        deleteTodoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                todoListModel.remove(currentSelectedTodo);
                todoList.removeTodo(currentSelectedTodo);
            }
        });

        // Add buttons to root container
        controlRoot.add(toggleTodoStateButton);
        controlRoot.add(deleteTodoButton);
        return controlRoot;
    }
}
