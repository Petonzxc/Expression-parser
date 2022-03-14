package expression;

public class Add extends BinaryOperation {
    public Add(InExpression left, InExpression right) {
        super(left, right);
    }

    @Override
    public boolean mustBracket() {
        return false;
    }

    @Override
    public String getOperation() {
        return "+";
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public int getAssociativity() {
        return 0;
    }

    @Override
    public int calc(final int left, final int right) {
        return left + right;
    }

    public String toMiniString() {
        return toMiniString(0, 0);
    }
}
