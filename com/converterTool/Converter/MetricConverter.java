package com.converterTool.Converter;

import com.converterTool.Units.NumberWithUnits;

public class MetricConverter implements ConverterInterface{
    @Override
    public String convertValues(NumberWithUnits _inputValue, String _targetUnit) {
        int inputIndex = unitIndexer(_inputValue.getUnitSign());
        int targetIndex = unitIndexer(_targetUnit);
        if (targetIndex == 5 || targetIndex == 6)
        {
            if (inputIndex != 5 && inputIndex != 6)
            {
                return "Error";
            }
        }
        return switch (inputIndex) {
            case 1 -> String.valueOf(convertFromMillimeter(_inputValue.getValue(), targetIndex));
            case 2 -> String.valueOf(convertFromCentimeter(_inputValue.getValue(), targetIndex));
            case 3 -> String.valueOf(convertFromMeter(_inputValue.getValue(), targetIndex));
            case 4 -> String.valueOf(convertFromKilometer(_inputValue.getValue(), targetIndex));
            case 5 -> String.valueOf(convertFromGram(_inputValue.getValue(), targetIndex));
            case 6 -> String.valueOf(convertFromKilogram(_inputValue.getValue(), targetIndex));
            default -> "Error";
        };
    }

    public int unitIndexer(String _unit)
    {
        return switch (_unit) {
            case "mm" -> 1;
            case "cm" -> 2;
            case "m" -> 3;
            case "km" -> 4;
            case "g" -> 5;
            case "kg" -> 6;
            default -> 0;
        };
    }

    private double convertFromMillimeter(double _inputLength, int _targetIndex)
    {
        return switch(_targetIndex)
        {
            case 1 -> _inputLength;
            case 2 -> _inputLength / 10;
            case 3 -> _inputLength / 1000;
            case 4 -> _inputLength / 1000000;
            default -> 0;
        };
    }

    private double convertFromCentimeter(double _inputLength, int _targetIndex)
    {
        return switch(_targetIndex)
        {
            case 1 -> _inputLength * 10;
            case 2 -> _inputLength;
            case 3 -> _inputLength / 100;
            case 4 -> _inputLength / 100000;
            default -> 0;
        };
    }

    private double convertFromMeter(double _inputLength, int _targetIndex)
    {
        return switch(_targetIndex)
        {
            case 1 -> _inputLength * 1000;
            case 2 -> _inputLength * 100;
            case 3 -> _inputLength;
            case 4 -> _inputLength / 1000;
            default -> 0;
        };
    }

    private double convertFromKilometer(double _inputLength, int _targetIndex)
    {
        return switch(_targetIndex)
        {
            case 1 -> _inputLength * 1000000;
            case 2 -> _inputLength * 100000;
            case 3 -> _inputLength * 1000;
            case 4 -> _inputLength;
            default -> 0;
        };
    }

    private double convertFromGram(double _inputWeight, int _targetIndex)
    {
        return switch(_targetIndex)
        {
            case 5 -> _inputWeight;
            case 6 -> _inputWeight / 1000;
            default -> 0;
        };
    }

    private double convertFromKilogram(double _inputWeight, int _targetIndex)
    {
        return switch(_targetIndex)
        {
            case 5 -> _inputWeight * 1000;
            case 6 -> _inputWeight;
            default -> 0;
        };
    }

}
