package expression;

public class RightNull extends AbstractUnary {
    public RightNull(InExpression expression) {
        super(expression);
    }

    @Override
    public int calc(int x) {
        int result = 0;
        for (int i = 31; i >= 0; i--) {
            if ((x & (1 << i)) == 0) {
                result++;
            } else {
                break;
            }
        }
        return result;
    }

    @Override
    public String getOperation() {
        return "l0";
    }

    @Override
    public String toMiniString() {
        return super.toMiniString();
    }
}
