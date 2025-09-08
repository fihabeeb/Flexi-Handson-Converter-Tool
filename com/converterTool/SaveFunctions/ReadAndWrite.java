package com.converterTool.SaveFunctions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ReadAndWrite {
    public static void createNewFile(String _output)
    {
        String filePath = "history/output.txt";

        try {
            // This single line creates the file (if it doesn't exist) and writes the content.
            // If the file does exist, it will be overwritten.
            Files.writeString(Paths.get(filePath), _output);
            //System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public static void readHistoryFile()
    {
        Path filePath = Paths.get("history/output.txt"); // Assumes the file from the last example exists

        try {
            String content = Files.readString(filePath);
            System.out.println("File content:");
            System.out.println(content);
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    public static void appendToHistory(String _output)
    {
        Path filePath = Paths.get("history/output.txt");

        try {
            // The StandardOpenOption.APPEND flag tells the method to add to the end of the file
            String outputString = "\n" + _output;
            Files.writeString(filePath, outputString, StandardOpenOption.APPEND);
            //System.out.println("Successfully appended to the file.");
        } catch (IOException e) {
            createNewFile(_output);
            //System.err.println("An error occurred while appending to the file: " + e.getMessage());
        }
    }

    public static void deleteHistory()
    {
        Path filePath = Paths.get("history/output.txt"); // The file you want to empty

        try {
            // Writing an empty string overwrites and empties the file.
            Files.writeString(filePath, "");
            System.out.println("Successfully emptied the file.");
        } catch (IOException e) {
            System.err.println("An error occurred while emptying the file: " + e.getMessage());
        }
    }

}
