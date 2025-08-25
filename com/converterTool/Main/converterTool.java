package com.converterTool.Main;

import com.converterTool.Units.NumberWithUnits;

import java.util.Scanner;


public class converterTool {
    public static void help()
    {
        System.out.println("Yes");
    }
    public static void main(String[] args)
    {
        Scanner inputScanner = new Scanner(System.in);
        help();
        boolean programRun = true;
        while(programRun)
        {
            System.out.println("Value to convert: ");
            String inputValue = inputScanner.nextLine();
            NumberWithUnits test = new NumberWithUnits(inputValue);
            System.out.println("Convert to: ");
            String convertValue = inputScanner.nextLine().toLowerCase();
            programRun = false;
        }
    }
}