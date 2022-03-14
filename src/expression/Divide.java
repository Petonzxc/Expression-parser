package expression;

public class Divide extends BinaryOperation {

    public Divide(InExpression left, InExpression right) {
        super(left, right);
    }

    @Override
    public boolean mustBracket() {
        return true;
    }

    @Override
    public String getOperation() {
        return "/";
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public int getAssociativity() {
        return 1;
    }

    @Override
    public int calc(final int left, final int right) {
        return left / right;
    }

    public String toMiniString() {
        return toMiniString(1, 1);
    }
}
