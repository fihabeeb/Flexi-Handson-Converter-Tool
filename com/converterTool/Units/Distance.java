package com.converterTool.Units;

public class Distance extends NumberWithUnits{

    public Distance(double _value, String _unitSign) {
        super(_value, _unitSign);
    }

    public Distance(String _userInputString) {
        super(_userInputString);
    }
}
