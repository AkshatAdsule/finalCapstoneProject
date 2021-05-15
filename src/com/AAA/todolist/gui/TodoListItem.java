package com.AAA.todolist.gui;

import com.AAA.todolist.Todo;
import javax.swing.*;
import java.awt.*;


/**
 * Represents an item in the GUI todolist
 * of the todo
 *
 * @author Akshat Adsule
 * @author Shaun Andrews
 * @date 5/14/21
 * @version 0.2
 * @see ListCellRenderer
 * Rev Notes:
 *      0.1: Able to render, but checkbox does not work
 *      0.2: Use JLabel instead of checkbox
 */
public class TodoListItem implements ListCellRenderer<Todo> {
    // Color to show if item is selected
    public static final Color SELECTED_COLOR = new Color(3, 203, 255); // Light blueish
    // Color to show if item is not selected
    public static final Color DEFAULT_COLOR = new Color(168, 169, 175); // Light gray

    // Renders a todo from the list of todos
    @Override
    public Component getListCellRendererComponent(JList<? extends Todo> list, Todo value, int index, boolean isSelected, boolean cellHasFocus) {
        Todo todo = list.getModel().getElementAt(index);
        JLabel todoLabel = new JLabel(todo.toString());

        // Check if current todo is selected. If it is, change the background to reflect so
        Color backgroundColor = isSelected ? SELECTED_COLOR : DEFAULT_COLOR;
        todoLabel.setBackground(backgroundColor);
        todoLabel.setOpaque(true);

        return todoLabel;
    }
}
