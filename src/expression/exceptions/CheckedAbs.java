package expression.exceptions;

import expression.Abs;
import expression.InExpression;

public class CheckedAbs extends Abs {
    public CheckedAbs(InExpression vy) {
        super(vy);
    }

    @Override
    public int calc(int x) throws OverflowException {
        if(x == Integer.MIN_VALUE) {
            throw new OverflowException("Overflow by abs");
        }
        return super.calc(x);
    }
}
