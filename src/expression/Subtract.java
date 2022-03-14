package expression;

public class Subtract extends BinaryOperation {
    public Subtract(InExpression left, InExpression right) {
        super(left, right);
    }

    @Override
    public boolean mustBracket() {
        return false;
    }

    @Override
    public String getOperation() {
        return "-";
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public int getAssociativity() {
        return 1;
    }

    @Override
    public int calc(final int left, final int right) {
        return left - right;
    }

    public String toMiniString() {
        return toMiniString(0, 1);
    }
}
