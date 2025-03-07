package xxl.content;

public  class Functions extends Content {

    String _specs; //Arguments inside the parenthesis
    String _type; //Type of function

    public Functions(String specs) {
        super();
        _specs = specs;
    }
    
    public Functions (String specs, String type) {
        super();
        _specs = specs;
        _type = type;
    
    }
    public Functions(String value, String specs, String type) {
        super(value);  
        _specs = specs;
        _type = type;
    }

    public String getSpecs() {
        return _specs;
    }

    public Functions shallowCopy() {
        Functions c = new Functions(_specs, _type);
        return c;
    }

    public String getType() {
        return _type;
    }
    
    public String calculate() {
        return "";
    }

    public int checkFunction(String phrase) {
        String[] parts = phrase.split("\\(");
        switch (parts[0]) {
            case "=ADD":
                return 1;
            case "=SUB":
                return 2;
            case "=MUL":
                return 3;
            case "=DIV":
                return 4;
            case "=AVERAGE":
                return 5;
            case "=PRODUCT":
                return 6;
            case "=CONCAT":
                return 7;
            case "=COALESCE":
                return 8;
            default:
                return 0; //Not a function
        }
    }

    @Override
    public String toString() {
        if (getValue() == null) {
            return "#VALUE=" + _type + "(" + _specs + ")";
        }
        return getValue() + "=" + _type + "(" + _specs + ")";
    }
}