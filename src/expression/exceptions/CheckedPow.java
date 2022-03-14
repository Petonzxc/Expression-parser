package expression.exceptions;

import expression.InExpression;
import expression.Pow;

public class CheckedPow extends Pow {
    public CheckedPow(InExpression vx, InExpression vy) {
        super(vx, vy);
    }

    @Override
    public int calc(int left, int right) throws OverflowException, IncorrectPowException {
        if(left == 0 && right == 0) {
            throw new IncorrectPowException("Pow 0 to degree 0");
        }
        if(right < 0) {
            throw new IncorrectPowException("negative degree");
        }
        int ans = 1;
        while(right > 0) {
            int newans = ans * left;
            if(OverflowException.IsOverflow(ans, left, newans)) {
                throw new OverflowException("Overflow on exponentiation");
            }
            right--;
            ans = newans;
        }
        return ans;
    }
}