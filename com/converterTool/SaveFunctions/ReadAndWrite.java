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
            Files.writeString(Paths.get(filePath), _output);
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public static void readHistoryFile()
    {
        Path filePath = Paths.get("history/output.txt");

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
            String outputString = "\n" + _output;
            Files.writeString(filePath, outputString, StandardOpenOption.APPEND);
            //System.out.println("Successfully appended to the file.");
        } catch (IOException e) {
            createNewFile(_output);
        }
    }

    public static void deleteHistory()
    {
        Path filePath = Paths.get("history/output.txt");

        try {
            Files.writeString(filePath, "");
            System.out.println("Successfully emptied the file.");
        } catch (IOException e) {
            System.err.println("An error occurred while emptying the file: " + e.getMessage());
        }
    }

}
