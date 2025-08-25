package com.converterTool.Units;

public class Temperature extends NumberWithUnits{
    public Temperature(double _value, String _unitSign) {
        super(_value, _unitSign);
    }

    public Temperature(String _userInputString) {
        super(_userInputString);
    }
}
