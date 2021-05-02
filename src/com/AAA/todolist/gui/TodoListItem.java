package com.AAA.todolist.gui;

import com.AAA.todolist.Todo;
import com.AAA.todolist.TodoList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TodoListItem implements ListCellRenderer<Todo> {
    private DefaultListModel<Todo> listModel;

    public TodoListItem (DefaultListModel<Todo> listModel) {
        this.listModel = listModel;
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Todo> list, Todo value, int index, boolean isSelected, boolean cellHasFocus) {
        Todo todo = (Todo) list.getModel().getElementAt(index);
        JCheckBox doneCheckBox = new JCheckBox(todo.toString(), todo.getTodoState());

        doneCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                todo.setTodoState(!todo.getTodoState());
                listModel.setElementAt(todo, index);
                doneCheckBox.setSelected(true);
                System.out.println("Called change listener");

                JCheckBox cbLog = (JCheckBox) e.getSource();
                if (cbLog.isSelected()) {
                    System.out.println("Logging is enabled");
                } else {
                    System.out.println("Logging is disabled");
                }
            }
        });

        return doneCheckBox;
    }
}
