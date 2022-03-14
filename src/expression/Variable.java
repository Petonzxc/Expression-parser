package expression;

import java.util.InputMismatchException;

public class Variable implements InExpression, TripleExpression {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    public String getVariable(){
        return name;
    }

    @Override
    public boolean mustBracket() {
        return false;
    }

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public int evaluate(int val) {
        return val;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (name) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
            default:
                throw new InputMismatchException("Variable is not x, y or z");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Variable) {
            final Variable cur = (Variable) obj;
            return name.equals(cur.getVariable());
        }
        return false;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String toMiniString() {
        return name;
    }

    @Override
    public int hashCode() {
        return (int)(name.charAt(0));
    }
}
