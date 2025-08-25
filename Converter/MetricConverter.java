package Converter;

import Units.NumberWithUnits;

public class MetricConverter implements ConverterInterface{
    @Override
    public String convertValues(NumberWithUnits _inputValue, String _targetUnit) {
        return "";
    }

    public int unitIndexer(String _unit)
    {
        return switch (_unit) {
            case "mm" -> 1;
            case "cm" -> 2;
            case "m" -> 3;
            case "km" -> 4;
            default -> 0;
        };
    }

}
