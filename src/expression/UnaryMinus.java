package expression;


public class UnaryMinus extends AbstractUnary {
    public UnaryMinus(InExpression expression) {
        super(expression);
    }

    @Override
    public int calc(int x) {
        return -x;
    }

    @Override
    public String getOperation() {
        return "-";
    }

    @Override
    public String toMiniString() {
        return super.toMiniString();
    }
}
