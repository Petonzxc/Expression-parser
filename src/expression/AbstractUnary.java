package expression;

public abstract class AbstractUnary implements InExpression {
    private final InExpression expression;

    public AbstractUnary(InExpression expression) {
        this.expression = expression;
    }

    @Override
    public boolean mustBracket() {
        return false;
    }

    protected InExpression getExpression() {
        return expression;
    }

    @Override
    public int getPriority() {
        return 3;
    }

    public abstract int calc(int x);

    public int evaluate(int x) {
        return calc(expression.evaluate(x));
    }

    public int evaluate(int x, int y, int z) {
        return calc(expression.evaluate(x, y , z));
    }

    public abstract String getOperation();

    @Override
    public String toString() {
        return getOperation() + '(' + expression.toString() + ')';
    }

    @Override
    public String toMiniString() {
        if(expression.getPriority() == 3) {
            return getOperation() + ' ' + expression.toMiniString();
        } else {
            return getOperation() + '(' + expression.toMiniString() + ')';
        }
    }
}
