package com.converterTool.Converter;

import com.converterTool.Units.NumberWithUnits;

public interface ConverterInterface {
    String convertValues(NumberWithUnits _inputValue, String _targetUnit);
    int unitIndexer(String _unit);
}
