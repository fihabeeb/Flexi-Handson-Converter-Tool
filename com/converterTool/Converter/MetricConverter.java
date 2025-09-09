package com.converterTool.Converter;

import com.converterTool.Units.NumberWithUnits;

public class MetricConverter implements ConverterInterface{
    @Override
    public String convertValues(NumberWithUnits _inputValue, String _targetUnit) {
        int inputIndex = unitIndexer(_inputValue.getUnitSign());
        int targetIndex = unitIndexer(_targetUnit);
        if (targetIndex == 5 || targetIndex == 6 || targetIndex == 8)
        {
            if (inputIndex != 5 && inputIndex != 6 && inputIndex != 8)
            {
                return "Error";
            }
        }
        return switch (inputIndex) {
            case 1 -> String.valueOf(convertFromMillimeter(_inputValue.getValue(), targetIndex)) + " " + _targetUnit;
            case 2 -> String.valueOf(convertFromCentimeter(_inputValue.getValue(), targetIndex)) + " " + _targetUnit;
            case 3 -> String.valueOf(convertFromMeter(_inputValue.getValue(), targetIndex)) + " " + _targetUnit;
            case 4 -> String.valueOf(convertFromKilometer(_inputValue.getValue(), targetIndex)) + " " + _targetUnit;
            case 5 -> String.valueOf(convertFromGram(_inputValue.getValue(), targetIndex)) + " " + _targetUnit;
            case 6 -> String.valueOf(convertFromKilogram(_inputValue.getValue(), targetIndex)) + " " + _targetUnit;
            case 7 -> String.valueOf(convertFromMiles(_inputValue.getValue(), targetIndex)) + " " + _targetUnit;
            case 8 -> String.valueOf(convertFromPounds(_inputValue.getValue(), targetIndex)) + " " + _targetUnit;
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
            case "mi" -> 7;
            case "lbs" -> 8;
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
            case 7 -> convertFromKilometer(_inputLength / 1000000, 7);
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
            case 7 -> convertFromKilometer(_inputLength / 100000, 7);
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
            case 7 -> convertFromKilometer(_inputLength / 1000,7);
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
            case 7 -> _inputLength / 1.60934;
            default -> 0;
        };
    }

    private double convertFromMiles(double _inputLength, int _targetIndex)
    {
        return switch(_targetIndex)
        {
            case 1 -> convertFromKilometer(_inputLength * 1.60934, 1);
            case 2 -> convertFromKilometer(_inputLength * 1.60934, 2);
            case 3 -> convertFromKilometer(_inputLength * 1.60934, 3);
            case 4 -> _inputLength * 1.60934;
            case 7 -> _inputLength;
            default -> 0;
        };
    }

    private double convertFromGram(double _inputWeight, int _targetIndex)
    {
        return switch(_targetIndex)
        {
            case 5 -> _inputWeight;
            case 6 -> _inputWeight / 1000;
            case 8 -> _inputWeight / 1000 * 2.20462;
            default -> 0;
        };
    }

    private double convertFromKilogram(double _inputWeight, int _targetIndex)
    {
        return switch(_targetIndex)
        {
            case 5 -> _inputWeight * 1000;
            case 6 -> _inputWeight;
            case 8 -> _inputWeight * 2.20462;
            default -> 0;
        };
    }

    private double convertFromPounds(double _inputWeight, int _targetIndex)
    {
        return switch(_targetIndex)
        {
            case 5 -> _inputWeight * 1000 / 2.20462;
            case 6 -> _inputWeight / 2.20462;
            case 8 -> _inputWeight;
            default -> 0;
        };
    }

}
