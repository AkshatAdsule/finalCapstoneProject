package com.AAA.todolist.gui;

import com.AAA.todolist.TimedTodo;
import com.AAA.todolist.Todo;
import com.AAA.todolist.TodoList;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.*;

public class GUI extends JFrame {
    /**
     * The todo list
     */
    private TodoList todoList;

    private final DefaultListModel<Todo> todoListModel;

    public GUI(TodoList todoList) {
        // Set title of todolist
        super("To Do List");
        this.todoList = todoList;
        this.todoListModel = new DefaultListModel<Todo>();

        // Instantiate root container
        Box rootContainer = Box.createVerticalBox();
        rootContainer.setPreferredSize(new Dimension(500, 700));

        // Add components to root container
        rootContainer.add(buildList());
        rootContainer.add(buildInput());
        Container c = getContentPane();
        c.add(rootContainer);
    }

    /**
     * Builds the ui to view todo items
     * @return List component
     */
    private JComponent buildList() {
        JList<Todo> list = new JList<Todo>(todoListModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setCellRenderer(new TodoListItem(todoListModel));
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
        
        // Year input
        JTextField yearTextField = new JTextField("2021");
        yearTextField.setPreferredSize(new Dimension(50, 70));

        JTextField monthTextField = new JTextField("4");
        monthTextField.setPreferredSize(new Dimension(50, 70));

        JTextField dayTextField = new JTextField("30");
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
}
