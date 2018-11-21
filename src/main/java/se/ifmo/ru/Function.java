package se.ifmo.ru;

public enum Function {

    FIRST("y = x*x"), SECOND("y = sin(x)"), THIRD("y = cos(x)");

    private String function;

    Function(String function) {
        this.function = function;
    }

    public double calculateFunction(double x) {
        switch (this) {
            case FIRST:
                return (x * x);
            case SECOND:
                return Math.sin(x);
            case THIRD:
                return Math.cos(x);
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return this.function;
    }
}
