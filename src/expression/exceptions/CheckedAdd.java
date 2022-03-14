package expression.exceptions;

import expression.Add;
import expression.InExpression;

public class CheckedAdd extends Add {
    public CheckedAdd(InExpression vx, InExpression vy) {
        super(vx, vy);
    }

    @Override
    public int calc(final int left, final int right) throws OverflowException {
        if((left > 0 && right > 0 && left + right < 0) || (left < 0 && right < 0 && left + right >= -1)) {
            throw new OverflowException("Overflow on addition");
        }
        return super.calc(left, right);
    }
}
