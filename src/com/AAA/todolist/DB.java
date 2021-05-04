package com.AAA.todolist;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Allows for basic DB like functionality to store todos.
 *
 * @author Akshat Adsule
 * @date 5/1/21
 * @version 0.1
 * Rev Notes:
 *      0.1: Create a basic DB implementation
 */

public class DB {
    /**
     * Represents the actual file where the DB is located. File should not change, so it is final
     */
    private final File dbFile;

    /**
     * Constructs a new DB object with a file from the given file path
     *
     * @param filePath The relative path of the file, eg "todos.txt"
     * @throws IOException Throws error if file is invalid
     */
    public DB(String filePath) throws IOException {
        dbFile = new File(filePath);
    }

    /**
     * Reads dbFile and returns a {@link TodoList}
     *
     * @return A TodoList from values in the dbFile
     * @throws IOException Throws error if file is invalid
     * @see TodoList
     */
    public TodoList readDB() throws IOException {
        // Make sure file exists, if not create the file
        if (dbFile.createNewFile()){
            // File does not exist
            System.out.println("Store does not exist, creating store");
            // Return a blank todolist as file does not exist, and hence, has no todos saved
            return new TodoList();
        } else {
            // File exists

            // Setup filestream
            FileInputStream inputStream = new FileInputStream(dbFile);

            // Setup scanner to read file
            Scanner fileScanner = new Scanner(inputStream);
            ArrayList<Todo> listItems = new ArrayList<Todo>();

            // For each line, get the title and due date of the TimedTodo and add it to the listItems arraylist
            while(fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();

                // Split line by commas
                String[] splitLine = line.split(",");
                String title = splitLine[0];
                Date dueDate = new Date(splitLine[1]);
                boolean isDone = splitLine[2].equals("Y");
                listItems.add(new TimedTodo(title, isDone, dueDate));
            }
            // Return todolist with items from dbFile
            fileScanner.close();
            return new TodoList(listItems);
        }
    }

    /**
     * Attempts to save a given todolist to the dbFile
     *
     * @param list The list to save in the database
     * @throws IOException Throws error if file is invalid
     */
    public void writeToDB(TodoList list) throws IOException {
        // False signifies that the the file should be cleared before writing to it again
        FileWriter writer = new FileWriter(dbFile, false);

        // Iterate through each todo, and add it to the dbFile
        for (Todo todo: list.getTodos()) {
            String dbLine = todo.getDBString();
            writer.append(dbLine);
        }
        // Close the writer to save the file
        writer.close();
    }
}
