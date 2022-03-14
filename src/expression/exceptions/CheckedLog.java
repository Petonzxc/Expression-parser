package expression.exceptions;

import expression.InExpression;
import expression.Log;

public class CheckedLog extends Log {
    public CheckedLog(InExpression vx, InExpression vy) {
        super(vx, vy);
    }

    @Override
    public int calc(final int right, final int left) throws LogException {
        if(right <= 0) {
            throw new LogException("Illegal logarithm argument");
        }
        if(left <= 0 || left == 1) {
            throw new LogException("Illegal logarithm base");
        }
        return super.calc(left, right);
    }
}