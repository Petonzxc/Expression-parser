package expression.exceptions;

import expression.Divide;
import expression.InExpression;

public class CheckedDivide extends Divide {
    public CheckedDivide(InExpression vx, InExpression vy) {
        super(vx, vy);
    }

    @Override
    public int calc(final int left, final int right) throws DBZException, OverflowException {
        if(right == 0) {
            throw new DBZException("Division by zero");
        }
        if(left == Integer.MIN_VALUE && right == -1) {
            throw new OverflowException("Division overflow");
        }
        return super.calc(left, right);
    }
}
