package expression.exceptions;

import expression.InExpression;
import expression.UnaryMinus;

public class CheckedNegate extends UnaryMinus {
    public CheckedNegate(InExpression vy) {
        super(vy);
    }

    @Override
    public int calc(final int x) throws OverflowException {
        if(x == Integer.MIN_VALUE) {
            throw new OverflowException("Overflow by unary minus");
        }
        return super.calc(x);
    }
}
