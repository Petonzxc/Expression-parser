package expression.exceptions;

import expression.InExpression;
import expression.Subtract;

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(InExpression vx, InExpression vy) {
        super(vx, vy);
    }

    @Override
    public int calc(final int left, final int right) throws OverflowException {
        if((left >= 0 && right < 0 && left - right < 0) || (left < 0 && right > 0 && left - right >= -1)) {
            throw new OverflowException("Overflow on subtraction");
        }
        return super.calc(left, right);
    }
}
