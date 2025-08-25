package Units;

public class Weight extends NumberWithUnits{
    public Weight(double _value, String _unitSign) {
        super(_value, _unitSign);
    }

    public Weight(String _userInputString) {
        super(_userInputString);
    }
}
