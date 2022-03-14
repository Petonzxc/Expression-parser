package expression;

public class Abs extends AbstractUnary {
    public Abs(InExpression expression) {
        super(expression);
    }

    @Override
    public int calc(int x) {
        if(x > 0) {
            return x;
        } else {
            return -x;
        }
    }

    @Override
    public String getOperation() {
        return "abs";
    }

    @Override
    public String toMiniString() {
        return super.toMiniString();
    }
}
