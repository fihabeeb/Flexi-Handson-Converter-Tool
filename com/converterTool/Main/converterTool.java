package com.converterTool.Main;

import com.converterTool.Converter.MetricConverter;
import com.converterTool.Converter.TemperatureConverter;
import com.converterTool.Units.Distance;
import com.converterTool.Units.NumberWithUnits;
import com.converterTool.Units.Temperature;
import com.converterTool.Units.Weight;

import java.util.Scanner;


public class converterTool {
    static class unitDeterminer {
        public static boolean isNumeric(String str) {
            try {
                Integer.parseInt(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
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
    }
    public static void main(String[] args)
    {
        Scanner inputScanner = new Scanner(System.in);
        help();
        boolean programRun = true;
        String[] unitsTemperature = {"c", "f", "k"};
        String[] unitsMetric = {"m", "km", "cm", "mm", "g", "kg"};
        unitDeterminer unitHelper = new unitDeterminer();
        while(programRun)
        {
            System.out.println("Value to convert: ");
            String inputValue = inputScanner.nextLine();
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
                        System.out.println(temperatureConverter.convertValues(inputObject, convertValue));
                        isValidSecondaryInput = false;
                    }
                } else if (unitHelperOutput == 2) {
                    if (!convertValue.equals("m") && !convertValue.equals("km") && !convertValue.equals("cm") && !convertValue.equals("mm")) {
                        System.out.println("Please select a valid converting unit");
                    }
                    else {
                        System.out.println(metricConverter.convertValues(inputObject, convertValue));
                        isValidSecondaryInput = false;
                    }
                } else if (unitHelperOutput == 3) {
                    if (!convertValue.equals("g") && !convertValue.equals("kg")) {
                        System.out.println("Please select a valid converting unit");
                    }
                    else {
                        System.out.println(metricConverter.convertValues(inputObject, convertValue));
                        isValidSecondaryInput = false;
                    }
                }
            }



            programRun = false;
        }
    }
}