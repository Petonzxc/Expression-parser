package expression.exceptions;

import expression.InExpression;
import expression.Multiply;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(InExpression vx, InExpression vy) {
        super(vx, vy);
    }

    @Override
    public int calc(final int left, final int right) throws OverflowException {
        final int res = left * right;
        if(OverflowException.IsOverflow(left, right, res)) {
            throw new OverflowException("Multiplication overflow");
        }
        return super.calc(left, right);
    }
}
