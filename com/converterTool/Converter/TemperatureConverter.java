package com.converterTool.Converter;

import com.converterTool.Units.NumberWithUnits;

public class TemperatureConverter implements ConverterInterface{
    @Override
    public String convertValues(NumberWithUnits _inputValue, String _targetUnit) {
        int inputIndex = unitIndexer(_inputValue.getUnitSign());
        int targetIndex = unitIndexer(_targetUnit);
        return switch (inputIndex) {
            case 1 -> String.valueOf(convertFromKelvin(_inputValue.getValue(), targetIndex)) + " " + _targetUnit;
            case 2 -> String.valueOf(convertFromCelsius(_inputValue.getValue(), targetIndex)) + " " + _targetUnit;
            case 3 -> String.valueOf(convertFromFahrenheit(_inputValue.getValue(), targetIndex)) + " " + _targetUnit;
            default -> "Error";
        };
    }

    public int unitIndexer(String _unit)
    {
        return switch (_unit) {
            case "k" -> 1;
            case "c" -> 2;
            case "f" -> 3;
            default -> 0;
        };
    }

    private double convertFromKelvin(double _kelvinTemp, int _targetIndex)
    {
        return switch(_targetIndex)
        {
            case 1 -> _kelvinTemp;
            case 2 -> _kelvinTemp - 273.15;
            case 3 -> convertFromCelsius(_kelvinTemp - 273.15, 3);
            default -> 0;
        };
    }

    private double convertFromCelsius(double _celsiusTemp, int _targetIndex) {
        return switch(_targetIndex)
        {
            case 1 -> _celsiusTemp + 273.15;
            case 2 -> _celsiusTemp;
            case 3 -> (_celsiusTemp * 9/5) + 32;
            default -> 0;
        };
    }

    private double convertFromFahrenheit(double _fahrenheitTemp, int _targetIndex) {
        return switch(_targetIndex)
        {
            case 1 -> convertFromCelsius((_fahrenheitTemp - 32) * 5/9, 1);
            case 2 -> (_fahrenheitTemp - 32) * 5/9;
            case 3 -> _fahrenheitTemp;
            default -> 0;
        };
    }
}
