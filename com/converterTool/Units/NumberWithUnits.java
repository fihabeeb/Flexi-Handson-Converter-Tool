package com.converterTool.Units;

public class NumberWithUnits {
    double value;
    String unitSign;
    public NumberWithUnits(double _value, String _unitSign)
    {
        value = _value;
        unitSign = _unitSign;
    }
    public NumberWithUnits(String _userInputString)
    {
        String[] splits = _userInputString.split(" ");
        value = Double.parseDouble(splits[0]);
        unitSign = splits[1].toLowerCase();
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUnitSign() {
        return unitSign;
    }

    public void setUnitSign(String unitSign) {
        this.unitSign = unitSign;
    }
}
