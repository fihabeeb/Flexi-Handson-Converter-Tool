package com.converterTool.Main;

import com.converterTool.Converter.MetricConverter;
import com.converterTool.Converter.TemperatureConverter;
import com.converterTool.SaveFunctions.ReadAndWrite;
import com.converterTool.Units.Distance;
import com.converterTool.Units.NumberWithUnits;
import com.converterTool.Units.Temperature;
import com.converterTool.Units.Weight;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import java.util.Scanner;


public class converterTool {
    public static String getCurrentTime() {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Create a formatter with the desired pattern.
        // Using Locale.ENGLISH ensures the month is "Sep" regardless of the system's language.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy h:mm a", Locale.ENGLISH);

        // Apply the formatter to the current date-time object
        String formattedDateTime = now.format(formatter);

        return (formattedDateTime + ": ");
    }
    static class unitDeterminer {
        public static boolean isNumeric(String str) {
            boolean isValid = true;
            try {
                Double.parseDouble(str);
                return true;
            } catch (NumberFormatException e) {
                //return false;
            }
            try {
                Integer.parseInt(str);
                return true;
            } catch (NumberFormatException e) {
                //return false;
            }
            try {
                Float.parseFloat(str);
                return true;
            } catch (NumberFormatException e) {
                //return false;
            }
            return false;
        }

        int isUnitValid(String _userInputString)
        {
            String[] splits = _userInputString.split(" ");
            String unitSign;
            if (splits.length > 2)
            {
                return 997;
            }
            if (splits.length < 2)
            {
                return 996;
            }
            unitSign = splits[1].toLowerCase();
            if (!isNumeric(splits[0]))
            {
                System.out.println(splits[0]);
                return 998;
            }
            return switch (unitSign) {
                case "c", "f", "k" -> 1;
                case "m", "km", "cm", "mm" -> 2;
                case "g", "kg" -> 3;
                default -> 999;
            };
        }
    }
    public static void help()
    {
        System.out.println("Ensure a single space between the value and its unit");
        System.out.println("Units: c, f, k, m, km");
        System.out.println("cm, mm, g, kg");
        System.out.println("Type: \"help\" to get help");
        System.out.println("Type: \"history\" to get history");
        System.out.println("Type: \"delete\" to erase history");
        System.out.println("Type: \"exit\" to exit");
    }
    public static void main(String[] args)
    {
        Scanner inputScanner = new Scanner(System.in);
        help();
        boolean programRun = true;
        String[] unitsTemperature = {"c", "f", "k"};
        String[] unitsMetric = {"m", "km", "cm", "mm", "g", "kg"};
        unitDeterminer unitHelper = new unitDeterminer();
        StringBuilder saveFileString = new StringBuilder();
        saveFileString.append(getCurrentTime());
        while(programRun)
        {
            System.out.println("Input: ");
            String inputValue = inputScanner.nextLine();

            if (inputValue.equals("help"))
            {
                help();
                continue;
            }

            if (inputValue.equals("history"))
            {
                ReadAndWrite.readHistoryFile();
                continue;
            }

            if (inputValue.equals("delete"))
            {
                System.out.println("History deleted");
                ReadAndWrite.deleteHistory();
                continue;
            }

            if (inputValue.equals("exit"))
            {
                System.out.println("Exiting...");
                programRun = false;
                continue;
            }
            saveFileString.append(inputValue);
            saveFileString.append(" to ");
            MetricConverter metricConverter = new MetricConverter();
            TemperatureConverter temperatureConverter = new TemperatureConverter();
            boolean foundValueA = false;
            int unitHelperOutput = unitHelper.isUnitValid(inputValue);
            if (unitHelperOutput == 999)
            {
                System.out.println("The input unit is not supported, please try again");
                continue;
            }
            else if (unitHelperOutput == 998)
            {
                System.out.println("The input number is not a number, please try again");
                continue;
            }
            else if (unitHelperOutput == 997)
            {
                System.out.println("The input contains more than a number and its unit, please try again");
                continue;
            }
            else if (unitHelperOutput == 996)
            {
                System.out.println("Please ensure there is a number and a unit with");
                System.out.println("a space between them and try again");
                continue;
            }
            NumberWithUnits inputObject;
            switch (unitHelperOutput)
            {
                case 1:
                    inputObject = new Temperature(inputValue);
                    break;
                case 2:
                    inputObject = new Distance(inputValue);
                    break;
                case 3:
                    inputObject = new Weight(inputValue);
                    break;
                default:
                    System.out.println("An unexpected error has occurred, please try again");
                    continue;
            }
            boolean isValidSecondaryInput = true;
            while (isValidSecondaryInput) {
                System.out.println("Convert to: ");
                String convertValue = inputScanner.nextLine().toLowerCase();
                if (unitHelperOutput == 1) {
                    if (!convertValue.equals("c") && !convertValue.equals("f") && !convertValue.equals("k")) {
                        System.out.println("Please select a valid converting unit");
                    }
                    else {
                        String output = temperatureConverter.convertValues(inputObject, convertValue);
                        saveFileString.append(output);
                        System.out.println(output);
                        isValidSecondaryInput = false;
                    }
                } else if (unitHelperOutput == 2) {
                    if (!convertValue.equals("m") && !convertValue.equals("km") && !convertValue.equals("cm") && !convertValue.equals("mm")) {
                        System.out.println("Please select a valid converting unit");
                    }
                    else {
                        String output = metricConverter.convertValues(inputObject, convertValue);
                        saveFileString.append(output);
                        System.out.println(output);
                        isValidSecondaryInput = false;
                    }
                } else if (unitHelperOutput == 3) {
                    if (!convertValue.equals("g") && !convertValue.equals("kg")) {
                        System.out.println("Please select a valid converting unit");
                    }
                    else {
                        String output = metricConverter.convertValues(inputObject, convertValue);
                        saveFileString.append(output);
                        System.out.println(output);
                        isValidSecondaryInput = false;
                    }
                }
            }
            ReadAndWrite.appendToHistory(saveFileString.toString());
            saveFileString.setLength(0);
            saveFileString.append(getCurrentTime());
        }
    }
}